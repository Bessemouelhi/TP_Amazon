package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.dao.CommandeDao;
import com.sdzee.tp.dao.DaoException;
import com.sdzee.tp.dao.DaoFactory;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet("/listeCommandes")
public class ListeCommandes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoFactory daoFactory = DaoFactory.getInstance();
	CommandeDao commandeDao = daoFactory.getCommandeDao();

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, affichage de la liste des commandes */
		List<Commande> commandes = null;
		
		try {
			commandes = commandeDao.getAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("commandes", commandes);
		
        this.getServletContext().getRequestDispatcher( StaticStrings.COMMANDE_VIEW_LIST ).forward( request, response );
    }
}