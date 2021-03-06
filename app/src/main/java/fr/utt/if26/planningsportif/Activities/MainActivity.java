package fr.utt.if26.planningsportif.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import fr.utt.if26.planningsportif.Modeles.ActiviteTemps;
import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.Modeles.TypeProgramme;
import fr.utt.if26.planningsportif.Persistence.ActivitePersistence;
import fr.utt.if26.planningsportif.Persistence.ProgrammePersistence;
import fr.utt.if26.planningsportif.R;

public class MainActivity extends AppCompatActivity {

    protected Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        go = (Button)findViewById(R.id.btnGoMenu);

        ActivitePersistence activitePersistence = new ActivitePersistence(getApplicationContext());
        ProgrammePersistence programmePersistence = new ProgrammePersistence(getApplicationContext());
        //activitePersistence.deleteTable();
        //programmePersistence.deleteTable();


        activitePersistence.onCreate(null);
        programmePersistence.onCreate(null);

//        Programme p = new Programme(1, "Adrian");
//        Programme prg = new Programme(2, "Hugo");
//
//        programmePersistence.addProgramme(p);
//        programmePersistence.addProgramme(prg);
//
//        Programme p2 = programmePersistence.getProgramme(1);
//        Log.d("BD", p2.getId() +" ");
//        Log.d("BD", p2.getDateCreation());
//
//       // Date date = new Date(Integer.parseInt(p.getDateCreation()));
//       // Log.d("BD", date.toString());
//
//        ArrayList listPrg = programmePersistence.getAllProgrammes();
//        Log.d("liste", listPrg.toString());
//        Log.d("liste", listPrg.size() +" ");



       /* ActiviteTemps ac = new ActiviteTemps(1,1,"tot",15);

        ActivitePersistence activitePersistence = new ActivitePersistence(getApplicationContext(), null, null, 1);
        activitePersistence.onCreate(mydatabase);
        activitePersistence.onOpen(mydatabase);
        activitePersistence.addActiviteTemps(ac);
        Log.d("ac ajout", "ok");
*/


        btnCliqued();


    }

    private void btnCliqued() {
        go.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent myIntent = new Intent(view.getContext(), MenuActivity.class);
                startActivityForResult(myIntent, 0);
            }


        });

    }
}
