package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tile {
	
	private double x;
	private double y;
	private String name;
	private static double width = TX.TEXTURE_WIDTH;
	private static int nameOffset = 4;
	private int type;
	
	public Tile(int xin, int yin) {
		x = xin;
		y = yin;
		name = "";
		MakeName();
		type = TX.TT.WALL;
	}
	

	private void MakeName() {
		//make a tile's name based on the x,y coords of the tile
		char secondLetter = (char) (((int) x % 26) + 65 + 32);
		char firstLetter = (char) (((int) x / 26) + 65 + 32);
		
		name += firstLetter;
		name += secondLetter;
		name += "_";
		name += Integer.toString((int) y);
	}


	/* this supports zooming in and out - use the anchor to determine the "point"
	 * at which the program begins drawing. 
	 * first, check to see if the anchor point is within the x and y range - if
	 * not, this tile is completely outside the window.
	 * if it's in the window, the coordinates need to convert to pixels. xAnchor 
	 * and yAnchor are the top left corner of the "window"; scale is how zoomed
	 * in everything is. 
	 * subtract xAnchor and yAnchor from the coordinates to get the tile's location
	 * on the screen, then multiply by scale to draw it accurately.
	 */
	public void paintComponent(Graphics g, double scale, int xAnchor, int yAnchor, boolean dark) {
		if(type == TX.TT.BLANK) { return; } //don't paint "blank" tiles
		
		//briefly change type to "dark wall" if it's surrounded by walls
		int temptype = type;
		if(dark) {
			type = TX.TT.DARK_WALL;
		}
		
		Color co = g.getColor(); //preserve color
		
		double drawX = (x * width) - xAnchor;
		double drawY = (y * width) - yAnchor;
		double drawSY = ((y + 1) * width) - yAnchor; //for the string

		/*
		g.setColor(CC.TT.getColor(type));		
		g.fillRect(
				(int) (drawX * scale), 
				(int) (drawY * scale), 
				(int) (width * scale), 
				(int) (width * scale)
		);
		*/
		
		//TODO: only draw if on screen
		if(onscreen((x * width * scale), (y * width * scale), scale, (width * scale), xAnchor, yAnchor))
		{
			//long startT = System.nanoTime();
			BufferedImage b = TX.imageToBufferedImage(
					TX.GET_SCALED_TEXTURE(
							type, 
							(int) (width * scale), 
							(int) (width * scale), 
							Image.SCALE_DEFAULT
					)
			);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(
					b//CC.TX.GET_SCALED_TEXTURE(type, (int) (width * scale), (int) (width * scale), Image.SCALE_DEFAULT)
					,(int) (drawX * scale)
					,(int) (drawY * scale)
					,null);
			//long endT = System.nanoTime() - startT;
			//System.out.println("full paint " + endT);
			
			g.setColor(Color.BLACK);
			g.drawRect(
					(int) (drawX * scale), 
					(int) (drawY * scale), 
					(int) (width * scale), 
					(int) (width * scale)
			);
			
			if(dark) {
				g.setColor(Color.DARK_GRAY);
			}
			else {
				g.setColor(Color.LIGHT_GRAY);
			}
			g.drawString(
					name, 
					(int) (drawX * scale + nameOffset), 
					(int) (drawSY *scale - nameOffset)
			);
		}

		
		type = temptype; //restore type if it was changed
		
		g.setColor(co);
	}
	
	private boolean onscreen(double scaledX, double scaledY, double scale, double length, int xAnchor, int yAnchor) {
		//drawX and drawY are the coordinates of the top left corner of this
		//tile, and xAnchor, yAnchor are where the screen's top left corner is.
		//check if a tile's onscreen before drawing it.
		if((scaledX + length) < (xAnchor * scale) || (scaledY + length) < (yAnchor * scale)) {
			return false;
		}
		if(scaledX  > (xAnchor * scale + CC.WS.MAIN_WINDOW_WIDTH_PX) 
				|| scaledY > (yAnchor * scale + CC.WS.MAIN_WINDOW_HEIGHT_PX)) {
			
			return false;
		}
		return true;
	}


	//return tile if clicked
	public Tile hitClick(double scale, int xAnchor, int yAnchor, double xin, double yin) {

		//get width at current zoom
		double tileW = width * scale;
		//get coordinates of top left corner of this tile
		//include zoom and "movement" offsets
		double tileX = (x * tileW) - (xAnchor * scale);
		double tileY = (y * tileW) - (yAnchor * scale);
		

		//System.out.printf("check %s: %f, %f | %f %f\n", name, tileX, tileY, xin, yin);
		//System.out.printf("check %s: %f, %f | %f %f\n\n", name, tileX+tileW, tileY+tileW, xin, yin);
		
		if(xin > tileX && yin > tileY) {
			if((tileX + tileW) - xin > 0 && (tileY + tileW) - yin > 0) {
				//System.out.printf("click %s\n", name);
				return this;
			}
		}
		
		
		
		return null;
	}

	private Tile() { /* no declaring a tile without coordinates >:I */ }

	public int getType() {
		return type;
	}
	
	public boolean setType(int t) {
		if(type == t) {
			return false;
		}
		else {
			type = t;
			return true;
		}
	}

	public static double getWidth() {
		return width;
	}
}
