/*
 * Copyright(C) 2018 Luvina Software Company
 *
 * ChessPieceReader.java, Jul 18, 2018 Latrodectus
 */

package pro_test_caro_buitrunghieu.fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import pro_test_caro_buitrunghieu.entity.ChessPiece;
import pro_test_caro_buitrunghieu.utils.Common;
import pro_test_caro_buitrunghieu.utils.Constants;

/**
 * Class chứa các hàm đọc thế cờ từ file thế cờ
 * 
 * @author Latrodectus
 *
 */
public class ChessPieceReader {
	// Arraylist lưu trữ các file thế cờ được đọc từ file
	private static ArrayList<ChessPiece> listChessPiece = new ArrayList<>();

	/**
	 * 
	 * Hàm đọc file thế cờ vào ArrayList
	 * 
	 * @return trả về ArrayList các thế cờ
	 */
	public static ArrayList<ChessPiece> readFile() {
		try {
			// Khời tạo đối tượng FileReader theo đường dẫn bên dưới
			FileReader fr = new FileReader(Constants.CHESSPIECE_FILEPATH);
			// Khởi tạo đối tượng bufferReader theo fr
			BufferedReader br = new BufferedReader(fr);
			// Biến đếm số dòng đã đọc
			int i = 0;
			// Biến lưu giá trị dòng đọc
			String line = "";
			// Biến lưu giá trị của một thế cờ
			StringBuilder chessPieceStr = new StringBuilder();
			// Đọc file thế cờ đến cuối file
			while ((line = br.readLine()) != null) {
				// Tăng biến đếm
				i++;
				// Thêm một dòng vào string lưu thê cờ
				chessPieceStr.append(line);
				/*
				 * Khi đủ 5 dòng thì khởi tạo một đối tượng thế cờ và add vào ArrayList Sau đó
				 * làm rỗng string lưu thế cờ
				 */
				if (i % Constants.VALID_LINE_COUNT == 0) {
					listChessPiece.add(toChessPiece(chessPieceStr.toString()));
					chessPieceStr.delete(0, Constants.VALID_CHARACTER_COUNT);
				}
			}
			// đóng bufferReader
			br.close();
			// Đóng FileReader
			fr.close();
			/**
			 * Bắt các ngoại lệ
			 */
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		// Trả về list thế cờ được đọc tư file
		return listChessPiece;
	}

	/**
	 * 
	 * Hàm chuyển đổi từ String thành đối tượng thế cờ char 5x5
	 * 
	 * @param Chuỗi ký tự đầu vào hợp lệ
	 * @return thế cờ dưới dạng mảng hai chiều char 5x5
	 */
	public static ChessPiece toChessPiece(String str) {
		try {
			// Nếu độ dài của str = 25
			if (str.length() == Constants.VALID_CHARACTER_COUNT) {
				// Khởi tạo một mảng hai chiều kích thươc 5x5
				char[][] chessPiece = new char[Constants.CHESSPIECE_ROW][Constants.CHESSPIECE_COLUMN];
				/*
				 * Gán các phần tử trong string vào file thế cờ
				 */
				for (int i = 0; i < Constants.CHESSPIECE_ELEMENT_COUNT; i++) {
					chessPiece[i / Constants.CHESSPIECE_ROW][i % Constants.CHESSPIECE_COLUMN] = str.charAt(i);
				}
				// Khởi tạo đối tượng thế cờ từ char 5x5
				ChessPiece cp = new ChessPiece(chessPiece);
				// Trả về thế cờ
				return cp;
				/*
				 * Nếu độ dài không hợp lệ in ra thông báo và trả về null
				 */
			} else {
				System.out.println(Common.prop.getProperty("INVALID_STRING_MSG"));
			}
			/*
			 * Bắt các ngoại lệ
			 */
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
