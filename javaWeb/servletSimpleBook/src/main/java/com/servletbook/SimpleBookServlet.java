package com.servletbook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SimpleBookServlet extends HttpServlet {

    private Notebook notebook;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        notebook = new Notebook();
        ArrayList<String> nums = new ArrayList<>();
        nums.add("Tel1"); nums.add("tel2");
        notebook.addOrUpdateNote("Misha", nums.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        notebook.addOrUpdateNote("Title", "Value1");

        request.setAttribute("notes", notebook.getAllNotes());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        response.setHeader("X-Total-count", "100");
        if (uri.equals("/servlet-simple-book/action/add")) {
            String name = request.getParameter("name");
            notebook.addOrUpdateNote("Title", name);
            response.encodeRedirectURL("");
        } else if (uri.equals("/servlet-simple-book/action/reset")) {
            notebook.clearAllNotes();
            response.encodeRedirectURL("");
        }

        request.setAttribute("notes", notebook.getAllNotes());
        dispatcher.forward(request, response);
    }
}
