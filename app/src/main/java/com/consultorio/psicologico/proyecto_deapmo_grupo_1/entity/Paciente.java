package com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Paciente {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_paciente")
    private Integer idPaciente;

    @ColumnInfo(name = "dni_paciente")
    private String dniPaciente;

    @ColumnInfo(name = "nombre_paciente")
    private String nombrePaciente;

    @ColumnInfo(name = "apellido_paciente")
    private String apellidoPaciente;

    @ColumnInfo(name = "fecha_nacimiento_paciente")
    private String fechaNacimientoPaciente;

    @ColumnInfo(name = "direccion_paciente")
    private String direccionPaciente;

    @ColumnInfo(name = "telefono_paciente")
    private String telefonoPaciente;

    public Paciente() {
    }

    public Paciente(Integer idPaciente, String dniPaciente, String nombrePaciente, String apellidoPaciente, String fechaNacimientoPaciente, String direccionPaciente, String telefonoPaciente) {
        this.idPaciente = idPaciente;
        this.dniPaciente = dniPaciente;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.fechaNacimientoPaciente = fechaNacimientoPaciente;
        this.direccionPaciente = direccionPaciente;
        this.telefonoPaciente = telefonoPaciente;
    }


    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getFechaNacimientoPaciente() {
        return fechaNacimientoPaciente;
    }

    public void setFechaNacimientoPaciente(String fechaNacimientoPaciente) {
        this.fechaNacimientoPaciente = fechaNacimientoPaciente;
    }

    public String getDireccionPaciente() {
        return direccionPaciente;
    }

    public void setDireccionPaciente(String direccionPaciente) {
        this.direccionPaciente = direccionPaciente;
    }

    public String getTelefonoPaciente() {
        return telefonoPaciente;
    }

    public void setTelefonoPaciente(String telefonoPaciente) {
        this.telefonoPaciente = telefonoPaciente;
    }
}
