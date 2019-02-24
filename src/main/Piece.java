package main;

import java.util.Vector;

/**
 * Super piece class
 * 
 * @file Piece.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public abstract class Piece {
    /**
     * owner of piece
     */
	private boolean player;
	
    /**
     * x position of the piece
     */
	private int row;
	
    /**
     * y position of the piece
     */
	private int col;
	
    /**
     * board containing this piece
     */
	private Board board;
	
	/**
	 * Create create a new piece
	 * @param player owner of the piece
	 * @param row x position of the piece
	 * @param col y position of the piece
	 * @param board board containing the piece
	 */
    public Piece(boolean player, int row, int col, Board board){
        this.player = player;
        this.row = row;
        this.col = col;
        this.board = board;
    }
    
    /**
     * Abstract function
     * Check if this piece can move to target position without considering being checked
     * @param i x position of the piece
     * @param j y position of the piece
     * @return boolean A boolean that is true if this piece can move to target position without considering being checked
     */
    public abstract boolean canMove(int i, int j);
    
    /**
     * Abstract function
     * Check if this piece can move to target position, considering being checked
     * @param i x position of the piece
     * @param j y position of the piece
     * @return boolean A boolean that is true if this piece can move to target position, considering being checked
     */
    public boolean validMove(int i, int j) {
        return canMove(i, j) && !this.isMate(i,j);
    }
    
	/**
	 * Get board containing this piece
	 * @return Board Board containing this player
	 */
    public Board getBoard() {
        return board;
    }

	/**
	 * Get x position of this piece
	 * @return int x position of this piece
	 */
    public int getRow() {
        return row;
    }
    
	/**
	 * Get y position of this piece
	 * @return int y position of this piece
	 */
    public int getCol() {
        return col;
    }
    
	/**
	 * Get Owner of this piece
	 * @return boolean Owner of this piece
	 */
    public boolean getPlayer() {
        return player;
    }
    
    /**
     * Check if target position is in the board
     * 
     * @param i x position of target position
     * @param j y position of target position
     * @return boolean A boolean that is true if target position is in the board
     */
    public boolean inBoard(int i, int j) {
		if(i > 7 || i < 0 || j > 7 || j < 0) return false;
		return true;
    }
    
    /**
     * Check if target position is the same as current position
     * 
     * @param i x position of target position
     * @param j y position of target position
     * @return boolean A boolean that is true if target position is the same as current position
     */
    public boolean samePos(int i, int j) {
    	if(this.getRow() == i && this.getCol() == j) return true;
    	return false;
    }    
   
    /**
     * Check if target position is the same as current position and if target position is the same as current position
     * 
     * @param i x position of target position
     * @param j y position of target position
     * @return boolean A boolean that is true if target position is in the board and target position is not the same as current position
     */
    public boolean basicCheck(int i, int j) {
    	return !(!inBoard(i,j)||samePos(i,j));
    }
    
    /**
     * Check if target position can be occupied by this piece, considering capture
     * @param i x position of target position
     * @param j y position of target position
     * @return boolean A boolean that is true if target position can be occupied by this piece
     */
    public boolean validPos(int i, int j) {
    	Piece targetPiece = this.getBoard().getPiece(i, j);
    	if(targetPiece == null) {
    		return true;
    	} else {
    		if(targetPiece.getPlayer() == this.getPlayer()) return false;
    		return true;
    	}
    }
    
    /**
     * Check if target position is at the same row with position of this piece
     * @param i x position of target position
     * @param j y position of target position
     * @return boolean A boolean that is true if target position is at the same row or column with this piece
     */
    public boolean sameRow(int i, int j) {
    	if(this.getRow() == i) return true;
    	return false;
    }
    
    /**
     * Check if target position is at the same column with position of this piece
     * @param i x position of target position
     * @param j y position of target position
     * @return boolean A boolean that is true if target position is at the same row or column with this piece
     */
    public boolean sameCol(int i, int j) {
    	if(this.getCol() == j) return true;
    	return false;
    }
    
    /**
     * Check if target position is at the same row or column with position of this piece
     * @param i x position of target position
     * @param j y position of target position
     * @return boolean A boolean that is true if target position is at the same row or column with this piece
     */
    public boolean straight(int i, int j) {
    	if(sameRow(i,j) || sameCol(i,j)) return true;
    	return false;
    }
    
    /**
     * Check if target position and position of this piece is on a diagonal line
     * @param i x position of target position
     * @param j y position of target position
     * @return boolean A boolean that is true if target position is at the same row or column with this piece
     */
    public boolean diagonal(int i, int j) {
    	if(Math.abs(i - this.getRow()) == Math.abs(j - this.getCol())) return true;
    	return false;
    }
    
    /**
     * Check if there is no piece between target position and position of this piece straightly or diagonally
     * @param i x position of target position
     * @param j y position of target position
     * @return boolean A boolean that is true if there is no piece between target position and position of this piece straightly or diagonally
     */
    public boolean noLeap(int i, int j) {
    	if(sameRow(i, j)) {
    		if(Math.abs(j - this.getCol()) != 1) {
        		for(int k = Math.min(j, this.getCol()) + 1; k < Math.max(j, this.getCol()); k++) 
        			if(this.getBoard().getPiece(i, k) != null) return false;  			
    		} else return true;
    	} else if(sameCol(i, j)) {
    		if(Math.abs(i - this.getRow()) != 1) {
        		for(int k = Math.min(i, this.getRow()) + 1; k < Math.max(i, this.getRow()); k++) 
        			if(this.getBoard().getPiece(k, j) != null) return false;
    		} else return true;
    	} else if(diagonal(i, j)) {
    		if(Math.abs(i - this.getRow()) != 1) {
    			int curRow = this.getRow();
    			int curCol = this.getCol();
        		for(int k = 1; k < Math.abs(i - this.getRow()); k++) 
        			if(this.getBoard().getPiece(curRow + k*Integer.signum(i-curRow), curCol + k*Integer.signum(j-curCol)) != null) return false;
    		} else return true;
    	}
    	
    	return true;
    }
    
    /**
     * Create a new piece with given parameters
     * 
     * @param i x position of new piece
     * @param j y position of new piece
     * @param newBoard board containing the piece
     * @param isPawn true if this piece if pawn
     * @param isFirst true if this piece is at firstMove stage
     * @return Piece New piece with given parameters
     */
    public Piece copyPiece(int i, int j, Board newBoard, boolean isPawn, boolean isFirst) {
    	if(isPawn) {
    		return new Pawn(this.getPlayer(),i,j,newBoard,((Pawn) this).isFirstMove());
    	}  
    	if (this instanceof Bishop){
        	return new Bishop(this.getPlayer(),i,j,newBoard);
        } 
    	if (this instanceof Knight){
        	return new Knight(this.getPlayer(),i,j,newBoard);
        } 
    	if (this instanceof Rook){
        	return new Rook(this.getPlayer(),i,j,newBoard);
        } 
    	if (this instanceof Queen){
        	return new Queen(this.getPlayer(),i,j,newBoard);
        } 
    	if (this instanceof King){
        	return new King(this.getPlayer(),i,j,newBoard);
        }
    	if (this instanceof Archer){
        	return new Archer(this.getPlayer(),i,j,newBoard);
        }
    	if (this instanceof Cannon){
        	return new Cannon(this.getPlayer(),i,j,newBoard);
        }
    	return null;
    };
    
    /**
     * Check if after this piece move to target position, this player is being checked or not
     * 
     * @param i target x position
     * @param j target x position
     * @return boolean A boolean that is true if after this piece move to target position, this player is being checked or not
     */
    public boolean isMate(int i, int j) {
    	Board newBoard = new Board();
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
            	Piece itPiece = this.getBoard().getPiece(r,c);
            	if(itPiece == null) continue;
            	if(itPiece instanceof Pawn) newBoard.addPiece(itPiece.copyPiece(itPiece.getRow(),itPiece.getCol(),newBoard,true,((Pawn) itPiece).isFirstMove()));
            	else newBoard.addPiece(itPiece.copyPiece(itPiece.getRow(),itPiece.getCol(),newBoard,false,false));
            }
        }
        
    	Boolean curPlayer = this.getPlayer();
    	
    	Piece newP;
    	if(this instanceof Pawn) newP = this.copyPiece(i,j,newBoard,true,false);
    	else newP = this.copyPiece(i,j,newBoard,false,false);
        
        newBoard.deletePiece(newBoard.getPiece(this.getRow(), this.getCol()));
    	newBoard.forceSetPiece(i, j, newP);
    	
		Vector<Piece> pieceArr0, pieceArr1;
		if(curPlayer == false) {
			pieceArr0 = newBoard.getPlayer0();
			pieceArr1 = newBoard.getPlayer1();
		} else {
			pieceArr0 = newBoard.getPlayer1();
			pieceArr1 = newBoard.getPlayer0();
		}
		

    	for (Piece p0 : pieceArr0) {
    	    if(p0 instanceof King) {
   	    		int kingRow = p0.getRow();
   	    		int kingCol = p0.getCol();
        	    for (Piece p1 : pieceArr1) 
    	   	    	if(p1.canMove(kingRow,kingCol)) return true;
    	   	    return false;
    	    }
    	}
    	return false;
    }	
    
    /**
     * Get Piece name
     */
    public abstract String getPieceName();
    	
    
}