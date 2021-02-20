package br.com.healthtrack.repository;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.healthtrack.entity.Gender;
import br.com.healthtrack.controller.ConnectionController;

public class GenderRepository {
	
	public List<Gender> getAll() {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		List<Gender> genders = new ArrayList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_GENDER");
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				Gender gender = new Gender();
				
				gender.setId(rs.getInt("ID"));
				gender.setName(rs.getString("NAME"));
				
				genders.add(gender);
			}
			
			rs.close();

			if (count == 0) {
				System.out.println("Nada encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		}
		
		return genders;
	}
	
	public Gender get(int id) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		Gender gender = new Gender();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_GENDER WHERE ID = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;

				gender.setId(id);
				gender.setName(rs.getString("NAME"));
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
		
		return gender;
    }
	
	public boolean insert(Gender gender) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("INSERT INTO T_GENDER (ID, NAME) VALUES (GENDER_SEQ.NEXTVAL, ?)");
			stmt.setString(1, gender.getName());
			
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
	
 	public boolean update(Gender gender) {
 		Connection conn = ConnectionController.getInstance().getConnection();
 		
 		PreparedStatement stmt = null; 
		try {
			stmt = conn.prepareStatement("UPDATE T_GENDER SET NAME = ? WHERE ID = ?");

			stmt.setString(1, gender.getName());
			stmt.setInt(2, gender.getId());
			
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
			stmt = conn.prepareStatement("DELETE FROM T_GENDER WHERE ID = ?");
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
