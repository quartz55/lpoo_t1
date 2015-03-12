package maze.GameObjects;

public class Dart extends GameObject {
    private boolean pickedUp = false;

    public Dart(int x, int y) {
        super(x, y);
        this.setCliChar('t');
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
