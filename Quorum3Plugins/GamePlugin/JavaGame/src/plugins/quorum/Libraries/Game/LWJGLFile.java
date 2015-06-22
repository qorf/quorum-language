/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import java.io.File;

import plugins.quorum.Libraries.Game.GameFileHandler.FileType;

/**
 *
 * @author alleew
 */
public class LWJGLFile extends GameFile
{
    public LWJGLFile(String fileName, FileType type) {
		super(fileName, type);
	}

	public LWJGLFile (File file, FileType type) {
		super(file, type);
	}

	public GameFile Child (String name) {
		if (file.getPath().length() == 0) return new LWJGLFile(new File(name), type);
		return new LWJGLFile(new File(file, name), type);
	}

	public GameFile Sibling (String name) {
		if (file.getPath().length() == 0) throw new GameRuntimeError("Cannot get the sibling of the root.");
		return new LWJGLFile(new File(file.getParent(), name), type);
	}

	public GameFile Parent () {
		File parent = file.getParentFile();
		if (parent == null) {
			if (type == FileType.Absolute)
				parent = new File("/");
			else
				parent = new File("");
		}
		return new LWJGLFile(parent, type);
	}

	public File GetFile () {
		if (type == FileType.External) return new File(LWJGLFileHandler.externalPath, file.getPath());
		if (type == FileType.Local) return new File(LWJGLFileHandler.localPath, file.getPath());
		return file;
	}
    
}
