package test;

import main.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CannonTest {
    Board board;
    
    @Test
    public void testCanMove() {
        board = new Board();
        

        Cannon c1 = new Cannon(false, 3, 3, board);
        Cannon c2 = new Cannon(true, 3, 3, board);
        assertEquals(c1.getPieceName(),"Cannon_white");
        assertEquals(c2.getPieceName(),"Cannon_black");
        
        Pawn p1 = new Pawn(true, 3, 0, board, false);
        Pawn p2 = new Pawn(true, 3, 1, board, false);
        Pawn p3 = new Pawn(true, 3, 2, board, false);
        Pawn p4 = new Pawn(true, 3, 5, board, false);
        Pawn p5 = new Pawn(true, 4, 3, board, false);
        Pawn p6 = new Pawn(true, 6, 3, board, false);
        
        board.addPiece(c1);
        board.addPiece(p1);
        board.addPiece(p2);
        board.addPiece(p3);
        board.addPiece(p4); 
        board.addPiece(p5); 
        board.addPiece(p6); 

        assertEquals(c1.canMove(-1, 7),false); 			
        assertEquals(c1.canMove(20, 8),false); 			
        assertEquals(c1.canMove(0, 20),false); 			
        assertEquals(c1.canMove(0, -1),false); 			
        assertEquals(c1.canMove(3, 3),false); 			
        
    	///7 # # # # # # # # 
    	///6 # # # # # # # # 
    	///5 # # # A # # # # 
    	///4 # # # # # # # # 
    	///3 # # # C A # A #
    	///2 # # # A # # # # 
    	///1 # # # A # # # # 
    	///0 # # # A # # # # 
        ///	 0 1 2 3 4 5 6 7
        
        assertEquals(c1.canMove(3, 0),false); 
        assertEquals(c1.canMove(3, 1),true); 
        assertEquals(c1.canMove(3, 2),false); 
        assertEquals(c1.canMove(3, 4),true); 
        assertEquals(c1.canMove(3, 5),false); 
        assertEquals(c1.canMove(3, 6),false); 
        assertEquals(c1.canMove(3, 7),false);
        
        assertEquals(c1.canMove(0, 3),true);
        assertEquals(c1.canMove(1, 3),true);
        assertEquals(c1.canMove(2, 3),true);
        assertEquals(c1.canMove(4, 3),false);
        assertEquals(c1.canMove(5, 3),false);
        assertEquals(c1.canMove(6, 3),true);
        assertEquals(c1.canMove(7, 3),false);
        
        assertEquals(c1.canMove(7, 7),false);
        assertEquals(c1.canMove(0, 1),false);
        assertEquals(board.canMove(6, 3,c1),true);
        assertEquals(board.movePiece(6, 3,c1),true);
        
        board = new Board();
        Cannon c3 = new Cannon(false, 3, 3, board);
        Pawn p11 = new Pawn(false, 3, 4, board, false);
        board.addPiece(c2);
        board.addPiece(p11);
        assertEquals(board.canMove(3, 4, c3), false);
        
    }
    
    
}
