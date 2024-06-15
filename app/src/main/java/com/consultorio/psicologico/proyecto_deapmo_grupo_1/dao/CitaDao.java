package com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao;

// Importaciones necesarias para trabajar con Room (una biblioteca de persistencia en Android)
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Cita;

import java.util.List;

// Definimos una interfaz para el acceso a datos (DAO) para la entidad "Cita"
@Dao
public interface CitaDao {
    // Método para obtener todas las citas
    @Query("SELECT * FROM cita")
    List<Cita> getAll();

    // Método para obtener citas específicas por sus IDs
    @Query("SELECT * FROM cita WHERE id_cita IN (:citaIds)")
    List<Cita> loadAllByIds(int[] citaIds);

    // Método para insertar varias citas a la vez en la base de datos
    @Insert
    void insertAll(Cita... citas);

    // Método para eliminar una cita específica de la base de datos
    @Delete
    void delete(Cita cita);

    // Método para obtener una cita específica por su ID
    @Query("SELECT * FROM cita WHERE id_cita = :idCita")
    Cita get(int idCita);

    // Método para actualizar una cita específica en la base de datos
    @Update
    void update(Cita cita);
}
