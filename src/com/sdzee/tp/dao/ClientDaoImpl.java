package com.sdzee.tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sdzee.tp.beans.Article;
import com.sdzee.tp.beans.BeanException;
import com.sdzee.tp.beans.Client;

public class ClientDaoImpl implements ClientDao {
	
	private DaoFactory daoFactory;

	ClientDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        
    }
	
	/*CREATE TABLE IF NOT EXISTS client 
	(id_client BINARY(16) NOT NULL PRIMARY KEY,
	nom varchar(30) NOT NULL,
	prenom varchar(30),
	password varchar(50) NOT NULL,
	telephone varchar(10) NOT NULL,
	email varchar(30)
	);*/

	@Override
	public void add(Client client) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("INSERT INTO client(id_client, nom, prenom, password, telephone, email) VALUES(UuidToBin(?), ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, client.getId().toString());
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3, client.getPrenom());
            preparedStatement.setString(4, client.getPassword());
            preparedStatement.setString(5, client.getTelephone());
            preparedStatement.setString(6, client.getEmail());

            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            e.printStackTrace();
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        /*finally {
            try {
                if (connexion != null) {
                    //connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }*/
	}

	@Override
	public Client getById(UUID id) throws BeanException, DaoException {
		Client client = new Client();
        Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet resultat = null;

        try {
            connexion = DaoFactory.getConnection();
            statement = connexion.prepareStatement("SELECT UuidFromBin(id_client), nom, prenom, password, telephone, email FROM client WHERE id_client = UuidToBin(?);");
            statement.setString(1, id.toString());
            resultat = statement.executeQuery();
            
            while (resultat.next()) {
            	UUID idA = UUID.fromString(resultat.getString("UuidFromBin(id_client)"));
            	String nom = resultat.getString("nom");
            	String prenom = resultat.getString("prenom");
                String telephone = resultat.getString("telephone");
                String password = resultat.getString("password");
            	String email = resultat.getString("email");

                client.setId(idA);
                client.setNom(nom);
                client.setPrenom(prenom);
                client.setPassword(password);
                client.setTelephone(telephone);
                client.setEmail(email);
            }
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            e.printStackTrace();
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        /*finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }*/
        
        return client;
	}

	@Override
	public void update(Client client) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DaoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("UPDATE client" + 
            		" SET" +
            		" nom = ?," + 
            		" prenom = ?," + 
            		" password = ?," + 
            		" telephone = ?," + 
            		" email = ?" + 
            		" WHERE id_client = UuidToBin(?)");
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.setString(4, client.getTelephone());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.setString(6, client.getId().toString());

            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
	}

	@Override
	public void delete(UUID id) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DaoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("DELETE FROM client" + 
            		" WHERE id_client = UuidToBin(?)");
            preparedStatement.setString(1, id.toString());
            preparedStatement.executeUpdate();
            
            connexion.commit();
        } 
        catch (SQLIntegrityConstraintViolationException e) {
        	throw new DaoException("Impossible de supprimer cet article");
        } 
        catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } 
            catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
	}

	@Override
	public List<Client> getAll() throws DaoException {
		List<Client> listClient = new ArrayList<Client>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT UuidFromBin(id_client), nom, prenom, password, telephone, email FROM client;");

            while (resultat.next()) {
            	UUID idA = UUID.fromString(resultat.getString("UuidFromBin(id_client)"));
            	String nom = resultat.getString("nom");
            	String prenom = resultat.getString("prenom");
                String telephone = resultat.getString("telephone");
                String password = resultat.getString("password");
            	String email = resultat.getString("email");

                Client client = new Client();
                client.setId(idA);
                client.setNom(nom);
                client.setPrenom(prenom);
                client.setPassword(password);
                client.setTelephone(telephone);
                client.setEmail(email);

                listClient.add(client);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        /*finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }*/
        
        return listClient;
	}

}
