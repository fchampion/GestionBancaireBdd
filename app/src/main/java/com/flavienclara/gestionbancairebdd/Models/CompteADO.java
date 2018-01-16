package com.flavienclara.gestionbancairebdd.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Clara on 15/01/2018.
 */

public class CompteADO extends SQLiteOpenHelper {

    private static final String TABLE_COMPTE = "TABLE_COMPTE";

    private static final String COL_ID_COMPTE = "ID_COMPTE";
    private static final String COL_NUMERO = "NUMERO";
    private static final String COL_MONTANT_COMPTE = "MONTANT_COMPTE";
    private static final String COL_FK_ID_CLIENT = "FK_ID_CLIENT";
    private static final String TABLE_CLIENT = "TABLE_CLIENT";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_COMPTE + " ("
            + COL_ID_COMPTE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NUMERO + " TEXT NOT NULL, "
            + COL_MONTANT_COMPTE + " TEXT NOT NULL, "
            + "FOREIGN KEY (" + COL_FK_ID_CLIENT + ") REFERENCES " + TABLE_CLIENT + "(ID_CLIENT)"+");";

    public CompteADO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super (context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Dans cette méthode, vous devez gérer les montées de version de votre base de données
        db.execSQL("DROP TABLE " + TABLE_COMPTE);
        onCreate(db);
    }
}
