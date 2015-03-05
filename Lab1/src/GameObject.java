public class GameObject {

    private int x,y,xspeed,yspeed;


    private char cliChar = 'a';

    GameObject(int x, int y){
        this.x = x;
        this.y = y;
        this.xspeed = this.yspeed = 0;
    }

    public void update(){
        x += xspeed;
        y += yspeed;

        xspeed = 0;
        yspeed = 0;
    }

    public char getCliChar() {
        return cliChar;
    }

    public void setCliChar(char cliChar) {
        this.cliChar = cliChar;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXspeed() {
        return xspeed;
    }

    public void setXspeed(int xspeed) {
        this.xspeed = xspeed;
    }

    public int getYspeed() {
        return yspeed;
    }

    public void setYspeed(int yspeed) {
        this.yspeed = yspeed;
    }
}
