package maze.logic.GameObjects;

import java.io.Serializable;

/**
 * Game object that represents a dart 
 */
public class Dart extends GameObject implements Serializable{
    private boolean pickedUp = false;

    /**
     * Constructor for the dart class
     * @param x Initial X position
     * @param y Initial Y position
     */
    public Dart(int x, int y) {
        super(x, y);
        this.setCliChar('-');
    }

    /**
     * @return TRUE: If has been picked up | FALSE: IF hasn't been picked up
     */
    public boolean isPickedUp() {
        return pickedUp;
    }

    /**
     * @param pickedUp Value to set 'pickedUp' to
     */
    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
