package com.lab1.thais.gestion_inventaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thais on 2017-06-05.
 */

public class BDSQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    //DataBase Name
    private static final String DATABASE_NAME = "Inventaire";
    // cat table name
    private static final String TABLE_PRODUITS = "Produits";

    // cat Table Columns names
    private static final String ID_PRODUIT = "ref";
    private static final String NOM_PRODUIT = "nom";
    private static final String CODE_CATEGORIE = "code";
    private static final String PRIX = "prixUnit";
    private static final String UNITE_STOCK = "uniteStock";

    //Config pra trazer todas as colunas
    private static final String[] ALL_COl = {ID_PRODUIT, NOM_PRODUIT, CODE_CATEGORIE, PRIX, UNITE_STOCK};

    //Constutor
    public BDSQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Metodos obrigatórios criados pela ide
    @Override
    public void onCreate(SQLiteDatabase bd) {

        Log.i("---------> Confirmation", "Connected");

        try {
            String CREATE_TABLE = "CREATE TABLE " + TABLE_PRODUITS +
                    "(" +
                    "ref INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT," +
                    "code TEXT," +
                    "prixUnit FLOAT," +
                    "uniteStock INTEGER)";

            Log.i("---------> Confirmation", "Table Created");
            bd.execSQL(CREATE_TABLE);

            Boolean created = true;


        } catch (Exception e) {
            Log.e("Ërror", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {

        bd.execSQL("DROP TABLE IF EXISTS Produits");
        this.onCreate(bd);
        Log.i("---------> Confirmation", "Upgraded");
    }

    public void insertProd() {

            Produit produit;

            addProd(new Produit(1, "Chai", "Boissons", 90.00, 39));
            addProd(new Produit(2, "Chang", "Boissons", 95.00, 17));
            addProd(new Produit(3, "Anissed Syrup", "Condiments", 110.00, 53));
            addProd(new Produit(4, "Chef Antons Cajum Seasoning", "Condiments", 10.00, 13));
            addProd(new Produit(5, "Chef Anton's Gumto Mix", "Condiments", 106.75, 20));
            addProd(new Produit(6, "Grandmas Boysenberry", "Condiments", 125.75, 120));
            addProd(new Produit(7, "Uncle Bob's Organic Dried Pears", "Produits secs", 150.00, 15));
            addProd(new Produit(8, "Northwoods Cranbrerry", "Condiments", 485.00, 29));
            addProd(new Produit(9, "Mishi Kobe Niku", "Viandes", 485.00, 29));
            addProd(new Produit(10, "Ikura", "Poissons et fruits de mer", 155.00, 31));
            addProd(new Produit(1000,"Select","Select",0.0,0));
    }

    //Metodos adicionados
    public void addProd(Produit produit) {

        SQLiteDatabase bd = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NOM_PRODUIT, produit.getNomProd());
        values.put(CODE_CATEGORIE, produit.getCodeCategorie());
        values.put(PRIX, new Double(produit.getPrixUnit()));
        values.put(UNITE_STOCK, new Integer(produit.getUniteStock()));

        bd.insert(TABLE_PRODUITS, null, values);
        bd.close();
    }

    //aponta para cada linha e pega as informaoces e passa para a Classe Prod
    private Produit cursorToProduit(Cursor cursor) {

        Produit produit = new Produit();

        produit.setRef(Integer.parseInt(cursor.getString(0)));
        produit.setNomProd(cursor.getString(1));
        produit.setCodeCategorie(cursor.getString(2));
        produit.setPrixUnit(Double.parseDouble(cursor.getString(3)));
        produit.setUniteStock(Integer.parseInt(cursor.getString(4)));

        return produit;
    }

    public ArrayList<Produit> getAllProduits() {

        ArrayList<Produit> listProd = new ArrayList<Produit>();

        String query = "SELECT * FROM " + TABLE_PRODUITS;

        //Read
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            do {
                Produit produit = cursorToProduit(cursor);
                listProd.add(produit);
            } while (cursor.moveToNext());
        }
        return listProd;
    }

    public ArrayList<Produit> getAllProduits(String categorie) {

        ArrayList<Produit> listProd = new ArrayList<Produit>();

        String query = "SELECT * FROM " + TABLE_PRODUITS + " WHERE code = "+"'"+categorie+"'";

        //Read
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            do {
                Produit produit = cursorToProduit(cursor);
                listProd.add(produit);
            } while (cursor.moveToNext());
        }
        return listProd;
    }

    public List<String> getAllCategories() {

        List<String> cat = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PRODUITS + " GROUP BY code "; //GROUP BY code

        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                cat.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        bd.close();

        // returning lables
        return cat;
    }

    public Double getTotal(){

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT sum(prixUnit) * sum(uniteStock) as total FROM " + TABLE_PRODUITS;

        Cursor c = db.rawQuery(query, null);

        //Add in the movetofirst etc here? see SO
        c.moveToFirst();
        Double i = c.getDouble(0);

        return i;
    }


}
