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
@WebServlet("/EnemyAttackServlet")
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
		
		int numOfHits = service.countSuccessfulHits(0);
		logger.debug("Enemy successful hits: "+numOfHits);
		if(numOfHits == 17){
			enemyTarget[1] = 5; //Return a value that indicates the winning move
			session.setAttribute("currGameIDInPlay", null); //Don't want the servlet to be able to take anymore moves
		}	
		
		Gson gson = new Gson();
		String resultJson = gson.toJson(enemyTarget);
		logger.debug("The enemy target/result is: "+enemyTarget);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		out.write(resultJson);
	}

}
