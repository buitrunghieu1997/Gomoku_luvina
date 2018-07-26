/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * ChessBoardParser.java, Jul 18, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.logic;

import pro_test_caro_buitrunghieu.entity.ChessPiece;
import pro_test_caro_buitrunghieu.entity.Coordinate;
import pro_test_caro_buitrunghieu.utils.Common;
import pro_test_caro_buitrunghieu.utils.Constants;

/**
 * Class thực hiện việc so sánh thế cờ với bàn cờ
 * 
 * @author Latrodectus
 *
 */
public class ChessPieceParser {
	// Biến lưu thế cờ máy sẽ đánh
	private ChessPiece validChessPiece;
	// Biến lưu toạ độ của vùng cờ 5x5 mà máy sẽ đánh
	private Coordinate validCoordinate;

	/**
	 * 
	 * Getter của validChessPiece
	 * 
	 * @return validChessPiece
	 */
	public ChessPiece getValidChessPiece() {
		return validChessPiece;
	}

	/**
	 * 
	 * Getter của validCoordinate
	 * 
	 * @return validCoordinate
	 */
	public Coordinate getValidCoordinate() {
		return validCoordinate;
	}
	
	/**
	 * 
	 * Setter
	 * @param validChessPiece
	 */
	private void setValidChessPiece(ChessPiece validChessPiece) {
		this.validChessPiece = validChessPiece;
	}

	/**
	 * 
	 * Setter
	 * @param validCoordinate
	 */
	private void setValidCoordinate(Coordinate validCoordinate) {
		this.validCoordinate = validCoordinate;
	}


	/**
	 * 
	 * Constructer của ChessPieceParser
	 */
	public ChessPieceParser() {
		/*
		 * Khởi tạo hai thuộc tính
		 */
		validChessPiece = new ChessPiece();
		validCoordinate = new Coordinate();
	}

	/**
	 * 
	 * Hàm xét xem list thế cờ có match vs bàn cờ hay không
	 * 
	 * @param chessBoardLogic bàn cờ logic chứa toạ độ và giá trị các ô
	 * @return true nếu tìm được nước đi trong list, false nếu không tìm được nước
	 *         đi
	 */
	public boolean compareListChessPieceWithBoard(ChessBoardLogic chessBoardLogic) {
		/*
		 * Nếu list thế cờ không null
		 * thù duyệt hết list thế cờ
		 * kiểm tra xem có thế cờ nào thoả mãn bàn cờ khôgn
		 * nếu có thì trả về true ngược lại trả về false
		 * Nếu list thế cờ null thì in ra thông báo và trả về false
		 */
		if (Common.listChessPiece != null) {
			for (ChessPiece chessPiece : Common.listChessPiece) {
				if (compareChessPieceWithBoard(chessPiece, chessBoardLogic)) {
					return true;
				}
			}
		} else {
			System.out.println("Danh sách thế cờ rỗng, vui lòng kiểm tra lại.");
		}
		return false;
	}

	/**
	 * 
	 * Hàm kiểm tra xem một thế cờ có khớp với bàn cờ hay không
	 * 
	 * @param cp thế cờ
	 * @param chessBoardLogic bàn cờ logic 
	 * @return true nếu thế cờ thoả mãn và ngược lại là false
	 */
	private boolean compareChessPieceWithBoard(ChessPiece cp, ChessBoardLogic chessBoardLogic) {
		// Ma trận lưu một phần 5x5 của bàn cờ
		char[][] matrix5x5 = new char[Constants.MATRIX5x5_ROW][Constants.MATRIX5x5_COLUMN];
		/*
		 * Duyệt theo từng phần từ trong bàn cờ
		 * Với mỗi phần từ trong bàn cờ thì từ đó lấy ra một ma trần 5x5 của giá trị trạng thái các ô cờ
		 */
		for (int row = 0; row < Constants.MAX_MATRIX5x5_ROW_COUNT; row++) {
			for (int column = 0; column < Constants.MAX_MATRIX5x5_COLUMN_COUNT; column++) {
				/*
				 * Duyệt và gán từng giá trị trạng thái của phần từ 5x5 vừa lấy ra của bàn cờ cho mảng 5x5
				 */
				for (int matrixRow = 0; matrixRow < Constants.MATRIX5x5_ROW; matrixRow++) {
					for (int matrixColumn = 0; matrixColumn < Constants.MATRIX5x5_COLUMN; matrixColumn++) {
						matrix5x5[matrixRow][matrixColumn] = chessBoardLogic.getButtons()[row + matrixRow][column
								+ matrixColumn].getValue();
					}
				}
				/*
				 * Nếu thế cờ mà thoả mãn một ma trận 5x5 nào đó của bàn cờ thì
				 * gán validChessPiece bằng thế cờ đó
				 * Gán validCoordinate bằng toạ độ của phần từ đầu của mảng 5x5 đó 
				 * trả về true
				 */
				if (compareChessPiceWithMatrix5x5(cp, matrix5x5)) {
					setValidChessPiece(cp);;
					setValidCoordinate(new Coordinate(row, column));
					return true;
				}
			}
		}
		// Nếu duyệt hết bàn cờ mà không tìm được logic thoả mãn thì trả về false
		return false;
	}

	/**
	 * 
	 * Hàm xét xem một thế cờ có khớp với một phần từ ma trận giá trị ô cờ 5x5 được lấy từ bàn cờ
	 * 
	 * @param cp thế cờ
	 * @param matrix5x5 phần tử ma trận 5x5 của các giá trị ô cờ được lấy ra từ bàn cờ
	 * @return true nếu khớp và ngược lại là false
	 */
	private boolean compareChessPiceWithMatrix5x5(ChessPiece cp, char[][] matrix5x5) { 
		/*
		 * duyệt từ đầu tới cuối của thế cờ và phần tử mảng 5x5
		 * so sánh tương ứng các phần tử
		 */
		for (int row = 0; row < Constants.CHESSPIECE_ROW; row++) {
			for (int column = 0; column < Constants.CHESSPIECE_COLUMN; column++) {
				// Giá trị của một phần tử trong thế cờ
				char checkValue = cp.getChessPiece()[row][column];
				// giá trị của một phần từ trong ma trận 5x5
				char matrixValue = matrix5x5[row][column];
				// Kiểm tra theo giá trị trong thế cờ
				switch (checkValue) {
				// Nếu là các giá trị quy định người chơi hoặc máy đánh thì nếu không trùng trả về false
				case Constants.PLAYER_CHESS:
				case Constants.BOT_CHESS:
					if (checkValue != matrixValue) {
						return false;
					}
					break;
					// Nếu là các giá trị quy định ô trống hoặc ô phải đánh thì trong ma trận lấy ra ô phải trống nếu không trả về false
				case Constants.EMPTY_CHESS:
				case Constants.NEXT_CHESS:
					if (matrixValue != Constants.EMPTY_CHESS) {
						return false;
					}
					break;
				}
			}
		}
		// Nếu không có logic nào bên trên bị vi phạm thì trả về true
		return true;
	}
}
