package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {

	private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/Tsubuyaki";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysql";

	// 全レコードを取得するメソッド
	public List<Mutter> findAll(){
		Connection conn = null;
		List<Mutter> mutterList = new ArrayList<Mutter>();
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			// SELECT文の準備
			// ORDER BY ID DESCでIDが大きい順に並び替える(投稿が新しい順になる)
			String sql = "SELECT ID,NAME,TEXT FROM MUTTER ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をArrayListに格納
			while(rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				Mutter mutter = new Mutter(id, userName, text);

				mutterList.add(mutter);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			// DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return mutterList;
	}

	// レコードを追加するメソッド
	public boolean create(Mutter mutter) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

			// INSERT文の準備(idは自動連番)
			String sql = "INSERT INTO MUTTER(NAME, TEXT) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERT文の?に使用する値を設定
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());

			// INSERT文の実行
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}
