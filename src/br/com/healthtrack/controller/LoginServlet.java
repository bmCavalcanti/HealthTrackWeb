package br.com.healthtrack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.entity.User;
import br.com.healthtrack.repository.UserRepository;
import br.com.healthtrack.util.EncryptUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserRepository ur;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        ur = new UserRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		
		user.setEmail(request.getParameter("email"));
		user.setPassword(EncryptUtil.encrypt(request.getParameter("password")));
		
		int user_id = ur.validateLogin(user);
		
		if (user_id != 0) {
			HttpSession session = request.getSession();
			
			session.setAttribute("user_id", user_id);
			response.sendRedirect("dashboard");
		} else {
			request.setAttribute("message", "Usuário e/ou senha incorretos.");
			request.setAttribute("email", user.getEmail());
			request.setAttribute("type", "danger");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
