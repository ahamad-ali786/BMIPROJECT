package org_ibm_site;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")


public class Login  extends HttpServlet{




	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {


	    // CORS
	    resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5501");
	    resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	    resp.setHeader("Access-Control-Allow-Headers", "Content-Type");



	    // Get data from frontend
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");

	    System.out.println("received " + username);
	    System.out.println("received " + password);

	    try {

	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	
	        String url = "jdbc:mysql://localhost:3306/ibm_db";
	        String user = "root";
	        String pass = "root";

	        Connection con = DriverManager.getConnection(url, user, pass);

	        String query =
	            "SELECT * FROM users WHERE username = ? AND password = ?";

	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1, username);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            resp.getWriter().println("Login Successful");
	            
	            System.out.println(rs);

	        } else {

	            resp.getWriter().println("Invalid Username or Password");

	        }

	        con.close();

	    } catch (Exception e) {

	       e.getCause().printStackTrace();




	    }
	}




		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//			resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
//			resp.setHeader("Access-Control-Allow-Methods", "POST");
			doPost(req, resp);
		}

	}




