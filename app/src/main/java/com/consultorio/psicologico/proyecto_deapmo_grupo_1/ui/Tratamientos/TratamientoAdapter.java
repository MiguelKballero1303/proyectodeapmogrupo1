package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Tratamientos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Tratamiento;

import java.util.ArrayList;
import java.util.List;

public class TratamientoAdapter extends RecyclerView.Adapter<TratamientoAdapter.ViewHolder> {
    private List<Tratamiento> list;
    private Dialog dialog;

    public TratamientoAdapter(List<Tratamiento> list, Dialog dialog) {
        this.list = list;
        this.dialog = dialog;
    }

    public interface Dialog {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTratamiento;
        TextView descripcionTratamiento;
        TextView duracionEstimada;
        TextView costo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTratamiento = itemView.findViewById(R.id.nombreTratamiento);
            descripcionTratamiento = itemView.findViewById(R.id.descripcionTratamiento);
            duracionEstimada = itemView.findViewById(R.id.duracionEstimada);
            costo = itemView.findViewById(R.id.costo);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tratamiento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tratamiento tratamiento = list.get(position);
        holder.nombreTratamiento.setText(tratamiento.getNombreTratamiento());
        holder.descripcionTratamiento.setText(tratamiento.getDescripcionTratamiento());
        holder.duracionEstimada.setText(tratamiento.getDuracionEstimada());
        holder.costo.setText(String.valueOf(tratamiento.getCosto()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<Tratamiento> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}
