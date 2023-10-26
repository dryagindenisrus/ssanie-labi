package com.servletcallboard.actions;

import com.auth.Authentication;
import com.config.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/action/login")
public class LoginActionServlet extends HttpServlet {

    String pathToSaveUsers;
    Authentication authentication;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pathToSaveUsers = (String) getServletContext().getAttribute("pathToSaveUsers");
        if (pathToSaveUsers == null) {
            Config paramConfig = new Config();
            pathToSaveUsers = paramConfig.pathToSaveUsers;
        }
        authentication = new Authentication(pathToSaveUsers);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/html");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println("L: " + username + " P: " + password);

            if (authentication.checkAuth(username, password)){
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath());
            } else {
                request.setAttribute("isValid", "0");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}