package com.flavienclara.gestionbancairebdd.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.flavienclara.gestionbancairebdd.classes.Client;
import com.flavienclara.gestionbancairebdd.Models.ClientADO;

/**
 * Created by flavien.champion on 12/01/2018.
 */

public class ClientBDD {

    private static final int VERSION = 1;
    private static final String NOM_BDD = "client.db";
    private static final String TABLE_CLIENT="table_client";
    private static final int NUM_COL_NOM  = 0;
    private static final String COL_NOM = "nom";
    private static final int NUM_COL_PRENOM = 1;
    private static final String COL_PRENOM = "prenom";
    private static final int NUM_COL_LOGIN = 2;
    private static final String COL_LOGIN = "login";
    private static final int NUM_COL_MDP = 3;
    private static final String COL_MDP = "mdp";

    private SQLiteDatabase bdd;
    private ClientADO Clients;


    public ClientBDD(Context context) {
        Clients = new ClientADO(context, NOM_BDD, null, VERSION);
    }

    public void openForWrite() {
        bdd = Clients.getWritableDatabase();
    }
    public void openForRead() {
        bdd = Clients.getReadableDatabase();
    }

    public void close() { bdd.close(); }

    public SQLiteDatabase getBdd() { return bdd; }
    public long insertChapter(Client client) {
        ContentValues content = new ContentValues();
        content.put(COL_NOM, client.getNom());
        content.put(COL_PRENOM, client.getPrenom());
        content.put(COL_LOGIN, client.getLogin());
        content.put(COL_MDP, client.getMdp());
        return bdd.insert(TABLE_CLIENT, null, content);
    }

    public int updateClient(String name, Client client) {
        ContentValues content = new ContentValues();
        content.put(COL_NOM, client.getNom());
        content.put(COL_PRENOM, client.getPrenom());
        content.put(COL_LOGIN, client.getLogin());
        content.put(COL_MDP, client.getMdp());
        return bdd.update(TABLE_CLIENT, content, COL_NOM + " = " + name, null);
    }

    public int removeClient(String name) {
        return bdd.delete(TABLE_CLIENT, COL_NOM + " = " + name, null);
    }

    public Client getClient(String name) {
        Cursor c = bdd.query(TABLE_CLIENT, new String[]
                {COL_NOM, COL_PRENOM, COL_LOGIN, COL_MDP }, COL_NOM + " LIKE \"" + name + "\"", null, null, null, COL_NOM);
        return cursorToClient(c);
    }

    public Client cursorToClient(Cursor c) {

        if (c.getCount() == 0) {
            c.close(); return null;
        }
        Client client = new Client();
        client.setNom(c.getString(NUM_COL_NOM ));
        client.setPrenom(c.getString(NUM_COL_PRENOM ));
        client.setLogin(c.getString(NUM_COL_LOGIN ));
        client.setPrenom(c.getString(NUM_COL_MDP ));
        c.close();
        return client;
    }

    public ArrayList<Client> getAllClients() {
        Cursor c = bdd.query(TABLE_CLIENT, new String[]
                {COL_NOM, COL_PRENOM, COL_LOGIN, COL_MDP  }, null, null, null, null, COL_NOM);
        if (c.getCount() == 0) {
            c.close(); return null;
        }
        ArrayList<Client> clientList = new ArrayList<Client> ();
        while (c.moveToNext()) {
            Client client = new Client();
            client.setNom(c.getString(NUM_COL_NOM ));
            client.setPrenom(c.getString(NUM_COL_PRENOM ));
            client.setLogin(c.getString(NUM_COL_LOGIN ));
            client.setPrenom(c.getString(NUM_COL_MDP ));
            clientList.add(client);
        }
        c.close();
        return clientList;
    }
}

