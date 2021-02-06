package lab2.level;

import java.awt.Color;

@Deprecated
public class Room { 
	protected int width, height, x, y;
	protected Color roomColor;
	Room[] connections = {null, null, null, null};
	public Room(int dx, int dy, Color color) {
		width = dx;
		height = dy;
		roomColor = color;
	}
	public void connectNorthTo(Room r) {
		connections[0] = r;
	}
	public void connectEastTo(Room r) {
		connections[1] = r;
	}
	public void connectSouthTo(Room r) {
		connections[2] = r;
	}
	public void connectWestTo(Room r) {
		connections[3] = r;
	}
}