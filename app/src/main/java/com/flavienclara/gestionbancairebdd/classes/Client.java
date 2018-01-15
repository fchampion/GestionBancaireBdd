package com.flavienclara.gestionbancairebdd.classes;

import java.util.ArrayList;

/**
 * Created by Julian on 10/01/2018.
 */

public class Client {
    private String nom;
    private String prenom;
    private String login;
    private String mdp;
    private ArrayList<Compte> lesComptes;


    public Client(String nom, String prenom, String login, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.lesComptes = new ArrayList<>();
    }

    public ArrayList<Compte> getLesComptes() {
        return lesComptes;
    }

    public void setLesComptes(ArrayList<Compte> lesComptes) {
        this.lesComptes = lesComptes;
    }

    public Client() {
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
