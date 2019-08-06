package model;

import dao.LoginDAO;
import dto.LoginDTO;

public class LoginLogic {

		public boolean execute(LoginUser loginUser) {
			LoginDAO dao = new LoginDAO();
			LoginDTO account = dao.findByLogin(loginUser);
			return account != null;
		}
}