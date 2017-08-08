package com.revature.battleship.servlets;

import java.io.IOException;
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
 * Servlet implementation class LoadGameServlet
 */
@WebServlet("/LoadGameServlet")
public class LoadGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ViewPlayerInformationServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadGameServlet() {
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Service service = ServiceImpl.getService();
		
		int gid = Integer.parseInt(request.getParameter("gid"));
		
		GameState gs = service.loadGame(gid);
		int[] numOfHits = service.countSuccessfulHits();
		
		session.setAttribute("currGameIDInPlay", gid);
		session.setAttribute("playerNumOfHits", numOfHits[0]);
		session.setAttribute("enemyNumOfHits", numOfHits[1]);
		
		Gson gson = new Gson();
		String JSON = gson.toJson(gs);		
		response.getWriter().write(JSON);
		
	}

}
