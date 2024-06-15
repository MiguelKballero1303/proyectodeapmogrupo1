package com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Terapeuta;

import java.util.List;

@Dao
public interface TerapeutaDao {
    @Query("SELECT * FROM terapeuta")
    List<Terapeuta> getAll();

    @Query("SELECT * FROM terapeuta WHERE id_terapeuta IN (:terapeutaIds)")
    List<Terapeuta> loadAllByIds(int[] terapeutaIds);

    @Insert
    void insertAll(Terapeuta... terapeutas);

    @Delete
    void delete(Terapeuta terapeuta);
    @Query("SELECT * FROM terapeuta WHERE id_terapeuta = :idTerapeuta")
    Terapeuta get(int idTerapeuta);
    @Update
    void update(Terapeuta terapeuta);
}
