package lab4.data;

import java.util.Arrays;
import java.util.Observable;

/**
 * Represents the 2-d game grid
 * 
 * @author André Roaas, Ivan Sedelkin
 */

public class GameGrid extends Observable{
	
	private int[][] board;
	
	// Board pieces
	public static final int EMPTY = 0;
	public static final int ME = 1;
	public static final int OTHER = 2;
	
	public static final int INROW = 5;

	/**
	 * Constructor
	 * 
	 * @param size The width/height of the game grid
	 */
	public GameGrid(int size){
		board = new int[size][size];
	}
	
	/**
	 * Reads a location of the grid
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return the value of the specified location
	 */
	public int getLocation(int x, int y){
		return board[x][y];
	}
	
	/**
	 * Returns the size of the grid
	 * 
	 * @return the grid size
	 */
	public int getSize(){
		return board.length;
	}
	
	/**
	 * Enters a move in the game grid
	 * 
	 * @param x the x position
	 * @param y the y position
	 * @param player
	 * @return true if the insertion worked, false otherwise
	 */
	public boolean move(int x, int y, int player){
		if (board[x][y] != EMPTY) {
			return false;
		}
		board[x][y] = player;
		setChanged();
		notifyObservers();
		return true;
	}
	
	/**
	 * Clears the grid of pieces
	 */
	public void clearGrid(){
		for (int x = 0; x < getSize(); x++) {
			for (int y = 0; y < getSize(); y++) {
				board[x][y] = EMPTY;
			}
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Check if a player has 5 in row
	 * 
	 * @param player the player to check for
	 * @return true if player has INROW elements in row, false otherwise
	 */
	public boolean isWinner(int player){
		for (int x = 0; x < getSize(); x++) {
			for (int y = 0; y < getSize(); y++) {
				if (player != board[x][y]) {
					continue;
				}
				// INROW subsequent values of player to the right
				if (x <= getSize() - INROW && inRow(x, y, 1, 0, player)) {
					return true;
				}
				// INROW subsequent values of player to the bottom
				if (y <= getSize() - INROW && inRow(x, y, 0, 1, player)) {
					return true;
				}
				// INROW subsequent values of player diagonally to the right bottom
				if (y <= getSize() - INROW && x <= getSize() - INROW && inRow(x, y, 1, 1, player)) {
					return true;
				}
				// INROW subsequent values of player diagonally to the left bottom
				if (y < getSize() - INROW && x >= (INROW-1) && inRow(x, y, -1, 1, player)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Searches from a fixated position on the board if it exists INROW elements vertically, horizontally or diagonally.
	 */
	private boolean inRow(int x, int y, int dx, int dy, int player) {
		for (int inrow = 1; inrow < INROW; inrow++) {
			if (player != board[x += dx][y += dy]) {
				return false;
			}
		}
		
		return true;
	}
}