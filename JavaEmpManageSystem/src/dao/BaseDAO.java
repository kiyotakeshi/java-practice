package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 全てのテーブル共通のDBにアクセスするための処理
 */
public class BaseDAO {

	private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	// DB名がFinalSubject
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/FinalSubject";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysql";

	Connection con = null;

	/**
	 * ドライバにつなぐ処理
	 * @return con
	 * @throws SQLException
	 */
	public Connection connect() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// DBに接続
		con = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

		return con;
	}
}
