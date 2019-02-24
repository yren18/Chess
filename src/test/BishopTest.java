package test;

import main.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopTest {
    Board board;
    
    @Test
    public void testCanMove() {
        board = new Board();
        Bishop b1 = new Bishop(true, 3, 3, board);
        Bishop b2 = new Bishop(false, 0, 7, board);
        assertEquals(b1.getPieceName(),"Bishop_black");
        assertEquals(b2.getPieceName(),"Bishop_white");
        Queen q1 = new Queen(true, 2, 4, board);
        Queen q2 = new Queen(false, 5, 1, board);
        
        board.addPiece(b1);
        board.addPiece(b2);
        
        assertTrue(b1.equals(board.getPiece(3, 3)));  			
        assertTrue(b2.equals(board.getPiece(0, 7)));
        assertEquals(b1.canMove(8, 8),false); 			
        assertEquals(b1.canMove(3, 3),false); 			
        assertEquals(b1.canMove(2, 2),true); 			
        assertEquals(b1.canMove(6, 0),true);
        
        board.addPiece(q1);						
        assertEquals(b1.canMove(2, 4),false);		
        board.addPiece(q2);
        assertEquals(b1.canMove(6, 0),false);
        assertEquals(b1.canMove(5, 1),true);		
    }
    
    @Test
    public void moveTest() {
    	board = new Board();
        King k1 = new King(false, 0, 0, board);
        Bishop b1 = new Bishop(false, 1, 1, board);
        King k2 = new King(true, 7, 7, board);
        Bishop b2 = new Bishop(true, 6, 6, board);
        
        board.addPiece(k1);
        board.addPiece(k2);
        board.addPiece(b1);
        board.addPiece(b2);
        
        
        assertEquals(b1.validMove(3, 3),true);
        assertEquals(b1.validMove(3, 1),false);
        assertEquals(board.movePiece(5,5,b1),true);
        assertEquals(board.getPlayer0().size(), 2);
        assertEquals(board.getPlayer1().size(), 2);
    }
    
    @Test
    public void noLeapTest() {
    	board = new Board();
    	Bishop b1 = new Bishop(false, 1, 1, board);
    	assertEquals(b1.noLeap(5, 4),true);
    }
}