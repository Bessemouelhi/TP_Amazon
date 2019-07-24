package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Article;
import com.sdzee.tp.dao.ArticleDao;
import com.sdzee.tp.dao.DaoException;
import com.sdzee.tp.dao.DaoFactory;
import com.sdzee.tp.utils.StaticStrings;

/**
 * Servlet implementation class ListeArticles
 */
@WebServlet("/listeArticles")
public class ListeArticles extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	DaoFactory daoFactory = DaoFactory.getInstance();
	ArticleDao articleDao = daoFactory.getArticleDao();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Article> articles = null;
		
		try {
			articles = articleDao.getAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("articles", articles);
        this.getServletContext().getRequestDispatcher( StaticStrings.ARTICLE_VIEW_LIST ).forward( request, response );
	}

}
