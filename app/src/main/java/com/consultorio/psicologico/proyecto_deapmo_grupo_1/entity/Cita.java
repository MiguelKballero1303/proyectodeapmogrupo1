package com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Paciente.class,
                parentColumns = "id_paciente",
                childColumns = "id_paciente",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Terapeuta.class,
                parentColumns = "id_terapeuta",
                childColumns = "id_terapeuta",
                onDelete = ForeignKey.CASCADE)
})
public class Cita {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_cita")
    private Integer idCita;

    @ColumnInfo(name = "fecha_hora_cita")
    private String fechaHoraCita;

    @ColumnInfo(name = "id_paciente")
    private Integer idPaciente;

    @ColumnInfo(name = "id_terapeuta")
    private Integer idTerapeuta;

    @ColumnInfo(name = "estado_cita")
    private String estadoCita;

    @ColumnInfo(name = "notas_cita")
    private String notasCita;

    public Cita() {
    }

    public Cita(Integer idCita, String fechaHoraCita, Integer idPaciente, Integer idTerapeuta, String estadoCita, String notasCita) {
        this.idCita = idCita;
        this.fechaHoraCita = fechaHoraCita;
        this.idPaciente = idPaciente;
        this.idTerapeuta = idTerapeuta;
        this.estadoCita = estadoCita;
        this.notasCita = notasCita;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public String getFechaHoraCita() {
        return fechaHoraCita;
    }

    public void setFechaHoraCita(String fechaHoraCita) {
        this.fechaHoraCita = fechaHoraCita;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdTerapeuta() {
        return idTerapeuta;
    }

    public void setIdTerapeuta(Integer idTerapeuta) {
        this.idTerapeuta = idTerapeuta;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public String getNotasCita() {
        return notasCita;
    }

    public void setNotasCita(String notasCita) {
        this.notasCita = notasCita;
    }
}
