package plugins.quorum.Libraries.Game.Graphics.Models.Printing;

import quorum.Libraries.Containers.Number32BitArray_;
import quorum.Libraries.Containers.Integer32BitArray_;

import java.io.File;

public class Slicer {
	public java.lang.Object me_ = null;

	public static final String WINDOWS = "Windows";
	public static final String MAC = "Mac";
	public static final String LINUX = "Linux";

	private static boolean isLoaded = false;
	private static File loadedLibrary = null;

	private static native String OrcaSlice(float[] vertices, int[] indices, 
		String settings, String filament, String outputFolder);

	public String JavaSlice(Number32BitArray_ vertices, Integer32BitArray_ indices, String settings, String filament, String outputFolder) {
		float[] ver = Floats(vertices);
		int[] ind = Ints(indices);

		if (ver.length == 0 || ind.length == 0) {
			throw new RuntimeException("Error");
		}

		LoadLibrary();

		// Call C++
		try {
			return OrcaSlice(vertices, indices, settings, filament, outputFolder);
		}
		catch (UnsatisfiedLinkError error) {
			throw new RuntimeException("Error", error);
		}
	}

	private static String GetOS() {
		String os = System.getProperty("os.name", "");

		if (os.startsWith("Windows")) 
			return WINDOWS;
		if (os.startsWith("Mac OS X"))
			return MAC;
		if (os.startsWith("Linux"))
			return LINUX;

		// OrcaSlicer can't run on anything else
		throw new RuntimeException("Error");
	}

	// TODO
	private static String GetLibraryName(String os) {
		if (os.equals(WINDOWS))
			return "OrcaSlicer.dll";
		if (os.equals(MAC))
			return "TO DO";
		// if (os.equals(LINUX))
		return "";
	}

	// TODO
	private static void LoadLibrary() {
		String os = GetOS();

		if (isLoaded == true) {
			return;
		}

		File library;
		// get library

		try {
			System.load(library.getAbsolutePath());
		} 
		catch (Exception e) {
			throw new RuntimeException("Error");
		}

		loadedLibrary = library;
		isLoaded = true;
	}

} /* End of Slicer Class */
