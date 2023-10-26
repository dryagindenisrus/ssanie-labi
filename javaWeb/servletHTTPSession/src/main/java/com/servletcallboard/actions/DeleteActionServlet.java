package com.servletcallboard.actions;

import com.board.AdvertisementManager;
import com.config.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/action/delete")
public class DeleteActionServlet extends HttpServlet {

    String pathToSaveItems;
    Config paramsConfig;
    AdvertisementManager advertisementManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pathToSaveItems = (String) getServletContext().getAttribute("pathToSaveItems");
        if (pathToSaveItems == null) {
            paramsConfig = new Config();
            pathToSaveItems = paramsConfig.pathToSaveItems;
        }
        advertisementManager = (AdvertisementManager) getServletContext().getAttribute("advertisements");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            response.setContentType("text/html");
            String title = request.getParameter("title");
            HttpSession session = request.getSession(false);

            if(session != null ){
                if (session.getAttribute("username")!=null) {
                    advertisementManager.deleteAdvertisementByTitle(title);
                    System.out.println(title);
                    advertisementManager.saveToTextFile(pathToSaveItems);
                    response.sendRedirect(request.getContextPath());
                } else {
                    response.sendRedirect(request.getContextPath());
                }
            } else {
                response.sendRedirect(request.getContextPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
