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

@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		LoginUser user = (LoginUser) session.getAttribute("loginUser");
		if (user == null) {
			response.sendRedirect("./login");
		}

		request.setAttribute("sex", Sex.values());
		request.setAttribute("comissionState", CommissioningStatus.values());
		request.setAttribute("depart", Department.values());
		request.setAttribute("delete", IsDeleted.values());
		request.setAttribute("state", Status.values());

		CompanyDAO cmpDao = new CompanyDAO();
		ArrayList<CompanyDTO> cmpList = cmpDao.findCompany();
		request.setAttribute("cmpList",cmpList );

		request.setCharacterEncoding("UTF-8");

		// list.jspのリクエストパラメータを取得
		String num = request.getParameter("empId");

		// 新規登録か更新登録かを判定
		if (num == null || num == "") {
			request.setAttribute("btn", "登録");
		} else {
			request.setAttribute("btn", "更新");

			// 1人分のDAOをセット
			EmployeeDAO empDao = new EmployeeDAO();
			int number = Integer.parseInt(num);
			EmployeeDTO empDto = empDao.findByEmployeeId(number);

			request.setAttribute("emp", empDto);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/employeeDetail.jsp");
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		LoginUser user = (LoginUser) session.getAttribute("loginUser");

		if (user  == null) {
			response.sendRedirect("./login");
		}

		EmployeeDTO empDto = new EmployeeDTO();
		boolean isUpdate = false;

		// リクエストスコープ内にuserIdがある場合は更新処理になる
		String userId = request.getParameter("userId");
		int empId = 0;

		if (userId != null && !userId.isEmpty()) {
			empId = Integer.parseInt(userId);
			empDto.setEmployeeId(empId);
			isUpdate = true;
		}

		// employeeDetail.jsp で入力した値を変数に格納
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

		boolean jspParameterCheck = false;

		// 取得したパラメータがnullかチェック
		// retireDay については別のスコープでチェック
		if (name != null && !name.isEmpty() && nameHiragana != null && !nameHiragana.isEmpty()
		        && birthday != null && !birthday.isEmpty() && sex != null && !sex.isEmpty() && mail != null && !mail.isEmpty()
		        && phone != null && !phone.isEmpty() && companyId != null && !companyId.isEmpty()
		        && manager != null && !manager.isEmpty() && department != null && !department.isEmpty() && run != null && !run.isEmpty()
		        && enterDay != null && !enterDay.isEmpty() &&  status != null && !status.isEmpty()){

			// 値に問題がないためtrueにする
			jspParameterCheck = true;
			        // TODO:validation check
		    }

			String url = "./detail";

			// 入力値が無効の場合
			System.out.println(jspParameterCheck);
			if(! jspParameterCheck) {
				if (isUpdate) {
					url += "?empId" + userId;
				}

				response.sendRedirect(url);
				System.out.println("jsp parameter error");
				return;
			}

			// validationを通したjspの入力値をもとに
			// dtoのオブジェクトを作成
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

			// ifのスコープ外で使えるように宣言
			LocalDate rDay;

			// 退職していた場合、型変換して値を格納
			if (retireDay != null && !retireDay.isEmpty()) {
				rDay = LocalDate.parse(retireDay, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			} else {
				rDay = null;
			}
			empDto.setRetireDate(rDay);
			empDto.setStatus(status);

			// 更新登録
			if (isUpdate) {
				EmployeeDAO empDao = new EmployeeDAO();
				empDao.update(empDto, user.getLoginId());
				System.out.println("update");
			}
			// 新規登録
			else {
				EmployeeDAO empDao = new EmployeeDAO();
				empDao.insert(empDto, userId);
			}

			// 社員一覧画面にリダイレクト
			response.sendRedirect("./list");
	}
}
