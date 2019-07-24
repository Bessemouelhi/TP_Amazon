package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Article;
import com.sdzee.tp.beans.BeanException;
import com.sdzee.tp.beans.Client;
import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.dao.ArticleDao;
import com.sdzee.tp.dao.ClientDao;
import com.sdzee.tp.dao.CommandeDao;
import com.sdzee.tp.dao.CommandeLineDao;
import com.sdzee.tp.dao.DaoException;
import com.sdzee.tp.dao.DaoFactory;
import com.sdzee.tp.utils.Functions;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet("/afficherCommande")
public class AfficherCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoFactory daoFactory = DaoFactory.getInstance();
	ClientDao clientDao = daoFactory.getClientDao();
	ArticleDao articleDao = daoFactory.getArticleDao();
	CommandeDao commandeDao = daoFactory.getCommandeDao();
	CommandeLineDao lineDao = daoFactory.getCommandeLineDao();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Récupération du paramètre */
		UUID commandeId = null;
		if(Functions.getValeurParametre(request, StaticStrings.COMMANDE_PARAM_ID) != null) {
			commandeId = UUID.fromString(Functions.getValeurParametre(request, StaticStrings.COMMANDE_PARAM_ID));
		}
		
		System.out.println(commandeId.toString());


		/* Récupération de l'objet dans le contexte */
        ServletContext context = request.getServletContext();
        Map<UUID, Commande> commandes = (HashMap<UUID, Commande>) context.getAttribute( StaticStrings.COMMANDE_CONTEXT_NAME_MAP );
        Map<UUID, Client> clients = (HashMap<UUID, Client>) context.getAttribute( StaticStrings.CLIENT_CONTEXT_NAME_MAP );
        //Map<UUID, Article> articles = (HashMap<UUID, Article>) context.getAttribute( StaticStrings.ARTICLE_CONTEXT_NAME_MAP );

        //Commande commande = commandes.get(commandeId);
        Commande commande = null;
		try {
			commande = commandeDao.getById(commandeId);
		} catch (BeanException | DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        request.setAttribute(StaticStrings.COMMANDE_CONTEXT_NAME_SINGLE, commande);

        //Client client = clients.get(commande.getClientId());
        Client client = null;
		try {
			client = clientDao.getById(commande.getClientId());
		} catch (BeanException | DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        request.setAttribute(StaticStrings.CLIENT_CONTEXT_NAME_SINGLE, client);

        //Map<UUID, Integer> quantity = commande.getArticles();
        Map<UUID, Integer> quantity = null;
		try {
			quantity = lineDao.getById(commandeId);
		} catch (BeanException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Article> myArticles = new ArrayList<Article>();
        
        for (UUID u : quantity.keySet()) {
        	System.out.println(u);
			try {
				myArticles.add(articleDao.getById(u));
			} catch (BeanException | DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
     
        request.setAttribute(StaticStrings.ARTICLE_CONTEXT_NAME_MAPQ, quantity);
        request.setAttribute(StaticStrings.ARTICLE_CONTEXT_NAME_MAP, myArticles);

		this.getServletContext().getRequestDispatcher( StaticStrings.COMMANDE_VIEW_ONE ).forward( request, response );
	}

}
