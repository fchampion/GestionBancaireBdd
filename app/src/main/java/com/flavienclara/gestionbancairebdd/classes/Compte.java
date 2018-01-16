package com.flavienclara.gestionbancairebdd.classes;

import java.util.ArrayList;

/**
 * Created by Julian on 10/01/2018.
 */

public class Compte {

    private int id;
    private int num;
    private double montant_compte;
    private int fk_id_client;
    private ArrayList<Operation> lesOperations;


    public Compte(int num, double montant_compte, int fk_id_client) {
        this.num = num;
        this.montant_compte = montant_compte;
        this.fk_id_client = fk_id_client;
        this.lesOperations = new ArrayList<>();

    }

    public Compte() {
    }

    public ArrayList<Operation> getLesOperations() {
        return lesOperations;
    }

    public void setLesOperations(ArrayList<Operation> lesOperations) {
        this.lesOperations = lesOperations;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getMontant_compte() {return montant_compte;}

    public void setMontant_compte(double montant_compte) {this.montant_compte = montant_compte;}

    public int getId() {return id;}

    public void setId(int Id) {this.id = Id;}

    public int getFk_id_client() {return fk_id_client;}

    public void setFk_id_client(int fk_id_client) {this.fk_id_client = fk_id_client;}

    public void operate(Operation operation){
        montant_compte += operation.getMontant_operation();
        lesOperations.add(operation);
    }

    public void desoperate(Operation operation){
        montant_compte -= operation.getMontant_operation();
        lesOperations.remove(operation);
    }
}
