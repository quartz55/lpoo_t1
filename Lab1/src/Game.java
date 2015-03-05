import java.util.Random;
import java.util.Scanner;

public class Game {

    private boolean done = false;
    private boolean win = false;

    private Input input;
    private Maze maze;
    private Hero hero;
    private Sword sword;
    private Dragon dragon;

    private void getInput(){
        System.out.print("Command(w/a/s/d)? ");
        String command = input.readConsoleInput();
        if(command.equals("d")){
            hero.setXspeed(hero.getXspeed()+1);
        }
        else if(command.equals("a")){
            hero.setXspeed(hero.getXspeed()-1);
        }
        else if(command.equals("w")){
            hero.setYspeed(hero.getYspeed() - 1);
        }
        else if(command.equals("s")){
            hero.setYspeed(hero.getYspeed()+1);
        }
    }

    private void update(){

        Random rand = new Random();
        if(rand.nextBoolean()){
            dragon.setXspeed(rand.nextInt(3)-1);
        }
        else{
            dragon.setYspeed(rand.nextInt(3)-1);
        }

        if(maze.getPosition(dragon.getX()+dragon.getXspeed(), dragon.getY()+dragon.getYspeed())== 0){
            dragon.update();
        }

        if(maze.getPosition(hero.getX()+hero.getXspeed(), hero.getY()+hero.getYspeed()) == 0
             || (maze.getPosition(hero.getX()+hero.getXspeed(), hero.getY()+hero.getYspeed()) == 2 && hero.hasSword())){
            hero.update();
        }

        if(maze.getPosition(hero.getX(), hero.getY())== 2){
            done = true;
            win = true;
        }

        boolean adjacente = false;
        for(int i = -1; i <=1; i++)
            for(int j = -1; j <=1; j++) {
                if(Math.abs(i) == Math.abs(j) && i != 0) continue;
                if (hero.getX() == dragon.getX() + j
                        && hero.getY() == dragon.getY() + i)
                    adjacente = true;
            }

        if(adjacente) {
            if (!hero.hasSword()) {
                done = true;
                win = false;
            } else {
                dragon.setAlive(false);
            }
        }

        if(hero.getX() == sword.getX()
                && hero.getY() == sword.getY()
                && !sword.isPickedUp()){
            sword.setPickedUp(true);
            hero.setSword(true);
        }

        hero.setXspeed(0);
        hero.setYspeed(0);

        dragon.setXspeed(0);
        dragon.setYspeed(0);
    }

    private void draw(){
        /* Clean the screen */
        for(int i = 0; i < 30; i++) System.out.println();

        for(int i = 0; i < maze.getH(); i++) {
            for (int j = 0; j < maze.getW(); j++) {
                if(i == hero.getY() && j == hero.getX()) {
                    if(hero.hasSword())
                        System.out.print("A");
                    else
                        System.out.print("H");

                    continue;
                }

                else if(i == sword.getY() && j == sword.getX()
                        && !sword.isPickedUp()) {
                    if(i == dragon.getY() && j == dragon.getX()
                            && dragon.isAlive())
                        System.out.print("F");
                    else
                        System.out.print("E");

                    continue;
                }

                else if(i == dragon.getY() && j == dragon.getX()
                        && dragon.isAlive()) {
                    System.out.print("D");
                    continue;
                }

                if (maze.getPosition(j,i) == 2)
                    System.out.print("S");
                else if (maze.getPosition(j,i) == 1)
                    System.out.print(".");
                else if (maze.getPosition(j,i) == 0)
                    System.out.print(" ");

            }
            System.out.println();
        }
    }

    public void loop(){
        draw();
        /* Main game loop */
        while(!done){
            getInput();
            update();
            draw();
        }
        if(win)
            System.out.println("VICTORY!");
        else
            System.out.println("YOU LOSE!");
    }

    public Game(){
        maze = new Maze();
        input = new Input();
        while(true) {
            System.out.print("Load a maze (1, random): ");
            String command = input.readConsoleInput();
            if(command.equals("1")){
                maze.loadMaze(1);
            }
            else if(command.equals("random")){
                int h = 0, w = 0;
                while(h%2 == 0) {
                    System.out.print("Height? ");
                    h = Integer.parseInt(input.readConsoleInput());
                }
                while(w%2 == 0) {
                    System.out.print("Width? ");
                    w = Integer.parseInt(input.readConsoleInput());
                }
                maze.generateRandomMaze(h,w);
            }
            else continue;
            break;
        }

        /* Generate random positions for the game objects (hero, sword and dragon) */
        int randomX, randomY;
        while(true){
            randomX = (int)(Math.random()*maze.getW());
            randomY = (int)(Math.random()*maze.getH());
            if(maze.getPosition(randomX, randomY) == 0){
                hero = new Hero(randomX, randomY);
                break;
            }
        }
        while(true){
            randomX = (int)(Math.random()*maze.getW());
            randomY = (int)(Math.random()*maze.getH());
            if(maze.getPosition(randomX, randomY) == 0){
                sword = new Sword(randomX, randomY);
                break;
            }
        }
        while(true){
            randomX = (int)(Math.random()*maze.getW());
            randomY = (int)(Math.random()*maze.getH());
            if(maze.getPosition(randomX, randomY) == 0){
                dragon = new Dragon(randomX, randomY);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Game jogo = new Game();
        jogo.loop();
    }

}
