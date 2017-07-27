package com.revature.battleship.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.battleship.service.Service;
import com.revature.battleship.service.ServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Got here");
		/*HttpSession session = request.getSession();

		String usernameEntered = request.getParameter("username");
		String passwordEntered = request.getParameter("password");
		
		session.setAttribute("username", usernameEntered);
		
		Service service = new ServiceImpl();
		boolean authenticated = service.login(usernameEntered, passwordEntered);
		
		/*PrintWriter out = response.getWriter();
		if (true)
		{
			out.println("<h1> Success </h1>");
		}*/
		/*else
		{
			out.print("<h1> Success </h1>");
		}*/
	}

}
