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

import br.com.healthtrack.entity.Food;
import br.com.healthtrack.entity.FoodItem;
import br.com.healthtrack.entity.FoodType;
import br.com.healthtrack.entity.User;
import br.com.healthtrack.entity.Weight;
import br.com.healthtrack.repository.UserRepository;
import br.com.healthtrack.repository.WeightRepository;
import br.com.healthtrack.repository.FoodRepository;
import br.com.healthtrack.repository.FoodItemRepository;
import br.com.healthtrack.repository.FoodTypeRepository;

/**
 * Servlet implementation class FoodServlet
 */
@WebServlet("/food")
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserRepository ur;
	private FoodRepository fr;
	private FoodItemRepository fir;
	private FoodTypeRepository ftr;
	private WeightRepository wr = new WeightRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodServlet() {
        super();
    	ur = new UserRepository();
    	fr = new FoodRepository();
    	fir = new FoodItemRepository();
    	ftr = new FoodTypeRepository();
    	wr = new WeightRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Weight lastWeight = wr.getLast((int) session.getAttribute("user_id"));	
				
		List<Food> foods = fr.getAll((int) session.getAttribute("user_id"));
		List<FoodType> food_types = ftr.getAll();
		
	    Map<Calendar, List<Food>> foodsMap = foods.stream().collect(Collectors.groupingBy(Food::getCreatedAt));
		
		request.setAttribute("foods", foodsMap);
		request.setAttribute("food_types", food_types);
		request.setAttribute("user", ur.get((int) session.getAttribute("user_id")));
		request.setAttribute("weight", lastWeight.getValue());
		request.getRequestDispatcher("list.food.jsp").forward(request, response);
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
						
				Food food = new Food();
				
				food.setDescription(request.getParameter("description"));
				food.setFoodType(ftr.get(Integer.parseInt(request.getParameter("food_type"))));
				food.setCreatedAt(created_at);
				food.setUser(user);
				
				int food_id = fr.insert(food);
				food.setId(food_id);
				
				String[] food_items = request.getParameterValues("food_item[name]");
				
				for (int i = 0; i < food_items.length; i++)
				{
					FoodItem food_item = new FoodItem();
					food_item.setName(food_items[i]);
					food_item.setCalories(Float.parseFloat(request.getParameterValues("food_item[cal]")[i]));
					food_item.setFood(food);
					
					fir.insert(food_item);
				}	
				
				request.setAttribute("message", "Refeição cadastrada com sucesso!");
				request.setAttribute("type", "success");
			} else if (action.equals("update")) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar created_at = Calendar.getInstance();
				created_at.setTime(format.parse(request.getParameter("created_at")));
						
				Food food = new Food();
				
				food.setId(Integer.parseInt(request.getParameter("id")));
				food.setDescription(request.getParameter("description"));
				food.setFoodType(ftr.get(Integer.parseInt(request.getParameter("food_type"))));
				food.setCreatedAt(created_at);
				food.setUser(user);
								
				if (fr.update(food)) {
					request.setAttribute("message", "Refeição atualizada com sucesso!");
					request.setAttribute("type", "success");
				} else {

					request.setAttribute("message", "Erro ao tentar atualizar a refeição.");
					request.setAttribute("type", "danger");
				}
			} else if (action.equals("delete")) {
				int food_id = Integer.parseInt(request.getParameter("id"));
				
				List<FoodItem> food_items = fir.getAll(food_id);
				
				for (FoodItem food_item : food_items) {
					fir.delete(food_item.getId());
				}
				
				if (fr.delete(food_id)) {
					request.setAttribute("message", "Refeição deletada com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar deletar a refeição.");
					request.setAttribute("type", "danger");
				}	
				
				
			} else if (action.equals("insert_item")) {
				FoodItem food_item = new FoodItem();
				
				food_item.setName(request.getParameter("name"));
				food_item.setCalories(Float.parseFloat(request.getParameter("calories")));
				food_item.setFood(fr.get(Integer.parseInt(request.getParameter("food"))));
			
				if (fir.insert(food_item)) {
					request.setAttribute("message", "Alimento cadastrado com sucesso!");
					request.setAttribute("type", "success");	
				} else {
					request.setAttribute("message", "Erro ao tentar cadastrar o alimento.");
					request.setAttribute("type", "danger");	
				}
			} else if (action.equals("update_item")) {
				FoodItem food_item = new FoodItem();
				
				food_item.setId(Integer.parseInt(request.getParameter("id")));
				food_item.setName(request.getParameter("name"));
				food_item.setCalories(Float.parseFloat(request.getParameter("calories")));
				food_item.setFood(fr.get(Integer.parseInt(request.getParameter("food"))));
				
				if (fir.update(food_item)) {
					request.setAttribute("message", "Alimento atualizado com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar atualizar o alimento.");
					request.setAttribute("type", "danger");
				}
			} else if (action.equals("delete_item")) {				
				if (fir.delete(Integer.parseInt(request.getParameter("id")))) {
					request.setAttribute("message", "Alimento deletado com sucesso!");
					request.setAttribute("type", "success");
				} else {
					request.setAttribute("message", "Erro ao tentar deletar o alimento.");
					request.setAttribute("type", "danger");
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Erro ao tentar realizar a ação.");
			request.setAttribute("type", "danger");
		}
		
		doGet(request, response);
	}

}
