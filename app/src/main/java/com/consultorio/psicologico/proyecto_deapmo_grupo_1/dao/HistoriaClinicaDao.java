package com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.HistoriaClinica;

import java.util.List;

@Dao
public interface HistoriaClinicaDao {
    @Query("SELECT * FROM historiaclinica")
    List<HistoriaClinica> getAll();

    @Query("SELECT * FROM historiaclinica WHERE id_historia_clinica IN (:historiaIds)")
    List<HistoriaClinica> loadAllByIds(int[] historiaIds);

    @Insert
    void insertAll(HistoriaClinica... historiasClinicas);

    @Delete
    void delete(HistoriaClinica historiaClinica);

    @Query("SELECT * FROM historiaclinica WHERE id_historia_clinica = :idHistoriaClinica")
    HistoriaClinica get(int idHistoriaClinica);

    @Update
    void update(HistoriaClinica historiaClinica);
}
