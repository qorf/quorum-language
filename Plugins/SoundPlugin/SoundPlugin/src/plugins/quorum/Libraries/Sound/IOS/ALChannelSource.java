/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.IOS;

import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.annotation.Property;
import org.robovm.rt.bro.annotation.Library;

@Library(Library.INTERNAL)
@NativeClass
public final class ALChannelSource extends NSObject 
{
    static 
    {
        ObjCRuntime.bind(ALChannelSource.class);
    }
    
    @Property(selector = "sourcePool")
    public native ALSoundSourcePool getSourcePool();	
}