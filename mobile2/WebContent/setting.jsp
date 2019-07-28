<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.io.*" %>
    <%@ page import = "java.sql.*" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "java.net.*" %>
    <%@ page import = "org.json.simple.*" %>
    
<% 
	String url = "jdbc:mysql://power.cn4zjtmscige.ap-northeast-1.rds.amazonaws.com:3306/userdb?useSSL=false&serverTimezone=UTC";
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url,"root","D349h65307!");
		String sql = "SELECT NAME FROM MEMBER WHERE WEDO=? AND KYUNGDO=?";  /* 사용자가 폰에서 입력한 위도 경도  */
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,request.getParameter("wedo"));  /* 폰에서 입력한 값을 설 */
		pstmt.setString(2,request.getParameter("kyungdo"));
		rs = pstmt.executeQuery();
		String result = "";
		JSONObject jsonMain = new JSONObject(); // 객체
		JSONArray jArray = new JSONArray(); // 배열
		if(rs.next()) /* 레코드를 읽는다 */
		{
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("wedo", rs.getString("wedo")); /* 있으면 저장을 하고 */
			jsonobj.put("kyungdo", rs.getString("kyungdo"));
			jArray.add(0, jsonobj);
		}
		jsonMain.put("sendData", jArray); // JSON의 제목 지정
		out.clear(); // 출력 화면을 클리어 시킴
		out.println(jsonMain); // 출력
		out.flush(); // 버퍼를 비움
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		// db close 처리
		try
		{
			if(rs != null)
			{
				rs.close();
			}
			if(pstmt != null)
			{
				pstmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>