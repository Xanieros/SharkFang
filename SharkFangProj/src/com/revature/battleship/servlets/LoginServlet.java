package com.revature.battleship.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.battleship.pojos.Player;
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
		HttpSession session = request.getSession();

		String usernameEntered = request.getParameter("username");
		String passwordEntered = request.getParameter("password");
		
		session.setAttribute("username", usernameEntered);
		
		Service service = new ServiceImpl();
		Player currentPlayer = service.login(usernameEntered, passwordEntered);
		
		String nextPage = "/html/login.html";
		int currID = currentPlayer.getUid();
		if (currID > 0)
		{
			session.setAttribute("uid", currentPlayer.getUid());
			session.setAttribute("username", currentPlayer.getUname());
			session.setAttribute("email", currentPlayer.getEmail());
			session.setAttribute("fname", currentPlayer.getFname());
			session.setAttribute("lname", currentPlayer.getLname());
			session.setAttribute("profPic", currentPlayer.getProfPic());
			 
			nextPage = "/html/game.html";
		}
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

}
