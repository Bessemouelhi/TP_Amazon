package com.sdzee.tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.sdzee.tp.beans.BeanException;
import com.sdzee.tp.beans.CommandeLine;

public class CommandeLineDaoImpl implements CommandeLineDao {

	@Override
	public void add(CommandeLine line) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DaoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("INSERT INTO commande_line("
            		+ "id_com, "
            		+ "id_art, "
            		+ "quantite) "
            		+ "VALUES(UuidToBin(?), UuidToBin(?), ?);");
            preparedStatement.setString(1, line.getIdCommande().toString());
            preparedStatement.setString(2, line.getIdArticle().toString());
            preparedStatement.setInt(3, line.getQuantite());

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
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }*/
	}

	@Override
	public Map<UUID, Integer> getById(UUID id) throws BeanException, DaoException {
		Map<UUID, Integer> articles = new HashMap<UUID, Integer>();
        Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet resultat = null;

        try {
            connexion = DaoFactory.getConnection();
            statement = connexion.prepareStatement("SELECT UuidFromBin(id_com), "
            		+ "UuidFromBin(id_art), "
            		+ "quantite "
            		+ "FROM commande_line WHERE id_com = UuidToBin(?);");
            statement.setString(1, id.toString());
            resultat = statement.executeQuery();
            
            while (resultat.next()) {
            	UUID idC = UUID.fromString(resultat.getString("UuidFromBin(id_com)"));
            	UUID idA = UUID.fromString(resultat.getString("UuidFromBin(id_art)"));
                int quantite = (int) resultat.getInt("quantite");
                
                articles.put(idA, quantite);
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
        
        return articles;
	}

	@Override
	public void update(CommandeLine line) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UUID id) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DaoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("DELETE FROM commande_line" + 
            		" WHERE id_com = UuidToBin(?)");
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
	public List<CommandeLine> getAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
