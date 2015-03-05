public class Maze {
    private int h=0, w=0;
    private int[][] maze;

    public void loadMaze(int number){
        switch (number){
            case 1:
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
        if(x >= w || y >= h) throw new IndexOutOfBoundsException();
        else return maze[y][x];
    }

    /* Predefined mazes */
    private int[][] baseMaze1 = {
            {1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,2},
            {1,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1},
    };
}
