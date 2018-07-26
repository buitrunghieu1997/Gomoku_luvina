/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * ChessPiece.java, Jul 17, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.entity;

import pro_test_caro_buitrunghieu.utils.Constants;

/**
 * Đối tượng thế cờ
 * @author Latrodectus
 *
 */
public class ChessPiece { 
	// Thế cờ có thuộc tính là một mảng char 5x5
	private char[][] chessPiece = new char[Constants.CHESSPIECE_ROW][Constants.CHESSPIECE_COLUMN];

	/**
	 * 
	 * Getter
	 * @return trả về thế cờ
	 */
	public char[][] getChessPiece() {
		return chessPiece;
	}

	/**
	 * 
	 * Constructor
	 */
	public ChessPiece() {
		super();
		for (int column = 0; column < 5; column++) {
			for (int row = 0; row < 5; row++) {
				// Khơi tạo mặc định là giá trị mà không cần quan tâm nước cờ là gì
				chessPiece[row][column] = Constants.IGNORE_CHESS;
			}
		}
	}

	/**
	 * 
	 * Constructor
	 * @param chessPiece tham số truyền vào
	 */
	public ChessPiece(char[][] chessPiece) {
		super();
		this.chessPiece = chessPiece;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String chessPieceStr = "";
		for (int column = 0; column < 5; column++) {
			for (int row = 0; row < 5; row++) {
				chessPieceStr += chessPiece[row][column];
			}
			chessPieceStr += "\n";
		}
		return chessPieceStr;
	}
}
