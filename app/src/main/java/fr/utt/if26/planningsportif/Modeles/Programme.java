package fr.utt.if26.planningsportif.Modeles;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;

/**
 * Created by Meklo on 10/12/2017.
 */

public class Programme implements Serializable{

    protected int id;
    protected  String titre;
    protected  TypeProgramme type;
    protected String dateCreation;
    protected ArrayList<Activite> listeActivites;

    public Programme(int id, String titre, TypeProgramme type, String dateCreation, ArrayList<Activite> listeActivites) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.dateCreation = dateCreation;
        this.listeActivites = listeActivites;
    }

    public Programme(int id, String titre) {
        //Long timeStamp = System.currentTimeMillis()/1000;

        Timestamp time = new Timestamp(System.currentTimeMillis());
        Date date = new Date(time.getTime());


        this.id = id;
        this.titre = titre;
        this.type = TypeProgramme.MUSCULATION;
        this.dateCreation =  date.toString();
        this.listeActivites = new ArrayList<>();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public TypeProgramme getType() {
        return type;
    }

    public void setType(TypeProgramme type) {
        this.type = type;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ArrayList<Activite> getListeActivites() {
        return listeActivites;
    }

    public void setListeActivites(ArrayList<Activite> listeActivites) {
        this.listeActivites = listeActivites;
    }

    public void ajouterActivite(Activite activite){
        this.listeActivites.add(activite);
    }

    public void supprimerActivite(Activite activite){
        this.listeActivites.remove(activite);
    }

    public String toString(){
        return  this.getId() + ") " + this.getTitre() + " - " + this.getType() + " \n" + this.getDateCreation() + "\n\n" + this.getListeActivites();
    }



}
