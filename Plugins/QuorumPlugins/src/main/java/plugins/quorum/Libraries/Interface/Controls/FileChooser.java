/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Interface.Controls;

import org.lwjgl.PointerBuffer;
import org.lwjgl.util.nfd.NFDFilterItem;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.system.MemoryUtil.memAllocPointer;
import static org.lwjgl.system.MemoryUtil.memFree;
import static org.lwjgl.system.MemoryStack.stackPush;
//import static org.lwjgl.util.nfd.NativeFileDialog.NFD_CANCEL;
//import static org.lwjgl.util.nfd.NativeFileDialog.NFD_OKAY;
//import static org.lwjgl.util.nfd.NativeFileDialog.NFD_OpenDialog;
//import static org.lwjgl.util.nfd.NativeFileDialog.NFD_SaveDialog;
//import static org.lwjgl.util.nfd.NativeFileDialog.NFD_PickFolder;


import static org.lwjgl.util.nfd.NativeFileDialog.*;
/**
 *  An implementation of a native file chooser using the NFD library. It covers
 *  the most basic and common use cases, including File Filters, Default Locations, 
 *  and Saving/Loading.
 *  
 * @author stefika
 */
public class FileChooser {
    public java.lang.Object me_ = null;

    private static boolean STARTED = false;

    public FileChooser() {
        // compareAndSet changes false to true exactly once, safely across all instances
        if (!STARTED) {
            STARTED = true;
            NFD_Init();
        }
    }

    // Optional global shutdown helper
    public static void ShutDown() {
        if (STARTED) {
            STARTED = false;
            NFD_Quit();
        }
    }

    public String OpenFileDialogNative(String path, String filter) {
        PointerBuffer outPath = memAllocPointer(1);
        String resultPath = null;
        try (MemoryStack stack = stackPush()) {
            NFDFilterItem.Buffer filterList = parseFilterString(stack, filter);

            // Signature: NFD_OpenDialog(outPath, filterList, defaultPath)
            int result = NFD_OpenDialog(outPath, filterList, path);

            switch (result) {
                case NFD_OKAY:
                    resultPath = outPath.getStringUTF8(0);
                    break;
                case NFD_CANCEL:
                default:
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
            int result = NFD_PickFolder(outPath, path);
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
        try (MemoryStack stack = stackPush()) {//example filter: "png,jpg;pdf"
            NFDFilterItem.Buffer filterList = parseFilterString(stack, filter);
            int result = NFD_SaveDialog(outPath, filterList, path, (CharSequence) null);
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

    private NFDFilterItem.Buffer parseFilterString(MemoryStack stack, String filter) {
        if (filter == null || filter.trim().isEmpty()) {
            return null;
        }
        NFDFilterItem.Buffer filters = NFDFilterItem.malloc(1, stack);
        filters.get(0)
                .name(stack.UTF8("Supported Files"))
                .spec(stack.UTF8(filter)); // pass raw "png,jpg"

        return filters;
    }
}
