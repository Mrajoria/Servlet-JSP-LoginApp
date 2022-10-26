package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Dao;
import com.model.Item;
import com.model.UserItem;

/**
 * Servlet implementation class Approvereq
 */
@WebServlet("/approvereq")
public class ApproveReq extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Dao dao;  
    UserItem uitem;
    Item item;
    List<UserItem> uitems;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		dao = new Dao();
		item = new Item();
		uitem = new UserItem();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramname = request.getParameter("id");
		System.out.println(paramname);
		if(!paramname.equals("all")){
			uitem = dao.DBSELECT_WrequestsbyUsers(paramname);	
			
			String name = uitem.getName();
			int quantity =  uitem.getQuantity();
			String location =  uitem.getLocation();
			String status  = uitem.getStatus();
			
			item = new Item(name,quantity,location,status);
			
			System.out.println("PARAMETER IS SINGLE- showing item object attributes..");
			System.out.println(item.getName());
			System.out.println(item.getQuantity());
			System.out.println(item.getLocation());
			System.out.println(item.getStatus());

			
			try {
				dao.insertUser(item);
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			String actioncode = "approved";
			try {
				dao.DBupdate_WByUsers(actioncode, paramname);
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
			response.sendRedirect("viewtable.jsp");
		}
		else if(paramname.equals("all")){
			uitems = dao.DBSELECT_BY_CLM_requestsbyUsers("pending");
			for(UserItem usritem:uitems) {
				
				String name = usritem.getName();
				int quantity =  usritem.getQuantity();
				String location =  usritem.getLocation();
				String status  = usritem.getStatus();
				
				item = new Item(name,quantity,location,status);
				
				System.out.println("PARAMETER IS ALL- showing item object attributes..");
				System.out.println(item.getName());
				System.out.println(item.getQuantity());
				System.out.println(item.getLocation());
				System.out.println(item.getStatus());

				
				try {
					dao.insertUser(item);
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				String actioncode = "approved";
				try {
					dao.DBupdate_WByUsers(actioncode, name);
				} catch (SQLException e) {
			
					e.printStackTrace();
				}
			}
		
			response.sendRedirect("viewtable.jsp");
		}
	
		
	}

}
