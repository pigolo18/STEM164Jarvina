package com.webapp.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Students {

    Connection conn;
    Statement st;
    ResultSet rs;

    public ResultSet GetData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/webapp";
            conn = DriverManager.getConnection(url, "root", "");
            st = conn.createStatement();
            rs = st.executeQuery( "SELECT * FROM students");

        } catch (Exception e) {

        }
        return rs;
    }
    public void Close(){
        try{
            rs.close();
            st.close();
            conn.close();
        }catch (Exception e){}
    }
}
