package com.flavienclara.gestionbancairebdd.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.flavienclara.gestionbancairebdd.R;
import com.flavienclara.gestionbancairebdd.classes.Compte;
import com.flavienclara.gestionbancairebdd.tools.ActivityWithToolbar;

/**
 * Created by Julian on 10/01/2018.
 */

public class CompteActivity extends ActivityWithToolbar {

    public static Compte selectedCompte;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, (int) R.layout.activity_compte);

        if (LoginActivity.connected == null) {
            Intent i = new Intent(CompteActivity.this, LoginActivity.class);
            startActivity(i);
        } else {
            String[] arrayCompte = new String[LoginActivity.connected.getLesComptes().size()];
            for (int i = 0; i < LoginActivity.connected.getLesComptes().size(); i++) {
                arrayCompte[i] = "Détails compte : " + String.valueOf(LoginActivity.connected.getLesComptes().get(i).getNum()) + "      Solde : " + String.valueOf(LoginActivity.connected.getLesComptes().get(i).getMontant());
            }
            ListView listComptes = (ListView) findViewById(R.id.list_comptes);
            ArrayAdapter adapter = new ArrayAdapter(CompteActivity.this, android.R.layout.simple_list_item_1, arrayCompte);
            listComptes.setAdapter(adapter);

            listComptes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // Récupère l'élément de la liste
                    selectedCompte = LoginActivity.connected.getLesComptes().get(i);

                    Intent intent = new Intent(getApplicationContext(), OperationActivity.class);
                    startActivity(intent);
                }
            });
        }

    }


}
