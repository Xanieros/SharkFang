package com.revature.battleship.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.battleship.pojos.Player;
import com.revature.battleship.service.Service;
import com.revature.battleship.service.ServiceImpl;

/**
 * Servlet implementation class UpdatePlayerInfoServlet
 */

public class UpdatePlayerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(UpdatePlayerInfoServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePlayerInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = ServiceImpl.getService();
		
		//Retrieve the current Player data
		HttpSession session = request.getSession();
		int uid 		= (Integer)session.getAttribute("uid");
		String uname 	= (String) session.getAttribute("username");
		String pword  	= service.getPassword(uid); //This requires a DB call as we do not store pass in session
		String email 	= (String)session.getAttribute("email");
		String fname 	= (String)session.getAttribute("fname");
		String lname 	= (String)session.getAttribute("lname");
		/*Profile Picture*/
		
		//This represents player before updates
		Player player = new Player(uid, uname, pword, email, fname, lname, null);
		logger.debug("Player Info before: "+player);		
		
		//Retrieve the new Player Data from frontend
		String fnameNew = request.getParameter("fname");
		String lnameNew = request.getParameter("lname");
		String emailNew = request.getParameter("email");
		String pwordNew = request.getParameter("pword");
		logger.debug(fnameNew+lnameNew+emailNew+pwordNew);
		
		//These will change the player to reflect updated value
		if(pwordNew != null){
			player.setPword(pwordNew);
		}

		if(fnameNew != null){
			player.setFname(fnameNew);
		}
		
		if(lnameNew != null){
			player.setLname(lnameNew);
		}
		
		if(emailNew != null){
			player.setEmail(emailNew);
		}
		logger.debug("Player Info after: "+player);
		
		//Call the method
		Player updatedPlayer = service.updatePlayer(player);
		
		//Update the session objects
		if(updatedPlayer != null){
			session.setAttribute("email", updatedPlayer.getEmail());
			session.setAttribute("fname", updatedPlayer.getFname());
			session.setAttribute("lname", updatedPlayer.getLname());
		}
		else{
			System.out.println("Why you do this");
		}
		
	}

}
