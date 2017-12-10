package fr.utt.if26.planningsportif.Modeles;

/**
 * Created by Meklo on 10/12/2017.
 */

public class ActiviteRepetition extends Activite {

    protected int repetition; // nombre de fois ou on reproduit le mouvement
    protected int serie; //Nb de fois ou le fait les repetitions

    public ActiviteRepetition(int id, String titre, int repetition, int serie) {
        super(id, titre);
        this.repetition = repetition;
        this.serie = serie;
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
}
