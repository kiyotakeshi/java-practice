### アプリケーションサーバ

- Webサーバが常時稼働してブラウザからのアクセスを待つ
- Webアプリケーションが動作した結果、表示される画面がWebページ(サーバサイドプログラム)
    - 実行結果をHTMLとして作成し、ブラウザにレスポンスする

- サーブレットはサーブレットクラス,JSPはJSPクラスというブラウザから実行できる特別なクラスを使用
    - JSPファイルは実行時にサーブレットクラスに変換される

- プログラムを動かす機能を持つサーバを「アプリケーションサーバ」と呼ぶ
    - アプリケーションサーバ内のサーブレットクラスを実行する機能をサーブレットコンテナ

- サーブレットクラスはブラウザからのリクエストによって実行され、結果をHTMLで出力する

---
### サーブレット

- サーブレットクラスの基本形(IDEが自動生成してくれる)

```
package servlet;

// サーブレットクラスを作成するために最低限必要なもの
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SampleServlet
 */

// URLパターンの設定
@WebServlet("/SampleServlet")

// サーブレットクラスの元となる HttpServletクラスを継承
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

//  doGet()メソッドはサーブレットクラスがリクエストされると
//  実行されるメソッド(メインメソッドのようなもの)
//  引数にブラウザからのリクエストが入り、その詳細情報を取り出して処理したものを
//  HttpServletResponseインスタンスを用いてブラウザに送り返す

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

- サーブレットクラスはファイル名ではなくURLパターンをリクエスト時のURLに指定
    - サーブレットクラスはURLパターンを設定しないとリクエストを実行することができない

```
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SampleServlet
 */
@WebServlet("/SampleServlet")
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

//		運勢を決定
		String[] luckArray = {"super sukkiri", "sukkiri", "bad"};

		int index = (int) (Math.random() * 3) ;
		String luck = luckArray[index];

//		実行日を取得
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String today = sdf.format(date);

//		HTMLを出力
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<tittle>fortune</tittle>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>"+ today + " fortune is .. "+ luck  + "</p>");
		out.println("</body>");
		out.println("</html>");
	}
}

// 実行結果(ブラウザで表示され、実行するたびに結果が変わる)
fortune

2019/07/23 fortune is .. bad

```

---
### JSP

- リクエストされるとJSPファイルをサーブレットクラスに変換

1. JSPファイルをコーディング
2. サーブレットクラスのソースファイルに変換
3. サーブレットクラスのクラスファイルにコンパイル
4. サーブレットクラスのインスタンス化

- HTMLの中にJAVAのコードを埋め込むことが可能
    - HTML部分をテンプレート,Javaのコード部分をスクリプト
    - スクリプトは、スクリプトレット、スクリプト式、スクリプト宣言からなる

- スクリプトレット

```
<% int x = 20 %>
```

- スリクプト式
    - 変数や戻り値を出力可能

```
<%= %>
```

---
- JSPを使ってみる

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Date,java.text.SimpleDateFormat"%>
<%
	// 運勢のリスト
	String[] luckArray = { "super_sukkiri", "sukkiri", "bad" };

	int index = (int) (Math.random() * 3);
	String luck = luckArray[index];

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	String today = sdf.format(date);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fortune</title>
</head>
<body>
<p><%= today %>'s fortune is ... <%= luck %></p>
</body>
</html>
```

---
### 確認

```
// Employee.java

package lesson;

public class Employee {
	private String id;
	private String name;

	public Employee(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
```

```
// lesson.jsp

<%@ page contentType="text/html;charset=UTF-8" import="lesson.Employee"%>
<%
	Employee emp = new Employee("002", "Mike");
%>

<!DOCTYPE html>
<html>
<body>
	<p> ID:<%=emp.getId()%>,Name is <%=emp.getName()%> </p>
</body>
</html>
```

- 実行結果
```
http://localhost:8080/lesson/lesson.jsp
 ID:002,Name is Mike

kiyota-MacBook-Pro:WebContent kiyotatakeshi$ curl http://localhost:8080/lesson/lesson.jsp
<!DOCTYPE html>
<html>
<body>
	<p> ID:002,Name is Mike </p>
</body>
</html>
```
