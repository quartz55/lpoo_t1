package maze.Graphics;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import maze.GameObjects.*;
import maze.Input.Input;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


	public GamePanel(Input input, int width, int height) {
		this.addKeyListener(this);
		this.input = input;
		this.WIDTH = width;
		this.HEIGHT = height;
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

		g.setColor(Color.ORANGE);
		for(int i = 0; i < darts.size(); i++)
			g.fillRect(darts.get(i).getX()*gridW, darts.get(i).getY()*gridH, gridW, gridH);

		g.setColor(Color.GREEN);
		g.fillOval(hero.getX()*gridW, hero.getY()*gridH, gridW, gridH);
		if(!sword.isPickedUp()){
			g.setColor(Color.CYAN);
			g.fillRect(sword.getX()*gridW+gridW/4, sword.getY()*gridH, gridW/2, gridH);
		}
		if(!shield.isPickedUp()){
			g.setColor(Color.GRAY);
			g.fillOval(shield.getX()*gridW+gridW/4, shield.getY()*gridH, gridW/2, gridH);
		}

		g.setColor(Color.RED);
		for(int i = 0; i < dragons.size(); i++){
			if(dragons.get(i).isSleeping()) g.setColor(Color.MAGENTA);
			g.fillRect(dragons.get(i).getX()*gridW, dragons.get(i).getY()*gridH, gridW, gridH);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_Q:
			input.addInput("q");
			break;
		case KeyEvent.VK_W:
			input.addInput("w");
			break;
		case KeyEvent.VK_S:
			input.addInput("s");
			break;
		case KeyEvent.VK_A:
			input.addInput("a");
			break;
		case KeyEvent.VK_D:
			input.addInput("d");
			break;
		case KeyEvent.VK_F:
			System.out.println("FIRE");
			input.addInput("f");
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setMaze(Maze maze){ this.maze = maze;}
	public void setHero(Hero hero){ this.hero = hero;}
	public void setSword(Sword sword){ this.sword = sword;}
	public void setShield(Shield shield){ this.shield = shield;}
	public void setDragons(ArrayList<Dragon> dragons){ this.dragons = dragons;}
	public void setDarts(ArrayList<Dart> darts){ this.darts = darts;}
}
