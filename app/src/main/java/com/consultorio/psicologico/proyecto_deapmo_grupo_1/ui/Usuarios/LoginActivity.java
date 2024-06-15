package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Usuarios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.MainActivity;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.AppDatabase;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private EditText nombreUsuarioEditText;
    private EditText contrasenaEditText;
    private Button btnLogin;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nombreUsuarioEditText = findViewById(R.id.nombreUsuarioEditText);
        contrasenaEditText = findViewById(R.id.contrasenaEditText);
        btnLogin = findViewById(R.id.btnLogin);
        database = AppDatabase.getInstance(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario = nombreUsuarioEditText.getText().toString();
                String contrasena = contrasenaEditText.getText().toString();
                if (validateLogin(nombreUsuario, contrasena)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateLogin(String nombreUsuario, String contrasena) {
        Usuario usuario = database.usuarioDao().getByNombreUsuario(nombreUsuario);
        if (usuario != null) {
            String encryptedPassword = encryptPassword(contrasena);
            return usuario.getContrasena().equals(encryptedPassword);
        }
        return false;
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] byteData = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
