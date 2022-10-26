package com.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.Dao;
import com.model.Item;
import com.model.UserItem;

/**
 * Servlet implementation class ViewUserTable
 */
@WebServlet("/viewusertable")
public class ViewUserTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Dao dao;
	List<UserItem> uitems;
	
    public void init() {
     dao = new Dao();
     uitems = new ArrayList<UserItem>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		uitems = dao.DBSELECTrequestsbyUsers();
		HttpSession session = request.getSession();
		session.setAttribute("usertableitems", uitems);
		response.sendRedirect("userupdatereq.jsp");
	}


}
