/*
 * Purpose: defines the block
 * of cells in the game area.
 * 
 * Author: Adriana Ferraro
 * Date: S1 2012
 */

import java.awt.*;

public class BlockOfCells {  
    public static final int NUMBER_OF_ROWS = A3Constants.NUMBER_OF_ROWS;
    public static final int NUMBER_OF_COLS = A3Constants.NUMBER_OF_COLS;
    public static final int CELL_SIZE = A3Constants.CELL_SIZE;
    public static final Rectangle GAME_AREA = A3Constants.GAME_AREA;
    public static final Rectangle VISITED_AREA = A3Constants.VISITED_AREA; 
    public static final int[] MONEY_AMOUNTS = A3Constants.MONEY_AMOUNTS;
    
    private Cell[] cellBlock;
    
    public BlockOfCells() {   
        int numberInBlock = NUMBER_OF_ROWS * NUMBER_OF_COLS; 
        cellBlock = new Cell[numberInBlock];
        int x = GAME_AREA.x;
        int y = GAME_AREA.y;
        
        
        for (int i = 0; i < numberInBlock; i++) {
            cellBlock[i] = new Cell(x, y);
            Point centreOfCell = new Point(x + CELL_SIZE / 2, y + CELL_SIZE / 2);
            
            if (VISITED_AREA.contains(centreOfCell)) {
                cellBlock[i].setHasBeenVisited(true);
            }
            
            x = x + CELL_SIZE;
            
            if (i != 0 && (i + 1) % NUMBER_OF_COLS == 0) {
                y = y + CELL_SIZE;
                x = GAME_AREA.x;
            }
        }
        cellBlock[0].setHasBeenVisited(true);
        assignMoneyToRandomCells(cellBlock);
    }
    private void assignMoneyToRandomCells(Cell[] cells) {
        Cell randomCell;
        int randomIndex;
        int moneyToAssign = MONEY_AMOUNTS.length;
        
        while (moneyToAssign > 0) {
            randomIndex = (int) (Math.random() * cells.length);
            randomCell = cells[randomIndex];
            if (!randomCell.getHasBeenVisited() && !randomCell.hasMoney()) {
                moneyToAssign--;
                randomCell.setMoney(MONEY_AMOUNTS[moneyToAssign]);
            }   
        }
    } 
//-------------------------------------------------------
// Get the cell array
// Get the number of cells which have not yet been
// visited
//-------------------------------------------------------  
    public Cell[] getCells() {
        return cellBlock;
    }
    
    public int getNumberNotVisited() {
        int count = 0;
        
        for(int i = 0; i < cellBlock.length; i++) {
            if (!cellBlock[i].getHasBeenVisited()) {
                count++;
            }
        }
        
        return count;
    }
//-------------------------------------------------------
// Returns the cell at the required position
//-------------------------------------------------------    
    public Cell getSingleCell(int rowNumber, int colNumber) {
        int position = rowNumber * NUMBER_OF_ROWS + colNumber;
        
        if (position < cellBlock.length) {
            return cellBlock[position];
        }
        
        return null;
    }
    // change the position of a cell contains money randomly
    public void changeMoneyPosition(){
/*    	for(Cell aRandomCell: cellBlock){
    		if (Math.random() < 0.01 && aRandomCell.hasMoney()){
    			// move the money of this cell to another cell which doesn't contains any money
    			int randomIndex = -1;
    			do {
    				randomIndex = (int) (Math.random() * cellBlock.length);
    			} while (!cellBlock[randomIndex].hasMoney());
    			cellBlock[randomIndex].setMoney(aRandomCell.getMoney());
    			aRandomCell.setMoney(0);
    		}*/
    	if (Math.random() > 0.3){
    		return;
    	}
    	for(int i = 0; i < cellBlock.length; i++) {
			if (Math.random() < 0.01 && cellBlock[i].hasMoney()) {
				// move the money of this cell to another cell which doesn't
				// contains any money
				int randomIndex = -1;
				do {
					randomIndex = (int) (Math.random() * cellBlock.length);
					System.out.println(cellBlock[randomIndex].hasMoney());
				} while ((cellBlock[randomIndex].hasMoney()));//false);//cellBlock[randomIndex].hasMoney());
				cellBlock[randomIndex].setMoney(cellBlock[i].getMoney());
				cellBlock[i].setMoney(0);
			}
    	}
    }
    
//-------------------------------------------------------
// If the heroCentrePt is inside one of the cells the method
// returns the money which is in the cell, otherwise
// the method returns 0
//-------------------------------------------------------    
    public int check007PositionAndGetMoney(Point double07CentrePt) {
        Rectangle area;
        int money;  
        for(int i = 0; i < cellBlock.length; i++) {
            area = cellBlock[i].getArea();
            
            if (area.contains(double07CentrePt)) {
                cellBlock[i].setHasBeenVisited(true);
                money = cellBlock[i].getMoney();
                cellBlock[i].setMoney(0);
                return money;
            }
        }
        
        return 0;  
    }
//-------------------------------------------------------
// Draw the cells
//-------------------------------------------------------
    public void drawCells(Graphics g) {   
    	//System.out.println("inside of BlockOfCells.drawCells()");
        for(int i = 0; i < cellBlock.length; i++) {
            cellBlock[i].draw(g);
        }
    }
}