package maze.logic.GameObjects;

import java.io.Serializable;
import java.util.Random;

/**
 * Game object that represents a dragon 
 */
public class Dragon extends GameObject implements Serializable{
    private double chanceToSleep = 0.5;
    private double chanceToWake = 0.5;
    private boolean alive = true;
    private boolean sleeping = false;
    private int difficulty = 0;
    private double chanceToBreatheFire= 0.5;
    private boolean fire = false;
    

    /**
     * Constructor for the dragon class
     * @param x Initial X position
     * @param y Initial Y position
     * @param difficulty Dragon difficulty
     */
    public Dragon(int x, int y, int difficulty) {
        super(x, y);
        this.setCliChar('D');
        this.difficulty = difficulty;
    }

    /**
     * Updates x and y position of the dragon.
     * Random chance to sleep and breathe fire 
     * @param maze Game maze in use for collision checking
     */
    public void update(Maze maze){
        if(difficulty > 0) {
            Random rand = new Random();
            if (rand.nextBoolean()) {
                this.setXspeed(rand.nextInt(3) - 1);
            } else {
                this.setYspeed(rand.nextInt(3) - 1);
            }
        }

        if(difficulty > 1) {
        	fire = false;
        	if(Math.random() <= chanceToBreatheFire) fire = true;

            if (sleeping) {
                this.setCliChar('d');
                if (Math.random() <= chanceToWake)
                    sleeping = false;
            } else if (Math.random() <= chanceToSleep)
                sleeping = true;
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

    /**
     * @return TRUE: If dragon is alive | FALSE: If dragon is dead
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive Value to set 'alive' to 
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * @return TRUE: If dragon is sleeping | FALSE: If dragon is dead
     */
    public boolean isSleeping() {
        return sleeping;
    }

    /**
     * @param sleeping Value to set 'sleeping' to
     */
    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }
    
    /**
     * @return TRUE: If dragon is breathing fire | FALSE: If dragon is not breathing fire
     */
    public boolean isBreathingFire() {
        return fire;
    }
    
    /**
     * @param fire Value to set 'fire' to
     */
    public void setFire(boolean fire) {
        this.fire = fire;
    }
}