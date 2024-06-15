package com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();
    @Query("SELECT * FROM usuario WHERE id_usuario IN (:usuarioIds)")
    List<Usuario> loadAllByIds(int[] usuarioIds);
    @Insert
    void insertAll(Usuario... usuarios);
    @Delete
    void delete(Usuario usuario);
    @Query("SELECT * FROM usuario WHERE nombre_usuario = :nombreUsuario")
    Usuario getByNombreUsuario(String nombreUsuario);
    @Update
    void update(Usuario usuario);

    @Query("SELECT * FROM usuario WHERE id_usuario = :usuarioId")
    Usuario get(int usuarioId);

}
