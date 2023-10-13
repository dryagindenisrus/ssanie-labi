package com.servletbook;

import com.config.Config;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/")
public class SimpleBookServlet extends HttpServlet {

    // ParseIniConfig paramsConfig = new ParseIniConfig();
    Config paramsConfig = new Config();
    private Notebook notebook;
    String pathToSave;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        notebook = new Notebook();
        pathToSave = paramsConfig.pathToSave;
        notebook.loadFromTextFile(pathToSave);

        getServletContext().setAttribute("notebook", notebook);
        getServletContext().setAttribute("pathToSave", pathToSave);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
        request.setAttribute("notes", notebook.getAllNotes());
        dispatcher.forward(request, response);
    }
}
