package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Facturas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Factura;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Cita;

import java.util.ArrayList;
import java.util.List;

public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.ViewHolder> {
    private List<Factura> list;
    private List<Cita> citas;
    private Dialog dialog;
    public interface Dialog {
        void onClick(int position);
    }

    public FacturaAdapter(List<Factura> list, List<Cita> citas) {
        this.list = list;
        this.citas = citas;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fechaFactura;
        TextView montoFactura;
        TextView estadoFactura;
        TextView citaInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fechaFactura = itemView.findViewById(R.id.fechaFactura);
            montoFactura = itemView.findViewById(R.id.montoFactura);
            estadoFactura = itemView.findViewById(R.id.estadoFactura);
            citaInfo = itemView.findViewById(R.id.citaInfo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) {
                        dialog.onClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_factura, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Factura factura = list.get(position);
        holder.fechaFactura.setText(factura.getFechaFactura());
        holder.montoFactura.setText(String.valueOf(factura.getMontoFactura()));
        holder.estadoFactura.setText(factura.getEstado());

        Cita cita = getCitaById(factura.getIdCita());
        if (cita != null) {
            holder.citaInfo.setText("ID Cita: " + cita.getIdCita() + " - Fecha Cita: " + cita.getFechaHoraCita());
        } else {
            holder.citaInfo.setText("Cita no encontrada");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private Cita getCitaById(int id) {
        for (Cita cita : citas) {
            if (cita.getIdCita() == id) {
                return cita;
            }
        }
        return null;
    }

    public void updateList(List<Factura> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}
