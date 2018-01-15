package com.flavienclara.gestionbancairebdd.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Clara on 15/01/2018.
 */

public class CompteADO extends SQLiteOpenHelper {

    private static final String TABLE_COMPTE = "table_compte";

    private static final String CLIENT_TABLE = "TABLE_CLIENT";

    private static final String COL_ID = "ID";
    private static final String COL_NUMERO = "NUMERO";
    private static final String COL_MONTANT = "MONTANT";
    private static final String COL_FK_ID_CLIENT = "FK_ID_CLIENT";
    private static final String TABLE_CLIENT = "TABLE_CLIENT";

    private static final String CREATE_BDD = "CREATE TABLE "
            + TABLE_COMPTE + " (" + COL_ID + " INTEGER PRIMARY KEYAUTOINCREMENT, "
            + COL_NUMERO + " TEXT NOT NULL, " + COL_MONTANT + "TEXT NOT NULL, "
            + "FOREIGN KEY (" + COL_FK_ID_CLIENT + ") REFERENCES " + TABLE_CLIENT + "(COL_ID)"+");";

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
