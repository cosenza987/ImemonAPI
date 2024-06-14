package com.example.imemonapi.Servlets;

import com.example.imemonapi.DAO.MoveDAO;
import com.example.imemonapi.DAO.PokemonMoveDAO;
import com.example.imemonapi.Model.Move;
import com.example.imemonapi.Model.PokemonMove;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/pokedex/movesByPokemon/*")
public class MovesByPokemonServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract Pokemon ID from the request URL
        String[] pathParts = request.getPathInfo().split("/");
        if (pathParts.length != 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int pokemonId = Integer.parseInt(pathParts[1]);

        try {
            ArrayList<PokemonMove> pokemonMoves = new PokemonMoveDAO().findByPokemonId(pokemonId);
            MoveDAO moveDAO = new MoveDAO();
            ArrayList<Move> moves = new ArrayList<>();
            for (PokemonMove move : pokemonMoves) {
                moves.add(moveDAO.findById(move.getMoveId()));
            }
            String json = new Gson().toJson(moves);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}