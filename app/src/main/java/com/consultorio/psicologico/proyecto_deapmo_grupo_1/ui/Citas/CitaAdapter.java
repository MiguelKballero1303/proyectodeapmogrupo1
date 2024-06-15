package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Citas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Cita;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Paciente;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Terapeuta;

import java.util.ArrayList;
import java.util.List;

// Adaptador para mostrar las citas en un RecyclerView
public class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.ViewHolder> {
    private List<Cita> list;
    private List<Paciente> pacientes;
    private List<Terapeuta> terapeutas;
    private Dialog dialog;

    // Constructor del adaptador que recibe listas de citas, pacientes y terapeutas
    public CitaAdapter(List<Cita> list, List<Paciente> pacientes, List<Terapeuta> terapeutas) {
        this.list = list;
        this.pacientes = pacientes;
        this.terapeutas = terapeutas;
    }

    // Método para establecer el diálogo que se ejecuta al hacer clic en un elemento
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    // Interfaz para definir el comportamiento del clic en un elemento
    public interface Dialog {
        void onClick(int position);
    }

    // Clase interna ViewHolder que representa cada fila del RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fechaHoraCita;
        TextView pacienteInfo;
        TextView terapeutaInfo;
        TextView estadoCita;
        TextView notasCita;

        // Constructor del ViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fechaHoraCita = itemView.findViewById(R.id.fechaHoraCita);
            pacienteInfo = itemView.findViewById(R.id.pacienteInfo);
            terapeutaInfo = itemView.findViewById(R.id.terapeutaInfo);
            estadoCita = itemView.findViewById(R.id.estadoCita);
            notasCita = itemView.findViewById(R.id.notasCita);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cita, parent, false);
        return new ViewHolder(view);
    }

    // Método para enlazar los datos de una cita a una fila del RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cita cita = list.get(position);
        holder.fechaHoraCita.setText(cita.getFechaHoraCita());

        // Obtenemos el paciente correspondiente a la cita
        Paciente paciente = getPacienteById(cita.getIdPaciente());
        String pacienteFullName = paciente.getNombrePaciente() + " " + paciente.getApellidoPaciente();
        holder.pacienteInfo.setText(pacienteFullName);

        // Obtenemos el terapeuta correspondiente a la cita
        Terapeuta terapeuta = getTerapeutaById(cita.getIdTerapeuta());
        String terapeutaInfo = terapeuta.getNombreTerapeuta() + " " + terapeuta.getApellidoTerapeuta() + " - " + terapeuta.getEspecialidadTerapeuta();
        holder.terapeutaInfo.setText(terapeutaInfo);

        holder.estadoCita.setText(cita.getEstadoCita());
        holder.notasCita.setText(cita.getNotasCita());
    }

    // Método para obtener la cantidad de elementos en la lista
    @Override
    public int getItemCount() {
        return list.size();
    }

    // Método para obtener un paciente por su ID
    private Paciente getPacienteById(int id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getIdPaciente() == id) {
                return paciente;
            }
        }
        return null;
    }

    // Método para obtener un terapeuta por su ID
    private Terapeuta getTerapeutaById(int id) {
        for (Terapeuta terapeuta : terapeutas) {
            if (terapeuta.getIdTerapeuta() == id) {
                return terapeuta;
            }
        }
        return null;
    }

    // Método para actualizar la lista de citas y notificar al adaptador
    public void updateList(List<Cita> newList) {
        list = new ArrayList<>(newList);
        notifyDataSetChanged();
    }
}
