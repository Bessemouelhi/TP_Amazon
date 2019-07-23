package com.sdzee.tp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.utils.Functions;
import com.sdzee.tp.utils.StaticStrings;

public final class CreationClientForm {
    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();

    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Client creerClient( HttpServletRequest request ) {
        String nom = Functions.getValeurParametre( request, StaticStrings.CLIENT_PARAM_NAME );
        String prenom = Functions.getValeurParametre( request, StaticStrings.CLIENT_PARAM_FIRSTNAME );
        String password = Functions.getValeurParametre( request, StaticStrings.CLIENT_PARAM_PASSWORD );
        String telephone = Functions.getValeurParametre( request, StaticStrings.CLIENT_PARAM_PHONE );
        String email = Functions.getValeurParametre( request, StaticStrings.CLIENT_PARAM_EMAIL );

        Client client = new Client();

        try {
            validationNom( nom );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.CLIENT_PARAM_NAME, e.getMessage() );
        }
        client.setNom( nom );

        try {
            validationPrenom( prenom );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.CLIENT_PARAM_FIRSTNAME, e.getMessage() );
        }
        client.setPrenom( prenom );

        try {
            validationPassword( password );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.CLIENT_PARAM_PASSWORD, e.getMessage() );
        }
        client.setPassword( password );

        try {
            validationTelephone( telephone );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.CLIENT_PARAM_PHONE, e.getMessage() );
        }
        client.setTelephone( telephone );

        try {
            validationEmail( email );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.CLIENT_PARAM_EMAIL, e.getMessage() );
        }
        client.setEmail( email );

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Échec de la création du client.";
        }

        return client;
    }

    private void validationNom( String nom ) throws FormValidationException {
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 2 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un nom d'utilisateur." );
        }
    }

    private void validationPrenom( String prenom ) throws FormValidationException {
        if ( prenom != null && prenom.length() < 2 ) {
            throw new FormValidationException( "Le prénom d'utilisateur doit contenir au moins 2 caractères." );
        }
    }

    private void validationPassword( String password ) throws FormValidationException {
        if ( password != null ) {
            if ( password.length() < 10 ) {
                throw new FormValidationException( "Le mot de passe doit contenir au moins 6 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un mot de passe valide." );
        }
    }

    private void validationTelephone( String telephone ) throws FormValidationException {
        if ( telephone != null ) {
            if ( !telephone.matches( "^\\d+$" ) ) {
                throw new FormValidationException( "Le numéro de téléphone doit uniquement contenir des chiffres." );
            } else if ( telephone.length() < 4 ) {
                throw new FormValidationException( "Le numéro de téléphone doit contenir au moins 6 chiffres." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un numéro de téléphone." );
        }
    }

    private void validationEmail( String email ) throws FormValidationException {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new FormValidationException( "Merci de saisir une adresse mail valide." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
}