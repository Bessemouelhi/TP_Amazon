package com.sdzee.tp.dao;

import java.util.List;
import java.util.UUID;

import com.sdzee.tp.beans.BeanException;
import com.sdzee.tp.beans.Commande;

public interface CommandeDao {
	
	void add(Commande commande) throws DaoException;
	
	Commande getById(UUID id) throws BeanException, DaoException;

	void update(Commande commande) throws DaoException;
	
	void delete(UUID id) throws DaoException;
	
	List<Commande> getAll() throws DaoException;

}
