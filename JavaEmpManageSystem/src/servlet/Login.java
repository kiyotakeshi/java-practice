package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.LoginUser;

/*
 * 一番最初にブラウザからアクセスされるページ
 * http://localhost:8080/JavaEmpManageSystem/login
 * のコントローラー
 */

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 初回ログイン時、ログアウト時に呼び出される
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Viewへフォワード
		// ユーザに見えているパスは `/JavaEmpManageSystem/login` のまま
		// WEB-INF ディレクトリ配下にjspを配置することでブラウザから直接リクエストできないようになる
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request,response);
	}

	// login.jspからのpostリクエストで呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		// LoginUserインスタンスの生成
		LoginUser loginUser = new LoginUser(loginId, password);
		// System.out.println(loginUser);

		// ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(loginUser);

		// if(isLogin == true)とは書かない
		if(isLogin) {

			// ユーザ情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);

			// Listのコントローラー(List.java)にリダイレクト
			response.sendRedirect("./list");
			return ;
		}

		// DBにレコードが存在しなかった時の処理
		else {

			// リクエストスコープ にエラーメッセージを格納
			request.setAttribute("message", "IDかpasswordが違います");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
