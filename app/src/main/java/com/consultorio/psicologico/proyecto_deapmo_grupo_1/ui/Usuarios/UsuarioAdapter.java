package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Usuarios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {
    private List<Usuario> list;
    private Dialog dialog;

    public UsuarioAdapter(List<Usuario> list, Dialog dialog) {
        this.list = list;
        this.dialog = dialog;
    }

    public interface Dialog {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreUsuario;
        TextView tipoUsuario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreUsuario = itemView.findViewById(R.id.nombreUsuario);
            tipoUsuario = itemView.findViewById(R.id.tipoUsuario);

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_usuario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario usuario = list.get(position);
        holder.nombreUsuario.setText(usuario.getNombreUsuario());
        holder.tipoUsuario.setText(usuario.getTipoUsuario());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<Usuario> newList) {
        list = new ArrayList<>(newList);
        notifyDataSetChanged();
    }
}
