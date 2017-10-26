package com.example.ahmed.meteosnew;

/**
 * Created by joudar on 23/10/17.
 */

class ClimatElement {
    String ville;
    String pays;
    String lcation;
    String minTemp;
    String maxTemp;
    long timeStamp;
    String nomDuMois;
    String nomDuJour;
    int    jour;
    int    mois;
    int    annee;





    public ClimatElement(String ville, String pays, String lcation, String minTemp, String maxTemp, long timeStamp, String nomDuMois, String nomDuJour, int jour, int mois ,int annee) {
         this.ville =ville;
        this.pays =pays;
        this.lcation = lcation;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.nomDuMois = nomDuMois;
        this.nomDuJour = nomDuJour;
        this.jour = jour;
        this.mois = mois;
        this.annee =annee;



    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getLcation() {
        return lcation;
    }

    public void setLcation(String lcation) {
        this.lcation = lcation;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNomDuMois() {
        return nomDuMois;
    }

    public void setNomDuMois(String nomDuMois) {
        this.nomDuMois = nomDuMois;
    }

    public String getNomDuJour() {
        return nomDuJour;
    }

    public void setNomDuJour(String nomDuJour) {
        this.nomDuJour = nomDuJour;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
