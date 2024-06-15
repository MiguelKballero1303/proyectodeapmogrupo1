package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Terapeutas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TerapeutasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TerapeutasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Terapeutas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}