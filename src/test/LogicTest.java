package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import main.*;

public class LogicTest {
	Logic logic;
    
    @Test
    public void correctPosTest() {
    	logic = new Logic(true);
    	assertEquals(logic.getBoard().getPiece(0, 0) instanceof Rook, true);
    	assertEquals(logic.getBoard().getPiece(1, 0) instanceof Knight, true);
    	assertEquals(logic.getBoard().getPiece(2, 0) instanceof Bishop, true);
    	assertEquals(logic.getBoard().getPiece(3, 0) instanceof Queen, true);
    	assertEquals(logic.getBoard().getPiece(4, 0) instanceof King, true);
    	assertEquals(logic.getBoard().getPiece(5, 0) instanceof Bishop, true);
    	assertEquals(logic.getBoard().getPiece(6, 0) instanceof Knight, true);
    	assertEquals(logic.getBoard().getPiece(7, 0) instanceof Rook, true);
    	
    	assertEquals(logic.getBoard().getPiece(0, 7) instanceof Rook, true);
    	assertEquals(logic.getBoard().getPiece(1, 7) instanceof Knight, true);
    	assertEquals(logic.getBoard().getPiece(2, 7) instanceof Bishop, true);
    	assertEquals(logic.getBoard().getPiece(3, 7) instanceof Queen, true);
    	assertEquals(logic.getBoard().getPiece(4, 7) instanceof King, true);
    	assertEquals(logic.getBoard().getPiece(5, 7) instanceof Bishop, true);
    	assertEquals(logic.getBoard().getPiece(6, 7) instanceof Knight, true);
    	assertEquals(logic.getBoard().getPiece(7, 7) instanceof Rook, true);
    	for(int i = 0; i < 8; i ++) {
    		assertEquals(logic.getBoard().getPiece(i, 1) instanceof Pawn, true);
    		assertEquals(logic.getBoard().getPiece(i, 6) instanceof Pawn, true);
    	}
    	
    	for(int j = 0; j < 2; j ++) {
    		for(int i = 0; i < 8; i ++) {
        		assertEquals(logic.getBoard().getPiece(i, j).getPlayer(), false);
    		}
    	}
    	
    	for(int j = 6; j < 8; j ++) {
    		for(int i = 0; i < 8; i ++) {
        		assertEquals(logic.getBoard().getPiece(i, j).getPlayer(), true);
    		}
    	}
    	
    	for(int j = 3; j < 6; j ++) {
    		for(int i = 0; i < 8; i ++) {
        		assertEquals(logic.getBoard().getPiece(i, j), null);
    		}
    	}
    	
    }
    
    @Test
    public void customGameTest() {
    	logic = new Logic(false);
    	assertEquals(logic.getBoard().getPiece(0, 0) instanceof Rook, true);
    	assertEquals(logic.getBoard().getPiece(1, 0) instanceof Archer, true);
    	assertEquals(logic.getBoard().getPiece(2, 0) instanceof Cannon, true);
    	assertEquals(logic.getBoard().getPiece(3, 0) instanceof Queen, true);
    	assertEquals(logic.getBoard().getPiece(4, 0) instanceof King, true);
    	assertEquals(logic.getBoard().getPiece(5, 0) instanceof Cannon, true);
    	assertEquals(logic.getBoard().getPiece(6, 0) instanceof Archer, true);
    	assertEquals(logic.getBoard().getPiece(7, 0) instanceof Rook, true);
    	
    	assertEquals(logic.getBoard().getPiece(0, 7) instanceof Rook, true);
    	assertEquals(logic.getBoard().getPiece(1, 7) instanceof Archer, true);
    	assertEquals(logic.getBoard().getPiece(2, 7) instanceof Cannon, true);
    	assertEquals(logic.getBoard().getPiece(3, 7) instanceof Queen, true);
    	assertEquals(logic.getBoard().getPiece(4, 7) instanceof King, true);
    	assertEquals(logic.getBoard().getPiece(5, 7) instanceof Cannon, true);
    	assertEquals(logic.getBoard().getPiece(6, 7) instanceof Archer, true);
    	assertEquals(logic.getBoard().getPiece(7, 7) instanceof Rook, true);
    	for(int i = 0; i < 8; i ++) {
    		assertEquals(logic.getBoard().getPiece(i, 1) instanceof Pawn, true);
    		assertEquals(logic.getBoard().getPiece(i, 6) instanceof Pawn, true);
    	}
    	
    	for(int j = 0; j < 2; j ++) {
    		for(int i = 0; i < 8; i ++) {
        		assertEquals(logic.getBoard().getPiece(i, j).getPlayer(), false);
    		}
    	}
    	
    	for(int j = 6; j < 8; j ++) {
    		for(int i = 0; i < 8; i ++) {
        		assertEquals(logic.getBoard().getPiece(i, j).getPlayer(), true);
    		}
    	}
    	
    	for(int j = 3; j < 6; j ++) {
    		for(int i = 0; i < 8; i ++) {
        		assertEquals(logic.getBoard().getPiece(i, j), null);
    		}
    	}
    	
    	assertEquals(logic.getRecord().size(), 0);
    	
    }
    
    
}