package fr.utt.if26.planningsportif.Modeles;

import java.io.Serializable;

/**
 * Created by Meklo on 10/12/2017.
 */

public class ActiviteRepetition extends Activite {

    protected int repetition; // nombre de fois ou on reproduit le mouvement
    protected int serie; //Nb de fois ou le fait les repetitions

    public ActiviteRepetition(int id, int programme_id, String titre, int repetition, int serie) {
        super(id, titre, programme_id);
        this.repetition = repetition;
        this.serie = serie;
        this.type = "repetition";
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        String  msg = new String();
        msg = super.toString();
        msg += " repetition : " + repetition +" série : " + serie;
        return msg;
    }
}
