package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DO.book;

public class bookAddr {
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3306/jspdb?useSSL=false&serverTimezone=UTC";
	
	void connect() {
		try 
		{
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "jspbook","D349h65307!");
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<book> getAddrList(){
		connect();
		ArrayList<book> addrlist=new ArrayList<book>();
		String sql="select * from user";
		try {
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				book ab=new book();
				ab.setBook_code(rs.getInt("book_code"));
				ab.setAmount(rs.getInt("amount"));
				ab.setBook_name(rs.getString("book_name"));
				ab.setBook_price(rs.getInt("book_price"));
				ab.setPress(rs.getString("book_press"));
				addrlist.add(ab);
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return addrlist;
	}
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
