package com.flavienclara.gestionbancairebdd.tools;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.flavienclara.gestionbancairebdd.R;
import com.flavienclara.gestionbancairebdd.activities.AjoutOperationActivity;
import com.flavienclara.gestionbancairebdd.activities.OperationActivity;
import com.flavienclara.gestionbancairebdd.classes.Operation;

/**
 * Created by Julian on 11/01/2018.
 */

public class RecyclerViewOperationAdapter extends RecyclerView.Adapter<RecyclerViewOperationAdapter.ViewHolder> {
    private int recyclerItemRes; //id ressource items du recyclerView
    private ArrayList<Operation> data; // les données à afficher
    private Context context; // le contexte de l’application

//Constructeur de notre classe
public RecyclerViewOperationAdapter(int recyclerItemRes, ArrayList<Operation> data, Context context) {
    this.recyclerItemRes = recyclerItemRes;
    this.data = data;
    this.context = context;
}

//Méthode à surcharger : affichage de la vue via la classe viewHolder
@Override
public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(recyclerItemRes, parent, false);
    final ViewHolder vh = new ViewHolder(view);
    view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Operation o = new Operation();
            o.setLibelle(vh.libelleOperation.getText().toString());
            o.setMontant_operation(Double.parseDouble(vh.montantOperation.getText().toString()));
            OperationActivity.operation = o;
            Intent i = new Intent(parent.getContext(), AjoutOperationActivity.class);
            parent.getContext().startActivity(i);

        }
    });
    return vh;
}

//Méthode à surcharger : mappage du nom affiché en fonction de la place de l’élément
@Override
public void onBindViewHolder(RecyclerViewOperationAdapter.ViewHolder holder, int position) {
    holder.libelleOperation.setText(data.get(position).getLibelle());
    holder.montantOperation.setText(data.get(position).getMontant_operation().toString());

}
//Méthode à surcharger : retourne la taille de la liste
    @Override
    public int getItemCount() {
    if(data!=null){
        return data.size();
    }
    return 0;
}
//Classe faisant le lien entre le composant de la vue et l’item
 public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView libelleOperation;
     private TextView montantOperation;

     public ViewHolder(View itemView) {
        super(itemView);
         libelleOperation = (TextView)itemView.findViewById(R.id.libelle_operation);
         montantOperation = (TextView)itemView.findViewById(R.id.libelle_montant);
    }
}
}
