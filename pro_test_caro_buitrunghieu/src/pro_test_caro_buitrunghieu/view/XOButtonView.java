/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * XOButtonLogic.java, Jul 18, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.view;

import javax.swing.JButton;

import pro_test_caro_buitrunghieu.entity.Coordinate;
import pro_test_caro_buitrunghieu.utils.Constants;

/**
 * Class đối tượng các nút trên bàn cờ
 * 
 * @author Latrodectus
 *
 */
public class XOButtonView extends JButton {
	/**
	 * Description
	 *
	 */
	private static final long serialVersionUID = -6338006533562182957L;
	/*
	 *  Version của class, cần có cho các đối tượng seriablize, khi có thay đổi UID thay đổi
	 *  uid khi deseriablize phải trùng với UID trong đối tượng được tạo khi Serabilze thì mới biên dịch được
	 */
	
	// Toạ độ của nút
	private Coordinate coordinate;

	/**
	 * 
	 * Constructor
	 */
	public XOButtonView() {
		super();
		// Không đổ màu lên các vùng của nút (độ bóng của nút) để tạo flat style
		this.setContentAreaFilled(false);
		// Mặc định là có thể tuỳ biến nút
		this.setOpaque(true);
		// Màu nền mặc định
		this.setBackground(Constants.DEFAULT_COLOR);
	}

	/**
	 * 
	 * Getter
	 * @return toạ độ
	 */
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	
	/**
	 * 
	 * Setter
	 * @param toạ độ
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
}
