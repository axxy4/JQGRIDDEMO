package com.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.bean.ConnectionClass;
import com.bean.StudentBean;




public class StudentDAO {
	private ResourceBundle rbStudentDAO = ResourceBundle.getBundle("com.DAO/StudentDAO");

	public  List<StudentBean> getAllEmployees() throws SQLException {
		List<StudentBean> list = new ArrayList<StudentBean>();
		Connection con = null;
		try {
		
			 con = ConnectionClass.getInstance().getConnection();
			
	//		 PreparedStatement ps = con.prepareStatement("select * from IFSapp.temp_Student_Info");
			 
			 	//using properties
			 PreparedStatement ps = con.prepareStatement(rbStudentDAO.getString("getAllStudent"));
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentBean sb = new StudentBean();
				sb.setName(rs.getString(1));
				
				sb.setMaths(rs.getInt(2));
				sb.setPhysics(rs.getInt(3));
				sb.setChemistry(rs.getInt(4));
				sb.setEnglish(rs.getInt(5));
				list.add(sb);
				
			}
		}
		 catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (con != null) {
				con.close();
			}
		}
		return list;
	}
	
	
	public  List<StudentBean> getEmployeesByName(String name) throws SQLException {
		List<StudentBean> list = new ArrayList<StudentBean>();
		Connection con = null;
		try {
			PreparedStatement ps=null;
			String sql = null;
			 con = ConnectionClass.getInstance().getConnection();
		
			// sql="select * from ifsapp.temp_student_info where STUDENT_NAME = '"+name+"'";
			// sql=rbStudentDAO.getString("getStudentByName");

			ps = con.prepareStatement(rbStudentDAO.getString("getStudentByName"));
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentBean sb = new StudentBean();
				sb.setName(rs.getString(1));
				sb.setMaths(rs.getInt(2));
				sb.setPhysics(rs.getInt(3));
				sb.setChemistry(rs.getInt(4));
				sb.setEnglish(rs.getInt(5));
				list.add(sb);
				
			}
		}
		 catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (con != null) {
				con.close();
			}
	}
		return list;
	}
		
		
	public  List<StudentBean> getEmployeesByName(String name,String operation) throws SQLException {

		List<StudentBean> list = new ArrayList<StudentBean>();
		Connection con = null;
		try {
			PreparedStatement ps=null;
		//	String sql = null;
			 con = ConnectionClass.getInstance().getConnection();
		 if(operation.equalsIgnoreCase("eq"))
		{
			 //sql="select * from ifsapp.temp_student_info where STUDENT_NAME = '"+name+"'";
			 ps = con.prepareStatement(rbStudentDAO.getString("getStudentByName"));
				ps.setString(1, name);
		}else if(operation.equalsIgnoreCase("cn"))
		{
			ps = con.prepareStatement(rbStudentDAO.getString("getStudentByName"));
			ps.setString(1, '%'+name+'%');
			//sql="select * from ifsapp.temp_student_info where STUDENT_NAME like '%"+name+"%'";
		}
		// ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentBean sb = new StudentBean();
				sb.setName(rs.getString(1));
				
				sb.setMaths(rs.getInt(2));
				sb.setPhysics(rs.getInt(3));
				sb.setChemistry(rs.getInt(4));
				sb.setEnglish(rs.getInt(5));
				list.add(sb);
				
			}
		}
		 catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (con != null) {
				con.close();
			}
		}
		return list;
	}
	
	
	
	public int addStudent(StudentBean sb) 
	{
		int status=0;
		Connection con = null;
		try {
		
			 con = ConnectionClass.getInstance().getConnection();
		
			PreparedStatement ps=con.prepareStatement("Insert into IFSapp.temp_Student_Info(STUDENT_NAME,MATHS,PHYSICS,CHEMISTRY,ENGLISH) values(?,?,?,?,?)");
			ps.setString(1, sb.getName());
			ps.setInt(2, sb.getMaths());
			ps.setInt(3, sb.getPhysics());
			ps.setInt(4,sb.getChemistry());
			ps.setInt(5, sb.getEnglish());
			
			
			status=ps.executeUpdate();
			
	con.close();

		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public int editStudent(StudentBean sb) throws SQLException
	{
		int status=0;
		Connection con=null;
		con=ConnectionClass.getInstance().getConnection();
		PreparedStatement ps=con.prepareStatement("update IFSapp.temp_Student_Info set MATHS=?,PHYSICS=?,CHEMISTRY=?,ENGLISH=? where STUDENT_NAME=?");
		ps.setInt(1, sb.getMaths());
		ps.setInt(2, sb.getPhysics());
		ps.setInt(3,sb.getChemistry());
		ps.setInt(4, sb.getEnglish());
		ps.setString(5, sb.getName());
		status=ps.executeUpdate();
		con.close();
		return status;
	}
	
	public int deleteStudent(String name) throws SQLException
	{
		int status=0;
		Connection con=null;
		try {
			//String n=name;
			System.out.println(name);
			con=ConnectionClass.getInstance().getConnection();
			PreparedStatement ps=con.prepareStatement("Delete from IFSapp.temp_Student_Info where STUDENT_NAME=?");
			ps.setString(1, name);
			status=ps.executeUpdate();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally 
		{
			con.close();
		}
		
		return status;
	}
}