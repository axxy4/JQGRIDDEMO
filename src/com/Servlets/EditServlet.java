package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.StudentDAO;
import com.bean.StudentBean;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =response.getWriter();
		String name=request.getParameter("name");
		int maths=Integer.parseInt(request.getParameter("maths"));
		int physics=Integer.parseInt(request.getParameter("physics"));
		int chemistry=Integer.parseInt(request.getParameter("chemistry"));
		int english=Integer.parseInt(request.getParameter("english"));
		StudentDAO dao=new StudentDAO();
		StudentBean sb=new StudentBean();
		sb.setName(name);
		sb.setMaths(maths);
		sb.setPhysics(physics);
		sb.setChemistry(chemistry);
		sb.setEnglish(english);
		try {
			int status=dao.editStudent(sb);
			if (status>0)
			{
				out.print("Edit is done");
			}
			else
			{
				out.print("Edit is not possible");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
