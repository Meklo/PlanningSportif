package fr.utt.if26.planningsportif.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.Modeles.TypeProgramme;
import fr.utt.if26.planningsportif.R;

public class ChoixActiviteActivity extends AppCompatActivity {

    Button activiteTps = null;
    Button activiteRep = null;
    Button validerToutesActivités;

    Programme programmeEnCours = null;

    TypeProgramme type= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_activite);

        programmeEnCours = (Programme) getIntent().getSerializableExtra("programmeEnCours");
        type = (TypeProgramme) getIntent().getSerializableExtra("type") ;

        activiteRep = (Button) findViewById(R.id.activiteRepetBtnChoixActivite);
        activiteTps =  (Button) findViewById(R.id.activiteTpsBtnChoixActivite);
        validerToutesActivités = (Button) findViewById(R.id.validerToutesActivités);

        //activiteRepet
        activiteRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ActiviteRepetitionActivity.class);
                myIntent.putExtra("type", type); //TYPE ACTIVITE
                myIntent.putExtra("programmeEnCours", programmeEnCours);
                startActivityForResult(myIntent, 0);
            }
        });

        //activiteTemps
        activiteTps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ActiviteTempsActivity.class);
                myIntent.putExtra("type", type); //TYPE ACTIVITE
                myIntent.putExtra("programmeEnCours", programmeEnCours);
                startActivityForResult(myIntent, 0);
            }
        });

        validerToutesActivités.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ActiviteRecapActivity.class);
                myIntent.putExtra("type", type); //TYPE ACTIVITE
                myIntent.putExtra("programmeEnCours", programmeEnCours);
                startActivityForResult(myIntent, 0);
            }
        });



     }

}
