/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * Player.java, Jul 18, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.entity;

import pro_test_caro_buitrunghieu.logic.ChessBoardLogic;
import pro_test_caro_buitrunghieu.logic.ChessPieceParser;
import pro_test_caro_buitrunghieu.logic.XOButtonLogic;
import pro_test_caro_buitrunghieu.utils.Constants;

/**
 * Class đối tượng người chơi
 * 
 * @author Latrodectus
 *
 */
public class Player {
	// Đối tượng để so sánh thế cờ
	ChessPieceParser chessPieceParser;
	
	/**
	 * 
	 * Constructor
	 */
	public Player() {
		// Khởi tạo đối tượng
		chessPieceParser = new ChessPieceParser();
	}
	
	/**
	 * 
	 * Hàm thực hiện hành động đánh cờ của người chơi
	 * @param button nút mà người dùng chơi
	 * @param chessBoardLogic bàn cờ logic mà người dùng chơi
	 */
	public void play(XOButtonLogic button, ChessBoardLogic chessBoardLogic) {
		// nếu button chưa được đánh
		if(button.isClickable()) {
			// Set giá trị bằng giá trị người chơi đánh
			button.setValue(Constants.PLAYER_CHESS);
		}
	}
}
