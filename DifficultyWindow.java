package mainGame;

/*
 * Difficulty Options
 * 
 * Easy (1)
 * 	Two extra lives
 * 
 * Normal (2)
 * 	Standard game
 * 
 * Hard (3)
 * 	Increased spawn rate
 * 
 * Extreme (4)
 * 	Same as hard mode
 * 	Upgrades Screen is now full of downgrades
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DifficultyWindow extends Frame implements WindowListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	MouseListener parent;
	
	//Styling
	Font f_head = new Font("Arial", Font.BOLD, 13);

	public DifficultyWindow(MouseListener listener) {
		
		parent = listener;
		
		//Components
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
			JButton b_easy = new JButton("Easy");
			JButton b_normal = new JButton("Normal");
			JButton b_hard = new JButton("Hard");
			JButton b_extreme = new JButton("Extreme");
		
		//Positioning
		add(mainPanel);
			mainPanel.add(b_easy);
			mainPanel.add(b_normal);
			mainPanel.add(b_hard);
			mainPanel.add(b_extreme);
		
		//Housekeeping
		setSize(400,100);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setTitle("SELECT A DIFFICULTY");
			b_easy.setFocusable(false);
			b_normal.setFocusable(false);
			b_hard.setFocusable(false);
			b_extreme.setFocusable(false);
		
		//Listeners
		addWindowListener(this);
		
		//Button Bindings
		addButtonAction(b_easy, (evt) -> {listener.wavesNewStart(1);this.dispose();});
		addButtonAction(b_normal, (evt) -> {listener.wavesNewStart(2);this.dispose();});
		addButtonAction(b_hard, (evt) -> {listener.wavesNewStart(3);this.dispose();});
		addButtonAction(b_extreme, (evt) -> {listener.wavesNewStart(4);this.dispose();});
	
		}
	
	public static void addButtonAction(JButton button, ActionListener lambda) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lambda.actionPerformed(e);
			}
		});
	}
	

	//Window Features
		@Override public void windowClosing(WindowEvent evt) {this.dispose();}
		@Override public void windowIconified(WindowEvent evt) {}
		@Override public void windowDeactivated(WindowEvent evt) {}
		@Override public void windowDeiconified(WindowEvent evt) {}
		@Override public void windowActivated(WindowEvent evt) {}
		@Override public void windowClosed(WindowEvent arg0) {}
		@Override public void windowOpened(WindowEvent arg0) {}
}