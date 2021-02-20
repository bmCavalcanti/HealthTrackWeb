package br.com.healthtrack.repository;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.healthtrack.entity.FoodItem;
import br.com.healthtrack.controller.ConnectionController;

public class FoodItemRepository {
	
	public List<FoodItem> getAll(int food_id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		FoodRepository fr = new FoodRepository();
		
		List<FoodItem> food_items = new ArrayList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_FOOD_ITEM WHERE FOOD_ID = ? ORDER BY ID DESC");
			stmt.setInt(1, food_id);
			
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				FoodItem food_item = new FoodItem();
								
				food_item.setId(rs.getInt("ID"));
				food_item.setName(rs.getString("NAME"));
				food_item.setCalories(rs.getFloat("CALORIES"));
				food_item.setFood(fr.get(rs.getInt("FOOD_ID")));
				
				food_items.add(food_item);
			}
			
			rs.close();

			if (count == 0) {
				System.out.println("Nada encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		}
		
		return food_items;
	}
	
	public FoodItem get(int id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		FoodRepository fr = new FoodRepository();
		
		FoodItem food_item = new FoodItem();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_FOOD_ITEM WHERE ID = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				
				food_item.setId(rs.getInt("ID"));
				food_item.setId(rs.getInt("ID"));
				food_item.setName(rs.getString("NAME"));
				food_item.setCalories(rs.getFloat("CALORIES"));
				food_item.setFood(fr.get(rs.getInt("FOOD_ID")));
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
		
		return food_item;
    }
	
	public boolean insert(FoodItem food_item) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("INSERT INTO T_FOOD_ITEM (ID, NAME, CALORIES, FOOD_ID) VALUES (FOOD_ITEM_SEQ.NEXTVAL, ?, ?, ?)");
			
			stmt.setString(1, food_item.getName());
			stmt.setFloat(2, food_item.getCalories());
			stmt.setInt(3, food_item.getFood().getId());
			
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
	
 	public boolean update(FoodItem food_item) {
 		Connection conn = ConnectionController.getInstance().getConnection();
 		
 		PreparedStatement stmt = null; 
		try {
			stmt = conn.prepareStatement("UPDATE T_FOOD_ITEM SET NAME = ?, CALORIES = ?, FOOD_ID = ? WHERE ID = ?");

			stmt.setString(1, food_item.getName());
			stmt.setFloat(2, food_item.getCalories());
			stmt.setInt(3, food_item.getFood().getId());
			stmt.setInt(4, food_item.getId());
			
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
			stmt = conn.prepareStatement("DELETE FROM T_FOOD_ITEM WHERE ID = ?");
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
