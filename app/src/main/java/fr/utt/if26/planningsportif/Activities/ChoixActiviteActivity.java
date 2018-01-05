package fr.utt.if26.planningsportif.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.R;

public class ChoixActiviteActivity extends AppCompatActivity {

    Button activiteTps = null;
    Button activiteRep = null;
    Programme programmeEnCours = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_activite);

        programmeEnCours = (Programme) getIntent().getSerializableExtra("programmeEnCours");
        activiteRep = (Button) findViewById(R.id.activiteRepetBtnChoixActivite);
        activiteTps =  (Button) findViewById(R.id.activiteTpsBtnChoixActivite);

        //activiteRepet
        activiteRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ActiviteRepetitionActivity.class);
                myIntent.putExtra("programmeEnCours", programmeEnCours);
                startActivityForResult(myIntent, 0);
            }
        });

        //activiteTemps
        activiteTps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ActiviteTempsActivity.class);
                myIntent.putExtra("programmeEnCours", programmeEnCours);
                startActivityForResult(myIntent, 0);
            }
        });



     }

}
