package com.flavienclara.gestionbancairebdd.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.flavienclara.gestionbancairebdd.R;
import com.flavienclara.gestionbancairebdd.classes.Operation;
import com.flavienclara.gestionbancairebdd.tools.ActivityWithToolbar;
import com.flavienclara.gestionbancairebdd.tools.RecyclerViewOperationAdapter;

/**
 * Created by Julian on 11/01/2018.
 */

public class OperationActivity extends ActivityWithToolbar{
        public static Operation operation;
        @SuppressLint("MissingSuperCall")
        @Override
        protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState, (int) R.layout.activity_operation);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_operation);
        recyclerView.setHasFixedSize(true);

        final Button btnAjouter = (Button) findViewById(R.id.btn_ajouter_operation);

        if(CompteActivity.selectedCompte != null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            RecyclerViewOperationAdapter adapter = new RecyclerViewOperationAdapter(R.layout.recycler_view_item_operation, CompteActivity.selectedCompte.getLesOperations(), this);
            recyclerView.setAdapter(adapter);
        }

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperationActivity.operation = null;
                Intent i = new Intent(getApplicationContext(), AjoutOperationActivity.class);
                startActivity(i);


            }
        });
    }


}
