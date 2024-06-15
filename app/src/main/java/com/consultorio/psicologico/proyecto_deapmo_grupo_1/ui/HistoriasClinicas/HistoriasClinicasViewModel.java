package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.HistoriasClinicas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoriasClinicasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HistoriasClinicasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Historia Clinica fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}