package view;

import java.awt.Color;	
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * GameFrame will contain two main JPanels, one will be the start screen that informs the user how to play,
 * and the other will be the actual game panel that displays the actual game.
 * @author Connor
 *
 */
public class GameFrame extends JFrame{
	private StartPanel startPanel;
	private DisplayPanel displayPanel;
	private Thread waitTimer;
	
	/**
	 * Constructor that sets up a new GameFrame.
	 */
	public GameFrame() {
		super("The New Zealand Driving Simulator");
		startPanel = new StartPanel();
		displayPanel = new DisplayPanel();
		waitTimer = new Thread();
		
		setSize(1200, 600);
		setResizable(false);
		createMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
		add(startPanel);
		waitForStart();
		remove(startPanel);
		add(displayPanel);
		displayPanel.requestFocusInWindow();
		repaint();
		revalidate();
	}
	
	/**
	 * Method that waits for the start button to be press to start the game.
	 */
	private void waitForStart() {
		while(!startPanel.getStart()) {
			try {
				waitTimer.sleep(10); // Sleeps for 10 milliseconds to let the system update.
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method that creates a menu bar for the JFrame.
	 */
	private void createMenu() {
		MenuBar menuBar = new MenuBar();
		
		Menu file = new Menu("File");
		MenuItem exit = new MenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				GameFrame.this.dispose(); // Distorys the JFrame and quits the game.
			}
		});
		
		file.add(exit);
		menuBar.add(file);
		
		Menu help = new Menu("Help");
		MenuItem howToPlay = new MenuItem("How to play");
		// TODO add actions to howToPlay menu item.
		help.add(howToPlay);
		menuBar.add(help);
		
		this.setMenuBar(menuBar);
	}
	
	/**
	 * Main method that calls the GameFrame constructor.
	 * @param args, command line arguments. 
	 */
	public static void main(String[] args) {new GameFrame();}

}
