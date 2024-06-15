package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Terapeutas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Terapeuta;

import java.util.ArrayList;
import java.util.List;

public class TerapeutaAdapter extends RecyclerView.Adapter<TerapeutaAdapter.ViewHolder> {
    private List<Terapeuta> list;
    private Dialog dialog;

    public TerapeutaAdapter(List<Terapeuta> list, Dialog dialog) {
        this.list = list;
        this.dialog = dialog;
    }

    public interface Dialog {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTerapeuta;
        TextView apellidoTerapeuta;
        TextView especialidadTerapeuta;
        TextView telefonoTerapeuta;
        TextView horarioTerapeuta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTerapeuta = itemView.findViewById(R.id.nombreTerapeuta);
            apellidoTerapeuta = itemView.findViewById(R.id.apellidoTerapeuta);
            especialidadTerapeuta = itemView.findViewById(R.id.especialidadTerapeuta);
            telefonoTerapeuta = itemView.findViewById(R.id.telefonoTerapeuta);
            horarioTerapeuta = itemView.findViewById(R.id.horarioTerapeuta);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.onClick(getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_terapeuta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Terapeuta terapeuta = list.get(position);
        holder.nombreTerapeuta.setText(terapeuta.getNombreTerapeuta());
        holder.apellidoTerapeuta.setText(terapeuta.getApellidoTerapeuta());
        holder.especialidadTerapeuta.setText(terapeuta.getEspecialidadTerapeuta());
        holder.telefonoTerapeuta.setText(terapeuta.getTelefonoTerapeuta());
        holder.horarioTerapeuta.setText(terapeuta.getHorarioTerapeuta());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<Terapeuta> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}