/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * Common.java, Jul 18, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import pro_test_caro_buitrunghieu.entity.ChessPiece;
import pro_test_caro_buitrunghieu.fileReader.ChessPieceReader;
import pro_test_caro_buitrunghieu.view.ChessBoardView;

/**
 * Lớp chứa các hàm thường dùng, được tạo ngay khi biên dịch
 * @author Latrodectus
 *
 */
public class Common {
	public static ArrayList<ChessPiece> listChessPiece = ChessPieceReader.readFile();
	public static Properties prop;
	public static FileInputStream fis;

	public void showExceptionDialog(String title, String msg) {
		JOptionPane.showMessageDialog(ChessBoardView.getInstance() , msg, title, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * 
	 * Hàm khởi tạo đọc file properties
	 */
	public static void initProperties() {
		try {
			prop = new Properties();
			fis = new FileInputStream(Constants.CONFIG_FILE);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void closeConnection() {
		try {
			fis.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
