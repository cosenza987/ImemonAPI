package com.example.imemonapi.Servlets;

import com.example.imemonapi.DAO.LoggedUsersDAO;
import com.example.imemonapi.DAO.UserDAO;
import com.example.imemonapi.Model.LoggedUsers;
import com.example.imemonapi.Model.User;
import com.example.imemonapi.Responses.SimpleResponse;
import com.example.imemonapi.Responses.UserResponse;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/account/*")
public class UserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            switch (pathInfo) {
                case "/login":
                    handleLogin(request, response);
                    break;
                case "/register":
                    handleRegister(request, response);
                    break;
                case "/logoff":
                    handleLogoff(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findByEmail(email);

            if (user != null && user.getPassword().equals(password)) {

                String username = user.getUsername();

                String session = CreateSession(username);

                Gson gson = new Gson();
                String userJson = gson.toJson(new UserResponse(200, "successfully logged in", user.getUsername(), session));

                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(userJson);
            } else {
                Gson gson = new Gson();
                String userJson = gson.toJson(new SimpleResponse(403, "unauthorized"));

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(userJson);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");

        if(existsUser(email)){
            Gson gson = new Gson();
            String userJson = gson.toJson(new SimpleResponse( 409, "email already registered"));
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(userJson);

            return;
        }

        // Insert new user into database
        try {
            User user = new User(0, email, password, username);
            new UserDAO().insert(user);
            Gson gson = new Gson();
            String session = CreateSession(username);
            String userJson = gson.toJson(new UserResponse(200, "registered", username, session));

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(userJson);
        } catch (Exception e) {
            Gson gson = new Gson();
            String simplePokedexJson = gson.toJson(new SimpleResponse(500, "not inserted"));

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(simplePokedexJson);
        }
    }

    private void handleLogoff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String session = request.getParameter("session");

        try {
            LoggedUsersDAO loggedUsersDAO = new LoggedUsersDAO();

            if(loggedUsersDAO.findBySession(session) == null){
                Gson gson = new Gson();
                String simplePokedexJson = gson.toJson(new SimpleResponse(403, "unauthorized"));

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(simplePokedexJson);
            }
            loggedUsersDAO.deleteBySession(session);
            Gson gson = new Gson();
            String simplePokedexJson = gson.toJson(new SimpleResponse(200, "logged out"));

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(simplePokedexJson);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }


    private boolean existsUser(String email) throws SQLException {
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findByEmail(email);
            return user != null;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isLoggedUser(String session, String username) throws SQLException {
        try {
            LoggedUsersDAO loggedUsersDAO = new LoggedUsersDAO();
            LoggedUsers loggedUsers = loggedUsersDAO.findBySession(session);
            if(loggedUsers == null) return false;
            else return loggedUsers.getUsername().equals(username);
        } catch (Exception e) {
            return false;
        }
    }

    private String CreateSession(String username) throws SQLException {
        String session = java.util.UUID.randomUUID().toString();
        try {
            LoggedUsersDAO loggedUsersDAO = new LoggedUsersDAO();
            LoggedUsers logged = loggedUsersDAO.findByUsername(username);
            if (logged != null) {
                return logged.getSession();
            }
            while(loggedUsersDAO.findBySession(session) != null){
                session = java.util.UUID.randomUUID().toString();
            }
            loggedUsersDAO.insert(new LoggedUsers(session, username));
            return session;
        } catch (Exception e) {
            return null;
        }
    }

}