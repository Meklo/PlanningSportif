package fr.utt.if26.planningsportif.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import fr.utt.if26.planningsportif.Modeles.Activite;
import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.Modeles.TypeProgramme;
import fr.utt.if26.planningsportif.Persistence.ProgrammePersistence;
import fr.utt.if26.planningsportif.R;

public class NouveauProgrammeActivity extends AppCompatActivity {

    Button validerNewProg;
    ImageButton foot;
    ImageButton basket;
    ImageButton muscu;
    ImageButton velo;
    ImageButton cheval;
    ImageButton natation;

    TypeProgramme type = null;
    ProgrammePersistence connexionBD;

    EditText titreEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        connexionBD = new ProgrammePersistence(this.getApplicationContext());

        setContentView(R.layout.activity_nouveau_programme);

        validerNewProg = (Button) findViewById(R.id.validerProgrammeBtnNouveauProg);
        titreEdit = (EditText) findViewById(R.id.titrePrg);

        foot = (ImageButton) findViewById(R.id.footImg);
        basket = (ImageButton) findViewById(R.id.basketImg);
        muscu = (ImageButton) findViewById(R.id.muscuImg);
        velo = (ImageButton) findViewById(R.id.veloImg);
        cheval = (ImageButton) findViewById(R.id.horseImg);
        natation = (ImageButton) findViewById(R.id.natationImg);

        //Affiche le sport + modifie la variable type
        foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Football sélectionné  !", Toast.LENGTH_SHORT).show();
                 type = TypeProgramme.FOOT;
            }
        });

        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Basket sélectionné  !", Toast.LENGTH_SHORT).show();
                 type = TypeProgramme.BASKET;

            }
        });

        muscu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Musculation sélectionnée  !", Toast.LENGTH_SHORT).show();
                 type = TypeProgramme.MUSCULATION;

            }
        });

        velo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Vélo sélectionné  !", Toast.LENGTH_SHORT).show();
                 type = TypeProgramme.VELO;

            }
        });

        cheval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Equitation sélectionné  !", Toast.LENGTH_SHORT).show();
                 type = TypeProgramme.EQUITATION;

            }
        });

        natation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Natation sélectionnée  !", Toast.LENGTH_SHORT).show();
                 type = TypeProgramme.NATATION;

            }
        });


        validerNewProg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //L'utilisateur sélectionne un sport --> changement de page
               // if (sportSelectionne > -1 && sportSelectionne < 6) {
                if (type != null){
                    //try {
                        String titre = titreEdit.getText().toString();
                        int programmeID = connexionBD.getAllProgrammes().size()+1;
                        Programme p = new Programme(programmeID, titre, type, null, new ArrayList<Activite>());

                        // Toast.makeText(getApplicationContext(), "Programme créé  !", Toast.LENGTH_LONG).show();

                        Intent myIntent = new Intent(view.getContext(), ChoixActiviteActivity.class);
                        myIntent.putExtra("type", type); //TYPE ACTIVITE
                        myIntent.putExtra("programmeEnCours", p);
                        startActivityForResult(myIntent, 0);
                   // } catch ()
                }
                //L'utilisateur n'a pas sélectionné de sport --> retour sur la page
                else {
                    Toast.makeText(getApplicationContext(), "Veuillez sélectionner un sport svp", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}
