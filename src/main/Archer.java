package main;

/**
 * Archer is a the archer piece
 * 
 * @file Archer.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public class Archer extends Piece{
	
	/**
	 * Create new Archer
	 * @param player Owner of the piece
	 * @param row x position of the piece
	 * @param col x position of the piece
	 * @param board Board containing this piece
	 */
    public Archer(boolean player,int row, int col, Board board){
        super(player, row, col, board);
    }
    
    /**
     * Check if this piece can move to target position or capture piece at target position
     */
    public boolean canMove(int i, int j) {
    	if(!basicCheck(i,j)) return false;
    	
    	if((Math.abs(this.getCol() - j) == 2) && (Math.abs(this.getRow() - i) <= 2) || 
    		(Math.abs(this.getCol() - j) <= 2) && (Math.abs(this.getRow() - i) == 2)) {
    		Piece tarPiece = this.getBoard().getPiece(i, j);
    		if(tarPiece == null) return false;
    		return tarPiece.getPlayer()!=this.getPlayer();
    	}

    	if(!(Math.abs(i - this.getRow()) > 1 || Math.abs(j - this.getCol()) > 1)) return (this.getBoard().getPiece(i, j) == null);
		
    	return false;
	}
    
    /**
     * Get Piece name
     */
    public String getPieceName() {
    	String retval = "Archer_";
    	if(this.getPlayer()==false) retval += "white";
    	else retval += "black";
    	
    	return retval;
    }

}