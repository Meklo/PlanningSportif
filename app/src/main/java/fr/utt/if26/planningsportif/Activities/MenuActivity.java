package fr.utt.if26.planningsportif.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.utt.if26.planningsportif.R;

public class MenuActivity extends AppCompatActivity {

    Button newProgramme;
    Button mesProgrammes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        newProgramme = (Button)findViewById(R.id.nouveauProgrammeBtnMenu);
        mesProgrammes = (Button) findViewById(R.id.listProgrammeBtnMenu);

        boutonNewProgCliquer();
        boutonMesProgCliquer();



    }

    private void boutonNewProgCliquer() {
        newProgramme.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent myIntent = new Intent(view.getContext(), NouveauProgrammeActivity.class); /** Class name here */
                startActivityForResult(myIntent, 0);
            }

        });

    }

    private void boutonMesProgCliquer() {
        mesProgrammes.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent myIntent = new Intent(view.getContext(), ListProgrammeActivity.class); /** Class name here */
                startActivityForResult(myIntent, 0);
            }

        });

    }



}
