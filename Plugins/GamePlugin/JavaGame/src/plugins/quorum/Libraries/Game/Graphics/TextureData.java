/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*  This interface is used by various classes to hold the information needed
    for different types of textures. Any information which would be needed by
    all different kinds of texture types is determined here.

    Work from 1/13/15 by William Allee.
*/

package plugins.quorum.Libraries.Game.Graphics;

import quorum.Libraries.Game.Graphics.PixelMap_;

/**
 * Used by texture to load pixel data
 * 
 * 
 * @author Taylor Bockman
 */

public interface TextureData {

	public int GetWidth ();

 //test for preparedness
	public boolean IsPrepared ();
 //prepares texturedata for consume pixmap
	public int GetHeight ();
 public PixelMap_ ConsumePixmap();
 public boolean DisposePixmap();
 public boolean UseMipMaps ();

 // Beginning of new work, 1/13/15:
 
 
 
}
