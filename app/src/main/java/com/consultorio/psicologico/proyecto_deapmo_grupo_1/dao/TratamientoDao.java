package com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Tratamiento;

import java.util.List;

@Dao
public interface TratamientoDao {
    @Query("SELECT * FROM tratamiento")
    List<Tratamiento> getAll();

    @Query("SELECT * FROM tratamiento WHERE id_tratamiento IN (:tratamientoIds)")
    List<Tratamiento> loadAllByIds(int[] tratamientoIds);

    @Insert
    void insertAll(Tratamiento... tratamientos);

    @Delete
    void delete(Tratamiento tratamiento);

    @Query("SELECT * FROM tratamiento WHERE id_tratamiento = :idTratamiento")
    Tratamiento get(int idTratamiento);

    @Update
    void update(Tratamiento tratamiento);
}
