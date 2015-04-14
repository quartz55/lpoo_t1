package maze.Graphics;

import java.util.ArrayList;

import maze.logic.GameObjects.*;

public class CliGraphics implements Graphics{

	@Override
	public void draw(Maze maze, Hero hero, Sword sword, Shield shield,
			ArrayList<Dragon> dragons, ArrayList<Dart> darts) {

        /* Clean the screen */
        for (int i = 0; i < 30; i++) System.out.println();

        for (int i = 0; i < maze.getH(); i++) {
            for (int j = 0; j < maze.getW(); j++) {
                if (i == hero.getY() && j == hero.getX()) {
                    System.out.print(hero.getCliChar());
                } else if (i == sword.getY() && j == sword.getX()
                        && !sword.isPickedUp()) {
                    System.out.print(sword.getCliChar());
                } else if (i == shield.getY() && j == shield.getX()
                        && !shield.isPickedUp()) {
                    System.out.print(shield.getCliChar());
                }
                else {
                    boolean printed = false;
                    for (int k = 0; k < dragons.size(); k++) {
                        if (i == dragons.get(k).getY() && j == dragons.get(k).getX()
                                && dragons.get(k).isAlive()) {
                            System.out.print(dragons.get(k).getCliChar());
                            printed = true;
                        }
                    }
                    if (printed) continue;

                    for (int k = 0; k < darts.size(); k++) {
                        if (i == darts.get(k).getY() && j == darts.get(k).getX()
                                && !darts.get(k).isPickedUp()) {
                            System.out.print(darts.get(k).getCliChar());
                            printed = true;
                        }
                    }
                    if (printed) continue;
                    if (maze.getPosition(j, i) == 2)
                        System.out.print("S");
                    else if (maze.getPosition(j, i) == 1)
                        System.out.print("|");
                    else if (maze.getPosition(j, i) == 0)
                        System.out.print(" ");
                }

            }
            System.out.println();
        }
        System.out.println("Darts: " + hero.getDarts());
		System.out.print("Command(w/a/s/d/f)? ");
	}

	@Override
	public void gameOver(boolean win) {
		if(win)
			System.out.println("You win!");
		else
			System.out.println("You lose!");
	}

	@Override
	public void close() {
	}

	@Override
	public void loadGame() {
	}

	@Override
	public void saveGame() {
	}
}
