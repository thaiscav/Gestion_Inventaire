package com.lab1.thais.gestion_inventaire;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProdActivity extends AppCompatActivity {

    private BDSQLiteHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prod);

        bd = new BDSQLiteHelper(this);

        final EditText nom = (EditText) findViewById(R.id.txt_addNom);
        final EditText categ = (EditText) findViewById(R.id.txt_addCateg);
        final EditText prix = (EditText) findViewById(R.id.txt_addPrix);
        final EditText unit = (EditText) findViewById(R.id.txt_addUnit);

        Button btnAdd = (Button) findViewById(R.id.btn_add);

        /*// Hiding the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(unit.getWindowToken(), 0);*/

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void  onClick (View view){
                //Insert Label
                String categoria = categ.getText().toString();

                if ((nom.getText().toString().trim().length() > 0)
                        && (categ.getText().toString().trim().length() > 0)
                        && (prix.getText().toString().trim().length() > 0)
                        && (unit.getText().toString().trim().length() > 0)) {

                    // Add database handler
                    //bd = new BDSQLiteHelper(getApplicationContext());
                    Produit produit = new Produit();

                    produit.setNomProd(nom.getText().toString());
                    produit.setCodeCategorie(categ.getText().toString());
                    produit.setPrixUnit(Double.parseDouble(prix.getText().toString()));
                    produit.setUniteStock(Integer.parseInt(unit.getText().toString()));

                    bd.addProd(produit);

                    Toast.makeText(getBaseContext(), "Added", Toast.LENGTH_SHORT).show();

                    nom.setText("");
                    categ.setText("");
                    prix.setText("");
                    unit.setText("");

                    //Redirecionamento de activities ----- ORIGEM / DESTINO
                    Intent intent = new Intent(AddProdActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter information",
                            Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}
