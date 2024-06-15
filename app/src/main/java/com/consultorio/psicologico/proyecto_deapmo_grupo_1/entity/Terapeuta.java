package com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Terapeuta {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_terapeuta")
    private Integer idTerapeuta;

    @ColumnInfo(name = "nombre_terapeuta")
    private String nombreTerapeuta;

    @ColumnInfo(name = "apellido_terapeuta")
    private String apellidoTerapeuta;
    @ColumnInfo(name = "especialidad_terapeuta")
    private String especialidadTerapeuta;
    @ColumnInfo(name = "telefono_terapeuta")
    private String telefonoTerapeuta;
    @ColumnInfo(name = "horario_terapeuta")
    private String horarioTerapeuta;

    public Terapeuta() {
    }

    public Terapeuta(Integer idTerapeuta, String nombreTerapeuta, String apellidoTerapeuta, String especialidadTerapeuta, String telefonoTerapeuta, String horarioTerapeuta) {
        this.idTerapeuta = idTerapeuta;
        this.nombreTerapeuta = nombreTerapeuta;
        this.apellidoTerapeuta = apellidoTerapeuta;
        this.especialidadTerapeuta = especialidadTerapeuta;
        this.telefonoTerapeuta = telefonoTerapeuta;
        this.horarioTerapeuta = horarioTerapeuta;
    }

    public Integer getIdTerapeuta() {
        return idTerapeuta;
    }

    public void setIdTerapeuta(Integer idTerapeuta) {
        this.idTerapeuta = idTerapeuta;
    }

    public String getNombreTerapeuta() {
        return nombreTerapeuta;
    }

    public void setNombreTerapeuta(String nombreTerapeuta) {
        this.nombreTerapeuta = nombreTerapeuta;
    }

    public String getApellidoTerapeuta() {
        return apellidoTerapeuta;
    }

    public void setApellidoTerapeuta(String apellidoTerapeuta) {
        this.apellidoTerapeuta = apellidoTerapeuta;
    }

    public String getEspecialidadTerapeuta() {
        return especialidadTerapeuta;
    }

    public void setEspecialidadTerapeuta(String especialidadTerapeuta) {
        this.especialidadTerapeuta = especialidadTerapeuta;
    }

    public String getTelefonoTerapeuta() {
        return telefonoTerapeuta;
    }

    public void setTelefonoTerapeuta(String telefonoTerapeuta) {
        this.telefonoTerapeuta = telefonoTerapeuta;
    }

    public String getHorarioTerapeuta() {
        return horarioTerapeuta;
    }

    public void setHorarioTerapeuta(String horarioTerapeuta) {
        this.horarioTerapeuta = horarioTerapeuta;
    }
}
