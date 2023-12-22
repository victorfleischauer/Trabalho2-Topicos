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

import edu.ifsul.trabalho2.model.Deputado;
import edu.ifsul.trabalho2.model.DeputadoResultArray;
import edu.ifsul.trabalho2.model.PerfilDeputadoResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.squareup.picasso.Picasso;

public class PerfilDeputado extends AppCompatActivity {

    private Retrofit retrofit;

    BottomNavigationView bottomNavigationView;
    private int deputadoId;
    private Deputado deputado;
    private ImageView imagem;
    private TextView nomeText, partidoText, emailText, condicaoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_deputado);

        imagem = findViewById(R.id.imagem);
        nomeText = findViewById(R.id.nome);
        partidoText = findViewById(R.id.partido);
        emailText = findViewById(R.id.email);
        condicaoText = findViewById(R.id.condicao);

        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.apiUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        deputadoId = getIntent().getIntExtra("deputadoId", 0);

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
                    startActivity(new Intent(getApplicationContext(), Deputados.class));
                    overridePendingTransition(0,0);
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

        consultarApi();
    }

    private void consultarApi() {
        RestService restService = retrofit.create(RestService.class);

        Call<PerfilDeputadoResult> call = restService.getPerfilDeputadoResult(deputadoId);

        call.enqueue(new Callback<PerfilDeputadoResult>() {
            @Override
            public void onResponse(Call<PerfilDeputadoResult> call, Response<PerfilDeputadoResult> response) {
                if (response.isSuccessful()) {
                    deputado = response.body().getDeputado();

                    Picasso.get().load(deputado.getUltimoStatus().getUrlFoto()).into(imagem);

                    nomeText.setText("Nome: " + deputado.getNome());
                    partidoText.setText("Partido: " + deputado.getUltimoStatus().getSiglaPartido());
                    emailText.setText("Email: " + deputado.getUltimoStatus().getEmail());
                    condicaoText.setText("Condição Eleitoral: " + deputado.getUltimoStatus().getCondicaoEleitoral());
                }
            }
            @Override
            public void onFailure(Call<PerfilDeputadoResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.erro) + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("ErroAPI:", t.getMessage());
            }
        });
    }
}