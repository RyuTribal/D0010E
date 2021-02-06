package lab4.data;

import java.util.Observable;
import java.util.Observer;

import lab4.client.GomokuClient;

public class GomokuGameState extends Observable implements Observer {
	private GomokuClient client = null;
	private GomokuGrid grid = null;
	private final int DEFAULT_SIZE = 19;
	private final int NOT_STARTED = 0;
	private final int MY_TURN = 1;
	private final int OTHER_TURN = 2;
	private final int FINISHED = 3;
	private int currentState = 0;
	private String message = "";
	public GomokuGameState(GomokuClient gc){
		client = gc;
		client.addObserver(this);
		gc.setGameState(this);
		currentState = NOT_STARTED;
		grid = new GomokuGrid(DEFAULT_SIZE);
		message = "Welcome to gomoku";
	}
	public String getMessageString(){
		return message;
	}
	public GomokuGrid getGameGrid() {
		return this.grid;
	}
	public void move(int x, int y) {
		if(currentState == MY_TURN && this.grid.move(x, y, 1)) {
			if(this.grid.isWinner(1)) {
				message = "You have won the game";
				currentState = FINISHED;
			}
			else {
				message = "You moved to square (" + x +", " + y + ").";
				currentState = OTHER_TURN;
			}
			client.sendMoveMessage(x, y);
		}
		else {
			message = "This move is not possible at current time";
		}
		setChanged();
		notifyObservers();
		
	}
	public void receivedMove(int x, int y) {
		if(currentState == OTHER_TURN &&this.grid.move(x, y, 2)) {
			if(this.grid.isWinner(2)) {
				message = "The opponent has won the game";
				currentState = FINISHED;
			}
			else {
				message = "Opponent moved to square (" + x + ", " + y + ").";
				currentState = MY_TURN;
			}
			
		}
		else {
			message = "This move is not possible at current time";
		}	
		setChanged();
		notifyObservers();
		
	}
	public void disconnect() {
		client.disconnect();
		grid.clearGrid();
		message = "Disconnected from game";
		currentState = FINISHED;
		setChanged();
		notifyObservers();
		
	}
	
	public void otherGuyLeft() {
		grid.clearGrid();
		message = "Opponent disconnected from game";
		currentState = FINISHED;
		setChanged();
		notifyObservers();
	}
	public void newGame() {
		grid.clearGrid();
		currentState = OTHER_TURN;
		message = "Game started, waiting for other player...";
		client.sendNewGameMessage();
		setChanged();
		notifyObservers();
	}
	public void receivedNewGame() {
		grid.clearGrid();
		currentState = MY_TURN;
		message = "Game started, it is your turn!";
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