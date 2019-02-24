package test;

import main.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {	
    Board board;
    
    @Test
	//player 0 bot
	//player 1 top
    
    public void testCanMove() {
        board = new Board();
        Pawn p1, p2;
        Queen q1, q2;

        p1 = new Pawn(false, 1, 1, board, true);
        Pawn p11 = new Pawn(true, 1, 1, board, true);
        new Pawn(false, 1, 1, board, true);
        assertEquals(p1.getPieceName(),"Pawn_white");
        assertEquals(p11.getPieceName(),"Pawn_black");
        
        board.addPiece(p1);
        
        assertTrue(p1.equals(board.getPiece(1, 1)));
        assertEquals(p1.canMove(1, 1),false); 			
        
        assertEquals(p1.canMove(4, 1),false);			
        assertEquals(p1.canMove(1, 4),false);			
        assertEquals(p1.canMove(1, 3),true);		
        assertEquals(p1.canMove(1, 2),true);
        assertEquals(p1.canMove(1, 0),false);			
        assertEquals(p1.canMove(2, 0),false);		
        assertEquals(p1.canMove(2, 2),false);			
        
        q1 = new Queen(true, 1, 2, board);			
        board.addPiece(q1);
        assertEquals(p1.canMove(1, 3),false);			
        assertEquals(p1.canMove(1, 2),false);
        
        board.deletePiece(q1);
        q1 = new Queen(true, 1, 3, board);				
        board.addPiece(q1);
        assertEquals(p1.canMove(1, 3),false);			
        assertEquals(p1.canMove(1, 2),true);
        
        board.deletePiece(q1);
        q1 = new Queen(true, 2, 2, board);				
        board.addPiece(q1);
        assertEquals(p1.canMove(2, 2),true);			
        q2 = new Queen(false, 0, 2, board);				
        board.addPiece(q2);
        assertEquals(p1.canMove(0, 2),false);
        board.deletePiece(q1);
        board.deletePiece(q2);

        board.deletePiece(p1);
        p1 = new Pawn(false, 1, 1, board, false);
        assertEquals(p1.canMove(1, 3),false);			
        assertEquals(p1.canMove(1, 2),true);			
        

        p2 = new Pawn(true, 1, 6, board, true);
        board.addPiece(p2);
        
        assertEquals(p2.canMove(1, 3),false);			 
        assertEquals(p2.canMove(1, 4),true);			 
        assertEquals(p2.canMove(1, 5),true);			 
        assertEquals(p2.canMove(1, 7),false);			 
        assertEquals(p2.canMove(2, 6),false);			
        assertEquals(p2.canMove(0, 5),false);			
        
        q1 = new Queen(true, 1, 5, board);				
        board.addPiece(q1);
        assertEquals(p2.canMove(1, 4),false);			
        assertEquals(p2.canMove(1, 5),false);
        
        board.deletePiece(q1);
        q1 = new Queen(true, 1, 4, board);				
        board.addPiece(q1);
        assertEquals(p2.canMove(1, 4),false);			
        assertEquals(p2.canMove(1, 5),true);
        
        board.deletePiece(q1);
        q1 = new Queen(false, 0, 5, board);			
        board.addPiece(q1);
        assertEquals(p2.canMove(0, 5),true);			
        q2 = new Queen(true, 2, 5, board);				
        board.addPiece(q2);
        assertEquals(p2.canMove(2, 5),false);
        board.deletePiece(q1);
        board.deletePiece(q2);
        
        board.deletePiece(p1);
        p2 = new Pawn(true, 1, 6, board, false);
        assertEquals(p2.canMove(1, 4),false);			
        assertEquals(p2.canMove(1, 5),true);	
    }
    
    @Test
    public void moveTest() {
    	board = new Board();
        King k1 = new King(false, 1, 1, board);
        Knight n1 = new Knight(false, 4, 5, board);
        King k2 = new King(true, 5, 7, board);
        Pawn p2 = new Pawn(true, 5, 6, board, true);
        
        board.addPiece(k1);
        board.addPiece(k2);
        board.addPiece(n1);
        board.addPiece(p2);
        
        assertEquals(p2.validMove(5, 5),false);
        assertEquals(p2.validMove(5, 4),false);
        assertEquals(p2.validMove(4, 5),true);
        
        assertEquals(board.movePiece(4,5,p2),true);
        assertEquals(board.getPlayer0().size(), 1);
        assertEquals(board.getPlayer1().size(), 2);
    }
}