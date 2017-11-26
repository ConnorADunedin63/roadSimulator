package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Player;

/**
 * The DisplayPanel class is used to display the current state of the game and generate events for the controller to handle.
 * @author Connor
 *
 */
public class DisplayPanel extends JPanel implements KeyListener {
	private ImageIcon level;
	private Player player;
	private Timer repaintTimer;
	
	/**
	 * Constructor for the DisplayPanel that sets up panel to display the game.
	 */
	public DisplayPanel() {
		BufferedImage playerImage;
		try {
			playerImage = ImageIO.read(new File("Player.png")); 
		}
		catch(IOException e) {
			System.out.println("The image for the player could not be read");
			throw new Error(e);
		}
		
		level = new ImageIcon("L1.png");
		player = new Player(1000, 500, 0, 0, playerImage);
		repaintTimer = new Timer(5, new RepaintListener());
		setSize(1200, 600);
		addKeyListener(this);
		repaintTimer.start();
	}
	
	public void paintComponent(Graphics g) {
		level.paintIcon(this, g, 0, 0);
		g.setColor(Color.BLACK);
		g.drawString("Gear: " + player.getGear(), 1100, 50);
		if(player.getSpeed() < 0.1) {
			g.drawString("Speed: " + 0 + " Kph", 1100, 65);
		}
		else {
			g.drawString("Speed: " + (player.getSpeed() * 100) + "Kph", 1100, 65);
		}
		//g.fillRect(0, 0, 1200, 600);
		player.paintComponent(g);
	}
	
	/**
	 * Main method calls the constructor for the DisplayPanel class.
	 * @param args, the command line arguments.
	 */
	public static void main(String[] args) {new DisplayPanel();}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_UP) {
			player.accelerate();
		}
		else if(e.getKeyCode() == e.VK_DOWN) {
			player.decelerate();
		}
		else if(e.getKeyCode() == e.VK_RIGHT) {
			player.turnRight();
		}
		else if(e.getKeyCode() == e.VK_LEFT) {
			player.turnLeft();
		}
		else if(e.getKeyCode() == e.VK_D) {
			player.changeGear("D");
		}
		else if(e.getKeyCode() == e.VK_R) {
			player.changeGear("R");
		}
		else if(e.getKeyCode() == e.VK_P) {
			player.changeGear("P");
		}
		else if(e.getKeyCode() == e.VK_PERIOD) {
			player.signalRight();
		}
		else if(e.getKeyCode() == e.VK_COMMA) {
			player.signalLeft();
		}
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}
	
	private class RepaintListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			repaint();
		}
		
	}
}
