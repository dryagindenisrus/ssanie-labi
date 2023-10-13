package com.servletbook.actions;

import com.servletbook.Notebook;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/action/reset")
public class ResetActionServlet extends HttpServlet {

    Notebook notebook;
    String pathToSave;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        notebook = (Notebook) getServletContext().getAttribute("notebook");
        pathToSave = (String) getServletContext().getAttribute("pathToSave");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setAttribute("notes", notebook.getAllNotes());
        notebook.clearAllNotes();
        notebook.saveToTextFile(pathToSave);
        response.sendRedirect(request.getContextPath());

    }
}
