package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.LoginDTO;
import model.LoginUser;

/**
 * ログイン画面で入力されたユーザが
 * DBに存在するかを確認するクラス
 */
public class LoginDAO extends BaseDAO {

	/**
	 * DBに接続し、レコードが存在するかを返す
	 * @param LoginUser
	 * @return LoginDTO logDto
	 */
	public LoginDTO findByLogin(LoginUser LoginUser) {

            // 初期化
            LoginDTO logDto = null;

            System.out.println("dao");

        // DBと接続できるかを判定
        try {
            // DBと接続する処理をBaseDAOから呼び出す
            con = connect();

            // 送信するSQLの用意
            PreparedStatement pstmt = con.prepareStatement(
                    "SELECT LOGIN_ID,PASSWORD,IS_DELETED,CREATED,MODIFIED,CREATED_ID,MODIFIED_ID FROM login_info WHERE LOGIN_ID = ? AND PASSWORD =?");

            // userのIDとpasswordをgetterで取得しselect文の?に代入
            pstmt.setString(1, LoginUser.getLoginId());
            pstmt.setString(2, LoginUser.getPassword());
            System.out.println(pstmt);

            // SQL文の送信
            ResultSet rs = pstmt.executeQuery();
            //次の行がある場合、処理を続ける
            if (rs.next()) {
                String login_id = rs.getString("login_id");
                String password = rs.getString("password");
                String is_deleted = rs.getString("is_deleted");
                String created_id = rs.getString("created_id");
                String modified_id = rs.getString("modified_id");

                logDto = new LoginDTO(login_id, password);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(logDto);
        return logDto;
    }
}