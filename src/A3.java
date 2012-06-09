import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Purpose: application for the
 * game.
 * 
 * Author: Adriana Ferraro
 * Date: S1 2012
 */

public class A3 {
	public static final int JFRAME_AREA_WIDTH = A3Constants.JFRAME_AREA_WIDTH;
	public static final int JFRAME_AREA_HEIGHT = A3Constants.JFRAME_AREA_HEIGHT;

	public static void main(String[] args) {
		// by qzhu496
		
/*		JFrame frame = new JFrame();
	    frame.getContentPane().add(new WelcomeJPanel());

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(200, 200);
	    frame.setVisible(true);*/
		//welcomeFrame.getContentPane().add(new WelcomeJPanel());
		
		A3JFrame gui = new A3JFrame("GrabItQuick ", 3, 3, JFRAME_AREA_WIDTH,JFRAME_AREA_HEIGHT);
	}

	
	
}
