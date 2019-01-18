package map;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class MapHolder extends JFrame {
	private static final long serialVersionUID = 1L;

	private MapGUIv2 m2;
	
	MapHolder(MapGUIv2 m) {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Map Window");
		this.setBackground(Color.DARK_GRAY);
		
		m2 = m;
		
		this.add(m2);
		this.pack();
		this.setSize(CC.WS.MAIN_WINDOW_WIDTH_PX, CC.WS.MAIN_WINDOW_HEIGHT_PX);
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		this.m2.paintComponent(g);
	}
}
