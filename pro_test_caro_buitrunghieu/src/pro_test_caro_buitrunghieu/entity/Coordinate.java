/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * Coordinate.java, Jul 18, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.entity;

/**
 * Class đối tượng toạ độ
 * 
 * @author Latrodectus
 *
 */
public class Coordinate {
	// toạ độ hàng
	private int row;
	// Toạ độ cột
	private int column;

	/**
	 * 
	 * Constructor
	 */
	public Coordinate() {
		super();
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param x toạ độ row
	 * @param y toạ độ column
	 */
	public Coordinate(int x, int y) {
		super();
		this.row = x;
		this.column = y;
	}

	/**
	 * 
	 * Getter
	 * 
	 * @return row
	 */
	public int getX() {
		return row;
	}

	/**
	 * 
	 * Setter
	 * 
	 * @param x giá trị row
	 */
	public void setX(int x) {
		this.row = x;
	}

	/**
	 * 
	 * Getter
	 * 
	 * @return giá trị column
	 */
	public int getY() {
		return column;
	}

	/**
	 * 
	 * Setter
	 * 
	 * @param y Gí trị column
	 */
	public void setY(int y) {
		this.column = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + row + ", " + column + "]";
	}
}
