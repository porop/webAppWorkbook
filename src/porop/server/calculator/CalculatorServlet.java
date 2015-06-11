package porop.server.calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/calc")
@SuppressWarnings("serial")
public class CalculatorServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operator = request .getParameter("op");
		int a = Integer.parseInt(request.getParameter("v1"));
		int b = Integer.parseInt(request.getParameter("v2"));
		int r = 0;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		switch (operator) {
		case "+": r = a + b; break;
		case "-": r = a - b; break;
		case "*": r = a * b; break;
		case "%": r = a % b; break;
		case "/": 
			if(b == 0) out.println("Cannot divide with 0!");
			r = a / b; break;
		default:
			out.println("Not supported operation.");
				
		}
		out.println("Calculator Result: " + a + " " + operator + " " + b + " = " + r);
	}

}
