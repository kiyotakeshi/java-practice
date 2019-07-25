### MVC

- Model,View,Controllerに分けてアプリケーションを開発すること
    - Modelは計算処理やデータの格納を行う
    - Viewはユーザに対して画面の表示を行う
    - Controllerはユーザからの要求を受け取り、処理の実行をモデルに依頼し、その結果をビューに依頼する

- 処理の流れ
1. ユーザがアプリケーションの機能を要求
2. コントローラーが要求を受け付ける(サーブレットクラス)
3. コントローラーがモデルに処理の実行を依頼する
4. モデルが処理を実行(webアプリケーションに関するクラスを含まない、一般的なJavaのクラス)
5. コントローラーが処理結果の表示をビューに依頼する
6. ビューがユーザの要求の結果を表示(JSPファイル)
7. ユーザは要求の結果を見る

- リクエストをうけるのはサーブレットクラス(コントローラー)
- レスポンスをするのはJSPファイル(ビュー)
- 処理を担うのは一般的なJavaのクラス(モデル)

---
### フォワーディングとリダイレクト

- サーブレットクラスからJSPファイルにフォワードすることでビューに出力処理を任せる

- JSPファイルを直接リクエストできないように WEB-INF ディレクトリ配下におく
    - MVCモデルに従い、ブラウザからリクエストされるのはサーブレットだけ
    - JSPファイルはサーブレットからフォワードされて動くことを前提に作成するため直接呼び出されるとまずい

- フォワードのサンプルプログラム
    - サーブレットクラスをリクエストするとJSPファイルを出力する

```
// ForwardServlet.java

package servlet;

import java.io.IOException;

// 追記
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForwardServlet")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// フォワードの設定
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/forward.jsp");
		dispatcher.forward(request, response);
	}
}
```

```
// /WEB-INF/jsp/forward.jsp

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forward sample</title>
</head>
<body>
	<h1>Forward sample</h1>
	<p>this is sample of forward</p>
</body>
</html>
```

---
- リダイレクト
    - サーブレットクラスから次のリクエスト先の指示をだして、ブラウザが再リクエストを行う

```
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RedirectServlet")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("/example/ForwardServlet");

//		URLを使ったリダイレクトの指定も可能
//		response.sendRedirect("http://localhost/example/ForwardServlet");
	}
}
```

---
- フォワードは同じアプリケーション内のサーブレットクラスやJSPクラスに処理を移す
    - リクエスト、レスポンスは1往復
    - アドレスバーに表示されるURLはリクエスト時のまま

- リダイレクトはブラウザに別のサーブレットクラスやJSPファイルをリクエストさせ、実行し直す
    - リクエスト、レスポンスは2往復
    - アドレスバーに表示されるURLはリダイレクト先のURLに変更

- フォワードが使えるときは、転送が早いのでフォワードを使う

---
### 確認

- アクセス時の乱数に応じてリダイレクトかフォワードする

```
http://localhost:8080/ex50/redirected.jsp
http://localhost:8080/ex50/Ex50servlet
```


```
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ex50servlet")
public class Ex50servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int rand = (int) (Math.random() * 10);

		if (rand % 2 == 1) {
			response.sendRedirect("/ex50/redirected.jsp");
		} else {
			RequestDispatcher d = request.getRequestDispatcher("/forwarded.jsp");
			d.forward(request, response);
		}
	}
}
```