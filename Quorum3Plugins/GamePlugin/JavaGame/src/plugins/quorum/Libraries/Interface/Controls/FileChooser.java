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
    
    public String OpenFileDialogNative(String path, String filter) {
        PointerBuffer outPath = memAllocPointer(1);
        String resultPath = null;
        try {//example filter: "png,jpg;pdf"
            int result = NFD_OpenDialog(filter, path, outPath);
            switch (result) {
                case NFD_OKAY:
                    resultPath = outPath.getStringUTF8(0);
                    break;
                case NFD_CANCEL:
                    resultPath = null;
                    break;
                default: // NFD_ERROR
                    resultPath = null;
            }
        } finally {
            memFree(outPath);
        }
        return resultPath;
    }
}
