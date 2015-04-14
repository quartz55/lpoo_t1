package maze.logic;

import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * Global class responsible for keeping track of game settings
 */
public class GameSettings implements Serializable{
	public static int size = 11;
	public static int difficulty = 2;
	public static int numDragons = 3;
	public static int numDarts = 3;

	public static int K_UP = KeyEvent.VK_W;
	public static int K_DOWN = KeyEvent.VK_S;
	public static int K_LEFT = KeyEvent.VK_A;
	public static int K_RIGHT = KeyEvent.VK_D;
	public static int K_FIRE = KeyEvent.VK_F;
}
