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
import model.LoginUser;

/**
 * ログインに成功すると表示される従業員一覧画面
 * http://localhost:8080/JavaEmpManageSystem/list
 * のコントローラー
 */
@WebServlet("/list")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログインユーザか確認
		HttpSession session = request.getSession();
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("./login");
		}

		// 従業員のデータを取得し配列に格納
		EmployeeDAO empDao = new EmployeeDAO();
		ArrayList<EmployeeDTO> detailList = empDao.find();

		// 会社のデータを取得し配列に格納
		CompanyDAO cmpDao = new CompanyDAO();
		ArrayList<CompanyDTO> cmpList = cmpDao.findCompany();

		// 配列をセッションスコープにセット
		request.setAttribute("detailList", detailList);
		request.setAttribute("cmpList",cmpList );

		// enumの値をセッションスコープにセット
        // values() で、列挙したオブジェクトをすべて持つ配列が得られる
		request.setAttribute("comStatus", CommissioningStatus.values());
		request.setAttribute("depart", Department.values());

		// 従業員一覧画面(view)にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/employeeList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
