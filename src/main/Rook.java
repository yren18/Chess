package main;

/**
 * Rook is the rook class
 * @file Rook.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public class Rook extends Piece{
	/**
	 * Create new Rook
	 * @param player Owner of the piece
	 * @param row x position of the piece
	 * @param col x position of the piece
	 * @param board Board containing this piece
	 */
    public Rook(boolean player,int row, int col, Board board){
        super(player, row, col, board);
    }
    
    /**
     * Check if this piece can move to target position or capture piece at target position
     */
    public boolean canMove(int i, int j) {
    	return basicCheck(i,j) && straight(i,j) && noLeap(i, j) && validPos(i, j);
    }
    
    /**
     * Get Piece name
     */
    public String getPieceName() {
    	String retval = "Rook_";
    	if(this.getPlayer()==false) retval += "white";
    	else retval += "black";
    	
    	return retval;
    }
}