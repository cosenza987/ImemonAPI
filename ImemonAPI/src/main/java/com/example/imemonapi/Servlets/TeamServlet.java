package com.example.imemonapi.Servlets;

import com.example.imemonapi.DAO.LoggedUsersDAO;
import com.example.imemonapi.DAO.TeamDAO;
import com.example.imemonapi.Model.LoggedUsers;
import com.example.imemonapi.Model.Team;
import com.example.imemonapi.Requests.AuthRequest;
import com.example.imemonapi.Requests.TeamRequest;
import com.example.imemonapi.Responses.SimpleResponse;
import com.example.imemonapi.Responses.TeamResponse;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/teams/*")
public class TeamServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/retrieve")) {
            retrieveTeams(request, response);
        } else if (pathInfo.startsWith("/delete")) {
            deleteTeam(request, response);
        } else if (pathInfo.equals("/add")) {
            addTeam(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void retrieveTeams(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String session = request.getParameter("session");
        String username = request.getParameter("username");
        try {
            if (!isLoggedUser(session,username)){
                Gson gson = new Gson();
                String simplePokedexJson = gson.toJson(new SimpleResponse(403, "forbidden"));

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // Set content type and write JSON response
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(simplePokedexJson);
                out.flush();
                return;
            }

            ArrayList<Team> teams = new TeamDAO().findAllByUsername(username);
            Gson gson = new Gson();
            String TeamJson = gson.toJson(new TeamResponse(200, "Returning teams", teams));

            // Set content type and write JSON response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(TeamJson);
            out.flush();
        } catch (Exception e) {
            Gson gson = new Gson();
            String simplePokedexJson = gson.toJson(new SimpleResponse(500, "internal error"));

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // Set content type and write JSON response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(simplePokedexJson);
            out.flush();
        }
    }

    private void deleteTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String session = request.getParameter("session");
        String username = request.getParameter("username");
        String teamIdString = request.getPathInfo().substring("/delete/".length());
        int teamId = Integer.parseInt(teamIdString);
        try {
            if (!isLoggedUser(session,username)){
                Gson gson = new Gson();
                String simplePokedexJson = gson.toJson(new SimpleResponse(403, "forbidden"));

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // Set content type and write JSON response
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(simplePokedexJson);
                out.flush();
                return;
            }
            TeamDAO teamDAO = new TeamDAO();
            Team team = teamDAO.findById(teamId);
            if (team == null || !team.getUsername().equals(username)) {
                if (!isLoggedUser(session,username)){
                    Gson gson = new Gson();
                    String simplePokedexJson = gson.toJson(new SimpleResponse(404, "forbidden"));

                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    // Set content type and write JSON response
                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.print(simplePokedexJson);
                    out.flush();
                    return;
                }
            }

            teamDAO.deleteById(teamId);

            Gson gson = new Gson();
            String simpleResponseJson = gson.toJson(new SimpleResponse(200, "Deleted Team"));

            // Set content type and write JSON response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(simpleResponseJson);
            out.flush();
        } catch (Exception e) {
            Gson gson = new Gson();
            String simpleResponseJson = gson.toJson(new SimpleResponse(500, "internal error"));

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // Set content type and write JSON response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(simpleResponseJson);
            out.flush();
        }
    }

    private void addTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String session = request.getParameter("session");
        String username = request.getParameter("username");
        try {
            if (!isLoggedUser(session,username)){
                Gson gson = new Gson();
                String simpleResponseJson = gson.toJson(new SimpleResponse(403, "forbidden"));

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // Set content type and write JSON response
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(simpleResponseJson);
                out.flush();
                return;
            }

            TeamRequest teamRequest = new TeamRequest(
                    request.getParameter("session"),
                    request.getParameter("username"),
                    request.getParameter("teamName"),
                    Integer.parseInt(request.getParameter("poke1")),
                    Integer.parseInt(request.getParameter("move11")),
                    Integer.parseInt(request.getParameter("move12")),
                    Integer.parseInt(request.getParameter("move13")),
                    Integer.parseInt(request.getParameter("move14")),
                    Integer.parseInt(request.getParameter("poke2")),
                    Integer.parseInt(request.getParameter("move21")),
                    Integer.parseInt(request.getParameter("move22")),
                    Integer.parseInt(request.getParameter("move23")),
                    Integer.parseInt(request.getParameter("move24")),
                    Integer.parseInt(request.getParameter("poke3")),
                    Integer.parseInt(request.getParameter("move31")),
                    Integer.parseInt(request.getParameter("move32")),
                    Integer.parseInt(request.getParameter("move33")),
                    Integer.parseInt(request.getParameter("move34")),
                    Integer.parseInt(request.getParameter("poke4")),
                    Integer.parseInt(request.getParameter("move41")),
                    Integer.parseInt(request.getParameter("move42")),
                    Integer.parseInt(request.getParameter("move43")),
                    Integer.parseInt(request.getParameter("move44")),
                    Integer.parseInt(request.getParameter("poke5")),
                    Integer.parseInt(request.getParameter("move51")),
                    Integer.parseInt(request.getParameter("move52")),
                    Integer.parseInt(request.getParameter("move53")),
                    Integer.parseInt(request.getParameter("move54")),
                    Integer.parseInt(request.getParameter("poke6")),
                    Integer.parseInt(request.getParameter("move61")),
                    Integer.parseInt(request.getParameter("move62")),
                    Integer.parseInt(request.getParameter("move63")),
                    Integer.parseInt(request.getParameter("move64"))
            );

            TeamDAO teamDAO = new TeamDAO();
            teamDAO.insertFromTeamRequest(teamRequest);
            Gson gson = new Gson();
            String simpleResponseJson = gson.toJson(new SimpleResponse(200, "added"));

            // Set content type and write JSON response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(simpleResponseJson);
            out.flush();
            return;
        } catch (Exception e) {
            Gson gson = new Gson();
            String simpleResponseJson = gson.toJson(new SimpleResponse(500, "server error"));

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // Set content type and write JSON response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(simpleResponseJson);
            out.flush();
            return;
        }
    }

    private AuthRequest extractAuthRequest(HttpServletRequest request) {
        String session = request.getParameter("session");
        String username = request.getParameter("username");
        return new AuthRequest(session, username);
    }

    private boolean isLoggedUser(String session, String username) throws SQLException {
        try {
            LoggedUsersDAO loggedUsersDAO = new LoggedUsersDAO();
            LoggedUsers loggedUsers = loggedUsersDAO.findBySession(session);
            if (loggedUsers == null) return false;
            else return loggedUsers.getUsername().equals(username);
        } catch (Exception e) {
            return false;
        }
    }
}