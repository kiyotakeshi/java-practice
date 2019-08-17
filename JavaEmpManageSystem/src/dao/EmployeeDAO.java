package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dto.EmployeeDTO;
import model.CalcAge;
import model.TypeCasting;

/**
 * 従業員データにアクセスするためのDAO
 */
public class EmployeeDAO extends BaseDAO {

	/**
	 * 従業員全員のデータを取得するメソッド
	 * @return EmployeeDTO
	 */
	public ArrayList<EmployeeDTO> find() {

		// ArrayListは要素数が決まっていない動的な配列(Listは初期化時に要素数が決まっている)
		ArrayList<EmployeeDTO> empList = new ArrayList<EmployeeDTO>();

		try {
			con = connect();

			// StringBuilderインスタンスが内部に連結した文字列を蓄える
			// メモリ領域(バッファ)を持つため、 + 演算子を使うより高速で処理できる
			StringBuilder sb = new StringBuilder();

			// appendメソッドの戻り値はStringBuilderのため
			// 自身への参照を返すメソッドを連続して呼び出される(メソッドチェーン)
			sb.append("select * from employee_state As es ");
			sb.append("join employee_info AS ei ON ");
			sb.append("(es.employee_info_id = ei.employee_id) ");
			sb.append("left join company_info AS ci ");
			sb.append("on (ei.company_info_id = ci.company_id) ");
			sb.append("order by employee_id ASC;");

			// SQLの準備(StringBuilderをString型に変換)
			PreparedStatement pstmt = con.prepareStatement(sb.toString());
			// System.out.println(pstmt);

			// SQLの実行し、結果をrsに格納
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// 従業員1人ごとのデータ
				EmployeeDTO ed = new EmployeeDTO();

				// DBのレコードをDTOにセット
				// ex) employee_idレコードをemployeeIdにセット
				ed.setEmployeeId(rs.getInt("employee_id"));
				ed.setCompanyInfoId(rs.getInt("company_info_id"));
				ed.setDepartment(rs.getString("department"));
				ed.setName(rs.getString("name"));
				ed.setNameHiragana(rs.getString("name_hiragana"));

				// 年齢の計算を行うメソッドに引数を渡すため
				// birthdayレコードをLocalDate型に変換
				int age = CalcAge.calcAge(rs.getDate("birthday").toLocalDate());
				ed.setAge(age);

				ed.setAbbreviation(rs.getString("abbreviation"));
				ed.setBusinessManager(rs.getString("business_manager"));

				String eday = TypeCasting.toString(rs.getDate("enter_date").toLocalDate());
				ed.setStrEnterDate(eday);

				ed.setCommissioningStatus(rs.getString("commissioning_status"));

				// メソッドの返り値である配列に従業員データを追加
				empList.add(ed);
			}

			// SQLの終了処理
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

	/**
	 * empIdをもとにDBから情報を取得するメソッド
	 * @param empId
	 * @return EmployeeDTO
	 */
	public EmployeeDTO findByEmployeeId(int empId) {

		EmployeeDTO empInfo = null;
		try {
			con = connect();

			// StringBuilderインスタンスが内部に連結した文字列を蓄える
			// メモリ領域(バッファ)を持つため、 + 演算子を使うより高速で処理できる
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM employee_state AS es ");
			sb.append("JOIN employee_info AS ei ");
			sb.append("ON (es.employee_info_id = ei.employee_id) ");
			sb.append("LEFT JOIN company_info AS ci ");
			sb.append("ON (ei.company_info_id = ci.company_id)");

			// メソッドの引数、empIdを使用してレコードを取得
			sb.append("WHERE es.employee_info_id = ?");

			// SQLの準備
			PreparedStatement pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, empId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				// DBから取得したデータをDTO(Data Transfer Object)に格納
				empInfo = new EmployeeDTO();

				empInfo.setEmployeeId(rs.getInt("employee_id"));
				empInfo.setName(rs.getString("name"));
				empInfo.setNameHiragana(rs.getString("name_hiragana"));

				// DBから取得したするとdate型のため
				// LocalDate型を経由してString型にキャスト
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

				// 退職日のデータが取得できている場合
				// 型変換してDTOにセット
				if (rs.getDate("retire_date") != null) {
					String rday = TypeCasting.toString(rs.getDate("retire_date").toLocalDate());
					empInfo.setStrRetireDate(rday);
				}

				empInfo.setStatus(rs.getString("status"));
			}

		// 接続失敗、またはSQL処理の失敗時
		} catch (SQLException e) {
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

	/**
	 * jspで入力した値で、
	 * リクエストスコープ、DTOを経由し、DBのレコードを更新するメソッド
	 * @param empDto
	 * @param loginId
	 */
	public void update(EmployeeDTO empDto, String loginId) {

		try {
			con = connect();

			// employee_info テーブルの設定
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE employee_info SET ");
			sb.append("name = ?,name_hiragana = ?,birthday = ?,sex  = ?,mail_address = ?, ");
			sb.append("telephone_number = ?,company_info_id = ?,business_manager = ?,department = ?, ");
			sb.append("commissioning_status = ?, modified_id = ? ");
			sb.append("WHERE employee_id = ?;");

			PreparedStatement pstmt = con.prepareStatement(sb.toString());

			// 自動コミット機能をオフにして、複数のsqlをまとめてコミットする
			con.setAutoCommit(false);

			// ? にjspの入力をもとにDTOに格納した値を使用する
			pstmt.setString(1, empDto.getName());
			pstmt.setString(2, empDto.getNameHiragana());
			pstmt.setDate(3, java.sql.Date.valueOf(empDto.getBirthday()));
			pstmt.setString(4, empDto.getSex());
			pstmt.setString(5, empDto.getMailAddress());
			pstmt.setString(6, empDto.getTelephoneNumber());
			pstmt.setLong(7, empDto.getCompanyInfoId());
			pstmt.setString(8, empDto.getBusinessManager());
			pstmt.setString(9, empDto.getDepartment());
			pstmt.setString(10, empDto.getCommissioningStatus());
			pstmt.setString(11, loginId);
			pstmt.setInt(12, empDto.getEmployeeId());

			// update後は社員一覧画面に戻すため
			// 結果を rs に格納する必要はない
			System.out.println(pstmt);
			pstmt.executeUpdate();

			// employee_state テーブルの設定　
			StringBuilder sb2 = new StringBuilder();
			sb2.append("UPDATE employee_state SET");
			sb2.append(" enter_date = ?,retire_date = ?, ");
			sb2.append("status = ?,modified_id = ? where employee_info_id = ?; ");

			pstmt = con.prepareStatement(sb2.toString());

			pstmt.setDate(1, java.sql.Date.valueOf(empDto.getEnterDate()));

			// 退職日のデータがある場合、
			// LocalDate型関数を経由してテーブルの型(Date）の値をセット
			LocalDate rd = empDto.getRetireDate();
			if (rd != null) {
				pstmt.setDate(2, java.sql.Date.valueOf(rd));
			} else {
				pstmt.setDate(2, null);
			}

			pstmt.setString(3, empDto.getStatus());
			pstmt.setString(4, loginId);
			pstmt.setInt(5, empDto.getEmployeeId());

			// System.out.println(pstmt);
			// SQLの実行
			pstmt.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			// 接続、またはSQL処理の失敗時
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

	/**
	 * jspで入力した値で、
	 * リクエストスコープ、DTOを経由し、DBのレコードを更新するメソッド
	 * @param empDto
	 * @param userId
	 */
	public void insert(EmployeeDTO empDto, String userId) {

		try {
			con = connect();

			// SQLの準備
			// 登録されている中でもっとも大きいIDの値を取得
			PreparedStatement pstmt = con.prepareStatement("SELECT MAX(employee_id) AS MAXID FROM employee_info");

			// SQLの実行
			ResultSet rs = pstmt.executeQuery();

			// 新規登録するIDを準備
			int id = 0;
			if (rs.next()) {
				id = rs.getInt("MAXID") + 1;
			}

			con.setAutoCommit(false);

			// employee_info テーブルへの登録
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO employee_info ");
			sb.append("(employee_id,name, name_hiragana, birthday, ");
			sb.append("sex,mail_address,telephone_number, ");
			sb.append("company_info_id, business_manager, ");
			sb.append("department, commissioning_status, ");
			sb.append("created_id, modified_id) VALUES ");
			sb.append("(?,?,?,?,?,?,?,?,?,?,?,?,?); ");

			pstmt = con.prepareStatement(sb.toString());

			pstmt.setInt(1, id);
			pstmt.setString(2, empDto.getName());
			pstmt.setString(3, empDto.getNameHiragana());
			pstmt.setDate(4, java.sql.Date.valueOf(empDto.getBirthday()));
			pstmt.setString(5, empDto.getSex());
			pstmt.setString(6, empDto.getMailAddress());
			pstmt.setString(7, empDto.getTelephoneNumber());
			pstmt.setLong(8, empDto.getCompanyInfoId());
			pstmt.setString(9, empDto.getBusinessManager());
			pstmt.setString(10, empDto.getDepartment());
			pstmt.setString(11, empDto.getCommissioningStatus());
			pstmt.setString(12, userId);
			pstmt.setString(13, userId);

			// 新規登録後は一覧画面に移動するため
			// 結果を格納は不要
			pstmt.executeUpdate();

			// employee_state テーブルへの登録
			StringBuilder sb2 = new StringBuilder();
			sb2.append("INSERT INTO employee_state ");
			sb2.append("(employee_info_id, enter_date, retire_date, ");
			sb2.append("status, created_id, modified_id) ");
			sb2.append("VALUES(?,?,?,?,?,?)");

			pstmt = con.prepareStatement(sb2.toString());

			pstmt.setInt(1, id);
			pstmt.setDate(2, java.sql.Date.valueOf(empDto.getEnterDate()));
			LocalDate rd = empDto.getRetireDate();
			if (rd != null) {
				pstmt.setDate(3, java.sql.Date.valueOf(rd));
			} else {
				pstmt.setDate(3, null);
			}

			pstmt.setString(4, empDto.getStatus());
			pstmt.setString(5, userId);
			pstmt.setString(6, userId);

			// 同様に結果を格納は不要
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
}