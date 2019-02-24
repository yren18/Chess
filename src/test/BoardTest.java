package test;

import main.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BoardTest {
    Board board;
    
    @Test
    public void getPieceOutOfScopeTest() {
    	board = new Board();
    	assertEquals(board.getPiece(1, -1),null);
    	assertEquals(board.getPiece(1, 9),null);
    	assertEquals(board.getPiece(-9, 1),null);
    	assertEquals(board.getPiece(10, 3),null);
    }
    
    @Test
    public void allValidMoveTest() {
    	board = new Board();
    	Pawn p = new Pawn(false, 0, 0, board, false);
    	board.addPiece(p);
    	assertEquals(board.allValidMove(p).size(), 1);
    	assertEquals(board.isBeingChecked(false), false);
    	assertEquals(board.isBeingChecked(true), false);
    }
    
    @Test
    public void addPieceTest() {
    	board = new Board();
    	assertEquals(board.addPiece(null), false);
    	assertEquals(board.addPiece(0,0,null), true);
    	Pawn p1 = new Pawn(false, 0, 0, board, false);
    	Pawn p2 = new Pawn(false, 0, 0, board, false);
    	board.addPiece(p1);
    	assertEquals(board.addPiece(p2), false);
    }
    
}
