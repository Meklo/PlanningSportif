package fr.utt.if26.planningsportif.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.utt.if26.planningsportif.Modeles.ActiviteTemps;
import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.R;

public class ActiviteTempsActivity extends AppCompatActivity {

    Programme programmeEnCours = null;
    EditText titreActivite;
    EditText tempsActivite;
    Button valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_temps);

        programmeEnCours = (Programme) getIntent().getSerializableExtra("programmeEnCours");

        titreActivite = (EditText) findViewById(R.id.titreActiviteTpsEditText);
        tempsActivite = (EditText) findViewById(R.id.tempsActiviteEditText);
        valider = (Button) findViewById(R.id.btnValiderActiviteTemps);

        //Ajout de l'activité qd clique sur bouton lancer
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titre = titreActivite.getText().toString();
                int temps = Integer.parseInt(tempsActivite.getText().toString());

                ActiviteTemps activiteTps = new ActiviteTemps(1, 1, titre, temps);
                Log.d("aa", "bb");
                Log.d("cc", activiteTps.toString());

                programmeEnCours.ajouterActivite(activiteTps);
                Log.d("activité ajoutée", "activité ajoutée");
                Toast.makeText(getApplicationContext(), "Activité ajoutée !", Toast.LENGTH_LONG).show();

            }
        });


    }
}
