package maze;

import maze.Input.*;
import maze.GameObjects.*;
import maze.Graphics.*;

import java.util.ArrayList;

public class Game {

	private boolean done = false;
	private boolean win = false;
	private boolean canUpdate = false;
	private boolean firing = false;

	private Input gameInput;
	private Graphics gameGraphics;
	private Maze maze;
	private Hero hero;
	private Sword sword;
	private Shield shield;
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	private ArrayList<Dart> darts = new ArrayList<Dart>();

	public boolean getWin(){
		return win;
	}

	private void generateRandomPositions(int numberOfDragons, int numberOfDarts, int dragonDifficulty){
		/* Generate random positions for the game objects (hero, sword, shield, darts and dragon) */
		int randomX, randomY;
		while (true) {
			randomX = (int) (Math.random() * maze.getW());
			randomY = (int) (Math.random() * maze.getH());
			if (maze.getPosition(randomX, randomY) == 0) {
				hero = new Hero(randomX, randomY);
				break;
			}
		}
		while (true) {
			randomX = (int) (Math.random() * maze.getW());
			randomY = (int) (Math.random() * maze.getH());
			if (maze.getPosition(randomX, randomY) == 0) {
				sword = new Sword(randomX, randomY);
				break;
			}
		}
		while (true) {
			randomX = (int) (Math.random() * maze.getW());
			randomY = (int) (Math.random() * maze.getH());
			if (maze.getPosition(randomX, randomY) == 0) {
				shield = new Shield(randomX, randomY);
				break;
			}
		}
		while (numberOfDragons > 0) {
			randomX = (int) (Math.random() * maze.getW());
			randomY = (int) (Math.random() * maze.getH());
			if (maze.getPosition(randomX, randomY) == 0) {
				dragons.add(new Dragon(randomX, randomY, dragonDifficulty));
				numberOfDragons--;
			}
		}
		while (numberOfDarts > 0) {
			randomX = (int) (Math.random() * maze.getW());
			randomY = (int) (Math.random() * maze.getH());
			if (maze.getPosition(randomX, randomY) == 0) {
				darts.add(new Dart(randomX, randomY));
				numberOfDarts--;
			}
		}
	}

	private void getInput() {
		canUpdate = true;
		String command;
		if(firing){
			command = gameInput.getInput();
			if(command.equals("a")){
				fireDart(0);
				firing = false;
			}
			else if(command.equals("d")){
				fireDart(1);
				firing = false;
			}
			else if(command.equals("w")){
				fireDart(2);
				firing = false;
			}
			else if(command.equals("s")){
				fireDart(3);
				firing = false;
			}
			canUpdate = false;
			return;
		}
		//System.out.print("Command(w/a/s/d/f)? ");
		command = gameInput.getInput();
		if (command.equals("d")) {
			hero.setXspeed(hero.getXspeed() + 1);
		} else if (command.equals("a")) {
			hero.setXspeed(hero.getXspeed() - 1);
		} else if (command.equals("w")) {
			hero.setYspeed(hero.getYspeed() - 1);
		} else if (command.equals("s")) {
			hero.setYspeed(hero.getYspeed() + 1);
		} else if (command.equals("f")) {
			canUpdate = false;
			//System.out.print("Direction(w/a/s/d)? ");
			firing = true;
		} else  if(command.equals("q")){
			done = true;
			win = false;
		}
		else canUpdate = false;
	}

	private void update() {

		/* Update game objects */
		hero.update(maze);
		for (int i = 0; i < dragons.size(); i++)
			dragons.get(i).update(maze);

		if (hero.getX() == sword.getX()
				&& hero.getY() == sword.getY()
				&& !sword.isPickedUp()) {
			sword.setPickedUp(true);
			hero.setSword(true);
		}

		if (hero.getX() == shield.getX()
				&& hero.getY() == shield.getY()
				&& !shield.isPickedUp()) {
			shield.setPickedUp(true);
			hero.setShield(true);
		}
		for(int i = 0; i < darts.size(); i ++)
			if(hero.getX() == darts.get(i).getX()
			&& hero.getY() == darts.get(i).getY()
			&& !darts.get(i).isPickedUp()){
				hero.addDart();
				darts.get(i).setPickedUp(true);
				darts.remove(i);
			}

		/* Update dragons ArrayList */
		for (int i = 0; i < dragons.size(); i++) {               
			for(int k=0; k < 4; k++){

				if (maze.checkLineOfSight(dragons.get(i).getX(), dragons.get(i).getY(), hero.getX(),hero.getY(),k)){
					if(Math.pow(hero.getX() - dragons.get(i).getX(), 2) + Math.pow(hero.getY() - dragons.get(i).getY(), 2) <= 9){
						if (!hero.hasShield() && !dragons.get(i).isSleeping() && dragons.get(i).isBreathingFire()){
							hero.setDead(true);
							done = true;
							win = false;
						}			
					}		
				}
			}

			boolean adjacente = false;
			for (int j = -1; j <= 1; j++) {
				for (int k = -1; k <= 1; k++) {
					if (Math.abs(j) == Math.abs(k) && j!=0) continue;
					if (hero.getX() == dragons.get(i).getX() + k
							&& hero.getY() == dragons.get(i).getY() + j)
						adjacente = true;
				}
			}
			if (adjacente) {
				if (!hero.hasSword() && !dragons.get(i).isSleeping()) {
					hero.setDead(true);
					done = true;
					win = false;
				} else if(hero.hasSword()){
					dragons.get(i).setAlive(false);
					dragons.remove(i);
				}
			}
		}

		if (maze.getPosition(hero.getX(), hero.getY()) == 2) {
			if(dragons.isEmpty()){
				done = true;
				win = true;
			}
			else{
				hero.goBack();
			}
		}
	}

	private void draw() {
		gameGraphics.draw(maze, hero, sword, shield, dragons, darts);
	}

	private void fireDart(int direction){
		if(hero.getDarts() <= 0) return;
		hero.removeDart();
		for(int i = 0; i < dragons.size(); i++){
			Dragon temp_dragon = dragons.get(i);
			if(maze.checkLineOfSight(hero.getX(), hero.getY(), temp_dragon.getX(), temp_dragon.getY(), direction)) {
				dragons.remove(i);
			}
		}
	}

	public void loop() {
		draw();
		/* Main game loop */
		while (!done) {
			getInput();
			if(canUpdate) update();
			draw();

			try { Thread.sleep(1000/60); }
			catch(Exception e){}
		}
		gameGraphics.close(win);
	}


	public Game(boolean gui) {
		maze = new Maze();
		this.gameInput = new GraphicsInput();
		this.gameGraphics = new GuiGraphics(this.gameInput);

		maze.generateRandomMaze(11, 11);
		int difficulty = 2;
		generateRandomPositions(3,3,difficulty);
	}
	public Game() {
		maze = new Maze();
		this.gameInput = new ConsoleInput();
		this.gameGraphics = new CliGraphics();
		int difficulty = -1;
		while (true) {
			System.out.print("Load a maze (1, random): ");
			String command = gameInput.getInput();
			if (command.equals("1")) {
				maze.loadMaze(1);
			} else if (command.equals("random")) {
				int h = 0, w = 0;
				while (h % 2 == 0) {
					System.out.print("Height? ");
					h = Integer.parseInt(gameInput.getInput());
				}
				while (w % 2 == 0) {
					System.out.print("Width? ");
					w = Integer.parseInt(gameInput.getInput());
				}
				maze.generateRandomMaze(h, w);
			} else continue;

			System.out.print("Dragon movement(easy, medium, hard)? ");
			command = gameInput.getInput();
			if (command.equals("easy")) {
				difficulty = 0;
			}
			else if(command.equals("medium")){
				difficulty = 1;
			}
			else if (command.equals("hard")) {
				difficulty = 2;
			} else continue;
			break;
		}

		generateRandomPositions(3,3,difficulty);

	}

	public Game(int maze[][], String[] input, Hero hero, Sword sword, Shield shield, ArrayList<Dragon> dragons, ArrayList<Dart> darts){
		this.gameInput = new TestInput(input);
		this.maze = new Maze(maze);
		this.hero = hero;
		this.sword = sword;
		this.shield = shield;
		this.dragons = dragons;
		this.darts = darts;
	}
	public static void main(String[] args) {
		Game game = new Game(true);
		game.loop();
	}

}
