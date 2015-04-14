package maze.logic.GameObjects;

import java.io.Serializable;

/**
 * Game object that represents a sword 
 */
public class Sword extends GameObject implements Serializable{
    private boolean pickedUp = false;

    /**
     * Constructor for the Sword class
     * @param x Initial X position
     * @param y Initial Y position
     */
    public Sword(int x, int y){
        super(x,y);
        this.setCliChar('E');
    }

    /**
     * @return TRUE: If sword has been picked up | FALSE: If sword hasn't been picked up
     */
    public boolean isPickedUp() {
        return pickedUp;
    }

    /**
     * @param pickedUp Value to set the 'pickedUp' value to
     */
    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
