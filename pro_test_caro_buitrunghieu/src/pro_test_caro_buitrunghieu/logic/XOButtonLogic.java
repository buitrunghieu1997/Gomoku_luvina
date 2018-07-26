/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * XOButtonLogic.java, Jul 20, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.logic;

import pro_test_caro_buitrunghieu.entity.Coordinate;
import pro_test_caro_buitrunghieu.utils.Constants;

/**
 * Description
 * 
 * @author Latrodectus
 *
 */
public class XOButtonLogic {
	// Giá trị trạng thái của nút
	private char value;
	// Toạ độ của nút
	private Coordinate coordinate;
	// Trạng thái có thể click hay không
	private boolean isClickable;

	/**
	 * 
	 * Constructor
	 */
	public XOButtonLogic() {
		super();
		// trạng thái mặc định ô rỗng
		this.setValue(Constants.EMPTY_CHESS);
		// Mặc định là có thể click
		this.setClickable(true);
	}

	public char getValue() {
		return this.value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public boolean isClickable() {
		return this.isClickable;
	}

	public void setClickable(boolean isClickable) {
		this.isClickable = isClickable;
	}

}
