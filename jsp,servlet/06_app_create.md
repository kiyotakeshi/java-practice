### アプリケーションをつくってみる

- 実装する機能
    - ログイン、ログアウト、ツイート、ツイート閲覧

---
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
	public Mutter() {String userName, String text){
		this.userName = userName;
		this.text = text;
	}

	public String getUserName() {return userName;}
	public String getText() { return text;}
}
```

---
- トップ画面の作成