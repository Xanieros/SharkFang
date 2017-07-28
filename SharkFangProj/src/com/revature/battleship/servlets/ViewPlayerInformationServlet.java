package com.revature.battleship.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.battleship.pojos.Player;

/**
 * Servlet implementation class ViewPlayerInformationServlet
 */
public class ViewPlayerInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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

		System.out.println("Called ViewPlayerInformationServlet");
		
		HttpSession session = request.getSession();
		
		int uid = (Integer) session.getAttribute("uid");
		String username = (String) session.getAttribute("username");
		String email = (String) session.getAttribute("email");
		String fname = (String) session.getAttribute("fname");
		String lname = (String) session.getAttribute("lname");
		String profPic = (String) session.getAttribute("profPic");
		
		Player player = new Player(uid, username, null, email, fname, lname, profPic);
		
		Gson gson = new Gson();
		String playerJSON = gson.toJson(player);
		
		response.getWriter().write(playerJSON);
	}

}
