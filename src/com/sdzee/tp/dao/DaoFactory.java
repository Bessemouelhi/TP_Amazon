package com.sdzee.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.sdzee.tp.utils.StaticStrings.*;

public class DaoFactory {
	
	private static DaoFactory instance = null;
	private static Connection connexion = null;
	
	private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
    	
    	if(instance != null) {
    		return instance;
    	}
    	
        try {
            Class.forName(DAO_NAME);
        } catch (ClassNotFoundException e) {

        }

        instance = new DaoFactory(
        		DAO_CLASSNAME, DAO_USER, DAO_PASS);
        
        return instance;
    }

    public static Connection getConnection() throws SQLException {
    	if(connexion != null) {
    		return connexion;
    	}
    	
    	connexion = DriverManager.getConnection(DAO_CLASSNAME, DAO_USER, DAO_PASS);
        connexion.setAutoCommit(false);
        return connexion; 
    }

    // Récupération de ArticleDao
    public ArticleDao getArticleDao() {
        return new ArticleDaoImpl();
    }
    
 // Récupération de ClientDao
    public ClientDao getClientDao() {
        return new ClientDaoImpl(this);
    }
    
 // Récupération de CommandeDao
    public CommandeDao getCommandeDao() {
        return new CommandeDaoImpl();
    }
    
 // Récupération de CommandeDao
    public CommandeLineDao getCommandeLineDao() {
        return new CommandeLineDaoImpl();
    }

}
