package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Article;
import com.sdzee.tp.beans.Client;
import com.sdzee.tp.dao.ArticleDao;
import com.sdzee.tp.dao.ClientDao;
import com.sdzee.tp.dao.DaoException;
import com.sdzee.tp.dao.DaoFactory;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet("/listeClients")
public class ListeClients extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	DaoFactory daoFactory = DaoFactory.getInstance();
	ClientDao clientDao = daoFactory.getClientDao();


	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* A la réception d'une requète GET, affichage de la liste des clients */
		List<Client> clients = null;
		
		try {
			clients = clientDao.getAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("clients", clients);
        this.getServletContext().getRequestDispatcher( StaticStrings.CLIENT_VIEW_LIST ).forward( request, response );
    }
}