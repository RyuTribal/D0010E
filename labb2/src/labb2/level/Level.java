package labb2.level;

import java.util.Observable;
import java.util.ArrayList;

@Deprecated
public class Level extends Observable {
	ArrayList<Room> rooms = new ArrayList<Room>();
	public int width = 0;
	public int height = 0;
	public Room playerRoom = null;
	
	public boolean place(Room r, int x, int y)  {
		if(playerRoom == null){
			for(int i = 0; i < rooms.size(); i++){
				Room currentRoom = rooms.get(i);
				if(currentRoom.x < x + r.width && currentRoom.x + currentRoom.width > x && currentRoom.y < y + r.height && currentRoom.y + currentRoom.height > y){
					return false;
				}
			}
			r.x = x;
			r.y = y;
			rooms.add(r);
			if(r.width + x > width){
				width = r.width + x;
			}
			if(r.height + y > height){
				height = r.height + y;
			}
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public void firstLocation(Room r) {
		playerRoom = r;
	}
	

	public void moveRoom(Room currentRoom, int direction){
		if(currentRoom.connections[direction] != null){
			playerRoom = currentRoom.connections[direction];
			setChanged(); 
			notifyObservers(); 
		}
		
	}
}