package fr.utt.if26.planningsportif.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import fr.utt.if26.planningsportif.Modeles.ActiviteRepetition;
import fr.utt.if26.planningsportif.Modeles.ActiviteTemps;
import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.Modeles.TypeProgramme;
import fr.utt.if26.planningsportif.Persistence.ActivitePersistence;
import fr.utt.if26.planningsportif.R;



public class ActiviteRepetitionActivity extends AppCompatActivity  {

    Programme programmeEnCours = null;
    EditText titreActivite;
    EditText repetActivite;
    EditText serieActivite;
    TypeProgramme type ;

    Button valider;

    ActivitePersistence connexionBD;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_repetition);
        connexionBD = new ActivitePersistence(this.getApplicationContext());


        programmeEnCours = (Programme) getIntent().getSerializableExtra("programmeEnCours");
        type = (TypeProgramme) getIntent().getSerializableExtra("type") ;

        titreActivite = (EditText) findViewById(R.id.titreActiviteRepetEditText);
        repetActivite = (EditText) findViewById(R.id.repetActiviteEditText);
        serieActivite = (EditText) findViewById(R.id.serieActiviteEditText);
        valider = (Button) findViewById(R.id.btnValiderActiviteRepet);

        //Ajout de l'activité qd clique sur bouton lancer
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titre = titreActivite.getText().toString();
                int repet = Integer.parseInt(repetActivite.getText().toString());
                int serie = Integer.parseInt(serieActivite.getText().toString());

                int activiteId = connexionBD.getAllActivite().size() +1;

                ActiviteRepetition activiteRepet = new ActiviteRepetition(programmeEnCours.getListeActivites().size(), programmeEnCours.getId(), titre, repet, serie); //CHOPER ID DU PROGRAMME EN COURS

                Log.d("cc", activiteRepet.toString());

                programmeEnCours.ajouterActivite(activiteRepet);
                //Log.d("activité ajoutée", "activité ajoutée");
                Toast.makeText(getApplicationContext(), "Activité ajoutée !", Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(view.getContext(), ChoixActiviteActivity.class);
                myIntent.putExtra("type", type); //TYPE ACTIVITE
                myIntent.putExtra("programmeEnCours", programmeEnCours);
                startActivityForResult(myIntent, 0);

            }
        });


    }
}
