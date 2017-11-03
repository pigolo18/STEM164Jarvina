package com.webapp.servlets;

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

import com.webapp.models.Students;
import com.webapp.models.Users;

@WebServlet(
        name = "login",
        urlPatterns = { "/servlet/login" }
)
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users user = new Users();
        if (user.authentication(request.getParameter("username"), request.getParameter("password"))) {
            HttpSession session = request.getSession();
            session.setAttribute("username", request.getParameter("username"));
            request.setAttribute("username", request.getParameter("username"));
            Students students = new Students();
            ResultSet rs = students.GetData();
            String studentsData = "";
            try{
                while(rs.next()){
                    studentsData +=
                            "<tr>" +
                                    "<td>"+rs.getString("student_id")+"</td>" +
                                    "<td>"+rs.getString("first_name")+"</td>" +
                                    "<td>"+rs.getString("last_name")+"</td>" +
                                    "<td>"+rs.getString("student_id")+"</td>" +
                                    "<td><img class=\"profile-img\" src=\"assets/images/" + rs.getString("image")+"\"></td>"+
                                    "<td>"+
                                    "<a href=\"view?id="+"<td>"+rs.getString("id")+"\">" +
                                    "<i class=\"fa fa-eye\" aria-hidden=\"true\"></i>" +
                                    "</a>" +
                                    "&nbsp;" +
                                    "<i class=\"fa fa-pencil\" aria-hidden=\"true\"></i>" +
                                    "&nbsp;" +
                                    "<i class=\"fa fa-trash-o\" aria-hidden=\"true\"></i>" +
                                    "</tr>";

                }
                rs.close();
            } catch (Exception e){}
            students.Close();
            request.setAttribute("studentsData", studentsData);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else{
            String errorMessage =
                    "<div class=\"alert alert-danger alert-dismissable alert-login\" role=\"alert\">\n" +
                            "   <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
                            "<span aria-hidden=\"true\">&times;</span>" +
                            "   </button>" +
                            "  <strong>Error!</strong> Invalid username or password.\n" +
                            "</div>";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<h1>test</h1>");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null) out.print(username);
        if (password != null) out.print(password);
        out.flush();
    }
}

