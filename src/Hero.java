/*
 * COMMENT
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

public class Hero implements ActionListener {
	public static final Color[] COLOURS = A3Constants.COLOURS;
	public static final int CELL_SIZE = A3Constants.CELL_SIZE;

	public static final int UP = A3Constants.UP;
	public static final int DOWN = A3Constants.DOWN;
	public static final int LEFT = A3Constants.LEFT;
	public static final int RIGHT = A3Constants.RIGHT;
	
	public static final Rectangle INITIAL_HERO_AREA = A3Constants.INITIAL_HERO_AREA;

	public static final Rectangle GAME_AREA = A3Constants.GAME_AREA;
	public static final Rectangle ABOVE_GAME_AREA = A3Constants.ABOVE_GAME_AREA;
	public static final Rectangle BELOW_GAME_AREA = A3Constants.BELOW_GAME_AREA;
	public static final Rectangle LEFT_OF_GAME_AREA = A3Constants.LEFT_OF_GAME_AREA;
	public static final Rectangle RIGHT_OF_GAME_AREA = A3Constants.RIGHT_OF_GAME_AREA;
	
	public static Color[] MYCOLOR;
	private int moveX;
	private int moveY;
	private Rectangle area;
	private int currentDirection;
	private int moneyCollected;
	// to change hero's color
	private int colorCounter;
	private Timer timer;

	public Hero() {
		colorCounter = 0;
		timer = new Timer(10, this);
		timer.start();
		area = new Rectangle(INITIAL_HERO_AREA);
		moveX = (int) area.getX();
		moveY = (int) area.getY();
		// moveX = (int) GAME_AREA.getX();
		// moveY = (int) GAME_AREA.getY();
		currentDirection = RIGHT;
		int colorCounter = 0;
		MYCOLOR = new Color[729];
		for (int i = 0; i <= 255; i += 30){
			for (int j = 0; j <= 255; j += 30){
				for (int k = 0; k <= 255; k += 30){
					MYCOLOR[colorCounter] = new Color(i,j,k);
					colorCounter ++;
				}
			}
		}
		System.out.println("colorCounter: " + colorCounter + " MYCOLOR.length: " + MYCOLOR.length);
	}

	public void actionPerformed(ActionEvent arg0) {
		colorCounter++;
	}

	public Rectangle getArea() {
		return area;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}

	public int getMoneyCollected() {
		return moneyCollected;
	}

	public void move() {
		// TODO: object should never move out of the area
		switch (currentDirection) {
		case UP:

			moveY -= CELL_SIZE;
			/*
			 * if (new Rectangle(moveX, moveY, CELL_SIZE,
			 * CELL_SIZE).intersects(ABOVE_GAME_AREA)){
			 * System.err.println("St.Hero out of border"); moveY += CELL_SIZE;
			 * // break; }
			 */
			break;
		case DOWN:
			moveY += CELL_SIZE;
			break;
		case LEFT:
			moveX -= CELL_SIZE;
			break;
		case RIGHT:
			moveX += CELL_SIZE;
			break;
		}
		area.setRect(moveX, moveY, CELL_SIZE, CELL_SIZE);
		// TODO:
		if (!area.intersects(GAME_AREA)) {
			// if (area.intersects(ABOVE_GAME_AREA) ||
			// area.intersects(BELOW_GAME_AREA) ||
			// area.intersects(LEFT_OF_GAME_AREA) ||
			// area.intersects(LEFT_OF_GAME_AREA)){
			// System.err.println("St.Hero out of border");
			switch (currentDirection) {
			case UP:
				moveY += CELL_SIZE;
				break;
			case DOWN:
				moveY -= CELL_SIZE;
				break;
			case LEFT:
				moveX += CELL_SIZE;
				break;
			case RIGHT:
				moveX -= CELL_SIZE;
				break;
			}
		}

	}

	public void draw(Graphics g) {
		// System.out.println("inside of Hero.draw()");
		// HeroShap heroShap = new HeroShap(g ,moveX, moveY);
		
		this.drawHero(g, moveX, moveY);
/*		int x = moveX;
		int y = moveY;
		x += (int) (CELL_SIZE * 0.5);
		y += (int) (CELL_SIZE * 0.5);
		int size = CELL_SIZE;
		colorCounter += 30;

		for (int i = 0, t = 255; i < 20; i++, t += (255 / 20)) {
			g.setColor(new Color((t + colorCounter) % 255, 0, 255));
			size *= 19 / 20;
		}
		for (int i = 128, t = 20; i > 0; i -= (128 / 10), t--) {
			size = (int) (CELL_SIZE * ((double) t / 20));
			g.setColor(new Color(0, i, 255));
			g.fillOval(x - size / 2, y - size / 2, size, size);
		}*/

	}
	public void drawHero(Graphics g, int x, int y){
		x += (int) (CELL_SIZE * 0.5);
		y += (int) (CELL_SIZE * 0.5);
		int size = CELL_SIZE;
		//colorCounter += 30;
		
		
//		for (int i = 0, t = 255; i < 20; i++, t += (255 / 20)) {
//			g.setColor(new Color((t + colorCounter) % 255, 0, 255));
//			size *= 19 / 20;
//		}
		int drawTimes = 50;
		
		for (int i = 0; i < drawTimes; i++){
			size = (int) (CELL_SIZE - CELL_SIZE * (i/(double)drawTimes));
			g.setColor(MYCOLOR[(colorCounter+i)%MYCOLOR.length]);
			//System.out.println((colorCounter++)%MYCOLOR.length);
			g.fillOval(x - size / 2, y - size / 2, size, size);
		}
//		for (int i = 128, t = 20; i > 0; i -= (128 / 20), t--) {
//			size = (int) (CELL_SIZE * ((double) t / 128));
//			g.setColor(MYCOLOR[(colorCounter++)%MYCOLOR.length]);
//			//System.out.println((colorCounter++)%MYCOLOR.length);
//			g.fillOval(x - size / 2, y - size / 2, size, size);
//		}
	}
	// all i want is just a colorful hero....
	/*
	 * private class HeroShap{ public HeroShap(Graphics g, int x, int y){ // x,
	 * y is the centre of the cell of hero x += (int)(CELL_SIZE * 0.5); y +=
	 * (int)(CELL_SIZE * 0.5); int size = CELL_SIZE; colorCounter += 30;
	 * 
	 * for (int i = 0, t = 255; i < 20; i ++, t += (255/20)){ g.setColor(new
	 * Color((t + colorCounter) % 255,0,255)); size *= 19/20; }
	 * 
	 * for(int i = (255 + colorCounter ) % 255, t=15; i > 0; i-=(255/10), t--){
	 * size = (int)(CELL_SIZE * ((double)t/15)); g.setColor(new Color(i,0,255));
	 * g.fillOval(x - size/2 , y - size/2, size, size);
	 * //System.out.println("in my heroShap, the t = " + t); } for(int i = 128,
	 * t=20; i > 0; i-=(128/10), t--){ size = (int)(CELL_SIZE * ((double)t/20));
	 * g.setColor(new Color(0,i,255)); g.fillOval(x - size/2 , y - size/2, size,
	 * size); //System.out.println("in my heroShap, the t = " + t); }
	 * 
	 * 
	 * // DONE: TODO: still need another for } }
	 */
}
