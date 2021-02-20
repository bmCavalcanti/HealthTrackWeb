package br.com.healthtrack.repository;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.healthtrack.entity.PhysicalActivityType;
import br.com.healthtrack.controller.ConnectionController;

public class PhysicalActivityTypeRepository {
	
	public List<PhysicalActivityType> getAll() {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		List<PhysicalActivityType> PhysicalActivityTypes = new ArrayList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_PHYSICAL_ACTIVITY_TYPE");
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				PhysicalActivityType PhysicalActivityType = new PhysicalActivityType();
				
				PhysicalActivityType.setId(rs.getInt("ID"));
				PhysicalActivityType.setName(rs.getString("NAME"));
				
				PhysicalActivityTypes.add(PhysicalActivityType);
			}
			
			rs.close();

			if (count == 0) {
				System.out.println("Nada encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		}
		
		return PhysicalActivityTypes;
	}
	
	public PhysicalActivityType get(int id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		PhysicalActivityType PhysicalActivityType = new PhysicalActivityType();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_PHYSICAL_ACTIVITY_TYPE WHERE ID = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;

				PhysicalActivityType.setId(id);
				PhysicalActivityType.setName(rs.getString("NAME"));
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
		
		return PhysicalActivityType;
    }
	
	public boolean insert(PhysicalActivityType PhysicalActivityType) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("INSERT INTO T_PHYSICAL_ACTIVITY_TYPE (ID, NAME) VALUES (PHYSICAL_ACTIVITY_TYPE_SEQ.NEXTVAL, ?)");
			stmt.setString(1, PhysicalActivityType.getName());
			
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
	
 	public boolean update(PhysicalActivityType PhysicalActivityType) {
 		Connection conn = ConnectionController.getInstance().getConnection();
 		
 		PreparedStatement stmt = null; 
		try {
			stmt = conn.prepareStatement("UPDATE T_PHYSICAL_ACTIVITY_TYPE SET NAME = ? WHERE ID = ?");

			stmt.setString(1, PhysicalActivityType.getName());
			stmt.setInt(2, PhysicalActivityType.getId());
			
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
			stmt = conn.prepareStatement("DELETE FROM T_PHYSICAL_ACTIVITY_TYPE WHERE ID = ?");
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
