package view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Info bar shows the game info including player's turn and player's score
 */
public class ScoreBoard extends JPanel {
    private String playerOneName;
    private String playerTwoName = "Black";
    private boolean currentPlayer;

    private int playerOneScore;
    private int playerTwoScore;

    private JPanel scoreBoardPanel;
    private JLabel turnLabel;
    private JLabel scoreLabel;
    
    /**
     * Create a score board
     * @param inputplayerOneName Name of Player1
     * @param inputplayerTwoName Name of Player2
     */
    public ScoreBoard(String inputplayerOneName, String inputplayerTwoName) {
    	playerOneName = inputplayerOneName;
    	playerTwoName = inputplayerTwoName;
        currentPlayer = false;
        playerOneScore = 0;
        playerTwoScore = 0;
        scoreBoardPanel = new JPanel();
        scoreBoardPanel.setLayout(new BoxLayout(scoreBoardPanel, Y_AXIS));
        
        turnLabel = new JLabel(playerOneName + "'s turn");
        scoreLabel = new JLabel(playerOneName + ": " + playerOneScore + " vs " + playerTwoName + ": " + playerTwoScore);
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreBoardPanel.add(turnLabel);
        scoreBoardPanel.add(scoreLabel);
    }

    /**
     * Get score board
     * @return JPanel Score board
     */
    public JPanel getscoreBoardPanel() {
    	return scoreBoardPanel;
    }

    /**
     * Change turn
     */
    public void changePlayer() {
    	if(currentPlayer == false) turnLabel.setText(playerTwoName + "'s turn");
    	else turnLabel.setText(playerOneName + "'s turn");
    	currentPlayer = !currentPlayer;
    }

    /**
     * Set current turn
     * @param curId Index representing the current player
     */
    public void setCurrentPlayer(int curId) {
    	if(curId == 1) {
    		turnLabel.setText(playerOneName + "'s turn");
    		currentPlayer = false;
    	} else {
    		turnLabel.setText(playerTwoName + "'s turn");
    		currentPlayer = true;
    	} 
    }

    /**
     * Update score on score board
     * @param winnerId Index representing the winner
     */
    public void updateScore(int winnerId) {
        if (winnerId == 1) playerOneScore++;
        if (winnerId == 2) playerTwoScore++;
        scoreLabel.setText(playerOneName + ": " + playerOneScore + " vs " + playerTwoName + ": " + playerTwoScore);
    }

    /**
     * Set names of player on score board
     * @param isPlayerOne True to set player1's name
     * @param playerName Name to set
     */
    public void setPlayerName(boolean isPlayerOne, String playerName) {
    	if(isPlayerOne) playerOneName = playerName;
    	else playerTwoName = playerName;

    }
    
    /**
     * Get name of player1
     * @return Name of player1
     */
    public String getPlayerOneName() {
    	return playerOneName;
    }
    
    /**
     * Get name of player2
     * @return Name of player2
     */
    public String getPlayerTwoName() {
    	return playerTwoName;
    }
}
