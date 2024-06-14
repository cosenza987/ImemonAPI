package com.example.imemonapi.Servlets;

import com.example.imemonapi.DAO.*;
import com.example.imemonapi.Model.*;
import com.example.imemonapi.Responses.PokedexResponse;
import com.example.imemonapi.Responses.PokemonResponse;
import com.example.imemonapi.Responses.SimplePokedexResponse;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/pokedex/*")
public class PokemonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            handleGetAll(request, response);
        } else if (pathInfo.startsWith("/simple")) {
            handleGetSimple(request, response);
        } else {
            handleGetById(request, response);
        }
    }

    private void handleGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PokemonDAO pokemonDAO = new PokemonDAO();
            ArrayList<Pokemon> pokemons = pokemonDAO.findAll();

            ArrayList<PokedexEntryClass> pokedexEntries = new ArrayList<>();
            for (Pokemon pokemon : pokemons) {
                ArrayList<String> types = new PokemonTypeDAO().findByPokemonId(pokemon.getId());
                pokedexEntries.add(new PokedexEntryClass(pokemon,types));
            }
            Gson gson = new Gson();
            String pokemonsJson = gson.toJson(new PokedexResponse(200, "success", pokedexEntries));

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(pokemonsJson);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleGetSimple(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PokemonDAO pokemonDAO = new PokemonDAO();
            ArrayList<SimplePokedexEntryClass> simplePokedex = pokemonDAO.findSimpleAll();

            Gson gson = new Gson();
            String simplePokedexJson = gson.toJson(new SimplePokedexResponse(200, "successfully logged in", simplePokedex));

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(simplePokedexJson);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleGetById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            int id = Integer.parseInt(pathInfo.substring(1));
            PokemonDAO pokemonDAO = new PokemonDAO();
            Pokemon pokemon = pokemonDAO.findById(id);

            if (pokemon == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }


            ArrayList<String> types = new PokemonTypeDAO().findByPokemonId(pokemon.getId());
            ArrayList<PokemonAbility> pokemonAbilities = new PokemonAbilityDAO().findByPokemonId(pokemon.getId());
            AbilityDAO abilityDAO = new AbilityDAO();
            ArrayList<Ability> abilities = new ArrayList<>();
            for (PokemonAbility pokemonAbility : pokemonAbilities) {
                abilities.add(abilityDAO.findById(pokemonAbility.getAbilityId()));
            }
            ArrayList<PokemonMove> pokemonMoves = new PokemonMoveDAO().findByPokemonId(pokemon.getId());
            ArrayList<Move> moves = new ArrayList<>();
            MoveDAO moveDAO = new MoveDAO();
            for (PokemonMove pokemonMove : pokemonMoves) {
                moves.add(moveDAO.findById(pokemonMove.getMoveId()));
            }

            PokemonClass pokemonClass = new PokemonClass(pokemon, types, abilities, moves);


            Gson gson = new Gson();
            String pokemonJson = gson.toJson(new PokemonResponse(200, "success", pokemonClass));

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(pokemonJson);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
