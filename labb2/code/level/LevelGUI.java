package lab2.level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;

@Deprecated
public class LevelGUI implements Observer {

	private Level lv;
	private Display d;
	
	public LevelGUI(Level level, String name) {
		
		this.lv = level;
		
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// TODO: You should change 200 to a value 
		// depending on the size of the level
		d = new Display(lv, lv.width, lv.height);
		
		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0,0);
		frame.setVisible(true);
	}
	
	
	public void update(Observable arg0, Object arg1) {
		d.repaint();
	}
	
	private class Display extends JPanel {
		int borderSize = 5;
		Color borderColor = Color.YELLOW;
		int connectionSize = 15;
		public Display(Level fp, int x, int y) {
		
			
			addKeyListener(new Listener());
			
			setBackground(Color.BLACK);
			setPreferredSize(new Dimension(x+20,y+20));
			setFocusable(true);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(borderSize));
			
			for(int i = 0; i < lv.rooms.size(); i++){
				Room currentRoom = lv.rooms.get(i);
				DisplayRooms(g2, currentRoom);
				DisplayConnection(g2, currentRoom, currentRoom.connections[0], 0);
				DisplayConnection(g2, currentRoom, currentRoom.connections[1], 1);
				DisplayConnection(g2, currentRoom, currentRoom.connections[2], 2);
				DisplayConnection(g2, currentRoom, currentRoom.connections[3], 3);
				
			}

		}

		private void DisplayRooms(Graphics g2, Room currentRoom){
			g2.setColor(currentRoom.roomColor);
			if(lv.playerRoom == currentRoom){
				g2.fillRect(currentRoom.x, currentRoom.y, currentRoom.width-(borderSize/2), currentRoom.height-(borderSize/2));
				g2.setColor(borderColor);
				g2.drawRect(currentRoom.x, currentRoom.y, currentRoom.width-(borderSize/2), currentRoom.height-(borderSize/2));
			}
			else{
				g2.fillRect(currentRoom.x, currentRoom.y, currentRoom.width, currentRoom.height);
			}
		}

		private void DisplayConnection(Graphics g2, Room currentRoom, Room connection, int type){
			if(connection != null){
				g2.setColor(connection.roomColor);
				switch(type){
					case 0:
						g2.fillRect((currentRoom.x+(currentRoom.width/2))-(connectionSize/2), currentRoom.y, connectionSize, connectionSize);
						break;
					case 1:
						g2.fillRect(currentRoom.x+currentRoom.width-connectionSize, (currentRoom.y+(currentRoom.height/2))-(connectionSize/2) , connectionSize, connectionSize);
						break;
					case 2:
						g2.fillRect((currentRoom.x+(currentRoom.width/2))-(connectionSize/2), currentRoom.y+currentRoom.height - connectionSize , connectionSize, connectionSize);
						break;
					case 3:
						g2.fillRect(currentRoom.x, (currentRoom.y+(currentRoom.height/2))-(connectionSize/2) , connectionSize, connectionSize);
						break;
					default:
						System.out.println("No room located");
				}
				
			}
		}
		

	 	private class Listener implements KeyListener {

	 		
	 		public void keyPressed(KeyEvent arg0) {
				switch(arg0.getKeyCode()){
					case 87:
						lv.moveRoom(lv.playerRoom, 0);
						break;
					case 68:
						lv.moveRoom(lv.playerRoom, 1);
						break;
					case 83:
						lv.moveRoom(lv.playerRoom, 2);
						break;
					case 65:
						lv.moveRoom(lv.playerRoom, 3);
						break;
					default:
						System.out.println("Not a direction");
				}
	 		}

	 		public void keyReleased(KeyEvent arg0) {
	 		}

	 		public void keyTyped(KeyEvent event) {
	 		}
	 	}

	}
	
}