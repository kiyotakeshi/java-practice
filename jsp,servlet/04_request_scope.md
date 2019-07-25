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
