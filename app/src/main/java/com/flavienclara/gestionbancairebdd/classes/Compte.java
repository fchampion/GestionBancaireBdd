package com.flavienclara.gestionbancairebdd.classes;

import java.util.ArrayList;

/**
 * Created by Julian on 10/01/2018.
 */

public class Compte {

    private int Id;
    private int num;
    private double montant;
    private ArrayList<Operation> lesOperations;


    public Compte(int num, double montant) {
        this.num = num;
        this.montant = montant;
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

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getId() {return Id;}

    public void setId(int Id) {this.Id = Id;}

    public void operate(Operation operation){
        montant += operation.getMontant();
        lesOperations.add(operation);
    }

    public void desoperate(Operation operation){
        montant -= operation.getMontant();
        lesOperations.remove(operation);
    }
}
