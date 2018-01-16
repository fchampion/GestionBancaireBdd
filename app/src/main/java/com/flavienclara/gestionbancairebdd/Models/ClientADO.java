package com.flavienclara.gestionbancairebdd.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by flavien.champion on 12/01/2018.
 */


public class ClientADO extends SQLiteOpenHelper {

    public static final String TABLE_CLIENT="TABLE_CLIENT";
    public static final String COL_ID_CLIENT = "ID_CLIENT";
    public static final String COL_NOM = "NOM";
    public static final String COL_PRENOM = "PRENOM";
    public static final String COL_LOGIN = "LOGIN";
    public static final String COL_MDP = "MDP";


    private  final String CREATE_BDD = "CREATE TABLE " + TABLE_CLIENT + " ("
            + COL_ID_CLIENT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NOM + " TEXT NOT NULL, "
            +COL_PRENOM + " TEXT NOT NULL, "
            + COL_LOGIN + " TEXT NOT NULL, "
            + COL_MDP + " TEXT NOT NULL);";

    public ClientADO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super (context, name, factory, version);

    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Dans cette méthode, vous devez gérer les montées de version de votre base de données
        db.execSQL("DROP TABLE " + TABLE_CLIENT);
        onCreate(db);
    }
}

