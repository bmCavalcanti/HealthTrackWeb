package br.com.healthtrack.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import br.com.healthtrack.entity.User;
import br.com.healthtrack.entity.Weight;
import br.com.healthtrack.repository.UserRepository;
import br.com.healthtrack.repository.WeightRepository;

/**
 * Servlet implementation class WeightServlet
 */
@WebServlet("/weight")
public class WeightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserRepository ur;
	private WeightRepository wr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeightServlet() {
        super();
        ur = new UserRepository();
        wr = new WeightRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Weight> weights = wr.getAll((int) session.getAttribute("user_id"));
		request.setAttribute("weights", weights);
		
	    weights.removeIf(w -> w.getCreatedAt().get(Calendar.YEAR) != Calendar.getInstance().get(Calendar.YEAR));
	    
	    List<String> weight_chart_labels = new ArrayList<String>();
	    List<Float> weight_chart_data = new ArrayList<Float>();
	    final DateFormat df = new SimpleDateFormat("dd 'de' MMMMM");
	    for (Weight w : weights) {
	    	weight_chart_labels.add(df.format(w.getCreatedAt().getTime()));
	    	weight_chart_data.add(w.getValue());
	    }
	    
		JSONArray chart_labels = new JSONArray(weight_chart_labels);
		JSONArray chart_data = new JSONArray(weight_chart_data);
		
		request.setAttribute("user", ur.get((int) session.getAttribute("user_id")));
		request.setAttribute("chart_labels", chart_labels);
		request.setAttribute("chart_data", chart_data);
		request.setAttribute("chart_year", Calendar.getInstance().get(Calendar.YEAR));
		request.getRequestDispatcher("list.weight.jsp").forward(request, response);
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
						
				Weight weight = new Weight();
				
				weight.setValue(Float.parseFloat(request.getParameter("value")));
				weight.setCreatedAt(created_at);
				weight.setUser(user);
						
				if (wr.insert(weight)) {
					request.setAttribute("message", "Peso cadastrado com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar cadastrar o peso.");
					request.setAttribute("type", "danger");
				}
			} else if (action.equals("update")) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar created_at = Calendar.getInstance();
				created_at.setTime(format.parse(request.getParameter("created_at")));
						
				Weight weight = new Weight();
				
				weight.setId(Integer.parseInt(request.getParameter("id")));
				weight.setValue(Float.parseFloat(request.getParameter("value")));
				weight.setCreatedAt(created_at);
				weight.setUser(user);

				if (wr.update(weight)) {
					request.setAttribute("message", "Peso atualizado com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar atualizar o peso.");
					request.setAttribute("type", "danger");
				}
			} else if (action.equals("delete")) {
				
				if (wr.delete(Integer.parseInt(request.getParameter("id")))) {
					request.setAttribute("message", "Peso deletado com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar deletar o peso.");
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
