package maze.Graphics;

import java.util.ArrayList;

import maze.Graphics.GUI.OptionsDialog;
import maze.Input.Input;
import maze.logic.GameObjects.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiGraphics implements Graphics{

	private JFrame g_window;
	private GamePanel g_panel;
	private Input g_Input;
	private JLabel darts_label;
	

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
		darts_label.setText("Darts: " + hero.getDarts());
	}

	public GuiGraphics(Input input){
		g_window = new JFrame();
		g_window.setTitle("Game Tutorial");
		g_window.setSize(640, 800);
		g_window.setLocationRelativeTo(null);
		g_window.setResizable(false);
		g_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g_window.getContentPane().setLayout(new BorderLayout(0, 0));

		this.g_Input = input;

		g_panel = new GamePanel(input, 640, 640);
		g_window.getContentPane().add(g_panel, BorderLayout.CENTER);

		//JPanel panel = new JPanel();
		//g_window.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel south_panel = new JPanel();
		g_window.getContentPane().add(south_panel, BorderLayout.SOUTH);
		south_panel.setLayout(new BorderLayout(0, 0));

		/* --- info --- */
		JPanel info_panel = new JPanel();
		south_panel.add(info_panel, BorderLayout.NORTH);

		darts_label = new JLabel("Darts: ");
		info_panel.add(darts_label);

		/* --- buttons --- */
		JPanel buttons_panel = new JPanel();
		south_panel.add(buttons_panel, BorderLayout.SOUTH);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(true);
				int response = JOptionPane.showConfirmDialog(null, "Start a new game?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					return;
				} else if (response == JOptionPane.YES_OPTION) {
					g_Input.addInput("new game");
				} else if (response == JOptionPane.CLOSED_OPTION) {
					return;
				}
			}
		});
		buttons_panel.add(btnNewGame);

		JButton btnSaveGame = new JButton("Save Game");
		btnSaveGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveGame();
			}
		});
		buttons_panel.add(btnSaveGame);

		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadGame();
			}
		});
		buttons_panel.add(btnLoadGame);


		JButton btnOptions = new JButton("Options");
		btnOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(true);
				JFrame optionsWindow = new JFrame();
				optionsWindow.setTitle("Options");
				optionsWindow.setSize(500, 500);
				optionsWindow.setResizable(false);
				optionsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				OptionsDialog test = new OptionsDialog(g_panel, optionsWindow);
				test.setVisible(true);
			}
		});
		buttons_panel.add(btnOptions);

		JButton btnClose = new JButton("Quit");
		buttons_panel.add(btnClose);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(true);
				int response = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					return;
				} else if (response == JOptionPane.YES_OPTION) {
					saveGame();
					close();
				} else if (response == JOptionPane.CLOSED_OPTION) {
					return;
				}

			}
		});

		g_window.setVisible(true);
	}

	@Override
	public void gameOver(boolean win) {
		JDialog.setDefaultLookAndFeelDecorated(true);
		String message;
		if (win) message = "You WON!";
		else message = "You lost...";

		int response = JOptionPane.showConfirmDialog(null, message + "\nTry again?", "Game Over",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			close();
			return;
		} else if (response == JOptionPane.YES_OPTION) {
			g_Input.addInput("new game");
		} else if (response == JOptionPane.CLOSED_OPTION) {
			close();
			return;
		}
	}
	
	@Override
	public void saveGame(){
		JDialog.setDefaultLookAndFeelDecorated(true);
		int response = JOptionPane.showConfirmDialog(null, "Save current game?", "Save Game",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			return;
		} else if (response == JOptionPane.YES_OPTION) {
			g_Input.addInput("save game");
		} else if (response == JOptionPane.CLOSED_OPTION) {
			return;
		}
	}

	@Override
	public void loadGame(){
		JDialog.setDefaultLookAndFeelDecorated(true);
		int response = JOptionPane.showConfirmDialog(null, "Load previously saved game?\nCurrent game will be lost.", "Load Game",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			return;
		} else if (response == JOptionPane.YES_OPTION) {
			g_Input.addInput("load game");
		} else if (response == JOptionPane.CLOSED_OPTION) {
			return;
		}
	}

	@Override
	public void close(){
		g_window.setVisible(false);
		g_Input.addInput("close");
	}
}
