package model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * This is the class that defines the area the player can see.
 * @author Connor
 *
 */
public class ViewAngle extends JPanel {

	public void paintComponent(Graphics g, double x, double y) {
		// The arc will be made up of three sections, two of the sections will be blurry while the centre section will be clear.
		g.setColor(new Color(0,255,0, 150));
		g.fillArc((int) x - 175, (int) y - 40, 350, 100, 120, 30);
		g.fillArc((int) x - 175, (int) y - 40, 350, 100, 180, 30);
		g.setColor(new Color(0,255,0, 50));
		g.fillArc((int) x - 175, (int) y - 40, 350, 100, 150, 30);
	}
	
	public static void main(String[] args) {}

}
