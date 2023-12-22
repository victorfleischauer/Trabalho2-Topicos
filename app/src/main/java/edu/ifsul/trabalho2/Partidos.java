package edu.ifsul.trabalho2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.ifsul.trabalho2.model.Partido;
import edu.ifsul.trabalho2.model.PartidoResultArray;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Partidos extends AppCompatActivity {

    private Retrofit retrofit;

    BottomNavigationView bottomNavigationView;
    private ListView lista;
    private ArrayAdapter<String> adapter;

    private List<Partido> listaPartidosApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidos);

        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.apiUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        lista = findViewById(R.id.list_view);

        lista.setAdapter(adapter);

        consultarApi();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Partido partidoSelecionado = listaPartidosApi.get(position);

                Intent i = new Intent(getApplicationContext(), PerfilPartido.class);
                i.putExtra("partidoId", partidoSelecionado.getId());
                startActivity(i);
                finish();
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigation_partidos);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.navigation_settings) {
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if (item.getItemId() == R.id.navigation_deputados) {
                    startActivity(new Intent(getApplicationContext(), Deputados.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if (item.getItemId() == R.id.navigation_partidos) {
                    return true;
                }
                return false;
            }
        });
    }

    private void consultarApi() {
        RestService restService = retrofit.create(RestService.class);

        Call<PartidoResultArray> call = restService.getPartidoResultArray();

        call.enqueue(new Callback<PartidoResultArray>() {
            @Override
            public void onResponse(Call<PartidoResultArray> call, Response<PartidoResultArray> response) {
                if (response.isSuccessful()) {
                    listaPartidosApi = response.body().getPartidos();

                    for(Partido partido : listaPartidosApi) {
                        adapter.add(partido.getSigla() + " - " + partido.getNome());
                    }
                }
            }
            @Override
            public void onFailure(Call<PartidoResultArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.erro) + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("ErroAPI:", t.getMessage());
            }
        });
    }

}