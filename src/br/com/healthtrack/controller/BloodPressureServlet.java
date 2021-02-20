package br.com.healthtrack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.entity.BloodPressure;
import br.com.healthtrack.entity.User;
import br.com.healthtrack.repository.UserRepository;
import br.com.healthtrack.repository.BloodPressureRepository;

/**
 * Servlet implementation class BloodPressureServlet
 */
@WebServlet("/bloodpressure")
public class BloodPressureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserRepository ur;
	private BloodPressureRepository bpr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BloodPressureServlet() {
        super();
        ur = new UserRepository();
        bpr = new BloodPressureRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		List<BloodPressure> blood_pressures = bpr.getAll((int) session.getAttribute("user_id"));
		request.setAttribute("blood_pressures", blood_pressures);
		request.getRequestDispatcher("list.bloodpressure.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User user = ur.get((int) session.getAttribute("user_id"));
		
		try {	
			if (action.equals("insert")) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar created_at = Calendar.getInstance();
				created_at.setTime(format.parse(request.getParameter("created_at")));
						
				BloodPressure blood_pressure = new BloodPressure();
				
				blood_pressure.setValue(request.getParameter("value"));
				blood_pressure.setCreatedAt(created_at);
				blood_pressure.setUser(user);
						
				if (bpr.insert(blood_pressure)) {
					request.setAttribute("message", "Press�o cadastrada com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar cadastrar a press�o.");
					request.setAttribute("type", "danger");
				}	
				
			} else if (action.equals("update")) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar created_at = Calendar.getInstance();
				created_at.setTime(format.parse(request.getParameter("created_at")));
						
				BloodPressure blood_pressure = new BloodPressure();
				
				blood_pressure.setId(Integer.parseInt(request.getParameter("id")));
				blood_pressure.setValue(request.getParameter("value"));
				blood_pressure.setCreatedAt(created_at);
				blood_pressure.setUser(user);
						
				if (bpr.update(blood_pressure)) {
					request.setAttribute("message", "Press�o atualizada com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar atualizar a press�o.");
					request.setAttribute("type", "danger");
				}
			} else if (action.equals("delete")) {
				
				if (bpr.delete(Integer.parseInt(request.getParameter("id")))) {
					request.setAttribute("message", "Press�o deletada com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar deletar a press�o.");
					request.setAttribute("type", "danger");
				}	
			}
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("message", "Erro ao tentar realizar a a��o.");
			request.setAttribute("type", "danger");
		}
		
		doGet(request, response);
	}
}
