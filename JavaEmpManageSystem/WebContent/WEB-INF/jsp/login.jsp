<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--  ログイン画面 -->
 <!-- Login.javaからフォワードされる -->
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <!--  ブラウザのタブに表示されるタイトル -->
    <title>社員管理システム</title>
</head>

<body>
    <h1>社員管理システム</h1>

	<!-- form内で入力フォームの設定を記述 -->
	<!--  Postリクエストを Login.java に送る -->
    <form action="./login" method="post">

    	<!-- ログイン失敗時にリクエストスコープ にあるメッセージを表示 -->
        ${message}<br>

		<!--  入力した値はリクエストスコープに格納され
				name="" の値を指定し、リクエストパラメータとして取得可能 -->
        ログインID <input type="text" name="loginId"><br>

        <!-- passwordを指定すると入力値をplain textとして表示しない -->
        パスワード<input type="password" name="password"><br>

        <!-- 送信ボタンにLoginと表示 -->
        <input type="submit" value="Login">
    </form>
</body>

</html>