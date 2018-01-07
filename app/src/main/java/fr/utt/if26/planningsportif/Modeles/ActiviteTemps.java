package fr.utt.if26.planningsportif.Modeles;

/**
 * Created by Meklo on 10/12/2017.
 */

public class ActiviteTemps extends Activite {

    protected int temps; // temps en seconde

    public ActiviteTemps(int id, int programme_id, String titre, int temps) {
        super(id, titre, programme_id);
        this.temps = temps;
        this.type = "temps";
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    @Override
    public String toString() {
        String  msg = new String();
        msg = super.toString();
        msg += " temps : " + temps;
        return msg;
    }
}
