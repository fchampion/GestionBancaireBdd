package com.flavienclara.gestionbancairebdd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import com.flavienclara.gestionbancairebdd.R;
import com.flavienclara.gestionbancairebdd.classes.Client;
import com.flavienclara.gestionbancairebdd.classes.Compte;
import com.flavienclara.gestionbancairebdd.classes.Operation;
import com.flavienclara.gestionbancairebdd.tools.ClientBDD;
import com.flavienclara.gestionbancairebdd.tools.CompteBDD;

public class LoginActivity extends AppCompatActivity {
    public ArrayList<Client> lesClients;
    public ArrayList<Compte> lesComptes;
    public static Client connected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(connected != null){
            setContentView(R.layout.activity_logout);
            Button btnLogout = (Button) findViewById(R.id.btn_logout);
            TextView labelProfil = (TextView) findViewById(R.id.label_profil);
            labelProfil.setText(connected.getLogin() + " Voulez-vous vous déconnecter ?");
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    connected = null;
                    Intent i = new Intent(LoginActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            });
        }else {
            setContentView(R.layout.activity_login);
            if (this.connected != null) {
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                if (toolbar != null) {
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                }
            }

            ClientBDD clientBdd = new ClientBDD(this);
            clientBdd.openForWrite();
            Client c1 = new Client("Dupond", "Dupont", "d", "d");
            Client c2 = new Client("Laela", "Lola", "llaela", "ll39");


            Compte cpte1 = new Compte(145, 1010.58,1);
            Compte cpte2 = new Compte(146, 489.04,1);
            Compte cpte3 = new Compte(147, 10.58,2);
            Compte cpte4 = new Compte(148, 48.04,2);


            Operation op1 = new Operation("telephone", -614.00);
            Operation op2 = new Operation("bar", -17.80);
            Operation op3 = new Operation("cine", -14.00);
            Operation op4 = new Operation("piscine", -9.00);
            Operation op5 = new Operation("essence", -54.89);
            Operation op6 = new Operation("loyer", -450.99);
            Operation op7 = new Operation("edf", -84.00);
            Operation op8 = new Operation("eau", -9.07);


            CompteBDD compteBdd = new CompteBDD(this);
            compteBdd.openForWrite();

            /*cpte1.getLesOperations().add(op1);
            cpte1.getLesOperations().add(op3);

            cpte2.getLesOperations().add(op5);
            cpte2.getLesOperations().add(op8);

            cpte3.getLesOperations().add(op2);
            cpte3.getLesOperations().add(op4);

            cpte4.getLesOperations().add(op6);
            cpte4.getLesOperations().add(op7);*/



            //GOOD
            compteBdd.insertCompte(cpte1);
            compteBdd.insertCompte(cpte2);
            compteBdd.insertCompte(cpte3);
            compteBdd.insertCompte(cpte4);

            lesComptes = compteBdd.getAllComptes();
            compteBdd.close();

            //c1.getLesComptes().add(cpte1);
            //c1.getLesComptes().add(cpte2);
            //c2.getLesComptes().add(cpte3);
            //c2.getLesComptes().add(cpte4);


            clientBdd.insertClient(c1);
            clientBdd.insertClient(c2);
            lesClients = clientBdd.getAllClients();
            clientBdd.close();


            final EditText login = (EditText) findViewById(R.id.input_login);
            final EditText password = (EditText) findViewById(R.id.input_password);
            final Button btnConnexion = (Button) findViewById(R.id.btn_ajouter);
            final TextView error = (TextView) findViewById(R.id.error_auth);

            btnConnexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    error.setText("");
                    if (login.getText().toString().equals("") || password.getText().toString().equals("")) {
                        error.setText("Veuillez remplir tous les champs.");
                    } else {
                        for (Client c : lesClients) {
                            if (c.getLogin().equals(login.getText().toString()) && c.getMdp().equals(password.getText().toString())) {
                                setClient(c);
                                Intent i = new Intent(LoginActivity.this, CompteActivity.class);
                                startActivity(i);
                                return;
                            } else {
                                error.setText("Login ou mot de passe incorrect.");
                            }
                        }
                    }
                }
            });

        }
    }

    //appel de la toolbar
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_item, menu);
        return true;
    }

    //actions sur éléments toolbar
    public boolean onOptionsItemSelected(MenuItem item){
            int id = item.getItemId();
            Intent intent;
            switch (id){
                case R.id.item_login:
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    return  true;
                case R.id.item_comptes:
                    intent = new Intent(getApplicationContext(), CompteActivity.class);
                    startActivity(intent);
                    return  true;
                case R.id.item_operations:
                    Log.i("item","clic item opération");
                    return  true;
            }
        return super.onOptionsItemSelected(item);
    }

    public void setClient(Client cl) {
        this.connected = cl;
    }
}
