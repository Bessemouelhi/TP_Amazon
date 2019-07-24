package com.sdzee.tp.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.sdzee.tp.beans.BeanException;
import com.sdzee.tp.beans.CommandeLine;

public interface CommandeLineDao {
	
	void add(CommandeLine line) throws DaoException;
	
	Map<UUID, Integer> getById(UUID id) throws BeanException, DaoException;

	void update(CommandeLine line) throws DaoException;
	
	void delete(UUID id) throws DaoException;
	
	List<CommandeLine> getAll() throws DaoException;

}
