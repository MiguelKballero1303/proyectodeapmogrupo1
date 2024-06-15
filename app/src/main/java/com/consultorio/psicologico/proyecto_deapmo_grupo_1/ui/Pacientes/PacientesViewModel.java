package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Pacientes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PacientesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PacientesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Pacientes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}