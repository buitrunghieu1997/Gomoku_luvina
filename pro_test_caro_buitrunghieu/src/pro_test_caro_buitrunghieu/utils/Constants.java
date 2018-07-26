/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * Constants.java, Jul 17, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.utils;

import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * Các hằng số sử dụng trong bài
 * 
 * @author Latrodectus
 *
 */
public class Constants {
	/*
	 * Value of chess
	 */
	// giá trị quân cờ của máy
	public static final char BOT_CHESS = 'X';
	// giá trị quân cờ của người
	public static final char PLAYER_CHESS = 'O';
	// giá trị chỉ ra vị trí quân cờ máy cần đánh
	public static final char NEXT_CHESS = 'D';
	// giá trị ô trống
	public static final char EMPTY_CHESS = 'T';
	// Giá trị thể hiện ô cờ không cần xét đến
	public static final char IGNORE_CHESS = 'G';

	/*
	 * Các hằng số liên quan đến bàn cờ và logic bàn cờ
	 */
	// Tổng phần tử của một phần tử thế cờ
	public static final int CHESSPIECE_ELEMENT_COUNT = 25;
	// Số hàng của một CHESSPIECE
	public static final int CHESSPIECE_ROW = 5;
	// Số cột của một CHESSPIECE
	public static final int CHESSPIECE_COLUMN = 5;
	// Số hàng hợp lệ của String thế cờ
	public static final int VALID_LINE_COUNT = 5;
	// Số ký tự hợp lệ một String thế cờ
	public static final int VALID_CHARACTER_COUNT = 25;
	// số cột trong bàn cờ
	public static final int CHESSBOARD_COLUMN = 20;
	// số hàng trong bàn cờ
	public static final int CHESSBOARD_ROW = 20;
	// số nút lân cận xét thêm trong hàm check win
	public static final int NEIGHBOUR_COUNT = 4;
	// số nút cờ liên tiếp giống nhau để win
	public static final int WIN_STATE = 5;
	// Số cột của các matrix 5x5
	public static final int MAX_MATRIX5x5_COLUMN_COUNT = 16;
	// Số hàng của các matrix 5x5
	public static final int MAX_MATRIX5x5_ROW_COUNT = 16;
	// Số hàng trong một ma trận 5x5
	public static final int MATRIX5x5_ROW = 5;
	// Số cột của một CHESSPIECE
	public static final int MATRIX5x5_COLUMN = 5;
	// Số nước cờ tối đa trong một bàn cờ
	public static final int MAX_CHESS_COUNT = 400;
	// Error exit status
	public static final int EXIT_ON_ERROR_STATUS = 0;
	
	/*
	 *  kích thước cửa sổ
	 */
	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 600;
	
	/*
	 * màu sắc 
	 */
	// Màu sắc mặc định của các ô trên bàn cờ
	public static final Color DEFAULT_COLOR = new Color(179, 217, 255);
	// Màu sắc đánh dấu ô máy đánh
	public static final Color BOT_COLOR = new Color(153, 255, 153);
	//Màu sắc đánh dấu ô người đánh
	public static final Color PLAYER_COLOR = new Color(255, 217, 179);
	
	/*
	 * Icon
	 */
	// Icon O đại diện cho quân cờ của người chơi
	public static final ImageIcon O = new ImageIcon("./images/nought.gif");
	// Icon X đại diện cho quân cờ của máy
	public static final ImageIcon X = new ImageIcon("./images/cross.gif");
	
//	/*
//	 * Các câu thông báo
//	 */
//	// Thông báo người thắng
//	public static final String PLAYER_WIN = "Chúc mừng, bạn đã thắng.";
//	// Thông báo máy thắng
//	public static final String BOT_WIN = "Máy thắng, chúc bạn may mắn lần sau.";
//	// Thông báo hoà
//	public static final String NO_WIN = "Bàn cờ hết ô trống, kết quả hoà.";
//	// Thông báo thế cờ không hợp lệ
//	public static final String INVALID_STRING_MSG = "String thế cờ không hợp lệ.";
//	// thông báo không tìm được thế cờ
//	public static final String NO_CHESSPIECE_FOUND_MSG = "Không tìm được thế cờ phù hợp, File thế cờ chưa bao quát.";
//	// Tên cửa sổ thông báo
//	public static final String DIALOG_NAME = "Kết thúc ván chơi";

	/*
	 * Các hằng số liên quan đến fie
	 */
	// tên của file properties
	public static final String CONFIG_FILE = "config.properties";
	// đường dẫn của file thế cờ
	public static final String CHESSPIECE_FILEPATH = "./01_buitrunghieu_theco.txt";
}
