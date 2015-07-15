package porop.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateServlet
 */
/*Note: Not recommended..
@WebServlet(
		urlPatterns={"/member/update"},
		initParams={
				@WebInitParam(name="driver", value="com.mysql.jdbc.Driver"),
				@WebInitParam(name="url", value="jdbc:mysql://localhost/studydb", description="mysql studydb"),
				@WebInitParam(name="username", value="study"),
				@WebInitParam(name="password", value="study"),
		}
	)
*/
@SuppressWarnings("serial")
public class UpdateServlet extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//Note: without using 'init parameter'
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("username"), this.getInitParameter("password"));
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS where MNO="+request.getParameter("no"));
			rs.next();//Note: Only one person
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Member Information</title></head>");
			out.println("<body><h1>Member Information</h1>");
			out.println("<form action='update' method='post'>");
			out.println("번호: <input type='text' name='no' value='"+request.getParameter("no")+"' readonly><br>");
			out.println("이름: <input type='text' name='name' value='"+rs.getString("MNAME")+"'><br>");
			out.println("이메일: <input type='text' name='email' value='"+rs.getString("EMAIL")+"'><br>");
			out.println("<input type='submit' value='SAVE'>");
			out.println("<input type='button' value='CANCEL' onclick='location.href=\"list\"'>");
			out.println("</form></body></html>");
			out.println("가입일: "+rs.getDate("CRE_DATE")+"<br>");
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try{if (rs != null) rs.close();} catch(Exception e) {}
			try{if (stmt != null) stmt.close();} catch(Exception e) {}
			try{if (conn != null) conn.close();} catch(Exception e) {}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("username"), this.getInitParameter("password"));
			stmt = conn.prepareStatement("UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now() WHERE MNO=?");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("name"));
			stmt.setInt(3, Integer.parseInt(request.getParameter("no") != null?request.getParameter("no"):"0"));
			stmt.executeUpdate();
			response.sendRedirect("list");
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try{if (stmt != null) stmt.close();} catch(Exception e) {}
			try{if (conn != null) conn.close();} catch(Exception e) {}
		}
	}

}
