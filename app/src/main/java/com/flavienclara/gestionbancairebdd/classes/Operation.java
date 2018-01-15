package com.flavienclara.gestionbancairebdd.classes;

/**
 * Created by Julian on 10/01/2018.
 */

public class Operation {
    private String libelle;
    private Double montant;

    public Operation(){

    }

    public Operation(String libelle, Double montant) {
        this.libelle = libelle;
        this.montant = montant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
}
