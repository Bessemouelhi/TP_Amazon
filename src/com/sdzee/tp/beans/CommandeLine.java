package com.sdzee.tp.beans;

import java.util.Date;
import java.util.UUID;

public class CommandeLine {
	
	private UUID idCommande;
    private UUID idArticle;
    private int quantite;
    
	public UUID getIdCommande() {
		return idCommande;
	}
	
	public void setIdCommande(UUID idCommande) {
		this.idCommande = idCommande;
	}
	
	public UUID getIdArticle() {
		return idArticle;
	}
	
	public void setIdArticle(UUID idArticle) {
		this.idArticle = idArticle;
	}
	
	public int getQuantite() {
		return quantite;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
