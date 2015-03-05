public class Sword extends GameObject{
    private boolean pickedUp = false;

    public Sword(int x, int y){
        super(x,y);
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
