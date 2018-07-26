/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * Bot.java, Jul 18, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.entity;

import java.util.Random;

import pro_test_caro_buitrunghieu.logic.ChessBoardLogic;
import pro_test_caro_buitrunghieu.logic.ChessPieceParser;
import pro_test_caro_buitrunghieu.logic.XOButtonLogic;
import pro_test_caro_buitrunghieu.utils.Common;
import pro_test_caro_buitrunghieu.utils.Constants;

/**
 * Đối tượng máy
 * 
 * @author Latrodectus
 *
 */
public class Bot {
	// đối tượng dùng để so sánh thế cờ với bàn cờ
	private ChessPieceParser chessPieceParser;
	private XOButtonLogic playedChess;

	/**
	 * 
	 * Hàm khởi tạo
	 */
	public Bot() {
		// khởi tạo đối tượng chessPieceParser
		chessPieceParser = new ChessPieceParser();
		playedChess = new XOButtonLogic();
	}

	/**
	 * 
	 * Hàm thực hiejn hành động chơi cờ của máy
	 * 
	 * @param button          vị trí người chơi vừa chơi
	 * @param buttons         mảng các nút trên bàn cờ
	 * @param chessBoardLogic bàn cờ logic đang đánh
	 * @return nút mà máy vừa đánh
	 */
	public void play(XOButtonLogic button, ChessBoardLogic chessBoardLogic) {
		// Tìm thế cờ phù hợp
		if (chessPieceParser.compareListChessPieceWithBoard(chessBoardLogic)) {
			// Thế cờ phù hợp
			ChessPiece validChessPiece = chessPieceParser.getValidChessPiece();
			// Vị trí đánh thế cờ trên bàn cờ
			Coordinate validCoordinate = chessPieceParser.getValidCoordinate();
			// Vị trí cần đánh trên bàn cờ
			Coordinate playPositon = findPositionToPlay(validChessPiece, validCoordinate);
			// List Button
			XOButtonLogic[][] buttons = chessBoardLogic.getButtons();
			// Nút máy đánh trên bàn cờ
			XOButtonLogic botButton = buttons[playPositon.getX()][playPositon.getY()];
			// Đổi giá trị nút thành máy đánh
			botButton.setValue(Constants.BOT_CHESS);
			// Đổi trạng thái click thành false
			botButton.setClickable(false);
			// Trả về nút máy vừa đánh vào biến playedChess
			playedChess = botButton;
		} else {
			System.out.println(Common.prop.getProperty("NO_CHESSPIECE_FOUND_MSG"));
			System.exit(Constants.EXIT_ON_ERROR_STATUS);
		}
	}

	/**
	 * 
	 * Tìm ra vị trí để máy đánh
	 * 
	 * @param validChessPiece Thế cờ thoả mãn
	 * @param validCoordinate Vị trí ma trận 5x5 trên bàn cờ để khớp thế cờ
	 * @return toạ độ cần đánh con cờ
	 */
	public Coordinate findPositionToPlay(ChessPiece validChessPiece, Coordinate validCoordinate) {
		/*
		 * duyệt trong thế cờ 5x5 tìm vị trí có giá trị D (vị trí cần đánh) rồi trả về
		 * toạ độ của vị trí đó
		 */
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				if (validChessPiece.getChessPiece()[row][column] == Constants.NEXT_CHESS) {
					Coordinate returnValue = new Coordinate(validCoordinate.getX() + row,
							validCoordinate.getY() + column);
					return returnValue;
				}
			}
		}
		// Nếu không có thì trả về null
		return null;
	}
	
	/**
	 * 
	 * Hàm Trả về ô mà máy vừa đánh
	 * @return ô máy vừa đánh
	 */
	public XOButtonLogic getPlayedChess() {
		return playedChess;
	}
	
	/**
	 * 
	 * Hàm cho máy đánh ngẫu nhiên
	 */
	public void playRandom(ChessBoardLogic chessBoardLogic) {
		XOButtonLogic[][] buttons = chessBoardLogic.getButtons();
		Random rand = new Random();
		int x = rand.nextInt(Constants.CHESSBOARD_ROW);
		int y = rand.nextInt(Constants.CHESSBOARD_COLUMN);
		// Nút máy đánh trên bàn cờ
		XOButtonLogic botButton = buttons[x][y];
		// Đổi giá trị nút thành máy đánh
		botButton.setValue(Constants.BOT_CHESS);
		// Đổi trạng thái click thành false
		botButton.setClickable(false);
		// Trả về nút máy vừa đánh vào biến playedChess
		playedChess = botButton;
	}
}
