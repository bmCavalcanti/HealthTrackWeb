package br.com.healthtrack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.entity.User;
import br.com.healthtrack.repository.GenderRepository;
import br.com.healthtrack.repository.UserRepository;
import br.com.healthtrack.util.EncryptUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/profile")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserRepository ur;
	private GenderRepository gr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        ur = new UserRepository();
        gr = new GenderRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("user", ur.get((int) session.getAttribute("user_id")));
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = ur.get((int) session.getAttribute("user_id"));
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar birthday = Calendar.getInstance();
			birthday.setTime(format.parse(request.getParameter("birthday")));
					
			user.setName(request.getParameter("name"));
			user.setBirthday(birthday);
			user.setEmail(request.getParameter("email"));
			user.setHeight(Float.parseFloat(request.getParameter("height")));
			user.setGender(gr.get(Integer.parseInt(request.getParameter("gender"))));
			
			System.out.println(request.getParameter("old_password"));
			
			if (!request.getParameter("old_password").isEmpty() && !request.getParameter("password").isEmpty()) {
				String old_password = EncryptUtil.encrypt(request.getParameter("old_password"));
								
				if (!old_password.equals(user.getPassword())) {
					request.setAttribute("message", "A senha atual está incorreta.");
					request.setAttribute("type", "danger");
					request.getRequestDispatcher("profile.jsp").forward(request, response);
					return;
				} else {
					user.setPassword(EncryptUtil.encrypt(request.getParameter("password")));
				}
			}
			
			if (ur.update(user)) {
				request.setAttribute("message", "Perfil atualizado!");
				request.setAttribute("type", "success");				
			} else {
				request.setAttribute("message", "Erro ao tentar atualizar o perfil.");
				request.setAttribute("type", "danger");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Erro ao tentar realizar a ação.");
			request.setAttribute("type", "danger");
		}
		
		doGet(request, response);
	}

}
