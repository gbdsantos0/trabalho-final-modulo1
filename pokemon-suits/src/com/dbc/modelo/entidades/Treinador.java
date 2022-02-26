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

    public Treinador(String nome, int idade, double peso, Utils sexo, Pokedex pokedex) {
        super(nome, idade, peso, sexo);
        this.pokedex = pokedex;
        this.mochila = new Mochila();
    }

    //Metodo que retorna um boolean se o pokemon for capturado(a chance de captura é delegada para a pokebola)
    public boolean capturar(Pokebola pokebola, Pokemon pokemon, Random r){
        if(r.nextInt(100) <= pokebola.calcularChance(pokemon)){
            //trocar futuramente pelo metodo adiciona do CRUD
            this.mochila.bag.add(pokemon);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void imprimir() {
        this.mochila.bag.forEach(p -> System.out.println("======================\n" + p + "============================\n"));
    }

    //getter
    public Pokedex getPokedex() {return pokedex;}

    public Mochila getMochila() {
        return mochila;
    }
}

