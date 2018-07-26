/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * ChessBoardActionListener.java, Jul 19, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pro_test_caro_buitrunghieu.entity.Bot;
import pro_test_caro_buitrunghieu.entity.Player;
import pro_test_caro_buitrunghieu.logic.ChessBoardLogic;
import pro_test_caro_buitrunghieu.logic.XOButtonLogic;
import pro_test_caro_buitrunghieu.utils.Common;
import pro_test_caro_buitrunghieu.utils.Constants;
import pro_test_caro_buitrunghieu.view.ChessBoardView;
import pro_test_caro_buitrunghieu.view.XOButtonView;

/**
 * Class thực hiện kiểm soát hành động chơi cờ của người chơi
 * 
 * @author Latrodectus
 *
 */
public class ChessBoardActionListener implements ActionListener {
	// Nút lưu trữ ô cờ trước mà máy đánh
	private XOButtonView oldBotChess = new XOButtonView();
	// Nút lưu trữ ô cờ trước mà máy đánh
	private XOButtonView oldPlayerChess = new XOButtonView();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Lấy ra button mà người dùng vừa thao tác
			XOButtonView buttonView = (XOButtonView) e.getSource();
			// Trả về button logic tương ứng vs button view bên trên
			XOButtonLogic buttonLogic = ChessBoardLogic.getInstance().getButton(buttonView.getCoordinate());
			// Nếu thuộc tích clickable của nút là true
//			if (!X.equals(button.getIcon()) && !O.equals(button.getIcon())) {
			if (buttonLogic.isClickable()) {
				// Đổi icon button thành O
				buttonView.setIcon(Constants.O);
				// Tạo đối tượng người chơi
				Player player = new Player();
				// Tạo đối tượng máy
				Bot bot = new Bot();
				// Lấy ra thể hiện của bàn cờ logic
				ChessBoardLogic chessBoardLogic = ChessBoardLogic.getInstance();
				// lấy ra thể hiện của giao diện bàn cờ
				ChessBoardView chessBoardView = ChessBoardView.getInstance();
				// Người chơi thực hiện logic chơi cờ tại button trên bàn cờ logic
				player.play(buttonLogic, chessBoardLogic);
				// Tăng số lượng nước cờ đã chơi lên 1
				chessBoardLogic.increaseChessCount();
				// Đổi màu nút cũ về màu mặc định
				oldPlayerChess.setBackground(Constants.DEFAULT_COLOR);
				// Đánh dấu nút hiện tại vừa đánh
				buttonView.setBackground(Constants.PLAYER_COLOR);
				// Gán nút cũ bằng nút vừa đánh
				oldPlayerChess = buttonView;
				// Kiểm tra win của người chơi tại nút vừa đánh, nếu người chơi thắng
				if (chessBoardLogic.checkWin(buttonLogic)) {
					resetGame(chessBoardView, chessBoardLogic, Common.prop.getProperty("PLAYER_WIN"));
					// Nếu người chơi không thắng thì kiểm tra xem bàn cờ đã full chưa, nếu bàn cờ đã full
				} else if (chessBoardLogic.getChessCount() == Constants.MAX_CHESS_COUNT) {
					if (chessBoardLogic.checkFull()) {
						resetGame(chessBoardView, chessBoardLogic, Common.prop.getProperty("NO_WIN"));
					}
				} else {
					// Nếu nút người chơi vừa đánh có isclick = true thì
					if (buttonLogic.isClickable()) {
						// Máy thực hiện chơi cờ và trả về ô nó vừa chơi
						bot.play(buttonLogic, chessBoardLogic);
						// Trả về ô mà máy vừa đánh
						XOButtonLogic botButtonLogic = bot.getPlayedChess();
						// Tăng số lượng nước cờ đã chơi trong bàn cờ
						chessBoardLogic.increaseChessCount();
						// Lấy ra đối tượng ButtonView theo ButtonLogic
						XOButtonView botButtonView = ChessBoardView.getInstance()
								.getButton(botButtonLogic.getCoordinate());
						// Xoá đánh dấu của nút cũ
						oldBotChess.setBackground(Constants.DEFAULT_COLOR);
						// set icon ô máy vừa đánh là X
						botButtonView.setIcon(Constants.X);
						// Cho phép tuỳ biến nút như nền, ...
						botButtonView.setOpaque(true);
						// Đổi màu nền của nút máy vừa đánh để đánh dấu
						botButtonView.setBackground(Constants.BOT_COLOR);
						// Gán nút old bằng nút mà máy vừa đánh
						oldBotChess = botButtonView;
						// set trạng thái clickable của nút mà người chơi đánh lúc trước là false
						buttonLogic.setClickable(false);
						// Kiểm tra thắng của máy
						if (chessBoardLogic.checkWin(botButtonLogic)) {
							resetGame(chessBoardView, chessBoardLogic, Common.prop.getProperty("BOT_WIN"));
							// nếu chưa thắng thì check xem bàn cờ đã full chưa
						} else if (chessBoardLogic.getChessCount() == Constants.MAX_CHESS_COUNT) {
							if (chessBoardLogic.checkFull()) {
								resetGame(chessBoardView, chessBoardLogic, Common.prop.getProperty("NO_WIN"));
							}
						}
					}
				}
			}
			/**
			 * bắt các ngoại lệ từ nhỏ đến lớn và in ra dưới dạng msg
			 */
		} catch (NullPointerException npe) {
//			System.out.println(npe.getMessage() + "\n" + npe.getCause());
			npe.printStackTrace();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * Reset lại game về trạng thái ban đầu
	 * @param chessBoardView
	 * @param chessBoardLogic
	 */
	private void resetGame(ChessBoardView chessBoardView, ChessBoardLogic chessBoardLogic,String msg) {
		// hiển thị thông báo hoà
		chessBoardView.showDialog(msg);
		// Reset giao diện bàn cờ
		chessBoardView.resetView();
		// Reset bàn cờ logic
		chessBoardLogic.resetLogic();
		// Reset số nước trên bàn cờ về 0
		chessBoardLogic.resetChessCount();
	}
}
