package com.guerrero.crud_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Adapter  extends ArrayAdapter<Usuarios> {


    Context context;
    List<Usuarios>usuariosList;

    public Adapter(@NonNull Context context, List<Usuarios>UsuariosList) {
        super(context,R.layout.list_usuarios,UsuariosList);
        this.context=context;
        this.usuariosList=UsuariosList;
    }

    public View getView( int position, @NonNull View context, ViewGroup resource) {
       View view= LayoutInflater.from(resource.getContext()).inflate(R.layout.list_usuarios,null,true);
        TextView tvid=view.findViewById(R.id.txt_id);
        TextView tvnombre=view.findViewById(R.id.txt_name);

        tvid.setText(usuariosList.get(position).getId());
        tvnombre.setText(usuariosList.get(position).getNombre());

        return view;
    }




}
