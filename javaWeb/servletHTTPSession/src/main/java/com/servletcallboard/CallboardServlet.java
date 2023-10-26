package com.servletcallboard;

import com.auth.Authentication;
import com.board.Advertisement;
import com.board.AdvertisementManager;
import com.config.Config;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class CallboardServlet extends HttpServlet {

    Config paramsConfig = new Config();
    AdvertisementManager items;
    Authentication users;

    String pathToSaveItems;
    String pathToSaveUsers;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        items = new AdvertisementManager();
        pathToSaveItems = paramsConfig.pathToSaveItems;
        pathToSaveUsers = paramsConfig.pathToSaveUsers;

        items.loadFromTextFile(pathToSaveItems);

        getServletContext().setAttribute("advertisements", items);
        getServletContext().setAttribute("pathToSaveItems", pathToSaveItems);
        getServletContext().setAttribute("pathToSaveUsers", pathToSaveUsers);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            HttpSession session = request.getSession(false);
            if(session != null){
                String username = (String) session.getAttribute("username");
                request.setAttribute("username", username);
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
            request.setAttribute("advertisements", items.getAllAdvertisements());
            dispatcher.forward(request, response);

    }
}
