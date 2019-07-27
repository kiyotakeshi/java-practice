### アプリケーションをつくってみる

- 実装する機能
    - ログイン、ログアウト、ツイート、ツイート閲覧

---
### はじめにつくる

- 単純に情報を保持するだけのJavaBeansクラスを作成

```
// User.java
// ユーザ情報を持つJavaBeansモデル

package model;

import java.io.Serializable;

public class User implements Serializable {

	private String name;
	private String pass;

	public User() {}
	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public String getName() { return name;}
	public String getPass() {return pass;}

}
```

```
// Mutter.java
// ツイートに関する情報をもつJavaBeansモデル

package model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private String userName;
	private String text;

	public Mutter() {}

	public Mutter(String userName, String text){
		this.userName = userName;
		this.text = text;
	}

	public String getUserName() {return userName;}
	public String getText() { return text;}
}
```

---
- トップ画面の作成

```
// index.jsp
// ブラウザから直接リクエストするため、WebContext直下に配置
// デフォルトページのためファイル名を省略してもアクセス可能

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>
<body>
<h1>Welcome to Tubuyaki</h1>
</body>
</html>
```

---
### ログイン機能を作成

```
// User.java(修正)

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		// Userインスタンス(ユーザ情報)の生成
		User user = new User(name, pass);

		// ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(user);

		// ログイン成功時の処理
		if(isLogin) {
			// ユーザ情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}

		// ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
```

```
// LoginLogic.java

package model;

public class LoginLogic {
	public boolean execute(User user) {
		if(user.getPass().equals("1234")) { return true;}
		return false;
	}
}
```

```
// index.jsp(修正)

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>
<body>
<h1>Welcome to Tubuyaki</h1>
<form action="/Tsubuyaki/Login" method="post">
UserName:<input type="text" name="name"><br>
Password:<input type="password" name="pass"><br>
<input type="submit" value="Login">
</form>
</body>
</html>
```

```
// loginResult.jsp

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="model.User" %>
  <%
  //　セッションスコープからユーザ情報を取得
  User loginUser = (User) session.getAttribute("loginUser");
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>

<body>
<h1>Tsubuyaki Login</h1>
<% if(loginUser != null){ %>
	<p>Login sucess!</p>
	<p>Welcome <%= loginUser.getName() %></p>
	<a href="/Tsubuyaki/Main">Let's Tsubuyaki</a>
<% } else { %>
	<p>Login failed...</p>
	<a href="/Tsubuyaki/">go to Top...</a>
<% } %>
</body>
</html>
```

```
// Login.java
// ログインに関するリクエストを処理するコントローラ

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		// Userインスタンス(ユーザ情報)の生成
		User user = new User(name, pass);

		// ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(user);

		// ログイン成功時の処理
		if(isLogin) {
			// ユーザ情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}

		// ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
```

- index.jspにアクセスするとログイン画面がでてくる
- passが1234の時にログインに成功

---
###