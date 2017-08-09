package com.revature.battleship.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.revature.battleship.pojos.GameState;
import com.revature.battleship.service.Service;
import com.revature.battleship.service.ServiceImpl;

/**
 * Servlet implementation class LoadPlayerGamesServlet
 */
public class LoadPlayerGamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ViewPlayerInformationServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadPlayerGamesServlet() {
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
		logger.debug("Called LoadPlayerGamesServlet");
		HttpSession session = request.getSession();
		int uid = (Integer)session.getAttribute("uid");
		int offset = Integer.parseInt(request.getParameter("offset"));

		Service service = ServiceImpl.getService();
		ArrayList<GameState> gs = service.loadPlayerGames(uid, offset);
		
		Gson gson = new Gson();
		String JSON = gson.toJson(gs);
		
		response.getWriter().write(JSON);
	}

}
