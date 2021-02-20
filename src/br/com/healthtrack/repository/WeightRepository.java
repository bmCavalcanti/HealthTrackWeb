package br.com.healthtrack.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.healthtrack.entity.Weight;
import br.com.healthtrack.controller.ConnectionController;

public class WeightRepository {
	
	public List<Weight> getAll(int user_id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		UserRepository ur = new UserRepository();
		
		List<Weight> weights = new ArrayList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_WEIGHT WHERE USER_ID = ? ORDER BY CREATED_AT DESC");
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();	
			int count = 0;
			while (rs.next()) {
				count++;
				Weight weight = new Weight();
				
				java.sql.Date date = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date.getTime());
				
				weight.setId(rs.getInt("ID"));
				weight.setValue(rs.getFloat("VALUE"));
				weight.setCreatedAt(created_at);
				weight.setUser(ur.get(rs.getInt("USER_ID")));
				
				weights.add(weight);
			}
			
			rs.close();

			if (count == 0) {
				System.out.println("Nada encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		}
		
		return weights;
	}
	
	public Weight get(int id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		UserRepository ur = new UserRepository();
		
		Weight weight = new Weight();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_WEIGHT WHERE ID = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;

				java.sql.Date date = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date.getTime());
				
				weight.setId(id);
				weight.setValue(rs.getFloat("VALUE"));
				weight.setCreatedAt(created_at);
				weight.setUser(ur.get(rs.getInt("USER_ID")));	
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
		
		return weight;
    }
		public boolean insert(Weight weight) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("INSERT INTO T_WEIGHT (ID, VALUE, CREATED_AT, USER_ID) VALUES (WEIGHT_SEQ.NEXTVAL, ?, ?, ?)");
			stmt.setFloat(1, weight.getValue());
			stmt.setDate(2, new java.sql.Date(weight.getCreatedAt().getTimeInMillis()));
			stmt.setInt(3, weight.getUser().getId());
			
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
	
 	public boolean update(Weight weight) {
 		Connection conn = ConnectionController.getInstance().getConnection();
 		
 		PreparedStatement stmt = null; 
		try {
			stmt = conn.prepareStatement("UPDATE T_WEIGHT SET VALUE = ?, CREATED_AT = ? WHERE ID = ?");

			stmt.setFloat(1, weight.getValue());
			stmt.setDate(2, new java.sql.Date(weight.getCreatedAt().getTimeInMillis()));
			stmt.setInt(3, weight.getId());
			
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
			stmt = conn.prepareStatement("DELETE FROM T_WEIGHT WHERE ID = ?");
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
	
	public Weight getLast(int user_id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		Weight weight = new Weight();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_WEIGHT WHERE USER_ID = ? ORDER BY CREATED_AT DESC FETCH NEXT 1 ROWS ONLY");
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				java.sql.Date date = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date.getTime());
				
				weight.setId(rs.getInt("ID"));
				weight.setValue(rs.getFloat("VALUE"));
				weight.setCreatedAt(created_at);
			}
			
			rs.close();
			
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
		
		return weight;
    }
	
}
