package com.dbc.modelo.entidades;

import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.interfaces.Impressao;
import com.dbc.modelo.interfaces.Pokebola;

import java.util.ArrayList;
import java.util.Random;

public class Treinador extends Entidade implements Impressao {

    private ArrayList<Pokemon> bag = new ArrayList<>();
    private final Pokedex pokedex;



    public Treinador(String nome, int idade, double peso, Utils sexo, Pokedex pokedex) {
        super(nome, idade, peso, sexo);
        this.pokedex = pokedex;
    }

    public boolean capturar(Pokebola pokebola, Pokemon pokemon, Random r){
        if(r.nextInt(100) <= pokebola.calcularChance(pokemon)){
            this.bag.add(pokemon);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void imprimir() {
       bag.forEach(p -> System.out.println("======================\n" + p + "============================\n"));
    }

        //getter e setter
    public Pokedex getPokedex() {return pokedex;}
}
