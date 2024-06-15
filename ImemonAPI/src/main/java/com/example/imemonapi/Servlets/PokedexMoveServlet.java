package com.example.imemonapi.Servlets;

import com.example.imemonapi.DAO.MoveDAO;
import com.example.imemonapi.Model.Move;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/pokedex/moves/*")
public class PokedexMoveServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] pathParts = request.getPathInfo().split("/");
        if (pathParts.length != 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        long moveId = Long.parseLong(pathParts[1]);

        try {
            Move move = new MoveDAO().findById(moveId);

            String json = new Gson().toJson(move);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}