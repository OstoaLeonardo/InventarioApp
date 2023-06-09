package com.example.inventarioapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_RUN = "firstRun";

    EditText txtMail, txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean firstRun = settings.getBoolean(KEY_FIRST_RUN, true);
        txtMail = findViewById(R.id.inCorreo);
        txtPass = findViewById(R.id.inContrasena);
    }

    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean(KEY_FIRST_RUN, false);
                    editor.apply();

                }
                else
                    Toast.makeText(Login.this, "Usuario invalido", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usuario", txtMail.getText().toString());
                params.put("password", txtPass.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void Login(View view){
        if(txtMail.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado el correo");
            return;
        }
        if(txtPass.getText().toString().isEmpty()) {
            mostrarError("No se ha ingresado la contraseña");
            return;
        }
        validarUsuario("https://daduappmovil.000webhostapp.com/validar_usuario.php");
    }

    private void mostrarError(String msg){
        View view = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public void Register(View view){
       // Intent intent = new Intent(Login.this, Register.class);
        //startActivity(intent);
    }

    public void Recovery(View view){
        Intent intent = new Intent(Login.this, Password.class);
        startActivity(intent);
    }
}