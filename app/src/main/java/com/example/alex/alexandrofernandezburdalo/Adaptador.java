package com.example.alex.alexandrofernandezburdalo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alex on 21/02/2018.
 */

public class Adaptador {
    Context contexto; //contexto de la aplicacion
    ArrayList<Pais> Datos;

    public Adaptador(Context contexto, ArrayList<Pais> datos) {
        this.contexto = contexto;
        Datos = datos;
    }


    public int getCount() {
        return Datos.size();
    }


    public Object getItem(int i) { //Devuelve el objeto de la lista en la posición indicada como parametro
        return Datos.get(i);
    }


    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) { //Es el método que se ejecuta cuando se muestra en mi ListView cada item
        View vista = view;
        LayoutInflater inflate = LayoutInflater.from(contexto); //Obtenemos el contexto del item sobre el cual estamos trabajando del ListView
        vista = inflate.inflate(R.layout.itemlistview_pais, null); //Consigo referenciar a la vista en sí

        TextView tvNombre, tvNombre2, tvClave, tvCapital, tvcontinente, tvPoblacion,tvLatitud,tvLongitud,tvPfronterizoa;
        tvNombre = vista.findViewById(R.id.tvNombre);
        tvNombre2 = vista.findViewById(R.id.tvnombre2);
        tvClave = vista.findViewById(R.id.tvClave);
        tvCapital = vista.findViewById(R.id.tvCapital);
        tvcontinente = vista.findViewById(R.id.tvContinente);
        tvPoblacion = vista.findViewById(R.id.tvPoblacion);
        tvLatitud =  vista.findViewById(R.id.tvLatitud);
        tvLongitud = vista.findViewById(R.id.tvLongitud);
        tvPfronterizoa = vista.findViewById(R.id.tvPfronterizos);


        // IMPRIMO LOS VALORES DEL ARRAY LIST

        tvNombre.setText("Nombre: " + Datos.get(i).getNombre().toString());
        tvNombre2.setText("Nombre2: " + Datos.get(i).getNombre2().toString());
        tvClave.setText("Clave: " + Datos.get(i).getAlpha2Code().toString());
        tvCapital.setText("Capital: " + Datos.get(i).getCapital().toString());
        tvcontinente.setText("continente: " + Datos.get(i).getContinente().toString());
        tvPoblacion.setText("Poblacion: " + Datos.get(i).getPoblacion().toString());
        tvLatitud.setText("Latitud: " + Datos.get(i).getLatitud().toString());
        tvLongitud.setText("Longitud: " + Datos.get(i).getLongitud().toString());
        tvPfronterizoa.setText("Paises Fronterizos: " + Datos.get(i).getPaisesfront().toString());

        return vista;
    }
}
