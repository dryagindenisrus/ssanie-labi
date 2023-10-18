package com.servletbook.actions;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import com.servletbook.Notebook;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/action/search")
public class SearchActionServlet extends HttpServlet {

    Notebook notebook;
    String pathToSave;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        notebook = (Notebook) getServletContext().getAttribute("notebook");
        pathToSave = (String) getServletContext().getAttribute("pathToSave");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String search = request.getParameter("search");

        request.setAttribute("notes", notebook.getByTitle(search));
        response.sendRedirect(request.getContextPath());
    }
}
