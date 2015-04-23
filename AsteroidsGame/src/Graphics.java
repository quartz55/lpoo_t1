import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Graphics extends JPanel{

	private JFrame g_window;
	private JPanel g_panel;	
	
	public void GuiGraphics(){
		g_window = new JFrame();
		g_window.setTitle("Asteroids Game");
		g_window.setSize(640, 480);
		g_window.setLocationRelativeTo(null);
		g_window.setResizable(false);
		g_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g_window.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel south_panel = new JPanel();
		g_window.getContentPane().add(south_panel, BorderLayout.SOUTH);
		south_panel.setLayout(new BorderLayout(0, 0));

		JPanel buttons_panel = new JPanel();
		south_panel.add(buttons_panel, BorderLayout.SOUTH);

		JButton btnNewGame = new JButton("New Game");
		buttons_panel.add(btnNewGame);
		
		JButton btnLoadGame = new JButton("Load Game");
		buttons_panel.add(btnLoadGame);
		
		JButton btnSaveGame = new JButton("Save Game");
		buttons_panel.add(btnSaveGame);
		
		JButton btnOptions = new JButton("Options");
		buttons_panel.add(btnOptions);
		
		JButton btnQuitGame = new JButton("Quit Game");
		buttons_panel.add(btnQuitGame);
		btnQuitGame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(true);
				int response = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					return;
				} else if (response == JOptionPane.YES_OPTION) {
					QuitGame();
				} else if (response == JOptionPane.CLOSED_OPTION) {
					return;
				}

			}
		});
		
		g_panel = new GamePanel(640, 640);
		g_window.getContentPane().add(g_panel, BorderLayout.CENTER);
		
		g_window.setVisible(true);
	}
	
	public void QuitGame(){
		g_window.setVisible(false);
	}


}
