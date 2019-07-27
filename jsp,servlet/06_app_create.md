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
