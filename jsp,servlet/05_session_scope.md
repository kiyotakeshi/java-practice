### セッションスコープ

- リクエストスコープは保存したインスタンスがリクエストをまたげない(リクエストの終了とともに消滅)
- セッションスコープに保存されたインスタンスは利用者がブラウザを閉じるか明示的にインスタンスを削除するまで使用可能

- ユーザ登録機能の実装
    - http://localhost:8080/example/RegisterUser でアクセス

```
// User.java
// 登録するユーザを表すJavaBeansのモデル

package model;

import java.io.Serializable;
public class User implements Serializable {

		private String id;
		private String name;
		private String pass;

		public User() {}

		public User(String id, String name, String pass) {
			this.id = id;
			this.name = name;
			this.pass = pass;
		}

		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public String getPass() {
			return pass;
		}
}
```

```
// RegisterUserLogic.java
// ユーザ登録を行うモデル(ファイルやDB登録は行わない)

package model;

public class RegisterUserLogic {

		public boolean execute(User user) {
			// 登録処理
			return true;
		}
}
```

```
// RegisterUser.java
// ユーザ登録に関するリクエストを処理するコントローラー

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegisterUserLogic;
import model.User;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// フォワード先
		String forwardPath = null;

		// サーブレットクラスの動作を決定する action の値を
		// リクエストラパラメーターから取得
		String action = request.getParameter("action");

		// 登録の開始をリクエストされた時の処理
		if(action == null) {

			// フォワード先
			forwardPath = "/WEB-INF/jsp/registerForm.jsp";
		}

		// 登録認画面から登録実行をリクエストされた時の処理
		else if(action.equals("done")) {

			// セッションスコープに保存された登録ユーザを取得
			HttpSession session = request.getSession();
			User registerUser = (User) session.getAttribute("registerUser");

			// 登録処理の呼び出し
			RegisterUserLogic logic = new RegisterUserLogic();
			logic.execute(registerUser);

			// 不要となったセッションスコープ内のインスタンスを削除
			session.removeAttribute("registerUser");

			// 登録後のフォワード先を設定
			forwardPath = "/WEB-INF/jsp/registerDone.jsp";
		}

		// 設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		// 登録するユーザの情報を設定
		User registerUser = new User(id, name, pass);

		// セッションスコープに登録ユーザを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request, response);
	}
}
```

```
// registerForm.jsp
// ユーザ登録入力画面を出力するビュー

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User register</title>
</head>
<body>
<form action="/example/RegisterUser" method="post">
Login ID:<input type="text" name="id"><br>
Password:<input type="password" name="pass"><br>
Name:<input type="text" name="name"><br>
<input type="submit" value="confirm">
</form>
</body>
</html>
```

```
// registerConfirm.jsp
// ユーザ登録確認画面を出力するビュー

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.User" %>
<%
User registerUser = (User) session.getAttribute("registerUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User register</title>
</head>
<body>
<p>User register,OK?</p>
<p>
Login ID:<%= registerUser.getId() %><br>
Name:<%= registerUser.getName() %><br>
</p>
<a href="/example/RegisterUser">return</a>

<!-- リンクでリクエストパラメータを送る方法 -->
<a href="/example/RegisterUser?action=done">register</a>
</body>
</html>
```

```
// registerDone.jsp
// ユーザ登録完了画面を出力するビュー

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User register</title>
</head>
<body>
<p>register seccess!</p>
<a href="/example/RegisterUser">return</a>
</body>
</html>
```

```
// RegisterUser.java
// ユーザ登録に関数するリクエストを処理するコントローラ

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegisterUserLogic;
import model.User;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// フォワード先
		String forwardPath = null;

		// サーブレットクラスの動作を決定する action の値を
		// リクエストラパラメーターから取得
		String action = request.getParameter("action");

		// 登録の開始をリクエストされた時の処理
		if(action == null) {

			// フォワード先
			forwardPath = "/WEB-INF/jsp/registerForm.jsp";
		}

		// 登録認画面から登録実行をリクエストされた時の処理
		else if(action.equals("done")) {

			// セッションスコープに保存された登録ユーザを取得
			HttpSession session = request.getSession();
			User registerUser = (User) session.getAttribute("registerUser");

			// 登録処理の呼び出し
			RegisterUserLogic logic = new RegisterUserLogic();
			logic.execute(registerUser);

			// 不要となったセッションスコープ内のインスタンスを削除
			session.removeAttribute("registerUser");

			// 登録後のフォワード先を設定
			forwardPath = "/WEB-INF/jsp/registerDone.jsp";
		}

		// 設定されたフォワード先にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		// 登録するユーザの情報を設定
		User registerUser = new User(id, name, pass);

		// セッションスコープに登録ユーザを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request, response);

	}
}
```

---
### セッションスコープの補足

- セッションスコープの正体であるHttpSessionインスタンスはユーザ(ブラウザ)ごとに作成される

- APサーバがHttpSessionインスタンスを作成する(最初のgetSession()を実行)とセッションIDを発行
    - セッションIDをHttpSessionインスタンスとブラウザに設定することで両者を紐づける
    - リクエストの度に設定されたセッションIDを送信する
    - サーバがレスポンスにクッキーを含めることでレスポンスを受信したブラウザはクッキーにセッションIDを含める

- セッションスコープの多用はAPサーバのメモリ不足を引き起こす
    - HttpSessionインスタンスはすぐにガベージコレクションの対象にならないため
    - HttpSessionインスタンスが不要になったかはサーバは判断できない
    - セッションタイムアウトがくるとガベージコレクションの対象とする

- 短期間にリクエストが集中した際にはガベージコレクションが間に合わないことがあるため開発者が積極的にセッションスコープを管理する

- リクエストをまたいでユーザの情報を保持する通信を**ステートフル**な通信と呼ぶ
- リクエストをまたいで情報を保持できない通信を**ステートレス**な通信と呼ぶ
    - HTTPはステートレスなためセッションスコープやクッキーなどでステートフルを実現する

---
### 確認

- リクエストがあるとインスタンスを作成し、セッションスコープを使いJSPに受け渡す

```
// FrutInstance.java

package lesson;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FrutInstance")
public class FrutInstance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fruit f = new Fruit("apple",200);

		// リクエストスコープを使用
//		request.setAttribute("fruit", f);

		// セッションスコープを使用
		HttpSession session = request.getSession();
		session.setAttribute("fruit",f);

		RequestDispatcher d = request.getRequestDispatcher("/show.jsp");
		d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
package lesson;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FrutInstance")
public class FrutInstance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fruit f = new Fruit("apple",200);

		// リクエストスコープを使用
//		request.setAttribute("fruit", f);

		// セッションスコープを使用
		HttpSession session = request.getSession();
		session.setAttribute("fruit",f);

		RequestDispatcher d = request.getRequestDispatcher("/show.jsp");
		d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

```

```
// Fruit.java

package lesson;

public class Fruit implements java.io.Serializable {

	private String name;
	private int price;

	public Fruit() {}

	public Fruit(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
}
```

```
// show.jsp

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="lesson.Fruit" %>
 <% Fruit fruit = (Fruit) session.getAttribute("fruit");  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fruit price</title>
</head>
<body>
<p><%= fruit.getName() %> price is <%= fruit.getPrice() %> yen.</p>
</body>
</html>
```