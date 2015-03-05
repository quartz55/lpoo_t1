import java.util.Random;
import java.util.Scanner;

public class Labirinto {

    private boolean done = false;
    private boolean win = false;

    int h=0, w=0;
    private int[][] mapa;

    private Prim mazeGenerator = new Prim();
    private Heroi hero;
    private Espada sword;
    private Dragao dragon;

    private void getInput(){
        Scanner user_input = new Scanner(System.in);
        String command;
        System.out.print("Command(w/a/s/d)? ");
        command = user_input.next();
        if(command.equals("d")){
                hero.xspeed++;
        }
        else if(command.equals("a")){
                hero.xspeed--;
        }
        else if(command.equals("w")){
                hero.yspeed--;
        }
        else if(command.equals("s")){
                hero.yspeed++;
        }
    }

    private void updateMapa(){

        Random rand = new Random();
        if(rand.nextBoolean()){
            dragon.xspeed = rand.nextInt(3)-1;
        }
        else{
            dragon.yspeed = rand.nextInt(3)-1;
        }

        if(mapa[dragon.y+dragon.yspeed][dragon.x+dragon.xspeed] == 0){
            dragon.x += dragon.xspeed;
            dragon.y += dragon.yspeed;
        }

        if(mapa[hero.y+hero.yspeed][hero.x+hero.xspeed] == 0
                || (mapa[hero.y+hero.yspeed][hero.x+hero.xspeed] == 2
        && hero.temEspada)){
            hero.x += hero.xspeed;
            hero.y += hero.yspeed;
        }

        if(mapa[hero.y][hero.x] == 2){
            done = true;
            win = true;
        }

        boolean adjacente = false;
        for(int i = -1; i <=1; i++)
            for(int j = -1; j <=1; j++) {
                if(Math.abs(i) == Math.abs(j) && i != 0) continue;
                if (hero.x == dragon.x + j
                        && hero.y == dragon.y + i)
                    adjacente = true;
            }

        if(adjacente) {
            if (!hero.temEspada) {
                done = true;
                win = false;
            } else {
                dragon.alive = false;
            }
        }

        if(hero.x == sword.x
                && hero.y == sword.y
                && !sword.pickedUp){
            sword.pickedUp = true;
            hero.temEspada = true;
        }

        hero.xspeed = 0;
        hero.yspeed = 0;

        dragon.xspeed = 0;
        dragon.yspeed = 0;
    }

    private void drawMapa(){
        /* Clean the screen */
        for(int i = 0; i < 30; i++) System.out.println();

        for(int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(i == hero.y && j == hero.x) {
                    if(hero.temEspada)
                        System.out.print("A");
                    else
                        System.out.print("H");

                    continue;
                }

                else if(i == sword.y && j == sword.x
                        && !sword.pickedUp) {
                    if(i == dragon.y && j == dragon.x
                            && dragon.alive)
                        System.out.print("F");
                    else
                        System.out.print("E");

                    continue;
                }

                else if(i == dragon.y && j == dragon.x
                        && dragon.alive) {
                    System.out.print("D");
                    continue;
                }

                if (mapa[i][j] == 2)
                    System.out.print("S");
                else if (mapa[i][j] == 1)
                    System.out.print(".");
                else if (mapa[i][j] == 0)
                    System.out.print(" ");

            }
            System.out.println();
        }
    }

    public Labirinto(){
        Scanner user_input = new Scanner(System.in);
        while(h%2 == 0) {
            System.out.print("Height? ");
            h = Integer.parseInt(user_input.next());
        }
        while(w%2 == 0) {
            System.out.print("Width? ");
            w = Integer.parseInt(user_input.next());
        }
        mapa = mazeGenerator.createMaze(h,w);

        int herox, heroy, swordx, swordy, dragonx, dragony;
        while(true){
            herox = (int)(Math.random()*w);
            heroy = (int)(Math.random()*h);
            if(mapa[heroy][herox] == 0){
                hero = new Heroi(herox, heroy);
                break;
            }
        }

        while(true){
            swordx = (int)(Math.random()*w);
            swordy = (int)(Math.random()*h);
            if(mapa[swordy][swordx] == 0){
                sword = new Espada(swordx, swordy);
                break;
            }
        }

        while(true){
            dragonx = (int)(Math.random()*w);
            dragony = (int)(Math.random()*w);
            if(mapa[dragony][dragonx] == 0){
                dragon = new Dragao(dragonx, dragony);
                break;
            }
        }
    }

    public void loop(){
        drawMapa();
        while(!done){
            getInput();
            updateMapa();
            drawMapa();
        }
        if(win)
            System.out.println("VICTORY!");
        else
            System.out.println("YOU LOOSE!");
    }

    public static void main(String[] args) {
        Labirinto jogo = new Labirinto();
        jogo.loop();
    }

}
