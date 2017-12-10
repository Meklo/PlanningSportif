package fr.utt.if26.planningsportif.Modeles;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Meklo on 10/12/2017.
 */

public class Programme {

    protected int id;
    protected  String titre;
    protected  TypeProgramme type;
    protected Date dateCreation;
    protected ArrayList<Activite> listeActivites;

    public Programme(int id, String titre, TypeProgramme type, Date dateCreation, ArrayList<Activite> listeActivites) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.dateCreation = dateCreation;
        this.listeActivites = listeActivites;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
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

}
