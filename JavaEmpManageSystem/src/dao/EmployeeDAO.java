package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.EmployeeDTO;
import model.CalcAge;
import model.TypeCasting;


public class EmployeeDAO extends BaseDAO {

	/**
	 * 従業員全員のデータを取得するメソッド
	 **/
	public ArrayList<EmployeeDTO> find(){
		ArrayList<EmployeeDTO> empList = new ArrayList<EmployeeDTO>();

		try {
			con = connect();

			StringBuilder sb = new StringBuilder();
			sb.append("select * from employee_state As es ");
			sb.append("join employee_info AS ei ON ");
			sb.append("(es.employee_info_id = ei.employee_info_id)");
			sb.append("left join company_info AS ci ");
			sb.append("on (ei.company_info_id = ci.company_id);");

			// SQLの準備
			PreparedStatement pstmt = con.prepareStatement(sb.toString());
			System.out.println(pstmt);

			// SQLの実行
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				EmployeeDTO ed = new EmployeeDTO();

				ed.setEmployeeId(rs.getInt("employee_id"));
				ed.setCompanyInfoId(rs.getInt("company_info_id"));
				ed.setDepartment(rs.getString("department"));
				ed.setName(rs.getString("name"));
				ed.setNameHiragana(rs.getString("name_hiragana"));

				// 年齢の計算
				int age = CalcAge.calcAge(rs.getDate("birthday").toLocalDate());

				ed.setAbbreviation(rs.getString("abbreviation"));
				ed.setAge(age);
				ed.setBusinessManager(rs.getString("bussiness_manager"));

				String eday = TypeCasting.toString(rs.getDate("enter_Date").toLocalDate());
				ed.setStrEnterDate(eday);
				ed.setCommissioningStatus(rs.getString("commissioning_status"));

				// リストに追加
				empList.add(ed);
			}
			rs.close();
			pstmt.close();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// 切断失敗の処理
					e.printStackTrace();
				}
			}
		}
		return empList;
	}
}
