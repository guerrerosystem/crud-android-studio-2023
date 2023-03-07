package com.guerrero.crud_api;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Agregar extends AppCompatActivity {
 EditText ednombre,edcorreo,eddireccion;
 Button btnregistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        ednombre=findViewById(R.id.ednombre);
        edcorreo=findViewById(R.id.edcorreo);
        eddireccion=findViewById(R.id.eddireccion);
        btnregistrar=findViewById(R.id.button);


        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrDatos();
            }
        });
    }

    private void registrDatos() {

        String nombre=ednombre.getText().toString().trim();
        String correo=edcorreo.getText().toString().trim();
        String direccion=eddireccion.getText().toString().trim();

        ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("cargando");


        if (nombre.isEmpty()){
            Toast.makeText(this,"ingrese nombre",Toast.LENGTH_SHORT).show();
        }else if (correo.isEmpty()){
            Toast.makeText(this,"ingrese correo",Toast.LENGTH_SHORT).show();
        }else if (direccion.isEmpty()){
            Toast.makeText(this,"ingrese direccion",Toast.LENGTH_SHORT).show();
        }else {
            progressDialog.show();
            StringRequest request =new StringRequest(Request.Method.POST, "http://192.168.16.105/crud_android/insertar_.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("datas insertados")) {
                                Toast.makeText(Agregar.this, "registrado correctamente", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(Agregar.this, "Error no se puede registrar", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Agregar.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            ){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String>params=new HashMap<>();
                    params.put("nombre",nombre);
                    params.put("correo",correo);
                    params.put("direccion",direccion);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Agregar.this);
            requestQueue.add(request);
        }
    }
}