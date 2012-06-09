/*
 * COMMENT
 */

import java.awt.*;

public class BandOfVillains {
	public static final int MAX_NUMBER_OF_VILLAINS = A3Constants.MAX_NUMBER_OF_VILLAINS;

	private int upTo;
	private Villain[] enemies = new Villain[MAX_NUMBER_OF_VILLAINS];
	private Hero hero;
	private Point mousePoint;
	private int killNums;
	
	public BandOfVillains(Hero hero) {
		upTo = 0;
		killNums = 0;
		this.hero = hero;
		mousePoint = new Point(0, 0);
	}

	public void addAVillain() {
		if (MAX_NUMBER_OF_VILLAINS != upTo) {
			enemies[upTo] = new Villain(hero);
			enemies[upTo].setVisible(true);
			upTo++;
		}
	}
	
	public void setMousePoint(Point mousePoint){
		this.mousePoint = mousePoint;
	}
	public int getNumberOfVillainsBeenShoot() {
		int num = 0;
//		for (Villain villain:enemies){
//			if  (villain.getIsBeenShoot()){
//				num ++;
//			} 
//		}
		for (int i = 0; i < enemies.length; i++){
			if (enemies[i] != null && enemies[i].getIsBeenShoot())
				num ++;
		}
		//System.out.println("----==== the number " + num);
		this.killNums = num;
		return num;
	}
	public int getNumberOfVillainsRemaining() {
		int num = 0;
		for (int i = 0; i < upTo; i++){
			if (null != enemies[i] /*&& enemies[i].getVisible()*/){
				num ++;
			}
		}
		//System.out.println("----==== the number " + num);
		return num;
	}
    public boolean enemyHitsHero(Hero double07){
    	for(int i = 0; i < upTo; i++){
    		if (enemies[i].getVisible() && enemies[i].getArea().intersects(double07.getArea())){
        		return  true;
        	}
    	}//if (enemies[i].getVisible()){
    	return false;
    }

	public void moveVillains(Hero hero) {
		int remained =  getNumberOfVillainsRemaining();
		for (int i = 0; i < remained; i++){
			enemies[i].move(hero);
		}
	}

	public void drawVillains(Graphics g) {
		int remained =  getNumberOfVillainsRemaining();
		for (int i = 0; i < remained; i++){
			enemies[i].draw(g, mousePoint);
		}
		//System.out.println("drawVillains runed times: " + remained);
		mousePoint = new Point (0, 0);
	}
}