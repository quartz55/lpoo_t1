package maze.Graphics.GUI;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSpinner;
import javax.swing.JLabel;

import java.awt.Window;

import javax.swing.SpinnerNumberModel;

import maze.Graphics.GamePanel;
import maze.logic.GameSettings;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class OptionsDialog extends JDialog {

	private JDialog myself;

	private final int WIDTH = 450;
	private final int HEIGHT = 300;

	JSpinner spinnerSize;
	JSpinner spinnerNDragons;
	JSpinner spinnerDarts;
	JComboBox<String> comboBxDifficulty;

	public OptionsDialog(final GamePanel g_panel, Window modalBlocker) {

		super(modalBlocker, ModalityType.APPLICATION_MODAL);    	
		setTitle("Game Options");
		myself = this;
		setBounds(0, 0, WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());


		JPanel settingsPanel = new JPanel();
		settingsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		settingsPanel.setLayout(new GridLayout(0, 2, 0, 30));

		JPanel keysPanel = new JPanel();
		keysPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		keysPanel.setLayout(new GridLayout(0, 3, 0, 20));

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		settingsPanel(settingsPanel);
		keysPanel(keysPanel);
		buttonsPanel(buttonPane);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Game Settings", settingsPanel);
		tabbedPane.addTab("Keys", keysPanel);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}
	
	private void settingsPanel(JPanel panel){

		/* Size of Lab label*/{
			JLabel lblSizeOfLabirinth = new JLabel("Size of Labirinth");
			panel.add(lblSizeOfLabirinth);
		}

		/* Size of Lab spinner */{
			spinnerSize = new JSpinner();
			spinnerSize.setModel(new SpinnerNumberModel(GameSettings.size, 7, 99, 1));
			spinnerSize.setEnabled(true);
			spinnerSize.setEditor(new JSpinner.DefaultEditor(spinnerSize));
			spinnerSize.addChangeListener(new ChangeListener() {
				int prev = (int) spinnerSize.getValue();
				@Override
				public void stateChanged(ChangeEvent e) {
					if((int)spinnerSize.getValue() > prev){
						spinnerSize.setValue(prev+2);
						prev = (int)spinnerSize.getValue();
					}
					else{
						spinnerSize.setValue(prev-2);
						prev = (int)spinnerSize.getValue();
					}
				}
			});

			panel.add(spinnerSize);     
		}

		/* Dragon difficulty label*/{
			JLabel lblDragonDifficulty = new JLabel("Dragon Difficulty");
			panel.add(lblDragonDifficulty);
		}

		/* Dragon difficulty combo*/{
			String[] difficulties = {"Easy", "Medium", "Hard"};
			comboBxDifficulty = new JComboBox<String>(difficulties);
			comboBxDifficulty.setSelectedIndex(GameSettings.difficulty);
			comboBxDifficulty.setEnabled(true);

			panel.add(comboBxDifficulty);     
		}

		/* Number of Dragons label */{
			JLabel lblNumDragons = new JLabel("Number of Dragons");
			panel.add(lblNumDragons);
		}

		/* Number of Dragons spinner */{
			spinnerNDragons = new JSpinner();
			spinnerNDragons.setModel(new SpinnerNumberModel(GameSettings.numDragons, 1, 6, 1));
			spinnerNDragons.setEditor(new JSpinner.DefaultEditor(spinnerNDragons));
			spinnerNDragons.setEnabled(true);
			panel.add(spinnerNDragons);
		}


		/* Number of Darts label*/{
			JLabel label = new JLabel("Number of Darts");
			panel.add(label);
		}

		/* Number of Darts spinner*/{
			spinnerDarts = new JSpinner();
			spinnerDarts.setModel(new SpinnerNumberModel(GameSettings.numDarts, 1,  6, 1));
			spinnerDarts.setEditor(new JSpinner.DefaultEditor(spinnerDarts));
			spinnerDarts.setEnabled(true);
			panel.add(spinnerDarts);
		}

	}
	
	private void keysPanel(JPanel panel){
			JLabel lblUpKey = new JLabel("UP Key:");
			panel.add(lblUpKey);
			JLabel lblCurrentUpKey = new JLabel(""+(char) GameSettings.K_UP);
			panel.add(lblCurrentUpKey);
			JButton btnChangeUpKey = new JButton("CHANGE");
			btnChangeUpKey.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {                                             
					getKeyDialog grabber = new getKeyDialog(myself, "Change UP Key", Dialog.ModalityType.APPLICATION_MODAL);
					grabber.setLocationRelativeTo(null);
					grabber.setVisible(true);
					GameSettings.K_UP = grabber.getKey();
					lblCurrentUpKey.setText(""+(char) GameSettings.K_UP);
				}
			});
			panel.add(btnChangeUpKey);

			JLabel lblLeftKey = new JLabel("LEFT Key:");
			panel.add(lblLeftKey);
			JLabel lblCurrentLeftKey = new JLabel(""+(char) GameSettings.K_LEFT);
			panel.add(lblCurrentLeftKey);
			JButton btnChangeLeftKey = new JButton("CHANGE");
			btnChangeLeftKey.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {                                             
					getKeyDialog grabber = new getKeyDialog(myself, "Change LEFT Key", Dialog.ModalityType.APPLICATION_MODAL);
					grabber.setLocationRelativeTo(null);
					grabber.setVisible(true);
					GameSettings.K_LEFT = grabber.getKey();
					lblCurrentLeftKey.setText(""+(char) GameSettings.K_LEFT);
				}
			});
			panel.add(btnChangeLeftKey);

			JLabel lblDownKey = new JLabel("DOWN Key:");
			panel.add(lblDownKey);
			JLabel lblCurrentDownKey = new JLabel(""+(char) GameSettings.K_DOWN);
			panel.add(lblCurrentDownKey);
			JButton btnChangeDownKey = new JButton("CHANGE");
			btnChangeDownKey.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {                                             
					getKeyDialog grabber = new getKeyDialog(myself, "Change DOWN Key", Dialog.ModalityType.APPLICATION_MODAL);
					grabber.setLocationRelativeTo(null);
					grabber.setVisible(true);
					GameSettings.K_DOWN = grabber.getKey();
					lblCurrentDownKey.setText(""+(char) GameSettings.K_DOWN);
				}
			});
			panel.add(btnChangeDownKey);

			JLabel lblRightKey = new JLabel("RIGHT Key:");
			panel.add(lblRightKey);
			JLabel lblCurrentRightKey = new JLabel(""+(char) GameSettings.K_RIGHT);
			panel.add(lblCurrentRightKey);
			JButton btnChangeRightKey = new JButton("CHANGE");
			btnChangeRightKey.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {                                             
					getKeyDialog grabber = new getKeyDialog(myself, "Change RIGHT Key", Dialog.ModalityType.APPLICATION_MODAL);
					grabber.setLocationRelativeTo(null);
					grabber.setVisible(true);
					GameSettings.K_RIGHT  = grabber.getKey();
					lblCurrentRightKey.setText(""+(char) GameSettings.K_RIGHT);
				}
			});
			panel.add(btnChangeRightKey);

			JLabel lblFireKey = new JLabel("FIRE Key:");
			panel.add(lblFireKey);
			JLabel lblCurrentFireKey = new JLabel(""+(char) GameSettings.K_FIRE);
			panel.add(lblCurrentFireKey);
			JButton btnChangeFireKey = new JButton("CHANGE");
			btnChangeFireKey.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {                                             
					getKeyDialog grabber = new getKeyDialog(myself, "Change FIRE Key", Dialog.ModalityType.APPLICATION_MODAL);
					grabber.setLocationRelativeTo(null);
					grabber.setVisible(true);
					GameSettings.K_FIRE = grabber.getKey();
					lblCurrentFireKey.setText(""+(char) GameSettings.K_FIRE);
				}
			});
			panel.add(btnChangeFireKey);
	}
	
	private void buttonsPanel(JPanel panel){
		/* Apply btn */{
			JButton btnApply = new JButton("Apply");
			btnApply.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {                                             
					GameSettings.size = (int) spinnerSize.getValue();
					GameSettings.numDragons = (int) spinnerNDragons.getValue();
					GameSettings.numDarts = (int) spinnerDarts.getValue();
					GameSettings.difficulty = comboBxDifficulty.getSelectedIndex();
				}
			});
			panel.add(btnApply);
		}

		/* Close btn */{
			JButton btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {                                             
					myself.dispatchEvent(new WindowEvent(myself, WindowEvent.WINDOW_CLOSING));
				}
			});
			panel.add(btnClose);
			getRootPane().setDefaultButton(btnClose);
		}
	}

	private class getKeyDialog extends JDialog {
		private int key;
		public getKeyDialog(Window owner, String title, Dialog.ModalityType modalityType) {
			super(owner,title,modalityType);
			this.getContentPane().setLayout(new FlowLayout());
			this.setSize(250,90);
			JLabel j = new JLabel("Press new key");
			j.setAlignmentX(CENTER_ALIGNMENT);
			this.getContentPane().add(j);
			this.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					getKeyDialog.this.key = arg0.getKeyCode();
					getKeyDialog.this.setVisible(false);
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
				}

			});
			key = 0;
		}
		public int getKey() {
			int oldkey = key;
			key = 0;
			return oldkey;
		}

	}
}
