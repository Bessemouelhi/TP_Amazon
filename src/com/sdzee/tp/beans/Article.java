package com.sdzee.tp.beans;

import java.util.UUID;

public class Article {
	private UUID id;
	private String nom;
	private String description;
	private Double prix;
	
	
	public Article() {
	}

	public Article(String nom, String description, Double prix) {
		this.id = UUID.randomUUID();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}

	public Article(UUID id, String nom, String description, Double prix) {
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getPrix() {
		return prix;
	}
	
	public void setPrix(Double prix) {
		this.prix = prix;
	}
}
