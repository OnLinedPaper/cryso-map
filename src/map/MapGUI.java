/* +------------------------------------------------+
 * |                                                |
 * |      DEPRECATED - use the MapGUIv2 class       |
 * |                                                |
 * +------------------------------------------------+
 */

package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MapGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private Grid env;
	private double scale;
	private int xAnchor;
	private int yAnchor;
	private int mode;
	
	private int xPrev;	//used to move map and track last click
	private int yPrev;  //used to move map and track last click
	
	MapGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Map Window");
		this.setBackground(Color.DARK_GRAY);
		
		env = new Grid();
		scale = 1; //default for a relatively zoomed in view
		xAnchor = 0;
		yAnchor = 0;
		mode = CC.OC.MOVE;	//default to move operation
		
		
		//create mouse listener
		
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
				
		
		
		
		this.pack();
		this.setSize(CC.WS.MAIN_WINDOW_WIDTH_PX, CC.WS.MAIN_WINDOW_HEIGHT_PX);
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
			env.setTile(scale, xAnchor, yAnchor, e.getX(), e.getY(), TX.TT.NORMAL);
			repaint();
		}
		else if (mode == CC.OC.BARRIER) {
			//set the tile to normal mode
			env.setTile(scale, xAnchor, yAnchor, e.getX(), e.getY(), TX.TT.WALL);
			repaint();
		}
		else if(mode == CC.OC.DEBUG_TOUCH) {
			//this is a debugger to check if hitclick is working
			env.debug_changecolor(scale, xAnchor, yAnchor, e.getX(), e.getY());
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
			env.setTile(scale, xAnchor, yAnchor, e.getX(), e.getY(), CC.TT.NORMAL);
			repaint();
		}
		else if (mode == CC.OC.BARRIER) {
			//set the tile to normal mode
			env.setTile(scale, xAnchor, yAnchor, e.getX(), e.getY(), CC.TT.WALL);
			repaint();
		}
		else if(mode == CC.OC.DEBUG_TOUCH) {
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
		
		super.paintComponents(g);
		
		Color co = g.getColor();
		
		this.g.paintComponent(g, scale, xAnchor, yAnchor); 
		
		
		g.setColor(co);;
	}
}
