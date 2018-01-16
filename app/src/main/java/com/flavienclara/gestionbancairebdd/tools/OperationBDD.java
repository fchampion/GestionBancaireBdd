package com.flavienclara.gestionbancairebdd.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flavienclara.gestionbancairebdd.Models.BanqueADO;
import com.flavienclara.gestionbancairebdd.classes.Client;
import com.flavienclara.gestionbancairebdd.classes.Operation;

import java.util.ArrayList;

/**
 * Created by flavien.champion on 16/01/2018.
 */

public class OperationBDD {
    private static final int VERSION = 1;
    private static final String NOM_BDD = "Banque.db";
    private static final String TABLE_OPERATION = "TABLE_OPERATION";
    private static final String COL_ID_OPERATION = "ID_OPERATION";
    private static final int NUM_COL_ID_OPERATION = 0;
    private static final String COL_LIBELLE = "LIBELLE";
    private static final int NUM_COL_LIBELLE = 1;
    private static final String COL_MONTANT_OPERATION= "MONTANT_OPERATION";
    private static final int NUM_COL_MONTANT_OPERATION = 2;
    private static final String COL_FK_ID_COMPTE = "FK_ID_COMPTE";
    private static final int NUM_COL_FK_ID_COMPTE = 3;
    private SQLiteDatabase bdd;
    private BanqueADO operations;

    public OperationBDD(Context context) {
        operations = new BanqueADO(context, NOM_BDD, null, VERSION);
    }

    //connexion pour ecrire
    public void openForWrite() {
        bdd = operations.getWritableDatabase();
    }
    //connexion pour lire
    public void openForRead() {bdd = operations.getReadableDatabase();}
    //pour fermer
    public void close() {
        bdd.close();
    }
    public SQLiteDatabase getBdd() {
        return bdd;
    }

    // pour inserer dans operation
    public long insertOperation(Operation operations) {
        ContentValues content = new ContentValues();
        content.put(COL_LIBELLE, operations.getLibelle());
        content.put(COL_MONTANT_OPERATION, operations.getMontant_operation());
        content.put(COL_FK_ID_COMPTE, operations.getFk_id_compte());
        return bdd.insert(TABLE_OPERATION, null, content);
    }

    //pour modifier dans operation
    public int updateOperation(int id, Operation operations) {
        ContentValues content = new ContentValues();
        content.put(COL_LIBELLE, operations.getLibelle());
        content.put(COL_MONTANT_OPERATION, operations.getMontant_operation());
        return bdd.update(TABLE_OPERATION, content, COL_ID_OPERATION + " = " + id, null);
    }
    public int removeOperation(String name) {
        return bdd.delete(TABLE_OPERATION, COL_LIBELLE + " = " + name, null);
    }
    public Operation getOperation(String num) {
        Cursor c = bdd.query(TABLE_OPERATION, new String[] {
                COL_ID_OPERATION, COL_LIBELLE,COL_MONTANT_OPERATION, COL_FK_ID_COMPTE }, COL_LIBELLE + " LIKE \"" + num + "\"", null, null, null, COL_LIBELLE);
        return cursorToOperation(c);
    }
    public Operation cursorToOperation(Cursor c) {

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        Operation cpt = new Operation();
        cpt.setId(c.getInt(NUM_COL_ID_OPERATION));
        cpt.setLibelle(c.getString(NUM_COL_LIBELLE));
        cpt.setMontant_operation(c.getDouble(NUM_COL_MONTANT_OPERATION));
        c.close();
        return cpt;
    }
    //pour lister les operations
    public ArrayList<Operation> getAllOperations() {
        Cursor c = bdd.query(TABLE_OPERATION, new String[] {
                COL_ID_OPERATION, COL_LIBELLE,COL_MONTANT_OPERATION }, null, null, null, null, COL_LIBELLE);
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        ArrayList<Operation> operationsList = new ArrayList<Operation> ();
        while (c.moveToNext()) {
            Operation cpt = new Operation();
            cpt.setId(c.getInt(NUM_COL_ID_OPERATION));
            cpt.setLibelle(c.getString(NUM_COL_LIBELLE));
            cpt.setMontant_operation(c.getDouble(NUM_COL_MONTANT_OPERATION));
            operationsList.add(cpt);
        }
        c.close();
        return operationsList;
    }

    //insert quand id_client = fk_id_client
    public long insertOperationClient(Operation operations, Client c) {
        ContentValues content = new ContentValues();
        content.put(COL_LIBELLE, operations.getLibelle());
        content.put(COL_MONTANT_OPERATION, operations.getMontant_operation());
        content.put(COL_FK_ID_COMPTE, operations.getFk_id_compte());
        return bdd.insert(TABLE_OPERATION, null, content);
    }
    //INSERT INTO Operation ..... where fk_id_client = Client.id;
}
