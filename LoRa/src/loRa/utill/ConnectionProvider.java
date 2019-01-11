package loRa.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	public static Connection getConnection(){
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/jspdb?characterEncoding=UTF-8&serverTimezone=UTC";
			return DriverManager.getConnection(url,"jspbook","passwd");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
