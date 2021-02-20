package br.com.healthtrack.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.healthtrack.entity.BloodPressure;
import br.com.healthtrack.controller.ConnectionController;

public class BloodPressureRepository {
	
	public List<BloodPressure> getAll(int user_id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		UserRepository ur = new UserRepository();
		
		List<BloodPressure> blood_pressures = new ArrayList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_BLOOD_PRESSURE WHERE USER_ID = ? ORDER BY CREATED_AT DESC");
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				BloodPressure blood_pressure = new BloodPressure();
				
				java.sql.Date date = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date.getTime());
				
				blood_pressure.setId(rs.getInt("ID"));
				blood_pressure.setValue(rs.getString("VALUE"));
				blood_pressure.setCreatedAt(created_at);
				blood_pressure.setUser(ur.get(rs.getInt("USER_ID")));
				
				blood_pressures.add(blood_pressure);

			}
			
			rs.close();

			if (count == 0) {
				System.out.println("Nada encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		}
		
		return blood_pressures;
	}
	
	public BloodPressure get(int id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		UserRepository ur = new UserRepository(); 
		
		BloodPressure blood_pressure = new BloodPressure();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_BLOOD_PRESSURE WHERE ID = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				java.sql.Date date = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date.getTime());
				
				blood_pressure.setId(id);
				blood_pressure.setValue(rs.getString("VALUE"));
				blood_pressure.setCreatedAt(created_at);
				blood_pressure.setUser(ur.get(rs.getInt("USER_ID")));
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
		
		return blood_pressure;
    }
	
	public boolean insert(BloodPressure blood_pressure) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("INSERT INTO T_BLOOD_PRESSURE (ID, VALUE, CREATED_AT, USER_ID) VALUES (BLOOD_PRESSURE_SEQ.NEXTVAL, ?, ?, ?)");
			stmt.setString(1, blood_pressure.getValue());
			stmt.setDate(2, new java.sql.Date(blood_pressure.getCreatedAt().getTimeInMillis()));
			stmt.setInt(3, blood_pressure.getUser().getId());
			
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
	
 	public boolean update(BloodPressure blood_pressure) {
 		Connection conn = ConnectionController.getInstance().getConnection();
 		
 		PreparedStatement stmt = null; 
		try {
			stmt = conn.prepareStatement("UPDATE T_BLOOD_PRESSURE SET VALUE = ?, CREATED_AT = ? WHERE ID = ?");

			stmt.setString(1, blood_pressure.getValue());
			stmt.setDate(2, new java.sql.Date(blood_pressure.getCreatedAt().getTimeInMillis()));
			stmt.setInt(3, blood_pressure.getId());
			
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
			stmt = conn.prepareStatement("DELETE FROM T_BLOOD_PRESSURE WHERE ID = ?");
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
