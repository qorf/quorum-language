/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import plugins.quorum.Libraries.Game.AndroidApplication;

/**
 *
 * @author stefika
 */
public class QuorumFileAndroid extends QuorumFile{
    public static String ASSET_REQUEST_KEY = "/";

    @Override
    public boolean Exists() {
        Activity activity = AndroidApplication.GetActivity();
        if(!IsAssetRequest()) {
            return super.Exists();
        }
        
        try
        {
            AssetManager assets = activity.getAssets();
            InputStream open = assets.open(GetPathNative());
            if(open != null) {
                open.close();
                return true;
            }
            return false;
        }
        catch (IOException e)
        {
            return false;
        }
    }
    
    public boolean IsAssetRequest() {
        return GetWorkingDirectoryNative().compareTo(ASSET_REQUEST_KEY) == 0;
    }
    
    public static boolean IsAssetRequest(String workingDirectory) {
        return workingDirectory.compareTo(ASSET_REQUEST_KEY) == 0;
    }
}
