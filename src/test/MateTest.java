package test;

import main.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MateTest {
    Board board;
    
    @Test
    public void test() {
        board = new Board();
        King k1,k2;
        k1 = new King(false, 0, 0, board);
        k2 = new King(true, 2, 1, board);
        board.addPiece(k1);
        board.addPiece(k2);
        
        assertEquals(k1.isMate(1, 0),true);
        assertEquals(board.getPlayer0().size(),1);
        assertEquals(board.getPlayer1().size(),1);
        assertEquals(k1.isMate(1, 1),true);        
        assertEquals(board.getPlayer0().size(),1);
        assertEquals(board.getPlayer1().size(),1);
        assertEquals(k1.isMate(0, 1),false);
        assertEquals(board.getPlayer0().size(),1);
        assertEquals(board.getPlayer1().size(),1);
        assertEquals(k1.validMove(1, 0),false);
        assertEquals(board.getPlayer0().size(),1);
        assertEquals(board.getPlayer1().size(),1);
        assertEquals(k1.validMove(1, 1),false);
        assertEquals(board.getPlayer0().size(),1);
        assertEquals(board.getPlayer1().size(),1);
        assertEquals(k1.validMove(0, 1),true);
        assertEquals(board.getPlayer0().size(),1);
        assertEquals(board.getPlayer1().size(),1);
        assertEquals(board.movePiece(0,1,k1),true);
        assertEquals(board.getPlayer0().size(),1);
        assertEquals(board.getPlayer1().size(),1);
        assertEquals(board.getPiece(0, 0),null);
        assertEquals(board.getPiece(1, 0),null);
        assertEquals(board.getPiece(1, 1),null);
        assertEquals(board.getPiece(2, 0),null);
        
        board = new Board();
        k1 = new King(false, 7, 0, board);
        k2 = new King(true, 7, 2, board);
        Bishop b1,b2;
        b1 = new Bishop(true, 5, 2, board);
        b2 = new Bishop(true, 4, 2, board);
        board.addPiece(k1);
        board.addPiece(k2);
        board.addPiece(b1);
        board.addPiece(b2);
        assertEquals(k1.validMove(7, 0),false);
        assertEquals(k1.validMove(7, 1),false);
        assertEquals(k1.validMove(6, 0),false);
        assertEquals(k1.validMove(6, 1),false);
        assertEquals(board.addPiece(7,0,null),false);
        assertEquals(board.canMove(1,2,null),false);
    }
    
    @Test
    public void checkMateTest() {
    	board = new Board();
        King k1,k2;
        Bishop b1, b2;
        Rook r2_0, r2_1;

        k1 = new King(false, 0, 0, board);
        b1 = new Bishop(false, 0, 1, board);
        k2 = new King(true, 7, 7, board);
        b2 = new Bishop(true, 6, 6, board);
        r2_0 = new Rook(true, 0, 7, board);
        r2_1 = new Rook(true, 7, 0, board);
        
        board.addPiece(k1);
        board.addPiece(b1);
        board.addPiece(k2);
        board.addPiece(b2);
        board.addPiece(r2_0);
        board.addPiece(r2_1);
    
        assertEquals(board.checkMate(false),true);      
        board.deletePiece(b2);
        assertEquals(board.checkMate(false),false);
    }
    
    @Test
    public void staleMateTest() {
    	board = new Board();
        King k1,k2;
        Bishop b1_0, b1_1;
        Rook r2_0, r2_1;

        k1 = new King(true, 0, 0, board);
        b1_0 = new Bishop(true, 0, 1, board);
        b1_1 = new Bishop(true, 1, 0, board);
        k2 = new King(false, 2, 2, board);
        r2_0 = new Rook(false, 0, 7, board);
        r2_1 = new Rook(false, 7, 0, board);
        
        board.addPiece(k1);
        board.addPiece(b1_0);
        board.addPiece(b1_1);
        board.addPiece(k2);
        board.addPiece(r2_0);
        board.addPiece(r2_1);
        
        assertEquals(board.staleMate(true),true);      
        board.movePiece(3, 3, k2);
        assertEquals(board.staleMate(true),false); 
    }
    
}