package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.forms.CreationClientForm;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet( urlPatterns = "/creationClient" )
public class CreationClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* la réception d'une requete GET, simple affichage du formulaire */
        this.getServletContext().getRequestDispatcher( StaticStrings.CLIENT_VIEW_CREATE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        CreationClientForm form = new CreationClientForm();

        /* Traitement de la requete et récupération du bean en résultant */
        Client client = form.creerClient( request );

        /* Ajout du bean et de l'objet métier à l'objet requete */
        request.setAttribute( StaticStrings.CLIENT_CONTEXT_NAME_SINGLE, client );
        request.setAttribute( StaticStrings.FORM, form );

        /* Si aucune erreur */
        if ( form.getErreurs().isEmpty() ) {
            /* Alors récupération de la map des clients dans le context */
            ServletContext context = request.getServletContext();
			Map<String, Client> clients = (Map<String, Client>) context.getAttribute(StaticStrings.CLIENT_CONTEXT_NAME_MAP);
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( clients == null ) {
                clients = new HashMap<String, Client>();
            }
            /* Puis ajout du client courant dans la map */
            clients.put( client.getNom(), client );
            /* Et enfin (ré)enregistrement de la map en context */
            context.setAttribute( StaticStrings.CLIENT_CONTEXT_NAME_MAP, clients );

            /* Affichage de la fiche récapitulative */
            this.getServletContext().getRequestDispatcher( StaticStrings.CLIENT_VIEW_ONE ).forward( request, response );
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
            this.getServletContext().getRequestDispatcher( StaticStrings.CLIENT_VIEW_CREATE ).forward( request, response );
        }
    }
}
