package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.dao.CommandeDao;
import com.sdzee.tp.dao.DaoException;
import com.sdzee.tp.dao.DaoFactory;
import com.sdzee.tp.utils.Functions;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet("/suppressionCommande")
public class SuppressionCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoFactory daoFactory = DaoFactory.getInstance();
	CommandeDao commandeDao = daoFactory.getCommandeDao();


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération du paramètre */
        UUID id = UUID.fromString( Functions.getValeurParametre( request, StaticStrings.COMMANDE_PARAM_ID ) );
        try {
			//Client client = clientDao.getById(id);
        	commandeDao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        /* Récupération de la Map des commandes enregistrées en context */
        ServletContext context = request.getServletContext();
        Map<UUID, Commande> commandes = (HashMap<UUID, Commande>) context.getAttribute( StaticStrings.COMMANDE_CONTEXT_NAME_MAP );

        /* Si la date de la commande et la Map des commandes ne sont pas vides */
        if ( id != null && commandes != null ) {
            /* Alors suppression de la commande de la Map */
            //commandes.remove( id );
            /* Et remplacement de l'ancienne Map en context par la nouvelle */
            //context.setAttribute( StaticStrings.COMMANDE_CONTEXT_NAME_MAP, commandes );
        }

        /* Redirection vers la fiche récapitulative */
        this.getServletContext().getRequestDispatcher( StaticStrings.COMMANDE_VIEW_LIST ).forward( request, response );
        //response.sendRedirect( request.getContextPath() + StaticStrings.COMMANDE_VIEW_LIST );
    }
}