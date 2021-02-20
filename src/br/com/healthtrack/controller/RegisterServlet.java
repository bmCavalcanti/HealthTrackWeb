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
import br.com.healthtrack.entity.Weight;
import br.com.healthtrack.repository.GenderRepository;
import br.com.healthtrack.repository.UserRepository;
import br.com.healthtrack.repository.WeightRepository;
import br.com.healthtrack.util.EncryptUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserRepository ur;
	private GenderRepository gr;
	private WeightRepository wr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        ur = new UserRepository();
        gr = new GenderRepository();
        wr = new WeightRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			User user = new User();
			Weight weight = new Weight();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar birthday = Calendar.getInstance();
			birthday.setTime(format.parse(request.getParameter("birthday")));
					
			user.setName(request.getParameter("name"));
			user.setEmail(request.getParameter("email"));
			user.setBirthday(birthday);
			user.setHeight(Float.parseFloat(request.getParameter("height")));
			user.setGender(gr.get(Integer.parseInt(request.getParameter("gender"))));
			user.setPassword(EncryptUtil.encrypt(request.getParameter("password")));	
			user.setCreatedAt(Calendar.getInstance());

			int user_validation = ur.emailIsRegistred(user.getEmail());
			
			if (user_validation > 0) {
				request.setAttribute("message", "Este e-mail já está sendo usado.");
				request.setAttribute("type", "danger");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
		
			int user_id = ur.insert(user);
			
			user.setId(user_id);
			weight.setCreatedAt(Calendar.getInstance());
			weight.setValue(Float.parseFloat(request.getParameter("weight")));
			weight.setUser(user);
			
			wr.insert(weight);
			
			if (user_id > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("user_id", user_id);
				response.sendRedirect("dashboard");	
			} else {
				request.setAttribute("message", "Não foi possível realizar o cadastro.");
				request.setAttribute("type", "danger");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Erro ao tentar realizar a ação.");
			request.setAttribute("type", "danger");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}	
	}
}
