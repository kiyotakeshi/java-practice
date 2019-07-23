### フォーム

- GETリクエストを使うとき
    - アドレスバーにURLを入力、リンクをクリック、ブックマークを選択したときに使われる
    - リクエストパラメータが情報を取得するために利用される場合
    - `http://example.com/search?q=java&`のようにURL末尾に `?名前=値&`の形式でリクエストパラメータ情報を付加
    - 送信した結果を保存、共有する場合に使用

- POSTリクエストを使うとき
    - method属性がpostのフォームの送信ボタンをクリックしたときのみ使われる
    - リクエストパラメータが情報の登録で利用される場合
    - formタグのaction属性に記述されたURLにそのままリクエスト
    - リクエストの本文としてリクエストパラメータ情報を伝送
    - データをアドレスバーに表示したくない場合に使用(個人情報や機密情報)

- リクエストパラメータはアプリケーションサーバによりHttpServletRequestインスタンスに格納
    - 送信先(リクエスト)のサーブレットクラスやJSPファイルに渡される
    - サーブレットクラスやJSPファイルはHttpServletRequestメソッドを利用してリクエストパラメータを取り出す

---
- フォーム送信元と送信先で対応させるポイント

1. action属性とサーブレットクラスのURLパターン
2. method属性とサーブレットクラスの実行メソッド
3. HTMLの文字コードとsetCharacterEncoding()メソッドの引数
4. 各部品のname属性とgetParameter()メソッドの引数

```
// フォーム(JSPファイル)

<%@ page contentType="text/html;charset=UTF-8" %>
<form action="/example/Hoge" method="post">
Name: <input type="text" name="name">
<input type="submit" value="送信"></form>

```

```
// フォームのリクエスト先(サーブレットクラス)

package lesson;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Lesson
 */
@WebServlet("/Hoge")
public class Hoge extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
	}

}

```
- 一致していなと起きるエラー

1. Hoge -> 404エラー
2. doPost -> 405エラー
3. UTF-8 -> リクエストパラメータが文字化け
4. name -> リクエストパラメータがnullになる

---
### 簡単なユーザ登録(DBは使わない)

- 名前と性別の両方を入力していれば成功

```
// formSample.jsp

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Register Modoki</title>
</head>
<body>
<form action="/example/FormSampleServlet"  method="post">
Name:<br>
<input type="text" name="name"><br>
Sex:<br>
Man<input type="radio" name="gender" value="0">
Woman<input type="radio" name="gender" value="1">
<input type="submit"  value="register">
</form>
</body>
</html>
```

```
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormSampleServlet
 */
@WebServlet("/FormSampleServlet")
public class FormSampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//		リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");

		//		リクエストパラメータとチェック
		String errorMsg = "";

//		リクエストパラメータがきちんと送信され、値が変数に格納されているかを確認している
//		単なる未入力チェックであれば "" と一致するか(空文字か)の比較でも良い
		if (name == null || name.length() == 0) {
			errorMsg += "there is no name<br>";
		}
		if (gender == null || gender.length() == 0) {
			errorMsg += "there is no gender<br>";
		} else {
			if (gender.equals("0")) {
				gender = "Man";
			} else if (gender.equals("1")) {
				gender = "Woman";
			}
		}

		//		表示するメッセージ
		String msg = name + "(" + gender + ") is registered";
		if (errorMsg.length() != 0) {
			msg = errorMsg;
		}

		//		HTMLを出力
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>User register result</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>" + msg + "</p>");
		out.println("</body>");
		out.println("</html>");
	}
}
```