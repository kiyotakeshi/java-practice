package model;

import dao.LoginDAO;
import dto.LoginDTO;

/**
 * ログイン処理を行う
 */
public class LoginLogic {

		/**
		 * 引数にしたユーザインスタンスのユーザ、パスワードが
		 * DBに存在するかを判断
		 * @param loginUser
		 * @return boolean
		 */
		public boolean execute(LoginUser loginUser) {
			LoginDAO dao = new LoginDAO();
			LoginDTO account = dao.findByLogin(loginUser);

			// DBにレコードが存在していれば返す
			return account != null;
		}
}