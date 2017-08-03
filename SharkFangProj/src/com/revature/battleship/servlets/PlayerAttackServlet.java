package com.revature.battleship.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.revature.battleship.service.Service;
import com.revature.battleship.service.ServiceImpl;

/**
 * Servlet implementation class BoardInteractionServlet
 */
@WebServlet("/BoardInteractionServlet")
public class PlayerAttackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(PlayerAttackServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerAttackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = ServiceImpl.getService();
		
		//Retrieve the number of the cell the player targeted
		int target = Integer.parseInt(request.getParameter("move"));
		logger.debug("Player targeted: "+target);
				
		//Determine the result of the attack
		int resultOfAttack = service.playerAttack(target);
		logger.debug("The attack 1hit/2missed: "+resultOfAttack);
		
		//Store last player move in session
		request.getSession().setAttribute("lastPlayerMove", target);
		request.getSession().setAttribute("lastPlayerResult", resultOfAttack);
		//Array to send in JSON if needed
		int lastPlayerMove [] = {target, resultOfAttack}; //TODO Determine if this is needed
		
		Gson gson = new Gson();
		String resultJson = gson.toJson(resultOfAttack);
		logger.debug("The JSON resultOfAttack is: "+resultJson);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		out.write(resultJson);		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
