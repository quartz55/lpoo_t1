package maze.logic;

import maze.Input.*;
import maze.logic.GameObjects.*;
import maze.Graphics.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Main game class that controls everything
 */
public class Game {

	private final String saveFolder = "./saves/";

	/* Interface related */
	private boolean done = false;
	private boolean closed = false;
	private boolean firstLoad = true;

	/* Game related */
	private boolean win = false;
	private boolean canUpdate = false;
	private boolean firing = false;

	/* Game objects */
	private Input gameInput;
	private Graphics gameGraphics;
	private Maze maze;
	private Hero hero;
	private Sword sword;
	private Shield shield;
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	private ArrayList<Dart> darts = new ArrayList<Dart>();


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
		command = gameInput.getInput();
		if(command.equals("new game")){
			newGame(false);
			return;
		}
		if(command.equals("save game")){
			saveGame();
			return;
		}
		if(command.equals("load game")){
			newGame(true);
			return;
		}
		if(command.equals("close")){
			closed = true;
			return;
		}
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
			firing = true;
		} else  if(command.equals("q")){
			done = true;
			win = false;
		}
		else canUpdate = false;
	}

	private void update() {
		/* Update hero */
		hero.update(maze);

		/* Update dragons */
		for (int i = 0; i < dragons.size(); i++)
			dragons.get(i).update(maze);

		/* Check if hero can pickup sword */
		if (hero.getX() == sword.getX()
				&& hero.getY() == sword.getY()
				&& !sword.isPickedUp()) {
			sword.setPickedUp(true);
			hero.setSword(true);
		}

		/* Check if hero can pickup shield */
		if (hero.getX() == shield.getX()
				&& hero.getY() == shield.getY()
				&& !shield.isPickedUp()) {
			shield.setPickedUp(true);
			hero.setShield(true);
		}

		/* Check if hero can pickup darts */
		for(int i = 0; i < darts.size(); i ++)
			if(hero.getX() == darts.get(i).getX()
			&& hero.getY() == darts.get(i).getY()
			&& !darts.get(i).isPickedUp()){
				hero.addDart();
				darts.get(i).setPickedUp(true);
				darts.remove(i);
			}

		for (int i = 0; i < dragons.size(); i++) {               

			/* Check if dragons can kill hero by breathing fire */
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

			/* Check if hero can kill dragons by being close */
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

		/* If hero has killed all the dragons and is in the exit game ends */
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

	/**
	 * Main game loop : getsInput, updates and draws each frame
	 */
	public void loop() {
		draw();
		/* Main game loop */
		while (!done && !closed) {
			if(this.firstLoad){
				this.gameGraphics.loadGame();
				this.firstLoad = false;
			}
			getInput();
			if(canUpdate) update();
			draw();

			if(gameGraphics instanceof GuiGraphics){
				try { Thread.sleep(1000/60); }
				catch(Exception e){}
			}

		}
		if(gameInput instanceof TestInput) return;
		if(!closed) gameGraphics.gameOver(win);
		while(!closed){
			getInput();
		}
		System.exit(0);
	}

	private void newGame(boolean load){
		canUpdate = false;
		firing = false;
		closed = false;
		done = false;
		win = false;
		dragons.clear();
		darts.clear();
		if(load){
			loadGame();
		}
		else{
			maze.generateRandomMaze(GameSettings.size, GameSettings.size);
			generateRandomPositions(GameSettings.numDragons,GameSettings.numDarts,GameSettings.difficulty);
		}
		loop();
	}

	/**
	 * Constructor for a game using the graphical interface
	 * @param gui TRUE: Creates a graphical interface | FALSE: Creates a graphical interface
	 */
	public Game(boolean gui) {
		maze = new Maze();
		this.gameInput = new GraphicsInput();
		this.gameGraphics = new GuiGraphics(this.gameInput);
		newGame(false);
	}

	/**
	 * Constructor for a game using the CLI interface
	 */
	public Game() {
		maze = new Maze();
		this.gameInput = new ConsoleInput();
		this.gameGraphics = new CliGraphics();
		while (true) {
			System.out.print("Load a maze (1, random): ");
			String command = gameInput.getInput();
			if (command.equals("1")) {
				maze.loadMaze(1);
			} else if (command.equals("random")) {
				int h = 0;
				while (h % 2 == 0) {
					System.out.print("Size? ");
					h = Integer.parseInt(gameInput.getInput());
					GameSettings.size = h;
				}
			} else continue;

			System.out.print("Dragon movement(easy, medium, hard)? ");
			command = gameInput.getInput();
			if (command.equals("easy")) {
				GameSettings.difficulty = 0;
			}
			else if(command.equals("medium")){
				GameSettings.difficulty = 1;
			}
			else if (command.equals("hard")) {
				GameSettings.difficulty = 2;
			} else continue;

			System.out.print("Number of dragons? ");
			GameSettings.numDragons = Integer.parseInt(gameInput.getInput());

			System.out.print("Number of darts? ");
			GameSettings.numDarts = Integer.parseInt(gameInput.getInput());
			break;
		}
		newGame(false);
	}

	/**
	 * Game constructor for test purposes
	 * @param maze Maze to use
	 * @param input Input to use
	 * @param hero Hero to use
	 * @param sword Sword to use
	 * @param shield Shield to use
	 * @param dragons List of dragons to use
	 * @param darts List of darts to use
	 */
	public Game(int maze[][], String[] input, Hero hero, Sword sword, Shield shield, ArrayList<Dragon> dragons, ArrayList<Dart> darts){
		this.gameInput = new TestInput(input);
		this.gameGraphics = new CliGraphics();
		this.maze = new Maze(maze);
		this.hero = hero;
		this.sword = sword;
		this.shield = shield;
		this.dragons = dragons;
		this.darts = darts;
	}

	private void saveGame(){
		ObjectOutputStream os = null;
		try{
			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "maze.save"));
			os.writeObject(this.maze);
			os.close();
			System.out.println("Saved maze\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "hero.save"));
			os.writeObject(this.hero);
			os.close();
			System.out.println("Saved hero\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "sword.save"));
			os.writeObject(this.sword);
			os.close();
			System.out.println("Saved sword\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "shield.save"));
			os.writeObject(this.shield);
			os.close();
			System.out.println("Saved shield\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "dragons.save"));
			os.writeObject(this.dragons);
			os.close();
			System.out.println("Saved dragons\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "darts.save"));
			os.writeObject(this.darts);
			os.close();
			System.out.println("Saved darts\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "settings.save"));
			os.writeObject(GameSettings.difficulty);
			os.close();
			System.out.println("Saved settings\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "keyup.save"));
			os.writeObject(GameSettings.K_UP);
			os.close();
			System.out.println("Loaded keyup\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "keydown.save"));
			os.writeObject(GameSettings.K_DOWN);
			os.close();
			System.out.println("Loaded keydown\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "keyleft.save"));
			os.writeObject(GameSettings.K_LEFT);
			os.close();
			System.out.println("Loaded keyleft\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "keyright.save"));
			os.writeObject(GameSettings.K_RIGHT);
			os.close();
			System.out.println("Loaded keyright\n");

			os = new ObjectOutputStream(new FileOutputStream(saveFolder + "keyfire.save"));
			os.writeObject(GameSettings.K_FIRE);
			os.close();
			System.out.println("Loaded keyfire\n");

		}
		catch(IOException e){ e.printStackTrace();
		}
	}

	private void loadGame(){
		ObjectInputStream is = null;
		try{
			is = new ObjectInputStream(new FileInputStream(saveFolder + "maze.save"));
			this.maze = (Maze) is.readObject();
			is.close();
			System.out.println("Loaded maze\n");

			is = new ObjectInputStream(new FileInputStream(saveFolder + "hero.save"));
			this.hero = (Hero ) is.readObject();
			is.close();
			System.out.println("Loaded hero\n");

			is = new ObjectInputStream(new FileInputStream(saveFolder + "sword.save"));
			this.sword = (Sword ) is.readObject();
			is.close();
			System.out.println("Loaded sword\n");

			is = new ObjectInputStream(new FileInputStream(saveFolder + "shield.save"));
			this.shield = (Shield ) is.readObject();
			is.close();
			System.out.println("Loaded shield\n");

			is = new ObjectInputStream(new FileInputStream(saveFolder + "dragons.save"));
			this.dragons = (ArrayList<Dragon>) is.readObject();
			is.close();
			System.out.println("Loaded dragons\n");

			is = new ObjectInputStream(new FileInputStream(saveFolder + "darts.save"));
			this.darts = (ArrayList<Dart>) is.readObject();
			is.close();
			System.out.println("Loaded darts\n");

			/* Set the game settings based on loaded files */
			GameSettings.size = this.maze.getW();
			GameSettings.numDarts = this.darts.size();
			GameSettings.numDragons = this.dragons.size();

			is = new ObjectInputStream(new FileInputStream(saveFolder + "settings.save"));
			GameSettings.difficulty = (int) is.readObject();
			is.close();
			System.out.println("Loaded settings\n");

			is = new ObjectInputStream(new FileInputStream(saveFolder + "keyup.save"));
			GameSettings.K_UP = (int) is.readObject();
			is.close();
			System.out.println("Loaded keyup\n");

			is = new ObjectInputStream(new FileInputStream(saveFolder + "keydown.save"));
			GameSettings.K_DOWN = (int) is.readObject();
			is.close();
			System.out.println("Loaded keydown\n");

			is = new ObjectInputStream(new FileInputStream(saveFolder + "keyleft.save"));
			GameSettings.K_LEFT = (int) is.readObject();
			is.close();
			System.out.println("Loaded keyleft\n");

			is = new ObjectInputStream(new FileInputStream(saveFolder + "keyright.save"));
			GameSettings.K_RIGHT = (int) is.readObject();
			is.close();
			System.out.println("Loaded keyright\n");

			is = new ObjectInputStream(new FileInputStream(saveFolder + "keyfire.save"));
			GameSettings.K_FIRE = (int) is.readObject();
			is.close();
			System.out.println("Loaded keyfire\n");

			System.out.println((char)GameSettings.K_UP);
			System.out.println((char)GameSettings.K_LEFT);
			System.out.println((char)GameSettings.K_DOWN);
			System.out.println((char)GameSettings.K_RIGHT);
			System.out.println((char)GameSettings.K_FIRE);
		}
		catch(IOException e){ e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the state of the game
	 * @return TRUE: If won | FALSE: If lost
	 */
	public boolean getWin(){
		return win;
	}
	/**
	 * @return Graphics interface the game is currently using
	 */
	public Graphics getGameGraphics(){ return this.gameGraphics;}
	
	/**
	 * @return Maze currently used by game
	 */
	public Maze getMaze(){return this.maze;}

	public static void main(String[] args) {
		Game game = new Game(true);
	}

}
