package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EnvironmentControlGUI  extends JFrame {
	private static final long serialVersionUID = 1L;
	private MapGUIv2 m;
	
	public EnvironmentControlGUI(MapGUIv2 min) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Environment");
		this.setBackground(Color.DARK_GRAY);
		
		
		m = min;
		
		GridLayout layout = new GridLayout(4, 2, 2, 2);
		JPanel j = new JPanel();
		j.setLayout(layout);

		JButton zoomIn = new JButton("+");
		zoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.changeScale(2);
			}
		});
		j.add(zoomIn);
		
		JButton zoomOut = new JButton("-");
		zoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.changeScale(0.5);
			}
		});
		j.add(zoomOut);
		
		
		JButton move = new JButton("Drag");
		move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setMode(CC.OC.MOVE);
			}
		});
		j.add(move);
		
		JButton blank2 = new JButton("");
		j.add(blank2);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setMode(CC.OC.CLEAR);
			}
		});
		j.add(clear);
		
		JButton wall = new JButton("Wall");
		wall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setMode(CC.OC.BARRIER);
			}
		});
		j.add(wall);
		
		
		
		
		JButton blank4 = new JButton("");
		j.add(blank4);
		JButton blank5 = new JButton("");
		j.add(blank5);
		
/*		JButton blank5 = new JButton("drag");
		blank5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setMode(CC.OC.DRAG_TOKEN);
			}
		});
		j.add(blank5);
*/
		
/*		JButton debug = new JButton("debug");
		debug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setMode(CC.OC.DEBUG_TOUCH);
			}
		});
		j.add(debug);
*/
		
		
		this.add(j);
		
		this.pack();
		this.setSize(CC.WS.ENV_CONTROL_WINDOW_WIDTH_PX, CC.WS.ENV_CONTROL_WINDOW_HEIGHT_PX);
		this.setLocation(CC.WS.ENV_CONTROL_WINDOW_LOCATION_X_PX, CC.WS.ENV_CONTROL_WINDOW_LOCATION_Y_PX);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		Color co = g.getColor();
		
		g.setColor(co);;
	}
	
	private EnvironmentControlGUI() { /*no declaring this without passing in a MapGUI*/ }

}
