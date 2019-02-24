package test;

import main.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightTest {
    Board board;
    
    @Test
    public void testCanMove() {
        board = new Board();
        Knight n1 = new Knight(true, 1, 1, board);
        Knight n2 = new Knight(false, 0, 7, board);
        Queen q1 = new Queen(true, 2, 3, board);
        Queen q2 = new Queen(false, 6, 3, board);
        
        assertEquals(n1.getPieceName(),"Knight_black");
        assertEquals(n2.getPieceName(),"Knight_white");
        
        board.addPiece(n1);
        board.addPiece(n2);
        
        assertTrue(n1.equals(board.getPiece(1, 1)));
        assertTrue(n2.equals(board.getPiece(0, 7)));	
        assertEquals(n1.canMove(-1, 2),false); 		
        assertEquals(n1.canMove(2, 2),false); 			
        assertEquals(n1.canMove(1, 1),false); 		
        assertEquals(n1.canMove(3, 0),true); 			 
        assertEquals(n1.canMove(2, 3),true);
        
        board.addPiece(q1);						
        assertEquals(n1.canMove(2, 3),false);			
        board.addPiece(q2);
        assertEquals(n1.canMove(3, 2),true);			
    }
    
    @Test
    public void moveTest() {
    	board = new Board();
        King k1 = new King(false, 0, 0, board);
        Knight n1 = new Knight(false, 2, 1, board);
        King k2 = new King(true, 7, 7, board);
        Queen q2 = new Queen(true, 0, 2, board);
        
        board.addPiece(k1);
        board.addPiece(k2);
        board.addPiece(n1);
        board.addPiece(q2);
        
        
        assertEquals(n1.validMove(0, 2),true);
        assertEquals(n1.validMove(1, 3),false);
        assertEquals(board.movePiece(0,2,n1),true);
        assertEquals(board.getPlayer0().size(), 2);
        assertEquals(board.getPlayer1().size(), 1);
    }
}