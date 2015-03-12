package maze.GameObjects;

public class Shield extends GameObject{
    private boolean pickedUp = false;

    public Shield(int x, int y){
        super(x,y);
        this.setCliChar('O');
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
