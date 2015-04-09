package maze.GameObjects;

import maze.Utils.MazeBuilder;

public class Maze {
    private int h=0, w=0;
    private int[][] maze;

    public void loadMaze(int number){
        switch (number){
            case 1:
                maze = new int[10][10];
                maze = baseMaze1;
                h = w = 10;
                break;
        }
    }

    public void generateRandomMaze(int h, int w){
        MazeBuilder builder = new MazeBuilder();
        maze = builder.createMaze(h, w);
        this.h = h;
        this.w = w;
    }

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
    public int getH(){
        return h;
    }
    public int getW(){
        return w;
    }
    public int[][] getMaze(){
        return maze;
    }
    public int getPosition(int x, int y){
        if(x >= w || y >= h){
            System.out.println(x + " - " + y);
            throw new IndexOutOfBoundsException();
        }
        else return maze[y][x];
    }
    
    public Maze(){
    	// Empty constructor
    }

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
