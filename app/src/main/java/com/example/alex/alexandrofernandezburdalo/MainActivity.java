package com.example.alex.alexandrofernandezburdalo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.Request.*;
import static com.android.volley.Request.Method.*;

public class MainActivity extends AppCompatActivity {

    ListView lvPaises;

    //private static final String URL="http://restcountries.eu/rest/v2/all";
    private static final String URL = "http://192.168.31.44/Paises/paises.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPaises = findViewById(R.id.lvPaises);

        RequestQueue request = Volley.newRequestQueue(this);
        JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(GET, URL, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                String cad = "";

                ArrayList<Pais> Datos = new ArrayList<Pais>();

                try {

                    JSONObject jsonObjectPrincipal = new JSONObject(response.toString(0));

                    JSONArray JSONList = jsonObjectPrincipal.getJSONArray("list");

                    //Saco todos los datos
                    for (int i = 0; i < JSONList.length(); i++) {
                        String nombre = JSONList.getJSONObject(i).getJSONObject("main").getString("name");
                        String clave = JSONList.getJSONObject(i).getJSONObject("main").getString("alphacode2");
                        String capital = JSONList.getJSONObject(i).getJSONObject("main").getString("capital");
                        String continente = JSONList.getJSONObject(i).getJSONObject("main").getString("region");
                        String poblacion = JSONList.getJSONObject(i).getJSONObject("main").getString("population");
                        String latitud = JSONList.getJSONObject(i).getJSONObject("main").getString("latlng");
                        String longitud = JSONList.getJSONObject(i).getJSONObject("main").getString("gini");
                        String paisesfront = JSONList.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("regionalBloccs");

                        String fechaHora = JSONList.getJSONObject(i).getString("dt_txt");


                        Pais registro = new Pais(nombre, clave, capital, continente, poblacion, latitud, longitud, paisesfront);

                        //Con la linea siguiente cargo el ArrayList de forma que ya podré intentar mostrar todos los datos a través de un ListView
                        Datos.add(registro);

                    }


                    /// SACO EL LIST VIEW

                    Adaptador adaptador = new Adaptador(getApplicationContext(), Datos);
                    lvPaises.setAdapter((ListAdapter) adaptador);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });
        request.add(JsonArrayRequest);

        lvPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), "Detalles del pais", Toast.LENGTH_SHORT).show();

            }
        });


    }
}


