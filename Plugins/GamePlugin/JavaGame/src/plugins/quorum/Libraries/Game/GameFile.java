/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import plugins.quorum.Libraries.Game.GameFileHandler;
import plugins.quorum.Libraries.Game.GameFileHandler.FileType;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import plugins.quorum.Libraries.Game.StreamUtilities;

import quorum.Libraries.System.File_;

/**
 * This class represents the different types of files that may be used.
 * This class will be replaced with an inheriting class of an appropriate
 * type based on what backend is necessary for a certain operating system.
 * 
 * @author alleew
 */
public class GameFile 
{
    protected File file;
    protected FileType type;

    protected GameFile () {
    }
    
    public GameFile(quorum.Libraries.System.File_ quorumFile)
    {
        
    }

    /** Creates a new absolute GameFile for the file name. Use this for tools on the desktop that don't need any of the backends.
    * Do not use this constructor in case you write something cross-platform. Use the {@link Files} interface instead.
    * @param fileName the filename. */
    public GameFile (String fileName) 
    {
	this.file = new File(fileName);
	this.type = FileType.Absolute;
    }

    /** Creates a new absolute GameFile for the {@link File}. Use this for tools on the desktop that don't need any of the
    * backends. Do not use this constructor in case you write something cross-platform. Use the {@link Files} interface instead.
    * @param file the file. */
    public GameFile (File file) 
    {
	this.file = file;
	this.type = FileType.Absolute;
    }

    protected GameFile (String fileName, FileType type) 
    {
	this.type = type;
	file = new File(fileName);
    }

    protected GameFile (File file, FileType type) 
    {
	this.file = file;
	this.type = type;
    }

    /** @return the path of the file as specified on construction, e.g. Gdx.files.internal("dir/file.png") -> dir/file.png. backward
    *         slashes will be replaced by forward slashes. */
    public String Path () 
    {
	return file.getPath().replace('\\', '/');
    }

    /** @return the name of the file, without any parent paths. */
    public String GetName () 
    {
	return file.getName();
    }

    public String Extension () 
    {
	String name = file.getName();
	int dotIndex = name.lastIndexOf('.');
	if (dotIndex == -1) return "";
        return name.substring(dotIndex + 1);
    }

    /** @return the name of the file, without parent paths or the extension. */
    public String NameWithoutExtension () 
    {
	String name = file.getName();
	int dotIndex = name.lastIndexOf('.');
	if (dotIndex == -1) return name;
	return name.substring(0, dotIndex);
    }

/** @return the path and filename without the extension, e.g. dir/dir2/file.png -> dir/dir2/file. backward slashes will be
*         returned as forward slashes. */
    public String PathWithoutExtension () 
    {
        String path = file.getPath().replace('\\', '/');
        int dotIndex = path.lastIndexOf('.');
        if (dotIndex == -1) return path;
        return path.substring(0, dotIndex);
    }

    public FileType Type () 
    {
        return type;
    }

    /** Returns a java.io.File that represents this file handle. Note the returned file will only be usable for
    * {@link FileType#Absolute} and {@link FileType#External} file handles. */
    public File GetFile() 
    {
        if (type == FileType.External) return new File(GameStateManager.fileHandler.GetExternalStoragePath(), file.getPath());
        return file;
    }

    /** Returns a stream for reading this file as bytes.
    * @throws GameRuntimeError if the file handle represents a directory, doesn't exist, or could not be read. */
    public InputStream Read () 
    {
        if (type == FileType.Classpath || (type == FileType.Internal && !GetFile().exists())
            || (type == FileType.Local && !GetFile().exists())) 
        {
            InputStream input = GameFile.class.getResourceAsStream("/" + file.getPath().replace('\\', '/'));
            if (input == null) throw new GameRuntimeError("File not found: " + file + " (" + type + ")");
                return input;
        }
        try 
        {
            return new FileInputStream(GetFile());
        } catch (Exception ex) 
        {
            if (GetFile().isDirectory())
                throw new GameRuntimeError("Cannot open a stream to a directory: " + file + " (" + type + ")", ex);
            throw new GameRuntimeError("Error reading file: " + file + " (" + type + ")", ex);
        }
    }

    /** Returns a buffered stream for reading this file as bytes.
    * @throws GameRuntimeError if the file handle represents a directory, doesn't exist, or could not be read. */
    public BufferedInputStream Read (int bufferSize) 
    {
        return new BufferedInputStream(Read(), bufferSize);
    }

    /** Returns a reader for reading this file as characters.
    * @throws GameRuntimeError if the file handle represents a directory, doesn't exist, or could not be read. */
    public Reader Reader () {
        return new InputStreamReader(Read());
    }

    /** Returns a reader for reading this file as characters.
    * @throws GameRuntimeError if the file handle represents a directory, doesn't exist, or could not be read. */
    public Reader Reader (String charset) 
    {
        InputStream stream = Read();
        try {
                return new InputStreamReader(stream, charset);
            } catch (UnsupportedEncodingException ex) {
                StreamUtilities.CloseQuietly(stream);
                throw new GameRuntimeError("Error reading file: " + this, ex);
            }
    }

    /** Returns a buffered reader for reading this file as characters.
    * @throws GameRuntimeError if the file handle represents a directory, doesn't exist, or could not be read. */
    public BufferedReader Reader (int bufferSize) {
        return new BufferedReader(new InputStreamReader(Read()), bufferSize);
    }

    /** Returns a buffered reader for reading this file as characters.
    * @throws GameRuntimeError if the file handle represents a directory, doesn't exist, or could not be read. */
    public BufferedReader Reader (int bufferSize, String charset) {
        try 
        {
            return new BufferedReader(new InputStreamReader(Read(), charset), bufferSize);
        }
        catch (UnsupportedEncodingException ex) 
        {
            throw new GameRuntimeError("Error reading file: " + this, ex);
        }
    }

    /** Reads the entire file into a string using the platform's default charset.
    * @throws GameRuntimeError if the file handle represents a directory, doesn't exist, or could not be read. */
    public String ReadString () {
            return ReadString(null);
    }

    /** Reads the entire file into a string using the specified charset.
    * @param charset If null the default charset is used.
    * @throws GameRuntimeError if the file handle represents a directory, doesn't exist, or could not be read. */
    public String ReadString (String charset) {
            StringBuilder output = new StringBuilder(EstimateLength());
            InputStreamReader reader = null;
            try {
                    if (charset == null)
                            reader = new InputStreamReader(Read());
                    else
                            reader = new InputStreamReader(Read(), charset);
                    char[] buffer = new char[256];
                    while (true) {
                            int length = reader.read(buffer);
                            if (length == -1) break;
                            output.append(buffer, 0, length);
                    }
            } catch (IOException ex) {
                    throw new GameRuntimeError("Error reading layout file: " + this, ex);
            } finally {
                    StreamUtilities.CloseQuietly(reader);
            }
            return output.toString();
    }

    /** Reads the entire file into a byte array.
    * @throws GameRuntimeError if the file handle represents a directory, doesn't exist, or could not be read. */
    public byte[] ReadBytes () {
            InputStream input = Read();
            try {
                    return StreamUtilities.CopyStreamToByteArray(input, EstimateLength());
            } catch (IOException ex) {
                    throw new GameRuntimeError("Error reading file: " + this, ex);
            } finally {
                    StreamUtilities.CloseQuietly(input);
            }
    }

    private int EstimateLength () 
    {
            int length = (int)Length();
            return length != 0 ? length : 512;
    }

/** Reads the entire file into the byte array. The byte array must be big enough to hold the file's data.
* @param bytes the array to load the file into
* @param offset the offset to start writing bytes
* @param size the number of bytes to read, see {@link #length()}
* @return the number of read bytes */
    public int ReadBytes (byte[] bytes, int offset, int size) {
            InputStream input = Read();
            int position = 0;
            try {
                    while (true) {
                            int count = input.read(bytes, offset + position, size - position);
                            if (count <= 0) break;
                            position += count;
                    }
            } catch (IOException ex) {
                    throw new GameRuntimeError("Error reading file: " + this, ex);
            } finally {
                    StreamUtilities.CloseQuietly(input);
            }
            return position - offset;
    }

    /** Returns a stream for writing to this file. Parent directories will be created if necessary.
    * @param append If false, this file will be overwritten if it exists, otherwise it will be appended.
    * @throws GameRuntimeError if this file handle represents a directory, if it is a {@link FileType#Classpath} or
    *            {@link FileType#Internal} file, or if it could not be written. */
    public OutputStream Write (boolean append) {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot write to a classpath file: " + file);
            if (type == FileType.Internal) throw new GameRuntimeError("Cannot write to an internal file: " + file);
            Parent().mkdirs();
            try {
                    return new FileOutputStream(GetFile(), append);
            } catch (Exception ex) {
                    if (GetFile().isDirectory())
                            throw new GameRuntimeError("Cannot open a stream to a directory: " + file + " (" + type + ")", ex);
                    throw new GameRuntimeError("Error writing file: " + file + " (" + type + ")", ex);
            }
    }

    /** Returns a buffered stream for writing to this file. Parent directories will be created if necessary.
    * @param append If false, this file will be overwritten if it exists, otherwise it will be appended.
    * @param bufferSize The size of the buffer.
    * @throws GameRuntimeError if this file handle represents a directory, if it is a {@link FileType#Classpath} or
    *            {@link FileType#Internal} file, or if it could not be written. */
    public OutputStream Write (boolean append, int bufferSize) {
            return new BufferedOutputStream(Write(append), bufferSize);
    }

    /** Reads the remaining bytes from the specified stream and writes them to this file. The stream is closed. Parent directories
    * will be created if necessary.
    * @param append If false, this file will be overwritten if it exists, otherwise it will be appended.
    * @throws GameRuntimeError if this file handle represents a directory, if it is a {@link FileType#Classpath} or
    *            {@link FileType#Internal} file, or if it could not be written. */
    public void Write (InputStream input, boolean append) {
            OutputStream output = null;
            try {
                    output = Write(append);
                    StreamUtilities.CopyStream(input, output, 4096);
            } catch (Exception ex) {
                    throw new GameRuntimeError("Error stream writing to file: " + file + " (" + type + ")", ex);
            } finally {
                    StreamUtilities.CloseQuietly(input);
                    StreamUtilities.CloseQuietly(output);
            }

    }

    /** Returns a writer for writing to this file using the default charset. Parent directories will be created if necessary.
    * @param append If false, this file will be overwritten if it exists, otherwise it will be appended.
    * @throws GameRuntimeError if this file handle represents a directory, if it is a {@link FileType#Classpath} or
    *            {@link FileType#Internal} file, or if it could not be written. */
    public Writer Writer (boolean append) {
            return Writer(append, null);
    }

    /** Returns a writer for writing to this file. Parent directories will be created if necessary.
    * @param append If false, this file will be overwritten if it exists, otherwise it will be appended.
    * @param charset May be null to use the default charset.
    * @throws GameRuntimeError if this file handle represents a directory, if it is a {@link FileType#Classpath} or
    *            {@link FileType#Internal} file, or if it could not be written. */
    public Writer Writer (boolean append, String charset) {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot write to a classpath file: " + file);
            if (type == FileType.Internal) throw new GameRuntimeError("Cannot write to an internal file: " + file);
            Parent().mkdirs();
            try {
                    FileOutputStream output = new FileOutputStream(GetFile(), append);
                    if (charset == null)
                            return new OutputStreamWriter(output);
                    else
                            return new OutputStreamWriter(output, charset);
            } catch (IOException ex) {
                    if (GetFile().isDirectory())
                            throw new GameRuntimeError("Cannot open a stream to a directory: " + file + " (" + type + ")", ex);
                    throw new GameRuntimeError("Error writing file: " + file + " (" + type + ")", ex);
            }
    }

    /** Writes the specified string to the file using the default charset. Parent directories will be created if necessary.
    * @param append If false, this file will be overwritten if it exists, otherwise it will be appended.
    * @throws GameRuntimeError if this file handle represents a directory, if it is a {@link FileType#Classpath} or
    *            {@link FileType#Internal} file, or if it could not be written. */
    public void WriteString (String string, boolean append) {
            WriteString(string, append, null);
    }

    /** Writes the specified string to the file using the specified charset. Parent directories will be created if necessary.
    * @param append If false, this file will be overwritten if it exists, otherwise it will be appended.
    * @param charset May be null to use the default charset.
    * @throws GameRuntimeError if this file handle represents a directory, if it is a {@link FileType#Classpath} or
    *            {@link FileType#Internal} file, or if it could not be written. */
    public void WriteString (String string, boolean append, String charset) {
            Writer writer = null;
            try {
                    writer = Writer(append, charset);
                    writer.write(string);
            } catch (Exception ex) {
                    throw new GameRuntimeError("Error writing file: " + file + " (" + type + ")", ex);
            } finally {
                    StreamUtilities.CloseQuietly(writer);
            }
    }

    /** Writes the specified bytes to the file. Parent directories will be created if necessary.
    * @param append If false, this file will be overwritten if it exists, otherwise it will be appended.
    * @throws GameRuntimeError if this file handle represents a directory, if it is a {@link FileType#Classpath} or
    *            {@link FileType#Internal} file, or if it could not be written. */
    public void WriteBytes (byte[] bytes, boolean append) {
            OutputStream output = Write(append);
            try {
                    output.write(bytes);
            } catch (IOException ex) {
                    throw new GameRuntimeError("Error writing file: " + file + " (" + type + ")", ex);
            } finally {
                    StreamUtilities.CloseQuietly(output);
            }
    }

    /** Writes the specified bytes to the file. Parent directories will be created if necessary.
    * @param append If false, this file will be overwritten if it exists, otherwise it will be appended.
    * @throws GameRuntimeError if this file handle represents a directory, if it is a {@link FileType#Classpath} or
    *            {@link FileType#Internal} file, or if it could not be written. */
    public void WriteBytes (byte[] bytes, int offset, int length, boolean append) {
            OutputStream output = Write(append);
            try {
                    output.write(bytes, offset, length);
            } catch (IOException ex) {
                    throw new GameRuntimeError("Error writing file: " + file + " (" + type + ")", ex);
            } finally {
                    StreamUtilities.CloseQuietly(output);
            }
    }

    /** Returns the paths to the children of this directory. Returns an empty list if this file handle represents a file and not a
    * directory. On the desktop, an {@link FileType#Internal} handle to a directory on the classpath will return a zero length
    * array.
    * @throws GameRuntimeError if this file is an {@link FileType#Classpath} file. */
    public GameFile[] List () {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot list a classpath directory: " + file);
            String[] relativePaths = GetFile().list();
            if (relativePaths == null) return new GameFile[0];
            GameFile[] handles = new GameFile[relativePaths.length];
            for (int i = 0, n = relativePaths.length; i < n; i++)
                    handles[i] = Child(relativePaths[i]);
            return handles;
    }

    /** Returns the paths to the children of this directory that satisfy the specified filter. Returns an empty list if this file
    * handle represents a file and not a directory. On the desktop, an {@link FileType#Internal} handle to a directory on the
    * classpath will return a zero length array.
    * @param filter the {@link FileFilter} to filter files
    * @throws GameRuntimeError if this file is an {@link FileType#Classpath} file. */
    public GameFile[] List (FileFilter filter) {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot list a classpath directory: " + file);
            File file = GetFile();
            String[] relativePaths = file.list();
            if (relativePaths == null) return new GameFile[0];
            GameFile[] handles = new GameFile[relativePaths.length];
            int count = 0;
            for (int i = 0, n = relativePaths.length; i < n; i++) {
                    String path = relativePaths[i];
                    GameFile child = Child(path);
                    if (!filter.accept(child.GetFile())) continue;
                    handles[count] = child;
                    count++;
            }
            if (count < relativePaths.length) {
                    GameFile[] newHandles = new GameFile[count];
                    System.arraycopy(handles, 0, newHandles, 0, count);
                    handles = newHandles;
            }
            return handles;
    }

    /** Returns the paths to the children of this directory that satisfy the specified filter. Returns an empty list if this file
    * handle represents a file and not a directory. On the desktop, an {@link FileType#Internal} handle to a directory on the
    * classpath will return a zero length array.
    * @param filter the {@link FilenameFilter} to filter files
    * @throws GameRuntimeError if this file is an {@link FileType#Classpath} file. */
    public GameFile[] List (FilenameFilter filter) {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot list a classpath directory: " + file);
            File file = GetFile();
            String[] relativePaths = file.list();
            if (relativePaths == null) return new GameFile[0];
            GameFile[] handles = new GameFile[relativePaths.length];
            int count = 0;
            for (int i = 0, n = relativePaths.length; i < n; i++) {
                    String path = relativePaths[i];
                    if (!filter.accept(file, path)) continue;
                    handles[count] = Child(path);
                    count++;
            }
            if (count < relativePaths.length) {
                    GameFile[] newHandles = new GameFile[count];
                    System.arraycopy(handles, 0, newHandles, 0, count);
                    handles = newHandles;
            }
            return handles;
    }

    /** Returns the paths to the children of this directory with the specified suffix. Returns an empty list if this file handle
    * represents a file and not a directory. On the desktop, an {@link FileType#Internal} handle to a directory on the classpath
    * will return a zero length array.
    * @throws GameRuntimeError if this file is an {@link FileType#Classpath} file. */
    public GameFile[] List (String suffix) {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot list a classpath directory: " + file);
            String[] relativePaths = GetFile().list();
            if (relativePaths == null) return new GameFile[0];
            GameFile[] handles = new GameFile[relativePaths.length];
            int count = 0;
            for (int i = 0, n = relativePaths.length; i < n; i++) {
                    String path = relativePaths[i];
                    if (!path.endsWith(suffix)) continue;
                    handles[count] = Child(path);
                    count++;
            }
            if (count < relativePaths.length) {
                    GameFile[] newHandles = new GameFile[count];
                    System.arraycopy(handles, 0, newHandles, 0, count);
                    handles = newHandles;
            }
            return handles;
    }

    /** Returns true if this file is a directory. Always returns false for classpath files. On Android, an {@link FileType#Internal}
    * handle to an empty directory will return false. On the desktop, an {@link FileType#Internal} handle to a directory on the
    * classpath will return false. */
    public boolean IsDirectory () {
            if (type == FileType.Classpath) return false;
            return GetFile().isDirectory();
    }

    /** Returns a handle to the child with the specified name.
    * @throws GameRuntimeError if this file handle is a {@link FileType#Classpath} or {@link FileType#Internal} and the child
    *            doesn't exist. */
    public GameFile Child (String name) {
            if (file.getPath().length() == 0) return new GameFile(new File(name), type);
            return new GameFile(new File(file, name), type);
    }

    /** Returns a handle to the sibling with the specified name.
     * @throws GameRuntimeError if this file handle is a {@link FileType#Classpath} or {@link FileType#Internal} and the sibling
     *            doesn't exist, or this file is the root. */
    public GameFile Sibling (String name) {
            if (file.getPath().length() == 0) throw new GameRuntimeError("Cannot get the sibling of the root.");
            return new GameFile(new File(file.getParent(), name), type);
    }

    public GameFile Parent () {
            File parent = file.getParentFile();
            if (parent == null) {
                    if (type == FileType.Absolute)
                            parent = new File("/");
                    else
                            parent = new File("");
            }
            return new GameFile(parent, type);
    }

    /** @throws GameRuntimeError if this file handle is a {@link FileType#Classpath} or {@link FileType#Internal} file. */
    public void mkdirs () {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot mkdirs with a classpath file: " + file);
            if (type == FileType.Internal) throw new GameRuntimeError("Cannot mkdirs with an internal file: " + file);
            GetFile().mkdirs();
    }

    /** Returns true if the file exists. On Android, a {@link FileType#Classpath} or {@link FileType#Internal} handle to a directory
     * will always return false. Note that this can be very slow for internal files on Android! */
    public boolean Exists () {
            switch (type) {
            case Internal:
                    if (GetFile().exists()) return true;
                    // Fall through.
            case Classpath:
                    return GameFile.class.getResource("/" + file.getPath().replace('\\', '/')) != null;
            }
            return GetFile().exists();
    }

    /** Deletes this file or empty directory and returns success. Will not delete a directory that has children.
     * @throws GameRuntimeError if this file handle is a {@link FileType#Classpath} or {@link FileType#Internal} file. */
    public boolean Delete () {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot delete a classpath file: " + file);
            if (type == FileType.Internal) throw new GameRuntimeError("Cannot delete an internal file: " + file);
            return GetFile().delete();
    }

    /** Deletes this file or directory and all children, recursively.
     * @throws GameRuntimeError if this file handle is a {@link FileType#Classpath} or {@link FileType#Internal} file. */
    public boolean DeleteDirectory () {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot delete a classpath file: " + file);
            if (type == FileType.Internal) throw new GameRuntimeError("Cannot delete an internal file: " + file);
            return DeleteDirectory(GetFile());
    }

    /** Deletes all children of this directory, recursively.
    * @throws GameRuntimeError if this file handle is a {@link FileType#Classpath} or {@link FileType#Internal} file. */
    public void EmptyDirectory () {
            EmptyDirectory(false);
    }

    /** Deletes all children of this directory, recursively. Optionally preserving the folder structure.
    * @throws GameRuntimeError if this file handle is a {@link FileType#Classpath} or {@link FileType#Internal} file. */
    public void EmptyDirectory (boolean preserveTree) {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot delete a classpath file: " + file);
            if (type == FileType.Internal) throw new GameRuntimeError("Cannot delete an internal file: " + file);
            EmptyDirectory(GetFile(), preserveTree);
    }

    /** Copies this file or directory to the specified file or directory. If this handle is a file, then 1) if the destination is a
    * file, it is overwritten, or 2) if the destination is a directory, this file is copied into it, or 3) if the destination
    * doesn't exist, {@link #mkdirs()} is called on the destination's parent and this file is copied into it with a new name. If
    * this handle is a directory, then 1) if the destination is a file, GameRuntimeError is thrown, or 2) if the destination is
    * a directory, this directory is copied into it recursively, overwriting existing files, or 3) if the destination doesn't
    * exist, {@link #mkdirs()} is called on the destination and this directory is copied into it recursively.
    * @throws GameRuntimeError if the destination file handle is a {@link FileType#Classpath} or {@link FileType#Internal}
    *            file, or copying failed. */
    public void CopyTo (GameFile dest) {
            boolean sourceDir = IsDirectory();
            if (!sourceDir) {
                    if (dest.IsDirectory()) dest = dest.Child(GetName());
                    CopyFile(this, dest);
                    return;
            }
            if (dest.Exists()) {
                    if (!dest.IsDirectory()) throw new GameRuntimeError("Destination exists but is not a directory: " + dest);
            } else {
                    dest.mkdirs();
                    if (!dest.IsDirectory()) throw new GameRuntimeError("Destination directory cannot be created: " + dest);
            }
            if (!sourceDir) dest = dest.Child(GetName());
            CopyDirectory(this, dest);
    }

    /** Moves this file to the specified file, overwriting the file if it already exists.
    * @throws GameRuntimeError if the source or destination file handle is a {@link FileType#Classpath} or
    *            {@link FileType#Internal} file. */
    public void MoveTo (GameFile dest) {
            if (type == FileType.Classpath) throw new GameRuntimeError("Cannot move a classpath file: " + file);
            if (type == FileType.Internal) throw new GameRuntimeError("Cannot move an internal file: " + file);
            CopyTo(dest);
            Delete();
            if (Exists() && IsDirectory()) DeleteDirectory();
    }

    /** Returns the length in bytes of this file, or 0 if this file is a directory, does not exist, or the size cannot otherwise be
    * determined. */
    public long Length () {
            if (type == FileType.Classpath || (type == FileType.Internal && !file.exists())) {
                    InputStream input = Read();
                    try {
                            return input.available();
                    } catch (Exception ignored) {
                    } finally {
                            StreamUtilities.CloseQuietly(input);
                    }
                    return 0;
            }
            return GetFile().length();
    }

    /** Returns the last modified time in milliseconds for this file. Zero is returned if the file doesn't exist. Zero is returned
    * for {@link FileType#Classpath} files. On Android, zero is returned for {@link FileType#Internal} files. On the desktop, zero
    * is returned for {@link FileType#Internal} files on the classpath. */
    public long LastModified () {
            return GetFile().lastModified();
    }

    @Override
    public boolean equals (Object obj) {
            if (!(obj instanceof GameFile)) return false;
            GameFile other = (GameFile)obj;
            return type == other.type && Path().equals(other.Path());
    }

    @Override
    public int hashCode () {
            int hash = 1;
            hash = hash * 37 + type.hashCode();
            hash = hash * 67 + Path().hashCode();
            return hash;
    }

    public String ToString () {
            return file.getPath().replace('\\', '/');
    }

    static public GameFile TempFile (String prefix) {
            try {
                    return new GameFile(File.createTempFile(prefix, null));
            } catch (IOException ex) {
                    throw new GameRuntimeError("Unable to create temp file.", ex);
            }
    }

    static public GameFile TempDirectory (String prefix) {
            try {
                    File file = File.createTempFile(prefix, null);
                    if (!file.delete()) throw new IOException("Unable to delete temp file: " + file);
                    if (!file.mkdir()) throw new IOException("Unable to create temp directory: " + file);
                    return new GameFile(file);
            } catch (IOException ex) {
                    throw new GameRuntimeError("Unable to create temp file.", ex);
            }
    }

    static private void EmptyDirectory (File file, boolean preserveTree) {
            if (file.exists()) {
                    File[] files = file.listFiles();
                    if (files != null) {
                            for (int i = 0, n = files.length; i < n; i++) {
                                    if (!files[i].isDirectory())
                                            files[i].delete();
                                    else if (preserveTree)
                                            EmptyDirectory(files[i], true);
                                    else
                                            DeleteDirectory(files[i]);
                            }
                    }
            }
    }

    static private boolean DeleteDirectory (File file) {
            EmptyDirectory(file, false);
            return file.delete();
    }

    static private void CopyFile (GameFile source, GameFile dest) {
            try {
                    dest.Write(source.Read(), false);
            } catch (Exception ex) {
                    throw new GameRuntimeError("Error copying source file: " + source.file + " (" + source.type + ")\n" //
                            + "To destination: " + dest.file + " (" + dest.type + ")", ex);
            }
    }

    static private void CopyDirectory (GameFile sourceDir, GameFile destDir) 
    {
            destDir.mkdirs();
            GameFile[] files = sourceDir.List();
            for (int i = 0, n = files.length; i < n; i++) {
                    GameFile srcFile = files[i];
                    GameFile destFile = destDir.Child(srcFile.GetName());
                    if (srcFile.IsDirectory())
                            CopyDirectory(srcFile, destFile);
                    else
                            CopyFile(srcFile, destFile);
            }
    }
}
