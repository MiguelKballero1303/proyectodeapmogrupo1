package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Usuarios;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.AppDatabase;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EditorActivity extends AppCompatActivity {

    private EditText nombreUsuarioEditText;
    private EditText contrasenaEditText;
    private EditText tipoUsuarioEditText;
    private Button btnRegistrarUsuario;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_usuario);

        nombreUsuarioEditText = findViewById(R.id.nombreUsuarioEditText);
        contrasenaEditText = findViewById(R.id.contrasenaEditText);
        tipoUsuarioEditText = findViewById(R.id.tipoUsuarioEditText);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        database = AppDatabase.getInstance(getApplicationContext());

        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            int usuarioId = intentExtras.getInt("id", 0);
            Usuario usuario = database.usuarioDao().get(usuarioId);

            nombreUsuarioEditText.setText(usuario.getNombreUsuario());
            contrasenaEditText.setText("********");
            tipoUsuarioEditText.setText(usuario.getTipoUsuario());
        }

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreUsuarioEditText.getText().length() > 0 && contrasenaEditText.getText().length() > 0 && tipoUsuarioEditText.getText().length() > 0) {
                    String encryptedPassword = encryptPassword(contrasenaEditText.getText().toString());
                    if (intentExtras != null) {
                        database.usuarioDao().update(new Usuario(
                                intentExtras.getInt("id", 0),
                                nombreUsuarioEditText.getText().toString(),
                                encryptedPassword,
                                tipoUsuarioEditText.getText().toString()
                        ));
                    } else {
                        database.usuarioDao().insertAll(new Usuario(
                                null,
                                nombreUsuarioEditText.getText().toString(),
                                encryptedPassword,
                                tipoUsuarioEditText.getText().toString()
                        ));
                    }
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, complete los datos correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
