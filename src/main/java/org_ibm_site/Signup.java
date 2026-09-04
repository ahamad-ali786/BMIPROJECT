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
        System.out.println("created " + password);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Railway MySQL environment variables
            String url = System.getenv("MYSQL_URL");
            String user = System.getenv("MYSQL_USER");
            String pass = System.getenv("MYSQL_PASSWORD");

            System.out.println("MYSQL_URL exists: " + (url != null));
            System.out.println("MYSQL_USER exists: " + (user != null));
            System.out.println("MYSQL_PASSWORD exists: " + (pass != null));
            
            Connection con = DriverManager.getConnection(url, user, pass);

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
            resp.getWriter().print("Database Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doPost(req, resp);
    }
}





