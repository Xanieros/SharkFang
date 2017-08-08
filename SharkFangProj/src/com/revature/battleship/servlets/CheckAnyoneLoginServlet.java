package com.revature.battleship.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckAnyoneLoginServlet
 */
public class CheckAnyoneLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAnyoneLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			response.getWriter().write("null");
			return;
		}
		String attemptedUsername = (String) session.getAttribute("attemptedUsername");
		String username = (String) session.getAttribute("username");
		if (username != null)
		{
			response.getWriter().write("true");
			return;
		}
		
		if (attemptedUsername == null)
		{
			// means no one tried logging in yet
			response.getWriter().write(attemptedUsername);
		}
		else 
		{
			// means an attempt at login but failed
			response.getWriter().write("false");
			session.removeAttribute("attemptedUsername");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
