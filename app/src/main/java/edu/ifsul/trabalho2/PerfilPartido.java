package edu.ifsul.trabalho2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import edu.ifsul.trabalho2.model.Partido;
import edu.ifsul.trabalho2.model.PerfilDeputadoResult;
import edu.ifsul.trabalho2.model.PerfilPartidoResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilPartido extends AppCompatActivity {

    private Retrofit retrofit;

    BottomNavigationView bottomNavigationView;

    private Long partidoId;
    private Partido partido;

    private ImageView imagem;
    private TextView nome, sigla, membros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_partido);

        imagem = findViewById(R.id.imagem);
        nome = findViewById(R.id.nome);
        sigla = findViewById(R.id.sigla);
        membros = findViewById(R.id.membros);

        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.apiUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        partidoId = getIntent().getLongExtra("partidoId", 0);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigation_partidos);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_settings) {
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.navigation_deputados) {
                    startActivity(new Intent(getApplicationContext(), Deputados.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.navigation_partidos) {
                    startActivity(new Intent(getApplicationContext(), Partidos.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        consultarApi();
    }

        private void consultarApi() {
            RestService restService = retrofit.create(RestService.class);

            Call<PerfilPartidoResult> call = restService.getPerfilPartidoResult(partidoId);

            call.enqueue(new Callback<PerfilPartidoResult>() {
                @Override
                public void onResponse(Call<PerfilPartidoResult> call, Response<PerfilPartidoResult> response) {
                    if (response.isSuccessful()) {
                        partido = response.body().getPartido();

                        Picasso.get().load(partido.getUrlLogo()).into(imagem);

                        nome.setText("Nome: " + partido.getNome());
                        sigla.setText("Sigla: " + partido.getSigla());
                        membros.setText("Total Membros: " + partido.getStatus().getTotalMembros().toString());
                    }
                }
                @Override
                public void onFailure(Call<PerfilPartidoResult> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), getString(R.string.erro) + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("ErroAPI:", t.getMessage());
                }
            });
        }
}