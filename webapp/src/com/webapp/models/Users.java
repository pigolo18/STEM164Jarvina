package com.webapp.models;

import javax.imageio.metadata.IIOMetadataNode;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Users {
    public boolean authentication(String username, String password) {
        /**if(username.equals("admin") && password.equals("abc123")) return true;
        else return false;**/
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user WHERE username='"+username+"' AND password='"+password+"'");
            while (rs.next()) {
                String user = rs.getString("username");
                if (user.equals(null)) {
                    return false;
                }
                else return true;

            }
            st.close();
            rs.close();
            conn.close();
        } catch (Exception e) {

        }
        return false;


    }

    }

