package servlet;

import java.io.IOException;
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("post");
	}

}
