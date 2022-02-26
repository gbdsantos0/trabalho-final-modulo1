package com.dbc.modelo.entidades;

import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.interfaces.Impressao;
import com.dbc.modelo.interfaces.Pokebola;
import com.dbc.modelo.objetos.Mochila;
import com.dbc.modelo.objetos.Pokedex;

import java.util.Random;

public class Treinador extends Entidade implements Impressao {

    //Instacia da mochila que guarda todos os poquemons do treinador
    private Mochila mochila;
    //Instancia da PokeDex, que guarda todos os Pokemons do Jogo
    private final Pokedex pokedex;

    public Treinador(String nome, Integer idade, Double peso, Utils sexo, Mochila mochila, Pokedex pokedex) {
        super(nome, idade, peso, sexo);
        this.mochila = mochila;
        this.pokedex = pokedex;
    }

    //Metodo que retorna um boolean se o pokemon for capturado(a chance de captura é delegada para a pokebola)
    public boolean capturar(Pokebola pokebola, Pokemon pokemon, Random r){
        if(r.nextInt(100) <= pokebola.calcularChance(pokemon)){
            //trocar futuramente pelo metodo adiciona do CRUD
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
                ", pokedex=" + pokedex +
                '}';
    }

    //getter
    public Pokedex getPokedex() {return pokedex;}

    public Mochila getMochila() {
        return mochila;
    }
}

