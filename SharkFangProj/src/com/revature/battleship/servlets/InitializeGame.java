package com.revature.battleship.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.battleship.service.Service;
import com.revature.battleship.service.ServiceImpl;

/**
 * Servlet implementation class InitializeGame
 */
public class InitializeGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(InitializeGame.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitializeGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Service service = ServiceImpl.getService();
		
		int xSize = Integer.parseInt(request.getParameter("xSize"));
		int ySize = Integer.parseInt(request.getParameter("ySize"));
		int userPlayerID = (Integer) session.getAttribute("uid");
		int enemyPlayerID = Integer.parseInt(request.getParameter("enemyID"));
		
		int gameID = service.initializeGame(xSize, ySize, userPlayerID, enemyPlayerID);
		logger.debug("\nCreated a game w. these values:\n"
				+"X: "+xSize+",\n"
				+"Y: "+ySize+",\n"
				+"P1: "+userPlayerID+",\n"
				+"P2: "+enemyPlayerID);
		
		session.setAttribute("currGameIDInPlay", gameID);
		session.setAttribute("playerNumOfHits", 0);
		session.setAttribute("enemyNumOfHits", 0);
		logger.debug("Stored currGameIDInPlay into session: "+gameID);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
