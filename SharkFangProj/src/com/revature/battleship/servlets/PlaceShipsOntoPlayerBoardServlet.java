package com.revature.battleship.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.battleship.service.ServiceImpl;

/**
 * Servlet implementation class PlaceShipsOntoPlayerBoardServlet
 */
public class PlaceShipsOntoPlayerBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PlaceShipsOntoPlayerBoardServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceShipsOntoPlayerBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceImpl service = ServiceImpl.getService();
		
		String shipString = request.getParameter("ships");
		String[] shipStringArray = shipString.split(",");

		service.placeShipsOntoBoard(shipStringArray);
		logger.debug("Ships placed on board at: "+shipString); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
