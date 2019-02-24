package controller;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Vector;
import view.*;
import main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BoardController implements ActionListener {
	private view.GameBoard gameBoard;
	private main.Logic gameLogic;
    private boolean curPlayer;
    private boolean selectStatus;
    private String selectedCellId;
    private List<Pair<Integer, Integer>> colorToReset;
    private String playerOneName;
    private String playerTwoName;
	private boolean regularGame;
    
	/**
	 * Create BoardController
	 */
    public BoardController() {
        if(JOptionPane.showConfirmDialog(null,"Try custom game?", "Game Type Selection", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
        	regularGame = true;
        } else {
        	regularGame = false;
        }
    	gameBoard = new GameBoard(regularGame);

    	gameLogic = new Logic(regularGame);
    	
    	playerOneName = gameBoard.getplayerOneName();
    	playerTwoName = gameBoard.getplayerTwoName();
    	
    	curPlayer = false;
    	selectStatus = false;
    	selectedCellId = "";
    	colorToReset = null;
        
        JMenu GameMenu = getMenu();
        JMenuItem newMenuItem = GameMenu.getItem(0);
        JMenuItem startMenuItem = GameMenu.getItem(1);
        JMenuItem forfeit = GameMenu.getItem(2);
        JMenuItem unDo = GameMenu.getItem(3);
        JMenuItem exitMenuItem = GameMenu.getItem(4);
        
        newMenuItem.addActionListener(this);
        startMenuItem.addActionListener(this);
        forfeit.addActionListener(this);
        unDo.addActionListener(this);
        exitMenuItem.addActionListener(this);
        
    }
    
    /**
     * Override actionPerformed
     * Handle click actions
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String commandId = e.getActionCommand();
        
        if(commandId == "New") {
        	newGame();
            for(int i = 0; i < 8; i ++) {
                for(int j = 0; j < 8; j ++) {
                	gameBoard.getBoard().getCell(coordToId(i, j)).getButton().addActionListener(this);	
                }        	
            } 
        	return;
        }
        if(commandId == "Restart") {
        	requestRestart();
        	return;
        }
        if(commandId == "Forfeit") {
        	forfeit();
        	return;
        }
        if(commandId == "Undo") {
        	unDo();
        	return;
        }
        if(commandId == "Exit") {
        	System.exit(0);
        }
        
        int i = idToCoord(commandId).getKey();
        int j = idToCoord(commandId).getValue();
        Piece currentPiece = gameLogic.getBoard().getPiece(i, j);
        
    	if(selectStatus == false) {
    		setFirstSelect(currentPiece, commandId);
    	} else {
    		setSecondSelect(commandId, i, j);
    	}
    }
    
    /**
     * Handle first select
     * @param currentPiece Current selected piece
     * @param commandId ID of corresponding cell (e.g: A3, B8)
     */
    private void setFirstSelect(Piece currentPiece, String commandId) {
		if((currentPiece != null) && (currentPiece.getPlayer() == curPlayer)) {
			gameBoard.getBoard().getCell(commandId).setSelectedColor();
			selectStatus = true;
			selectedCellId = commandId;
			colorToReset = currentPiece.getBoard().allValidMove(currentPiece);
			for(Pair<Integer, Integer> curPair : colorToReset) {
				gameBoard.getBoard().getCell(coordToId(curPair.getKey(),curPair.getValue())).setPossibleMoveColor();
			}
		}
    }
    
    /**
     * Handle second select
     * @param commandId ID of the clicked cell (e.g: A3, B8)
     * @param i Row index of the clicked cell
     * @param j Col index of the clicked cell
     */
    private void setSecondSelect(String commandId, int i, int j) {
		boolean isPlayerChanged  = false;
		if(selectedCellId == commandId) {
			gameBoard.getBoard().getCell(selectedCellId).setCellColor();
			selectStatus = false;
			selectedCellId = "";
		} else {
			int oriX = idToCoord(selectedCellId).getKey();
			int oriY = idToCoord(selectedCellId).getValue();
            Piece selectedPiece = gameLogic.getBoard().getPiece(oriX, oriY);
        	String pieceFrom = generateRecordInfo(selectedCellId);
        	String pieceTo = generateRecordInfo(commandId);
            if(gameLogic.getBoard().movePiece(i, j, selectedPiece)) {
            	this.gameLogic.getRecord().add(new Pair<String, String> (pieceFrom, pieceTo));
            	
            	gameBoard.getBoard().getCell(commandId).setPiece(selectedPiece.getPieceName());
            	gameBoard.getBoard().getCell(selectedCellId).setPiece("empty");
            	gameBoard.getBoard().getCell(selectedCellId).setCellColor();
            	
            	selectStatus = false;
            	selectedCellId = "";
            	curPlayer = !curPlayer;
            	gameBoard.getScoreBoard().changePlayer();
            	isPlayerChanged = true;
			} else {
				gameBoard.getBoard().getCell(selectedCellId).setCellColor();
            	selectStatus = false;
            	selectedCellId = "";
			}
		}
		
		for(Pair<Integer, Integer> curPair : colorToReset) {
			gameBoard.getBoard().getCell(coordToId(curPair.getKey(),curPair.getValue())).setCellColor();
		}
		colorToReset = null;
		
		if(isPlayerChanged) {
			if(gameLogic.getBoard().checkMate(curPlayer)) {
				if(curPlayer == false) {
					playerTwoWin();
				}
				else {
					playerOneWin();
				}
			}
			if(gameLogic.getBoard().staleMate(curPlayer)) {
				tie();
			}
		}
    }
    
    /**
     * Handle case when player 1 wins
     */
    private void playerOneWin() {
    	JOptionPane.showMessageDialog(null,playerOneName + "(White) wins!","Game Finish", JOptionPane.INFORMATION_MESSAGE);
		gameBoard.getScoreBoard().updateScore(1);
		restartGame();
    }
    
    /**
     * Handle case when player 2 wins
     */
    private void playerTwoWin() {
    	JOptionPane.showMessageDialog(null,playerTwoName + "(Black) wins!", "Game Finish", JOptionPane.INFORMATION_MESSAGE);
		gameBoard.getScoreBoard().updateScore(2);
		restartGame();
    }
    
    /**
     * Handle case when tie
     */
    private void tie() {
    	JOptionPane.showMessageDialog(null,"StaleMate -- Tie!", "Game Finish", JOptionPane.INFORMATION_MESSAGE);
		restartGame();
    }
    
    /**
     * Keep the score and restart current game
     */
    private void restartGame() {
		gameBoard.initPiece(regularGame);
		gameBoard.getScoreBoard().setCurrentPlayer(1);
		gameLogic = new Logic(regularGame);
    	curPlayer = false;
    	selectStatus = false;
    	selectedCellId = "";
    	colorToReset = null;
    	JOptionPane.showMessageDialog(null,"Ready? Go!", "Game Resteart", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Handle case when current player sends request of restart
     */
    private void requestRestart() {
    	if(curPlayer == false) {
    		if(JOptionPane.showConfirmDialog(null,playerOneName + "(white) requests to restart. Do you (" + playerTwoName + ") agree?", "Restart Request", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
        		for(int i = 0; i < 8; i ++) {
        			for(int j = 0; j < 8; j ++) {
        				gameBoard.getBoard().getCell(coordToId(i,j)).setCellColor();
        			}
        		}
    			restartGame();
    		}
    	} else {
        	if(JOptionPane.showConfirmDialog(null,playerTwoName + "(black) requests to restart. Do you (" + playerOneName + ") agree?", "Restart Request", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        		for(int i = 0; i < 8; i ++) {
        			for(int j = 0; j < 8; j ++) {
        				gameBoard.getBoard().getCell(coordToId(i,j)).setCellColor();
        			}
        		}
	        	restartGame();
    		}
    	}
    }

    /**
     * Handle case when current player surrenders
     */
    private void forfeit() {  	
    	if(curPlayer == false) {
    		for(int i = 0; i < 8; i ++) {
    			for(int j = 0; j < 8; j ++) {
    				gameBoard.getBoard().getCell(coordToId(i,j)).setCellColor();
    			}
    		}
    		JOptionPane.showMessageDialog(null,playerOneName + "(white) decides to surrender", "Forfeit", JOptionPane.INFORMATION_MESSAGE);
    		playerTwoWin();
        	
    	} else {
    		for(int i = 0; i < 8; i ++) {
    			for(int j = 0; j < 8; j ++) {
    				gameBoard.getBoard().getCell(coordToId(i,j)).setCellColor();
    			}
    		}
        	JOptionPane.showMessageDialog(null,playerTwoName + "(black) decides to surrender", "Forfeit", JOptionPane.INFORMATION_MESSAGE);
        	playerOneWin();
    	}

    	
    }
    
    /**
     * Handle case when current player requests to undo
     */
    private void unDo() {
    	if(gameLogic.getRecord().isEmpty()) {
    		JOptionPane.showMessageDialog(null,"No step to undo!","Undo", JOptionPane.INFORMATION_MESSAGE);
    		return;
    	}
    	Pair<String, String> lastCommand = gameLogic.getRecord().pop();
    	
    	String infoFrom = lastCommand.getKey();
    	String cellIdFrom = infoFrom.substring(0, 2);
    	String pieceNameFrom = infoFrom.substring(2);
    	int rowIdxFrom = idToCoord(cellIdFrom).getKey();
    	int colIdxFrom = idToCoord(cellIdFrom).getValue();
    	
    	String infoTo = lastCommand.getValue();
    	String cellIdTo = infoTo.substring(0, 2);
    	String pieceNameTo = infoTo.substring(2);
    	int rowIdxTo = idToCoord(cellIdTo).getKey();
    	int colIdxTo = idToCoord(cellIdTo).getValue();
    	
    	gameBoard.getBoard().getCell(cellIdFrom).setPiece(pieceNameFrom);
    	gameBoard.getBoard().getCell(cellIdTo).setPiece(pieceNameTo);
    	Piece pieceFrom = createNewPiece(pieceNameFrom, rowIdxFrom, colIdxFrom);
    	Piece pieceTo = createNewPiece(pieceNameTo, rowIdxTo, colIdxTo);
    	gameLogic.getBoard().addPiece(pieceFrom);
    	gameLogic.getBoard().deletePiece(gameLogic.getBoard().getPiece(rowIdxTo, colIdxTo));
    	gameLogic.getBoard().addPiece(pieceTo);
    	
    	selectStatus = false;
    	selectedCellId = "";
    	curPlayer = !curPlayer;
    	gameBoard.getScoreBoard().changePlayer();
    }
    
    /**
     * Start new game and initialize pieces
     */
    private void newGame() {
    	gameBoard.initPiece(regularGame);
    }
    
    /**
     * Convert CellID to corresponding coordinates
     * @param cellId CellID to convert
     * @return Corresponding coordinates
     */
    private Pair<Integer, Integer> idToCoord(String cellId) {
        return new Pair<>(cellId.charAt(0) - 'A', cellId.charAt(1) - '1');
    }
    
    /**
     * Convert corresponding coordinates to CellID
     * @param i Row index to convert
     * @param j Col index to convert
     * @return Corresponding CellID
     */
    private String coordToId(int i, int j) {
    	return Character.toString((char)(65 + i)) + Integer.toString(j+1);
    }
    
    /**
     * Get menu
     * @return Menu of the game
     */
    private JMenu getMenu() {
    	return gameBoard.getFrame().getJMenuBar().getMenu(0);
    }
    
    /**
     * Generate corresponding record information of the given cellId
     * @param cellid CellID to convert
     * @return corresponding record information
     */
    private String generateRecordInfo(String cellid) {
    	return cellid 
    			+ gameBoard.getBoard().getCell(cellid).getCellPieceName();
    }
    
    /**
     * Create a new piece based on given type and coordinates
     * @param pieceName Type of piece
     * @param i	Row index
     * @param j Col Index
     * @return new Piece of the given type and coordinates
     */
    private Piece createNewPiece(String pieceName, int i, int j) {
    	if(pieceName.equals("empty")) return null;
    	
    	if(pieceName.equals("Bishop_black")) return new Bishop(true, i, j, gameLogic.getBoard());
    	if(pieceName.equals("King_black")) return new King(true, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Knight_black")) return new Knight(true, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Queen_black")) return new Queen(true, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Rook_black")) return new Rook(true, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Cannon_black")) return new Cannon(true, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Archer_black")) return new Archer(true, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Pawn_black")) {
    		if(j == 6) return new Pawn(true, i, j, gameLogic.getBoard(),true);
    		else return new Pawn(true, i, j, gameLogic.getBoard(),false);
    	}
    	
    	if(pieceName.equals("Bishop_white")) return new Bishop(false, i, j, gameLogic.getBoard());
    	if(pieceName.equals("King_white")) return new King(false, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Knight_white")) return new Knight(false, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Queen_white")) return new Queen(false, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Rook_white")) return new Rook(false, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Cannon_white")) return new Cannon(false, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Archer_white")) return new Archer(false, i, j, gameLogic.getBoard());
    	if(pieceName.equals("Pawn_white")) {
    		if(j == 1) return new Pawn(false, i, j, gameLogic.getBoard(),true);
    		else return new Pawn(false, i, j, gameLogic.getBoard(),false);
    	}
    		
        return null;
    }
    
}