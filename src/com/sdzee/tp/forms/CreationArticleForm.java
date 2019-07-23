package com.sdzee.tp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Article;
import com.sdzee.tp.utils.Functions;
import com.sdzee.tp.utils.StaticStrings;

public final class CreationArticleForm {

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();

    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Article creerArticle( HttpServletRequest request ) {
        String nom = Functions.getValeurParametre( request, StaticStrings.ARTICLE_PARAM_NAME );
        String description = Functions.getValeurParametre( request, StaticStrings.ARTICLE_PARAM_DESCRIPTION );
        String prix = Functions.getValeurParametre( request, StaticStrings.ARTICLE_PARAM_PRICE );

        
        try {
            validationNom( nom );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.ARTICLE_PARAM_NAME, e.getMessage() );
        }

        try {
            validationDescription( description );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.ARTICLE_PARAM_DESCRIPTION, e.getMessage() );
        }

        try {
            validationPrix( prix );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.ARTICLE_PARAM_PRICE, e.getMessage() );
        }


        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Ã‰chec de la création du client.";
        }

        return new Article(nom, description, Double.parseDouble(prix));
    }

    private void validationNom( String nom ) throws FormValidationException {
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                throw new FormValidationException( "Le nom de l'article doit contenir au moins 2 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un nom d'article." );
        }
    }

    private void validationDescription( String description ) throws FormValidationException {
        if ( description != null ) {
            if ( description.length() < 10 ) {
                throw new FormValidationException( "La description doit contenir au moins 10 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer une description valide." );
        }
    }

    private void validationPrix( String prix ) throws FormValidationException {
        if ( prix != null ) {
            try {
            	Double.parseDouble(prix);
            } catch(NumberFormatException e) {
            	System.out.println("Erreur de parsing");
                throw new FormValidationException( "Le prix doit uniquement contenir des chiffres (et un point ou une virgule)." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un numéro de téléphone." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié Ã  la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
}