package com.sdzee.tp.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import com.sdzee.tp.beans.Article;
import com.sdzee.tp.beans.BeanException;
import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.beans.CommandeLine;

public class CommandeDaoImpl implements CommandeDao {
	
	DaoFactory daoFactory = DaoFactory.getInstance();
	CommandeLineDao lineDao = daoFactory.getCommandeLineDao();
	
	/*(id BINARY(16) NOT NULL,
			id_client BINARY(16) NOT NULL,
			dateCommande DATETIME NOT NULL,
			modePaiement varchar(50) NOT NULL,
			statutPaiement varchar(50),
			modeLivraison varchar(50) NOT NULL,
			statutLivraison varchar(50),*/

	@Override
	public void add(Commande commande) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DaoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("INSERT INTO commande(id, id_client, "
            		+ "dateCommande, "
            		+ "modePaiement, "
            		+ "statutPaiement, "
            		+ "modeLivraison, "
            		+ "statutLivraison) "
            		+ "VALUES(UuidToBin(?), UuidToBin(?), DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?);");
            preparedStatement.setString(1, commande.getId().toString());
            preparedStatement.setString(2, commande.getClientId().toString());
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            preparedStatement.setString(3, formatter.format(commande.getDate()));
            preparedStatement.setString(4, commande.getModePaiement());
            preparedStatement.setString(5, commande.getStatutPaiement());
            preparedStatement.setString(6, commande.getModeLivraison());
            preparedStatement.setString(7, commande.getStatutLivraison());

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
        
        for(Entry<UUID, Integer> entry : commande.getArticles().entrySet()) {
			CommandeLine line = new CommandeLine();
		    UUID key = entry.getKey();
		    Integer q = entry.getValue();
		    line.setIdCommande(commande.getId());
		    line.setIdArticle(key);
		    line.setQuantite(q);
		    try {
				lineDao.add(line);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public Commande getById(UUID id) throws BeanException, DaoException {
		Commande commande = null;
        Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet resultat = null;

        try {
            connexion = DaoFactory.getConnection();
            statement = connexion.prepareStatement("SELECT UuidFromBin(id), "
            		+ "UuidFromBin(id_client), "
            		+ "dateCommande, "
            		+ "modePaiement, "
            		+ "statutPaiement, "
            		+ "modeLivraison, "
            		+ "statutLivraison "
            		+ "FROM commande WHERE id = UuidToBin(?);");
            statement.setString(1, id.toString());
            resultat = statement.executeQuery();
            
            while (resultat.next()) {
            	UUID idA = UUID.fromString(resultat.getString("UuidFromBin(id)"));
            	UUID idC = UUID.fromString(resultat.getString("UuidFromBin(id_client)"));
                Date dateCommande = resultat.getDate("dateCommande");
                String modePaiement = resultat.getString("modePaiement");
                String statutPaiement = resultat.getString("statutPaiement");
                String modeLivraison = resultat.getString("modeLivraison");
                String statutLivraison = resultat.getString("statutLivraison");

                commande = new Commande();
                commande.setId(idA);
                commande.setClientId(idC);
                commande.setDate(dateCommande);
                commande.setModePaiement(modePaiement);
                commande.setStatutPaiement(statutPaiement);
                commande.setModeLivraison(modeLivraison);
                commande.setStatutLivraison(statutLivraison);
            }
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
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
        
        return commande;
	}

	@Override
	public void update(Commande commande) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UUID id) throws DaoException {
		lineDao.delete(id);
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DaoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("DELETE FROM commande" + 
            		" WHERE id = UuidToBin(?)");
            preparedStatement.setString(1, id.toString());
            preparedStatement.executeUpdate();
            
            connexion.commit();
        } 
        catch (SQLIntegrityConstraintViolationException e) {
        	e.printStackTrace();
        	throw new DaoException("Impossible de supprimer cet commande");
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
	public List<Commande> getAll() throws DaoException {
		List<Commande> listCommande = new ArrayList<Commande>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = DaoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT UuidFromBin(id), UuidFromBin(id_client), dateCommande, modePaiement, statutPaiement, modeLivraison, statutLivraison FROM commande;");

            while (resultat.next()) {
            	UUID idA = UUID.fromString(resultat.getString("UuidFromBin(id)"));
            	UUID idC = UUID.fromString(resultat.getString("UuidFromBin(id_client)"));
                Date dateCommande = resultat.getDate("dateCommande");
                String modePaiement = resultat.getString("modePaiement");
                String statutPaiement = resultat.getString("statutPaiement");
                String modeLivraison = resultat.getString("modeLivraison");
                String statutLivraison = resultat.getString("statutLivraison");

                Commande commande = new Commande();
                commande.setId(idA);
                commande.setClientId(idC);
                commande.setDate(dateCommande);
                commande.setModePaiement(modePaiement);
                commande.setStatutPaiement(statutPaiement);
                commande.setModeLivraison(modeLivraison);
                commande.setStatutLivraison(statutLivraison);

                listCommande.add(commande);
            }
        } catch (SQLException e) {
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
        
        return listCommande;
	}

}
