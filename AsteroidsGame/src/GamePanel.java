

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel
implements KeyListener{

	private BufferedImage background_img;
	
	public GamePanel(int width, int height) {
		this.addKeyListener(this);
		
		try{
			 background_img =  ImageIO.read(new File("./images/asteroids1.jpg"));
			
		} catch(IOException e){e.printStackTrace();}
	}

	public void draw(){ repaint();}

	public void paintComponent(java.awt.Graphics g){
		
		g.drawImage(background_img, 0, 0,640,480, null);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
