package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CompanyDTO;

/**
 * 会社データにアクセスするためのDAO
 */
public class CompanyDAO extends BaseDAO{

	/**
	 * 全ての会社情報を取得し配列に格納するメソッド
	 * @return ArrayList<CompanyDAO>
	 */
	public ArrayList<CompanyDTO> findCompany(){

		// ArrayListは要素数が決まっていない動的な配列(Listは初期化時に要素数が決まっている)
		ArrayList<CompanyDTO> cmpList = new ArrayList<CompanyDTO>();

		try {
			con = connect();

			// SQLの準備(FinalSubjectデータベースのcompany_infoテーブルの情報を全取得)
			PreparedStatement pstmt = con.prepareStatement("select * from FinalSubject.company_info");

			// SQLの送信
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {

				// 会社1件ごとのデータ
				CompanyDTO cd = new CompanyDTO();

				// DBのレコードをDTOにセット
				cd.setCompanyId(rs.getInt("company_id"));
				cd.setCompanyName(rs.getString("company_name"));
				cd.setAbbreviation(rs.getString("abbreviation"));
				cd.setIsDeleted(rs.getString("is_deleted"));
				cd.setCreated(rs.getDate("created").toLocalDate());
				cd.setModified(rs.getDate("modified").toLocalDate());
				cd.setCreatedId(rs.getString("created_id"));
				cd.setModifiedId(rs.getString("modified_id"));

				// メソッドの返り値である配列に会社のデータを追加
				cmpList.add(cd);
			}

			rs.close();
			pstmt.close();

		// SQL実行のエラーハンドリング
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cmpList;
	}
}
