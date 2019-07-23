package com.sdzee.tp.dao;

import java.util.List;
import java.util.UUID;

import com.sdzee.tp.beans.Article;
import com.sdzee.tp.beans.BeanException;


public interface ArticleDao {
	
	void add(Article article) throws DaoException;
	
	Article getById(UUID id) throws BeanException, DaoException;

	void update(Article article) throws DaoException;
	
	void delete(UUID id) throws DaoException;
	
	List<Article> getAll() throws DaoException;

}
