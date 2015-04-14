package maze.Graphics;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.Input.Input;
import maze.logic.GameSettings;
import maze.logic.GameObjects.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel
implements KeyListener{

	private Input input;
	private int WIDTH, HEIGHT;

	private Maze maze;
	private Hero hero;
	private Sword sword;
	private Shield shield;
	private ArrayList<Dragon> dragons;
	private ArrayList<Dart> darts;

	/* Images */
	private BufferedImage hero_img;
	private BufferedImage sword_img;
	private BufferedImage shield_img;
	private BufferedImage dragon_img;
	private BufferedImage dragonSleeping_img;
	private BufferedImage dart_img;

	public GamePanel(Input input, int width, int height) {
		this.addKeyListener(this);
		this.input = input;
		this.WIDTH = width;
		this.HEIGHT = height;
		
		try{
            hero_img =  ImageIO.read(new File("images/hero.png"));
            sword_img =  ImageIO.read(new File("images/sword.png"));
            shield_img =  ImageIO.read(new File("images/shield.png"));
            dragon_img =  ImageIO.read(new File("images/dragon.png"));
            dragonSleeping_img =  ImageIO.read(new File("images/dragon_sleep.png"));
            dart_img =  ImageIO.read(new File("images/dart.png"));
			
		} catch(IOException e){e.printStackTrace();}
	}

	public void draw(){ repaint();}

	public void paintComponent(java.awt.Graphics g){
		super.paintComponent(g);

		int gridW = WIDTH/maze.getW();
		int gridH = HEIGHT/maze.getH();
		for (int i = 0; i < maze.getH(); i++) {
			for (int j = 0; j < maze.getW(); j++) {
				switch(maze.getPosition(j, i)){
				case 0:
					g.setColor(Color.WHITE);
					break;
				case 1:
					g.setColor(Color.BLACK);
					break;
				case 2:
					g.setColor(Color.BLUE);
					break;
				}
				g.fillRect(j*gridW, i*gridH, gridW, gridH);
			}
		}

		for(int i = 0; i < darts.size(); i++)
			g.drawImage(dart_img, darts.get(i).getX()*gridW, darts.get(i).getY()*gridH, gridH, gridW, null);

		g.drawImage(hero_img,hero.getX()*gridW, hero.getY()*gridH, gridW, gridH, null);

		if(!sword.isPickedUp()){
			g.drawImage(sword_img,sword.getX()*gridW, sword.getY()*gridH, gridW, gridH, null);
		}
		if(!shield.isPickedUp()){
			g.drawImage(shield_img,shield.getX()*gridW, shield.getY()*gridH, gridW, gridH, null);
		}

		for(int i = 0; i < dragons.size(); i++){
			if(dragons.get(i).isSleeping()){
				g.drawImage(dragonSleeping_img,dragons.get(i).getX()*gridW, dragons.get(i).getY()*gridH, gridW, gridH, null);
			}
			else g.drawImage(dragon_img,dragons.get(i).getX()*gridW, dragons.get(i).getY()*gridH, gridW, gridH, null);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int kcode = e.getKeyCode();
		if(kcode == GameSettings.K_UP)
			input.addInput("w");
		if(kcode == GameSettings.K_LEFT)
			input.addInput("a");
		if(kcode == GameSettings.K_DOWN)
			input.addInput("s");
		if(kcode == GameSettings.K_RIGHT)
			input.addInput("d");
		if(kcode == GameSettings.K_FIRE)
			input.addInput("f");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	public Input getInput(){ return this.input;}

	public void setMaze(Maze maze){ this.maze = maze;}
	public void setHero(Hero hero){ this.hero = hero;}
	public void setSword(Sword sword){ this.sword = sword;}
	public void setShield(Shield shield){ this.shield = shield;}
	public void setDragons(ArrayList<Dragon> dragons){ this.dragons = dragons;}
	public void setDarts(ArrayList<Dart> darts){ this.darts = darts;}

	public Maze getMaze(){ return this.maze;}
	public Hero getHero(){ return this.hero;}
	public Sword getSword(){ return this.sword;}
	public Shield getShield(){ return this.shield;}
	public ArrayList<Dragon> getDragons(){ return this.dragons;}
	public ArrayList<Dart> getDarts(){ return this.darts;}
}
