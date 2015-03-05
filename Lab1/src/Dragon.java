public class Dragon extends GameObject{
    private boolean alive = true;

    public Dragon(int x, int y) {
        super(x, y);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}