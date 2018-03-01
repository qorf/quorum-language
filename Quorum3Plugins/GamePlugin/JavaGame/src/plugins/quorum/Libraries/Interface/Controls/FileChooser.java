/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Interface.Controls;

import org.lwjgl.PointerBuffer;

import static org.lwjgl.system.MemoryUtil.memAllocPointer;
import static org.lwjgl.system.MemoryUtil.memFree;
import static org.lwjgl.util.nfd.NativeFileDialog.NFD_CANCEL;
import static org.lwjgl.util.nfd.NativeFileDialog.NFD_GetError;
import static org.lwjgl.util.nfd.NativeFileDialog.NFD_OKAY;
import static org.lwjgl.util.nfd.NativeFileDialog.NFD_OpenDialog;

/**
 *
 * @author stefika
 */
public class FileChooser {
    public java.lang.Object me_ = null;
    
    public void OpenSaveFileDialogNative(String path) {
        PointerBuffer outPath = memAllocPointer(1);
        try {
            checkResult(
                NFD_OpenDialog("png,jpg;pdf", null, outPath),
                outPath
            );
        } finally {
            memFree(outPath);
        }
    }
    
    private static void checkResult(int result, PointerBuffer path) {
        switch (result) {
            case NFD_OKAY:
                System.out.println("Success!");
                System.out.println(path.getStringUTF8(0));
                //nNFD_Free(path.get(0));
                break;
            case NFD_CANCEL:
                System.out.println("User pressed cancel.");
                break;
            default: // NFD_ERROR
                System.err.format("Error: %s\n", NFD_GetError());
        }
    }
}
