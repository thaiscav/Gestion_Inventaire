package com.lab1.thais.gestion_inventaire;

import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BDSQLiteHelper bd;
    private ArrayAdapter<Produit> adapter;
    private ArrayList<Produit> lstProduits;
    private Spinner spCategorie;
    private String categorieChoisie;
    private ListView listViewTable;
    private TextView lbl_categorie;
    private double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Carrega MENU
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_menuPrincipal);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);

        //Carrega floating Button  = Clear List
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Limpa Através do adapter
                if (lstProduits != null){

                    lstProduits.clear();
                    listViewTable.setAdapter(adapter);
                }
            }
        });

        //Start BD
        bd = new BDSQLiteHelper(this); //getApplicationContext()


        if (bd != null) {
            bd.insertProd();
            Toast.makeText(MainActivity.this, "Created BD", Toast.LENGTH_SHORT).show();
        }

        //Start ListView
        listViewTable = (ListView) findViewById(R.id.lv_table);
    }

    @Override
    protected void onStart(){
        super.onStart();

        //Start spinner
        lbl_categorie = (TextView) findViewById(R.id.lbl_categ);
        spCategorie = (Spinner) findViewById(R.id.sp_categorie);
        addItensOnSpinner();

        //Spiner Listenner
        AdapterView.OnItemSelectedListener selectedItem = new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> AdapterView, View view, int position, long id) {

                categorieChoisie = spCategorie.getSelectedItem().toString();
                listerParCategorie();
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

                Toast.makeText(MainActivity.this, "Please select a category", Toast.LENGTH_SHORT).show();
            }
        };
        spCategorie.setOnItemSelectedListener(selectedItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem Produit) {

        int id = Produit.getItemId();

        switch(id){
            case R.id.lister:

                listerTout();
                lbl_categorie.setVisibility(View.INVISIBLE);
                spCategorie.setVisibility(View.INVISIBLE);

                return true;
            case R.id.categorie:

                lbl_categorie.setVisibility(View.VISIBLE);
                spCategorie.setVisibility(View.VISIBLE);

                return true;
            case R.id.total:

                total();
                lbl_categorie.setVisibility(View.INVISIBLE);
                spCategorie.setVisibility(View.INVISIBLE);

                return true;
            case R.id.ajouter:

                ajouter();

                return true;
            default:

                Toast.makeText(MainActivity.this, "ERRO", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(Produit);
        }

    }

    public void addItensOnSpinner() {

        // Start BD
        bd = new BDSQLiteHelper(this); //getApplicationContext()

        // Spinner Drop down elements
        List<String> lstCategories = bd.getAllCategories();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lstCategories);

        // Attaching data adapter to spinner
        spCategorie.setAdapter(dataAdapter);

    }

    public void listerParCategorie(){

        //String categ = Produit.conteneurProduits.get(0).getCodeCategorie();

        Toast.makeText(MainActivity.this,"Categorie choisie = "+categorieChoisie, Toast.LENGTH_SHORT).show();

        lstProduits = bd.getAllProduits(categorieChoisie);

        ProduitAdapter adapter = new ProduitAdapter(this,lstProduits);
        listViewTable.setAdapter(adapter);
    }

    public void listerTout(){//criar metodos diferentes

        Toast.makeText(MainActivity.this,"Toutes les Categories", Toast.LENGTH_SHORT).show();

        lstProduits = bd.getAllProduits();

        //adapter = new ArrayAdapter<Produit>(this, android.R.layout.simple_list_item_1, lstProduits);
        //listViewTable.setAdapter(adapter);

        ProduitAdapter adapter = new ProduitAdapter(this,lstProduits);
        listViewTable.setAdapter(adapter);

    }//fin listerTout

    public void total(){

        total = 0;

        total = bd.getTotal();
        /*
        for(Produit item : Produit.conteneurProduits)
        {
            total += (item.getPrixUnit() * item.getUniteStock());
        }
        */
        Toast.makeText(MainActivity.this, "TOTAL = " + total + " $", Toast.LENGTH_LONG).show();

    }

    public void ajouter(){

        //Redireciona ----- ORIGEM / DESTINO
        Intent intent = new Intent(MainActivity.this, AddProdActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
