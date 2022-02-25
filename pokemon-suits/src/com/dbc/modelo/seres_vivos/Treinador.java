package com.dbc.modelo.seres_vivos;

import com.dbc.modelo.pokebolas.Pokebola;

public class Treinador extends SerVivo {
    //substituir objects por PokeDex
    private Object pokedex;

    public boolean capturar(Pokebola pokebola, Pokemon pokemon){
        return false;
    }

    //getter e setter
    public Object getPokedex() {return pokedex;}
    public void setPokedex(Object pokedex) {this.pokedex = pokedex;}

}
