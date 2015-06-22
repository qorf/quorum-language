/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import java.io.File;

/**
 *
 * @author alleew
 */
public class LWJGLFileHandler implements GameFileHandler
{
    static public final String externalPath = System.getProperty("user.home") + File.separator;
    static public final String localPath = new File("").getAbsolutePath() + File.separator;
	
    @Override
    /** This will take a Quorum GameFile and return a Java GameFile. */
    public GameFile Convert(quorum.Libraries.System.File_ quorumFile)
    {
        // A type code of 4 defaults to being "Absolute" type.
        int type = 4;
        String fileName = quorumFile.GetAbsolutePath();
        return new LWJGLFile(fileName, FileType.ConvertFileType(type));
    }
    
    @Override
    public GameFile GetGameFile (String fileName, FileType type) 
    {
	return new LWJGLFile(fileName, type);
    }

    @Override
    public GameFile ClassPath (String path) 
    {
	return new LWJGLFile(path, FileType.Classpath);
    }

    @Override
    public GameFile Internal (String path) {
	return new LWJGLFile(path, FileType.Internal);
    }

    @Override
    public GameFile External (String path) {
	return new LWJGLFile(path, FileType.External);
    }

    @Override
    public GameFile Absolute (String path) {
	return new LWJGLFile(path, FileType.Absolute);
    }

    @Override
    public GameFile Local (String path) {
	return new LWJGLFile(path, FileType.Local);
    }

    @Override
    public String GetExternalStoragePath () {
	return externalPath;
    }

    @Override
    public boolean IsExternalStorageAvailable () {
	return true;
    }

    @Override
    public String GetLocalStoragePath () {
	return localPath;
    }

    @Override
    public boolean IsLocalStorageAvailable () {
	return true;
    }
}
