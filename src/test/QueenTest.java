package test;

import main.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenTest {
    Board board;
    
    @Test
    public void testCanMove() {
        board = new Board();
        Queen q1 = new Queen(true, 3, 3, board);
        Queen q2 = new Queen(false, 0, 7, board);
        Queen q3 = new Queen(true, 3, 6, board);
        Queen q4 = new Queen(false, 6, 3, board);
        assertEquals(q1.getPieceName(),"Queen_black");
        assertEquals(q2.getPieceName(),"Queen_white");
        
        
        board.addPiece(q1);
        board.addPiece(q2);
        
        assertTrue(q1.equals(board.getPiece(3, 3)));  			
        assertTrue(q2.equals(board.getPiece(0, 7)));	
        assertEquals(q1.canMove(8, 3),false); 			
        assertEquals(q1.canMove(3, 3),false); 			
        assertEquals(q1.canMove(4, 5),false); 		
        assertEquals(q1.canMove(6, 5),false); 			
        assertEquals(q1.canMove(3, 7),true); 		
        assertEquals(q1.canMove(0, 3),true);
        assertEquals(q1.canMove(4, 4),true);			

        board.addPiece(q3);						
        assertEquals(q1.canMove(3, 7),false);			
        
        board.addPiece(q4);
        assertEquals(q1.canMove(7, 3),false);
        assertEquals(q1.canMove(6, 3),true);			

        Queen q5 = new Queen(true, 2, 4, board);
        Queen q6 = new Queen(false, 5, 1, board);
        
        assertEquals(q1.canMove(8, 8),false); 			
        assertEquals(q1.canMove(3, 3),false); 			
        assertEquals(q1.canMove(2, 2),true); 			
        assertEquals(q1.canMove(6, 0),true);
        
        board.addPiece(q5);						
        assertEquals(q1.canMove(2, 4),false);		
        
        board.addPiece(q6);
        assertEquals(q1.canMove(6, 0),false);
        assertEquals(q1.canMove(5, 1),true);			

    }
    
    @Test
    public void moveTest() {
    	board = new Board();
        King k1 = new King(false, 0, 0, board);
        Queen q1 = new Queen(false, 1, 1, board);
        King k2 = new King(true, 7, 7, board);
        Queen q2 = new Queen(true, 6, 6, board);
        
        board.addPiece(k1);
        board.addPiece(k2);
        board.addPiece(q1);
        board.addPiece(q2);
        
        
        assertEquals(q1.validMove(3, 3),true);
        assertEquals(q1.validMove(3, 1),false);
        assertEquals(board.movePiece(5,5,q1),true);
        assertEquals(board.getPlayer0().size(), 2);
        assertEquals(board.getPlayer1().size(), 2);
    }
}
