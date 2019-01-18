package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public final class TX {
	//textures
	public static final int TEXTURE_WIDTH = 64;
	
	public static final class ENV {
		//environment
		public static boolean INIT = false;
		public static BufferedImage DEBUG_TEXTURE = null;
		public static BufferedImage MISSING_TEXTURE = null;
		public static BufferedImage NORMAL_TEXTURE = null;
		public static BufferedImage WALL_TEXTURE = null;
		public static BufferedImage DARK_WALL_TEXTURE = null;

		public static final void loadImages() {
			try {
				NORMAL_TEXTURE = ImageIO.read(
						CC.class.getResource("/textures/environment/grass_texture.png")
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				WALL_TEXTURE = ImageIO.read(
						CC.class.getResource("/textures/environment/wall_texture.png")
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			try {
				DARK_WALL_TEXTURE = ImageIO.read(
						CC.class.getResource("/textures/environment/wall_texture_dark.png")
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			try {
				DEBUG_TEXTURE = ImageIO.read(
						CC.class.getResource("/textures/environment/debug_texture.png")
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			try {
				MISSING_TEXTURE = ImageIO.read(
						CC.class.getResource("/textures/environment/missing_texture.png")
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	//=========================================================================
	
	public static final class ENTY {
		//entity

		public static BufferedImage TOKEN_AULORA_TEXTURE = null;
		public static BufferedImage TOKEN_DEBUG_TEXTURE = null;
		
		public static final void loadImages() {
			try {
				TOKEN_AULORA_TEXTURE = ImageIO.read(
						CC.class.getResource("/textures/entity/token_aulora_texture.png")
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				TOKEN_DEBUG_TEXTURE = ImageIO.read(
						CC.class.getResource("/textures/entity/token_debug_texture.png")
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	//=========================================================================

	public static Image GET_SCALED_TEXTURE(int type, int i, int j, int scaleDefault) {

		//long startT = System.nanoTime();
		BufferedImage img = ENV.MISSING_TEXTURE;
		
		//=====================================================================
		//environments
		
		if(type == TT.NORMAL) {
			//get normal texture
			if(ENV.NORMAL_TEXTURE != null) {
				img = ENV.NORMAL_TEXTURE;
			}
		}
		else if(type == TT.WALL) {
			//get wall texture
			if(ENV.WALL_TEXTURE != null) {
				img = ENV.WALL_TEXTURE;
			}
		}
		else if(type == TT.DARK_WALL) {
			//get wall texture
			if(ENV.WALL_TEXTURE != null) {
				img = ENV.DARK_WALL_TEXTURE;
			}
		}
		else if(type == TT.DEBUG) {
			//get debug texture
			if(ENV.DEBUG_TEXTURE != null) {
				img = ENV.DEBUG_TEXTURE;
			}
		}
		
		//=====================================================================
		//entities

		if(type == TT.DEBUG_TOKEN) {
			//get debug token texture
			if(ENTY.TOKEN_DEBUG_TEXTURE != null) {
				img = ENTY.TOKEN_DEBUG_TEXTURE;
			}
		}
		else if(type == TT.AULORA_TOKEN) {
			//get debug token texture
			if(ENTY.TOKEN_AULORA_TEXTURE != null) {
				img = ENTY.TOKEN_AULORA_TEXTURE;
			}
		}

		//=====================================================================
		
		//we have the texture in img now
		
		Image retImg = img.getScaledInstance(i, j, scaleDefault);
		//long endT = System.nanoTime() - startT;
		//System.out.println("image resizing " + endT);
		
		return retImg;
	}
	
	public static final BufferedImage imageToBufferedImage(Image img) {
		//change images to buffered images
		//thanks to https://stackoverflow.com/a/13605411
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

	public static final class TT {
		//tile types
		public static final int NORMAL = 0;
		public static final int WALL = 1;
		public static final int DARK_WALL = 2; //never used except to paint
		public static final int DOOR_HORIZ = 3;
		public static final int DOOR_VERT = 4;

		public static final int DEBUG = 99;
		
		
		
		public static final int BLANK = 200;
		public static final int AULORA_TOKEN = 201;
		
		public static final int DEBUG_TOKEN = 299;
		
		
		
		public static final Color getColor(int in) {
			if(in == NORMAL) {
				return (Color.WHITE);
			}
			else if(in == WALL) {
				return (Color.DARK_GRAY);
			}
			else if(in == DOOR_HORIZ) {
				return (Color.LIGHT_GRAY);
			}
			else if(in == DOOR_VERT) {
				return (Color.LIGHT_GRAY);
			}
			else if(in == DEBUG) {
				return (Color.PINK);
			}
			else {
				return (Color.MAGENTA);
			}
		}
	}
}
