package fr.utt.if26.planningsportif.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.Persistence.ActivitePersistence;
import fr.utt.if26.planningsportif.Persistence.ProgrammePersistence;
import fr.utt.if26.planningsportif.R;

public class ListProgrammeActivity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_programme);

        mListView = (ListView) findViewById(R.id.listView);

        ProgrammePersistence programmePersistence = new ProgrammePersistence(getApplicationContext());
        ActivitePersistence activitePersistence = new ActivitePersistence(getApplicationContext());

        ArrayList listPrg = programmePersistence.getAllProgrammes();


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListProgrammeActivity.this,android.R.layout.simple_list_item_1, listPrg);
        mListView.setAdapter(adapter);
    }
}
