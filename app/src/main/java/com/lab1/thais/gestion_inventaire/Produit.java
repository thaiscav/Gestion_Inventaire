package com.lab1.thais.gestion_inventaire;

/**
 * Created by Thais on 2017-06-05.
 */

public class Produit {

    private int ref;
    private String nomProd;
    private String codeCategorie;
    private float prixUnit;
    private int uniteStock;

    //Java gera o construtor por padrao se este n for indicado na classe

    //Get e Set

    public int getRef() {
        return ref;
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

    public float getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(float prixUnit) {
        this.prixUnit = prixUnit;
    }

    public int getUniteStock() {
        return uniteStock;
    }

    public void setUniteStock(int uniteStock) {
        this.uniteStock = uniteStock;
    }
}
