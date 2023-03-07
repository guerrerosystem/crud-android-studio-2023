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

public class Editar extends AppCompatActivity {

    EditText ednombre,edcorreo,eddireccion,edid;
    Button btnregistrar;
    private  int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);


        edid=findViewById(R.id.ids);
        ednombre=findViewById(R.id.ednombre);
        edcorreo=findViewById(R.id.edcorreo);
        eddireccion=findViewById(R.id.eddireccion);
        btnregistrar=findViewById(R.id.btneditar);


  Intent intent=getIntent();
  position=intent.getExtras().getInt("position");

  edid.setText(MainActivity.usuariosArrayList.get(position).getId());
  ednombre.setText(MainActivity.usuariosArrayList.get(position).getNombre());
  edcorreo.setText(MainActivity.usuariosArrayList.get(position).getCorreo());
  eddireccion.setText(MainActivity.usuariosArrayList.get(position).getDireccion());


        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });
    }

    private void Actualizar() {

        String id=edid.getText().toString().trim();
        String nombre=ednombre.getText().toString().trim();
        String correo=edcorreo.getText().toString().trim();
        String direccion=eddireccion.getText().toString().trim();

        ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Actualizando");


        if (nombre.isEmpty()){
            Toast.makeText(this,"ingrese nombre",Toast.LENGTH_SHORT).show();
        }else if (correo.isEmpty()){
            Toast.makeText(this,"ingrese correo",Toast.LENGTH_SHORT).show();
        }else if (direccion.isEmpty()){
            Toast.makeText(this,"ingrese direccion",Toast.LENGTH_SHORT).show();
        }else {
            progressDialog.show();
            StringRequest request =new StringRequest(Request.Method.POST, "http://192.168.16.105/crud_android/actualizar_.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                                Toast.makeText(Editar.this, "actualizo correctamente", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Editar.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            ){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String>params=new HashMap<>();
                    params.put("id",id);
                    params.put("nombre",nombre);
                    params.put("correo",correo);
                    params.put("direccion",direccion);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Editar.this);
            requestQueue.add(request);
        }
    }
}