package edu.ifsul.trabalho2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Inicializar variável
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setar o botão
        bottomNavigationView.setSelectedItemId(R.id.navigation_settings);

        //Implementar o listener do item selecionado
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navigation_settings) {
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
    }
}