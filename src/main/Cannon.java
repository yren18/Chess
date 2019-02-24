package main;


/**
 * Cannon is a the cannon piece
 * 
 * @file Cannon.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public class Cannon extends Piece{
	
	/**
	 * Create new Cannon
	 * @param player Owner of the piece
	 * @param row x position of the piece
	 * @param col x position of the piece
	 * @param board Board containing this piece
	 */
    public Cannon(boolean player,int row, int col, Board board){
        super(player, row, col, board);
    }
    
    /**
     * Check if this piece can move to target position or capture piece at target position
     */
    public boolean canMove(int i, int j) {
    	if(!basicCheck(i,j)) return false;
    	if((this.getBoard().getPiece(i, j) != null) && (this.getBoard().getPiece(i,j).getPlayer() == this.getPlayer())){
    		return false;
    	}
    	if(straight(i,j)) {
    		Piece tarPiece = this.getBoard().getPiece(i, j);
    		if(noLeap(i,j)) {
    			return tarPiece == null;
    		} else {
    			if(tarPiece == null) return false;
    			
    	    	if(sameRow(i, j)) {
    	    		if(Math.abs(j - this.getCol()) != 1) {
    	    			boolean foundSimp = false;
    	        		for(int k = Math.min(j, this.getCol()) + 1; k < Math.max(j, this.getCol()); k++) {
    	        			if(this.getBoard().getPiece(i, k) != null) {
    	        				if(foundSimp == false) foundSimp = true; 
    	        				else return false;
    	        			} 
    	        		}
    	        		return foundSimp == true;		
    	    		} else return false;
    	    	} else if(sameCol(i, j)) {
    	    		if(Math.abs(i - this.getRow()) != 1) {
    	    			boolean foundSimp = false;
    	        		for(int k = Math.min(i, this.getRow()) + 1; k < Math.max(i, this.getRow()); k++) {
    	        			if(this.getBoard().getPiece(k, j) != null) {
    	        				if(foundSimp == false) foundSimp = true; 
    	        				else return false;
    	        			} 
    	        		}
    	        		return foundSimp == true;		
    	    		} else return false;
    	    	}
    		}
    	}
    	return false;
	}
    
    /**
     * Get Piece name
     */
    public String getPieceName() {
    	String retval = "Cannon_";
    	if(this.getPlayer()==false) retval += "white";
    	else retval += "black";
    	
    	return retval;
    }
}
