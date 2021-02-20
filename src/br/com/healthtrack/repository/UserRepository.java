package br.com.healthtrack.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.healthtrack.entity.User;
import br.com.healthtrack.controller.ConnectionController;

public class UserRepository {
	
	public List<User> getAll() {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		GenderRepository gr = new GenderRepository();
		
		List<User> users = new ArrayList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_USER");
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				User user = new User();
				
				java.sql.Date date_ct = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date_ct.getTime());
				
				java.sql.Date date_b = rs.getDate("BIRTHDAY"); 
				Calendar birthday = Calendar.getInstance(); 
				birthday.setTimeInMillis(date_b.getTime());

				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setHeight(rs.getFloat("HEIGHT"));
				user.setPhoto(rs.getString("PHOTO"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setCreatedAt(created_at);
				user.setBirthday(birthday);			
				user.setGender(gr.get(rs.getInt("GENDER_ID")));
				
				users.add(user);
			}
			
			rs.close();

			if (count == 0) {
				System.out.println("Nada encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		}
		
		return users;
	}
	
	public User get(int id) {
		Connection conn = ConnectionController.getInstance().getConnection();

		GenderRepository gr = new GenderRepository();
		
		User user = new User();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_USER WHERE ID = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				
				java.sql.Date date_ct = rs.getDate("CREATED_AT"); 
				Calendar created_at = Calendar.getInstance(); 
				created_at.setTimeInMillis(date_ct.getTime());
				
				java.sql.Date date_b = rs.getDate("BIRTHDAY"); 
				Calendar birthday = Calendar.getInstance(); 
				birthday.setTimeInMillis(date_b.getTime());

				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setHeight(rs.getFloat("HEIGHT"));
				user.setPhoto(rs.getString("PHOTO"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setCreatedAt(created_at);
				user.setBirthday(birthday);
				user.setGender(gr.get(rs.getInt("GENDER_ID")));
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
		
		return user;
    }
	
	public int insert(User user) {
		Connection conn = ConnectionController.getInstance().getConnection();
		
		PreparedStatement stmt = null;
		int id = 0;
		
		try {
			stmt = conn.prepareStatement("INSERT INTO T_USER (ID, NAME, EMAIL, HEIGHT, PHOTO, PASSWORD, CREATED_AT, BIRTHDAY, GENDER_ID) VALUES (USER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)", new String[] {"ID"});
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setFloat(3, user.getHeight());
			stmt.setString(4, user.getPhoto());
			stmt.setString(5, user.getPassword());
			stmt.setDate(6, new java.sql.Date(user.getCreatedAt().getTimeInMillis()));
			stmt.setDate(7, new java.sql.Date(user.getBirthday().getTimeInMillis()));
			stmt.setInt(8, user.getGender().getId());
			
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
	
 	public boolean update(User user) {
 		Connection conn = ConnectionController.getInstance().getConnection();
 		
 		PreparedStatement stmt = null; 
		try {
			stmt = conn.prepareStatement("UPDATE T_USER SET NAME = ?, EMAIL = ?, HEIGHT = ?, PHOTO = ?, PASSWORD = ?, CREATED_AT = ?, BIRTHDAY = ?, GENDER_ID = ? WHERE ID = ?");

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setFloat(3, user.getHeight());
			stmt.setString(4, user.getPhoto());
			stmt.setString(5, user.getPassword());
			stmt.setDate(6, new java.sql.Date(user.getCreatedAt().getTimeInMillis()));
			stmt.setDate(7, new java.sql.Date(user.getBirthday().getTimeInMillis()));
			stmt.setInt(8, user.getGender().getId());
			stmt.setInt(9, user.getId());
			
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
			stmt = conn.prepareStatement("DELETE FROM T_USER WHERE ID = ?");
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
	
	public int validateLogin(User user) {
		Connection conn = ConnectionController.getInstance().getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_USER WHERE EMAIL = ? AND PASSWORD = ?");
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				 return rs.getInt("ID");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar os dados.");
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	public int emailIsRegistred(String email) {
		Connection conn = ConnectionController.getInstance().getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM T_USER WHERE EMAIL = ?");
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
    }
}
