package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPath = req.getParameter("path");

        if (currentPath == null || currentPath.isEmpty()) {
            currentPath = System.getProperty("user.home"); // Начальная директория
        }

        FileEntities entities = new FileEntities(currentPath);

        req.setAttribute("files", entities.getFiles());
        req.setAttribute("currentPath", currentPath);
        req.setAttribute("parentPath", entities.parentPath());
        req.setAttribute("Time", new Date());

        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }

}