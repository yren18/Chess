package main;

import javafx.util.Pair;
import java.util.*; 

public class Logic {
	private Board board = new Board();
	private Stack<Pair<String, String>> record;
	
	public Logic(boolean regularGame) {
		//player0
		if(regularGame) {
	        board.addPiece(new Rook(false, 0, 0, board));
	        board.addPiece(new Rook(false, 7, 0, board));
	        board.addPiece(new Knight(false, 1, 0, board));
	        board.addPiece(new Knight(false, 6, 0, board));
	        board.addPiece(new Bishop(false, 2, 0, board));
	        board.addPiece(new Bishop(false, 5, 0, board));
	        board.addPiece(new Queen(false, 3, 0, board));
	        board.addPiece(new King(false, 4, 0, board));
	        
			//player1
	        board.addPiece(new Rook(true, 0, 7, board));
	        board.addPiece(new Rook(true, 7, 7, board));
	        board.addPiece(new Knight(true, 1, 7, board));
	        board.addPiece(new Knight(true, 6, 7, board));
	        board.addPiece(new Bishop(true, 2, 7, board));
	        board.addPiece(new Bishop(true, 5, 7, board));
	        board.addPiece(new Queen(true, 3, 7, board));
	        board.addPiece(new King(true, 4, 7, board));
	        
	        for(int i = 0; i < 8; i++){
	            board.addPiece(new Pawn(false, i, 1, board, true));
	            board.addPiece(new Pawn(true, i, 6, board, true));
	        }
		} else {
	        board.addPiece(new Rook(false, 0, 0, board));
	        board.addPiece(new Rook(false, 7, 0, board));
	        board.addPiece(new Archer(false, 1, 0, board));
	        board.addPiece(new Archer(false, 6, 0, board));
	        board.addPiece(new Cannon(false, 2, 0, board));
	        board.addPiece(new Cannon(false, 5, 0, board));
	        board.addPiece(new Queen(false, 3, 0, board));
	        board.addPiece(new King(false, 4, 0, board));
	        
			//player1
	        board.addPiece(new Rook(true, 0, 7, board));
	        board.addPiece(new Rook(true, 7, 7, board));
	        board.addPiece(new Archer(true, 1, 7, board));
	        board.addPiece(new Archer(true, 6, 7, board));
	        board.addPiece(new Cannon(true, 2, 7, board));
	        board.addPiece(new Cannon(true, 5, 7, board));
	        board.addPiece(new Queen(true, 3, 7, board));
	        board.addPiece(new King(true, 4, 7, board));
	        
	        for(int i = 0; i < 8; i++){
	            board.addPiece(new Pawn(false, i, 1, board, true));
	            board.addPiece(new Pawn(true, i, 6, board, true));
	        }
		}
        
        record = new Stack<Pair<String, String>>();
	}
	
    public Board getBoard(){
        return board;
    }
    
    public Stack<Pair<String, String>> getRecord() {
    	return record;
    }
}
