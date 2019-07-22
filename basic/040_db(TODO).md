### データベース

- csvで扱うより複雑な検索が高速で可能(検索の仕事はDBMSが行ってくれる)

```
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {

        try {
            Class.forName("org.h2.Driver");
        }catch (ClassNotFoundException e){

//          JDBCドライバJARが見つからなかった場合の処理
            e.printStackTrace();
        }

        Connection con = null;

        try{
//          データベースの接続
            con = DriverManager.getConnection("jdbc:h2:~/test");
//          TODO:SQL送信処理


        }catch(SQLException e){
//          接続、SQL処理失敗時の処理
            e.printStackTrace();
        }finally {

//          データベース接続の切断
            if(con != null){
                try{
                    con.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
```