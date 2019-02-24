package test;

import main.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingTest {
    Board board;
    
    @Test
    public void testCanMove() {
        board = new Board();
        King k1 = new King(true, 3, 3, board);
        King k2 = new King(false, 0, 7, board);
        Queen q1 = new Queen(true, 3, 4, board); 
        Queen q2 = new Queen(false, 2, 1, board);
        
        assertEquals(k1.getPieceName(),"King_black");
        assertEquals(k2.getPieceName(),"King_white");
        
        board.addPiece(k1);
        board.addPiece(k2);

        
        assertTrue(k1.equals(board.getPiece(3, 3)));  		
        assertTrue(k2.equals(board.getPiece(0, 7)));	
        
        assertEquals(k1.canMove(-1, 7),false); 			
        assertEquals(k1.canMove(20, 8),false); 			
        assertEquals(k1.canMove(0, 20),false); 			
        assertEquals(k1.canMove(0, -1),false); 			
        
        assertEquals(k1.canMove(3, 3),false); 			
        assertEquals(k1.canMove(2, 2),true); 			
        assertEquals(k1.canMove(5, 5),false); 			
        assertEquals(k1.canMove(2, 3),true); 			
        assertEquals(k1.canMove(5, 3),false); 			
        assertEquals(k1.canMove(3, 4),true); 			
        assertEquals(k1.canMove(3, 1),false); 	
        
        board.addPiece(q1);						
        assertEquals(k1.canMove(3, 4),false);
        board.addPiece(q2);						
        assertEquals(k1.canMove(2, 3),true);
    }

}