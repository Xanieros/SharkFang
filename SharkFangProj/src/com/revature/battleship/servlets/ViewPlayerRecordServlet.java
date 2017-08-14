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
public class ViewPlayerRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ViewPlayerInformationServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPlayerRecordServlet() {
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
		logger.debug("Called the ViewPlayerRecordServlet");
		
		HttpSession session = request.getSession();
		Service service = ServiceImpl.getService();
		
		//Get UID from session
		int uid = (Integer)session.getAttribute("uid");
		Record playerRecord = service.loadPlayerRecord(uid);
		
		Gson gson = new Gson();
		String JSON = gson.toJson(playerRecord);
		
		response.getWriter().write(JSON);
		
	}

}
