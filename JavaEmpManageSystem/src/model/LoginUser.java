package model;

import java.io.Serializable;

/**
 * ログイン時に使用した変数の
 * コンストラクタ、getter/setterを保持するクラス
 */
public class LoginUser implements Serializable {

	private String loginId;
	private String password;

	public LoginUser() {}

	/**
	 *  login.jspでの入力値を使用するコンストラクタ
	 * @param loginId
	 * @param password
	 */
	public LoginUser(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
