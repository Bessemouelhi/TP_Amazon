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

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.utils.Functions;
import com.sdzee.tp.utils.StaticStrings;

@WebServlet("/afficherClient")
public class AfficherClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Récupération du paramètre */
		UUID id = null;
		if(Functions.getValeurParametre(request, StaticStrings.CLIENT_PARAM_ID) != null) {
			id = UUID.fromString(Functions.getValeurParametre(request, StaticStrings.CLIENT_PARAM_ID));
		}
		
		/* Récupération de l'objet dans le contexte */
        ServletContext context = request.getServletContext();
        Map<UUID, Client> clients = (HashMap<UUID, Client>) context.getAttribute( StaticStrings.CLIENT_CONTEXT_NAME_MAP );

        Client client = clients.get(id);
        request.setAttribute(StaticStrings.CLIENT_CONTEXT_NAME_SINGLE, client);
        
		this.getServletContext().getRequestDispatcher( StaticStrings.CLIENT_VIEW_ONE ).forward( request, response );
	}

}
