package com.guerrero.crud_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Detalles extends AppCompatActivity {

    TextView ednombre,edcorreo,eddireccion;
    Button btnregistrar;
    private  int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);


        ednombre=findViewById(R.id.ednombre);
        edcorreo=findViewById(R.id.edcorreo);
        eddireccion=findViewById(R.id.eddireccion);



        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");


        ednombre.setText(MainActivity.usuariosArrayList.get(position).getNombre());
        edcorreo.setText(MainActivity.usuariosArrayList.get(position).getCorreo());
        eddireccion.setText(MainActivity.usuariosArrayList.get(position).getDireccion());
}
}