package com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    private Integer idUsuario;

    @ColumnInfo(name = "nombre_usuario")
    private String nombreUsuario;

    @ColumnInfo(name = "contrasena")
    private String contrasena;

    @ColumnInfo(name = "tipo_usuario")
    private String tipoUsuario;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String contrasena, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
