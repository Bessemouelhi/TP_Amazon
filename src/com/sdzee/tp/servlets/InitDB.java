package com.sdzee.tp.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Article;
import com.sdzee.tp.beans.Client;
import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.beans.CommandeLine;
import com.sdzee.tp.dao.ArticleDao;
import com.sdzee.tp.dao.ClientDao;
import com.sdzee.tp.dao.CommandeDao;
import com.sdzee.tp.dao.CommandeLineDao;
import com.sdzee.tp.dao.DaoException;
import com.sdzee.tp.dao.DaoFactory;
import com.sdzee.tp.utils.StaticStrings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class InitDB
 */
@WebServlet("/init")
public class InitDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoFactory daoFactory = DaoFactory.getInstance();
	ArticleDao articleDao = daoFactory.getArticleDao();
	ClientDao clientDao = daoFactory.getClientDao();
	CommandeDao commandeDao = daoFactory.getCommandeDao();
	CommandeLineDao lineDao = daoFactory.getCommandeLineDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log = LoggerFactory.getLogger(getClass());
		log.info("Debut de InitDB");
		/* CLIENTS */
		Client c1 = new Client("aaaaaaaa", "aaaaaaaa", "aaaaaaaa", "0123456789", "aaaaaaaa@mail.com");
		Client c2 = new Client("bbbbbbbb", "bbbbbbbb", "bbbbbbbb", "1234567890", "bbbbbbbb@mail.com");
		Client c3 = new Client("cccccccc", "cccccccc", "cccccccc", "2345678901", "cccccccc@mail.com");
		Client c4 = new Client("dddddddd", "dddddddd", "dddddddd", "3456789012", "dddddddd@mail.com");
		
		try {
			clientDao.add(c1);
			clientDao.add(c2);
			clientDao.add(c3);
			clientDao.add(c4);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
		
        ServletContext context = request.getServletContext();
		Map<UUID, Client> clients = (Map<UUID, Client>) context.getAttribute(StaticStrings.CLIENT_CONTEXT_NAME_MAP);
        /* Create map if none exist */
        if ( clients == null ) {
            clients = new HashMap<UUID, Client>();
        }
        /* Add new objects */
        clients.put( c1.getId(), c1 );
        clients.put( c2.getId(), c2 );
        clients.put( c3.getId(), c3 );
        clients.put( c4.getId(), c4 );
        /* Save in context */
        context.setAttribute( StaticStrings.CLIENT_CONTEXT_NAME_MAP, clients );

        
        /* ARTICLES */
        Article a1 = new Article("telephone", "Un super telephone de Pomme", 800.0);
        Article a2 = new Article("television", "Une magnifique vision", 900.0);
        Article a3 = new Article("ordinateur", "Un super Or 10 routeur", 100.0);
        Article a4 = new Article("bureau", "Un super Bures Haut", 50.0);
        
        try {
			articleDao.add(a1);
	        articleDao.add(a2);
	        articleDao.add(a3);
	        articleDao.add(a4);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
        
		Map<UUID, Article> articles = (Map<UUID, Article>) context.getAttribute(StaticStrings.ARTICLE_CONTEXT_NAME_MAP);
        /* Create map if none exist */
        if ( articles == null ) {
        	articles = new HashMap<UUID, Article>();
        }
        /* Add new objects */
        articles.put( a1.getId(), a1 );
        articles.put( a2.getId(), a2 );
        articles.put( a3.getId(), a3 );
        articles.put( a4.getId(), a4 );
        /* Save in context */
        context.setAttribute( StaticStrings.ARTICLE_CONTEXT_NAME_MAP, articles );

        
        /* COMMANDES */
        Map<UUID, Integer> lc1 = new HashMap<UUID, Integer>();
        lc1.put(a1.getId(), 1); lc1.put(a2.getId(), 2);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
		try {
			date = formatter.parse("2019-07-22 14:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Commande co1 = new Commande(c1.getId(), date, "Carte", "Validé", "La Poste", "En cours", lc1);
		
        
        Map<UUID, Integer> lc2 = new HashMap<UUID, Integer>();
        lc2.put(a1.getId(), 1); lc2.put(a3.getId(), 6);
        try {
			date = formatter.parse("2019-07-23 14:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Commande co2 = new Commande(c2.getId(), date, "Liquide", "Validé", "UPS", "En attente", lc2);
        
        Map<UUID, Integer> lc3 = new HashMap<UUID, Integer>();
        lc3.put(a2.getId(), 4); lc3.put(a3.getId(), 2);
        try {
			date = formatter.parse("2019-07-24 14:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Commande co3 = new Commande(c2.getId(), date, "Carte", "Validé", "DHL", "En cours", lc3);
        
        try {
        	commandeDao.add(co1);
        	commandeDao.add(co2);
        	commandeDao.add(co3);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
        
        
        
        Map<UUID, Commande> commandes = (Map<UUID, Commande>) context.getAttribute(StaticStrings.COMMANDE_CONTEXT_NAME_MAP);
        /* Create map if none exist */
        if ( commandes == null ) {
        	commandes = new HashMap<UUID, Commande>();
        }
        /* Add new objects */
        commandes.put( co1.getId(), co1 );
        commandes.put( co2.getId(), co2 );
        commandes.put( co3.getId(), co3 );
        /* Save in context */
        context.setAttribute( StaticStrings.COMMANDE_CONTEXT_NAME_MAP, commandes );

	}
}
