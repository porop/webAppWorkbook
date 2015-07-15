package porop.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
//@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select MNO,MNAME,EMAIL,CRE_DATE from MEMBERS order by MNO ASC");
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Member List</title></head>");
			out.println("<body><h1>☆Member List</h1>");
			while(rs.next()) {
				out.println(rs.getInt("MNO")+", "+
				"<a href='update?no=" + rs.getInt("MNO") +"'>" + 
				rs.getString("MNAME")+"</a>, "+rs.getString("EMAIL")+", "+rs.getDate("CRE_DATE")+
				" <a href='delete?no=" + rs.getInt("MNO") +"'>[DEL]</a>"+"<br>");
			}
			out.println("</body></html>");
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try{if (rs != null) rs.close();} catch(Exception e) {}
			try{if (stmt != null) stmt.close();} catch(Exception e) {}
			try{if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
}