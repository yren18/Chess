package main;

/**
 * Knight is the archer piece
 * 
 * @file kinght.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public class Knight extends Piece{
	/**
	 * Create new Knight
	 * @param player Owner of the piece
	 * @param row x position of the piece
	 * @param col x position of the piece
	 * @param board Board containing this piece
	 */
    public Knight(boolean player,int row, int col, Board board){
        super(player, row, col, board);
    }
    
    /**
     * Check if this piece can move to target position or capture piece at target position
     */
    public boolean canMove(int i, int j) {
    	return basicCheck(i,j) && !(Math.abs(i - this.getRow())*Math.abs(j - this.getCol()) != 2) && validPos(i, j);
    }
    
    /**
     * Get Piece name
     */
    public String getPieceName() {
    	String retval = "Knight_";
    	if(this.getPlayer()==false) retval += "white";
    	else retval += "black";
    	
    	return retval;
    }
}