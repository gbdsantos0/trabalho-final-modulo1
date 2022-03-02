package com.dbc.modelo.entidades;

import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.interfaces.Impressao;
import com.dbc.modelo.interfaces.Pokebola;
import com.dbc.modelo.objetos.Mochila;
import com.dbc.modelo.objetos.Pokedex;

import java.util.Random;

public class Treinador extends Entidade implements Impressao {

    //a mochila guarda todos os pokemons do treinador
    private Mochila mochila;

    public Treinador(String nome, Integer idade, Double peso, Utils sexo, Mochila mochila) {
        super(nome, idade, peso, sexo);
        this.mochila = mochila;
    }

    //Metodo que retorna um boolean se o pokemon for capturado(a chance de captura é delegada para a pokebola)
    public boolean capturar(Pokebola pokebola, Pokemon pokemon, Random r){
        if(r.nextInt(100) <= pokebola.calcularChance(pokemon)){
            this.mochila.adicionarPokemom(pokemon);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void imprimir() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Treinador{" +
                "mochila=" + mochila +
                '}';
    }

    //getter
    public Mochila getMochila() {
        return mochila;
    }
}

