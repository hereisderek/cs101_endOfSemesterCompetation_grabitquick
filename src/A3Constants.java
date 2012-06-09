/*
 * Purpose: defines many constants 
 * which are useful in the game.
 * 
 * Author: Adriana Ferraro
 * Date: S1 2012
 */

import java.awt.*;

public class A3Constants { 
//-------------------------------------------------------
// width, height of the cells and width, height of the 
// JFrame area and gaps
//-------------------------------------------------------
    public static final int CELL_SIZE = 40; 
    public static final int GAP_LEFT_RIGHT = CELL_SIZE;
    public static final int GAP_ABOVE = 100;
    public static final int GAP_BELOW = 100;
    
    public static final int JFRAME_AREA_WIDTH = 600;
    public static final int JFRAME_AREA_HEIGHT = 720;
    
//-------------------------------------------------------
// number of rows and the number of columns of cells
//-------------------------------------------------------
    public static final int NUMBER_OF_ROWS = (JFRAME_AREA_HEIGHT - GAP_ABOVE - GAP_BELOW) / CELL_SIZE;
    public static final int NUMBER_OF_COLS =  (JFRAME_AREA_WIDTH - 2 * GAP_LEFT_RIGHT) / CELL_SIZE;
    
//-------------------------------------------------------
// Defines the game area
//------------------------------------------------------- 
    public static final int GAME_AREA_HEIGHT = NUMBER_OF_ROWS * CELL_SIZE;
    public static final int GAME_AREA_WIDTH = NUMBER_OF_COLS * CELL_SIZE; 
    public static final Rectangle GAME_AREA = new Rectangle(GAP_LEFT_RIGHT, GAP_ABOVE, GAME_AREA_WIDTH, GAME_AREA_HEIGHT);
    
//-------------------------------------------------------
// Defines the racing game area
//-------------------------------------------------------     
//	public static final int GAME_AREA_HEIGHT = NUMBER_OF_ROWS * CELL_SIZE;
//	public static final int GAME_AREA_WIDTH = NUMBER_OF_COLS * CELL_SIZE; 
    public static final Rectangle RACING_INFO_GAME_AREA = new Rectangle(0, 0, 500, 50);
    public static final Rectangle RACING_GAME_AREA = new Rectangle(0, 50, 500, 700);
	
   
    
 //-------------------------------------------------------
 // Defines the centre point of the game area
 //------------------------------------------------------- 
    public static final Point CENTRE_POINT = new Point((GAME_AREA.x + GAME_AREA.width / 2), ((GAME_AREA.y + GAME_AREA.height / 2)));
    
//-------------------------------------------------------
// Defines the area which is marked as already 
// having been visited area in the 
// centre of the game area
//-------------------------------------------------------
    public static final int VISITED_SIZE = CELL_SIZE * 3;
    public static final Rectangle VISITED_AREA = new Rectangle(CENTRE_POINT.x - VISITED_SIZE / 2, CENTRE_POINT.y - VISITED_SIZE / 2, VISITED_SIZE + 1, VISITED_SIZE + 1);
    
//-------------------------------------------------------
// Defines the already launch pad area in the 
// centre of the game area
//-------------------------------------------------------
    public static final int LAUNCH_PAD_SIZE = VISITED_SIZE / 2;
    public static final Rectangle LAUNCH_PAD_AREA = new Rectangle(CENTRE_POINT.x - LAUNCH_PAD_SIZE / 2, CENTRE_POINT.y - LAUNCH_PAD_SIZE / 2, LAUNCH_PAD_SIZE + 1, LAUNCH_PAD_SIZE + 1);
    
//-------------------------------------------------------
// Constants useful for the Hero objects
//-------------------------------------------------------
    public static final Rectangle INITIAL_HERO_AREA = new Rectangle(GAME_AREA.x, GAME_AREA.y, CELL_SIZE, CELL_SIZE);
    
//-------------------------------------------------------
// Constants useful for the Hero objects
//-------------------------------------------------------
    public static final int[] MONEY_AMOUNTS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    
//-------------------------------------------------------
// The four rectangular areas outside the game area
//-------------------------------------------------------      
    public static final Rectangle ABOVE_GAME_AREA = new Rectangle(0, -CELL_SIZE, JFRAME_AREA_WIDTH, GAP_ABOVE + CELL_SIZE);
    public static final Rectangle BELOW_GAME_AREA = new Rectangle(0, GAME_AREA.y + GAME_AREA.height + 1, JFRAME_AREA_WIDTH, GAP_BELOW + CELL_SIZE);
    public static final Rectangle LEFT_OF_GAME_AREA = new Rectangle(-CELL_SIZE, 0, GAME_AREA.x + CELL_SIZE, JFRAME_AREA_HEIGHT);
    public static final Rectangle RIGHT_OF_GAME_AREA = new Rectangle(GAME_AREA.x + GAME_AREA.width + 1, 0, JFRAME_AREA_WIDTH - GAME_AREA.x - GAME_AREA_WIDTH + CELL_SIZE, JFRAME_AREA_HEIGHT);
    
//-------------------------------------------------------
// Constants useful for the Villain objects
//-------------------------------------------------------
    // TOFO: i changed the size here to half 
    public static final int VILLAIN_SIZE = CELL_SIZE / 2; 
    public static final int MAX_NUMBER_OF_VILLAINS = 400;
    public static final Rectangle INITIAL_VILLAIN_AREA = new Rectangle(CENTRE_POINT.x - CELL_SIZE / 4, CENTRE_POINT.y - CELL_SIZE / 4, CELL_SIZE / 2, CELL_SIZE / 2);
    
//-------------------------------------------------------
// Colours which could be used for the Villain objects
//------------------------------------------------------- 
    public static final Color[] COLOURS = {new Color(224,32,32), new Color(145,32,224), new Color(32,32,224), new Color(41,223,225), new Color(27,234,56), new Color(176,228,53), new Color(255,252,29), new Color(255,138,0)};
    public static final Color NICE_GRAY_COLOUR = new Color(157, 161, 158);
    
//-------------------------------------------------------
// Constants to define four directions
//-------------------------------------------------------  
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
//-------------------------------------------------------
// x, y position for the game information
//-------------------------------------------------------
    public static final Point INFORMATION_POSITION1 = new Point(20, 30);
    public static final Point INFORMATION_POSITION2 = new Point(20, 70);
//-------------------------------------------------------
// Constant whioh defines an excellent score
//------------------------------------------------------- 
    public static final int EXCELLENT_SCORE = 25;
    
//-------------------------------------------------------
// Some Font sizes and Fonts
//-------------------------------------------------------
    public static final int SMALL_FONT_SIZE = 16;
    public static final int MEDIUM_FONT_SIZE = 20;
    public static final int LARGE_FONT_SIZE = 30;
    public static final int HUGE_FONT_SIZE = 60;
    
    public static final Font SMALL_FONT = new Font("GENEVA", Font.CENTER_BASELINE, SMALL_FONT_SIZE);
    public static final Font MEDIUM_FONT = new Font("TIMES", Font.CENTER_BASELINE, MEDIUM_FONT_SIZE);;
    public static final Font LARGE_FONT = new Font("ARIAL BLACK", Font.BOLD, LARGE_FONT_SIZE); 
    public static final Font HUGE_FONT = new Font("SansSerif", Font.BOLD, HUGE_FONT_SIZE);
}
