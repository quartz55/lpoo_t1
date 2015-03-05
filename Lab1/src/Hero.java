public class Hero extends GameObject{

    private boolean sword = false;

    public Hero(int x, int y){
        super(x,y);
        this.setCliChar('H');
    }

    public void update(){
        if(hasSword())
            this.setCliChar('A');
        else this.setCliChar('H');

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
