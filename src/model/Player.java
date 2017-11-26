package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is the class that represents the player.
 * @author Connor
 *
 */
public class Player extends AbstractVehicle implements ActionListener {
	private String gear;
	private Timer moveTimer; 
	private int angle;
	private ViewAngle view;
	
	/**
	 * The Player constructor sets up the Player object.
	 * @param x, the starting x position.
	 * @param y, the starting y position.
	 * @param spd, the starting speed.
	 * @param ang, the starting angle of rotation.
	 * @param playerImg, the player's image.
	 */
	public Player(int x, int y, int spd, int ang, BufferedImage playerImg) {
		super(x, y, spd, playerImg);
		gear = "P";
		moveTimer = new Timer(10, this);
		angle = ang;
		view = new ViewAngle();
		moveTimer.start();
	}
	
	/**
	 * Method that increases the player's speed by a set amount.
	 */
	public void accelerate() {
		if(!gear.equals("P")) {
			speed += 0.1;
		}
	}
	
	/**
	 * Method that decreases the player's speed by a set amount.
	 */
	public void decelerate() {
		if(!gear.equals("P") && speed > 0) {
			speed -= 0.1;
		}
	}
	
	/**
	 * Method that rotates the player by one degree anti-clockwise.
	 */
	public void turnLeft() {
		if(speed > 0) {
			if(angle == 0) {
				angle = 359;
			}
			else {
				angle --;
			}
		}
	}
	
	/**
	 * Method that rotates the player by one degree clockwise.
	 */
	public void turnRight() {
		if(speed > 0) {
			if (angle == 359) {
				angle = 0;
			}
			else {
				angle ++;
			}
		}
	}
	
	/**
	 * Method that plays the horn sound effect.
	 */
	public void useHorn() {
		//TODO add horn sound
	}
	
	/**
	 * Method that turns the left signal off or on.
	 */
	public void signalLeft() {
		if(indicateR) {
			indicateR = false;
			indicateL = true;
			indicatorTimer.restart(); // Restart the timer. 
		}
		else if(indicateL) {
			indicateL = false;
			indicatorTimer.stop();
		}
		else {
			indicateL = true;
			indicatorTimer.start();
		}
	}
	
	/**
	 * Method that turns the right signal off or on.
	 */
	public void signalRight() {
		if(indicateL) {
			indicateL = false;
			indicateR = true;
			indicatorTimer.restart(); // Restart the timer.
		}
		else if(indicateR) {
			indicateR = false;
			indicatorTimer.stop();
		}
		else {
			indicateR = true;
			indicatorTimer.start();
		}
	}
	
	/**
	 * Method that changes the gear to the parameter.
	 * Note that an error will occur if the parameter does not match any gear.
	 * @param g, the new gear.
	 */
	public void changeGear(String g) {
		if(g.equals("D") || g.equals("R")) {
			gear = g;
		}
		else if (g.equals("P")){
			setSpeed(0); // Cannot move in park.
		}
		else {
			System.out.println("Gear not recongized");
		}
	}
	
	/**
	 * Method that applies the player's speed to their co-ordinates.
	 */
	private void move() {
		if(gear.equals("D") && speed > 0) {
			moveForward();
		}
		else if(gear.equals("R") && speed > 0) {
			moveBackward();
		}
	}
	
	/**
	 * Method that moves the player forward.
	 */
	private void moveForward() {
		double percentage = determinePercentage();
		double minorSpeed = determineMinorSpeed();
		
		if(angle == 0) {
			x -= speed;
		}
		else if(angle > 0 && angle < 90) {
			x -= speed - minorSpeed;
			y -= minorSpeed;
		}
		else if(angle == 90) {
			y -= speed;
		}
		else if(angle > 90 && angle < 180) {
			y -= speed - minorSpeed;
			x += minorSpeed;
		}
		else if(angle == 180) {
			x += speed;
		}
		else if(angle > 180 && angle < 270) {
			x += speed - minorSpeed;
			y += minorSpeed;
		}
		else if(angle == 270) {
			y += speed;
		}
		else if(angle > 270 && angle < 360) {
			y += speed - minorSpeed;
			x -= minorSpeed;
		}
	}
	
	/**
	 * Method that moves the player backwards.
	 */
	private void moveBackward() {
		double percentage = determinePercentage();
		double minorSpeed = determineMinorSpeed();
		
		if(angle == 0) {
			x += speed;
		}
		else if(angle > 0 && angle < 90) {
			x += speed - minorSpeed;
			y += minorSpeed;
		}
		else if(angle == 90) {
			y += speed;
		}
		else if(angle > 90 && angle < 180) {
			y += speed - minorSpeed;
			x -= minorSpeed;
		}
		else if(angle == 180) {
			x -= speed;
		}
		else if(angle > 180 && angle < 270) {
			x -= speed - minorSpeed;
			y -= minorSpeed;
		}
		else if(angle == 270) {
			y -= speed;
		}
		else if(angle > 270 && angle < 360) {
			y -= speed - minorSpeed;
			x += minorSpeed;
		}
	}
	
	/**
	 * Method that paints the player to the panel.
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(degreesToRadians(), x + 60, y + 15);
		g2.drawImage(image, null, (int) x,(int) y);
		
		
		if(indicatorToggle) {
			g.setColor(Color.ORANGE.darker());
			
			if(indicateR) {
				g.fillRect((int) x,(int) y, 8, 8); // draw right indicator.
			}
			else if(indicateL){
				g.fillRect((int) x,(int) y + 22, 8, 8); // draw left indicator.
			}
		}
		if(gear.equals("R")) {
			g.setColor(Color.WHITE);
			g.fillRect((int) x + 52,(int) y, 8, 8); // draw right indicator.
			g.fillRect((int) x + 52,(int) y + 22, 8, 8); // draw left indicator.
		}
		
		//view.paintComponent(g, x, y);
	}

	/**
	 * Method that returns the percentage of the speed that should be applied to the minor axis.
	 * @return double, the percentage of the speed that should be applied to the minor axis.
	 */
	private double determinePercentage() {
		double tempAngle = 0;
		
		if(angle > 270 && angle < 360) {
			tempAngle = angle - 270;
		}
		else if(angle > 180 && angle < 270) {
			tempAngle = angle - 180;
		}
		else if(angle > 90 && angle < 180) {
			tempAngle = angle - 90;
		}
		else {
			tempAngle = angle;
		}
	
		return (90.0 / 100.0) * tempAngle;
	}
	
	/**
	 * Method that returns the minor speed that should be applied to the minor axis.
	 * @return double, the minor speed.
	 */
	private double determineMinorSpeed() {
		double percentage = determinePercentage();
		
		return (speed / 100.0) * percentage;
	}
	
	private double degreesToRadians() {
		return (angle*Math.PI)/180;
	}
	
	public void actionPerformed(ActionEvent e) {
		move();		
	}
	
	/**
	 * Method that returns the current gear.
	 * @return String, the current gear.
	 */
	public String getGear() {
		return gear;
	}
}
