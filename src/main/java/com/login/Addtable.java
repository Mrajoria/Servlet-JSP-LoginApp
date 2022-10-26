package com.login;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class Addtable
 */
@WebServlet("/AddTableAction")
public class Addtable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao;
	Item item;
	UserItem uitem;
	
	public void init() {
		dao = new Dao();
		item = new Item();
		uitem= new UserItem();
	}
     
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String usernamefromsession = (String)session.getAttribute("username");
		
		
		if(usernamefromsession.equals("pragya720"))
		{
		String name = request.getParameter("itemname");
		int quantity  = Integer.parseInt(request.getParameter("itemquant"));
		String location = request.getParameter("itemloc");
		String status = request.getParameter("itemstat");
		
		uitem.setName(name);
		uitem.setQuantity(quantity);
		uitem.setLocation(location);
		uitem.setStatus(status);
		uitem.setActioncode("pending");
		dao.DBINSERTrequestsbyUsers(uitem);
		}
		else {
			String name = request.getParameter("itemname");
			int quantity  = Integer.parseInt(request.getParameter("itemquant"));
			String location = request.getParameter("itemloc");
			String status = request.getParameter("itemstat");
		
			Item item = new Item();
			item.setName(name);
			item.setQuantity(quantity);
			item.setLocation(location);
			item.setStatus(status);
			
			try {
				dao.insertUser(item);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		    }
			
			response.sendRedirect("welcome.jsp");
	}

	
}






