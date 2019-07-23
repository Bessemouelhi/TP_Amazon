package com.sdzee.tp.beans;

import java.util.UUID;

public class Client {
	private UUID id;
    private String nom;
    private String prenom;
    private String password;
    private String telephone;
    private String email;

    
	public Client() {

	}

    public Client(String nom, String prenom, String password, String telephone, String email) {
    	this.id = UUID.randomUUID();
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.telephone = telephone;
		this.email = email;
	}

    public Client(UUID id, String nom, String prenom, String password, String telephone, String email) {
    	this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.telephone = telephone;
		this.email = email;
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

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone( String telephone ) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }
}
