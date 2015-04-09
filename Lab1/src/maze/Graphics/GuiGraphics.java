package maze.Graphics;

import java.util.ArrayList;

import maze.GameObjects.*;
import maze.Input.Input;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiGraphics implements Graphics{
	
	private JFrame g_window;
	private GamePanel g_panel;

	@Override
	public void draw(Maze maze, Hero hero, Sword sword, Shield shield,
			ArrayList<Dragon> dragons, ArrayList<Dart> darts) {
		g_panel.setMaze(maze);
		g_panel.setHero(hero);
		g_panel.setSword(sword);
		g_panel.setShield(shield);
		g_panel.setDragons(dragons);
		g_panel.setDarts(darts);
		g_panel.draw();
        g_panel.requestFocus();
	}
	
	public GuiGraphics(Input input){
		g_window = new JFrame();
		g_window.setTitle("Game Tutorial");
        g_window.setSize(640, 800);
        g_window.setResizable(false);
        g_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g_window.getContentPane().setLayout(new BorderLayout(0, 0));
        
       // g_panel = new GamePanel(input, 640, 640);
       // g_window.getContentPane().add(g_panel, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        g_window.getContentPane().add(panel, BorderLayout.CENTER);
        
        JPanel panel_1 = new JPanel();
        g_window.getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("Close");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	
        	public void mouseClicked(MouseEvent arg0) {
        		
        		close(true);
        		
        	}
        });
        
        panel_1.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Play Game");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                
                g_panel = new GamePanel(input, 640, 640);
                g_window.getContentPane().add(g_panel, BorderLayout.CENTER);
                
        	}
        });
        
        panel_1.add(btnNewButton_1);
        
        g_window.setVisible(true);
	}

	@Override
	public void close(boolean win) {
		g_window.setVisible(false);
		System.exit(0);
	}

}
