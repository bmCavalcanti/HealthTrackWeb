package br.com.healthtrack.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.healthtrack.entity.PhysicalActivity;
import br.com.healthtrack.controller.ConnectionController;

public class PhysicalActivityRepository {
	
	public List<PhysicalActivity> getAll(int user_id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		UserRepository ur = new UserRepository();
		PhysicalActivityTypeRepository patr = new PhysicalActivityTypeRepository();
		
		List<PhysicalActivity> PhysicalActivitys = new ArrayList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_PHYSICAL_ACTIVITY WHERE USER_ID = ? ORDER BY CREATED_AT DESC");
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				PhysicalActivity PhysicalActivity = new PhysicalActivity();
				
				java.sql.Date date = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date.getTime());
				
				PhysicalActivity.setId(rs.getInt("ID"));
				PhysicalActivity.setCalories(rs.getFloat("CALORIES"));
				PhysicalActivity.setDescription(rs.getString("DESCRIPTION"));
				PhysicalActivity.setDuration(rs.getString("DURATION"));
				PhysicalActivity.setCreatedAt(created_at);
				PhysicalActivity.setUser(ur.get(rs.getInt("USER_ID")));
				PhysicalActivity.setPhysicalActivityType(patr.get(rs.getInt("PHYSICAL_ACTIVITY_TYPE_ID")));
				
				PhysicalActivitys.add(PhysicalActivity);

			}
			
			rs.close();

			if (count == 0) {
				System.out.println("Nada encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		}
		
		return PhysicalActivitys;
	}
	
	public PhysicalActivity get(int id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		UserRepository ur = new UserRepository();
		PhysicalActivityTypeRepository patr = new PhysicalActivityTypeRepository();
		
		PhysicalActivity PhysicalActivity = new PhysicalActivity();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_PHYSICAL_ACTIVITY WHERE ID = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;

				java.sql.Date date = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date.getTime());
				
				PhysicalActivity.setId(id);
				PhysicalActivity.setCalories(rs.getFloat("CALORIES"));
				PhysicalActivity.setDescription(rs.getString("DESCRIPTION"));
				PhysicalActivity.setDuration(rs.getString("DURATION"));
				PhysicalActivity.setCreatedAt(created_at);
				PhysicalActivity.setUser(ur.get(rs.getInt("USER_ID")));
				PhysicalActivity.setPhysicalActivityType(patr.get(rs.getInt("PHYSICAL_ACTIVITY_TYPE_ID")));
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
		
		return PhysicalActivity;
    }
	
	public boolean insert(PhysicalActivity PhysicalActivity) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("INSERT INTO T_PHYSICAL_ACTIVITY (ID, CALORIES, CREATED_AT, DESCRIPTION, DURATION, USER_ID, PHYSICAL_ACTIVITY_TYPE_ID) VALUES (PHYSICAL_ACTIVITY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)");
			stmt.setFloat(1, PhysicalActivity.getCalories());
			stmt.setDate(2, new java.sql.Date(PhysicalActivity.getCreatedAt().getTimeInMillis()));
			stmt.setString(3, PhysicalActivity.getDescription());
			stmt.setString(4, PhysicalActivity.getDuration());
			stmt.setInt(5, PhysicalActivity.getUser().getId());
			stmt.setInt(6, PhysicalActivity.getPhysicalActivityType().getId());
			
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
	
 	public boolean update(PhysicalActivity PhysicalActivity) {
 		Connection conn = ConnectionController.getInstance().getConnection();
 		
 		PreparedStatement stmt = null; 
		try {
			stmt = conn.prepareStatement("UPDATE T_PHYSICAL_ACTIVITY SET CALORIES = ?, CREATED_AT = ?, DESCRIPTION = ?, DURATION = ?, USER_ID = ?, PHYSICAL_ACTIVITY_TYPE_ID  = ? WHERE ID = ?");

			stmt.setFloat(1, PhysicalActivity.getCalories());
			stmt.setDate(2, new java.sql.Date(PhysicalActivity.getCreatedAt().getTimeInMillis()));
			stmt.setString(3, PhysicalActivity.getDescription());
			stmt.setString(4, PhysicalActivity.getDuration());
			stmt.setInt(5, PhysicalActivity.getUser().getId());
			stmt.setInt(6, PhysicalActivity.getPhysicalActivityType().getId());
			stmt.setInt(7, PhysicalActivity.getId());
			
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
			stmt = conn.prepareStatement("DELETE FROM T_PHYSICAL_ACTIVITY WHERE ID = ?");
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
