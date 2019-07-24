package com.sdzee.tp.servlets;

import java.io.IOException;
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
import com.sdzee.tp.dao.ArticleDao;
import com.sdzee.tp.dao.DaoException;
import com.sdzee.tp.dao.DaoFactory;
import com.sdzee.tp.utils.Functions;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet(StaticStrings.ARTICLE_URL_DELETE)
public class SuppressionArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoFactory daoFactory = DaoFactory.getInstance();
	ArticleDao articleDao = daoFactory.getArticleDao();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Get id from form */
        UUID id = UUID.fromString( Functions.getValeurParametre( request, StaticStrings.ARTICLE_PARAM_ID ) );
        
        try {
			//Client client = clientDao.getById(id);
        	articleDao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /* Get the corresponding map in context */
        ServletContext context = request.getServletContext();
        Map<UUID, Article> articles = (HashMap<UUID, Article>) context.getAttribute( StaticStrings.ARTICLE_CONTEXT_NAME_MAP );

        /* Si le nom du client et la Map des clients ne sont pas vides */
        if ( id != null && articles != null ) {
            /* Alors suppression du client de la Map */
            articles.remove( id );
            /* Replace new map */
            context.setAttribute( StaticStrings.ARTICLE_CONTEXT_NAME_MAP, articles );
        }

        /* Redirect to new list */
        response.sendRedirect( request.getContextPath() + StaticStrings.ARTICLE_URL_LIST );
	}
}
