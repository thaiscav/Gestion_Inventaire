package com.lab1.thais.gestion_inventaire;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BDSQLiteHelper bd;
    ArrayList<Produit> lstProd;
    ArrayList<String> lstCategories;
    private Spinner spCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Carrega MENU
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_menuPrincipal);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        //Carrega BD
        bd = new BDSQLiteHelper(this);

        //Floating Button  = Clear List
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Limpar listView
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        //CArrega Lista
        ListView list = (ListView) findViewById(R.id.lst_table);
        lstProd = bd.getAllProduits();

        //Envia para a lista adaptada
        ProduitAdapter adapter = new ProduitAdapter(this,lstProd);
        list.setAdapter(adapter);

        //Carrega Spinner
        addItensOnSpinner();

    }

    public void listAll(){

        ListView list = (ListView) findViewById(R.id.lst_table);
        lstProd = bd.getAllProduits();

        //Envia para a lista adaptada
        ProduitAdapter adapter = new ProduitAdapter(this,lstProd);
        list.setAdapter(adapter);

    }

    public void addItensOnSpinner(){

        spCategorie = (Spinner) findViewById(R.id.sp_categorie);
        lstCategories = bd.getCategories();

        //Cria a lista e determina o layout dessa lista
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_row, lstProd);
        spCategorie.setAdapter(adapter);

        //Listenner
        AdapterView.OnItemSelectedListener selectedItem = new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> AdapterView, View view, int position, long id) {

                String item = spCategorie.getSelectedItem().toString();
                String positionItem = spCategorie.getItemAtPosition(position).toString();
                //String idItem = spCategorie.getSelectedItemId(id).toString();

                Toast.makeText(MainActivity.this, "Item: "+ item +" | Posicao: "+ position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView parent) {

                Toast.makeText(MainActivity.this, "Nada escolhido", Toast.LENGTH_SHORT).show();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        /*// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/

        switch(item.getItemId()){
            case R.id.lister:

                listAll();

                Toast.makeText(MainActivity.this, "Lista", Toast.LENGTH_SHORT).show();

                break;
            case R.id.categorie:

                Toast.makeText(MainActivity.this, "Categoria", Toast.LENGTH_SHORT).show();

                break;
            case R.id.total:

                Toast.makeText(MainActivity.this, "Total", Toast.LENGTH_SHORT).show();

                break;
                //this.finish();
            default:
                Toast.makeText(MainActivity.this, "Defalt", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
