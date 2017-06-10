package com.lab1.thais.gestion_inventaire;

import java.util.ArrayList;

/**
 * Created by Thais on 2017-06-05.
 */

public class Produit {

    private Integer ref;
    private String nomProd;
    private String codeCategorie;
    private Double prixUnit;
    private Integer uniteStock;

    //J'ai utilisé la BD pour gérer la Class
    public static ArrayList<Produit> conteneurProduits = new ArrayList<Produit>();

    //Constructeurs
    public Produit(){conteneurProduits.add(this);}

    public Produit(Integer ref, String nomProd, String codeCategorie, Double prixUnit, Integer uniteStock) {
        this.ref = ref;
        this.nomProd = nomProd;
        this.codeCategorie = codeCategorie;
        this.prixUnit = prixUnit;
        this.uniteStock = uniteStock;

        conteneurProduits.add(this);
    }

    public Produit(Produit produit){
        this(produit.getRef(), produit.getNomProd(), produit.getCodeCategorie(), produit.getPrixUnit(), produit.getUniteStock());
        conteneurProduits.add(this);
    }

    //Conne

    //Get et Set
    public int getRef() {
        return ref;
    }

    public String refToString() {

        return  ref.toString();
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    public double getPrixUnit() {
        return prixUnit;
    }

    public String prixUnitToString() {

        String prixUnitStg = prixUnit.toString() + " $";

        return  prixUnitStg;
    }

    public void setPrixUnit(Double prixUnit) {
        this.prixUnit = prixUnit;
    }

    public int getUniteStock() {
        return uniteStock;
    }

    public String uniteStockToString() {

        return  uniteStock.toString();
    }

    public void setUniteStock(int uniteStock) {
        this.uniteStock = uniteStock;
    }

    @Override
    public String toString() {

        return ref + "\u0009\u0009\u0009" + nomProd + "\u0009\u0009" +
                codeCategorie + "\u0009\u0009" + prixUnit + "\u0009\u0009" + uniteStock;
    }
}
