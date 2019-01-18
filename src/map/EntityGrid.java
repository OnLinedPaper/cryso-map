package map;

import java.awt.Color;
import java.awt.Graphics;

public class EntityGrid {
	private Tile[][] g;
	int xSize;
	int ySize;
	private Tile active = null;
	
	public EntityGrid() {
		xSize = CC.MISC.GRID_DIM_X;
		ySize = CC.MISC.GRID_DIM_Y;
		GridInit();
	}

	private void GridInit() {
		g = new Tile[xSize][ySize];
		
		for(int i=0; i<xSize; i++) {
			for(int j=0; j<ySize; j++) {
				//declare tiles
				g[i][j] = new Tile(i, j);
				g[i][j].setType(TX.TT.BLANK); //start them all off as blank
			}
		}
		
		
	}
	
	public void paintComponent(Graphics g, double scale, int xAnchor, int yAnchor) {
		Color co = g.getColor(); //preserve color

		for(int i=0; i<xSize; i++) {
			for(int j=0; j<ySize; j++) {
				this.g[i][j].paintComponent(g, scale, xAnchor, yAnchor, false);
			}
		}
		
		g.setColor(co);
	}
	
	public void setTile(double scale, int xAnchor, int yAnchor, int x, int y, int type ) {
		//get the tile and make it change to a new type
		for(int i=0; i<xSize; i++) {
			for(int j=0; j<ySize; j++) {
				//declare tiles - feed them coordinates based on width
				if(g[i][j].hitClick(scale, xAnchor, yAnchor, x, y) != null) {
					changeTileType(g[i][j].hitClick(scale, xAnchor, yAnchor, x, y), type);
				}
			}
		}			
	}

	private void changeTileType(Tile t, int type) {
		t.setType(type);
	}

	public void moveToken(double scale, int xAnchor, int yAnchor, int xin, int yin) {
		Tile temp = null;
		
		for(int i=0; i<xSize; i++) {
			for(int j=0; j<ySize; j++) {
				if(temp == null) {
					//keep checking till we hit a tile that's not null, i.e. till we
					//find the tile
					temp = g[i][j].hitClick(scale, xAnchor, yAnchor, xin, yin);
				}
			}
		}
		
		if(temp == null) { return; } //no tokens on the field
		
		//temp now holds the clicked tile
		if(active == null) {
			//no active tile was set yet
			if(temp.getType() != TX.TT.BLANK) {
				//it's an occupied tile! set the active tile to this
				active = temp;
			}
		}
		else {
			//there's an active tile
			if(temp.getType() != TX.TT.BLANK) {
				//this is an occupied tile, switch active to this
				active = temp;
			}
			else {
				//this is a blank tile - place it here
				temp.setType(active.getType()); //set tile to new type
				active.setType(TX.TT.BLANK); //revert old tile
				active = temp; //update active tile
			}
		}
		
	}

	public void removeToken(double scale, int xAnchor, int yAnchor, int x, int y) {
		setTile(scale, xAnchor, yAnchor, x, y, TX.TT.BLANK);
		
	}
	
	
	
	
	

}
