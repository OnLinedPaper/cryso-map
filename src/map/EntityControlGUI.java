package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EntityControlGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private MapGUIv2 m;
	
	public EntityControlGUI(MapGUIv2 min) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Entity");
		this.setBackground(Color.DARK_GRAY);
		
		
		m = min;
		
		GridLayout layout = new GridLayout(4, 2, 2, 2);
		JPanel j = new JPanel();
		j.setLayout(layout);

		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		j.add(add);
		
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setMode(CC.OC.REMOVE_TOKEN);
			}
		});
		j.add(remove);
		
		
		JButton drag = new JButton("Move");
		drag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setMode(CC.OC.DRAG_TOKEN);
			}
		});
		j.add(drag);
		
		JButton blank2 = new JButton("");
		j.add(blank2);
		JButton blank4 = new JButton("");
		j.add(blank4);
		JButton blank5 = new JButton("");
		j.add(blank5);
		JButton blank6 = new JButton("");
		j.add(blank6);
		JButton blank7 = new JButton("");
		j.add(blank7);

		
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
		this.setSize(CC.WS.ENTY_CONTROL_WINDOW_WIDTH_PX, CC.WS.ENTY_CONTROL_WINDOW_HEIGHT_PX);
		this.setLocation(CC.WS.ENTY_CONTROL_WINDOW_LOCATION_X_PX, CC.WS.ENTY_CONTROL_WINDOW_LOCATION_Y_PX);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		Color co = g.getColor();
		
		g.setColor(co);;
	}

}
