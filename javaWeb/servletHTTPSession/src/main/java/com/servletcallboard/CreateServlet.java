package com.servletcallboard;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher;
        if(session != null ) {
            if (null != session.getAttribute("username")) {
                dispatcher = getServletContext().getRequestDispatcher("/create.jsp");
                request.setAttribute("username", session.getAttribute("username"));
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath());
            }
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
