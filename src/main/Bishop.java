package main;

/**
 * Bishop is a the archer piece
 * 
 * @file Bishop.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public class Bishop extends Piece{
	
	/**
	 * Create new Bishop
	 * @param player Owner of the piece
	 * @param row x position of the piece
	 * @param col x position of the piece
	 * @param board Board containing this piece
	 */
    public Bishop(boolean player,int row, int col, Board board){
        super(player, row, col, board);
    }
    
    /**
     * Check if this piece can move to target position or capture piece at target position
     */
    public boolean canMove(int i, int j) {
		return	basicCheck(i,j) && diagonal(i,j) && noLeap(i, j) && validPos(i, j);
    }
    
    /**
     * Get Piece name
     */
    public String getPieceName() {
    	String retval = "Bishop_";
    	if(this.getPlayer()==false) retval += "white";
    	else retval += "black";
    	
    	return retval;
    }

}
