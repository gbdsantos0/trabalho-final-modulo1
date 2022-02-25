package com.dbc.modelo.seres_vivos;

import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.pokebolas.Pokebola;

public class Treinador extends SerVivo {
    //substituir objects por PokeDex
    private Pokedex pokedex;


    public Treinador(String nome, int idade, double peso, Utils sexo, Pokedex pokedex) {
        super(nome, idade, peso, sexo);
        this.pokedex = pokedex;
    }

    public boolean capturar(Pokebola pokebola, Pokemon pokemon){
        return false;
    }

    //getter e setter
    public Object getPokedex() {return pokedex;}
}
