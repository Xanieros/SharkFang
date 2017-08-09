package com.revature.battleship.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.battleship.pojos.Player;
import com.revature.battleship.service.Service;
import com.revature.battleship.service.ServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	Logger logger = Logger.getLogger(LoginServlet.class);
       
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
		System.out.println("Inside logging out");
		HttpSession session = request.getSession();

		String usernameEntered = request.getParameter("username");
		String passwordEntered = request.getParameter("password");
		
		session.setAttribute("attemptedUsername", usernameEntered);
		
		Service service = ServiceImpl.getService();
		Player currentPlayer = service.login(usernameEntered, passwordEntered);
		logger.debug("The current player is: "+currentPlayer);
		
		if (currentPlayer != null)
		{
			session.removeAttribute("attemptedUsername");
			session.setAttribute("uid", currentPlayer.getUid());
			session.setAttribute("username", currentPlayer.getUname());
			session.setAttribute("email", currentPlayer.getEmail());
			session.setAttribute("fname", currentPlayer.getFname());
			session.setAttribute("lname", currentPlayer.getLname());
			session.setAttribute("profPic", currentPlayer.getProfPic());
		}
		
		request.getRequestDispatcher("/html/game.html").forward(request, response);
	}

}
