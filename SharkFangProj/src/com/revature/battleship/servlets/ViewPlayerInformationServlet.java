package com.revature.battleship.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.revature.battleship.pojos.Player;
import com.revature.battleship.pojos.Record;
import com.revature.battleship.service.Service;
import com.revature.battleship.service.ServiceImpl;

/**
 * Servlet implementation class ViewPlayerInformationServlet
 */
public class ViewPlayerInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ViewPlayerInformationServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPlayerInformationServlet() {
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
		logger.debug("Called ViewPlayerInformationServlet");		
		HttpSession session = request.getSession();
		Service service = ServiceImpl.getService();
		
		//Player info to create Player object to send
		int uid = (Integer) session.getAttribute("uid");
		String username = (String) session.getAttribute("username");
		String email = (String) session.getAttribute("email");
		String fname = (String) session.getAttribute("fname");
		String lname = (String) session.getAttribute("lname");
		String profPic = (String) session.getAttribute("profPic");
		
		//Player info and record are sent together for viewing profile
		Player player = new Player(uid, username, null, email, fname, lname, profPic);
		Record playerRecord = service.loadPlayerRecord(uid);
		Object [] obj = {player, playerRecord}; 
		
		
		Gson gson = new Gson();
		String playerJSON = gson.toJson(obj);
		
		response.getWriter().write(playerJSON);
		
	}

}
