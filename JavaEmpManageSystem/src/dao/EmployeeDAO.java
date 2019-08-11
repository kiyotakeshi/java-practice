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
	public ArrayList<EmployeeDTO> find() {
		ArrayList<EmployeeDTO> empList = new ArrayList<EmployeeDTO>();

		try {
			con = connect();

			StringBuilder sb = new StringBuilder();
			sb.append("select * from employee_state As es ");
			sb.append("join employee_info AS ei ON ");
			sb.append("(es.employee_info_id = ei.employee_id) ");
			sb.append("left join company_info AS ci ");
			sb.append("on (ei.company_info_id = ci.company_id);");

			// SQLの準備
			PreparedStatement pstmt = con.prepareStatement(sb.toString());
			System.out.println(pstmt);

			// SQLの実行
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
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
				ed.setBusinessManager(rs.getString("business_manager"));

				String eday = TypeCasting.toString(rs.getDate("enter_date").toLocalDate());
				ed.setStrEnterDate(eday);
				ed.setCommissioningStatus(rs.getString("commissioning_status"));

				// リストに追加
				empList.add(ed);
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
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

	public void toDelete(int numEmpId) {

		try {
			con = connect();

			con.setAutoCommit(false);

			// employee_state が employee_info の外部キーで参照しているため
			// 先に employee_state を削除する必要がある
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM employee_state WHERE employee_info_id = ?");
			pstmt.setInt(1, numEmpId);
			pstmt.executeUpdate();

			pstmt = con.prepareStatement("DELETE FROM employee_info WHERE employee_id = ?");
			pstmt.setInt(1, numEmpId);
			pstmt.executeUpdate();

			con.commit();
		} catch (SQLException e) {
			// 接続失敗、またはSQL処理の失敗時
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// データベース接続の切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// 切断失敗の処理
					e.printStackTrace();
				}
			}
		}
	}

	public EmployeeDTO findByEmployeeId(int empId) {

		EmployeeDTO empInfo = null;
		try {
			con = connect();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM employee_state AS es ");
			sb.append("JOIN employee_info AS ei ");
			sb.append("ON (es.employee_info_id = ei.employee_id) ");
			sb.append("LEFT JOIN company_info AS ci ");
			sb.append("ON (ei.company_info_id = ci.company_id)");
			sb.append("WHERE es.employee_info_id = ?");

			// SQLの準備
			PreparedStatement pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, empId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				empInfo = new EmployeeDTO();

				empInfo.setEmployeeId(rs.getInt("employee_id"));
				empInfo.setName(rs.getString("name"));
				empInfo.setNameHiragana(rs.getString("name_hiragana"));

				// DBではdate型のためLocalDate型を経由してString型にキャスト
				String birthday = TypeCasting.toString(rs.getDate("birthday").toLocalDate());
				empInfo.setStrBirthday(birthday);

				empInfo.setSex(rs.getString("sex"));
				empInfo.setMailAddress(rs.getString("mail_address"));
				empInfo.setTelephoneNumber(rs.getString("telephone_number"));
				empInfo.setCompanyInfoId(rs.getInt("company_info_id"));
				empInfo.setAbbreviation(rs.getString("abbreviation"));
				empInfo.setBusinessManager(rs.getString("business_manager"));
				empInfo.setDepartment(rs.getString("department"));
				empInfo.setCommissioningStatus(rs.getString("commissioning_status"));

				String eday = TypeCasting.toString(rs.getDate("enter_date").toLocalDate());
				empInfo.setStrEnterDate(eday);

				if (rs.getDate("retire_date") != null) {
					String rday = TypeCasting.toString(rs.getDate("retire_date").toLocalDate());
					empInfo.setStrRetireDate(rday);
				}

				empInfo.setStatus(rs.getString("status"));
			}

		} catch (SQLException e) {
			// 接続失敗、またはSQL処理の失敗時
			e.printStackTrace();
		} finally {
			// データベース接続の切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// 切断失敗の処理
					e.printStackTrace();
				}
			}
		}
		return empInfo;
	}
}
