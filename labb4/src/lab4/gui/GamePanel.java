package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lab4.data.GomokuGrid;

/**
 * A panel providing a graphical view of the game board
 */

public class GamePanel extends JPanel implements Observer{

	private final int UNIT_SIZE = 20;
	public GomokuGrid grid;
	
	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */
	public GamePanel(GomokuGrid grid){
        this.grid = grid;
        grid.addObserver(this);
        Dimension d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setBackground(Color.BLACK);
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
        
        // ritar ut färgen på brädan 
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, grid.getSize() * UNIT_SIZE, grid.getSize() * UNIT_SIZE);
        
        //ritar ut linjerna med hjälp av många rektanglar som sitter ihop
        g.setColor(Color.black);
        for(int y = 0; y < grid.getSize(); y++) {
        	for(int x = 0; x < grid.getSize(); x++) {
        		g.drawRect(x*UNIT_SIZE, y*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        	}
        }

        //går igenom grid och ritar kulor på varje plats det är en spelare 
        for(int y = 0; y < grid.getSize(); y++) {
    	    for(int x = 0; x < grid.getSize(); x++) {
    		    if(grid.getLocation(x, y) == 1) {
    			    g.setColor(Color.black);
    			    g.fillOval(x * UNIT_SIZE, y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
    		    }
    		    if(grid.getLocation(x, y) == GomokuGrid.OTHER) {
    			    g.setColor(Color.white);
    			    g.fillOval(x * UNIT_SIZE, y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

    		    }
    	    }
        }
        
    }
	
}