package br.com.healthtrack.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.entity.BloodPressure;
import br.com.healthtrack.entity.User;
import br.com.healthtrack.repository.UserRepository;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserRepository ur;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        ur = new UserRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = ConnectionController.getInstance().getConnection();
		HttpSession session = request.getSession();
		User user = ur.get((int) session.getAttribute("user_id"));
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT w.value w_value, w.created_at w_created_at, bp.value bp_value, bp.created_at bp_created_at, pat.name pat_name, pa.created_at pa_created_at, ft.name ft_name, fd.created_at f_created_at FROM t_user u LEFT JOIN t_weight w ON u.id = w.user_id LEFT JOIN t_blood_pressure bp ON u.id = bp.user_id LEFT JOIN t_physical_activity pa ON u.id = pa.user_id LEFT JOIN t_physical_activity_type pat ON pat.id = pa.physical_activity_type_id LEFT JOIN t_food fd ON u.id = fd.user_id LEFT JOIN t_food_type ft ON ft.id = fd.food_type_id WHERE u.ID = ? ORDER BY w.created_at DESC, bp.created_at DESC, pa.created_at DESC, fd.created_at DESC FETCH NEXT 1 ROWS ONLY");
			stmt.setInt(1, user.getId());
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				
				java.sql.Date w_created_at = rs.getDate("W_CREATED_AT");
				Calendar wCreatedAt = Calendar.getInstance();
				if (w_created_at != null) {
					wCreatedAt.setTimeInMillis(w_created_at.getTime());	
				}
				
				java.sql.Date bp_created_at = rs.getDate("BP_CREATED_AT"); 
				Calendar bpCreatedAt = Calendar.getInstance(); 
				if (bp_created_at != null) {
					bpCreatedAt.setTimeInMillis(bp_created_at.getTime());	
				}

				java.sql.Date pa_created_at = rs.getDate("PA_CREATED_AT"); 
				Calendar paCreatedAt = Calendar.getInstance(); 
				if (pa_created_at != null) {
					paCreatedAt.setTimeInMillis(pa_created_at.getTime());
				}

				java.sql.Date f_created_at = rs.getDate("F_CREATED_AT"); 
				Calendar fCreatedAt = Calendar.getInstance(); 
				if (f_created_at != null) {
					fCreatedAt.setTimeInMillis(f_created_at.getTime());	
				}
				
				BloodPressure bp = new BloodPressure();
				bp.setValue(rs.getString("BP_VALUE"));

				request.setAttribute("wValue", rs.getFloat("W_VALUE"));
				request.setAttribute("wCreatedAt", wCreatedAt);
				request.setAttribute("bpValue", bp.getValue());
				request.setAttribute("bpNivel", bp.getNivel());
				request.setAttribute("bpCreatedAt", bpCreatedAt);
				request.setAttribute("patName", rs.getString("PAT_NAME"));
				request.setAttribute("paCreatedAt", paCreatedAt);
				request.setAttribute("ftName", rs.getString("FT_NAME"));
				request.setAttribute("fCreatedAt", fCreatedAt);
			}
			
			rs.close();

			if (count == 0) {
				System.out.println("Nada encontrado.");
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		}
		
		request.setAttribute("user", user);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
