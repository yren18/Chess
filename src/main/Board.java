package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javafx.util.Pair;

/**
 * Board containing pieces
 * 
 * @file board.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public class Board {
    /**
     * x position of the piece
     */
	private Piece [][] chessboard;
	
    /**
     * all piece of player0
     */
	private Vector<Piece> player0;
	
    /**
     * all piece of player1
     */
	private Vector<Piece> player1;
	
	/**
	 * Create a new board
	 * Initialize everything as empty
	 */
    public Board(){
        this.chessboard = new Piece [8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
            	this.chessboard[i][j] = null;
            }
        }
        player0 = new Vector<Piece>();
        player1 = new Vector<Piece>();
    }

    /**
     * Get all player0's piece
     * 
     * @return Vector<Piece> All pieces of player0
     */
    public Vector<Piece> getPlayer0() {
    	return player0;
    }
     
    /**
     * Get all player1's piece
     * 
     * @return Vector<Piece> All pieces of player1
     */
    public Vector<Piece> getPlayer1() {
    	return player1;
    }
    
    /**
     * Get piece at position (i,j)
     * 
     * @param i location on axis i
     * @param j location on axis j
     * @return Piece Piece at position (i,j)
     */
    public Piece getPiece(int i, int j){
        if (i < 0 || i > 7 || j < 0 || j > 7){
            return null;
        }
        return chessboard[i][j];
    }
    
    /**
     * Check if piece can move to position (i,j) without considering being checked
     * 
     * @param i target x-position
     * @param j target y-position
     * @param p piece to move
     * @return boolean A boolean that is true if piece can move to position (i,j) without considering being checked
     */
    public boolean canMove(int i, int j, Piece p){
        if (p instanceof Pawn) {
            Pawn pawn = (Pawn) p;
            return pawn.canMove(i, j);
        }
        if (p instanceof Bishop) {
            Bishop bishop = (Bishop) p;
            return bishop.canMove(i, j);
        }
        if (p instanceof Knight) {
            Knight knight = (Knight) p;
            return knight.canMove(i, j);
        }
        if (p instanceof Rook) {
            Rook rook = (Rook) p;
            return rook.canMove(i, j);
        }
        if (p instanceof Queen) {
            Queen queen = (Queen) p;
            return queen.canMove(i, j);
        }
        if (p instanceof King) {
            King king = (King) p;
            return king.canMove(i, j);
        }
        if (p instanceof Archer) {
        	Archer archer = (Archer) p;
        	return archer.canMove(i, j);
        }
        if (p instanceof Cannon) {
        	Cannon cannon = (Cannon) p;
        	return cannon.canMove(i, j);
        }
        
        return false;
    }
    
    /**
     * Check if piece can move to position (i,j) considering being checked
     * 
     * @param i target x-position
     * @param j target y-position
     * @param p piece to move
     * @return boolean A boolean that is true if piece can move to position (i,j) considering being checked
     */
    public boolean validMove(int i, int j, Piece p) {
    	return canMove(i, j, p) && !p.isMate(i,j);
    }
    
    /**
     * Helper function
     * Add piece to corresponding player vector
     * 
     * @param p piece to add
     */
    public void addHelper(Piece p) {
    	if(p == null) return;
		if(p.getPlayer() == false) {
			player0.add(p);
		} else {
			player1.add(p);
		}
    }
    
    /**
     * Helper function
     * Remove piece from corresponding player vector
     * 
     * @param p piece to remove
     */
    public void removeHelper(Piece p) {
		if(p.getPlayer() == false) {
			player0.remove(p);
		} else {
			player1.remove(p);
		}
    }
    
    /**
     * Add piece to corresponding player and change chessboard to specific location
     * can add null piece
     * 
     * @param i target x-position
     * @param j target x-position
     * @param p piece to add
     * @return boolean A boolean that is true if target piece is added to target position successfully
     */
    public boolean addPiece(int i, int j, Piece p) {
    	if(this.getPiece(i,j) == null) {
    		chessboard[i][j] = p;
    		addHelper(p);
    		return true;
    	}
    	return false;
    }
    
    /**
     * Add piece to corresponding player and change chessboard at its specific location
     * 
     * @param p piece to add
     * @return boolean A boolean that is true if target piece is added to its position successfully
     */
    public boolean addPiece(Piece p) {
    	if(p == null) return false;
    	int i = p.getRow();
    	int j = p.getCol();
    	if(this.getPiece(i,j) == null) {
    		chessboard[i][j] = p;
    		addHelper(p);
    		return true;
    	}
    	return false;
    }
    
    
    /**
     * Remove piece from corresponding player and change chessboard at its specific location
     * 
     * @param p piece to delete 
     */
    public void deletePiece(Piece p) {
    	if(p == null) return;
    	chessboard[p.getRow()][p.getCol()] = null;
    	removeHelper(p);
    }
    
    /**
     * Try to move target piece to position (i,j)
     * Return true if target piece is moved to target position
     * Return false if it is not
     * 
     * @param i target x position
     * @param j target y position
     * @param p piece to move
     * @return boolean A boolean that is true if target piece is moved to target position successfully
     */
    public boolean movePiece(int i, int j, Piece p) {
    	if(this.validMove(i, j, p) == true) {
    		Piece newP = null;
        	boolean player = p.getPlayer();
        	deletePiece(this.getPiece(i, j));
        	deletePiece(p);
        	
            if (p instanceof Pawn){
            	newP = new Pawn(player, i, j, this, false);
            }  
            if (p instanceof Bishop){
            	newP = new Bishop(player,  i, j, this);
            } 
            if (p instanceof Knight){
            	newP = new Knight(player,  i, j, this);
            } 
            if (p instanceof Rook){
            	newP = new Rook(player,  i, j, this);
            } 
            if (p instanceof Queen){
            	newP = new Queen(player,  i, j, this);
            } 
            if (p instanceof King){
            	newP = new King(player,  i, j, this);
            } 
            if (p instanceof Archer){
            	newP = new Archer(player,  i, j, this);
            } 
            if (p instanceof Cannon){
            	newP = new Cannon(player,  i, j, this);
            } 
            
            
        	if(this.addPiece(newP)==true) {
        		return true;
        	}
    	}
    	return false;
    }
    
    /**
     * Force to add the target piece at target position without considering mate condition
     * Need to pre-set position for p
     * 
     * @param i target x position
     * @param j target y position
     * @param p piece to move
     */
    public void forceSetPiece(int i, int j, Piece p) {
    	deletePiece(this.getPiece(i, j));
    	this.addPiece(p);
    }
    
    /**
     * Check if current player is being checked by opponents
     * 
     * @param pieceArr0 all pieces of current player
     * @param pieceArr1 all pieces of opponents
     * @return boolean A boolean that is true if current player is being checked by opponent
     */
    private boolean isBeingChecked(Vector<Piece> pieceArr0, Vector<Piece> pieceArr1) {
		int kingRow;
		int kingCol;
		
	    for (Piece p0 : pieceArr0) {
	    	if(p0 instanceof King) {
	    		kingRow = p0.getRow();
	    		kingCol = p0.getCol();
	    	    for (Piece p1 : pieceArr1) {
	    	    	if(p1.canMove(kingRow,kingCol)) 
	    	    		return true;
	    	    }
	    	    return false;
	    	}
	    }
	    return false;
    }
    
    /**
     * Check if the current player is being checked or not
     * @param curPlayer
     * @return True if the current player is being checked 
     */
    public boolean isBeingChecked(boolean curPlayer) {
		Vector<Piece> pieceArr0;
		Vector<Piece> pieceArr1;
		if(curPlayer == false) {
			pieceArr0 = this.getPlayer0();
			pieceArr1 = this.getPlayer1();
		} else {
			pieceArr0 = this.getPlayer1();
			pieceArr1 = this.getPlayer0();
		}
		return isBeingChecked(pieceArr0, pieceArr1);
    }
    
    /**
     * Check if there is any move that current player can take to avoid being checked
     * 
     * @param pieceArr0 all pieces of current player
     * @param pieceArr1 all pieces of opponents
     * @return boolean A boolean that is true if any moves of current player keep him/her being checked by opponent
     */
    public boolean noValidMove(Vector<Piece> pieceArr0, Vector<Piece> pieceArr1) {
	    for(int i = 0; i < 8; i++) {
		    for(int j = 0; j < 8; j++) {
			    for (Piece p : pieceArr0) 
			    	if(this.validMove(i, j, p) == true) return false;
		    }
	    }
	    return true;
    }
    
    /**
     * Check if current state is checkMate
     * 
     * @param curPlayer the current player
     * @return boolean A boolean that is true if current state is checkMate
     */
    public boolean checkMate(boolean curPlayer) {
		Vector<Piece> pieceArr0;
		Vector<Piece> pieceArr1;
		if(curPlayer == false) {
			pieceArr0 = this.getPlayer0();
			pieceArr1 = this.getPlayer1();
		} else {
			pieceArr0 = this.getPlayer1();
			pieceArr1 = this.getPlayer0();
		}
		
		if(isBeingChecked(pieceArr0, pieceArr1) == false) return false;
		return noValidMove(pieceArr0, pieceArr1);
    }
    
    /**
     * Check if current state is staleMate
     * 
     * @param curPlayer the current player
     * @return boolean A boolean that is true if current state is staleMate
     */
    public boolean staleMate(boolean curPlayer) {
		Vector<Piece> pieceArr0;
		Vector<Piece> pieceArr1;
		if(curPlayer == false) {
			pieceArr0 = this.getPlayer0();
			pieceArr1 = this.getPlayer1();
		} else {
			pieceArr0 = this.getPlayer1();
			pieceArr1 = this.getPlayer0();
		}
		
		if(isBeingChecked(pieceArr0, pieceArr1) == true) return false;
		return noValidMove(pieceArr0, pieceArr1);
    }
    
    /**
     * Get all valid move of the given piece
     * @param p Piece given
     * @return List<Pair<Integer, Integer>> All valid move
     */
    public List<Pair<Integer, Integer>> allValidMove(Piece p) {
    	List<Pair<Integer, Integer>> result = new ArrayList<>();
    	for(int i = 0 ; i < 8; i ++) {
        	for(int j = 0 ; j < 8; j ++) {
        		if(this.validMove(i,j,p)) {
        			result.add(new Pair<Integer,Integer> (i,j));
        		}
        	}
    	}
    	return result;
    }
}   