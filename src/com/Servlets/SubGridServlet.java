package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

import net.sf.json.JSONObject;

@WebServlet("/SubGridServlet")
public class SubGridServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			List<StudentBean> list = new ArrayList<StudentBean>();
			StudentDAO dao = new StudentDAO();
			PrintWriter out = response.getWriter();
		
		  String rowid_data=request.getParameter("name"); //alert(rowid_data);
		  System.out.print("servlet data is"+rowid_data);
		  try 
		  { 
		Connection  con=ConnectionClass.getInstance().getConnection(); 
		  list = dao.getEmployeesByName(rowid_data);
		  
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
		  mainObj.put("data",list); 
		  out.print(mainObj.toString()); } catch (SQLException e) 
		  { 
			  e.printStackTrace(); }
		  }
		 
			
		/*  PrintWriter out = response.getWriter(); StudentBean sb1=new StudentBean();
		  sb1.setName("Dilip"); sb1.setMaths(78); sb1.setPhysics(88);
		  sb1.setChemistry(73); sb1.setEnglish(86);
		 
		 StudentBean sb2=new StudentBean(); sb2.setName("ParthG"); sb2.setMaths(90);
		  sb2.setPhysics(80); sb2.setChemistry(70); sb2.setEnglish(60);
		  
		  StudentBean sb3=new StudentBean(); sb3.setName("John"); sb3.setMaths(48);
		  sb3.setPhysics(47); sb3.setChemistry(40); sb3.setEnglish(35);
		  
		  StudentBean sb4=new StudentBean(); sb4.setName("Tracy,R"); sb4.setMaths(36);
		  sb4.setPhysics(10); sb4.setChemistry(32); sb4.setEnglish(11);
		  
		 StudentBean sb5=new StudentBean(); sb5.setName("Rocky"); sb5.setMaths(45);
		  sb5.setPhysics(51); sb5.setChemistry(49); sb5.setEnglish(50);
		  
		 StudentBean sb6=new StudentBean(); 
		 sb6.setName("Jack"); 
		 sb6.setMaths(30);
		 sb6.setPhysics(20); 
		 sb6.setChemistry(28); 
		 sb6.setEnglish(27);
		
		  List <StudentBean> listobj=new ArrayList<StudentBean>(); listobj.add(sb1);
		  listobj.add(sb2); listobj.add(sb3); listobj.add(sb4); listobj.add(sb5);
		  listobj.add(sb6);
		  
		  JSONObject mainObj = new JSONObject(); 
		  
		  mainObj.put("data", listobj);
		  
		  mainObj.put("total", 1); mainObj.put("page", 1); mainObj.put("records",
		  listobj.size()); out.print(mainObj); }*/
	}
	//}

