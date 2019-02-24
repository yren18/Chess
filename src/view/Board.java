package view;

import javax.swing.*;
import java.awt.*;
import java.util.*;


/**
 * Board is a JPanel that works as a chess board
 * 
 * @file Board.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public class Board {
    private JPanel boardPanel = new JPanel();
    private Map<String, Cell> cellMap = new HashMap<String, Cell>();
    private static Board board = new Board();

    /**
     * Create new empty board
     */
    public Board() {
        boardPanel.setLayout(new GridBagLayout());
        
        setEmptyPiece();
        for (int i = 0; i < 8; i++) {
        	setColLabel(i);
        	setRowLabel(i);
        }
    }
    
    /**
     * Initialize empty cells
     */
    public void setEmptyPiece() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

            	Cell cell = new Cell(i, j);	
                cellMap.put(cell.getPos(), cell);
                
                c.gridx = j + 1;
                c.gridy = i + 1;
                boardPanel.add(cell.getButton(), c);
            }
        }
    }
    
    /**
     * Add ith-column label 
     * 
     * @param i the column to add label
     */
    public void setColLabel(int i) {
        JLabel label;
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridy = i + 1;
        
        c.gridx = 0;
    	label = new JLabel(Character.toString((char)(56 - i)), SwingConstants.CENTER);
        label.setBorder(null);
        boardPanel.add(label, c);
        
        c.gridx = 9;
        label = new JLabel();
        label.setBorder(null);
        boardPanel.add(label, c);
        
    }

    /**
     * Add jth-row label 
     * 
     * @param j the row to add label
     */
    public void setRowLabel(int j) {
    	JLabel label;
    	GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = j + 1;
        
        c.gridy = 9;
    	label = new JLabel(Character.toString((char)(65 + j)), SwingConstants.CENTER);
        label.setBorder(null);
        boardPanel.add(label, c);
        
        c.gridy = 0;
    	label = new JLabel();
        label.setBorder(null);
        boardPanel.add(label, c);
    }

    /**
     * Get JPanel
     * 
     * @return JPanel
     */
    public JPanel getPanel() {
        return boardPanel;
    }

    /**
     * Get cell at target position
     * 
     * @param pos Position(e.g: A1, B7)
     * @return Cell Cell at target position
     */
    public Cell getCell(String pos) {
        return cellMap.get(pos);
    }
}
