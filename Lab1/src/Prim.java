import java.util.ArrayList;

public class Prim {
    int maze[][];
    int visited[][];
    ArrayList<Point> stack;

    private void generateMaze(int h, int w){
        /* Init with the structure needed */
        for(int i = 0; i < h; i++) { // Actual maze
            for (int j = 0; j < w; j++) {
                if(i%2 == 1 && j%2 == 1) maze[i][j] = 0;
                else maze[i][j] = 1;
            }
        }
        for(int i = 0; i < (h-1)/2; i++) // Visited cells maze
            for(int j = 0; j < (w-1)/2; j++)
                visited[i][j] = 0;

        /* Generate starting point/exit */
        int startX = 0, startY = 0;
        switch((int)(Math.random()*4)){
            case 0: // Left wall
                startX = 0;
                startY = (int)(Math.random()*(h-1)/2);
                maze[startY*2+1][0] = 2;
                break;
            case 1: // Right wall
                startX = (w-1)/2-1;
                startY = (int)(Math.random()*(h-1)/2);
                maze[startY*2+1][w-1] = 2;
                break;
            case 2: // Up wall
                startX = (int)(Math.random()*(w-1)/2);
                startY = 0;
                maze[0][startX*2+1] = 2;
                break;
            case 3: // Down wall
                startX = (int)(Math.random()*(w-1)/2);
                startY = (h-1)/2-1;
                maze[h-1][startX*2+1] = 2;
                break;
        }
        Point guideCell = new Point(startX,startY);
        stack.add(new Point(guideCell.x, guideCell.y));
        visited[guideCell.y][guideCell.x] = 1;

        while(!stack.isEmpty()){ // While stack not empty
            guideCell = stack.get(stack.size()-1);
            /* Check if guideCell has available neighbors */
            if(!guideCell.hasNeighbors()){
                /* Pop the stack and point to previous guideCell */
                stack.remove(stack.size()-1);
                continue;
            }

            /* Generate a direction */
            int direction = (int)(Math.random()*4);
            int wallRemoveX = guideCell.x*2+1;
            int wallRemoveY = guideCell.y*2+1;
            switch(direction){
                case 0: // UP
                    if(guideCell.y-1 < 0) continue;
                    guideCell = new Point(guideCell.x, guideCell.y-1);
                    stack.add(new Point(guideCell.x, guideCell.y));
                    visited[guideCell.y][guideCell.x] = 1;
                    wallRemoveY--;
                    break;
                case 1: // RIGHT
                    if(guideCell.x+1 > (w-1)/2-1) continue;
                    guideCell = new Point(guideCell.x+1, guideCell.y);
                    stack.add(new Point(guideCell.x, guideCell.y));
                    visited[guideCell.y][guideCell.x] = 1;
                    wallRemoveX++;
                    break;
                case 2: // DOWN
                    if(guideCell.y+1 > (h-1)/2-1) continue;
                    guideCell = new Point(guideCell.x, guideCell.y+1);
                    stack.add(new Point(guideCell.x, guideCell.y));
                    visited[guideCell.y][guideCell.x] = 1;
                    wallRemoveY++;
                    break;
                case 3: // LEFT
                    if(guideCell.x-1 < 0) continue;
                    guideCell = new Point(guideCell.x-1, guideCell.y);
                    stack.add(new Point(guideCell.x, guideCell.y));
                    visited[guideCell.y][guideCell.x] = 1;
                    wallRemoveX--;
                    break;
            }
            /* Remove wall between cells */
            this.maze[wallRemoveY][wallRemoveX] = 0;
        }
    }

    public int[][] createMaze(int h, int w){
        this.maze = new int[h][w];
        this.visited = new int[(h-1)/2][(w-1)/2];
        this.stack = new ArrayList<Point>();

        generateMaze(h,w);

        return this.maze;
    }

    private class Point{
        public int x;
        public int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public boolean hasNeighbors(){
            for(int i = -1; i <= 1; i++){
                for(int j = -1; j <= 1; j++){
                    if(Math.abs(i) == Math.abs(j)) continue;
                    try {
                        if (visited[y + i][x + j] == 0) return true;
                    } catch(Exception e){ continue;}
                }
            }
            return false;
        }
    }
}