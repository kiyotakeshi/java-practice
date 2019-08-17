### JSP,Servletを使用した勤怠情報登録Webアプリ

- 実行環境
    - Java8
    - Tomcat8

- MVCモデルの学習で作成

---
### TODO

- CSS
- Validate

---
### MySQLのセットアップ

- イメージの取得

```
docker pull mysql
docker pull busybox
```

- コンテナの起動
```
docker run -v /var/lib/mysql --name mysql_data busybox
docker run --volumes-from mysql_data --name mysql -e MYSQL_ROOT_PASSWORD=mysql -d -p 3306:3306 mysql
```

- MySQLコンテナに入る

```
docker container ls
docker exec -e LANG=C.UTF-8 -it $(docker container ls --filter "name=mysql" -q) bash
mysql -u root -p mysql
```

- データベースの作成
```
DROP DATABASE FinalSubject;

CREATE DATABASE FinalSubject;

```
- テーブルの作成

```
CREATE TABLE FinalSubject.company_info (
company_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT'会社ID シーケンスによる自動採番',
company_name VARCHAR(50) COMMENT'会社名',
abbreviation VARCHAR(3) COMMENT'会社の略称',
is_deleted CHAR(1)  DEFAULT 0 COMMENT'会社が削除したかを判定(0:存在、1:削除)',
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT'登録した日時',
modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT'更新した日時',
created_id VARCHAR(20) COMMENT'登録したログインID',
modified_id VARCHAR(20) COMMENT'更新したログインID')
ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT='登録された会社情報を管理する';

CREATE TABLE FinalSubject.employee_info (
employee_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT'社員ID',
name VARCHAR(20)  COMMENT'社員の氏名',
name_hiragana VARCHAR(20) COMMENT'社員の氏名(ひらがな)',
birthday DATE  COMMENT'生年月日',
sex CHAR(1) COMMENT'性別(0:男、1:女)',
mail_address VARCHAR(50) COMMENT'メールアドレス',
telephone_number VARCHAR(13) COMMENT'電話番号(“-”(ハイフン)あり)',
company_info_id  INT COMMENT'所属会社の会社ID',
business_manager VARCHAR(20) COMMENT'担当管理営業の氏名',
department CHAR(1) COMMENT'事業部(0:開発、1:NW、2:検証、3:オフィス、4:管理)',
commissioning_status CHAR(1) COMMENT'稼働状況(0:未稼働、1:稼働)',
is_deleted CHAR(1) DEFAULT 0 COMMENT '社員情報を削除したかを判定(0:存在、1:削除)',
created_id VARCHAR(20) COMMENT'登録したログインID',
modified_id VARCHAR(20) COMMENT'更新したログインID',
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT'登録した日時',
modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT'更新した日時',
FOREIGN KEY (company_info_id) REFERENCES company_info(company_id))
ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT='登録された社員の情報を管理する';

CREATE TABLE FinalSubject.employee_state (
employee_info_id INT COMMENT'社員ID',
enter_date DATE  COMMENT'入社日',
retire_date DATE COMMENT'退職日',
status char(1) COMMENT'ステータス（0:在籍、1:退職,２：入社待,３:入社取り消し',
is_deleted CHAR(1) DEFAULT 0 COMMENT '社員状況を削除したかを判定(0:存在、1:削除)',
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT'登録した日時',
modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT'更新した日時',
created_id VARCHAR(20) COMMENT'登録したログインID',
modified_id VARCHAR(20) COMMENT'更新したログインID',
FOREIGN KEY (employee_info_id) REFERENCES employee_info(employee_id))
ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT='登録された社員状況を管理する';


CREATE TABLE FinalSubject.login_info(
login_id VARCHAR(20) COMMENT'ログインID',
password VARCHAR(20) COMMENT'パスワード',
is_deleted CHAR(1) DEFAULT 0 COMMENT 'ログイン情報を削除したかを判定(0:存在、1:削除)',
created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT'登録した日時',
modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT'更新した日時',
created_id VARCHAR(20) COMMENT'登録したログインID',
modified_id VARCHAR(20) COMMENT'更新したログインID')
ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT='登録されたユーザの情報を管理する';
USE FinalSubject;

```

- レコードの作成

```
INSERT INTO company_info  (company_name, abbreviation, created_id,modified_id) values('Java株式会社','JA','test','java');
INSERT INTO company_info  (company_name, abbreviation, created_id,modified_id) values('インフラ株式会社','IF','test','java');
INSERT INTO company_info  (company_name, abbreviation, created_id,modified_id) values('データベース株式会社','DB','test','java');

INSERT INTO employee_info (name,name_hiragana,birthday,sex,mail_address,telephone_number,company_info_id,business_manager,department,commissioning_status, created_id, modified_id) VALUES('Java太郎','じゃゔぁたろう','1995/09/28',0,'testshi@mhko.co','080-0000-0000','1','田社長',0,0, 'test','java');
INSERT INTO employee_info (name,name_hiragana,birthday,sex,mail_address,telephone_number,company_info_id,business_manager,department,commissioning_status, created_id, modified_id) VALUES('Java二郎','じゃゔぁじろう','1995/09/28',0,'testshi@mhko.co','080-0111-0111','3','田社長',0,0, 'test','java');
INSERT INTO employee_info (name,name_hiragana,birthday,sex,mail_address,telephone_number,company_info_id,business_manager,department,commissioning_status, created_id, modified_id) VALUES('インフラ太郎','いんふらたろう','1995/09/28',0,'testshi@mhko.co','080-0222-0222','2','田社長',1,0, 'test','java');
INSERT INTO employee_info (name,name_hiragana,birthday,sex,mail_address,telephone_number,company_info_id,business_manager,department,commissioning_status, created_id, modified_id) VALUES('管理太郎','かんりたろう','1995/09/28',0,'testshi@mhko.co','080-0333-0333','2','田社長',4,0, 'test','java');

INSERT INTO employee_state (employee_info_id, enter_date, status, created_id,modified_id) VALUES(1,'2018/06/18',0,'test','java');
INSERT INTO employee_state (employee_info_id, enter_date, status, created_id,modified_id) VALUES(2,'2018/06/18',0,'test','java');
INSERT INTO employee_state (employee_info_id, enter_date, status, created_id,modified_id) VALUES(3,'2018/06/18',0,'test','java');
INSERT INTO employee_state (employee_info_id, enter_date, status, created_id,modified_id) VALUES(4,'2018/06/18',0,'test','java');


INSERT INTO login_info (login_id, password, created_id , modified_id) VALUES('test' , 'java','test','java' );
INSERT INTO login_info (login_id, password, created_id , modified_id) VALUES('java' , 'test','java','test' );

```

- テストデータの確認

```
SELECT * FROM employee_info;

SELECT * FROM employee_state;

```