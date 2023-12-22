package edu.ifsul.trabalho2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Partidos extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ListView lista;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidos);

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        lista = findViewById(R.id.list_view);

        lista.setAdapter(adapter);

        adapter.add("Teste1");
        adapter.add("Teste2");

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelecionado = adapter.getItem( position );

                Toast.makeText(getApplicationContext(), itemSelecionado, Toast.LENGTH_SHORT).show();
            }
        });

        //Inicializar variável
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setar o botão
        bottomNavigationView.setSelectedItemId(R.id.navigation_partidos);

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
                    return true;
                }
                return false;
            }
        });
    }

}