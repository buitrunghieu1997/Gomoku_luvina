/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * ChessBoard.java, Jul 18, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.view;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pro_test_caro_buitrunghieu.controller.ChessBoardActionListener;
import pro_test_caro_buitrunghieu.entity.Coordinate;
import pro_test_caro_buitrunghieu.utils.Common;
import pro_test_caro_buitrunghieu.utils.Constants;

/**
 * Lớp đối tượng các nút trong bàn cờ 5x5
 * 
 * @author Latrodectus
 *
 */
public class ChessBoardView extends JFrame {
	/**
	 * Description
	 *
	 */
	private static final long serialVersionUID = -1595709984694077449L;
	/**
	 * Version của class, cần có khi extends các đối tượng đồ hoạ
	 *
	 */
	
	// Panel chứa bàn cờ
	private JPanel p = new JPanel();
	// Thể hiện của diện bàn cờ
	private static ChessBoardView instance;
	// Đối tượng điều khiển
	private ChessBoardActionListener al;
	// Các buttonView trong bàn cờ
	private XOButtonView[][] buttons;

	public XOButtonView[][] getButtons() {
		return buttons;
	}

	public void setButtons(XOButtonView[][] buttons) {
		this.buttons = buttons;
	}

	/**
	 * 
	 * Hàm trả về một thể hiện của đối tượng giao diện bàn cờ
	 * 
	 * @return
	 */
	public static ChessBoardView getInstance() {
		/*
		 * Nếu giao diện bàn cờ chưa khởi tạo thì khởi tạo một đối tượng mới sau đó trả
		 * về đối tượng đó
		 */
		if (instance == null) {
			instance = new ChessBoardView();
		}
		return instance;
	}

	/**
	 * 
	 * Constructor
	 */
	public ChessBoardView() {
		// Gọi hàm khởi tạo mặc định với tên gọi caro
		super("Caro");
		// set độ rộng và chiều cao cửa sổ giao diện
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		// Không cho phép thay đổi kích thước
		setResizable(false);
		// Hành động khi đóng cửa sổ là thoát chương trình
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Set layout cho panel là grid layout 20x20
		p.setLayout(new GridLayout(Constants.CHESSBOARD_ROW, Constants.CHESSBOARD_COLUMN));
		// Lấy về mảng các nút trong bàn cờ logic
		buttons = new XOButtonView[Constants.CHESSBOARD_ROW][Constants.CHESSBOARD_COLUMN];
		// Khởi tạo đối tượng controller
		al = new ChessBoardActionListener();
		/*
		 * Khởi tạo các nút Khởi tạo toạ độ các bút bằng vị trí ô trên bàn cờ 
		 * Gán controller vào mỗi nút Thêm các nút vào pannel
		 * set toolTipText bằng toạ độ của ô đó trên bàn cờ
		 */
		for (int row = 0; row < Constants.CHESSBOARD_ROW; row++) {
			for (int column = 0; column < Constants.CHESSBOARD_COLUMN; column++) {
				buttons[row][column] = new XOButtonView();
				XOButtonView buttonView = buttons[row][column];
				buttonView.setCoordinate(new Coordinate(row, column));
				buttonView.addActionListener(al);
				Coordinate buttonCoor = buttonView.getCoordinate();
				buttonView.setToolTipText("[" + buttonCoor.getX() + "; " + buttonCoor.getY() + "]");
				p.add(buttonView);
			}
		}
		// Thêm panel vào giao diện
		add(p);
		// Hiển thị chính giữa màn hình
		setLocationRelativeTo(null);
		// có thẻ hiển thị
		setVisible(true);
	}
	
	/**
	 * 
	 * Hàm trả về button theo toạ độ x y
	 * 
	 * @param coor toạ độ
	 * @return button theo toạ độ
	 */
	public XOButtonView getButton(Coordinate coor) {
		return buttons[coor.getX()][coor.getY()];
	}

	/**
	 * 
	 * Hiện của sổ thông báo
	 * @param msg thông điệp
	 */
	public void showDialog(String msg) {
		JOptionPane.showMessageDialog(p, msg, Common.prop.getProperty("DIALOG_NAME"), JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * 
	 * Hàm reset giao diện bàn cờ về trạng thái mặc định
	 */
	public void resetView() {
		/* Reset các nút về trạng thái mặc định
		 * Không có icon
		 * Màu sắc mặc định
		 * 
		 */
		for (int row = 0; row < Constants.CHESSBOARD_ROW; row++) {
			for (int column = 0; column < Constants.CHESSBOARD_COLUMN; column++) {
				buttons[row][column].setIcon(null);
				buttons[row][column].setBackground(Constants.DEFAULT_COLOR);
			}
		}
	}
}
