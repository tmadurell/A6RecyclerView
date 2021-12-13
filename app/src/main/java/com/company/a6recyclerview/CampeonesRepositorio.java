package com.company.a6recyclerview;

import java.util.*;

public class CampeonesRepositorio {

    List<Campeon> campeon = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Campeon> campeones);
    }
    //FUENTE: https://unite.pokemon.com/es-es/pokemon/
    CampeonesRepositorio(){
        campeon.add(new Campeon("PIKACHU",R.drawable.upikachu, "A Pikachu se le da genial usar electricidad para atacar a los rivales desde lejos. ¡Sus ataques pueden incluso dejar al otro Pokémon paralizado!"));
        campeon.add(new Campeon("SNORLAX",R.drawable.usnorlax, "Snorlax puede aguantar muchos golpes. Se trata de un Pokémon fiable, capaz de proteger a sus aliados."));
        campeon.add(new Campeon("LUCARIO",R.drawable.ulucario, "Lucario es un Pokémon muy equilibrado que combina a la perfección velocidad y fuerza."));
        campeon.add(new Campeon("VENUSAUR",R.drawable.uvenusaur, "Si actúa con precisión, Venusaur puede causar mucho daño a sus rivales desde una gran distancia."));
        campeon.add(new Campeon("MR. MIME",R.drawable.umrmime,"La especialidad de Mr. Mime es restringir a los rivales con sus movimientos complejos."));

    }

    List<Campeon> obtener() {
        return campeon;
    }

    void insertar(Campeon pokemon, Callback callback){
        campeon.add(pokemon);
        callback.cuandoFinalice(campeon);
    }

    void eliminar(Campeon elemento, Callback callback) {
        campeon.remove(elemento);
        callback.cuandoFinalice(campeon);
    }

    void actualizar(Campeon campeon, float valoracion, Callback callback) {
        campeon.valoracion = valoracion;
        callback.cuandoFinalice(this.campeon);
    }
}
