package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The StartPanel class tells the user how to play the game.
 * @author Connor
 *
 */
public class StartPanel extends JPanel {
	private JButton startBtn; 
	private GridBagConstraints gbc; // The constraints for the GridBagLayout.
	private boolean gameStart; // Stores whether or not the game should start.
	private ImageSlideShow slideShow;
	private Timer imageTimer;
	
	/**
	 * Constructor for the StartPanel which sets up the layout and information.
	 */
	public StartPanel() {
		super();
		gameStart = false;
		startBtn = new JButton("Start Game");
		slideShow = new ImageSlideShow();
		imageTimer = new Timer(3000, new TimerListener());
		
		setBackground(Color.GREEN.darker().darker().darker().darker().darker());
		setLayout(new GridBagLayout());
		setSize(1200, 600);
		
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.insets = new Insets(15,125,15,15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 1;
		add(startBtn, gbc);
		startBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				gameStart = true;
			}
		});
		
		repaint();
		revalidate();
		imageTimer.start();
	}
	
	/**
	 * Paints components to the panel.
	 * @param g, the graphics object that is used.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.BOLD, 32));
		g.setColor(Color.GREEN.brighter());
		g.drawString("New Zealand Driving Simulator", 390, 50);
		
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.setColor(Color.WHITE);
		g.drawString("How To Play", 150, 100);
		g.drawString("The objective of this game is to give people learning to drive in", 10, 150);
		g.drawString("New Zealand a safe place to apply the give way rules.", 10, 165);
		g.drawString("This game will teach you some of the New Zealand road code", 10, 195);
		g.drawString("including Speed Limits, Emergency Vehiles, Road Scanning", 10, 210);
		g.drawString("and more.", 10, 225);
		g.drawString("The first few levels will teach you how to control the virtual vehile", 10, 250);
		g.drawString("followed by a varity of situlations you will encounter on the road.", 10, 265);
		
		
		g.drawString("Controls", 900, 100);
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.drawString("Up Arrow = Accelerate", 900, 150);
		g.drawString("Down Arrow = Deccelerate", 900, 180);
		g.drawString("Right Arrow = Turn Right", 900, 210);
		g.drawString("Left Arrow = Turn Left", 900, 240);
		g.drawString("P = Park", 900, 270);
		g.drawString("R = Reverse", 900, 300);
		g.drawString("D = Drive", 900, 330);
		g.drawString("Space = Horn", 900, 360);
		g.drawString("Move Mouse = Look Around", 900, 390);
		g.drawString(", = Signal Left", 900, 410);
		g.drawString(". = Signal Right", 900, 440);
		
		slideShow.getImage().paintIcon(this, g, 450, 100);
	}
	
	/**
	 * Gets whether or not the game should be started.
	 * @return true if the game should start, false if not/
	 */
	public boolean getStart() {
		return gameStart;
	}
	
	/**
	 * Main method calls the constructor for the StartPanel.
	 * @param args, the command line arguments.
	 */
	public static void main(String[] args) {new StartPanel();}

	private class ImageSlideShow {
		
		private ArrayList<ImageIcon> images; // an ArrayList that holds ImageIcons
		private int currentImage;
		
		/**Constructor for the ImageSlideShow class*/
		public ImageSlideShow() {
			
			images = new ArrayList<ImageIcon>();
			currentImage = 0;
			addImageIcons();
		}
		
		/**
		 * Method that adds images to the ArrayList of images.
		 */
		private void addImageIcons() {
			images.add(new ImageIcon("LTR2.png"));
			images.add(new ImageIcon("RTR.png"));
			images.add(new ImageIcon("STR.png"));
			images.add(new ImageIcon("TIR.png"));
		}
		
		/**
		 * Method that returns the current image on the slide show.
		 * @return ImageIcon, the current image.
		 */
		public ImageIcon getImage() {
			return images.get(currentImage);
		}
		
		/**
		 * Method that moves to the next image, if at the end then will loop back to the start.
		 */
		public void nextImage() {
			if(currentImage < 3) {
				currentImage ++;
			}
			// Loop back to the start
			else {
				currentImage = 0;
			}
		}
	} 
	
	/**
	 * Class that handles the timer event.
	 * @author Connor.
	 */
	private class TimerListener implements ActionListener {
		/**
		 * Method that changes the image every three seconds
		 * @param e, the ActionEvent.
		 */
		public void actionPerformed(ActionEvent e) {
			slideShow.nextImage();
			repaint();
		}
	}
}
