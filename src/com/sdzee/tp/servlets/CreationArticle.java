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
import com.sdzee.tp.forms.CreationArticleForm;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet("/creationArticle")
public class CreationArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Return form */
        this.getServletContext().getRequestDispatcher( StaticStrings.ARTICLE_VIEW_CREATE ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Create form to test all fields */
        CreationArticleForm form = new CreationArticleForm();

        /* Create object */
        Article article = form.creerArticle( request );

        /* Add object and form to request */
        request.setAttribute( StaticStrings.ARTICLE_CONTEXT_NAME_MAP, article );
        request.setAttribute( StaticStrings.FORM, form );

        /* If no error are found */
        if ( form.getErreurs().isEmpty() ) {
            /* Get map from context */
            ServletContext context = request.getServletContext();
			Map<UUID, Article> articles = (Map<UUID, Article>) context.getAttribute(StaticStrings.ARTICLE_CONTEXT_NAME_MAP);
            /* Create map if it is empty */
            if ( articles == null ) {
                articles = new HashMap<UUID, Article>();
            }
            /* Add newly created object to corresponding map */
            articles.put( article.getId(), article );
            /* Save to context */
            context.setAttribute( StaticStrings.ARTICLE_CONTEXT_NAME_MAP, articles );

            /* Show created object */
            this.getServletContext().getRequestDispatcher( StaticStrings.ARTICLE_VIEW_ONE ).forward( request, response );
        } else {
            /* Re-Show previous view containing errors */
            this.getServletContext().getRequestDispatcher( StaticStrings.ARTICLE_VIEW_CREATE ).forward( request, response );
        }
	}

}
