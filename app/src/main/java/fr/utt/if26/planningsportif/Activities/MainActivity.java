package fr.utt.if26.planningsportif.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import fr.utt.if26.planningsportif.R;

public class MainActivity extends AppCompatActivity {

    Button go = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        go = (Button)findViewById(R.id.btnGoMenu);

        btnCliqued();


    }

    private void btnCliqued() {
        go.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //ANDROID SWITCH VIEW
              //  Log.d("ee","ee");
                Intent myIntent = new Intent(view.getContext(), MenuActivity.class); /** Class name here */
                startActivityForResult(myIntent, 0);
            }


        });

    }
}
