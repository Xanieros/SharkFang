package com.revature.battleship.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Service service = ServiceImpl.getService();
		GameState gs = service.loadGame(Integer.parseInt(request.getParameter("gid")));
		
		Gson gson = new Gson();
		String JSON = gson.toJson(gs);		
		response.getWriter().write(JSON);
		
	}

}
