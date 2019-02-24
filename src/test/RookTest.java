package test;

import main.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookTest {
    Board board;
    
    @Test
    public void testCanMove() {
        board = new Board();
        Rook r1 = new Rook(true, 3, 3, board);
        Rook r2 = new Rook(false, 0, 7, board);
        Queen q1 = new Queen(true, 3, 6, board);
        Queen q2 = new Queen(false, 6, 3, board);
        
        assertEquals(r1.getPieceName(),"Rook_black");
        assertEquals(r2.getPieceName(),"Rook_white");
        
        board.addPiece(r1);
        board.addPiece(r2);
        
        assertTrue(r1.equals(board.getPiece(3, 3)));  			
        assertTrue(r2.equals(board.getPiece(0, 7)));	
        assertEquals(r1.canMove(8, 3),false); 		
        assertEquals(r1.canMove(3, 3),false); 			
        assertEquals(r1.canMove(2, 2),false);			
        assertEquals(r1.canMove(3, 7),true); 			
        assertEquals(r1.canMove(3, 2),true);
        assertEquals(r1.canMove(0, 3),true);
        assertEquals(r1.canMove(6, 3),true);
        
        
        board.addPiece(q1);						
        assertEquals(r1.canMove(3, 7),false);	
        assertEquals(r1.canMove(3, 6),false);			
		
        
        board.addPiece(q2);
        assertEquals(r1.canMove(7, 3),false);
        assertEquals(r1.canMove(6, 3),true);			
    }
    
    @Test
    public void moveTest() {
    	board = new Board();
        King k1 = new King(false, 1, 1, board);
        Rook r1 = new Rook(false, 1, 2, board);
        King k2 = new King(true, 1, 6, board);
        Rook r2 = new Rook(true, 1, 5, board);
        
        board.addPiece(k1);
        board.addPiece(k2);
        board.addPiece(r1);
        board.addPiece(r2);
        
        assertEquals(k1.validMove(0, 2),true);
        assertEquals(r1.validMove(0, 2),false);
        assertEquals(board.movePiece(1,5,r1),true);
        assertEquals(board.getPlayer0().size(), 2);
        assertEquals(board.getPlayer1().size(), 1);
        assertEquals(board.movePiece(0,0,r1),false);
    }
}