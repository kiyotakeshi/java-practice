package dto;

/**
 * loginDAOで取得した情報を保持するためのコンストラクタ
 * getter/setterを格納するクラス
 */
public class LoginDTO {
	private String loginId;
	private String password;

	public LoginDTO() {
	}

	public LoginDTO(String loginId, String password) {
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
