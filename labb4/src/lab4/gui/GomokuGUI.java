package lab4.gui;
import java.util.Observable;
import java.util.Observer;

import lab4.client.GomokuClient;
import lab4.data.GameGrid;
import lab4.data.GomokuGameState;

import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*; 

/**
 * The GUI class
 * 
 * @author André Roaas, Ivan Sedelkin
 */

public class GomokuGUI implements Observer{
	
	private GomokuClient client;
	private GomokuGameState gamestate;
	private GamePanel gameGridPanel;
	
	// Declaration of JFrame and components
	private JFrame frame;
	private JPanel GamePanel;
	private JButton connectButton, newGameButton, disconnectButton;
	private JLabel messageLabel;
	
	/**
	 * The constructor
	 * 
	 * @param g   The game state that the GUI will visualize
	 * @param c   The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c) {
		this.client = c;
		this.gamestate = g;
		client.addObserver(this);
		gamestate.addObserver(this);
        
        addComponents();
        addInteractions();
	}
	
	/**
	 * Add GUI components
	 */
	private void addComponents() {
		frame = new JFrame("Gomoku");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel = new JPanel();
		gameGridPanel = new GamePanel(gamestate.getGameGrid());
		connectButton = new JButton("Connect");
		newGameButton = new JButton("New Game");
		disconnectButton = new JButton("Disconnect");
		messageLabel = new JLabel("Welcome to Gomoku!");
		
		GamePanel.add(gameGridPanel);
		GamePanel.add(connectButton);
		GamePanel.add(newGameButton);
		GamePanel.add(disconnectButton);
		GamePanel.add(messageLabel);
		
		frame.add(GamePanel);
		frame.pack();
		frame.setSize(360, 410);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	/**
	 * Add interactions to the GUI components
	 */
	private void addInteractions() {
		gameGridPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
                int[] square = gameGridPanel.getGridPosition(e.getX(), e.getY());
                gamestate.move(square[0],square[1]);
			}
        });
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamestate.newGame();
            }
        });
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConnectionWindow cw = new ConnectionWindow(client);
            }
        });
        disconnectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamestate.disconnect();
            }
        });
	}
	
	public void update(Observable arg0, Object arg1) {
		// Update the buttons if the connection status has changed
		if(arg0 == client){
			if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			}else{
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}
		
		// Update the status text if the gamestate has changed
		if(arg0 == gamestate){
			messageLabel.setText(gamestate.getMessageString());
		}
	}
}
