package com.revature.battleship.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.battleship.service.*;

/**
 * Servlet implementation class DeleteGameServlet
 */
public class DeleteGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(UpdatePlayerInfoServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGameServlet() {
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
		logger.debug("Called DeleteGameServlet");
		Service service = ServiceImpl.getService();
		int gid = Integer.parseInt(request.getParameter("gid"));		
		service.deleteGame(gid);
		logger.debug(gid+" Game was Deleted!");
	}

}
