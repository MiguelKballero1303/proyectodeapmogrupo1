package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Facturas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.AppDatabase;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Cita;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Factura;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditorActivity extends AppCompatActivity {
    private EditText fechaFacturaEditText;
    private EditText montoFacturaEditText;
    private Spinner citaSpinner;
    private Spinner estadoSpinner;
    private Button btnRegistrarFactura;
    private Button btnFechaFactura;
    private Calendar calendar;
    private AppDatabase database;
    private int facturaId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_factura);

        fechaFacturaEditText = findViewById(R.id.fechaFacturaEditText);
        montoFacturaEditText = findViewById(R.id.montoFacturaEditText);
        citaSpinner = findViewById(R.id.citaSpinner);
        estadoSpinner = findViewById(R.id.estadoSpinner);
        btnRegistrarFactura = findViewById(R.id.btn_registrar_factura);
        btnFechaFactura = findViewById(R.id.btn_fecha_factura);

        database = AppDatabase.getInstance(getApplicationContext());
        calendar = Calendar.getInstance();
        btnFechaFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        List<Cita> citas = database.citaDao().getAll();
        List<String> listaCitas = new ArrayList<>();
        for (Cita cita : citas) {
            listaCitas.add("ID: " + cita.getIdCita() + " - Fecha: " + cita.getFechaHoraCita());
        }
        ArrayAdapter<String> citaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCitas);
        citaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citaSpinner.setAdapter(citaAdapter);

        ArrayAdapter<CharSequence> estadoAdapter = ArrayAdapter.createFromResource(
                this, R.array.estados_factura, android.R.layout.simple_spinner_item);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoSpinner.setAdapter(estadoAdapter);

        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            facturaId = intentExtras.getInt("id", -1);
            if (facturaId != -1) {
                Factura factura = database.facturaDao().get(facturaId);
                if (factura != null) {
                    fechaFacturaEditText.setText(factura.getFechaFactura());
                    montoFacturaEditText.setText(String.valueOf(factura.getMontoFactura()));
                    citaSpinner.setSelection(getCitaIndexById(citas, factura.getIdCita()));
                    estadoSpinner.setSelection(getEstadoIndex(factura.getEstado()));
                }
            }
        }

        btnRegistrarFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    String fechaFactura = fechaFacturaEditText.getText().toString();
                    double montoFactura = Double.parseDouble(montoFacturaEditText.getText().toString());
                    String estadoFactura = estadoSpinner.getSelectedItem().toString();

                    int citaIndex = citaSpinner.getSelectedItemPosition();
                    int idCita = citas.get(citaIndex).getIdCita();

                    if (facturaId != -1) {
                        database.facturaDao().update(new Factura(
                                facturaId,
                                idCita,
                                fechaFactura,
                                montoFactura,
                                estadoFactura
                        ));
                        Toast.makeText(getApplicationContext(), "Factura actualizada correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        database.facturaDao().insertAll(new Factura(
                                null,
                                idCita,
                                fechaFactura,
                                montoFactura,
                                estadoFactura
                        ));
                        Toast.makeText(getApplicationContext(), "Factura registrada correctamente", Toast.LENGTH_SHORT).show();
                    }

                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, complete los datos correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validarCampos() {
        return !fechaFacturaEditText.getText().toString().isEmpty() &&
                !montoFacturaEditText.getText().toString().isEmpty();
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateTime();
            }
        };

        new DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateDateTime() {
        String myFormat = "dd/MM/yyyy"; // Formato de fecha
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        String fechaFactura = sdf.format(calendar.getTime());
        fechaFacturaEditText.setText(fechaFactura);
    }

    private int getCitaIndexById(List<Cita> citas, int idCita) {
        for (int i = 0; i < citas.size(); i++) {
            if (citas.get(i).getIdCita() == idCita) {
                return i;
            }
        }
        return -1;
    }

    private int getEstadoIndex(String estado) {
        String[] estados = getResources().getStringArray(R.array.estados_factura);
        for (int i = 0; i < estados.length; i++) {
            if (estados[i].equals(estado)) {
                return i;
            }
        }
        return -1;
    }
}
