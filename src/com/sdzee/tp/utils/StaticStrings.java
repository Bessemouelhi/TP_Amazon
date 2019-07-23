package com.sdzee.tp.utils;

import com.sdzee.tp.dao.DaoFactory;

public class StaticStrings {
	public static final String FORM                    = "form";
    public static final String FORMAT_DATE             = "dd/MM/yyyy HH:mm:ss";

    
    public static final String ARTICLE_CONTEXT_NAME_MAP    = "articles";
    public static final String ARTICLE_CONTEXT_NAME_MAPQ   = "quantities";
    public static final String ARTICLE_CONTEXT_NAME_SINGLE = "article";
	public static final String ARTICLE_PARAM_ID            = "idArticle";
	public static final String ARTICLE_PARAM_DESCRIPTION   = "descriptionArticle";
	public static final String ARTICLE_PARAM_NAME          = "nomArticle";
	public static final String ARTICLE_PARAM_PRICE         = "idArticle";
    public static final String ARTICLE_URL_DELETE          = "/suppressionArticle";
    public static final String ARTICLE_URL_LIST            = "/listeArticles";
    public static final String ARTICLE_VIEW_LIST           = "/WEB-INF/listerArticles.jsp";
    public static final String ARTICLE_VIEW_CREATE         = "/WEB-INF/creerArticle.jsp";
    public static final String ARTICLE_VIEW_ONE            = "/WEB-INF/afficherArticle.jsp";

    
    public static final String CLIENT_CONTEXT_NAME_MAP    = "clients";
    public static final String CLIENT_CONTEXT_NAME_SINGLE = "client";
    public static final String CLIENT_PARAM_EMAIL         = "nomClient";
    public static final String CLIENT_PARAM_FIRSTNAME     = "prenomClient";
	public static final String CLIENT_PARAM_ID            = "idClient";
    public static final String CLIENT_PARAM_NAME          = "nomClient";
    public static final String CLIENT_PARAM_PASSWORD      = "passwordClient";
    public static final String CLIENT_PARAM_PHONE         = "telephoneClient";
    public static final String CLIENT_URL_DELETE          = "/suppressionClient";
    public static final String CLIENT_URL_LIST            = "/listeClients";
    public static final String CLIENT_VIEW_LIST           = "/WEB-INF/listerClients.jsp";
    public static final String CLIENT_VIEW_CREATE         = "/WEB-INF/creerClient.jsp";
    public static final String CLIENT_VIEW_ONE            = "/WEB-INF/afficherClient.jsp";

    
    public static final String COMMANDE_CONTEXT_NAME_MAP      = "commandes";
    public static final String COMMANDE_CONTEXT_NAME_SINGLE   = "commande";
    public static final String COMMANDE_PARAM_CLIENTID        = "idClient";
    public static final String COMMANDE_PARAM_DATE            = "dateCommande";
	public static final String COMMANDE_PARAM_ID              = "idCommande";
    public static final String COMMANDE_PARAM_PAYMENT_MODE    = "modePaiementCommande";
    public static final String COMMANDE_PARAM_PAYMENT_STATUS  = "statutPaiementCommande";
    public static final String COMMANDE_PARAM_DELIVERY_MODE   = "modeLivraisonCommande";
    public static final String COMMANDE_PARAM_DELIVERY_STATUS = "statutLivraisonCommande";
    public static final String COMMANDE_URL_DELETE            = "/suppressionCommande";
    public static final String COMMANDE_URL_LIST              = "/listeCommandes";
    public static final String COMMANDE_VIEW_LIST             = "/WEB-INF/listerCommandes.jsp";
    public static final String COMMANDE_VIEW_CREATE           = "/WEB-INF/creerCommande.jsp";
    public static final String COMMANDE_VIEW_ONE              = "/WEB-INF/afficherCommande.jsp";
    
    
    public static final String DAO_CLASSNAME             = "jdbc:mysql://localhost:3306/tp_amazon?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DAO_USER           = "root";
    public static final String DAO_PASS              = "";
    public static final String DAO_NAME              = "com.mysql.cj.jdbc.Driver";

}
