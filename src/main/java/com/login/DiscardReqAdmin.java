package com.login;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.model.Item;
import com.model.UserItem;


@WebServlet("/discardreq")
public class DiscardReqAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao;

	UserItem uitem;
	List<UserItem> uitems;
	public void init(ServletConfig config) throws ServletException {
		dao = new Dao();
		uitem = new UserItem();
		uitems = new ArrayList<>();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramname = request.getParameter("id");
		
		if(!paramname.equals("all")){
			try {
				dao.DBupdate_WByUsers("invalid", paramname);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		response.sendRedirect("viewtable.jsp");	
		}
		else if(paramname.equals("all")){
			uitems = dao.DBSELECT_BY_CLM_requestsbyUsers("pending");
			for(UserItem usritem:uitems) {
				
				String name = usritem.getName();
			
				String actioncode = "invalid";
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

