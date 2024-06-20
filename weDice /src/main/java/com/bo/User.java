package com.bo;


public class User {


	private String nom;

	private String prenom;

	private String login;

	private String password;

	private double score;

	private double bestScore;

	private int compteurLancer;

	public User() {
	}


	public User(String nom, String prenom, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.score = 0;
        this.bestScore = 0;
        this.compteurLancer = 0;
        System.out.println("User constructor with nom, prenom, login, password called");
    }
    
	public User(String nom, String prenom, String login, String password, double score, double bestScore) {
	    this.nom = nom;
	    this.prenom = prenom;
	    this.login = login;
	    this.password = password;
	    this.score = score;
	    this.bestScore = bestScore;
	    System.out.println("User constructor with nom, prenom, login, password, score, bestScore called");
	}
	




	@Override
	public String toString() {
		return "Utilisateur [ nom=" + nom + ", prenom=" + prenom + ", login="
				+ login + ", password=" + password + ", score=" + score + ", bestScore=" + bestScore
				+ ", compteurLancer=" + compteurLancer + "]";
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {

		this.score = score;
	}

	public double getBestScore() {
		return bestScore;
	}

	public void setBestScore(double bestScore) {
		this.bestScore = bestScore;
	}

	public int getCompteurLancer() {
		return compteurLancer;
	}

	public void setCompteurLancer(int compteurLancer) {
		this.compteurLancer = compteurLancer;
	}

	public void incrementLance() {
		this.compteurLancer++;
	}

}
