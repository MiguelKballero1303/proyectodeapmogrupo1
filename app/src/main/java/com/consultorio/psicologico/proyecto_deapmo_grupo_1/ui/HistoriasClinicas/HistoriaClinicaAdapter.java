package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.HistoriasClinicas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.HistoriaClinica;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Paciente;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Tratamiento;

import java.util.ArrayList;
import java.util.List;

public class HistoriaClinicaAdapter extends RecyclerView.Adapter<HistoriaClinicaAdapter.ViewHolder> {
    private List<HistoriaClinica> list;
    private List<Paciente> pacientes;
    private List<Tratamiento> tratamientos;
    private Dialog dialog;

    public HistoriaClinicaAdapter(List<HistoriaClinica> list, List<Paciente> pacientes, List<Tratamiento> tratamientos, Dialog dialog) {
        this.list = list;
        this.pacientes = pacientes;
        this.tratamientos = tratamientos;
        this.dialog = dialog;
    }

    public interface Dialog {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fechaCreacionHistoria;
        TextView pacienteInfo;
        TextView tratamientoInfo;
        TextView diagnostico;
        TextView notasSesion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fechaCreacionHistoria = itemView.findViewById(R.id.fechaCreacionHistoria);
            pacienteInfo = itemView.findViewById(R.id.pacienteInfo);
            tratamientoInfo = itemView.findViewById(R.id.tratamientoInfo);
            diagnostico = itemView.findViewById(R.id.diagnostico);
            notasSesion = itemView.findViewById(R.id.notasSesion);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_historiaclinica, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoriaClinica historiaClinica = list.get(position);
        holder.fechaCreacionHistoria.setText(historiaClinica.getFechaCreacionHistoria());

        Paciente paciente = getPacienteById(historiaClinica.getIdPaciente());
        if (paciente != null) {
            String pacienteFullName = paciente.getNombrePaciente() + " " + paciente.getApellidoPaciente();
            holder.pacienteInfo.setText(pacienteFullName);
        }

        Tratamiento tratamiento = getTratamientoById(historiaClinica.getIdTratamiento());
        if (tratamiento != null) {
            holder.tratamientoInfo.setText(tratamiento.getNombreTratamiento());
        }

        holder.diagnostico.setText(historiaClinica.getDiagnostico());
        holder.notasSesion.setText(historiaClinica.getNotasSesion());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private Paciente getPacienteById(int id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getIdPaciente() == id) {
                return paciente;
            }
        }
        return null;
    }

    private Tratamiento getTratamientoById(int id) {
        for (Tratamiento tratamiento : tratamientos) {
            if (tratamiento.getIdTratamiento() == id) {
                return tratamiento;
            }
        }
        return null;
    }

    public void updateList(List<HistoriaClinica> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}