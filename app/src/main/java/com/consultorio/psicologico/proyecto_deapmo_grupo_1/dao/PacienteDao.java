package com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Paciente;

import java.util.List;

@Dao
public interface PacienteDao {
    @Query("SELECT * FROM paciente")
    List<Paciente> getAll();
    @Query("SELECT * FROM paciente WHERE id_paciente IN (:pacienteIds)")
    List<Paciente> loadAllByIds(int[] pacienteIds);
    @Insert
    void insertAll(Paciente... pacientes);
    @Delete
    void delete(Paciente paciente);
    @Query("SELECT * FROM paciente WHERE id_paciente = :idPaciente")
    Paciente get(int idPaciente);
    @Update
    void update(Paciente paciente);
}

