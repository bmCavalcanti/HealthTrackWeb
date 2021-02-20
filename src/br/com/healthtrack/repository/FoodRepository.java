package br.com.healthtrack.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.healthtrack.entity.Food;
import br.com.healthtrack.controller.ConnectionController;

public class FoodRepository {
	
	public List<Food> getAll(int user_id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		UserRepository ur = new UserRepository();
		FoodTypeRepository ftr = new FoodTypeRepository();
		FoodItemRepository fir = new FoodItemRepository();
		
		List<Food> foods = new ArrayList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_FOOD WHERE USER_ID = ? ORDER BY CREATED_AT DESC");
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				Food food = new Food();
				
				java.sql.Date date = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date.getTime());
				
				food.setId(rs.getInt("ID"));
				food.setDescription(rs.getString("DESCRIPTION"));
				food.setCreatedAt(created_at);
				food.setUser(ur.get(rs.getInt("USER_ID")));
				food.setFoodType(ftr.get(rs.getInt("FOOD_TYPE_ID")));
				food.setFoodItems(fir.getAll(food.getId()));
				
				foods.add(food);

			}
			
			rs.close();

			if (count == 0) {
				System.out.println("Nada encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		}
		
		return foods;
	}
	
	public Food get(int id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		UserRepository ur = new UserRepository();
		FoodTypeRepository ftr = new FoodTypeRepository();
		
		Food food = new Food();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_FOOD WHERE ID = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;

				java.sql.Date date = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date.getTime());
				
				food.setId(rs.getInt("ID"));
				food.setDescription(rs.getString("DESCRIPTION"));
				food.setCreatedAt(created_at);
				food.setUser(ur.get(rs.getInt("USER_ID")));
				food.setFoodType(ftr.get(rs.getInt("FOOD_TYPE_ID")));
			}
			
			rs.close();

			if (count == 0) {
				System.out.println("Nada encontrado.");
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return food;
    }
	
	public int insert(Food food) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		PreparedStatement stmt = null;
		int id = 0;
		try {
			stmt = conn.prepareStatement("INSERT INTO T_FOOD (ID, CREATED_AT, DESCRIPTION, USER_ID, FOOD_TYPE_ID) VALUES (FOOD_SEQ.NEXTVAL, ?, ?, ?, ?)", new String[] {"ID"});
			
			stmt.setDate(1, new java.sql.Date(food.getCreatedAt().getTimeInMillis()));
			stmt.setString(2, food.getDescription());
			stmt.setInt(3, food.getUser().getId());
			stmt.setInt(4, food.getFoodType().getId());
			
			stmt.executeUpdate();
			
			try (ResultSet rs = stmt.getGeneratedKeys()) {
			    if (rs.next()) {
			        id = rs.getInt(1);
			    }
			    rs.close();
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível realizar a ação.");
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
    }
	
 	public boolean update(Food food) {
 		Connection conn = ConnectionController.getInstance().getConnection();
 		
 		PreparedStatement stmt = null; 
		try {
			stmt = conn.prepareStatement("UPDATE T_FOOD SET CREATED_AT = ?, DESCRIPTION = ?, USER_ID = ?, FOOD_TYPE_ID = ? WHERE ID = ?");

			stmt.setDate(1, new java.sql.Date(food.getCreatedAt().getTimeInMillis()));
			stmt.setString(2, food.getDescription());
			stmt.setInt(3, food.getUser().getId());
			stmt.setInt(4, food.getFoodType().getId());
			stmt.setInt(5, food.getId());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Não foi possível realizar a ação.");
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
    }

	public boolean delete(int id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("DELETE FROM T_FOOD WHERE ID = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Não foi possível realizar a ação.");
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
    }
}
