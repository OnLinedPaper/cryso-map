package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MapGUIv2 extends JPanel {
	private static final long serialVersionUID = 1L;

	private EnvironmentGrid env;
	private EntityGrid enty;
	private double scale;
	private int xAnchor;
	private int yAnchor;
	private int mode;
	
	private int xPrev;	//used to move map and track last click
	private int yPrev;  //used to move map and track last click
	
	MapGUIv2() {
		this.setBackground(Color.LIGHT_GRAY);
		
		env = new EnvironmentGrid();
		enty = new EntityGrid();
		
		scale = 1;
		xAnchor = 0;
		yAnchor = 0;
		mode = CC.OC.MOVE; //default to move operation
		
		//TODO: remove this
		enty.setTile(scale, xAnchor, yAnchor, 400, 400, TX.TT.DEBUG_TOKEN);
		enty.setTile(scale, xAnchor, yAnchor, 300, 300, TX.TT.AULORA_TOKEN);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				doClickAction(e);
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				doDragAction(e);
			}
		});
		

		this.setVisible(true);
	}
	
	protected void doClickAction(MouseEvent e) {
		if(mode == CC.OC.MOVE) {
			//record where the user first clicked
			xPrev = e.getX();
			yPrev = e.getY();
		}
		else if (mode == CC.OC.CLEAR) {
			//set the tile to normal mode
			if(env.setTile(scale, xAnchor, yAnchor, e.getX(), e.getY(), TX.TT.NORMAL)) {
				//don't repaint if nothing is changed
				repaint();
			}
		}
		else if (mode == CC.OC.BARRIER) {
			//set the tile to normal mode
			if(env.setTile(scale, xAnchor, yAnchor, e.getX(), e.getY(), TX.TT.WALL)) {
				//don't repaint if nothing is changed
				repaint();
			}
		}
		else if(mode == CC.OC.DEBUG_TOUCH) {
			//this is a debugger to check if hitclick is working
			env.debug_changecolor(scale, xAnchor, yAnchor, e.getX(), e.getY());
			repaint();
		}
		else if(mode == CC.OC.DRAG_TOKEN) {
			//target a token to move by clicking
			enty.moveToken(scale, xAnchor, yAnchor, e.getX(), e.getY());
			repaint();
		}
		else if(mode == CC.OC.REMOVE_TOKEN) {
			//target a token to move by clicking
			enty.removeToken(scale, xAnchor, yAnchor, e.getX(), e.getY());
			repaint();
		}
		
	}

	

	protected void doDragAction(MouseEvent e) {
		if(mode == CC.OC.MOVE) {
			//move the map
			moveMap(e);
		}
		else if (mode == CC.OC.CLEAR) {
			//set the tile to normal mode
			if(env.setTile(scale, xAnchor, yAnchor, e.getX(), e.getY(), TX.TT.NORMAL)) {
				//don't repaint if nothing is changed
				repaint();
			}
		}
		else if (mode == CC.OC.BARRIER) {
			//set the tile to wall mode
			if(env.setTile(scale, xAnchor, yAnchor, e.getX(), e.getY(), TX.TT.WALL)) {
				//don't repaint if nothing is changed
				repaint();
			}
		}
		else if(mode == CC.OC.DEBUG_TOUCH) {
			//do nothing
			return;
		}
		else if(mode == CC.OC.DRAG_TOKEN) {
			//do nothing
			return;
		}
	}

	private void moveMap(MouseEvent e) {
		//starting at the xPrev and yPrev, measure the user's mouse movement and
		//then sub this from xAnchor and yAnchor
		xAnchor += (xPrev - e.getX()) / scale;
		yAnchor += (yPrev - e.getY()) / scale;
		
		if(xPrev != e.getX() || yPrev != e.getY()) {
			repaint();
		}
		xPrev = e.getX();
		yPrev = e.getY();
		
	}
	
	public void setMode(int m) {
		//System.out.printf("mode is %d\n", mode);
		mode = m;
	}
	
	public void changeScale(double factor) {
		scale = scale * factor;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		env.paintComponent(g, scale, xAnchor, yAnchor);
		enty.paintComponent(g, scale, xAnchor, yAnchor);
	}
	

}
