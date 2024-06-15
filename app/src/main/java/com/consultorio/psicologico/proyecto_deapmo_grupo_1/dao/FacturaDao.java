package com.consultorio.psicologico.proyecto_deapmo_grupo_1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Factura;

import java.util.List;

@Dao
public interface FacturaDao {
    @Query("SELECT * FROM factura")
    List<Factura> getAll();

    @Query("SELECT * FROM factura WHERE id_factura IN (:facturaIds)")
    List<Factura> loadAllByIds(int[] facturaIds);
    @Insert
    void insertAll(Factura... facturas);
    @Delete
    void delete(Factura factura);
    @Query("SELECT * FROM factura WHERE id_factura = :idFactura")
    Factura get(int idFactura);
    @Update
    void update(Factura factura);
}
