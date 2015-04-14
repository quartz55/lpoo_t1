package maze.logic.GameObjects;

import java.io.Serializable;

/**
 * Base class for the game objects. It is extended by all the other objects
 */
public class GameObject implements Serializable{

    private int x,y;
    private transient int xspeed,yspeed;

    private char cliChar = 'a';

    /**
     * Constructor for the GameObject class
     * @param x Initial X position
     * @param y Initial Y position
     */
    GameObject(int x, int y){
        this.x = x;
        this.y = y;
        this.xspeed = this.yspeed = 0;
    }

    /**
     * Updates X and Y values based on the speed
     */
    public void update(){
        x += xspeed;
        y += yspeed;

        xspeed = 0;
        yspeed = 0;
    }

    /**
     * @return Char to use for the CLI
     */
    public char getCliChar() {
        return cliChar;
    }

    /**
     * @param cliChar Char to set the 'cliChar' to
     */
    public void setCliChar(char cliChar) {
        this.cliChar = cliChar;
    }

    /**
     * @return X position of the game object
     */
    public int getX() {
        return x;
    }

    /**
     * @param x Value to set the X position of the game object to
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return Y position of the game object
     */
    public int getY() {
        return y;
    }

    /**
     * @param y Value to set the Y position of the game object to
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return Value of game object horizontal speed
     */
    public int getXspeed() {
        return xspeed;
    }

    /**
     * @param xspeed Value to set the horizontal speed of the game object to
     */
    public void setXspeed(int xspeed) {
        this.xspeed = xspeed;
    }

    /**
     * @return Value of game object vertical speed
     */
    public int getYspeed() {
        return yspeed;
    }

    /**
     * @param yspeed Value to set the vertical speed of the game object to
     */
    public void setYspeed(int yspeed) {
        this.yspeed = yspeed;
    }
}
