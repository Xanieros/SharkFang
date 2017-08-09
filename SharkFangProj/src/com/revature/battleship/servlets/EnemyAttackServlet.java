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
 * Servlet implementation class EnemyAttackServlet
 */
public class EnemyAttackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	Logger logger = Logger.getLogger(EnemyAttackServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnemyAttackServlet() {
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
		Service service = ServiceImpl.getService();
		
		int[] enemyTarget = service.enemyAttack();
		session.setAttribute("lastEnemyMove", enemyTarget[0]);
		session.setAttribute("lastEnemyResult", enemyTarget[1]);
		
		//Increment number of hits
		int numOfHits = (Integer)session.getAttribute("enemyNumOfHits");
		if(enemyTarget[1]==1){
			numOfHits++;
			session.setAttribute("enemyNumOfHits", numOfHits);
			logger.debug("Enemy successful hits increased: "+numOfHits);
		}

		//Determine if move won
		if(numOfHits == 17){
			enemyTarget[1] = 10; //Return a value that indicates the winning move
			service.saveGame(); //Record the final state of the board
			service.closeGame((Integer)session.getAttribute("currGameIDInPlay"), 0); //Change active to show winner and update both players' win/loss
			session.removeAttribute("currGameIDInPlay");//Remove game from session so servlet can't alter DB if called
		}		
		
		//Send the JSON results
		Gson gson = new Gson();
		String resultJson = gson.toJson(enemyTarget);
		logger.debug("The JSON resultOfAttack is: "+resultJson);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		out.write(resultJson);		
	}

}
