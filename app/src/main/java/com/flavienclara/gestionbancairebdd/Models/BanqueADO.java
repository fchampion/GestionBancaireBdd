package com.flavienclara.gestionbancairebdd.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by flavien.champion on 16/01/2018.
 */

public class BanqueADO extends SQLiteOpenHelper {

    //TABLE CLIENT
    public static final String TABLE_CLIENT="TABLE_CLIENT";
    public static final String COL_ID_CLIENT = "ID_CLIENT";
    public static final String COL_NOM = "NOM";
    public static final String COL_PRENOM = "PRENOM";
    public static final String COL_LOGIN = "LOGIN";
    public static final String COL_MDP = "MDP";

    //TABLE COMPTE
    private static final String TABLE_COMPTE = "TABLE_COMPTE";
    private static final String COL_ID_COMPTE = "ID_COMPTE";
    private static final String COL_NUMERO = "NUMERO";
    private static final String COL_MONTANT_COMPTE = "MONTANT_COMPTE";
    private static final String COL_FK_ID_CLIENT = "FK_ID_CLIENT";

    //TABLE OPERATIONS
    private static final String TABLE_OPERATION= "TABLE_OPERATION";
    private static final String COL_ID_OPERATION= "ID_COMPTE";
    private static final String COL_LIBELLE = "LIBELLE";
    private static final String COL_MONTANT_OPERATION= "MONTANT_COMPTE";
    private static final String COL_FK_ID_COMPTE = "FK_ID_COMPTE";



    private static final String CREATE_BDD_CLIENT = "CREATE TABLE " + TABLE_CLIENT + " ("
            + COL_ID_CLIENT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NOM + " TEXT NOT NULL, "
            +COL_PRENOM + " TEXT NOT NULL, "
            + COL_LOGIN + " TEXT NOT NULL, "
            + COL_MDP + " TEXT NOT NULL);";


    private static final String CREATE_BDD_COMPTES= "CREATE TABLE " + TABLE_COMPTE + " ("
            + COL_ID_COMPTE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NUMERO + " TEXT NOT NULL, "
            + COL_MONTANT_COMPTE + " TEXT NOT NULL, "
            + COL_FK_ID_CLIENT + " integer,"
            + "FOREIGN KEY (" + COL_FK_ID_CLIENT + ") REFERENCES " + TABLE_CLIENT + "(ID_CLIENT)"+");";

    private static final String CREATE_BDD_OPERATION = "CREATE TABLE " + TABLE_OPERATION+ " ("
            + COL_ID_OPERATION+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_LIBELLE + " TEXT NOT NULL, "
            + COL_MONTANT_OPERATION+ " TEXT NOT NULL, "
            + COL_FK_ID_COMPTE + " integer,"
            + "FOREIGN KEY (" + COL_FK_ID_COMPTE + ") REFERENCES " + TABLE_COMPTE + "(ID_COMPTE)"+");";

    public BanqueADO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super (context, name, factory, version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD_CLIENT);
        db.execSQL(CREATE_BDD_COMPTES);
        db.execSQL(CREATE_BDD_OPERATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Dans cette méthode, vous devez gérer les montées de version de votre base de données
        db.execSQL("DROP TABLE " + TABLE_CLIENT+";");
        db.execSQL("DROP TABLE " + TABLE_COMPTE+";");
        db.execSQL("DROP TABLE " + TABLE_OPERATION+";");
        onCreate(db);
    }
}
