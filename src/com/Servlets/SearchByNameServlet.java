package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ConnectionClass;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SearchByNameServlet
 */
@WebServlet("/SearchByNameServlet")
public class SearchByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		JSONObject jsonobject=new JSONObject();
		JSONArray jsonarray=new JSONArray();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sortBy = "";
		int currentPage;
		String sortOrder = "";
		int recordSize;
		String searchField = "";
		String searchString = "";
		String searchOper = "";
		String searchQuery = "";
		try {
			Connection con=ConnectionClass.getInstance().getConnection();
			//default query
			String sql="select * from ifsapp.temp_student_info";
			boolean isSearch=Boolean.valueOf(request.getParameter("_search"));
			if(isSearch=true)
			{
				sortBy = request.getParameter("sidx");
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
						searchQuery="select * from ifsapp.temp_student_info where name = '"+searchString+"'";
					}else if(searchOper.equalsIgnoreCase("cn"))
					{
						searchQuery="select * from ifsapp.temp_student_info where name like '%"+searchString+"%'";
					}
				}
				sql=searchQuery;
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
			}
			}
			while(rs.next())
			{
				JSONObject obj=new JSONObject();
				obj.put("name", rs.getString("STUDENT_NAME"));
				obj.put("maths", rs.getString("MATHS"));
				obj.put("physics", rs.getString("PHYSICS"));
				obj.put("chemistry", rs.getString("CHEMISTRY"));
				obj.put("english", rs.getString("ENGLISH"));
				jsonarray.add(obj);
			}
			jsonobject.put("page", "1");
			jsonobject.put("total", "1");
			jsonobject.put("records", jsonarray.size());
			jsonobject.put("rows", jsonarray);
			out.print(jsonobject);

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
