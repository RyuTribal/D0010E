package lab4.data;

import java.util.Observable;

public class GomokuGrid extends Observable {
    public static final int EMPTY = 0;
    public static final int ME = 1;
    public static final int OTHER = 2;
    public static final int INROW = 5;
	
	public int[][] grid;
	
	GomokuGrid(int size){
		this.grid = new int[size][size];
	}
	
	public int getLocation(int x, int y) {
		return grid[x][y];
	}
	
	public int getSize() {
		return grid.length;
	}
	
	public boolean move(int x, int y, int player) {
		if(grid[x][y] == EMPTY) {
			grid[x][y] = player;
			return true;
		}
		return false;
	}
	
	public void clearGrid() {
		this.grid = new int[getSize()][getSize()];
	}
	public boolean isWinner(int player){
        return horizontalWin(player) || verticalWin(player) || diagonalDownWin(player) || diagonalUpWin(player);
    }

    
    private boolean verticalWin(int player){

    	for(int x = 0; x < getSize(); x++){
        	int inARowCount = 0;
            for(int y = 0; y < getSize(); y++){
                if(grid[x][y] == player){
                    inARowCount += 1;
                }
                else {
                    inARowCount = 0;
                }
                if(inARowCount == INROW){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean horizontalWin(int player){
       
        for(int y = 0; y < getSize(); y++){
        	int inARowCount = 0;
            for(int x = 0; x < getSize(); x++){
                if(grid[x][y] == player){
                    inARowCount += 1;
                }
                else {
                    inARowCount = 0;
                }
                if(inARowCount == INROW){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean diagonalDownWin(int player){
        
        for(int x = 0; x < getSize(); x++){
        	int inARowCount = 0;
            for(int y = 0; y < getSize(); y++){
                for(int n = 0; n < getSize(); n ++){
                    try{
                        if(grid[x + n][y + n] == player){
                            inARowCount += 1;
                        }
                        else {
                            inARowCount = 0;
                        }
                        if(inARowCount == INROW){
                            return true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e){
                        inARowCount = 0;
                        break;
                    }

                }
            }
        }

        return false;
    }
    
    private boolean diagonalUpWin(int player){

        for(int x = 0; x < getSize(); x++){
        	int inARowCount = 0;
            for(int y = 0; y < getSize(); y++){
                for(int n = 0; n < getSize(); n ++){
                    try{
                        if(grid[x - n][y + n] == player){
                            inARowCount += 1;
                        }
                        else {
                            inARowCount = 0;
                        }
                        if(inARowCount == INROW){
                            return true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e){
                        inARowCount = 0;
                        break;
                    }
                }
            }
        }
        return false;
    }
	
}
