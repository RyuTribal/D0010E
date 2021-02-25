package lab4.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lab4.data.GameGrid;

/**
 * A panel providing a graphical view of the game board
 * 
 * @author André Roaas, Ivan Sedelkin
 */

public class GamePanel extends JPanel implements Observer{

	private final int UNIT_SIZE = 20;
	private GameGrid grid;
	
	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid){
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
	}
	
	/**
	 * Returns a grid position given pixel coordinates
	 * of the panel
	 * 
	 * @param x the x coordinates
	 * @param y the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y){
		return new int[]{x / UNIT_SIZE, y / UNIT_SIZE};
	}
	
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// Paints the background of the board a light gray color
		g.setColor(Color.lightGray);
        g.fillRect(0, 0, grid.getSize() * UNIT_SIZE, grid.getSize() * UNIT_SIZE);
        
        // Paints the board
        g.setColor(Color.black);
        for(int y = 0; y < grid.getSize(); y++) {
        	for(int x = 0; x < grid.getSize(); x++) {
        		g.drawRect(x*UNIT_SIZE, y*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        	}
        }
        
        // Iterates through the grid, and paints black or white to indicate all moves made
        for(int y = 0; y < grid.getSize(); y++) {
    	    for(int x = 0; x < grid.getSize(); x++) {
    		    if(grid.getLocation(x, y) == grid.ME) {
    			    g.setColor(Color.black);
    			    g.fillOval(x * UNIT_SIZE, y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
    		    }
    		    if(grid.getLocation(x, y) == grid.OTHER) {
    			    g.setColor(Color.white);
    			    g.fillOval(x * UNIT_SIZE, y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

    		    }
    	    }
        }
	}
	
}