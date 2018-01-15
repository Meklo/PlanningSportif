package fr.utt.if26.planningsportif.Activities;

import android.content.Intent;
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

import fr.utt.if26.planningsportif.Modeles.ActiviteTemps;
import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.Modeles.TypeProgramme;
import fr.utt.if26.planningsportif.Persistence.ActivitePersistence;
import fr.utt.if26.planningsportif.Persistence.ProgrammePersistence;
import fr.utt.if26.planningsportif.R;

public class ActiviteTempsActivity extends AppCompatActivity {

    Programme programmeEnCours = null;
    TypeProgramme type = null;

    EditText titreActivite;
    EditText tempsActivite;
    Button valider;

    ActivitePersistence connexionBD;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_temps);
        connexionBD = new ActivitePersistence(this.getApplicationContext());

        programmeEnCours = (Programme) getIntent().getSerializableExtra("programmeEnCours");
        type = (TypeProgramme) getIntent().getSerializableExtra("type") ;


        titreActivite = (EditText) findViewById(R.id.titreActiviteTpsEditText);
        tempsActivite = (EditText) findViewById(R.id.tempsActiviteEditText);
        valider = (Button) findViewById(R.id.btnValiderActiviteTemps);

        //Ajout de l'activité qd clique sur bouton lancer
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if( !titreActivite.getText().toString().equals("") && Integer.parseInt(tempsActivite.getText().toString()) >= 0 && !tempsActivite.getText().toString().equals("")) {

                    String titre = titreActivite.getText().toString();
                    int temps = Integer.parseInt(tempsActivite.getText().toString());

                    ActiviteTemps activiteTps = new ActiviteTemps(programmeEnCours.getListeActivites().size(), programmeEnCours.getId(), titre, temps);

                    programmeEnCours.ajouterActivite(activiteTps);
                    Log.d(programmeEnCours.toString(), "activité ajoutée");
                    Toast.makeText(getApplicationContext(), "Activité ajoutée !", Toast.LENGTH_LONG).show();


                    Intent myIntent = new Intent(view.getContext(), ChoixActiviteActivity.class);
                    myIntent.putExtra("type", type); //TYPE ACTIVITE
                    myIntent.putExtra("programmeEnCours", programmeEnCours);
                    startActivityForResult(myIntent, 0);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Veuillez renseigner correctement le titre et le temps", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
