package org_ibm_site;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")

public class Signup extends HttpServlet {



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		  // CORS
	    resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5501");
	    resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	    resp.setHeader("Access-Control-Allow-Headers", "Content-Type");



		  String username = req.getParameter("username");
		    String password = req.getParameter("password");

		    System.out.println("created " + username);
		    System.out.println("created " + password);


		    try {

		        Class.forName("com.mysql.cj.jdbc.Driver");

		        Connection con = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/ibm_db",
		            "root",
		            "root"
		        );

		        String query =
		            "INSERT INTO users(username, password) VALUES (?, ?)";

		        PreparedStatement preparedStatement =
		            con.prepareStatement(query);

		        preparedStatement.setString(1, username);
		        preparedStatement.setString(2, password);

		        // This executes the INSERT query
		        int dbquery = preparedStatement.executeUpdate();

		        if (dbquery > 0) {

		            System.out.println("Data inserted successfully");

		            resp.getWriter().print("SUCCESS");

		        } else {

		            resp.getWriter().print("FAILED");

		        }

		        con.close();

		    } catch (Exception e) {

		        e.printStackTrace();

		    }
		}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}


	}






