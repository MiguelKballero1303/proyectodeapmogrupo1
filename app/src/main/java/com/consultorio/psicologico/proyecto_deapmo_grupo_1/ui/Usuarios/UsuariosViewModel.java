package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Usuarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UsuariosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UsuariosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Usuarios fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}