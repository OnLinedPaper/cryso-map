package map;

import javax.swing.SwingUtilities;

public class MainDriver {

	public static void main(String[] args) {
		
		TX.ENV.loadImages();
		TX.ENTY.loadImages();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}
	
	private static void createAndShowGUI() {
		MapGUIv2 m2 = new MapGUIv2();
		MapHolder h = new MapHolder(m2);
		EnvironmentControlGUI env = new EnvironmentControlGUI(m2);
		EntityControlGUI enty = new EntityControlGUI(m2);
	}

}
