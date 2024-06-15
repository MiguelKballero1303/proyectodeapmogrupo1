package com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao.CitaDao;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao.HistoriaClinicaDao;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao.PacienteDao;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao.TerapeutaDao;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao.TratamientoDao;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao.FacturaDao;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao.UsuarioDao;

// Definimos una clase abstracta que extiende RoomDatabase y representa la base de datos de la aplicación
@Database(entities = {Paciente.class, Terapeuta.class, Cita.class, Tratamiento.class, HistoriaClinica.class, Factura.class, Usuario.class}, version = 8)
public abstract class AppDatabase extends RoomDatabase {
    // Variable estática para la instancia de la base de datos
    private static volatile AppDatabase instance;

    // Declaramos métodos abstractos para obtener los DAOs
    public abstract PacienteDao pacienteDao();
    public abstract TerapeutaDao terapeutaDao();
    public abstract CitaDao citaDao();
    public abstract TratamientoDao tratamientoDao();
    public abstract HistoriaClinicaDao historiaClinicaDao();
    public abstract FacturaDao facturaDao();
    public abstract UsuarioDao usuarioDao();

    // Método para obtener la instancia de la base de datos
    public static synchronized AppDatabase getInstance(Context context) {
        // Verificamos si la instancia es nula, si es así, la creamos
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app-database")
                    .fallbackToDestructiveMigration() // Permite migraciones destructivas
                    .allowMainThreadQueries() // Permite consultas en el hilo principal
                    .build();
        }
        // Retornamos la instancia de la base de datos
        return instance;
    }
}
