package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // получаем сессию
        HttpSession session = req.getSession();
        // получаем объект username
        String username = (String) session.getAttribute("username");

        if(username == null){
            resp.sendRedirect("login.jsp");
            return;
        }

        String userHome = "C:\\Users\\Artem\\IdeaProjects\\Spring\\ServletUsersFolder\\"+username;

        String currentPath = req.getParameter("path");

        if (currentPath == null || currentPath.isEmpty()) {
            currentPath = userHome;
            File dir = new File(currentPath);
            if(!dir.exists())
                dir.mkdirs();
            currentPath = userHome; // Начальная директория
        }


        FileEntities entities = new FileEntities(currentPath);


        if (entities.parentPath() != null && entities.parentPath().startsWith(userHome)) {
            req.setAttribute("parentPath", entities.parentPath());
        } else {
            req.setAttribute("parentPath", null);
        }


        req.setAttribute("files", entities.getFiles());
        req.setAttribute("currentPath", currentPath);
        req.setAttribute("Time", new Date());

        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }

}