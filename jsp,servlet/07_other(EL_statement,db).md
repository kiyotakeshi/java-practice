### EL式

- JSPファイルでのJavaBeansインスタンスの記述を楽にする

```
// セッションスコープに属性名「human」で保存されている
// Humanインスタンスのnameプロパティの値を出力する

// 今までの書き方
<%@ page import="model.Human" %>
<% Human human = (Human) session.getAttribute("human"); %>
<%= human.getName() %>

// EL式を使う
// 指定したプロパティのgetterが自動で実行される
${human.name}

```

- EL式がインスタンスを探し出す優先順位
1. ページスコープ
2. リクエストスコープ
3. セッションスコープ
4. アプリケーションスコープ

- 複数のスコープに同じ属性のインスタンスがある場合は指定する

```
// セッションスコープに保存されたインスタンスを利用する
${sessionScope.msg}
```

- 書き直してみる

- healthCheckResult.jsp(診断結果を出力するビュー)

```
// 以前の書き方

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Health" %>

<%
// リクエストスコープに保存されたHealthインスタンスを取得
Health health = (Health) request.getAttribute("health");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sukkiri health check</title>
</head>
<body>
<h1>Sukkiri health check result</h1>
<p>
height:<%= health.getHeight() %><br>
weight:<%= health.getWeight() %><br>
BMI:<%= health.getBmi() %><br>
BodyType:<%= health.getBodyType() %>
</p>
<a href="/example/HealthCheck">return</a>
</body>
</html>

```

```
// EL式を使用

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Health" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sukkiri health check</title>
</head>
<body>
<h1>Sukkiri health check result</h1>
<p>
height:${health.height}<br>
weight:${health.weight}<br>
BMI:${health.bmi}<br>
BodyType:${health.bodyType}
</p>
<a href="/example/HealthCheck">return</a>
</body>
</html>
```

- EL式でリストやマップといった「コレクションクラス」を利用する

```
// 0番目のHumanインスタンスのgetName()メソッドが実行されnameの値が取得できる
${humanList[0].name}
```

- EL式でスクリプト要素は使えない(繰り返しや条件分岐には使用不可)

---
### JSTL

- EL式で分岐や繰り返しで使用するためにJSTLというカスタムタグを使用する

- JSTLを使用するためにはJARファイルをダウンロードし、動的Webプロジェクトに配置する必要がある

```
// 変数の値を出力(<c:out>タグを使用)
// < > などの HTMLで使われる記号の出力をエスケープして出力してくれる
// ユーザが入力した値を出力する場合は、クロスサイトスクリプティング(XSS)を防ぐために必須
<c:out value="${human.name}" />
```

```
// 分岐(<c:if>)
<c:if test="{human.age>=20}">
    You are Adult.
</c:if>
```

```
// 多分岐(<c:choose>)
<c:choose>
    <c:when test="${human.age>=20}">
        You are Adult.
    </c:when>
    <c:otherwise>
        You are Minor.
    </c:otherwise>
</c:choose>
```

```
// 繰り返し(<c:forEach>)
<c:forEach var="i" begin="0" end="9" step="1">
    <c:out value="${i}" />
</c:forEach>
```

```
// 拡張forと同じ処理(<c:forEach var="Variable" items="instance">)
// スコープに保存されているArrayListに格納されたHumanインスタンスを先頭から順に取得して利用

<c:forEach var="human" items="${humanList}">
    Name:${human.name}
    Age:${human.age}
</c:forEach>
```

---
- JSTLを使用したプログラムの書き直し

- main.jsp(メイン画面を表示するビュー)

```
// 書き直す前

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

// JSTLを使用して書く

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sum.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tsubuyaki</title>
</head>
<body>
<h1>Tsubuyaki Main</h1>
<p>
<c:out value="${loginUser.name}" /> is login-user
<a href="/Tsubuyaki/Logout">Logout</a>
</p>

<p><a href="/Tsubuyaki/Main">Renew</a></p>
<form action="/Tsubuyaki/Main" method="post">
<input type="text" name="text">
<input type="submit" value="tweet">
</form>

<c:if test="${not empty errorMsg}">
    <p>${errorMsg}</p>
</c:if>
<c:forEach var="mutter" items="${mutterList}">
    <p><c:out value="${mutter.userName}" />:<c:out value="${mutter.text}" /></p>
</c:forEach>

</body>
</html>
```

- アクションタグとEL式を使うことでJSPファイルからJavaのコードをなくすことができる
    - ビューの担当の人が不用意にJavaのコードを触ってバグを起こすことを防げる

---
### DBの使用