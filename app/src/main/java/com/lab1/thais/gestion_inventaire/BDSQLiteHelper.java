package com.lab1.thais.gestion_inventaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Thais on 2017-06-05.
 */

public class BDSQLiteHelper extends SQLiteOpenHelper{

    private static  final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Inventaire";

    //Constants pra facilitar na busca dos dados
    private static final String TABLE_PRODUITS = "Produits";
    private static final String ID_PRODUIT = "ref";
    private static final String NOM_PRODUIT = "nom";
    private static final String CODE_CATEGORIE = "code";
    private static final String PRIX = "prixUnit";
    private static final String UNITE_STOCK = "uniteStock";

    //Config pra trazer todas as colunas
    private static final String[] ALL_COl = {ID_PRODUIT,NOM_PRODUIT,CODE_CATEGORIE,PRIX,UNITE_STOCK};

    public BDSQLiteHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE Produits("+
                "ref INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nom TEXT,"+
                "code TEXT,"+
                "prixUnit FLOAT,"+
                "uniteStock INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Produits");
        this.onCreate(db);
    }

    public void addProd(Produit produit){

        //Escreve no bd
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NOM_PRODUIT,produit.getNomProd());
        values.put(CODE_CATEGORIE,produit.getCodeCategorie());
        values.put(PRIX, new Float(produit.getPrixUnit()));
        values.put(UNITE_STOCK, new Integer(produit.getUniteStock()));

        db.insert(TABLE_PRODUITS, null, values);
        db.close();
    }

    public Produit getProduit(){

        //Le a bd
        SQLiteDatabase db = this.getReadableDatabase();

        //Apontador para as linhas da tabela
        Cursor cursor = db.query(TABLE_PRODUITS,
                ALL_COl,//todas as colunas
                "code = ?", //  ? será substituida pelo paramentro passado = code_Categorie
                new String [] {},
                null,//group by
                null,//having
                null,//order by
                null);//limit

        if (cursor == null){
            return null;
        }
        else{
            cursor.moveToFirst();
            Produit produit = cursorToProduit(cursor);
            return produit;
        }
    }

    //aponta para cada linha e pega as informaoces
    private Produit cursorToProduit(Cursor cursor){

        Produit produit = new Produit();

        produit.setRef(Integer.parseInt(cursor.getString(0)));
        produit.setNomProd(cursor.getString(1));
        produit.setCodeCategorie(cursor.getString(2));
        produit.setPrixUnit(Float.parseFloat(cursor.getString(3)));
        produit.setPrixUnit(Integer.parseInt(cursor.getString(4)));

        return produit;
    }

    public ArrayList<Produit> getAllProduits(){

        ArrayList<Produit> listProd = new ArrayList<Produit>();

        String query = "SELECT * FROM " + TABLE_PRODUITS;

        //le
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{
                Produit produit = cursorToProduit(cursor);
                listProd.add(produit);
            }while (cursor.moveToNext());
        }
        return listProd;
    }

    public ArrayList<String> getCategories(){

        ArrayList<String> listCateg = new ArrayList<String>();

        String query = "SELECT code FROM " + TABLE_PRODUITS + " WHERE code = " + CODE_CATEGORIE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{
                String categ = cursor.getString(2);
                listCateg.add(categ);
            }while (cursor.moveToNext());
        }
        return listCateg;
    }

    public int updateProduit(Produit produit){

        //Escreve no bd
        SQLiteDatabase db = this.getWritableDatabase();

        int countLigne;
        //ver min 7:34 de s04q13 SQLite parte03

        db.close();
        return 0;
    }

    public int deleteProduit(Produit produit){

        //Escreve no bd
        SQLiteDatabase db = this.getWritableDatabase();

        int countLigne;
        //ver min 7:34 de s04q13 SQLite parte03

        db.close();
        return 1;
    }


}
