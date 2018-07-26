/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * CaroGame.java, Jul 18, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.test;

import pro_test_caro_buitrunghieu.utils.Common;
import pro_test_caro_buitrunghieu.view.ChessBoardView;

/**
 * Class chứa hàm main để bắt đầu một ván caro
 * @author Latrodectus
 *
 */
public class CaroGame {
	public static void main(String[] args) {
		//Init Properties
		Common.initProperties();
		// Gọi instance của giao diện bàn cờ
		ChessBoardView.getInstance();
		// Đóng dòng đọc file properties
		Common.closeConnection();
	}
}
