package maze.logic.GameObjects;

import java.io.Serializable;

/**
 * Game object that represents the hero 
 */
public class Hero extends GameObject implements Serializable{

    private boolean dead = false;
    private boolean sword = false;
    private boolean shield = false;

    private int prevX, prevY;

    private int darts = 0;

    /**
     * Constructor for the Hero class
     * @param x Initial X position
     * @param y Initial Y position
     */
    public Hero(int x, int y){
        super(x,y);
        this.setCliChar('H');
    }

    private void updateCliChar(){
        if(hasSword() && hasShield())
            this.setCliChar('B');
        else if(hasSword() && !hasShield())
            this.setCliChar('A');
        else if(!hasSword() && hasShield())
            this.setCliChar('P');
        else this.setCliChar('H');
    }

    /**
     * Updates x and y position of the hero
     * @param maze Game maze in use for collision checking
     */
    public void update(Maze maze){
        updateCliChar();
        
        prevX = getX();
        prevY = getY();

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
    
    /**
     * Moves the hero back to the previous position
     */
    public void goBack(){
    	setX(prevX);
    	setY(prevY);
    }

    /**
     * @return TRUE: If hero is wielding sword | FALSE: If hero is not wielding sword
     */
    public boolean hasSword() {
        return sword;
    }

    /**
     * @param sword Value to set 'sword' to
     */
    public void setSword(boolean sword) {
        this.sword = sword;
        updateCliChar();
    }

    /**
     * @return TRUE: If hero is wielding shield | FALSE: If hero is not wielding shield
     */
    public boolean hasShield() {
        return shield;
    }
    
    /**
     * @param shield Value to set 'shield' to
     */

    public void setShield(boolean shield) {
        this.shield = shield;
        updateCliChar();
    }

    /**
     * @return Number of darts the hero is currently holding
     */
    public int getDarts() {
        return darts;
    }
    
    /**
     * Increments the number of darts the hero is holding
     */

    public void addDart(){ this.darts++;}

    /**
     * Decrements the number of darts the hero is holding
     */
    public void removeDart(){ this.darts--;}
    
    /**
     * @param darts Value of darts the hero will be holding
     */
    public void setDarts(int darts) {
        this.darts = darts;
    }
    
    /**
     * @return TRUE: If hero is dead | FALSE: If hero is alive
     */
	public boolean isDead() {
		return dead;
	}
	
	/**
	 * @param dead Value to set the hero state to
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
