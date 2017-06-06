package com.lab1.thais.gestion_inventaire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Thais on 2017-06-05.
 */

public class ProduitAdapter extends ArrayAdapter<Produit> {

    private final Context context;
    private final ArrayList<Produit> elements;

    public ProduitAdapter(Context context, ArrayList<Produit> elements){

        super(context, R.layout.line, elements);
        this.context = context;
        this.elements = elements;

    }

    @Override
    public View getView(int position, View  convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.line , parent, false);

        TextView id = (TextView) rowView.findViewById(R.id.txt_id);
        TextView prod = (TextView) rowView.findViewById(R.id.txt_nom);
        TextView categ = (TextView) rowView.findViewById(R.id.txt_categorie);
        TextView prix = (TextView) rowView.findViewById(R.id.txt_prix);
        TextView unite = (TextView) rowView.findViewById(R.id.txt_qnt);

        id.setText(elements.get(position).getRef());
        prod.setText(elements.get(position).getNomProd());
        categ.setText(elements.get(position).getCodeCategorie());
        prix.setText(Float.toString(elements.get(position).getPrixUnit()));
        unite.setText(Integer.toString(elements.get(position).getUniteStock()));

        return rowView;
    }

}
