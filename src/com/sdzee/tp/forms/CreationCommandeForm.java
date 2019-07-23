package com.sdzee.tp.forms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.utils.Functions;
import com.sdzee.tp.utils.StaticStrings;

public final class CreationCommandeForm {
    private static final String CHAMP_LISTE_CLIENTS    = "listeClients";

    private String              resultat;
    private Map<String, String> erreurs                = new HashMap<String, String>();

    
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Commande creerCommande( HttpServletRequest request ) {
        /* Récupération du nom du client choisi */
        UUID idClient = UUID.fromString(Functions.getValeurParametre( request, CHAMP_LISTE_CLIENTS ));
        /* Récupération de l'objet client correspondant dans le context */
        ServletContext context = request.getServletContext();
        Client client = ( (Map<UUID, Client>) context.getAttribute( StaticStrings.CLIENT_CONTEXT_NAME_MAP ) ).get( idClient );


        /*
         * Récupération et conversion de la date en String selon le format
         * choisi.
         */
        DateTime dt = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern( StaticStrings.FORMAT_DATE );
        Date date = dt.toDate();

        String modePaiement = Functions.getValeurParametre( request, StaticStrings.COMMANDE_PARAM_PAYMENT_MODE );
        String statutPaiement = Functions.getValeurParametre( request, StaticStrings.COMMANDE_PARAM_PAYMENT_STATUS );
        String modeLivraison = Functions.getValeurParametre( request, StaticStrings.COMMANDE_PARAM_DELIVERY_MODE );
        String statutLivraison = Functions.getValeurParametre( request, StaticStrings.COMMANDE_PARAM_DELIVERY_STATUS );

        Commande commande = new Commande();

        commande.setClientId( client.getId() );

        commande.setDate( date );

        try {
            validationModePaiement( modePaiement );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.COMMANDE_PARAM_PAYMENT_MODE, e.getMessage() );
        }
        commande.setModePaiement( modePaiement );

        try {
            validationStatutPaiement( statutPaiement );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.COMMANDE_PARAM_PAYMENT_STATUS, e.getMessage() );
        }
        commande.setStatutPaiement( statutPaiement );

        try {
            validationModeLivraison( modeLivraison );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.COMMANDE_PARAM_DELIVERY_MODE, e.getMessage() );
        }
        commande.setModeLivraison( modeLivraison );

        try {
            validationStatutLivraison( statutLivraison );
        } catch ( FormValidationException e ) {
            setErreur( StaticStrings.COMMANDE_PARAM_DELIVERY_STATUS, e.getMessage() );
        }
        commande.setStatutLivraison( statutLivraison );

        if ( erreurs.isEmpty() ) {
            resultat = "SuccÃ¨s de la création de la commande.";
        } else {
            resultat = "Ã‰chec de la création de la commande.";
        }
        return commande;
    }


    private void validationModePaiement( String modePaiement ) throws FormValidationException {
        if ( modePaiement != null ) {
            if ( modePaiement.length() < 2 ) {
                throw new FormValidationException( "Le mode de paiement doit contenir au moins 2 caractÃ¨res." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un mode de paiement." );
        }
    }

    private void validationStatutPaiement( String statutPaiement ) throws FormValidationException {
        if ( statutPaiement != null && statutPaiement.length() < 2 ) {
            throw new FormValidationException( "Le statut de paiement doit contenir au moins 2 caractÃ¨res." );
        }
    }

    private void validationModeLivraison( String modeLivraison ) throws FormValidationException {
        if ( modeLivraison != null ) {
            if ( modeLivraison.length() < 2 ) {
                throw new FormValidationException( "Le mode de livraison doit contenir au moins 2 caractÃ¨res." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un mode de livraison." );
        }
    }

    private void validationStatutLivraison( String statutLivraison ) throws FormValidationException {
        if ( statutLivraison != null && statutLivraison.length() < 2 ) {
            throw new FormValidationException( "Le statut de livraison doit contenir au moins 2 caractÃ¨res." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié Ã  la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
}