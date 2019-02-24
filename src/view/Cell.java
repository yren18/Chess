package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

/**
 * Unit representing a chess piece on the board
 * 
 * @file Cell.java
 * 
 * @author renyuxuan
 * @date 2-12-2019
 */
public class Cell {
	private int row, col;
    private JButton button;
    private String pieceName;
    private boolean selected;
	
    /**
     * Create new Cell object with image of type pieceName
     * 
     * @param row x location of the cell
     * @param col y location of the cell
     * @param pieceName Type of image
     */
    Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.pieceName = "empty";
        this.selected = false;
        
        button = new JButton(Character.toString((char)(65 + col)) + Character.toString((char)(56 - row)));
        button.setPreferredSize(new Dimension(40, 40));
        button.setFont(new Font("Dialog", Font.PLAIN, 0));
        button.setOpaque(true);
        button.setBorder(new LineBorder(Color.BLACK));;
        button.setVisible(true);
        setCellColor();
    }

    /**
     * Set cell color
     */
    public void setCellColor() {
    	if((row + col) % 2 == 0) button.setBackground(Color.decode("#eeeec0"));
    	else button.setBackground(Color.decode("#8aa568"));
    }
    
    /**
     * Get pieceName of cell (i.e : Bishop_black)
     * 
     * @return String pieceName of the cell
     */
    public String getCellPieceName() {
    	return pieceName;
    }

    /**
     * Change image of piece to be the one corresponding to piece_name
     * 
     * @param piece_name Type of image
     */
    public void setPiece(String piece_name) {
    	pieceName = piece_name;
        button.setIcon(new ImageIcon(getImage()));
    }

    /**
     * Get JButton of the cell
     * 
     * @return JButton return JButton of the cell
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Get ID of cell (i.e A1, B3)
     * 
     * @return String Cell ID
     */
    public String getPos() {
        return button.getText();
    }

    /**
     * Get name of corresponding image file
     * 
     * @return String Name of corresponding image file
     */
    private String getImage() {
        return "src/resources/" + pieceName + ".png";
    }
    
    /**
     * Change color of selected cell
     */
    public void setSelectedColor() {
    	if((row + col % 2) == 0) button.setBackground(Color.decode("#b9c45a"));
    	else button.setBackground(Color.decode("#e5e38e"));
    }

    /**
     * Change color of cells of possible-moves
     */
    public void setPossibleMoveColor() {
    	if(this.getCellPieceName().equals("empty")) button.setBackground(Color.decode("#6294db"));
    	else button.setBackground(Color.decode("#e00757"));
    }
    
    /**
     * Change color of cells representing movable cells
     */
    public void setHintsColor() {
    	button.setBackground(Color.PINK);
    }

}
