package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Article;
import com.sdzee.tp.beans.Client;
import com.sdzee.tp.utils.Functions;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet("/afficherArticle")
public class AfficherArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Récupération du paramètre */
		UUID id = null;
		if(Functions.getValeurParametre(request, StaticStrings.ARTICLE_PARAM_ID) != null) {
			id = UUID.fromString(Functions.getValeurParametre(request, StaticStrings.ARTICLE_PARAM_ID));
		}
		
		/* Récupération de l'objet dans le contexte */
        ServletContext context = request.getServletContext();
        Map<UUID, Article> articles = (HashMap<UUID, Article>) context.getAttribute( StaticStrings.ARTICLE_CONTEXT_NAME_MAP );

        Article article = articles.get(id);
        request.setAttribute(StaticStrings.ARTICLE_CONTEXT_NAME_SINGLE, article);
        
		this.getServletContext().getRequestDispatcher( StaticStrings.ARTICLE_VIEW_ONE ).forward( request, response );
	}
}
