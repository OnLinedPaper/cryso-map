package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class EnvironmentGrid {
	private Tile[][] g;
	int xSize;
	int ySize;
	
	public EnvironmentGrid() {
		xSize = CC.MISC.GRID_DIM_X;
		ySize = CC.MISC.GRID_DIM_Y;
		GridInit();
	}

	private void GridInit() {
		g = new Tile[xSize][ySize];
		
		for(int i=0; i<xSize; i++) {
			for(int j=0; j<ySize; j++) {
				//declare tiles - feed them coordinates based on width
				g[i][j] = new Tile(i, j);
			}
		}	
	}
	
	public void paintComponent(Graphics g, double scale, int xAnchor, int yAnchor) {
		Color co = g.getColor(); //preserve color
		
		for(int i=0; i<xSize; i++) {
			for(int j=0; j<ySize; j++) {
				this.g[i][j].paintComponent(g, scale, xAnchor, yAnchor, checkWalledIn(i, j));
			}
		}	
		
		
		g.setColor(co);
	}

	private boolean checkWalledIn(int i, int j) {
		//if the entirety of this tile is surrounded by other walls, return true
		
		
		//top left tile
		if(i > 0 && j > 0 && (g[i-1][j-1].getType() != TX.TT.WALL)) { return false; }
		
		//top middle tile
		if(j > 0 && (g[i][j-1].getType() != TX.TT.WALL)) { return false; }
		
		//top right tile
		if((i + 1) < xSize && j > 0 && (g[i+1][j-1].getType() != TX.TT.WALL)) { return false; }

		//middle left tile
		if(i > 0 && (g[i-1][j].getType() != TX.TT.WALL)) { return false; }
		
		//middle middle tile (this one)
		if(g[i][j].getType() != TX.TT.WALL) { return false; }
		
		//middle right tile
		if((i+1) < xSize && (g[i+1][j].getType() != TX.TT.WALL)) { return false; }
		
		//bottom left tile
		if(i > 0 && (j + 1) < ySize && (g[i-1][j+1].getType() != TX.TT.WALL)) { return false; }
		
		//bottom middle tile
		if((j + 1) < ySize && (g[i][j+1].getType() != TX.TT.WALL)) { return false; }
		
		//bottom right tile
		if((i + 1) < xSize && (j + 1) < ySize && (g[i+1][j+1].getType() != TX.TT.WALL)) { return false; }
		
		
		return true;
	}

	public void debug_changecolor(double scale, int xAnchor, int yAnchor, int x, int y) {

		for(int i=0; i<xSize; i++) {
			for(int j=0; j<ySize; j++) {
				//declare tiles - feed them coordinates based on width
				if(g[i][j].hitClick(scale, xAnchor, yAnchor, x, y) != null) {
					g[i][j].hitClick(scale, xAnchor, yAnchor, x, y).setType(TX.TT.DEBUG);
				}
			}
		}	
		
	}

	public boolean setTile(double scale, int xAnchor, int yAnchor, int x, int y, int type) {
		//get the tile and make it change to a new type
		for(int i=0; i<xSize; i++) {
			for(int j=0; j<ySize; j++) {
				//declare tiles - feed them coordinates based on width
				if(g[i][j].hitClick(scale, xAnchor, yAnchor, x, y) != null) {
					//found the tile
					return(changeTileType(g[i][j].hitClick(scale, xAnchor, yAnchor, x, y), type));
				}
			}
		}
		
		return false;
	}
	
	private boolean changeTileType(Tile t, int type) {
		return(t.setType(type));
	}

}
