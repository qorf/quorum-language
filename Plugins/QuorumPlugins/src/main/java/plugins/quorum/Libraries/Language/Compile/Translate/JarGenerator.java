/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Compile.Translate;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import quorum.Libraries.System.File_;

/**
 * Writes a jar: a zip file whose first entry is META-INF/MANIFEST.MF.
 *
 * Why this class writes the zip format itself: compressing the entries takes
 * most of the time, and they can be compressed independently, so each one is
 * compressed on a thread of its own while the compiler keeps adding files.
 * Java's JarOutputStream can't do that. It compresses each entry itself, one
 * after another on one thread, and has no way to accept data that's already
 * compressed. So the entries are compressed here with the same compressor and
 * level JarOutputStream uses (Deflater.DEFAULT_COMPRESSION, without zlib
 * headers), which gives the same compressed bytes, and Close writes the zip
 * structure around them: a header for each entry, a directory listing them
 * all, and a record saying where that directory is. The fields are the ones
 * JarOutputStream writes.
 *
 * One difference: JarOutputStream doesn't know an entry's sizes or checksum
 * until the entry is written, so it writes them after the data, in a 16-byte
 * "data descriptor". Here they're known first, so they go in the header and
 * there's no descriptor, which makes the jar 16 bytes smaller per entry.
 *
 * A jar too large for the plain zip format (MAXIMUM_ENTRIES entries or
 * MAXIMUM_OFFSET bytes, which need the Zip64 extension) is written with
 * JarOutputStream instead, as it was before.
 *
 * The zip format is described in PKWARE's APPNOTE.TXT. All its numbers are
 * little-endian.
 *
 * @author Andreas Stefik
 */
public class JarGenerator {

    public java.lang.Object me_ = null;
    private final String ENCODING = "UTF-8";
    //private final String WAR_CLASS_DIRECTORY = "WEB-INF/classes";

    // The size of the buffers used for compressing and writing.
    private static final int BUFFER_SIZE = 64 * 1024;

    // The first 4 bytes of each part of a zip file, which say what that part is.
    private static final int LOCAL_FILE_HEADER_SIGNATURE = 0x04034b50;         // "PK\3\4", before each entry's data
    private static final int CENTRAL_DIRECTORY_SIGNATURE = 0x02014b50;         // "PK\1\2", each entry in the directory
    private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 0x06054b50;  // "PK\5\6", the record at the end

    // Zip version 2.0, the version that added deflate compression and
    // directories. It's the "version needed to extract" and "version made by"
    // JarOutputStream writes for deflated entries.
    private static final int ZIP_VERSION = 20;

    // General purpose flag bit 11: entry names are UTF-8. JarOutputStream
    // sets it, as its names are UTF-8.
    private static final int UTF8_NAMES_FLAG = 0x0800;

    // The compression method: 8 means deflate.
    private static final int DEFLATED = ZipEntry.DEFLATED;

    // Fields this class always writes as zero: no comments, a single "disk",
    // and no file attributes, as JarOutputStream writes them.
    private static final int NO_COMMENT = 0;
    private static final int FIRST_DISK = 0;
    private static final int NO_ATTRIBUTES = 0;

    // The size of a local file header before the name and extra field,
    // used to estimate the jar's size before writing it.
    private static final int LOCAL_FILE_HEADER_SIZE = 30;

    // The largest number of entries and offset in bytes the plain zip format
    // can hold. A jar past either needs Zip64.
    private static final int MAXIMUM_ENTRIES = 0xFFFF;
    private static final long MAXIMUM_OFFSET = 0xFFFFFFFFL;

    // The extra field JarOutputStream puts on a jar's first entry, marking
    // the file as a jar: id 0xCAFE, with no data (length 0), little-endian.
    private static final byte[] JAR_MAGIC = {(byte) 0xFE, (byte) 0xCA, 0, 0};
    private static final byte[] NO_EXTRA = new byte[0];

    // Zip files store each entry's time as an MS-DOS date and time: years
    // from 1980, and seconds in steps of 2, packed into 32 bits as
    // year (7 bits), month (4), day (5), hour (5), minute (6), second/2 (5).
    private static final int DOS_FIRST_YEAR = 1980;
    private static final int DOS_YEAR_SHIFT = 25;
    private static final int DOS_MONTH_SHIFT = 21;
    private static final int DOS_DAY_SHIFT = 16;
    private static final int DOS_HOUR_SHIFT = 11;
    private static final int DOS_MINUTE_SHIFT = 5;
    private static final int DOS_SECONDS_PER_STEP = 2;
    // The earliest time that fits, 1980-01-01 00:00:00, used for any earlier time.
    private static final int DOS_EARLIEST_TIME = (1 << DOS_MONTH_SHIFT) | (1 << DOS_DAY_SHIFT);

    // Masks and shifts for writing numbers a byte at a time.
    private static final int BYTE_MASK = 0xFF;
    private static final int SHORT_MASK = 0xFFFF;
    private static final int BITS_PER_BYTE = 8;
    private static final int BITS_PER_SHORT = 16;

    private File writeLocation = null;
    private Manifest manifest = null;
    // Compresses the entries, one thread per core but one, so the compiler's
    // own thread keeps a core.
    private ExecutorService compressors = null;
    // Every entry, in the order it was added. Each finishes when its
    // compressor does.
    private final List<Future<Entry>> entries = new ArrayList<>();

    /** One entry: read in Add, then compressed on a compressor thread. */
    private static class Entry {
        String name;
        byte[] data;        // the file as read from disk
        byte[] compressed;  // raw deflate data, without zlib's header
        long crc;           // CRC-32 of data, which zip readers check
        long time;          // when the file was last modified
    }

    public void Open(quorum.Libraries.System.File_ location) {
        quorum.Libraries.Language.Compile.Translate.JarGenerator generator
                = (quorum.Libraries.Language.Compile.Translate.JarGenerator) me_;
        String manifestValue = generator.GetManifest();
        manifest = CreateManifest(manifestValue);
        writeLocation = new File(location.GetAbsolutePath());
        if (!writeLocation.exists()) {
            writeLocation.getParentFile().mkdirs();
        }

        entries.clear();
        int threads = Math.max(1, Runtime.getRuntime().availableProcessors() - 1);
        // Daemon threads, so a generator that's never closed doesn't keep
        // the program running.
        compressors = Executors.newFixedThreadPool(threads, runnable -> {
            Thread thread = new Thread(runnable, "Quorum Jar Compressor");
            thread.setDaemon(true);
            return thread;
        });

        // The manifest is the first entry, as JarOutputStream writes it.
        if (manifest != null) {
            try {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                manifest.write(bytes);
                Submit(JarFile.MANIFEST_NAME, bytes.toByteArray(), System.currentTimeMillis());
            } catch (IOException ex) {
                Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
     * Waits for every entry to be compressed, then writes the jar. Nothing
     * is written to the jar's file until now, because the entries must go in
     * the order they were added, and each entry's offset in the file is only
     * known once the entries before it are written.
     */
    public void Close() {
        try {
            List<Entry> done = new ArrayList<>(entries.size());
            long size = 0;
            for (Future<Entry> future : entries) {
                Entry entry = future.get();
                done.add(entry);
                size = size + LOCAL_FILE_HEADER_SIZE + entry.name.length() + entry.compressed.length;
            }

            if (done.size() >= MAXIMUM_ENTRIES || size >= MAXIMUM_OFFSET) {
                WriteWithJarOutputStream(done);
            } else {
                WriteZip(done);
            }
        } catch (Exception ex) {
            Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            compressors.shutdown();
            entries.clear();
        }
    }

    public void Add(File_ source) {
        Add(source, source.GetPath());
    }

    public void Add(File_ source, String path) {
        String name = path.replace("\\", "/");
        boolean directory = source.IsDirectory();
        if (directory && !name.endsWith("/")) {
            name = name + "/";
        }

        // The file is read now, as before, so the jar holds the file as it
        // was when it was added. Only the compressing moves to another thread.
        byte[] data = new byte[0];
        if (!directory) {
            try {
                data = Files.readAllBytes(Paths.get(source.GetAbsolutePath()));
            } catch (IOException ex) {
                Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Submit(name, data, (long) source.GetLastModifiedNative());
    }

    /*
     * Starts compressing an entry on a compressor thread. This is the work
     * JarOutputStream did one entry at a time, while the entry was written.
     */
    private void Submit(String name, byte[] data, long time) {
        entries.add(compressors.submit(() -> {
            Entry entry = new Entry();
            entry.name = name;
            entry.data = data;
            entry.time = time;

            CRC32 crc = new CRC32();
            crc.update(data);
            entry.crc = crc.getValue();

            // true: raw deflate data without zlib's header and checksum, as
            // zip files hold it (the checksum is the CRC-32 above instead).
            Deflater deflater = new Deflater(Deflater.DEFAULT_COMPRESSION, true);
            try {
                deflater.setInput(data);
                deflater.finish();
                // Room for the whole input; class files compress to about a third.
                ByteArrayOutputStream out = new ByteArrayOutputStream(data.length);
                byte[] buffer = new byte[BUFFER_SIZE];
                while (!deflater.finished()) {
                    int count = deflater.deflate(buffer);
                    out.write(buffer, 0, count);
                }
                entry.compressed = out.toByteArray();
            } finally {
                deflater.end();
            }
            return entry;
        }));
    }

    /*
     * Writes the jar in the zip format, the part JarOutputStream did. A zip
     * file is:
     * 1. for each entry, a local file header followed by its compressed data;
     * 2. the central directory: a header for each entry again, with where its
     *    local header starts, which is what zip readers use to find entries;
     * 3. the end of central directory record, saying where the directory
     *    starts, its size, and how many entries there are. Readers find the
     *    file's structure from this record, at the end.
     */
    private void WriteZip(List<Entry> done) throws IOException {
        try (ZipOut out = new ZipOut(new BufferedOutputStream(new FileOutputStream(writeLocation), BUFFER_SIZE))) {
            // 1. Each entry's local header and data, remembering where each starts.
            long[] offsets = new long[done.size()];
            for (int i = 0; i < done.size(); i++) {
                Entry entry = done.get(i);
                byte[] name = entry.name.getBytes(StandardCharsets.UTF_8);
                byte[] extra = i == 0 ? JAR_MAGIC : NO_EXTRA;
                offsets[i] = out.count;

                out.Int(LOCAL_FILE_HEADER_SIGNATURE);
                out.Short(ZIP_VERSION);             // version needed to extract
                out.Short(UTF8_NAMES_FLAG);
                out.Short(DEFLATED);
                out.Int(DosTime(entry.time));       // time and date
                out.Int((int) entry.crc);
                out.Int(entry.compressed.length);   // compressed size
                out.Int(entry.data.length);         // uncompressed size
                out.Short(name.length);
                out.Short(extra.length);
                out.write(name);
                out.write(extra);
                out.write(entry.compressed);
            }

            // 2. The central directory, one header per entry.
            long directoryStart = out.count;
            for (int i = 0; i < done.size(); i++) {
                Entry entry = done.get(i);
                byte[] name = entry.name.getBytes(StandardCharsets.UTF_8);
                byte[] extra = i == 0 ? JAR_MAGIC : NO_EXTRA;

                out.Int(CENTRAL_DIRECTORY_SIGNATURE);
                out.Short(ZIP_VERSION);             // version made by
                out.Short(ZIP_VERSION);             // version needed to extract
                out.Short(UTF8_NAMES_FLAG);
                out.Short(DEFLATED);
                out.Int(DosTime(entry.time));
                out.Int((int) entry.crc);
                out.Int(entry.compressed.length);
                out.Int(entry.data.length);
                out.Short(name.length);
                out.Short(extra.length);
                out.Short(NO_COMMENT);              // file comment length
                out.Short(FIRST_DISK);              // disk the entry starts on
                out.Short(NO_ATTRIBUTES);           // internal file attributes
                out.Int(NO_ATTRIBUTES);             // external file attributes
                out.Int((int) offsets[i]);          // where its local header starts
                out.write(name);
                out.write(extra);
            }
            long directorySize = out.count - directoryStart;

            // 3. The end of central directory record.
            out.Int(END_OF_CENTRAL_DIRECTORY_SIGNATURE);
            out.Short(FIRST_DISK);                  // this disk's number
            out.Short(FIRST_DISK);                  // disk where the directory starts
            out.Short(done.size());                 // entries on this disk
            out.Short(done.size());                 // entries in all
            out.Int((int) directorySize);
            out.Int((int) directoryStart);
            out.Short(NO_COMMENT);                  // zip file comment length
        }
    }

    /*
     * Writes a jar too large for WriteZip with JarOutputStream, as all jars
     * were written before, from the data read in Add. JarOutputStream writes
     * the manifest itself, so that entry is skipped.
     */
    private void WriteWithJarOutputStream(List<Entry> done) throws IOException {
        try (JarOutputStream target = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(writeLocation), BUFFER_SIZE), manifest)) {
            for (Entry entry : done) {
                if (JarFile.MANIFEST_NAME.equals(entry.name)) {
                    continue;
                }
                JarEntry jarEntry = new JarEntry(entry.name);
                jarEntry.setTime(entry.time);
                target.putNextEntry(jarEntry);
                target.write(entry.data);
                target.closeEntry();
            }
        }
    }

    /*
     * Converts a Java time (milliseconds since 1970) to the MS-DOS date and
     * time zip entries hold, in local time, as JarEntry:setTime does.
     */
    private static int DosTime(long time) {
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        if (date.getYear() < DOS_FIRST_YEAR) {
            return DOS_EARLIEST_TIME;
        }
        return ((date.getYear() - DOS_FIRST_YEAR) << DOS_YEAR_SHIFT)
                | (date.getMonthValue() << DOS_MONTH_SHIFT)
                | (date.getDayOfMonth() << DOS_DAY_SHIFT)
                | (date.getHour() << DOS_HOUR_SHIFT)
                | (date.getMinute() << DOS_MINUTE_SHIFT)
                | (date.getSecond() / DOS_SECONDS_PER_STEP);
    }

    /**
     * Writes zip numbers, which are little-endian (lowest byte first), and
     * counts the bytes written so far, which are the offsets the directory
     * needs.
     */
    private static class ZipOut extends java.io.FilterOutputStream {
        long count = 0;

        ZipOut(OutputStream out) {
            super(out);
        }

        @Override
        public void write(int b) throws IOException {
            out.write(b);
            count++;
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            out.write(b, off, len);
            count += len;
        }

        // A 2-byte number.
        void Short(int value) throws IOException {
            write(value & BYTE_MASK);
            write((value >>> BITS_PER_BYTE) & BYTE_MASK);
        }

        // A 4-byte number.
        void Int(int value) throws IOException {
            Short(value & SHORT_MASK);
            Short((value >>> BITS_PER_SHORT) & SHORT_MASK);
        }
    }

    private Manifest CreateManifest(String total) {
        try {
            InputStream stream = new ByteArrayInputStream(total.getBytes(ENCODING));
            Manifest manifest = new Manifest();
            manifest.read(stream);
            return manifest;
        } catch (IOException ex) {
            Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String GetManifestVersion() {
        return Attributes.Name.MANIFEST_VERSION.toString();
    }

    public String GetManifestMainClass() {
        return Attributes.Name.MAIN_CLASS.toString();
    }

    public String GetManifestClassPath() {
        return Attributes.Name.CLASS_PATH.toString();
    }
}
