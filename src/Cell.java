/*
 * declared every single cell
 * done.
 */

import java.awt.*;

public class Cell {
    public static final Color CELL_BACKGROUND_COLOUR = new Color(184, 200, 255);
    public static final Color CELL_FOREGROUND_COLOUR = new Color(232, 188, 255);
    public static final int CELL_SIZE = A3Constants.CELL_SIZE;
    
    private Rectangle area = new Rectangle();
    private boolean hasBeenVisited;
    private int moneyAmount;

    public Cell(int left, int top) {
    	//System.out.println("in the constructor of Cell, left = " + left + " top = " + top + " CELL_SIZE = " + CELL_SIZE);
        area.setRect(left, top, CELL_SIZE, CELL_SIZE);
    }
    
    public Rectangle getArea() {
        
        return area; //you may need to change this line of code
    }
    
    public boolean getHasBeenVisited() {
        
        return hasBeenVisited; //you may need to change this line of code
    }
    
    public void setHasBeenVisited(boolean visited) {
        this.hasBeenVisited = visited;
    }
    
    public boolean hasMoney() {
        
        return (moneyAmount > 0 ? true: false ); //you may need to change this line of code
    }
    
    public void setMoney(int amount) {
        this.moneyAmount = amount;
    }
    
    public int getMoney() {
        
        return moneyAmount; //you will need to change this line of code
    }
//-------------------------------------------------------
// Draw the cell
//-------------------------------------------------------
    public void draw(Graphics g) {
        final Font LARGE_FONT = A3Constants.LARGE_FONT;
        Color fillColor = CELL_BACKGROUND_COLOUR;
        if (this.hasBeenVisited){
        	g.setColor(CELL_FOREGROUND_COLOUR);
        } else {
        	g.setColor(CELL_BACKGROUND_COLOUR);
        }
        g.fillRect((int)area.getX(), (int)area.getY(), CELL_SIZE, CELL_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect((int)area.getX(), (int)area.getY(), CELL_SIZE, CELL_SIZE);
        if (this.hasMoney()){
        	g.setColor(Color.YELLOW);
        	g.setFont(LARGE_FONT); 
        	g.drawString(""+moneyAmount,(int) ((int) (0.25 * CELL_SIZE) + area.getX()),(int)(area.getY() + (int) (0.8 * CELL_SIZE)));
        }
        //System.out.println("in Cell.draw() " + (int)area.getX() + (int)area.getY());
    } 
    
}

