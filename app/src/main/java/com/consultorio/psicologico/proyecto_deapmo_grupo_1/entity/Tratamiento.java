package com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tratamiento {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_tratamiento")
    private Integer idTratamiento;

    @ColumnInfo(name = "nombre_tratamiento")
    private String nombreTratamiento;

    @ColumnInfo(name = "descripcion_tratamiento")
    private String descripcionTratamiento;

    @ColumnInfo(name = "duracion_estimada")
    private String duracionEstimada;

    @ColumnInfo(name = "costo")
    private double costo;

    public Tratamiento() {
    }

    public Tratamiento(Integer idTratamiento, String nombreTratamiento, String descripcionTratamiento, String duracionEstimada, double costo) {
        this.idTratamiento = idTratamiento;
        this.nombreTratamiento = nombreTratamiento;
        this.descripcionTratamiento = descripcionTratamiento;
        this.duracionEstimada = duracionEstimada;
        this.costo = costo;
    }

    public Integer getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Integer idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getNombreTratamiento() {
        return nombreTratamiento;
    }

    public void setNombreTratamiento(String nombreTratamiento) {
        this.nombreTratamiento = nombreTratamiento;
    }

    public String getDescripcionTratamiento() {
        return descripcionTratamiento;
    }

    public void setDescripcionTratamiento(String descripcionTratamiento) {
        this.descripcionTratamiento = descripcionTratamiento;
    }

    public String getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(String duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
