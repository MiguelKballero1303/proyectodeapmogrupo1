package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Citas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CitasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CitasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Citas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}