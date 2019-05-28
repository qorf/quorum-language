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
import static org.lwjgl.util.nfd.NativeFileDialog.NFD_OKAY;
import static org.lwjgl.util.nfd.NativeFileDialog.NFD_OpenDialog;
import static org.lwjgl.util.nfd.NativeFileDialog.NFD_SaveDialog;
import static org.lwjgl.util.nfd.NativeFileDialog.NFD_PickFolder;

/**
 *  An implementation of a native file chooser using the NFD library. It covers
 *  the most basic and common use cases, including File Filters, Default Locations, 
 *  and Saving/Loading.
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
    
    public String ChooseFolderDialogNative(String path) {
        PointerBuffer outPath = memAllocPointer(1);
        String resultPath = null;
        try {//example filter: "png,jpg;pdf"
            int result = NFD_PickFolder(path, outPath);
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
    
    public String SaveFileDialogNative(String path, String filter) {
        PointerBuffer outPath = memAllocPointer(1);
        String resultPath = null;
        try {//example filter: "png,jpg;pdf"
            int result = NFD_SaveDialog(filter, path, outPath);
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
