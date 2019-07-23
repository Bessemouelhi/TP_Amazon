package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.utils.StaticStrings;

@WebServlet("/listeClients")
public class ListeClients extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, affichage de la liste des clients */
        this.getServletContext().getRequestDispatcher( StaticStrings.CLIENT_VIEW_LIST ).forward( request, response );
    }
}