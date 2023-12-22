package edu.ifsul.trabalho2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.List;

import edu.ifsul.trabalho2.model.Deputado;
import edu.ifsul.trabalho2.model.DeputadoResultArray;
import edu.ifsul.trabalho2.model.Partido;
import edu.ifsul.trabalho2.model.PartidoResultArray;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Deputados extends AppCompatActivity {

    private Retrofit retrofit;

    BottomNavigationView bottomNavigationView;
    private ListView lista;
    private ArrayAdapter<String> adapter;

    private List<Deputado> listaDeputadosApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputados);

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
                Deputado partidoSelecionado = listaDeputadosApi.get(position);

                Intent i = new Intent(getApplicationContext(), PerfilDeputado.class);
                i.putExtra("deputadoId", partidoSelecionado.getId());
                startActivity(i);
                finish();
            }
        });

        //Inicializar variável
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setar o botão
        bottomNavigationView.setSelectedItemId(R.id.navigation_deputados);

        //Implementar o listener do item selecionado
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navigation_settings) {
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if (item.getItemId() == R.id.navigation_deputados) {
                    return true;
                }
                else if (item.getItemId() == R.id.navigation_partidos) {
                    startActivity(new Intent(getApplicationContext(), Partidos.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });
    }

    private void consultarApi() {
        RestService restService = retrofit.create(RestService.class);

        Call<DeputadoResultArray> call = restService.getDeputadoResultArray();

        call.enqueue(new Callback<DeputadoResultArray>() {
            @Override
            public void onResponse(Call<DeputadoResultArray> call, Response<DeputadoResultArray> response) {
                if (response.isSuccessful()) {
                    listaDeputadosApi = response.body().getDeputadoList();

                    for(Deputado deputado : listaDeputadosApi) {
                        adapter.add(deputado.getNome() + " - " + deputado.getSiglaPartido());
                    }
                }
            }
            @Override
            public void onFailure(Call<DeputadoResultArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.erro) + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("ErroAPI:", t.getMessage());
            }
        });
    }
}