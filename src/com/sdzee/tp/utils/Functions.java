package com.sdzee.tp.utils;

import javax.servlet.http.HttpServletRequest;

public class Functions {

	
    /*
     * Méthode utilitaire qui retourne null si un paramètre est vide, et son contenu sinon.
     */
    public static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
