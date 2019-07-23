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


public class ArticleDaoImpl implements ArticleDao {
	
	//private DaoFactory daoFactory;

	@Override
	public void add(Article article) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DaoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("INSERT INTO article(id, nom, description, prix) VALUES(UuidToBin(?), ?, ?, ?);");
            preparedStatement.setString(1, article.getId().toString());
            preparedStatement.setString(2, article.getNom());
            preparedStatement.setString(3, article.getDescription());
            preparedStatement.setDouble(4, article.getPrix());

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
	public Article getById(UUID id) throws BeanException, DaoException {
		Article article = null;
        Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet resultat = null;

        try {
            connexion = DaoFactory.getConnection();
            statement = connexion.prepareStatement("SELECT id, nom, description, prix FROM article WHERE id = UuidToBin(?);");
            statement.setString(1, id.toString());
            resultat = statement.executeQuery();
            
            while (resultat.next()) {
                UUID idA = UUID.fromString(resultat.getString("id"));
                String nom = resultat.getString("nom");
                String description = resultat.getString("description");
                double prix = resultat.getDouble("prix");

                article = new Article();
                article.setId(idA);
                article.setNom(nom);
                article.setDescription(description);
                article.setPrix(prix);
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
        
        return article;
	}

	@Override
	public List<Article> getAll() throws DaoException {
		List<Article> listArticle = new ArrayList<Article>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = DaoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id, nom, description, prix FROM article;");

            while (resultat.next()) {
            	UUID idA = UUID.fromString(resultat.getString("id"));
            	String nom = resultat.getString("nom");
                String description = resultat.getString("description");
                double prix = resultat.getDouble("prix");

                Article article = new Article();
                article.setId(idA);
                article.setNom(nom);
                article.setDescription(description);
                article.setPrix(prix);

                listArticle.add(article);
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
        
        return listArticle;
	}

	@Override
	public void update(Article article) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DaoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("UPDATE article" + 
            		" SET" +
            		" nom = ?," + 
            		" description = ?," + 
            		" prix = ?" + 
            		" WHERE id = UuidToBin(?)");
            preparedStatement.setString(1, article.getNom());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setDouble(3, article.getPrix());
            preparedStatement.setString(4, article.getId().toString());

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
	public void delete(UUID id) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DaoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("DELETE FROM article" + 
            		" WHERE id = UuidToBin(?)");
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
        /*finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données 2");
            }
        }*/
		
	}

}
