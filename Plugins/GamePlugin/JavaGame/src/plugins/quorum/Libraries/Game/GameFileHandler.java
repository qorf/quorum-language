/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

/**
 * This interface is used in the GameState to handle use of files.
 * The interface will be replaced with an appropriate implementing class in the
 * GameStateManager to handle different operating systems as needed.
 * 
 * @author alleew
 */
public interface GameFileHandler 
{
    public enum FileType
    {
	/** Path relative to the root of the classpath. Classpath files are always readonly. Note that classpath files are not
	 * compatible with some functionality on Android, such as {@link Audio#newSound(FileHandle)} and
	 * {@link Audio#newMusic(FileHandle)}. */
	Classpath,

	/** Path relative to the asset directory on Android and to the application's root directory on the desktop. On the desktop,
        * if the file is not found, then the classpath is checked. This enables files to be found when using JWS or applets.
	* Internal files are always readonly. */
        Internal,

	/** Path relative to the root of the SD card on Android and to the home directory of the current user on the desktop. */
	External,

	/** Path that is a fully qualified, absolute filesystem path. To ensure portability across platforms use absolute files only
	* when absolutely (heh) necessary. */
	Absolute,

	/** Path relative to the private files directory on Android and to the application's root directory on the desktop. */
	Local;
        
        /** This will convert an integer value to a FileType enumeration. */
        public static FileType ConvertFileType(int code)
        {
            if (code == 1)
                return FileType.External;
            if (code == 2)
                return FileType.Classpath;
            if (code == 3)
                return FileType.Local;
            if (code == 4)
                return FileType.Absolute;

            return FileType.Internal;
        }
    }
    
    
    /** This will take a Quorum GameFile and return a Java GameFile. */
    public GameFile Convert(quorum.Libraries.System.File_ quorumFile);

    /** Returns a handle representing a file or directory.
    * @param type Determines how the path is resolved.
    * @throws GdxRuntimeException if the type is classpath or internal and the file does not exist.
    * @see FileType */
    public GameFile GetGameFile (String path, FileType type);

    /** Convenience method that returns a {@link FileType#Classpath} file handle. */
    public GameFile ClassPath (String path);

    /** Convenience method that returns a {@link FileType#Internal} file handle. */
    public GameFile Internal (String path);

    /** Convenience method that returns a {@link FileType#External} file handle. */
    public GameFile External (String path);

    /** Convenience method that returns a {@link FileType#Absolute} file handle. */
    public GameFile Absolute (String path);

    /** Convenience method that returns a {@link FileType#Local} file handle. */
    public GameFile Local (String path);

    /** Returns the external storage path directory. This is the SD card on Android and the home directory of the current user on
    * the desktop. */
    public String GetExternalStoragePath ();

    /** Returns true if the external storage is ready for file IO. Eg, on Android, the SD card is not available when mounted for use
    * with a PC. */
    public boolean IsExternalStorageAvailable ();

    /** Returns the local storage path directory. This is the private files directory on Android and the directory of the jar on the
    * desktop. */
    public String GetLocalStoragePath ();

    /** Returns true if the local storage is ready for file IO. */
    public boolean IsLocalStorageAvailable ();
}
