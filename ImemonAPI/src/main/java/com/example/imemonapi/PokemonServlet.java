package com.example.imemonapi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/pokedex/*")
public class PokemonServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path == null || path.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        System.out.println(path);
        try {
            String[] splits = path.split("/");
            int id = Integer.parseInt(splits[1]);
            PokemonDAO op = new PokemonDAO();
            Pokemon p = op.search(id);

        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
