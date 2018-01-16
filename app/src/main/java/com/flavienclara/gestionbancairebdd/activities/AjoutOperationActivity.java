package com.flavienclara.gestionbancairebdd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.flavienclara.gestionbancairebdd.R;
import com.flavienclara.gestionbancairebdd.classes.Operation;

/**
 * Created by Julian on 11/01/2018.
 */

public class AjoutOperationActivity extends AppCompatActivity{
    private Button btnValiderOperation;
    private Button btnAnnulerOperation;
    private Button btnSupprimerOperation;
    private EditText inputLibelleOperation;
    private EditText inputMontantOperation;
    private TextView labelError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_operation);

        btnValiderOperation = (Button) findViewById(R.id.btn_valider_operation);
        btnAnnulerOperation = (Button) findViewById(R.id.btn_annuler_operation);
        btnSupprimerOperation = (Button) findViewById(R.id.btn_supprimer_operation);
        btnSupprimerOperation.setVisibility(View.INVISIBLE);
        inputLibelleOperation = (EditText) findViewById(R.id.input_libelle_operation);
        inputMontantOperation = (EditText) findViewById(R.id.input_montant_operation);
        labelError = (TextView) findViewById(R.id.label_error);
        btnAnnulerOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OperationActivity.class);
                startActivity(intent);
            }
        });
        btnValiderOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputLibelleOperation.getText().toString().isEmpty()
                        || inputMontantOperation.getText().toString().isEmpty())
                    labelError.setText("Veuillez remplir tous les champs");
                else if(OperationActivity.operation != null)
                {
                    int position = -1;
                    for(int i = 0; i < CompteActivity.selectedCompte.getLesOperations().size(); i++){
                        if(
                                CompteActivity
                                        .selectedCompte
                                        .getLesOperations()
                                        .get(i)
                                        .getLibelle()
                                        .equals(OperationActivity.operation.getLibelle()))
                            position = i;
                    }
                    if(position != -1) {
                        CompteActivity.selectedCompte.desoperate(
                                CompteActivity.selectedCompte.getLesOperations().get(position));
                        CompteActivity.selectedCompte.desoperate(OperationActivity.operation);
                        CompteActivity.selectedCompte.operate(
                                new Operation(
                                        inputLibelleOperation.getText().toString(),
                                        Double.valueOf(inputMontantOperation.getText().toString())));
                        Intent intent = new Intent(getApplicationContext(), OperationActivity.class);
                        startActivity(intent);
                    }

                }
                else if (
                        inputLibelleOperation.getText().toString().isEmpty() ||
                                inputMontantOperation.getText().toString().isEmpty()) {
                    labelError.setText("remplissez les champs !");
                } else {
                    CompteActivity.selectedCompte.operate(
                            new Operation(
                                inputLibelleOperation.getText().toString(),
                                Double.valueOf(inputMontantOperation.getText().toString())));
                    Intent i = new Intent(getApplicationContext(), OperationActivity.class);
                    startActivity(i);
                }

            }
        });

        if(OperationActivity.operation != null)
        {
            inputLibelleOperation.setText(OperationActivity.operation.getLibelle());
            inputMontantOperation.setText(OperationActivity.operation.getMontant_operation().toString());
            btnSupprimerOperation.setVisibility(View.VISIBLE);
            btnSupprimerOperation.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = -1;
                    for(int i=0; i<CompteActivity.selectedCompte.getLesOperations().size(); i++){
                        if(CompteActivity.selectedCompte.getLesOperations().get(i).getLibelle().equals(OperationActivity.operation.getLibelle())){
                            position = i;
                            break;
                        }
                    }
                    if(position != -1){
                        CompteActivity.selectedCompte.desoperate(
                                CompteActivity.selectedCompte.getLesOperations().get(position));
                        Intent intent = new Intent(getApplicationContext(), OperationActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}