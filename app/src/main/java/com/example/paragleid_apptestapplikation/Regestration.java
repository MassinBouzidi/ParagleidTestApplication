package com.example.paragleid_apptestapplikation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Regestration extends AppCompatActivity {

    EditText Name;
    EditText Adresse;
    EditText Telefonnummer;
    EditText EMail;                // Fehlerursache beim Deklarieren der Variable
    EditText Password;  // Fehlerursache beim Deklarieren der Variable
    Button btnRegestrieren;

    String KEY_TELEFONNUMMER;
    String KEY_NAME = "Name";
    String KEY_ADRESSE = "Adresse";

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);
        Activity();
        SaveData();

    }

    public void Activity () {
        Name = findViewById(R.id.editTextLogName);
        EMail = findViewById(R.id.EMail);
        Password = findViewById(R.id.editTextPassword);
        btnRegestrieren = findViewById(R.id.btnRegestration);

        firebaseAuth = FirebaseAuth.getInstance();


    }

    public void CreateUser () {

        String user_Email = EMail.getText().toString();
        String user_Passwort = Password.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(user_Email, user_Passwort).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(Regestration.this, "Registration successful!", Toast.LENGTH_SHORT).show(); // Daten werden in der Datenbank gespeichert


                }else{
                    Toast.makeText(Regestration.this, "Registration unsuccesful", Toast.LENGTH_SHORT ).show(); //Bis jetzt ist eine Regestrierung nicht erfolgreich
                }
            }
        });
    }


    public void SaveData () {

        btnRegestrieren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Regestration.this, "Button clicked!", Toast.LENGTH_SHORT).show();
                CreateUser();
                SaveInFirestore();

            }
        });
    }

    public void SaveInFirestore () {

        String user_Name = Name.getText().toString();
        String user_Adresse = Adresse.getText().toString();
        String user_EMail = EMail.getText().toString();

        Map<String, Object> user = new HashMap<>();
        user.put(KEY_NAME, user_Name);
        user.put(KEY_ADRESSE, user_Adresse);
        user.put(KEY_TELEFONNUMMER, user_EMail);

        db.collection("Users")
                .add(user);
    }



}
