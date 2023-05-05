package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.DAO.StudentDAO;
import com.bean.ConnectionClass;
import com.bean.StudentBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDAO dao = new StudentDAO();
		PrintWriter out = response.getWriter();
		List<StudentBean> list = new ArrayList<StudentBean>();
		int currentPage;
		String sortOrder = "";
		int recordSize;
		String searchField = "";
		String searchString = "";
		String searchOper = "";
		String searchQuery = "";
		
			//search operation
		//	Connection con=ConnectionClass.getInstance().getConnection();
			//default query
			boolean isSearch=Boolean.valueOf(request.getParameter("_search"));
			if(isSearch)
			{
				currentPage = Integer.valueOf(request.getParameter("page"));
				sortOrder = request.getParameter("sord");
				recordSize = Integer.valueOf(request.getParameter("rows"));
				searchField = request.getParameter("searchField");
				searchString = request.getParameter("searchString");
				searchOper = request.getParameter("searchOper");
			if(searchField!=null && !searchField.equalsIgnoreCase("") && !searchField.isEmpty())
			{
				if(searchString!=null)
				{
					searchString=searchString.toUpperCase();
				}
				if(searchField.equalsIgnoreCase("name"))
				{
					if(searchOper.equalsIgnoreCase("eq"))
					{
						try {
							list=dao.getEmployeesByName(searchString, "eq");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//searchQuery="select * from ifsapp.temp_student_info where name = '"+searchString+"'";
					}else if(searchOper.equalsIgnoreCase("cn"))
					{
						try {
							list=dao.getEmployeesByName(searchString, "cn");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//searchQuery="select * from ifsapp.temp_student_info where name like '%"+searchString+"%'";
					}
				}
				
			}
			JSONObject mainObj = new JSONObject();
			mainObj.put("page", 1);
			mainObj.put("total", 1);
			mainObj.put("records", 1);
			mainObj.put("rows", list);
			out.print(mainObj.toString());
			}//view operation
			else
			{
				
				try {
					
					
					list = dao.getAllEmployees();
					
					if(list != null ) {
						System.out.println("List is not NULL");
						list.forEach(data->{
							System.out.println("List item is : "+data.toString());
						});
						
					}
					
					JSONObject mainObj = new JSONObject();
					mainObj.put("page", 1);
					mainObj.put("total", 1);
					mainObj.put("records", 1);
					mainObj.put("rows", list);
					out.print(mainObj.toString());
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
		
			
			
		
		
	
	}
	
	

}
