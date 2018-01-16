package com.flavienclara.gestionbancairebdd.classes;

/**
 * Created by Julian on 10/01/2018.
 */

public class Operation {
    private int id;
    private String libelle;
    private Double montant_operation;
    private int fk_id_compte;

    public Operation(){

    }

    public Operation(String libelle, Double montant_operation) {
        this.libelle = libelle;
        this.montant_operation = montant_operation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Double getMontant_operation() {return montant_operation;}

    public void setMontant_operation(Double montant_operation) {this.montant_operation = montant_operation;}

    public int getFk_id_compte() {return fk_id_compte;}

    public void setFk_id_compte(int fk_id_compte) {this.fk_id_compte = fk_id_compte;}
}
