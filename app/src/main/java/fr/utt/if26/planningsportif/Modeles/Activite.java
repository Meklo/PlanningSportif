package fr.utt.if26.planningsportif.Modeles;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Meklo on 10/12/2017.
 */

public abstract class Activite {

    protected int id;
    protected  String titre;
    protected  String type;
    protected  int programme_id;

    public Activite(int id, String titre, int programme_id) {
        this.id = id;
        this.titre = titre;
        this.programme_id = programme_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProgramme_id() {
        return programme_id;
    }

    public void setProgramme_id(int programme_id) {
        this.programme_id = programme_id;
    }
}

