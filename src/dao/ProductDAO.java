package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import beans.ProductBean;
import dbconnection.DatabaseConnection;

public class ProductDAO {

	public ProductBean getProduct(ProductBean product){
		
		Statement stmt = null;
		
		try{
			Connection conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM products WHERE prod_id='" + product.getId() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Boolean b = rs.next();
			if(b){         
				String prodName = rs.getString("prod_name");
				int prodId = rs.getInt("prod_id");
				double price = rs.getDouble("prod_price");
				product.setId(prodId);
				product.setPrice(price);
				product.setName(prodName);
				product.setQuantity(rs.getInt("quantity"));
			}
			else{
				product.isValid = false;
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
		
		return product;
	}
	
	public List<ProductBean> getAllProducts(String type){
		
		List<ProductBean> products = new ArrayList<ProductBean>();
		DecimalFormat df = new DecimalFormat("#.00"); 
		
		Statement stmt = null;
		
		try{
			Connection conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql;
			sql =  type.equals("all") ? "SELECT * FROM products": "SELECT * FROM products WHERE quantity>0";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){         
				ProductBean product = new ProductBean();
				product.setId(rs.getInt("prod_id"));				
				product.setPrice(Double.parseDouble(df.format(rs.getDouble("prod_price"))));
				product.setName(rs.getString("prod_name"));
				product.setQuantity(rs.getInt("quantity"));
				products.add(product);
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
		
		return products;
	}
	
	public Boolean decrementQty(int id){
		Statement stmt = null;
		Boolean ret = false;
		try{
			Connection conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "UPDATE products SET quantity = quantity - 1 WHERE prod_id="+ id +" and quantity > 0";
			System.out.println("SQL: " + sql);
			int r = stmt.executeUpdate(sql);
			System.out.println("SQL r: " + r);
			ret = (r!=0) ? true : false;
			stmt.close();
			DatabaseConnection.closeConnection(conn);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	public Boolean addProduct(ProductBean product){
		Statement stmt = null;
		Boolean ret = false;
		try{
			Connection conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO products (prod_name, prod_price, quantity) VALUES ('" + product.getName() + "','" +
					product.getPrice() + "','" + product.getQuantity() + "')" ;
			System.out.println("SQL: " + sql);
			int r = stmt.executeUpdate(sql);
			System.out.println("SQL r: " + r);
			ret = (r!=0) ? true : false;
			stmt.close();
			DatabaseConnection.closeConnection(conn);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	public Boolean editProduct(ProductBean product){
		Statement stmt = null;
		Boolean ret = false;
		try{
			Connection conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "UPDATE products SET prod_name='" + product.getName() + "', prod_price='" + product.getPrice() + "', quantity='" + product.getQuantity() +
					"' WHERE prod_id='" + product.getId() + "'";
			System.out.println("SQL: " + sql);
			int r = stmt.executeUpdate(sql);
			System.out.println("SQL r: " + r);
			ret = (r!=0) ? true : false;
			stmt.close();
			DatabaseConnection.closeConnection(conn);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	public Boolean deleteProduct(int productId){
		Statement stmt = null;
		Boolean ret = false;
		try{
			Connection conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "DELETE FROM products " + "WHERE prod_id ='" + productId + "'";
			System.out.println("SQL: " + sql);
			int r = stmt.executeUpdate(sql);
			System.out.println("SQL r: " + r);
			ret = (r!=0) ? true : false;
			stmt.close();
			DatabaseConnection.closeConnection(conn);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
}
