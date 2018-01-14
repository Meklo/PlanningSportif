package fr.utt.if26.planningsportif.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import fr.utt.if26.planningsportif.Modeles.Activite;
import fr.utt.if26.planningsportif.Modeles.ActiviteTemps;
import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.Modeles.TypeProgramme;
import fr.utt.if26.planningsportif.Persistence.ProgrammePersistence;
import fr.utt.if26.planningsportif.R;

public class ActiviteRecapActivity extends AppCompatActivity  {

    ListView mListView;
    Programme programmeEnCours = null;
    Button valider ;
    TypeProgramme type = null;

    ActiviteTemps  activite;
    ArrayList<ActiviteTemps> listActivite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_recap);

        Log.d("va ds create", "ok");
        mListView = (ListView) findViewById(R.id.listView2);
        valider = (Button) findViewById(R.id.enregistrerPrg);


      /*  activite = (ActiviteTemps) getIntent().getSerializableExtra("activite");

        listActivite = new ArrayList<>();
        listActivite.add(activite);

*/
      try {
          programmeEnCours = (Programme) getIntent().getSerializableExtra("programmeEnCours");
          type = (TypeProgramme) getIntent().getSerializableExtra("type");

          // Log.d(programmeEnCours.toString(), "ok");
          ArrayList listActivite = programmeEnCours.getListeActivites();

          final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActiviteRecapActivity.this, android.R.layout.simple_list_item_1, listActivite);
          mListView.setAdapter(adapter);

          valider.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Toast.makeText(getApplicationContext(), "Programme enregistré ! :)", Toast.LENGTH_LONG).show();

                  //FAIRE LA SAUVEGARDE DANS LA BDD !!

                  Intent myIntent = new Intent(view.getContext(), MenuActivity.class);
                 // myIntent.putExtra("type", type); //TYPE ACTIVITE
                 // myIntent.putExtra("programmeEnCours", programmeEnCours);
                  startActivityForResult(myIntent, 0);
              }
          });


      }catch (Exception e){
          Log.e(e.toString(), "pb");
      }
    }


}
