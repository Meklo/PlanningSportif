package fr.utt.if26.planningsportif.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.R;

public class NouveauProgrammeActivity extends AppCompatActivity {

    Button validerNewProg;
    EditText titreEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_programme);

        validerNewProg = (Button) findViewById(R.id.validerProgrammeBtnNouveauProg);
        titreEdit = (EditText) findViewById(R.id.titrePrg);
        boutonValiderNewProgCliquer();
        Log.println(Log.DEBUG,"a","bb");

    }

    private void boutonValiderNewProgCliquer() {
      //  Log.i("rentre","");
        validerNewProg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String titre = titreEdit.getText().toString();
                Programme p = new Programme(1, titre);

                //Log.d("prg", p.toString());

                Intent myIntent = new Intent(view.getContext(), ChoixActiviteActivity.class);
                myIntent.putExtra("programmeEnCours", p);
                startActivityForResult(myIntent, 0);
            }

        });
    }
}
