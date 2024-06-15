package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Pacientes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.ViewHolder> {
    private List<Paciente> list;
    private Dialog dialog;

    public PacienteAdapter(List<Paciente> list, Dialog dialog) {
        this.list = list;
        this.dialog = dialog;
    }

    public interface Dialog {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dniPaciente;
        TextView nombrePaciente;
        TextView apellidoPaciente;
        TextView fechaNacimientoPaciente;
        TextView direccionPaciente;
        TextView telefonoPaciente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dniPaciente = itemView.findViewById(R.id.dniPaciente);
            nombrePaciente = itemView.findViewById(R.id.nombrePaciente);
            apellidoPaciente = itemView.findViewById(R.id.apellidoPaciente);
            fechaNacimientoPaciente = itemView.findViewById(R.id.fechaNacimientoPaciente);
            direccionPaciente = itemView.findViewById(R.id.direccionPaciente);
            telefonoPaciente = itemView.findViewById(R.id.telefonoPaciente);

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_paciente, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Paciente paciente = list.get(position);
        holder.dniPaciente.setText(paciente.getDniPaciente());
        holder.nombrePaciente.setText(paciente.getNombrePaciente());
        holder.apellidoPaciente.setText(paciente.getApellidoPaciente());
        holder.fechaNacimientoPaciente.setText(paciente.getFechaNacimientoPaciente());
        holder.direccionPaciente.setText(paciente.getDireccionPaciente());
        holder.telefonoPaciente.setText(paciente.getTelefonoPaciente());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<Paciente> newList) {
        list = new ArrayList<>(newList);
        notifyDataSetChanged();
    }
}
