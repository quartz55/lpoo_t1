package maze.GameObjects;

public class Hero extends GameObject{

    private boolean dead = false;
    private boolean sword = false;
    private boolean shield = false;

    private int prevX, prevY;

    private int darts = 0;

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
    
    public void goBack(){
    	setX(prevX);
    	setY(prevY);
    }

    public boolean hasSword() {
        return sword;
    }
    public void setSword(boolean sword) {
        this.sword = sword;
        updateCliChar();
    }

    public boolean hasShield() {
        return shield;
    }
    public void setShield(boolean shield) {
        this.shield = shield;
        updateCliChar();
    }

    public int getDarts() {
        return darts;
    }
    public void addDart(){ this.darts++;}
    public void removeDart(){ this.darts--;}
    public void setDarts(int darts) {
        this.darts = darts;
    }
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
