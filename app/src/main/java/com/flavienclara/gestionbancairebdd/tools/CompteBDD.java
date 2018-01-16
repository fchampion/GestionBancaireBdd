package com.flavienclara.gestionbancairebdd.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flavienclara.gestionbancairebdd.Models.CompteADO;
import com.flavienclara.gestionbancairebdd.classes.Client;
import com.flavienclara.gestionbancairebdd.classes.Compte;

import java.util.ArrayList;

/**
 * Created by Clara on 15/01/2018.
 */

public class CompteBDD {
    private static final int VERSION = 1;
    private static final String NOM_BDD = "compte.db";
    private static final String TABLE_COMPTE = "table_compte";
    private static final String COL_ID_COMPTE = "ID_COMPTE";
    private static final int NUM_COL_ID_COMPTE = 0;
    private static final String COL_NUMERO = "NUMERO";
    private static final int NUM_COL_NUMERO = 1;
    private static final String COL_MONTANT_COMPTE = "MONTANT_COMPTE";
    private static final int NUM_COL_MONTANT_COMPTE = 2;
    private static final String COL_FK_ID_CLIENT = "FK_ID_CLIENT";
    private static final int NUM_COL_FK_ID_CLIENT = 3;
    private SQLiteDatabase bdd;
    private CompteADO comptes;

    public CompteBDD(Context context) {
        comptes = new CompteADO(context, NOM_BDD, null, VERSION);
    }

    //connexion pour ecrire
    public void openForWrite() {
        bdd = comptes.getWritableDatabase();
    }
    //connexion pour lire
    public void openForRead() {bdd = comptes.getReadableDatabase();}
    //pour fermer
    public void close() {
        bdd.close();
    }
    public SQLiteDatabase getBdd() {
        return bdd;
    }

    // pour inserer dans compte
    public long insertCompte(Compte comptes) {
        ContentValues content = new ContentValues();
        content.put(COL_NUMERO, comptes.getNum());
        content.put(COL_MONTANT_COMPTE, comptes.getMontant_compte());
        content.put(COL_FK_ID_CLIENT, comptes.getFk_id_client());
        return bdd.insert(TABLE_COMPTE, null, content);
    }

    //pour modifier dans compte
    public int updateCompte(int id, Compte comptes) {
        ContentValues content = new ContentValues();
        content.put(COL_NUMERO, comptes.getNum());
        content.put(COL_MONTANT_COMPTE, comptes.getMontant_compte());
        return bdd.update(TABLE_COMPTE, content, COL_ID_COMPTE + " = " + id, null);
    }
    public int removeCompte(String name) {
        return bdd.delete(TABLE_COMPTE, COL_NUMERO + " = " + name, null);
    }
    public Compte getCompte(String num) {
        Cursor c = bdd.query(TABLE_COMPTE, new String[] {
                        COL_ID_COMPTE, COL_NUMERO,COL_MONTANT_COMPTE, COL_FK_ID_CLIENT }, COL_NUMERO + " LIKE \"" + num + "\"", null, null, null, COL_NUMERO);
        return cursorToCompte(c);
    }
    public Compte cursorToCompte(Cursor c) {

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        Compte cpt = new Compte();
        cpt.setId(c.getInt(NUM_COL_ID_COMPTE));
        cpt.setNum(c.getInt(NUM_COL_NUMERO));
        cpt.setMontant_compte(c.getDouble(NUM_COL_MONTANT_COMPTE));
        c.close();
        return cpt;
    }
    //pour lister les comptes
    public ArrayList<Compte> getAllComptes() {
        Cursor c = bdd.query(TABLE_COMPTE, new String[] {
                COL_ID_COMPTE, COL_NUMERO,COL_MONTANT_COMPTE }, null, null, null, null, COL_NUMERO);
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        ArrayList<Compte> comptesList = new ArrayList<Compte> ();
        while (c.moveToNext()) {
            Compte cpt = new Compte();
            cpt.setId(c.getInt(NUM_COL_ID_COMPTE));
            cpt.setNum(c.getInt(NUM_COL_NUMERO));
            cpt.setMontant_compte(c.getDouble(NUM_COL_MONTANT_COMPTE));
            comptesList.add(cpt);
        }
        c.close();
        return comptesList;
    }

    //insert quand id_client = fk_id_client
    public long insertCompteClient(Compte comptes, Client c) {
        ContentValues content = new ContentValues();
        content.put(COL_NUMERO, comptes.getNum());
        content.put(COL_MONTANT_COMPTE, comptes.getMontant_compte());
        content.put(COL_FK_ID_CLIENT, comptes.getFk_id_client());
        return bdd.insert(TABLE_COMPTE, null, content);
    }
    //INSERT INTO Compte ..... where fk_id_client = Client.id;
}
