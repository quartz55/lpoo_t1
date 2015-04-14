package maze.Graphics;

import java.util.ArrayList;

import maze.logic.GameObjects.*;

public interface Graphics {
	
	void draw(Maze maze, Hero hero, Sword sword, Shield shield, ArrayList<Dragon> dragons, ArrayList<Dart> darts);

	void gameOver(boolean win);
	
	void saveGame();
	
	void loadGame();
	
	void close();

}
