/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game.Graphics;

import quorum.Libraries.Game.Graphics.PixelMap_;
import plugins.quorum.Libraries.Game.GameStateManager;

/**
 * Stores texture data information from loaded textures
 * 
 * 
 * @author Taylor Bockman
 */
public class FileTextureData implements TextureData {
   
    public java.lang.Object me_ = null;
    
   String file;
   PixelMap_ pixmap;
   boolean useMipMaps;
   boolean isPrepared = false;
   
   static public boolean copyToPOT = false;
   
   public void CopyImagesToPowerOfTwo(boolean bool)
   {
       copyToPOT = bool;
   }
   
   public boolean TestForCopyToPowerOfTwo()
   {
       return copyToPOT && (GameStateManager.graphics == null);
   }
   
   @Override
   public int GetHeight () {
        quorum.Libraries.Game.Graphics.FileTextureData quorum = (quorum.Libraries.Game.Graphics.FileTextureData)me_;
        return quorum.Get_Libraries_Game_Graphics_FileTextureData__height_();
   }

   @Override
    public int GetWidth () {
        quorum.Libraries.Game.Graphics.FileTextureData quorum = (quorum.Libraries.Game.Graphics.FileTextureData)me_;
        return quorum.Get_Libraries_Game_Graphics_FileTextureData__width_();
    }
   	
   @Override
   public boolean IsPrepared () {
    return isPrepared;
   }
   
   //extend this to the real signature 
   //public FileTextureData (FileHandle file, Pixmap preloadedPixmap, Format format, boolean useMipMaps)
   //Once format is implemented
   
   /*
   public FileTextureData(String file, PixelMap_ prePix, boolean useMipMaps){
     this.file = file;
     this.pixmap = prePix;
     this.useMipMaps = false;
     
     if(pixmap != null){
       //pixmap call to ensurePot
       height = pixmap.GetHeight();
       width = pixmap.GetWidth();
       //set format by pixmap
     }
   }*/
   
  @Override
  public PixelMap_ ConsumePixmap () {
    //if (!isPrepared) throw new GdxRuntimeException("Call prepare() before calling getPixmap()");
    isPrepared = false;
    PixelMap_ pixmap = this.pixmap;
    this.pixmap = null;
    return pixmap;
	}
  @Override
  public boolean DisposePixmap () {
   return true;
  }
  
  @Override
  public boolean UseMipMaps () {
    return useMipMaps;
  }
    
}
