package maze;

import maze.GameObjects.*;

import java.util.ArrayList;

public class Game {

    private boolean done = false;
    private boolean win = false;

    private Input input;
    private Maze maze;
    private Hero hero;
    private Sword sword;
    private Shield shield;
    private ArrayList<Dragon> dragons = new ArrayList<Dragon>();

    private void getInput() {
        System.out.print("Command(w/a/s/d/f)? ");
        String command = input.readConsoleInput();
        if (command.equals("d")) {
            hero.setXspeed(hero.getXspeed() + 1);
        } else if (command.equals("a")) {
            hero.setXspeed(hero.getXspeed() - 1);
        } else if (command.equals("w")) {
            hero.setYspeed(hero.getYspeed() - 1);
        } else if (command.equals("s")) {
            hero.setYspeed(hero.getYspeed() + 1);
        }
     else if (command.equals("f")) {
            hero.removeDart();
    }
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

        /* Update dragons ArrayList */
        for (int i = 0; i < dragons.size(); i++) {

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
                    done = true;
                    win = false;
                } else if(hero.hasSword()){
                    dragons.remove(i);
                }
            }
        }

        if (maze.getPosition(hero.getX(), hero.getY()) == 2) {
            done = true;
            win = true;
        }
    }

    private void draw() {
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
                    if (maze.getPosition(j, i) == 2)
                        System.out.print("S");
                    else if (maze.getPosition(j, i) == 1)
                        System.out.print(".");
                    else if (maze.getPosition(j, i) == 0)
                        System.out.print(" ");
                }

            }
            System.out.println();
        }
        System.out.println("Darts: " + hero.getDarts());
    }

    public void loop() {
        draw();
        /* Main game loop */
        while (!done) {
            getInput();
            update();
            draw();
        }
        if (win)
            System.out.println("VICTORY!");
        else
            System.out.println("YOU LOSE!");
    }

    public Game() {
        maze = new Maze();
        input = new Input();
        int difficulty = -1;
        while (true) {
            System.out.print("Load a maze (1, random): ");
            String command = input.readConsoleInput();
            if (command.equals("1")) {
                maze.loadMaze(1);
            } else if (command.equals("random")) {
                int h = 0, w = 0;
                while (h % 2 == 0) {
                    System.out.print("Height? ");
                    h = Integer.parseInt(input.readConsoleInput());
                }
                while (w % 2 == 0) {
                    System.out.print("Width? ");
                    w = Integer.parseInt(input.readConsoleInput());
                }
                maze.generateRandomMaze(h, w);
            } else continue;

            System.out.print("Dragon movement(easy, medium, hard)? ");
            command = input.readConsoleInput();
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
        int number_of_dragons = 3;
        while (true) {
            randomX = (int) (Math.random() * maze.getW());
            randomY = (int) (Math.random() * maze.getH());
            if (maze.getPosition(randomX, randomY) == 0) {
                dragons.add(new Dragon(randomX, randomY, difficulty));
                number_of_dragons--;
                if (number_of_dragons <= 0) break;
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.loop();
    }

}
