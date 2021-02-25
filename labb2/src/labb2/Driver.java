package labb2;
import labb2.level.Level;
import labb2.level.LevelGUI;
import labb2.level.Room;

import java.awt.Color;
import java.util.ArrayList;
@Deprecated
public class Driver {

	public void run() {
		Level level1 = new Level();
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(256, 144, Color.BLACK));
		rooms.add(new Room(256, 144, Color.RED));
		rooms.add(new Room(256, 144, Color.BLUE));
		rooms.add(new Room(256, 144, Color.MAGENTA));
		rooms.add(new Room(256, 144, Color.YELLOW));
		rooms.add(new Room(256, 144, Color.GRAY));
		rooms.get(0).connectEastTo(rooms.get(1));
		rooms.get(0).connectSouthTo(rooms.get(5));
		rooms.get(1).connectSouthTo(rooms.get(4));
		rooms.get(1).connectEastTo(rooms.get(2));
		rooms.get(1).connectWestTo(rooms.get(0));
		rooms.get(2).connectSouthTo(rooms.get(3));
		rooms.get(2).connectWestTo(rooms.get(1));
		rooms.get(3).connectNorthTo(rooms.get(2));
		rooms.get(3).connectWestTo(rooms.get(4));
		rooms.get(4).connectNorthTo(rooms.get(1));
		rooms.get(4).connectEastTo(rooms.get(3));
		rooms.get(4).connectWestTo(rooms.get(5));
		rooms.get(5).connectNorthTo(rooms.get(0));
		rooms.get(5).connectEastTo(rooms.get(4));
		level1.place(rooms.get(0), 0, 0);
		level1.place(rooms.get(1), 257, 0);
		level1.place(rooms.get(2), 514, 0);
		level1.place(rooms.get(3), 514, 145);
		level1.place(rooms.get(4), 257, 145);
		level1.place(rooms.get(5), 0, 145);

		level1.firstLocation(rooms.get(0));
		LevelGUI levelGUI = new LevelGUI(level1, "Not so scary maze");
		level1.addObserver(levelGUI);
	}

}