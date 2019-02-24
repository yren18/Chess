package test;

import main.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArcherTest {
    Board board;
    
    @Test
    public void testCanMove() {
        board = new Board();
        Archer a1 = new Archer(false, 3, 3, board);
        Archer a2 = new Archer(true, 3, 3, board);
        Pawn p1 = new Pawn(true, 1, 1, board, false);
        Pawn p2 = new Pawn(true, 2, 1, board, false);
        Pawn p3 = new Pawn(true, 3, 1, board, false);
        Pawn p4 = new Pawn(true, 4, 1, board, false);
        Pawn p5 = new Pawn(true, 5, 1, board, false);
        Pawn p6 = new Pawn(true, 5, 2, board, false);
        Pawn p7 = new Pawn(true, 5, 3, board, false);
        Pawn p8 = new Pawn(true, 5, 4, board, false);
        Pawn p9 = new Pawn(true, 5, 5, board, false);
        
        Queen q1 = new Queen(true, 2, 2, board);
        Queen q2 = new Queen(true, 3, 2, board);
        Queen q3 = new Queen(true, 4, 2, board);
        Queen q4 = new Queen(true, 4, 3, board);
        Queen q5 = new Queen(true, 4, 4, board);
        
        Rook r1 = new Rook(true, 1, 6, board);
        Rook r2 = new Rook(true, 0, 1, board);

        board.addPiece(a1);
        assertEquals(a1.getPieceName(), "Archer_white");
        assertEquals(a2.getPieceName(), "Archer_black");
        board.addPiece(p1);
        board.addPiece(p2);
        board.addPiece(p3);
        board.addPiece(p4);
        board.addPiece(p5);
        board.addPiece(p6);
        board.addPiece(p7);
        board.addPiece(p8);
        board.addPiece(p9);
        
        board.addPiece(q1);
        board.addPiece(q2);
        board.addPiece(q3);
        board.addPiece(q4);
        board.addPiece(q5);
        
        board.addPiece(r1);
        board.addPiece(r2);
         
        assertTrue(a1.equals(board.getPiece(3, 3)));
        assertEquals(a1.canMove(-1, 7),false); 			
        assertEquals(a1.canMove(20, 8),false); 			
        assertEquals(a1.canMove(0, 20),false); 			
        assertEquals(a1.canMove(0, -1),false); 			
        assertEquals(a1.canMove(3, 3),false); 			
        assertEquals(a1.canMove(1, 1), true);
        assertEquals(a1.canMove(2, 1), true);
        assertEquals(a1.canMove(3, 1), true);
        assertEquals(a1.canMove(4, 1), true);
        assertEquals(a1.canMove(5, 1), true);
        assertEquals(a1.canMove(5, 2), true);
        assertEquals(a1.canMove(5, 3), true);
        assertEquals(a1.canMove(5, 4), true);
        assertEquals(a1.canMove(5, 5), true);
        
        assertEquals(a1.canMove(1, 2), false);
        assertEquals(a1.canMove(1, 3), false);
        assertEquals(a1.canMove(1, 4), false);
        assertEquals(a1.canMove(1, 5), false);
        assertEquals(a1.canMove(2, 5), false);
        assertEquals(a1.canMove(3, 5), false);
        assertEquals(a1.canMove(4, 5), false);
        
        assertEquals(a1.canMove(2, 2), false);
        assertEquals(a1.canMove(3, 2), false);
        assertEquals(a1.canMove(4, 2), false);
        assertEquals(a1.canMove(4, 3), false);
        assertEquals(a1.canMove(4, 4), false);
        
        assertEquals(a1.canMove(2, 3), true);
        assertEquals(a1.canMove(2, 4), true);
        assertEquals(a1.canMove(3, 4), true);        
        
        assertEquals(a1.canMove(1, 6), false);
        assertEquals(a1.canMove(0, 5), false);
        assertEquals(a1.canMove(0, 6), false);
        assertEquals(a1.canMove(0, 1), false);
        assertEquals(a1.canMove(1, 0), false);
        
        assertEquals(board.movePiece(2,3,a1),true);
    }
}
