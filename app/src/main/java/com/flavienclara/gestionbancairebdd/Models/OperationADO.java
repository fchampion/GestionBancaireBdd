package com.flavienclara.gestionbancairebdd.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Clara on 15/01/2018.
 */

public class OperationADO extends SQLiteOpenHelper {

    private static final String TABLE_OPERATION= "TABLE_OPERATION";

    private static final String COL_ID_OPERATION= "ID_COMPTE";
    private static final String COL_LIBELLE = "LIBELLE";
    private static final String COL_MONTANT_OPERATION= "MONTANT_COMPTE";
    private static final String COL_FK_ID_COMPTE = "FK_ID_COMPTE";
    private static final String TABLE_COMPTE = "TABLE_COMPTE";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_OPERATION+ " ("
            + COL_ID_OPERATION+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_LIBELLE + " TEXT NOT NULL, "
            + COL_MONTANT_OPERATION+ " TEXT NOT NULL, "
            + "FOREIGN KEY (" + COL_FK_ID_COMPTE + ") REFERENCES " + TABLE_COMPTE + "(ID_COMPTE)"+");";

    public OperationADO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super (context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Dans cette méthode, vous devez gérer les montées de version de votre base de données
        db.execSQL("DROP TABLE " + TABLE_OPERATION);
        onCreate(db);
    }
}
