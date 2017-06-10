package com.lab1.thais.gestion_inventaire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thais on 2017-06-05.
 */

public class ProduitAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final List<Produit> items;

    public ProduitAdapter(Context context, List<Produit> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Produit getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.items.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Produit Produit = getItem(position);

        if(convertView == null) {
            // If convertView is null we have to inflate a new layout
            convertView = this.inflater.inflate(R.layout.line, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.txt_ref = (TextView) convertView.findViewById(R.id.txt_id);
            viewHolder.txt_nom = (TextView) convertView.findViewById(R.id.txt_nom);
            viewHolder.txt_categ = (TextView) convertView.findViewById(R.id.txt_categorie);
            viewHolder.txt_prix = (TextView) convertView.findViewById(R.id.txt_prix);
            viewHolder.txt_unit = (TextView) convertView.findViewById(R.id.txt_qnt);

            // We set the view holder as tag of the convertView so we can access the view holder later on.
            convertView.setTag(viewHolder);
        }

        // Retrieve the view holder from the convertView
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        // Bind the values to the views
        viewHolder.txt_ref.setText(Produit.refToString());
        viewHolder.txt_nom.setText(Produit.getNomProd());
        viewHolder.txt_categ.setText(Produit.getCodeCategorie());
        viewHolder.txt_prix.setText(Produit.prixUnitToString());
        viewHolder.txt_unit.setText(Produit.uniteStockToString());
        return convertView;
    }

}
