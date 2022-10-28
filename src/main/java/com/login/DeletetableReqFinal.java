package com.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Dao;
import com.model.UserItem;

/**
 * Servlet implementation class DeletetableReqFinal
 */
@WebServlet("/UserDeleteToAdmin")
public class DeletetableReqFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserItem uitem;
    Dao dao;
    List<UserItem> uitems;
    
	public void init(ServletConfig config) throws ServletException {
	uitem = new UserItem();
	dao = new Dao();
	uitems = new ArrayList<>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	       if(!request.getParameter("uitemname").equals("all")) {
	    	System.out.println("USER ACTION -deleting 1 record submitted by USER from MAIN table");
	    	String getnametodelete = request.getParameter("uitemname");
	    	try {
	    		dao.DBdeleteItem_By_User(getnametodelete);
				dao.deleteItem(getnametodelete);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	response.sendRedirect("viewtable.jsp");
	    }
	       else if (request.getParameter("uitemname").equals("all")) {
	    	System.out.println("USER ACTION -deleting all records submitted by user from main table");
	    	
			uitems = dao.DBSELECT_BY_CLM_requestsbyUsers("userdelete");

	    	for(UserItem usritem:uitems) {
				
				String uname = usritem.getName();
			
				try {
					dao.DBdeleteItem_By_User(uname);
					dao.deleteItem(uname);
				} catch (SQLException e) {
			
					e.printStackTrace();
				}
			}
	    	response.sendRedirect("viewtable.jsp");
	    }
	}

}
