package fr.utt.if26.planningsportif.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.utt.if26.planningsportif.Modeles.ActiviteRepetition;
import fr.utt.if26.planningsportif.Modeles.ActiviteTemps;
import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.R;



public class ActiviteRepetitionActivity extends AppCompatActivity {

    Programme programmeEnCours = null;
    EditText titreActivite;
    EditText repetActivite;
    EditText serieActivite;
    Button valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_repetition);

        programmeEnCours = (Programme) getIntent().getSerializableExtra("programmeEnCours");

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

                ActiviteRepetition activiteRepet = new ActiviteRepetition(1,1, titre, repet, serie);
               // Log.d("aa", "bb");
                Log.d("cc", activiteRepet.toString());

                programmeEnCours.ajouterActivite(activiteRepet);
                //Log.d("activité ajoutée", "activité ajoutée");
                Toast.makeText(getApplicationContext(), "Activité ajoutée !", Toast.LENGTH_LONG).show();

            }
        });
    }
}
