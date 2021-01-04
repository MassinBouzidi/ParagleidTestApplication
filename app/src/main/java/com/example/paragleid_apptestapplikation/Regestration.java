package com.example.paragleid_apptestapplikation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Regestration extends AppCompatActivity {

    EditText EMail;                // Fehlerursache beim Deklarieren der Variable
    EditText Password;  // Fehlerursache beim Deklarieren der Variable
    Button btnRegestrieren;
    String KEY_NAME = "Name";
    String KEY_Passwort = "Passwort";
    String KEY_EMAIL = "Email";

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
        EMail = findViewById(R.id.EMail);
        Password = findViewById(R.id.editTextPassword);
        btnRegestrieren = findViewById(R.id.btnRegestration);

        firebaseAuth = FirebaseAuth.getInstance();


    }



    public void SaveData () {  //TODO Bedingung soll mit einem Button OnClick anfangen.

        btnRegestrieren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Regestration.this, "Button clicked!", Toast.LENGTH_SHORT).show();

                    String user_Email = EMail.getText().toString();
                    String user_Passwort = Password.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(user_Email, user_Passwort).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Regestration.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                UploadData();

                            }else{
                                Toast.makeText(Regestration.this, "Registration unsuccesful", Toast.LENGTH_SHORT ).show(); //Bis jetzt ist eine Regestrierung nicht erfolgreich
                            }
                        }
                    });
            }
        });
    }

    public void UploadData() {


        Map<String, Object> user = new HashMap<>();
        user.put(KEY_Passwort, Password);
        user.put(KEY_EMAIL, EMail);
    }



}
