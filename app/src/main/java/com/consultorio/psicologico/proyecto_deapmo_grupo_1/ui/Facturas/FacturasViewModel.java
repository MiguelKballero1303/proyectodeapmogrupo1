package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Facturas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FacturasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FacturasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Factura fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}