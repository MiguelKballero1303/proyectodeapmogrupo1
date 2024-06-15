package com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Cita.class,
                parentColumns = "id_cita",
                childColumns = "id_cita",
                onDelete = ForeignKey.CASCADE)
})
public class Factura {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_factura")
    private Integer idFactura;

    @ColumnInfo(name = "id_cita")
    private Integer idCita;

    @ColumnInfo(name = "fecha_factura")
    private String fechaFactura;

    @ColumnInfo(name = "monto_factura")
    private double montoFactura;

    @ColumnInfo(name = "estado")
    private String estado;

    public Factura() {
    }

    public Factura(Integer idFactura, Integer idCita, String fechaFactura, double montoFactura, String estado) {
        this.idFactura = idFactura;
        this.idCita = idCita;
        this.fechaFactura = fechaFactura;
        this.montoFactura = montoFactura;
        this.estado = estado;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getMontoFactura() {
        return montoFactura;
    }

    public void setMontoFactura(double montoFactura) {
        this.montoFactura = montoFactura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
