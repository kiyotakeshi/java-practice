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
### メイン画面の作成

```
// main.jsp
// メイン画面を出力するビュー

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.User" %>
 <%
// セッションスコープに保存されたユーザ情報を取得
User loginUser = (User) session.getAttribute("loginUser");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>
<body>
<h1>Tsubuyaki Main</h1>
<p>
<%= loginUser.getName() %> is login-user
</p>
</body>
</html>
```

```
// Main.java
// ツイートに関するリクエストを処理するコントローラー
// セッションスコープ内のUserインスタンスの取得を試みてログイン状況を確認
// アプリケーションスコープ内のツイートリストを確認してなければ空のリストを作成

package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.User;
/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ツイートリストをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List <Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

		// 取得できなかった際にはツイートリストを新規作成し、アプリケーションスコープに保存
		if(mutterList == null) {
			mutterList = new ArrayList<Mutter>();
			application.setAttribute("mutterList", mutterList);
		}

		// ログインしているか確認するためセッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {

			// ログインしていない場合リダイレクト(index.jsp)
			response.sendRedirect("/Tsubuyaki/");
		} else {

			// ログインしている場合はフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}
}
```

---
### ログアウト機能

```
// Logout.java
// ログアウトに関するリクエストを処理するコントローラー

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションスコープを破棄
		HttpSession session = request.getSession();
		session.invalidate();

		// ログアウト画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/logout.jsp");
		dispatcher.forward(request, response);
	}
}
```

```
// main.jsp(修正)
// ログアウト処理を追加

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.User" %>
 <%
// セッションスコープに保存されたユーザ情報を取得
User loginUser = (User) session.getAttribute("loginUser");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>
<body>
<h1>Tsubuyaki Main</h1>
<p>
<%= loginUser.getName() %> is login-user
<a href="/Tsubuyaki/Logout">Logout</a>
</p>
</body>
</html>
```

```
// logout.jsp

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>
<body>
<h1>Tsubuyaki logout</h1>
<p>Logout!!</p>
<a href="/Tsubuyaki/">go to Top page...</a>
</body>
</html>
```

---
### 投稿の閲覧機能

```
// PostMutterLogic.java
// ツイートの投稿に関する処理を行うモデル

package model;

import java.util.List;

public class PostMutterLogic {

	// 0番の位置にmutterListを格納
	// 指定した位置(0番)にインスタンが格納されている場合は以前のものを後ろにずらす
	public void execute(Mutter mutter, List<Mutter> mutterList) {
		mutterList.add(0, mutter);
	}
}
```

```
// Main.java(修正)
// ツイートに関するリクエストを処理するコントローラー(POSTとして呼び出された場合)
// GETで呼ばれた場合は単にメイン画面を表示する

package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.PostMutterLogic;
import model.User;
/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ツイートリストをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List <Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

		// 取得できなかった際にはツイートリストを新規作成し、アプリケーションスコープに保存
		if(mutterList == null) {
			mutterList = new ArrayList<Mutter>();
			application.setAttribute("mutterList", mutterList);
		}

		// ログインしているか確認するためセッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {

			// ログインしていない場合リダイレクト(index.jsp)
			response.sendRedirect("/Tsubuyaki/");
		} else {

			// ログインしている場合はフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		// 入力値のチェック
		if(text != null && text.length() != 0) {

			// アプリケーションスコープに保存されたツイートリストを取得
			ServletContext application = this.getServletContext();
			List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

			// セッションスコープに保存されたユーザ情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			// ツイートをツイートリストに追加
			Mutter mutter = new Mutter(loginUser.getName(), text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter, mutterList);

			// アプリケーションスコープにツイートリストを保存
			application.setAttribute("mutterList", mutterList);

			// メイン画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}
}
```

```
// main.jsp(修正)
// メイン画面を出力するビュー

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.User,model.Mutter,java.util.List" %>
 <%

// セッションスコープに保存されたユーザ情報を取得
User loginUser = (User) session.getAttribute("loginUser");

 // アプリケーションスコープに保存されたツイートリストを取得
 List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>
<body>
<h1>Tsubuyaki Main</h1>
<p>
<%= loginUser.getName() %> is login-user
<a href="/Tsubuyaki/Logout">Logout</a>
</p>
<p><a href="/Tsubuyaki/Main">Renew</a></p>
<form action="/Tsubuyaki/Main" method="post">
<input type="text" name="text">
<input type="submit" value="tweet">
</form>
<% for(Mutter mutter : mutterList) { %>
	<p><%= mutter.getUserName() %>:<%= mutter.getText() %></p>
<% } %>
</body>
</html>
```

---
### エラーメッセージの表示

- 空のツイートを投稿するとエラーメッセージを投稿

```
// Mina.java(修正)
// doPost()に条件を追加

package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.PostMutterLogic;
import model.User;
/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ツイートリストをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List <Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

		// 取得できなかった際にはツイートリストを新規作成し、アプリケーションスコープに保存
		if(mutterList == null) {
			mutterList = new ArrayList<Mutter>();
			application.setAttribute("mutterList", mutterList);
		}

		// ログインしているか確認するためセッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {

			// ログインしていない場合リダイレクト(index.jsp)
			response.sendRedirect("/Tsubuyaki/");
		} else {

			// ログインしている場合はフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		// 入力値のチェック
		if(text != null && text.length() != 0) {

			// アプリケーションスコープに保存されたツイートリストを取得
			ServletContext application = this.getServletContext();
			List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

			// セッションスコープに保存されたユーザ情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			// ツイートをツイートリストに追加
			Mutter mutter = new Mutter(loginUser.getName(), text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter, mutterList);

			// アプリケーションスコープにツイートリストを保存
			application.setAttribute("mutterList", mutterList);

		} else {

			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "Any messeges please");
		}

			// メイン画面にフォワード
			// ツイート内容のエラーの有無に関係なく必要な処理
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
	}
}
```

```
// main.jsp
// エラーメッセージの取得と表示に関する部分を追加

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.User,model.Mutter,java.util.List" %>
 <%

// セッションスコープに保存されたユーザ情報を取得
User loginUser = (User) session.getAttribute("loginUser");

 // アプリケーションスコープに保存されたツイートリストを取得
 List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

 // リクエストスコープに保存されたエラーメッセージを取得
 String errorMsg = (String) request.getAttribute("errorMsg");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>
<body>
<h1>Tsubuyaki Main</h1>
<p>
<%= loginUser.getName() %> is login-user
<a href="/Tsubuyaki/Logout">Logout</a>
</p>
<p><a href="/Tsubuyaki/Main">Renew</a></p>
<form action="/Tsubuyaki/Main" method="post">
<input type="text" name="text">
<input type="submit" value="tweet">
</form>
<% if(errorMsg != null) { %>
<p><%= errorMsg %></p>
<% } %>

<% for(Mutter mutter : mutterList) { %>
	<p><%= mutter.getUserName() %>:<%= mutter.getText() %></p>
<% } %>

</body>
</html>
```

---
### ツイートの保存先をデータベースに変更する

- アプリケーションスコープからDBに変更
	- サーバの停止や再開をしてもデータが残る

- JDBCドライバを配置

```
kiyota-MacBook-Pro:lib kiyotatakeshi$ pwd
/Applications/Eclipse_2019-06.app/Contents/workspace/Tsubuyaki/WebContent/lib
kiyota-MacBook-Pro:lib kiyotatakeshi$ ls -l
total 5064
-rwxr-xr-x  1 kiyotatakeshi  admin  2166760  7 30 21:48 h2-1.4.199.jar
-rwxr-xr-x  1 kiyotatakeshi  admin    30527  7 30 23:22 jstl-api-1.2.jar
-rwxr-xr-x  1 kiyotatakeshi  admin   391957  7 30 23:22 jstl-impl-1.2.jar
```

- DBの操作の練習

```
CREATE TABLE EMPLOYEE (
  ID CHAR(6) PRIMARY KEY,
  NAME VARCHAR(100) NOT NULL,
  AGE INT NOT NULL
);

INSERT INTO EMPLOYEE (ID, NAME, AGE)
  VALUES('EMP001', 'MIKE', 23);
INSERT INTO EMPLOYEE(ID, NAME, AGE)
  VALUES('EMP002', 'TOM', 22);

CREATE TABLE EMPLOYEE (
  ID CHAR(6) PRIMARY KEY, NAME VARCHAR(100) NOT NULL,
   AGE INT NOT NULL
);
```

- 今回使用するDBの準備
```
// データベース Tsubuyaki を作成

// テーブル MUTTER を作成

CREATE TABLE MUTTER(
	ID INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL,
	TEXT VARCHAR(255) NOT NULL,
);

// MUTTERテーブルにレコードを追加
INSERT INTO MUTTER (NAME, TEXT) VALUES ('MIKE', 'TODAY IS HOLIDAY');
INSERT INTO MUTTER (NAME, TEXT) VALUES ('TOM', 'GOOD');

// 確認
select * from mutter;
ID  	NAME  	TEXT
1	MIKE	TODAY IS HOLIDAY
2	TOM	GOOD
(2 行, 5 ms)
```

