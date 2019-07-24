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

import com.sdzee.tp.beans.BeanException;
import com.sdzee.tp.beans.Client;
import com.sdzee.tp.dao.ClientDao;
import com.sdzee.tp.dao.DaoException;
import com.sdzee.tp.dao.DaoFactory;
import com.sdzee.tp.utils.Functions;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet(StaticStrings.CLIENT_URL_DELETE)
public class SuppressionClient extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	DaoFactory daoFactory = DaoFactory.getInstance();
	ClientDao clientDao = daoFactory.getClientDao();


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération du paramètre */
        UUID id = UUID.fromString( Functions.getValeurParametre( request, StaticStrings.CLIENT_PARAM_ID ) );
        try {
			//Client client = clientDao.getById(id);
			clientDao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        /* Récupération de la Map des clients enregistrés en context */
        ServletContext context = request.getServletContext();
        Map<UUID, Client> clients = (HashMap<UUID, Client>) context.getAttribute( StaticStrings.CLIENT_CONTEXT_NAME_MAP );

        /* Si le nom du client et la Map des clients ne sont pas vides */
        if ( id != null && clients != null ) {
            /* Alors suppression du client de la Map */
            clients.remove( id );
            /* Et remplacement de l'ancienne Map en context par la nouvelle */
            context.setAttribute( StaticStrings.CLIENT_CONTEXT_NAME_MAP, clients );
        }

        /* Redirection vers la fiche récapitulative */
        response.sendRedirect( request.getContextPath() + StaticStrings.CLIENT_URL_LIST );
    }
}