/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * ChessBoard.java, Jul 18, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.logic;

import pro_test_caro_buitrunghieu.entity.Coordinate;
import pro_test_caro_buitrunghieu.utils.Constants;

/**
 * Class chứa các logic của bàn cờ
 * 
 * @author Latrodectus
 *
 */
public class ChessBoardLogic {
	// Mảng các button trong bàn cờ
	private XOButtonLogic[][] buttons;
	// Instance của board, chỉ cho phép 1 instance tại 1 phiên
	private static ChessBoardLogic instance;
	// Biến đếm số các nước đã đánh
	private int count = 0;

	/**
	 * 
	 * Hàm trả về instance của bàn cờ logic hiện tại
	 * 
	 * @return đối tượng instance của bàn cờ logic
	 */
	public static ChessBoardLogic getInstance() {
		/*
		 * nếu instance bằng null thì khởi tạo đối tượng mới
		 */
		if (instance == null) {
			instance = new ChessBoardLogic();
		}
		// Trả về đối tượng đã khởi tạo
		return instance;
	}

	/**
	 * 
	 * Constructor của đối tượng ChessBoardLogic
	 */
	public ChessBoardLogic() {
		// hàm khởi tạo của lớp cha
		super();
		// Khởi tạo mảng button
		this.buttons = new XOButtonLogic[Constants.CHESSBOARD_ROW][Constants.CHESSBOARD_ROW];
		/*
		 * Khởi tạo lần lượt từng button trong 20 button Khởi tạo toạ độ bằng vị trí
		 * theo hàng và cột hiện tại
		 */
		for (int row = 0; row < Constants.CHESSBOARD_ROW; row++) {
			for (int column = 0; column < Constants.CHESSBOARD_COLUMN; column++) {
				this.buttons[row][column] = new XOButtonLogic();
				this.buttons[row][column].setCoordinate(new Coordinate(row, column));
			}
		}
	}

	/**
	 * 
	 * Hàm trả về mảng các button trong bàn cờ
	 * 
	 * @return mảng 20x20 các button trong bàn cờ
	 */
	public XOButtonLogic[][] getButtons() {
		return this.buttons;
	}

	/**
	 * 
	 * Hàm trả về button theo toạ độ x y
	 * 
	 * @param x vị trí theo hàng
	 * @param y vị trí theo cột
	 * @return button theo hàng và cột
	 */
	public XOButtonLogic getButton(int x, int y) {
		return this.buttons[x][y];
	}

	/**
	 * 
	 * Hàm trả về button theo toạ độ x y
	 * 
	 * @param coor toạ độ
	 * @return button theo toạ độ
	 */
	public XOButtonLogic getButton(Coordinate coor) {
		return this.buttons[coor.getX()][coor.getY()];
	}
	
	/**
	 * 
	 * Check xem bàn cờ có full không
	 * 
	 * @return true false
	 */
	public boolean checkFull() {
		/*
		 * Duyệt toàn bộ bàn bàn cờ xem còn nút trống không nếu có thì trả về false
		 * không thì trả về true
		 */
		for (int row = 0; row < Constants.CHESSBOARD_ROW; row++) {
			for (int column = 0; column < Constants.CHESSBOARD_COLUMN; column++) {
				if (this.buttons[row][column].getValue() == Constants.EMPTY_CHESS) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * Hàm kiểm tra thắng
	 * 
	 * @param xoButton Nút để xét thắng
	 * @return true nếu thắng, false nếu chưa thắng
	 */
	public boolean checkWin(XOButtonLogic xoButton) {
		// Check theo hàng dọc, hàng ngang, chéo trái, chéo phải
		if (checkHorizontal(xoButton) || checkVertical(xoButton) || checkLeftDiagon(xoButton)
				|| checkRightDiagon(xoButton)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Hàm check thắng theo cột
	 * 
	 * @param xoButton nút đang xét
	 * @return true nếu thắng, false nếu chưa thắng
	 */
	public boolean checkVertical(XOButtonLogic xoButton) {
		try {
			// Lấy toạ độ của nút đang xét
			Coordinate checkLocation = xoButton.getCoordinate();
			// Lấy toạ độ x của toạ độ
			int row = checkLocation.getX();
			// Lấy toạ độ y của toạ độ
			int column = checkLocation.getY();
			// Lấy giá trị của nút đang xét
			char value = xoButton.getValue();
			// Biến đếm số nút liền nhau giống nhau
			int count = 0;
			// xét sang lên trên 4 nút
			int innerRow = row - Constants.NEIGHBOUR_COUNT;
			// Xét xuống dưới bốn nút
			int outerRow = row + Constants.NEIGHBOUR_COUNT;
			// Khi nút bên trên còn nhỏ hơn nút bên dưới
			while (innerRow <= outerRow) {
				// Chỉ xét nếu innerrow còn nằm trong bàn cờ
				if (innerRow >= 0 && innerRow < Constants.CHESSBOARD_ROW) {
					// Lấy giá trị của button đang xét
					char buttonValue = this.getButton(innerRow, column).getValue();
					// Nếu nút không trống
					if (buttonValue != Constants.EMPTY_CHESS) {
						// Nếu Giá trị của nút hàng xóm bằng giá trị của nút truyền vào
						if (buttonValue == value) {
							// Tăng biến đếm
							count++;
						} else {
							// Nếu có một nút trong nút hàng xóm đang xét mà khác thì reset count về 0
							count = 0;
						}
					} else {
						// Nếu có một nút trong nút hàng xóm đang xét mà rỗng thì reset về 0
						count = 0;
					}
				}
				/*
				 * Nếu số nút đếm được đạt đến trạng thái win thì trả về true
				 */
				if (Constants.WIN_STATE == count) {
					return true;
				}
				// Xét đến nút hàng xóm tiếp theo
				innerRow++;
			}
			/*
			 * Xử lý các ngoại lệ
			 */
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// nếu các logic bên trên không thoả mãn thì trả về false
		return false;

	}

	/**
	 * 
	 * Hàm check thắng theo hàng
	 * 
	 * @param xoButton nút xét
	 * @return true nếu có trạng thái thắng ngược lại là false
	 */
	public boolean checkHorizontal(XOButtonLogic xoButton) {
		try {
			// Lấy toạ độ của nút đang xét
			Coordinate checkLocation = xoButton.getCoordinate();
			// Lấy toạ độ x của toạ độ
			int row = checkLocation.getX();
			// Lấy toạ độ y của toạ độ
			int column = checkLocation.getY();
			// Lấy giá trị của nút đang xét
			char value = xoButton.getValue();
			// Biến đếm số nút liền nhau giống nhau
			int count = 0;
			// xét sang trái 4 nút
			int innerColumn = column - Constants.NEIGHBOUR_COUNT;
			// Xét sang phải 4 nút
			int outerColumn = column + Constants.NEIGHBOUR_COUNT;
			// Khi nút bên trên còn nhỏ hơn nút bên dưới
			while (innerColumn <= outerColumn) {
				// Khi nút top còn nằm trong phạm vi bàn cờ
				if (innerColumn >= 0 && innerColumn < Constants.CHESSBOARD_COLUMN) {
					// Lấy giá trị của nút hiện tại
					char buttonValue = getButton(row, innerColumn).getValue();
					/*
					 * Nếu ô cơ hiện tại không trống thì kiểm tra giá trị của nút đang xét có giống
					 * gía trị ban đầu không Nếu có thì tăng biến đếm lên 1 Nếu có một nút khác thì
					 * reset biến đếm về 0
					 */
					if (Constants.EMPTY_CHESS != buttonValue) {
						if (buttonValue == value) {
							count++;
						} else {
							count = 0;
						}
					} else {
						// Nếu có một nút trong nút hàng xóm đang xét mà rỗng thì reset về 0
						count = 0;
					}
				}
				/*
				 * Nếu win thì trả về true ngược lại xét tới nút tiếp theo bằng việc tăng
				 * innerColumn lên 1
				 */
				if (Constants.WIN_STATE == count) {
					return true;
				}
				innerColumn++;
			}
			/*
			 * Xử lý các ngoại lệ
			 */
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// trả về false nếu các logic không thoả mãn
		return false;
	}

	/**
	 * 
	 * Hàm check thắng theo đường chéo trái sang phải
	 * 
	 * @param xoButton nút xét
	 * @return true nếu có trạng thái thắng ngược lại là false
	 */
	public boolean checkLeftDiagon(XOButtonLogic xoButton) { // chéo trái sang phải
		try {
			// Lấy toạ độ của nút đang xét
			Coordinate checkLocation = xoButton.getCoordinate();
			// Lấy toạ độ x của toạ độ
			int row = checkLocation.getX();
			// Lấy toạ độ y của toạ độ
			int column = checkLocation.getY();
			// Lấy giá trị của nút đang xét
			char value = xoButton.getValue();
			// Biến đếm số nút liền nhau giống nhau
			int count = 0;
			// xét sang lên trên 4 nút
			int innerColumn = column - Constants.NEIGHBOUR_COUNT;
			// Xét xuống dưới 4 nút
			int outerColumn = column + Constants.NEIGHBOUR_COUNT;
			// Xét sang trái 4 nút
			int innerRow = row - Constants.NEIGHBOUR_COUNT;
			// Xét sang phải 4 nút
			int outerRow = row + Constants.NEIGHBOUR_COUNT;
			// Khi nút đang xét theo hàng và cột còn chưa vượt phạm vi bàn cờ dưới và vượt
			// khoảng phạm vi nút hàng xóm cần xét
			while (innerColumn <= outerColumn && innerColumn < Constants.CHESSBOARD_COLUMN && innerRow <= outerRow
					&& innerRow < Constants.CHESSBOARD_ROW) {
				// Column và row của nút đang xét chưa vượt phạm vi bàn cờ trên
				if (innerColumn >= 0 && innerRow >= 0) {
					// Lấy giá trị của nút đang xét
					char buttonValue = this.getButton(innerRow, innerColumn).getValue();
					/*
					 * Nếu ô cơ hiện tại không trống thì kiểm tra giá trị của nút đang xét có giống
					 * gía trị ban đầu không Nếu có thì tăng biến đếm lên 1 Nếu có một nút khác thì
					 * reset biến đếm về 0
					 */
					if (Constants.EMPTY_CHESS != buttonValue) {
						if (buttonValue == value) {
							count++;
						} else {
							count = 0;
						}
					} else {
						// Nếu có một nút trong nút hàng xóm đang xét mà rỗng thì reset về 0
						count = 0;
					}
				}
				/*
				 * Nếu win thì trả về true ngược lại xét tới nút tiếp theo bằng việc tăng
				 * innerColumn lên 1 và tăng innerrow lên 1
				 */
				if (Constants.WIN_STATE == count) {
					return true;
				}
				innerColumn++;
				innerRow++;
			}
			/*
			 * Xử lý các ngoại lệ
			 */
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// trả về false nếu các logic bên trên không thoả mãn
		return false;
	}

	/**
	 * 
	 * Hàm check thắng theo đường chéo phải sang trái
	 * 
	 * @param xoButton nút xét
	 * @return true nếu có trạng thái thắng ngược lại là false
	 */
	public boolean checkRightDiagon(XOButtonLogic xoButton) { // chéo phải sang trái
		try {
			// Lấy toạ độ của nút đang xét
			Coordinate checkLocation = xoButton.getCoordinate();
			// Lấy toạ độ x của toạ độ
			int row = checkLocation.getX();
			// Lấy toạ độ y của toạ độ
			int column = checkLocation.getY();
			// Lấy giá trị của nút đang xét
			char value = xoButton.getValue();
			// Biến đếm số nút liền nhau giống nhau
			int count = 0;
			// xét sang lên trên 4 nút
			int innerColumn = column - Constants.NEIGHBOUR_COUNT;
			// Xét xuống dưới 4 nút
			int outerColumn = column + Constants.NEIGHBOUR_COUNT;
			// Xét sang phải 4 nút
			int innerRow = row + Constants.NEIGHBOUR_COUNT;
			// Xét sang trái 4 nút
			int outerRow = row - Constants.NEIGHBOUR_COUNT;
			// Khi nút đang xét theo hàng và cột còn chưa vượt khoảng phạm vi nút hàng xóm
			// cần xét
			while (innerColumn <= outerColumn && innerRow >= outerRow) {
				// Các nút đang xét còn nằm trong phạm vi bàn cờ
				if (innerColumn >= 0 && innerRow >= 0 && innerColumn < 20 && innerRow < 20) {
					// Lấy giá trị của nút đang xét
					char buttonValue = this.getButton(innerRow, innerColumn).getValue();
					if (Constants.EMPTY_CHESS != buttonValue) {
						if (buttonValue == value) {
							count++;
						} else {
							count = 0;
						}
					} else {
						// Nếu có một nút trong nút hàng xóm đang xét mà rỗng thì reset về 0
						count = 0;
					}
				}
				/*
				 * Nếu win thì trả về true ngược lại xét tới nút tiếp theo bằng việc tăng
				 * innerColumn lên 1 và tăng innerrow lên 1
				 */
				if (Constants.WIN_STATE == count) {
					return true;
				}
				innerColumn++;
				innerRow--;
			}
			/*
			 * Xử lý các ngoại lệ
			 */
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Nếu không có logic nào thoả mãn thì trả về false
		return false;
	}

	/**
	 * 
	 * Hàm reset các logic về trạng thái khởi tạo ban đầu
	 */
	public void resetLogic() {
		try {
			// Lấy instance hiện tại bàn cờ logic
			ChessBoardLogic chessBoardLogic = getInstance();
			// Lấy các nút của instance
			XOButtonLogic[][] buttons = chessBoardLogic.getButtons();
			/*
			 * Duyệt các nút của bàn cờ, set giá trị các nút về trạn thái trống và có thể
			 * click
			 */
			for (int row = 0; row < Constants.CHESSBOARD_ROW; row++) {
				for (int column = 0; column < Constants.CHESSBOARD_COLUMN; column++) {
					buttons[row][column].setValue(Constants.EMPTY_CHESS);
					buttons[row][column].setClickable(true);
				}
			}
			/*
			 * Xử lý các ngoại lệ
			 */
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 
	 * Hàm đếm số lượng các nước cờ đã được đánh
	 * @return số lượng các nước cờ đã được đánh
	 */
	public int increaseChessCount() {
		return ++this.count;
	}

	/**
	 * 
	 * Getter
	 * @return
	 */
	public int getChessCount() {
		return this.count;
	}
	
	/**
	 * 
	 * Reset khi xong 1 ván cờ
	 */
	public void resetChessCount() {
		count = 0;
	}
}