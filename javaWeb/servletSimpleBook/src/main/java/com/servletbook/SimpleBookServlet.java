package com.servletbook;

import com.config.Config;
import com.config.ParseIniConfig;

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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String telephone = request.getParameter("telephone");
        notebook.addOrUpdateNote(name, telephone);
        notebook.saveToTextFile(pathToSave);

        request.setAttribute("notes", notebook.getAllNotes());
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        request.setAttribute("notes", notebook.getAllNotes());

        switch (uri) {
            case "/servlet-simple-book/action/reset" -> {
                notebook.clearAllNotes();
                notebook.saveToTextFile(pathToSave);
//                response.sendRedirect(request.getContextPath());
            }
            case "static/" -> {
                return;
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
        request.setAttribute("notes", notebook.getAllNotes());
        dispatcher.forward(request, response);
    }
}
