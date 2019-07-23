package com.sdzee.tp.dao;

import java.util.List;
import java.util.UUID;

import com.sdzee.tp.beans.BeanException;
import com.sdzee.tp.beans.Client;

public interface ClientDao {
	
	void add(Client client) throws DaoException;
	
	Client getById(UUID id) throws BeanException, DaoException;

	void update(Client client) throws DaoException;
	
	void delete(UUID id) throws DaoException;
	
	List<Client> getAll() throws DaoException;

}
