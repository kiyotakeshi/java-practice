package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CompanyDAO;
import dao.EmployeeDAO;
import dto.CompanyDTO;
import dto.EmployeeDTO;
import enumrate.CommissioningStatus;
import enumrate.Department;
import enumrate.IsDeleted;
import enumrate.Sex;
import enumrate.Status;
import model.LoginUser;

/**
 * 社員詳細情報画面
 * http://localhost:8080/JavaEmpManageSystem/detail
 * http://localhost:8080/JavaEmpManageSystem/detail?empId=
 * のコントローラー
 */
@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログインユーザか確認
		HttpSession session = request.getSession();
		LoginUser user = (LoginUser) session.getAttribute("loginUser");
		if (user == null) {
			response.sendRedirect("./login");
		}

		// enumの値をセッションスコープにセット
        // values() で、列挙したオブジェクトをすべて持つ配列が得られる
		request.setAttribute("sex", Sex.values());
		request.setAttribute("comissionState", CommissioningStatus.values());
		request.setAttribute("depart", Department.values());
		request.setAttribute("delete", IsDeleted.values());
		request.setAttribute("state", Status.values());

		// 会社のデータを取得し配列に格納
		CompanyDAO cmpDao = new CompanyDAO();
		ArrayList<CompanyDTO> cmpList = cmpDao.findCompany();

		// 配列をセッションスコープに格納
		request.setAttribute("cmpList",cmpList );
		request.setCharacterEncoding("UTF-8");

		// list.jspのリクエストパラメータを取得
		// 詳細か削除のリンクを押している場合、値が格納されている
		String strEmpId = request.getParameter("empId");

		// 新規登録か更新登録かを判定
		if (strEmpId == null || strEmpId == "") {

			// リクエストスコープの中身がない場合は新規登録
			request.setAttribute("btn", "登録");
		} else {
			request.setAttribute("btn", "更新");

			// 1人分のDAOをDBから値を取得しリクエストスコープにセット
			EmployeeDAO empDao = new EmployeeDAO();
			int empId = Integer.parseInt(strEmpId);
			EmployeeDTO empDto = empDao.findByEmployeeId(empId);

			request.setAttribute("emp", empDto);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/employeeDetail.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * employeeDetail.jspで登録か更新を行った際の
	 * Postリクエストの処理を行う
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// System.out.println("post");
		request.setCharacterEncoding("UTF-8");

		// ログインユーザか確認
		HttpSession session = request.getSession();
		LoginUser user = (LoginUser) session.getAttribute("loginUser");

		if (user  == null) {
			response.sendRedirect("./login");
		}

		EmployeeDTO empDto = new EmployeeDTO();

		// 更新処理かを判断する変数
		boolean isUpdate = false;

		// リクエストスコープ内にuserIdがある場合は更新処理になる
		String strUserId = request.getParameter("userId");

		int empId = 0;

		// nullかつ空ではない場合(必ず値が入っている必要がある)
		if (strUserId != null && !strUserId.isEmpty()) {
			empId = Integer.parseInt(strUserId);
			empDto.setEmployeeId(empId);
			isUpdate = true;
		}

		// リクエストスコープ からemployeeDetail.jsp
		// で入力した値を取得し、変数に格納
		String name = request.getParameter("name");
		String nameHiragana = request.getParameter("nameHiragana");
		String birthday = request.getParameter("birthday");
		String sex = request.getParameter("sex");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		String companyId = request.getParameter("company");
		int comId = Integer.parseInt(companyId);
		String manager = request.getParameter("manager");
		String department = request.getParameter("department");
		String run = request.getParameter("run");
		String enterDay = request.getParameter("enterDay");
		String retireDay = request.getParameter("retireDay");
		String status = request.getParameter("status");

		// jspの入力値に問題がないかチェックする変数
		boolean jspParameterCheck = false;

		// 取得したパラメータがnullかつ空ではない(必ず値が入っている必要がある)かチェック
		// retireDay については別のスコープでチェック
		if (name != null && !name.isEmpty() && nameHiragana != null && !nameHiragana.isEmpty()
		        && birthday != null && !birthday.isEmpty() && sex != null && !sex.isEmpty() && mail != null && !mail.isEmpty()
		        && phone != null && !phone.isEmpty() && companyId != null && !companyId.isEmpty()
		        && manager != null && !manager.isEmpty() && department != null && !department.isEmpty() && run != null && !run.isEmpty()
		        && enterDay != null && !enterDay.isEmpty() &&  status != null && !status.isEmpty()){

			        // TODO:validation check

			// 値に問題がないためtrueにする
			jspParameterCheck = true;
		    }

			String url = "./detail";

			// 入力値が無効(false)の場合
			// System.out.println(jspParameterCheck);
			if(! jspParameterCheck) {

				// 更新処理の入力値が無効だった場合のリダイレクト先の設定
				if (isUpdate) {
					url += "?empId" + strUserId;
				}

				response.sendRedirect(url);
				// System.out.println("jsp parameter error");
				return;
			}

			// validationを通したjspの入力値をもとに
			// DTOのオブジェクトを作成
			empDto.setName(name);
			empDto.setNameHiragana(nameHiragana);

			LocalDate birth = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			empDto.setBirthday(birth);
			empDto.setSex(sex);
			empDto.setMailAddress(mail);
			empDto.setTelephoneNumber(phone);
			empDto.setCompanyInfoId(comId);
			empDto.setBusinessManager(manager);
			empDto.setDepartment(department);
			empDto.setCommissioningStatus(run);

			LocalDate eDay = LocalDate.parse(enterDay, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			empDto.setEnterDate(eDay);

			// 退職の有無を判定する変数を宣言
			LocalDate rDay;

			// 退職していた場合、型変換して値を格納
			if (retireDay != null && !retireDay.isEmpty()) {
				rDay = LocalDate.parse(retireDay, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			}
			// 退職日のデータがない場合はnullを格納
			else {
				rDay = null;
			}
			empDto.setRetireDate(rDay);
			empDto.setStatus(status);

			// 更新登録メソッドを実行
			if (isUpdate) {
				EmployeeDAO empDao = new EmployeeDAO();
				empDao.update(empDto, user.getLoginId());
				// System.out.println("update");
			}
			// 新規登録
			else {
				EmployeeDAO empDao = new EmployeeDAO();
				empDao.insert(empDto, strUserId);
			}

			// 社員一覧画面にリダイレクト
			response.sendRedirect("./list");
	}
}
