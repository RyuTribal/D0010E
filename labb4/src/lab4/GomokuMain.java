package lab4;

import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;
import lab4.gui.GomokuGUI;

/**
 * Main class
 * 
 * @author André Roaas, Ivan Sedelkin
 */

public class GomokuMain {
	public static void main(String[] args) {
		if (args.length == 0) {
			GomokuClient client = new GomokuClient(4001);
			GomokuGameState gameState = new GomokuGameState(client);
			GomokuGUI gGUI = new GomokuGUI(gameState, client);
		} else {
			GomokuClient client = new GomokuClient(Integer.parseInt(args[0]));
			GomokuGameState gameState = new GomokuGameState(client);
			GomokuGUI gGUI = new GomokuGUI(gameState, client);
		}
	}
}
