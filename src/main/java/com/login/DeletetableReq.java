package com.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.Dao;
import com.model.UserItem;


@WebServlet("/UserDelete")
public class DeletetableReq extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	Dao dao;
	UserItem uitem;
	List<UserItem> uitems;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		dao = new Dao();
	
;	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		
		
		if(username.equals("pragya720")){
		System.out.println("UserDelete servlet called after delete action");
		
		StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
	    String queryString = request.getQueryString();
	    System.out.println(requestURL);
	    System.out.println(queryString);
	    
	    String name = request.getParameter("name");
	    int quantity =  Integer.parseInt(request.getParameter("quantity"));
	    String location = 	request.getParameter("location");
	    String status = request.getParameter("status");
	
	    
	    
	    uitem = new UserItem(name,quantity,location,status,"userdelete");
	    
	    dao.DBINSERTrequestsbyUsers(uitem);
	    
	    
		response.sendRedirect("viewtable.jsp");
//		try {
//			dao.DBdeleteItem_By_User(paramvalue);
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		}
//		response.sendRedirect("userupdatereq.jsp");
		
	}
		else if (username.equals("admin"))
		{
			String name = request.getParameter("name");
		    if(name !=null) {
		     System.out.println("ADMIN ACTION...DELETING FROM MAIN TABLE");
		    try {
				dao.deleteItem(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     response.sendRedirect("viewtable.jsp");
		    }
		    
		   
		}
 
	}

}
