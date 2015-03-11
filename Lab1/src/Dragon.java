import java.util.Random;

public class Dragon extends GameObject{
    private double chanceToSleep = 0.5;
    private double chanceToWake = 0.5;
    private boolean alive = true;
    private boolean sleeping = false;

    public Dragon(int x, int y) {
        super(x, y);
        this.setCliChar('D');
    }

    public void update(Maze maze){
        if(sleeping) {
            this.setCliChar('d');
            if (Math.random() <= chanceToWake)
                sleeping = false;
        }
        else if(Math.random() <= chanceToSleep)
                sleeping = true;

        Random rand = new Random();
        if (rand.nextBoolean()) {
            this.setXspeed(rand.nextInt(3) - 1);
        } else {
            this.setYspeed(rand.nextInt(3) - 1);
        }

        if (maze.getPosition(this.getX() + this.getXspeed(), this.getY() + this.getYspeed()) != 0) {
            this.setXspeed(0);
            this.setYspeed(0);
        }

        if(!sleeping){
            this.setCliChar('D');
            this.setX(this.getX()+this.getXspeed());
            this.setY(this.getY()+this.getYspeed());
        }

        this.setXspeed(0);
        this.setYspeed(0);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }
}