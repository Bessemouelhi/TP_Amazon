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
import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.forms.CreationCommandeForm;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet("/creationCommande")
public class CreationCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* à€ la réception d'une requète GET, simple affichage du formulaire */
        this.getServletContext().getRequestDispatcher( StaticStrings.COMMANDE_VIEW_CREATE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        CreationCommandeForm form = new CreationCommandeForm();

        /* Traitement de la requète et récupération du bean en résultant */
        Commande commande = form.creerCommande( request );

        /* Ajout du bean et de l'objet métier à  l'objet requète */
        request.setAttribute( StaticStrings.COMMANDE_CONTEXT_NAME_SINGLE, commande );
        request.setAttribute( StaticStrings.FORM, form );

        /* Si aucune erreur */
        if ( form.getErreurs().isEmpty() ) {
            ServletContext context = request.getServletContext();

            /* Récupération de la map des commandes dans le context */
            Map<String, Commande> commandes = (HashMap<String, Commande>) context.getAttribute( StaticStrings.COMMANDE_CONTEXT_NAME_MAP );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( commandes == null ) {
                commandes = new HashMap<String, Commande>();
            }
            /* Puis ajout de la commande courante dans la map */
            commandes.put( commande.getDate().toString(), commande );
            /* Et enfin (ré)enregistrement de la map en context */
            context.setAttribute( StaticStrings.COMMANDE_CONTEXT_NAME_MAP, commandes );

            /* Affichage de la fiche récapitulative */
            this.getServletContext().getRequestDispatcher( StaticStrings.COMMANDE_VIEW_ONE ).forward( request, response );
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
            this.getServletContext().getRequestDispatcher( StaticStrings.COMMANDE_VIEW_CREATE ).forward( request, response );
        }
    }
}