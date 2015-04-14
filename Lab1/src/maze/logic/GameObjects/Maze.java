package maze.logic.GameObjects;

import java.io.Serializable;

import maze.logic.Utils.MazeBuilder;

/**
 * Class that represents the maze in the game.
 * It is responsible for checking line of sight between positions. 
 */
public class Maze implements Serializable{
    private int h=0, w=0;
    private int[][] maze;

    /**
     * Loads a predefined maze
     * @param number Maze number to load
     */
    public void loadMaze(int number){
        switch (number){
            case 1:
                maze = new int[10][10];
                maze = baseMaze1;
                h = w = 10;
                break;
        }
    }

    /**
     * Generates a random maze using the utility class 'MazeBuilder'
     * @param h Height of the maze to generate
     * @param w Width of the maze to generate
     */
    public void generateRandomMaze(int h, int w){
        MazeBuilder builder = new MazeBuilder();
        maze = builder.createMaze(h, w);
        this.h = h;
        this.w = w;
    }

    /**
     * Checks line of sight between two points in the maze
     * @param xi X value for the initial point
     * @param yi Y value for the initial point
     * @param xf X value for the final point
     * @param yf Y value for the final point
     * @param direction Which direction to check (0:Left, 1:Right, 2:UP, 3:Down)
     * @return TRUE: If there is no walls between the two points | FALSE: If there is at least a wall between the two points
     */
    public boolean checkLineOfSight(int xi, int yi, int xf, int yf, int direction){
        int xspeed = 0;
        int yspeed = 0;
        switch(direction){
            case 0:
                xspeed = -1; break;
            case 1:
                xspeed = 1; break;
            case 2:
                yspeed = -1; break;
            case 3:
                yspeed = 1; break;
        }
        while(xi != xf || yi != yf){
            xi+=xspeed;
            yi+=yspeed;
            if(getPosition(xi,yi) != 0) return false;
        }
        return true;
    }
    
    /**
     * @return Height of maze
     */
    public int getH(){
        return h;
    }
    
    /**
     * @return Width of maze
     */
    public int getW(){
        return w;
    }

    /**
     * @return Current maze
     */
    public int[][] getMaze(){
        return maze;
    }
    
    /**
     * Gets the maze block equivalent to the X and Y values specified
     * @param x X value of the position
     * @param y Y value of the position
     * @return 0 if Floor, 1 if Wall and 2 if Exit
     */
    public int getPosition(int x, int y){
        if(x >= w || y >= h){
            throw new IndexOutOfBoundsException();
        }
        else return maze[y][x];
    }
    
    public Maze(){
    	// Empty constructor
    }

    /**
     * Constructor of the Maze class based on a predefined maze
     * @param maze Predefined maze
     */
    public Maze(int[][] maze){
    	this.maze = maze;
    	this.h = maze.length;
    	this.w = maze[0].length;
    }
    /* Predefined mazes */
    private int[][] baseMaze1 = {
            {1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,0,1,0,1,0,1},
            {1,0,1,1,0,1,0,1,0,1},
            {1,0,1,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,1,0,2},
            {1,0,1,1,0,1,0,1,0,1},
            {1,0,1,1,0,1,0,1,0,1},
            {1,0,1,1,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1}
    };
}
