public class Dragon extends GameObject{
    private double chanceToSleep = 0.5;
    private double chanceToWake = 0.5;
    private boolean alive = true;
    private boolean sleeping = false;

    public Dragon(int x, int y) {
        super(x, y);
        this.setCliChar('D');
    }

    public void update(){
        if(sleeping) {
            this.setCliChar('d');
            if (Math.random() <= chanceToWake)
                sleeping = false;
        }
        else if(Math.random() <= chanceToSleep)
                sleeping = true;

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