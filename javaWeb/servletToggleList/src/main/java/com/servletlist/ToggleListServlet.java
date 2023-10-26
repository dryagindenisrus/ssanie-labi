package com.servletlist;

import com.config.Config;
import com.list.ToggleList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/")
public class ToggleListServlet extends HttpServlet {
    Config paramsConfig = new Config();
    String pathToSave;
    ToggleList list;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pathToSave = paramsConfig.pathToSave;
        list = new ToggleList(pathToSave);
        getServletContext().setAttribute("pathToSave", pathToSave);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main.jsp");

        request.setAttribute("toggelList", list.getAllItems());
        dispatcher.forward(request, response);
    }
}
