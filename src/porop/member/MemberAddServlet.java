package porop.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Member Add</title></head>");
		out.println("<body><h1>☆Member Add</h1>");
		out.println("<form action='add' method='post'>");
		out.println("Name: <input type='text' name='name'><br>");
		out.println("Email <input type='text' name='email'><br>");
		out.println("Password: <input type='password' name='password'><br>");
		out.println("<input type='submit' value='add'>");
		out.println("<input type='reset' value='reset'>");
		out.println("</form>");
		out.println("</body></html>");
	}
}
