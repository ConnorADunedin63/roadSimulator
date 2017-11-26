package model;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is the class that defines the basics of a vehicle.
 * @author Connor
 *
 */
public abstract class AbstractVehicle extends JPanel {
	protected double x;
	protected double y;
	protected double speed;
	protected boolean indicateR;
	protected boolean indicateL;
	protected boolean indicatorToggle;
	protected Timer indicatorTimer;
	protected BufferedImage image;
	
	/**
	 * Constructor that sets up the general vehicle object.
	 * @param x, the x position.
	 * @param y, the y position.
	 * @param speed, the speed of the vehicle.
	 * @param img, the imageIcon that represents the vehicle.
	 */
	public AbstractVehicle(double x, double y, double speed, BufferedImage img) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		image = img;
		indicateL = false;
		indicateR = false;
		indicatorToggle = true;
		indicatorTimer = new Timer(300, new IndicatorTimer());
	}
	
	/**
	 * Method that paints the vehicle image on the panel.
	 */
	public abstract void paintComponent(Graphics g);
	
	/**
	 * Method that returns the x position of the vehicle.
	 * @return double, the x position.
	 */
	public double getXPos() {
		return x;
	}
	
	/**
	 * Method that returns the y position of the vehicle.
	 * @return double, the y position.
	 */
	public double getYPos() {
		return y;
	}
	
	/**
	 * Method that returns the speed of the vehicle.
	 * @return double, the speed of the vehicle.
	 */
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * Method that sets the x position to the parameter.
	 * @param x, the new x position.
	 * Requires: x >= 0.
	 */
	public void setX(int x) {
		if(x >= 0) {this.x = x;}
	}
	
	/**
	 * Method that sets the y position to the parameter.
	 * @param y, the new y position.
	 * Requires: y >= 0.
	 */
	public void setY(int y) {
		if(y >= 0) {this.y = y;}
	}
	
	/**
	 * Method that sets the speed to the parameter.
	 * @param s, the new speed.
	 * Requires: speed >= 0.
	 */
	public void setSpeed(int s) {
		if(s >= 0) {this.speed = s;}
	}
	
	/**
	 * Listener that handles the blinking of the indicators when they are on.
	 * @author Connor
	 *
	 */
	private class IndicatorTimer implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			if(indicatorToggle) {
				indicatorToggle = false;
			}
			else {
				indicatorToggle = true;
			}
		}
		
	}
}
