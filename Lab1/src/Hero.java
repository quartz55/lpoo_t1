public class Hero extends GameObject{

    private boolean sword = false;

    public Hero(int x, int y){
        super(x,y);
        this.setCliChar('H');
    }

    public void update(Maze maze){
        if(hasSword())
            this.setCliChar('A');
        else this.setCliChar('H');

        if(maze.getPosition(this.getX()+this.getXspeed(), this.getY()+this.getYspeed()) == 1
                || (maze.getPosition(this.getX()+this.getXspeed(), this.getY()+this.getYspeed()) == 2 && !this.hasSword())){
            this.setXspeed(0);
            this.setYspeed(0);
        }

        this.setX(this.getX()+this.getXspeed());
        this.setY(this.getY()+this.getYspeed());

        this.setXspeed(0);
        this.setYspeed(0);
    }

    public boolean hasSword() {
        return sword;
    }

    public void setSword(boolean sword) {
        this.sword = sword;
    }
}
