package view;

import javax.swing.*;
import java.awt.*;

/**
 * Game 
 * 
 * @file Game.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public class GameBoard {
	private JFrame window;
	private Board board;
    private ScoreBoard scoreBoard;
    private String playerOneName;
    private String playerTwoName;
    
	/**
	 * Create a new game
	 */
	public GameBoard(boolean regularGame) {
		window = new JFrame("Chess Game");
        window.setSize(1000, 1000);
        window.getContentPane().setLayout(new BorderLayout());
        
        initEmptyBoard();
        requestPlayerNames();

        addScoreBoard();
        addMenu();
        
        window.getContentPane().add(board.getPanel(), BorderLayout.CENTER);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	}
	
	/**
	 * Initialize empty bound
	 * 
	 */
	public void initEmptyBoard() {
		board = new Board();
	}
	
	/**
	 * Initialize piece at the beginning state
	 */
	public void initPiece(boolean regularGame) {
		board.getCell("A1").setPiece("Rook_white");
		board.getCell("D1").setPiece("Queen_white");
		board.getCell("E1").setPiece("King_white");
		board.getCell("H1").setPiece("Rook_white");
		board.getCell("A8").setPiece("Rook_black");
		board.getCell("D8").setPiece("Queen_black");
		board.getCell("E8").setPiece("King_black");
		board.getCell("H8").setPiece("Rook_black");
		if(regularGame) {
			board.getCell("B1").setPiece("Knight_white");
			board.getCell("C1").setPiece("Bishop_white");
			board.getCell("F1").setPiece("Bishop_white");
			board.getCell("G1").setPiece("Knight_white");
			board.getCell("B8").setPiece("Knight_black");
			board.getCell("C8").setPiece("Bishop_black");
			board.getCell("F8").setPiece("Bishop_black");
			board.getCell("G8").setPiece("Knight_black");
		} else {
			board.getCell("C1").setPiece("Cannon_white");
			board.getCell("B1").setPiece("Archer_white");
			board.getCell("F1").setPiece("Cannon_white");
			board.getCell("G1").setPiece("Archer_white");
			board.getCell("C8").setPiece("Cannon_black");
			board.getCell("B8").setPiece("Archer_black");
			board.getCell("F8").setPiece("Cannon_black");
			board.getCell("G8").setPiece("Archer_black");
		}
		
		for(int i = 0; i < 8; i ++) {
			String curChar = Character.toString((char)(65 + i));
			board.getCell(curChar+"2").setPiece("Pawn_white");
			board.getCell(curChar+"7").setPiece("Pawn_black");
		}
		
		for(int i = 0; i < 8; i ++) {
			for(int j = 2; j < 6; j ++) {
				String curChar = Character.toString((char)(65 + i)) + Integer.toString(j+1);
				board.getCell(curChar).setPiece("empty");
			}
		}

	}
	
	/**
	 * Add score board
	 */
    private void addScoreBoard() {
        scoreBoard = new ScoreBoard(playerOneName, playerTwoName);
        window.getContentPane().add(scoreBoard.getscoreBoardPanel(), BorderLayout.PAGE_START);
    }
    
    /**
     * Add menu
     */
    private void addMenu() {
        JMenuBar menu = new JMenuBar();

        JMenu file = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem forfeit = new JMenuItem("Forfeit");
        JMenuItem unDo = new JMenuItem("Undo");
        JMenuItem exit = new JMenuItem("Exit");
        
        file.add(newGame);
        file.add(restart);
        file.add(forfeit);
        file.add(unDo);
        file.add(exit);

        menu.add(file);
        window.setJMenuBar(menu);
    }
    
    /**
     * Get board of the GameBoard
     * @return Board Board of the GameBoard
     */
    public Board getBoard() {
    	return board;
    }
    
    /**
     * Get JFrame of the GameBoard
     * @return JFrame JFrame of the GameBoard
     */
    public JFrame getFrame() {
    	return window;
    }
    
    /**
     * Get ScoreBoard of the GameBoard
     * @return ScoreBoard ScoreBoard of the GameBoard
     */
    public ScoreBoard getScoreBoard() {
    	return scoreBoard;
    }
    
    /**
     * Create dialog to get player names input
     */
    private void requestPlayerNames() {
        playerOneName = JOptionPane.showInputDialog(board.getPanel(), "Player 1 name", "white");
        playerTwoName = JOptionPane.showInputDialog(board.getPanel(), "Player 2 name", "black");
    }
    
    /**
     * Get name of player 1
     * @return String Name of player 1
     */
    public String getplayerOneName() {
    	return playerOneName;
    }
    
    /**
     * Get name of player 2
     * @return String Name of player 2
     */
    public String getplayerTwoName() {
    	return playerTwoName;
    }
}
