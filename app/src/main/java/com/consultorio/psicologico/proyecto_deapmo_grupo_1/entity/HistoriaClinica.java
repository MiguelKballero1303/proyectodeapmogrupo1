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
        @ForeignKey(entity = Tratamiento.class,
                parentColumns = "id_tratamiento",
                childColumns = "id_tratamiento",
                onDelete = ForeignKey.CASCADE)
})
public class HistoriaClinica {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_historia_clinica")
    private Integer idHistoriaClinica;

    @ColumnInfo(name = "id_paciente")
    private Integer idPaciente;

    @ColumnInfo(name = "id_tratamiento")
    private Integer idTratamiento;

    @ColumnInfo(name = "fecha_creacion_historia")
    private String fechaCreacionHistoria;

    @ColumnInfo(name = "diagnostico")
    private String diagnostico;

    @ColumnInfo(name = "notas_sesion")
    private String notasSesion;

    public HistoriaClinica() {
    }

    public HistoriaClinica(Integer idHistoriaClinica, Integer idPaciente, Integer idTratamiento, String fechaCreacionHistoria, String diagnostico, String notasSesion) {
        this.idHistoriaClinica = idHistoriaClinica;
        this.idPaciente = idPaciente;
        this.idTratamiento = idTratamiento;
        this.fechaCreacionHistoria = fechaCreacionHistoria;
        this.diagnostico = diagnostico;
        this.notasSesion = notasSesion;
    }

    public Integer getIdHistoriaClinica() {
        return idHistoriaClinica;
    }

    public void setIdHistoriaClinica(Integer idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Integer idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getFechaCreacionHistoria() {
        return fechaCreacionHistoria;
    }

    public void setFechaCreacionHistoria(String fechaCreacionHistoria) {
        this.fechaCreacionHistoria = fechaCreacionHistoria;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getNotasSesion() {
        return notasSesion;
    }

    public void setNotasSesion(String notasSesion) {
        this.notasSesion = notasSesion;
    }
}
