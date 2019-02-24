package main;

/**
 * Pawn is a the archer piece
 * 
 * @file Pawn.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public class Pawn extends Piece{
	private boolean firstMove;
	
	/**
	 * Create new Pwan
	 * @param player Owner of the piece
	 * @param row x position of the piece
	 * @param col x position of the piece
	 * @param board Board containing this piece
	 * @param first True if the piece has not moved
	 */
    public Pawn(boolean player,int row, int col, Board board, boolean first){
        super(player, row, col, board);
        this.firstMove = first;
    }
    
    /**
     * Check if the piece has not moved
     * @return Boolean if the piece has not moved
     */
    public boolean isFirstMove() {
    	return this.firstMove;
    }
    
    /**
     * check can move to (i,j)
     */
    public boolean canMove(int i, int j) {
    	if(basicCheck(i,j) == false) return false;
    	
    	int vertiDist = j - this.getCol();
    	int horiDist = Math.abs(i-this.getRow());
    	if(horiDist>=2) {
    		return false;
    	} else if(horiDist == 1){
    		if(this.getPlayer()==false) {
        		if(vertiDist != 1) return false;
    		} else {
    			if(vertiDist != -1) return false;
    		}
    		
        	Piece targetPiece = this.getBoard().getPiece(i, j);
        	if(targetPiece == null) {
        		return false;
        	} else {
        		if(targetPiece.getPlayer() == this.getPlayer()) {
        			return false;
        		} else {
        			return true;
        		}
        	}    		
    	} else {
    		if(this.getPlayer()==false) {
        		if(vertiDist > 2 || vertiDist <= 0) {
        			return false;
        		} else if(vertiDist == 2){
        			if(this.isFirstMove() == false) return false;
        			if(this.getBoard().getPiece(i, j-1) != null) return false;
        			if(this.getBoard().getPiece(i, j) != null) return false;
        			return true;
        		} else {
        			if(this.getBoard().getPiece(i, j) != null) return false;
        			return true;
        		}
    		} else {
        		if(vertiDist < -2 || vertiDist >= 0) {
        			return false;
        		} else if(vertiDist == -2){
        			if(this.isFirstMove() == false) return false;
        			if(this.getBoard().getPiece(i, j+1) != null) return false;
        			if(this.getBoard().getPiece(i, j) != null) return false;
        			return true;
        		} else {
        			if(this.getBoard().getPiece(i, j) != null) return false;
        			return true;
        		}
    		}
    	}

    }
    
    /**
     * Get Piece name
     */
    public String getPieceName() {
    	String retval = "Pawn_";
    	if(this.getPlayer()==false) retval += "white";
    	else retval += "black";
    	
    	return retval;
    }
    
}