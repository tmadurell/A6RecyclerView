package com.company.a6recyclerview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.*;


import java.util.List;

public class CampeonesViewModel extends AndroidViewModel {

    CampeonesRepositorio campeonesRepositorio;

    MutableLiveData<List<Campeon>> listCampeonesMutableLiveData = new MutableLiveData<>();

    public CampeonesViewModel(@NonNull Application application) {
        super(application);

        campeonesRepositorio = new CampeonesRepositorio();

        listCampeonesMutableLiveData.setValue(campeonesRepositorio.obtener());
    }

    MutableLiveData<List<Campeon>> obtener(){
        return listCampeonesMutableLiveData;
    }
    MutableLiveData<Campeon> campeonSeleccionado = new MutableLiveData<>();

    void insertar(Campeon campeon){
        campeonesRepositorio.insertar(campeon, new CampeonesRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Campeon> campeones) {
                listCampeonesMutableLiveData.setValue(campeones);
            }
        });
    }

    void eliminar(Campeon campeon){
        campeonesRepositorio.eliminar(campeon, new CampeonesRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Campeon> campeones) {
                listCampeonesMutableLiveData.setValue(campeones);
            }
        });
    }

    void actualizar(Campeon campeon, float valoracion){
        campeonesRepositorio.actualizar(campeon, valoracion, new CampeonesRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Campeon> campeones) {
                listCampeonesMutableLiveData.setValue(campeones);
            }
        });
    }
    void seleccionar(Campeon campeon){
        campeonSeleccionado.setValue(campeon);
    }

    MutableLiveData<Campeon> seleccionado(){
        return campeonSeleccionado;
    }
}
