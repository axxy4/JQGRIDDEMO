package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.StudentDAO;
import com.bean.StudentBean;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		int maths=Integer.parseInt(request.getParameter("maths"));
		int physics=Integer.parseInt(request.getParameter("physics"));
		int chemistry=Integer.parseInt(request.getParameter("chemistry"));
		int english=Integer.parseInt(request.getParameter("english"));
		
		StudentBean sb=new StudentBean();
		sb.setName(name);
		sb.setMaths(maths);
		sb.setPhysics(physics);
		sb.setChemistry(chemistry);
		sb.setEnglish(english);
		StudentDAO sdao=new StudentDAO();
		int status=sdao.addStudent(sb);
		if(status>0)
		{
			out.print("Record succesfully added");
			
		}
		else
		{
			out.print("Fail to add Record");
		}
		
		
		
		
		//HttpSession httpss=request.getSession();
		//Connection con=
		
	}

}
