package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.UserBean;
import dbconnection.DatabaseConnection;
import hashing.AccountPassword;

public class UserDAO {
	
	public UserBean getUserDetails(UserBean user){
		
		Statement stmt = null;
		
		try{
			Connection conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String out = AccountPassword.hashPassword(user.getPassword());
			String sql = "SELECT * FROM users WHERE username='" + user.getUsername() + "' AND password='" + out + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Boolean b = rs.next();
			if(b){         
				String username = rs.getString("username");
				String user_fullname = rs.getString("user_fullname");
				int type = rs.getInt("user_type");
				user.setUsertype(type);
				user.setFullname(user_fullname);
				user.isValid=true;
				System.out.println("From database: " + username + " " + user_fullname);
			}
			else{
				user.isValid = false;
			}
			rs.close();
			stmt.close();
			DatabaseConnection.closeConnection(conn);
		}
		catch(SQLException se){
			se.printStackTrace();
			user.isValid = false;
		}
		catch(Exception e){
			e.printStackTrace();
			user.isValid = false;
		}
		
		return user;
	}
	
	public Boolean isUsernameExisting(UserBean user){
		Boolean existing = false;
		
		Statement stmt = null;
		
		try{
			Connection conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM users WHERE username='" + user.getUsername() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Boolean b = rs.next();
			existing = (b) ? true : false;        
			rs.close();
			stmt.close();
			DatabaseConnection.closeConnection(conn);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return existing;
	}
	
	public UserBean addUserAccount(UserBean user){
		Statement stmt = null;
		if(!isUsernameExisting(user)){ //username not yet existing
			try{
				Connection conn = DatabaseConnection.getConnection();
				stmt = conn.createStatement();
				String out = AccountPassword.hashPassword(user.getPassword());
				String sql = "INSERT INTO users (username, user_type, user_fullname, password) VALUES ('" + user.getUsername() + "','" +
						user.getUsertype() + "','" + user.getFullname() + "','" + out + "')" ;
				System.out.println("SQL: " + sql);
				int r = stmt.executeUpdate(sql);
				System.out.println("SQL r: " + r);
				user.isValid = true;
				
				stmt.close();
				DatabaseConnection.closeConnection(conn);
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Username already existing.");
		}		
		return user;
	}
	
	public List<UserBean> getAllUsers(){
		Statement stmt = null;
		List<UserBean> users = new ArrayList<UserBean>();
		
		try{
			Connection conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String query = "SELECT * FROM users";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				UserBean user = new UserBean();
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("user_fullname"));
				user.setUsertype(rs.getInt("user_type"));
				users.add(user);
			}
			
			rs.close();
			stmt.close();
			DatabaseConnection.closeConnection(conn);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return users;
	}
	
}
