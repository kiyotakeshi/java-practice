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

- Javaプログラムからデータベースを利用するに必要なもの
    - java.sqlパッケージのクラス
    - JDBCドライバと呼ばれるライブラリ
        - DB操作に必要なクラスやインターフェース群
        - DB開発元がJARファイルとして提供

- JARファイルはクラスやインターフェースをまとめて格納したファイル
    - クラスパスに追加やWEB-INF/lib以下に追加することでビルドパスに追加され利用可能に

```
kiyota-MacBook-Pro:lib kiyotatakeshi$ pwd
/Applications/Eclipse_2019-06.app/Contents/workspace/example/WebContent/WEB-INF/lib
kiyota-MacBook-Pro:lib kiyotatakeshi$ ls -l
total 5064
-rwxr-xr-x@ 1 kiyotatakeshi  admin  2166760  7 30 21:48 h2-1.4.199.jar
-rwxr-xr-x@ 1 kiyotatakeshi  admin    30527  7 30 23:22 jstl-api-1.2.jar
-rwxr-xr-x@ 1 kiyotatakeshi  admin   391957  7 30 23:22 jstl-impl-1.2.jar
```

- DBを利用するクラスは必ずDAO(Data Access Object)を利用する
    - DB操作(検索、更新、削除)を担当するクラス
    - JDBC特有のコードの修正が様々なところに混在しないため

- DAOクラスはテーブルごとに作成し、テーブル名+DAOとするのが一般的

---
- EMPLOYEEテーブルから全レコードを取得する

```
// EMPLOYEEテーブルの1件分のデータを格納するクラス
package model;

public class Employee {

	private String id;
	private String name;
	private int age;

	public Employee(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	public String getId() { return id;}
	public String getName() { return name;}
	public int getAge() { return age;}
}

```

```
// package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDAO {
	public List<Employee>  findAll(){

		Connection conn = null;
		List<Employee> empList = new ArrayList<Employee>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// DBへ接続
			// 接続先DB,ユーザ名、パスワード
			conn = DriverManager.getConnection("jdbc:h2:~/test/SAMPLE", "root", "Zaq12wsx");

			// SELECT文を準備
			String sql = "SELECT ID, NAME, AGE FROM EMPLOYEE";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行し、結果を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を
			// Employeeインスタンスに設定し、ArrayListインスタンスに追加
				while(rs.next()) {
					String id = rs.getString("ID");
					String name = rs.getString("NAME");
					int age = rs.getInt("AGE");
					Employee employee = new Employee(id,name,age);
					empList.add(employee);
				}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;

		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;

		} finally {
			// DB切断
			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return empList;
	}
}

```

```
// SelectEmployeeSample.java
// JDBC特有のコードを書く必要がない

package model;

import java.util.List;

import dao.EmployeeDAO;

public class SelectEmployeeSample {
	public static void main(String[] args) {

		// employeeテーブルの全レコードを取得
		EmployeeDAO empDAO = new EmployeeDAO();
		List <Employee> empList = empDAO.findAll();

		// 取得したレコードの内容を出力
		for(Employee emp: empList) {
			System.out.println("ID:" + emp.getId());
			System.out.println("Name:" + emp.getName());
			System.out.println("Age:" + emp.getAge() + "\n");
		}
	}
}
```

-

---

- MVCモデルではアプリケーションが扱う情報の管理はModelの役割
- ModelのクラスからDAOを利用するのが一般的

- コネクションプーリング
    - 事前にDBとの接続を複数確立して接続を使い回す方法
    - リクエストの度にDBとの接続の確立と切断はAPサーバの負荷が大きいため
    - 設定はAPサーバで行う
