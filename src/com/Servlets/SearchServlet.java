package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.StudentDAO;
import com.bean.ConnectionClass;
import com.bean.StudentBean;

import net.sf.json.JSONObject;


@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String searchbyname=request.getParameter("name");
		out.println("<!DOCTYPE html>");
	       out.println("<html>");
	       out.println("<head>");
	       out.println("<title>Servlet StudentServlet</title>");
	       out.println("</head>");
	       out.println("<body>");
		
		StudentBean sb=new StudentBean();
		ArrayList <StudentBean> l1=new ArrayList<StudentBean>();
		try {
			
			Connection con=ConnectionClass.getInstance().getConnection();
			PreparedStatement ps=con.prepareStatement("Select * from IFS.temp_Student_Info where STUDENT_NAME=?");
			ps.setString(1, searchbyname);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
			sb.setName(rs.getString(1));
			sb.setMaths(rs.getInt(2));
			sb.setChemistry(rs.getInt(4));
			sb.setPhysics(rs.getInt(3));
			sb.setEnglish(rs.getInt(5));
			l1.add(sb);
			}
			request.setAttribute("searchdata", l1);
			 RequestDispatcher rd =request.getRequestDispatcher("stdlist.jsp");
		  
		           rd.forward(request, response);
		            out.println("</body>");
		            out.println("</html>");		
		            
		            
//			JSONObject mainObj = new JSONObject();
//			mainObj.put("rows", l1);
//			out.print(mainObj.toString());
//			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		@Override
	    protected void doGet(HttpServletRequest request,
	                        HttpServletResponse response)
	        throws ServletException, IOException
	    {
	        processRequest(request, response);
	    }
	    @Override
	    protected void doPost(HttpServletRequest request,
	                        HttpServletResponse response)
	        throws ServletException, IOException
	    {
	        processRequest(request, response);
	    }
	    @Override
	    public String getServletInfo()
	    {
	        return "Short description";
	    }
}
