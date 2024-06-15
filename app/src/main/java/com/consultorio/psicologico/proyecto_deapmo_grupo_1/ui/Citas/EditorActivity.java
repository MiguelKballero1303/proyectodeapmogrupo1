package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Citas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.AppDatabase;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Cita;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Paciente;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Terapeuta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditorActivity extends AppCompatActivity {
    private EditText fechaHoraCita;
    private Spinner pacienteSpinner;
    private Spinner terapeutaSpinner;
    private Spinner estadoCitaSpinner;
    private EditText notasCita;
    private Button btnRegistrarCita;
    private Button btnFechaCita;
    private Button btnHoraCita;
    private Calendar calendar;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_cita);

        // Inicializamos los elementos de la interfaz de usuario
        fechaHoraCita = findViewById(R.id.fechaHoraCita);
        pacienteSpinner = findViewById(R.id.pacienteSpinner);
        terapeutaSpinner = findViewById(R.id.terapeutaSpinner);
        estadoCitaSpinner = findViewById(R.id.spinner_estado_cita);
        notasCita = findViewById(R.id.notasCita);
        btnRegistrarCita = findViewById(R.id.btn_registrar_cita);
        btnFechaCita = findViewById(R.id.btn_fecha_cita);
        btnHoraCita = findViewById(R.id.btn_hora_cita);

        // Obtenemos una instancia de la base de datos
        database = AppDatabase.getInstance(getApplicationContext());
        calendar = Calendar.getInstance();

        // Configuramos el botón para seleccionar la fecha
        btnFechaCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Configuramos el botón para seleccionar la hora
        btnHoraCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        // Cargamos los pacientes desde la base de datos y los agregamos al spinner
        List<Paciente> pacientes = database.pacienteDao().getAll();
        List<String> nombresPacientes = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            String nombreCompleto = paciente.getNombrePaciente() + " " + paciente.getApellidoPaciente();
            nombresPacientes.add(nombreCompleto);
        }
        ArrayAdapter<String> pacienteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresPacientes);
        pacienteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pacienteSpinner.setAdapter(pacienteAdapter);

        // Cargamos los terapeutas desde la base de datos y los agregamos al spinner
        List<Terapeuta> terapeutas = database.terapeutaDao().getAll();
        List<String> nombresTerapeutas = new ArrayList<>();
        for (Terapeuta terapeuta : terapeutas) {
            String nombreCompleto = terapeuta.getNombreTerapeuta() + " " + terapeuta.getApellidoTerapeuta() + " - " + terapeuta.getEspecialidadTerapeuta();
            nombresTerapeutas.add(nombreCompleto);
        }
        ArrayAdapter<String> terapeutaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresTerapeutas);
        terapeutaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        terapeutaSpinner.setAdapter(terapeutaAdapter);

        // Configuramos el spinner para los estados de la cita
        ArrayAdapter<CharSequence> estadoCitaAdapter = ArrayAdapter.createFromResource(
                this, R.array.estados_cita, android.R.layout.simple_spinner_item);
        estadoCitaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoCitaSpinner.setAdapter(estadoCitaAdapter);

        // Si la actividad se inició con un ID de cita, cargamos los datos de la cita para editar
        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            int citaId = intentExtras.getInt("id", 0);
            Cita cita = database.citaDao().get(citaId);

            fechaHoraCita.setText(cita.getFechaHoraCita());
            notasCita.setText(cita.getNotasCita());

            pacienteSpinner.setSelection(getIndexPaciente(pacienteSpinner, cita.getIdPaciente(), pacientes));
            terapeutaSpinner.setSelection(getIndexTerapeuta(terapeutaSpinner, cita.getIdTerapeuta(), terapeutas));

            estadoCitaSpinner.setSelection(estadoCitaAdapter.getPosition(cita.getEstadoCita()));
        }

        // Configuramos el botón para registrar o actualizar la cita
        btnRegistrarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fechaHoraCita.getText().length() > 0 && estadoCitaSpinner.getSelectedItemPosition() != -1) {
                    String nombrePaciente = (String) pacienteSpinner.getSelectedItem();
                    String nombreTerapeuta = (String) terapeutaSpinner.getSelectedItem();
                    int idPaciente = getPacienteId(nombrePaciente, pacientes);
                    int idTerapeuta = getTerapeutaId(nombreTerapeuta, terapeutas);
                    if (intentExtras != null) {
                        database.citaDao().update(new Cita(
                                intentExtras.getInt("id", 0),
                                fechaHoraCita.getText().toString(),
                                idPaciente,
                                idTerapeuta,
                                estadoCitaSpinner.getSelectedItem().toString(),
                                notasCita.getText().toString()
                        ));
                    } else {
                        database.citaDao().insertAll(new Cita(
                                null,
                                fechaHoraCita.getText().toString(),
                                idPaciente,
                                idTerapeuta,
                                estadoCitaSpinner.getSelectedItem().toString(),
                                notasCita.getText().toString()
                        ));
                    }
                    finish(); // Cerramos la actividad una vez registrada o actualizada la cita
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, complete los datos correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para mostrar el diálogo de selección de fecha
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

    // Método para mostrar el diálogo de selección de hora
    private void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                updateDateTime();
            }
        };

        new TimePickerDialog(this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    // Método para actualizar el campo de fecha y hora con la selección del usuario
    private void updateDateTime() {
        String myFormat = "dd/MM/yyyy HH:mm"; // Formato de fecha y hora
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        String fechaHora = sdf.format(calendar.getTime());
        fechaHoraCita.setText(fechaHora);
    }

    // Método para obtener el índice de un paciente en el spinner
    private int getIndexPaciente(Spinner spinner, int id, List<Paciente> pacientes) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getIdPaciente() == id) {
                return i;
            }
        }
        return 0;
    }

    // Método para obtener el índice de un terapeuta en el spinner
    private int getIndexTerapeuta(Spinner spinner, int id, List<Terapeuta> terapeutas) {
        for (int i = 0; i < terapeutas.size(); i++) {
            if (terapeutas.get(i).getIdTerapeuta() == id) {
                return i;
            }
        }
        return 0;
    }

    // Método para obtener el ID de un paciente basado en su nombre completo
    private int getPacienteId(String nombre, List<Paciente> pacientes) {
        for (Paciente paciente : pacientes) {
            if ((paciente.getNombrePaciente() + " " + paciente.getApellidoPaciente()).equals(nombre)) {
                return paciente.getIdPaciente();
            }
        }
        return 0;
    }

    // Método para obtener el ID de un terapeuta basado en su nombre completo y especialidad
    private int getTerapeutaId(String nombre, List<Terapeuta> terapeutas) {
        for (Terapeuta terapeuta : terapeutas) {
            if ((terapeuta.getNombreTerapeuta() + " " + terapeuta.getApellidoTerapeuta() + " - " + terapeuta.getEspecialidadTerapeuta()).equals(nombre)) {
                return terapeuta.getIdTerapeuta();
            }
        }
        return 0;
    }
}
