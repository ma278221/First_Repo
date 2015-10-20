package com.wipro.service;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wipro.bean.Employee;

@Path("/employee")
public class EmployeeService {
	
	// JDBC driver name and database URL
			static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
			static final String DB_URL = "jdbc:mysql://localhost/employees";
			 
			//  Database credentials
			static final String USER = "root";
			static final String PASS = "root";
			
			Connection conn = null;
			Statement stmt = null;
	
	@GET
	@Path("check")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeDetails(){
		
		System.out.println("Hello world");
		Employee employee = new Employee();
		employee.setUsername("mayur.popade");
		employee.setPassword("mayurpopade");
		employee.setRoleid(1);
		employee.setName("Mayur");
		employee.setPhone("9403063944");
		employee.setEmail("mayur.popade@gmail.com");
		employee.setDepid(5);
		return Response.status(200).entity(employee).build();
		
	}
	
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployeeDetails(Employee employee) throws ClassNotFoundException, SQLException{
		
		System.out.println("Inside method update");
		
		//Fetching individual variables from input
		String username = employee.getUsername();
		String password = employee.getPassword();
		int roleid = employee.getRoleid();
		String name = employee.getName();
		String phone = employee.getPhone();
		String email = employee.getEmail();
		int depid = employee.getDepid();
		
		//Register driver
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
		System.out.println("Connecting to database");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
		//Execute a query
		String sql;
	    sql = "UPDATE Employee SET password = ?, roleid = ?, name = ?, phone = ?, email = ?, depid = ? where username = ?";
	    PreparedStatement updateemp = conn.prepareStatement(sql);
	    updateemp.setString(7, username);
	    updateemp.setString(1,password);
	    updateemp.setInt(2,roleid);
	    updateemp.setString(3,name);
	    updateemp.setString(4,phone);
	    updateemp.setString(5,email);
	    updateemp.setInt(6,depid);
	    
	    updateemp.executeUpdate();
	    
	    //Closing active connections
	    updateemp.close();
	    conn.close();
	    
		return Response.status(200).entity(employee).build();	//Not getting response at client
		
	}
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee employee) throws ClassNotFoundException, SQLException
	{
		System.out.println("Inside method addEmployee");
		
		//Fetching input data from employee
		String username = employee.getUsername();
		String password = employee.getPassword();
		int roleid = employee.getRoleid();
		String name = employee.getName();
		String phone = employee.getPhone();
		String email = employee.getEmail();
		int depid = employee.getDepid();
		
		//STEP 2: Register JDBC driver
	    Class.forName("com.mysql.jdbc.Driver");

	    //STEP 3: Open a connection
	    System.out.println("Connecting to database...");
	    conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    
	    //STEP 4: Execute a query
	    System.out.println("Creating statement...");
	    //Using prepared statement
	    //stmt = conn.createStatement();
	    String sql;
	    sql = "INSERT into Employee values(?,?,?,?,?,?,?)";
	    PreparedStatement addemp = conn.prepareStatement(sql);
	    addemp.setString(1, username);
	    addemp.setString(2,password);
	    addemp.setInt(3,roleid);
	    addemp.setString(4,name);
	    addemp.setString(5,phone);
	    addemp.setString(6,email);
	    addemp.setInt(7,depid);
	    
	    addemp.executeUpdate();
	    
	    addemp.close();
	    //stmt.close();
	    conn.close();
		return Response.status(204).entity(username).build();
	}
	
	@DELETE
	@Path("delete")
	public Response deleteEmployee(String username) throws ClassNotFoundException, SQLException
	{
		System.out.println(username);
		//Register driver
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		String query;
		query = "Delete from Employee where username=?";
		PreparedStatement deleteemp = conn.prepareStatement(query);
		deleteemp.setString(1, username);
		
		deleteemp.executeUpdate(query);
		
		deleteemp.close();
		conn.close();
		
		return Response.status(200).entity("Employee deleted").build();
	}
	
	
}
