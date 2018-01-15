package com.flavienclara.gestionbancairebdd.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flavienclara.gestionbancairebdd.Models.CompteADO;
import com.flavienclara.gestionbancairebdd.classes.Compte;

import java.util.ArrayList;

/**
 * Created by Clara on 15/01/2018.
 */

public class CompteBDD {
    private static final int VERSION = 1;
    private static final String NOM_BDD = "compte.db";
    private static final String TABLE_COMPTE = "table_compte";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NUMERO = "NUMERO";
    private static final int NUM_COL_NUMERO = 1;
    private static final String COL_MONTANT= "MONTANT";
    private static final int NUM_COL_MONTANT = 2;
    private SQLiteDatabase bdd;
    private CompteADO comptes;

    public CompteBDD(Context context) {
        comptes = new CompteADO(context, NOM_BDD, null, VERSION);
    }

    public void openForWrite() {
        bdd = comptes.getWritableDatabase();
    }
    public void openForRead() {

        bdd = comptes.getReadableDatabase();
    }
    public void close() {
        bdd.close();
    }
    public SQLiteDatabase getBdd() {
        return bdd;
    }
    public long insertCompte(Compte comptes) {
        ContentValues content = new ContentValues();
        content.put(COL_NUMERO, comptes.getNum());
        content.put(COL_MONTANT, comptes.getMontant());
        return bdd.insert(TABLE_COMPTE, null, content);
    }
    public int updateCompte(int id, Compte comptes) {
        ContentValues content = new ContentValues();
        content.put(COL_NUMERO, comptes.getNum());
        content.put(COL_MONTANT, comptes.getMontant());
        return bdd.update(TABLE_COMPTE, content, COL_ID + " = " + id, null);
    }
    public int removeCompte(String name) {
        return bdd.delete(TABLE_COMPTE, COL_NUMERO + " = " + name, null);
    }
    public Compte getCompte(String num) {
        Cursor c = bdd.query(TABLE_COMPTE, new String[] {
                        COL_ID, COL_NUMERO,COL_MONTANT }, COL_NUMERO + " LIKE \"" + num + "\"", null, null, null, COL_NUMERO);
        return cursorToCompte(c);
    }
    public Compte cursorToCompte(Cursor c) {

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        Compte cpt = new Compte();
        cpt.setId(c.getInt(NUM_COL_ID));
        cpt.setNum(c.getInt(NUM_COL_NUMERO));
        cpt.setMontant(c.getDouble(NUM_COL_MONTANT));
        c.close();
        return cpt;
    }
    public ArrayList<Compte> getAllComptes() {
        Cursor c = bdd.query(TABLE_COMPTE, new String[] {
                COL_ID, COL_NUMERO,COL_MONTANT }, null, null, null, null, COL_NUMERO);
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        ArrayList<Compte> comptesList = new ArrayList<Compte> ();
        while (c.moveToNext()) {
            Compte cpt = new Compte();
            cpt.setId(c.getInt(NUM_COL_ID));
            cpt.setNum(c.getInt(NUM_COL_NUMERO));
            cpt.setMontant(c.getDouble(NUM_COL_MONTANT));
            comptesList.add(cpt);
        }
        c.close();
        return comptesList;
    }
}