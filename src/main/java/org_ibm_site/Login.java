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
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("received " + username);
        System.out.println("received " + password);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Railway MySQL environment variables
            Class.forName("com.mysql.cj.jdbc.Driver");

           
            String url = System.getenv("MYSQL_URL");

            String dbUser = System.getenv("MYSQLUSER");
            String dbPassword = System.getenv("MYSQLPASSWORD");

            System.out.println("MYSQL_URL EXISTS = " + (url != null));
            System.out.println("MYSQLUSER EXISTS = " + (dbUser != null));
            System.out.println("MYSQLPASSWORD EXISTS = " + (dbPassword != null));

            Connection con = DriverManager.getConnection(url, dbUser, dbPassword);


            System.out.println("DATABASE CONNECTED SUCCESSFULLY");
            String query =
                "SELECT * FROM users WHERE username = ? AND password = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                resp.getWriter().println("Login Successful");

            } else {

                resp.getWriter().println("Invalid Username or Password");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Database Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doPost(req, resp);
    }
}

