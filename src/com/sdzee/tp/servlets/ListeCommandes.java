package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.utils.StaticStrings;

@WebServlet("/listeCommandes")
public class ListeCommandes extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, affichage de la liste des commandes */
        this.getServletContext().getRequestDispatcher( StaticStrings.COMMANDE_VIEW_LIST ).forward( request, response );
    }
}