package com.example.paragleid_apptestapplikation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    EditText Name;
    EditText Passwort;
    Button Registieren;
    Button Login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Activity();
    }

    public void Activity() {

        Name = (EditText) findViewById(R.id.editTextLogPassword);
        Passwort = (EditText) findViewById(R.id.editTextLogName);
        Registieren = findViewById(R.id.btnRegestrieren);
        Login = findViewById(R.id.btnLogin);


        Registieren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegestration();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void openRegestration() {
        Intent intent = new Intent(this, Regestration.class);
        startActivity(intent);
    }

}
