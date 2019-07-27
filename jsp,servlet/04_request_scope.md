### リクエストスコープ

- サーブレットクラスとJSPファイルは独立したものなのでファイルをまたいでインスタンスを渡せない
    - サーブレットクラスが生成した占いの結果のStringインスタンスを、結果を表示するJSPファイルは受け取れない

- スコープを利用することでインスタンスを保存でき、サーブレットクラスとJSPファイル間でインスタンスの共有や受け渡しが可能に

- スコープに保存できるのはインスタンスだけ

- スコープではJavaBeansと呼ばれるクラスのインスタンスを保存

- 人間に関する情報を持つJavaBeans

```
package model;

// 直列化を可能にする
// インスタンスのフィールドの内容をバイト列に変換してファイルに保存し、再度インスタンスに復元する技術
import java.io.Serializable;

public class Human implements Serializable {

//	フィールドはカプセル化しメソッドを通じてのみアクセス可能にする
	private String name;
	private int age;

	public Human() {
	}

	public Human(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
}
```

---
- リクエストスコープ
    - リクエストごとに生成され、レスポンスを返すまで利用できる
    - フォワード元とフォワード先でインスタンスを共有できる

```
// サーブレットクラスでJavaBeansインスタンスをリクエストスコープに保存、取得

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Human human = new Human("mike" , 23);

//		リクエストスコープにインスタンスを保存
		request.setAttribute("human", human);

//		リクエストスコープからインスタンスを取得
		Human h = (Human) request.getAttribute("human")

	}
```

```
// JSPファイルでリクエストスコープを利用

<%@ page import="model.Human" %>
<%
// リクエストスコープからインスタンスを取得
Human h = (Human) request.getAttribute("human");
%>
<%= h.getName() %> is <%= h.getAge() %> age
```

---
- BMIを求めるサンプルプログラムの作成

```
// 健康診断に関する情報を(身長、体重、BMI,体型)をもつJavaBeansのモデル
package model;

import java.io.Serializable;


public class Health implements Serializable {
	private double height, weight, bmi;
	private String bodyType;

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
}
```

```
// 健康診断に関する処理(BMI値算出、体型判定)を行うモデル
package model;

public class HealthCheckLogic {

	//	 BMIを算出して設定
	public void execute(Health health) {
		double weight = health.getWeight();
		double height = health.getHeight();
		double bmi = weight / (height / 100.0 * height / 100.0);
		health.setBmi(bmi);

		//		BMI指数から体型を判定して設定
		String bodyType;
		if (bmi < 18.5) {
			bodyType = "skinny";
		} else if (bmi < 25) {
			bodyType = "normal";
		} else {
			bodyType = "fat";
		}
		health.setBodyType(bodyType);
	}

}
```

```
// 健康診断に関するリクエストを処理するコントローラー
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Health;
import model.HealthCheckLogic;

@WebServlet("/HealthCheck")
public class HealthCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// フォワード
		RequestDispatcher dispathcher = request.getRequestDispatcher("/WEB-INF/jsp/healthCheck.jsp");
		dispathcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータを取得
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");

		// 入力値をプロパティに設定
		Health health = new Health();
		health.setHeight(Double.parseDouble(height));
		health.setWeight(Double.parseDouble(weight));

		// 健康診断を実行し、結果を設定
		HealthCheckLogic healthCheckLogic = new HealthCheckLogic();
		healthCheckLogic.execute(health);

		// リクエストスコープに保存
		request.setAttribute("health", health);

		// フォワード
		RequestDispatcher dispathcher = request.getRequestDispatcher("/WEB-INF/jsp/healthCheckResult.jsp");
		dispathcher.forward(request, response);
	}

}
```

```
// 健康診断画面を表示するビュー
// healthCheck.jsp

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sukkiri health check</title>
</head>
<body>
<h1>Sukkiri health check</h1>
<form action="/example/HealthCheck" method="post">
height:<input type="text" name="height">(cm)<br>
weight:<input type="text" name="weight">(kg)<br>
<input type="submit" value="diagnosis">
</form>
</body>
</html>
```

```
// 診断結果を出力するビュー
// healthCheckResult.jsp

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

---
### 確認

- GETリクエストによりFruitインスタンスを生成して画面に表示する
	- リクエストスコープを使用
	
```
package lesson;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class FrutInstance
 */
@WebServlet("/FrutInstance")
public class FrutInstance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fruit f = new Fruit("banana", 300);
		request.setAttribute("fruit", f);
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
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="lesson.Fruit" %>
<% Fruit fruit = (Fruit) request.getAttribute("fruit");  %>
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
