package com.aperez.apps.androidfunwithflags;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity_SSNT extends AppCompatActivity {

    private EditText editTextUsuario,editTextContrasena;
    private Button buttonIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ssnt);

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        buttonIngresar = findViewById(R.id.buttonIngresar);

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = editTextUsuario.getText().toString();
                String contrasena = editTextContrasena.getText().toString();

                if(usuario.equals("santiago") && contrasena.equals("1234") || usuario.equals("kathy") && contrasena.equals()){
                    Intent intent - new Intent(this, MainActivity_SSNT.class);
                    startActivity(intent);
                }else{

                }

            }
        });
    }
}