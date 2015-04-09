package maze.Graphics;

import java.util.ArrayList;
import maze.GameObjects.*;

public interface Graphics {
	
	void draw(Maze maze, Hero hero, Sword sword, Shield shield, ArrayList<Dragon> dragons, ArrayList<Dart> darts);

	void close(boolean win);

}
