package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class CC {
	//constants
		
	public static final class OC {
		//operation constants
		public static final int MOVE = 0;
		public static final int CLEAR = 1;
		public static final int BARRIER = 2;
		public static final int DEBUG_TOUCH = 99;

		public static final int DRAG_TOKEN = 100;
		public static final int REMOVE_TOKEN = 101;
		public static final int PLACE_DEBUG_TOKEN = 199;
	}
	public static final class WS {
		//window sizes
		public static final int MAIN_WINDOW_WIDTH_PX = 1400;
		public static final int MAIN_WINDOW_HEIGHT_PX = 800;
		public static final int ENV_CONTROL_WINDOW_WIDTH_PX = 200;
		public static final int ENV_CONTROL_WINDOW_HEIGHT_PX = 400;
		public static final int ENV_CONTROL_WINDOW_LOCATION_X_PX = 1350;
		public static final int ENV_CONTROL_WINDOW_LOCATION_Y_PX = 0;
		public static final int ENTY_CONTROL_WINDOW_WIDTH_PX = 200;
		public static final int ENTY_CONTROL_WINDOW_HEIGHT_PX = 400;
		public static final int ENTY_CONTROL_WINDOW_LOCATION_X_PX = 1350;
		public static final int ENTY_CONTROL_WINDOW_LOCATION_Y_PX = 450;
	}
	public static final class MISC {
		public static final int GRID_DIM_X = 30;
		public static final int GRID_DIM_Y = 20;
	}

}
