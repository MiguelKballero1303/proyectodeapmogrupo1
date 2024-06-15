package com.consultorio.psicologico.proyecto_deapmo_grupo_1;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration; // Configuración de la barra de la aplicación
    private ActivityMainBinding binding; // Binding para el layout de la actividad

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el layout de la actividad
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar la barra de herramientas
        setSupportActionBar(binding.appBarMain.toolbar);

        // Obtener referencias al DrawerLayout y al NavigationView
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Construir la configuración de la barra de la aplicación con los destinos de navegación
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_citas, R.id.nav_pacientes, R.id.nav_terapeutas, R.id.nav_tratamientos, R.id.nav_historiasclinicas, R.id.nav_facturas)
                .setOpenableLayout(drawer)
                .build();

        // Configurar el NavController para la navegación
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // Configurar la barra de acción para que muestre el botón de navegación
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        // Configurar la navegación del NavigationView
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú de opciones en la barra de herramientas
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Manejar la navegación hacia atrás cuando se presiona el botón de navegación en la barra de herramientas
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
