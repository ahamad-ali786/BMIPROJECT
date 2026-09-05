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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("created " + username);

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("MYSQL DRIVER LOADED SUCCESSFULLY");

            // Railway MySQL variables
            String host = System.getenv("MYSQLHOST");
            String port = System.getenv("MYSQLPORT");
            String database = System.getenv("MYSQLDATABASE");
            String dbUser = System.getenv("MYSQLUSER");
            String dbPassword = System.getenv("MYSQLPASSWORD");

            System.out.println("MYSQLHOST exists: " + (host != null));
            System.out.println("MYSQLPORT exists: " + (port != null));
            System.out.println("MYSQLDATABASE exists: " + (database != null));
            System.out.println("MYSQLUSER exists: " + (dbUser != null));
            System.out.println("MYSQLPASSWORD exists: " + (dbPassword != null));

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            Connection con = DriverManager.getConnection(url, dbUser, dbPassword);
            

            System.out.println("DATABASE CONNECTED SUCCESSFULLY");

            String query =
                    "INSERT INTO users(username, password) VALUES (?, ?)";

            PreparedStatement preparedStatement =
                    con.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int dbquery = preparedStatement.executeUpdate();

            if (dbquery > 0) {

                System.out.println("Data inserted successfully");

                resp.getWriter().print("SUCCESS");

            } else {

                resp.getWriter().print("FAILED");
            }

            preparedStatement.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            resp.getWriter().print(
                    "Database Error: " + e.getMessage()
            );
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doPost(req, resp);
    }
}


