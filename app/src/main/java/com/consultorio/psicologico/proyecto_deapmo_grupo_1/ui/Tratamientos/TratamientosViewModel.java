package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Tratamientos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TratamientosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TratamientosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Tratamientos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}