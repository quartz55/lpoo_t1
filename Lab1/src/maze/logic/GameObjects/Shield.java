package maze.logic.GameObjects;

import java.io.Serializable;

/**
 * Game object that represents a shield 
 */
public class Shield extends GameObject implements Serializable{
    private boolean pickedUp = false;

    /**
     * Constructor for the Shield class
     * @param x Initial X position
     * @param y Initial Y position
     */
    public Shield(int x, int y){
        super(x,y);
        this.setCliChar('O');
    }

    /**
     * @return TRUE: If shield has been picked up | FALSE: If shield hasn't been picked up
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
