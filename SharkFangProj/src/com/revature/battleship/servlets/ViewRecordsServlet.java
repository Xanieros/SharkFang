package com.revature.battleship.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.revature.battleship.pojos.Record;
import com.revature.battleship.service.Service;
import com.revature.battleship.service.ServiceImpl;

/**
 * Servlet implementation class ViewRecordsServlet
 */
public class ViewRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ViewPlayerInformationServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRecordsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.debug("Called the ViewRecordsServlet");
		
		HttpSession session = request.getSession();
		Service service = ServiceImpl.getService();
		
		//Get UID from session and limit from frontend
		int uid = (Integer)session.getAttribute("uid");
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//allRecords contains the Player Record at index 0
		//And the top <limit> records at index > 0
		ArrayList<Record> allRecords = service.loadTopRecords(limit);
		allRecords.add(0, service.loadPlayerRecord(uid));
		
		Gson gson = new Gson();
		String JSON = gson.toJson(allRecords);
		
		response.getWriter().write(JSON);
		
	}

}
