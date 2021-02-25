package lab4.data;

import java.util.Observable;
import java.util.Observer;

import lab4.client.GomokuClient;

/**
 * Represents the state of a game
 * 
 * @author André Roaas, Ivan Sedelkin
 */

public class GomokuGameState extends Observable implements Observer{

	// Game variables
	public final int DEFAULT_SIZE = 15;
	private GameGrid gameGrid;
	
	//Possible game states
	private final int NOT_STARTED = 0;
	private int currentState;
	private final int FINISHED = 3;
	private final int MY_TURN = 1;
	private final int OTHER_TURN = 2;
	
	private GomokuClient client;
	
	private String message;
	
	/**
	 * The constructor
	 * 
	 * @param gc The client used to communicate with the other player
	 */
	public GomokuGameState(GomokuClient gc){
		client = gc;
		client.addObserver(this);
		gc.setGameState(this);
		currentState = NOT_STARTED;
		gameGrid = new GameGrid(DEFAULT_SIZE);
	}
	
	/**
	 * Returns the message string
	 * 
	 * @return the message string
	 */
	public String getMessageString(){
		return message;
	}
	
	/**
	 * Returns the game grid
	 * 
	 * @return the game grid
	 */
	public GameGrid getGameGrid(){
		return gameGrid;
	}

	/**
	 * This player makes a move at a specified location
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void move(int x, int y){
		if (currentState == NOT_STARTED) {
			message = "The Game has not been started.";
		} 
		else if (currentState == OTHER_TURN) {
			message = "Waiting for the opponent to make a move.";
		} 
		else if (currentState == MY_TURN) {
			if (!gameGrid.move(x, y, MY_TURN)) {
				message = "Pick an empty square.";
			} else {
				currentState = OTHER_TURN;
				message = "You picked ("+(x+1)+","+(y+1)+")";
				client.sendMoveMessage(x, y);
			}
		}
		
		if (gameGrid.isWinner(MY_TURN)) {
			currentState = FINISHED;
			message = "You have won.";
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Starts a new game with the current client
	 */
	public void newGame(){
		gameGrid.clearGrid();
		currentState = OTHER_TURN;
		message = "Game started, waiting for other player...";
		client.sendNewGameMessage();
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Other player has requested a new game, so the 
	 * game state is changed accordingly
	 */
	public void receivedNewGame(){
		gameGrid.clearGrid();
		currentState = MY_TURN;
		message = "Game started, it is your turn!";
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * The connection to the other player is lost, 
	 * so the game is interrupted
	 */
	public void otherGuyLeft(){
		gameGrid.clearGrid();
		message = "The other player disconnected, the game is over.";
		currentState = FINISHED;
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * The player receives a move from the other player
	 * 
	 * @param x The x coordinate of the move
	 * @param y The y coordinate of the move
	 */
	public void disconnect(){
		gameGrid.clearGrid();
		client.disconnect();
		currentState = FINISHED;
		message = "You disconnected, the game is over.";
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * The player receives a move from the other player
	 * 
	 * @param x The x coordinate of the move
	 * @param y The y coordinate of the move
	 */
	public void receivedMove(int x, int y){
		gameGrid.move(x, y, OTHER_TURN);
		if (gameGrid.isWinner(OTHER_TURN)) {
			currentState = FINISHED;
			message = "The other player has won.";
		} else {
			currentState = MY_TURN;
			message = "It is your turn!";
		}
		setChanged();
		notifyObservers();
	}
	
	public void update(Observable o, Object arg) {
		
		switch(client.getConnectionStatus()){
		case GomokuClient.CLIENT:
			message = "Game started, it is your turn!";
			currentState = MY_TURN;
			break;
		case GomokuClient.SERVER:
			message = "Game started, waiting for other player...";
			currentState = OTHER_TURN;
			break;
		}
		setChanged();
		notifyObservers();
		
		
	}
	
}