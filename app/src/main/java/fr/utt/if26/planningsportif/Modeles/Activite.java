package fr.utt.if26.planningsportif.Modeles;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Meklo on 10/12/2017.
 */

public abstract class Activite {

    protected int id;
    protected  String titre;

    public Activite(int id, String titre) {
        this.id = id;
        this.titre = titre;
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

    public String toString(){
        return id +" "+ titre;
    }
}

