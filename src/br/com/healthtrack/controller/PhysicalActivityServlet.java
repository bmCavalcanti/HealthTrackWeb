package br.com.healthtrack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.entity.PhysicalActivity;
import br.com.healthtrack.entity.PhysicalActivityType;
import br.com.healthtrack.entity.User;
import br.com.healthtrack.repository.UserRepository;
import br.com.healthtrack.repository.PhysicalActivityRepository;
import br.com.healthtrack.repository.PhysicalActivityTypeRepository;

/**
 * Servlet implementation class PhysicalActivityServlet
 */
@WebServlet("/physicalactivity")
public class PhysicalActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserRepository ur;
	private PhysicalActivityRepository par;
	private PhysicalActivityTypeRepository patr;
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhysicalActivityServlet() {
        super();
        ur = new UserRepository();
        par = new PhysicalActivityRepository();
        patr = new PhysicalActivityTypeRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<PhysicalActivity> physical_activities = par.getAll((int) session.getAttribute("user_id"));
		List<PhysicalActivityType> physical_activity_types = patr.getAll();
		
		Map<Calendar, List<PhysicalActivity>> physical_activities_map = physical_activities.stream().collect(Collectors.groupingBy(PhysicalActivity::getCreatedAt));
		
		Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		
		physical_activities.removeIf(pa -> !pa.getCreatedAt().equals(calendar));
		
		Float today_calories = 0f;
		int today_time = 0;
		for (PhysicalActivity pa : physical_activities) {
			today_calories += pa.getCalories();
			String[] split = pa.getDuration().split(":", 2);
			int mins = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
			today_time += mins;
		}
				
		request.setAttribute("physical_activities", physical_activities_map);
		request.setAttribute("physical_activity_types", physical_activity_types);
		request.setAttribute("today_calories", String.format("%.2f", today_calories));
		request.setAttribute("today_time", today_time);
		request.getRequestDispatcher("list.physicalactivity.jsp").forward(request, response);
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
						
				PhysicalActivity physical_activity = new PhysicalActivity();
				
				physical_activity.setPhysicalActivityType(patr.get(Integer.parseInt(request.getParameter("physical_activity_type"))));
				physical_activity.setCalories(Float.parseFloat(request.getParameter("calories")));
				physical_activity.setDescription(request.getParameter("description"));
				physical_activity.setDuration(request.getParameter("duration"));
				physical_activity.setCreatedAt(created_at);
				physical_activity.setUser(user);
				
				if (par.insert(physical_activity)) {
					request.setAttribute("message", "Atividade física cadastrada com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar cadastrar a atividade física.");
					request.setAttribute("type", "danger");
				}	
				
			} else if (action.equals("update")) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar created_at = Calendar.getInstance();
				created_at.setTime(format.parse(request.getParameter("created_at")));
						
				PhysicalActivity physical_activity = new PhysicalActivity();
				
				physical_activity.setId(Integer.parseInt(request.getParameter("id")));
				physical_activity.setPhysicalActivityType(patr.get(Integer.parseInt(request.getParameter("physical_activity_type"))));
				physical_activity.setCalories(Float.parseFloat(request.getParameter("calories")));
				physical_activity.setDescription(request.getParameter("description"));
				physical_activity.setDuration(request.getParameter("duration"));
				physical_activity.setCreatedAt(created_at);
				physical_activity.setUser(user);
				
				if (par.update(physical_activity)) {
					request.setAttribute("message", "Atividade física atualizada com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar atualizar a atividade física.");
					request.setAttribute("type", "danger");
				}	
				
			} else if (action.equals("delete")) {
				
				if (par.delete(Integer.parseInt(request.getParameter("id")))) {
					request.setAttribute("message", "Atividade física deletada com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar deletar a atividade física.");
					request.setAttribute("type", "danger");
				}	
			}
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("message", "Erro ao tentar realizar a ação.");
			request.setAttribute("type", "danger");
		}
		
		doGet(request, response);
	}

}
