package com.revature.battleship.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		
		//Retrieve the number of the cell the player targeted
		int target = Integer.parseInt(request.getParameter("move"));
		logger.debug("Player targeted: "+target);
				
		//Determine the result of the attack
		int resultOfAttack = service.playerAttack(target);
		logger.debug("The attack 1hit/2missed: "+resultOfAttack);
		
		//Store last player move in session
		session.setAttribute("lastPlayerMove", target);
		session.setAttribute("lastPlayerResult", resultOfAttack);
				
		//Increment number of hits
		int numOfHits = (Integer)session.getAttribute("playerNumOfHits");
		//int numOfHits = service.countSuccessfulHits((Integer)session.getAttribute("uid")); //This function should be used when loading a game & stored in session
		if(resultOfAttack==1){
			numOfHits++;
			session.setAttribute("playerNumOfHits", numOfHits);
			logger.debug("Player1 successful hits increased: "+numOfHits);
		}

		//Determine if move won
		if(numOfHits == 17){
			resultOfAttack = 5; //Return a value that indicates the winning move
			//service.ENDGAME //Update the Database, & nullify GameState
			session.removeAttribute("currGameIDInPlay");//Remove game from session so servlet can't alter DB if called
		}		
		
		//Send the JSON results
		//int lastPlayerMove [] = {target, resultOfAttack}; //TODO Determine if this is needed -- It isn't
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
