public class Hero extends GameObject{

    private boolean sword = false;

    public Hero(int x, int y){
        super(x,y);
    }

    public boolean hasSword() {
        return sword;
    }

    public void setSword(boolean sword) {
        this.sword = sword;
    }
}
