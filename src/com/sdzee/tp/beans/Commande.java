package com.sdzee.tp.beans;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Commande {

	private UUID id;
    private UUID clientId;
    private Date date;
    private String modePaiement;
    private String statutPaiement;
    private String modeLivraison;
    private String statutLivraison;
    private Map<UUID, Integer> articles;

    
    public Commande() {
    	
    }
    
    public Commande(UUID clientId, Date date, String modePaiement, String statutPaiement, String modeLivraison,
			String statutLivraison, Map<UUID, Integer> articles) {
		this.id = UUID.randomUUID();
		this.clientId = clientId;
		this.date = date;
		this.modePaiement = modePaiement;
		this.statutPaiement = statutPaiement;
		this.modeLivraison = modeLivraison;
		this.statutLivraison = statutLivraison;
		this.articles = articles;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getClientId() {
        return clientId;
    }

    public void setClientId( UUID clientId ) {
        this.clientId = clientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public Map<UUID, Integer> getArticles() {
		return articles;
	}

	public void setArticles(Map<UUID, Integer> articles) {
		this.articles = articles;
	}

	public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement( String modePaiement ) {
        this.modePaiement = modePaiement;
    }

    public String getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement( String statutPaiement ) {
        this.statutPaiement = statutPaiement;
    }

    public String getModeLivraison() {
        return modeLivraison;
    }

    public void setModeLivraison( String modeLivraison ) {
        this.modeLivraison = modeLivraison;
    }

    public String getStatutLivraison() {
        return statutLivraison;
    }

    public void setStatutLivraison( String statutLivraison ) {
        this.statutLivraison = statutLivraison;
    }
}
