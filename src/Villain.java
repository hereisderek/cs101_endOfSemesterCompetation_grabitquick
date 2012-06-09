/*
 * COMMENT
 */

import java.awt.*;

public class Villain {
    public static final Point CENTRE_POINT = A3Constants.CENTRE_POINT;
    public static final Color[] COLOURS = A3Constants.COLOURS;
    public static final int CELL_SIZE = A3Constants.CELL_SIZE;
    
    public static final int VILLAIN_SIZE = A3Constants.VILLAIN_SIZE; 
    public static final Rectangle INITIAL_VILLAIN_AREA = A3Constants.INITIAL_VILLAIN_AREA;
    
    public static final Rectangle GAME_AREA = A3Constants.GAME_AREA; 
    public static final Rectangle ABOVE_GAME_AREA = A3Constants.ABOVE_GAME_AREA;
    public static final Rectangle BELOW_GAME_AREA = A3Constants.BELOW_GAME_AREA;
    public static final Rectangle LEFT_OF_GAME_AREA = A3Constants.LEFT_OF_GAME_AREA;
    public static final Rectangle RIGHT_OF_GAME_AREA = A3Constants.RIGHT_OF_GAME_AREA;
    
    public static final int SUITABLE_DIATENCE = 3 * CELL_SIZE;
    
    private int xSpeed;
    private int ySpeed;
    private Rectangle area;
	private Hero hero;
    private Color colour; //optional
    private boolean isVisible;
    private boolean isBeenShoot;
    private Point mousePoint;
    
    private int getARandom(){
    	int t = (int) (Math.random()*21 - 10);
    	while (t == 0){
    		t = (int) (Math.random()*21 - 10);
    	}
    	return t;
    }
    private Rectangle getTheInitialPosition(Hero hero){
    	Point p1 = new Point((int) (Math.random() * INITIAL_VILLAIN_AREA.getX()), 
				(int) (Math.random() * INITIAL_VILLAIN_AREA.getY()));
    	while (getTheDistence(p1, new Point((int) hero.getArea().getX(), (int) hero.getArea().getY())) < SUITABLE_DIATENCE){
    		p1 = new Point((int) (Math.random() * INITIAL_VILLAIN_AREA.getX()), 
    				(int) (Math.random() * INITIAL_VILLAIN_AREA.getY()));
    	}
    	
    	return new Rectangle((int) (p1.x ), 
				(int) (p1.y), 
				(int) INITIAL_VILLAIN_AREA.getWidth(), 
				(int) INITIAL_VILLAIN_AREA.getHeight());
    }
    private int getTheDistence(Point p1, Point p2){
    	double distence;
    	distence = Math.sqrt(Math.pow(Math.abs(p1.x - p2.x), 2) + Math.pow(Math.abs(p1.y - p2.y), 2));
    	return (int) distence;
    }
    
    public Villain(Hero hero) {
    	this.hero = hero;
    	//System.err.println("" + hero.getArea().getX());
		this.xSpeed = getARandom();
		this.ySpeed = getARandom();
		this.area = getTheInitialPosition(hero);
		this.colour = getARandomColor();//Color.red;//getARandomColor();
		this.isVisible = false;
		this.isBeenShoot = false;
		mousePoint = new Point(0,0);
	}
	private Color getARandomColor(){
		int i = (int)(Math.random() * COLOURS.length);
		return COLOURS[i];
	}
	public Rectangle getArea() {
		return area;
	}
	public void setVisible(boolean isVisible) {
		//System.out.println("in the Villanin.isVisible: " + isVisible);
		this.isVisible = isVisible;
	}
	public boolean getVisible() {
		return isVisible;
	}
	public boolean getIsBeenShoot(){
		return isBeenShoot;
	}
    public void move(Hero hero){
    	
    	if (this.isVisible){
    		Point p1, p2;
    		p1 = hero.getArea().getLocation();
    		p2 = area.getLocation();
    		int x = (int) ((int)area.getX() + xSpeed + 0.5 * ((p1.x - p2.x) / CELL_SIZE));
    		int y = (int) ((int) area.getY() + ySpeed + 0.5  * ((p1.y - p2.y)) / CELL_SIZE);
    		
    		area.setRect(x, y, VILLAIN_SIZE, VILLAIN_SIZE);
    		}
    }
    public void draw(Graphics g, Point mousePoint){
    	// unvisible if intersects the border
    	if (area.intersects(ABOVE_GAME_AREA) || 
				area.intersects(BELOW_GAME_AREA) || 
				area.intersects(LEFT_OF_GAME_AREA) || 
				area.intersects(RIGHT_OF_GAME_AREA)){
/*			for(int i = 128, t=15; i > 0; i-=(128/10), t--){
				size = (int)(VILLAIN_SIZE * ((double)t/15));
				g.setColor(new Color(i,0,0));
				g.fillOval(x - size/2 , y - size/2, size, size);
				//System.out.println("in my Villain.draw(), the t = " + t);
			}*/
			this.isVisible = false;
		}
    	int size = 0;
    	if (this.isVisible){
    		if (area.contains(mousePoint)) {
    			//System.out.println("shoot: " + mousePoint.toString() + area.toString());
    			this.isBeenShoot = true;
    			this.isVisible = false;
    		}
    		// TODO: need to change the shap of it

    		g.setColor(Color.red);
    		g.fillOval((int)area.getX() , (int)area.getY(), VILLAIN_SIZE, VILLAIN_SIZE);
    		g.setColor(this.colour);
    		int width = 4;
    		g.fillOval((int)area.getX() + width/2 , (int)area.getY() + width/2, VILLAIN_SIZE - width, VILLAIN_SIZE - width);
//    		for (int i = 0; i < 5; i++){
//    			g.setColor(Color.red);
//    			g.drawOval((int)area.getX() , (int)area.getY(), VILLAIN_SIZE, VILLAIN_SIZE);
//    		}
    	}
    	
    	//
    }
}


