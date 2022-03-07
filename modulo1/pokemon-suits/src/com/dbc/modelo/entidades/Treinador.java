package com.dbc.modelo.entidades;

import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.interfaces.Impressao;
import com.dbc.modelo.interfaces.Pokebola;
import com.dbc.modelo.objetos.Mochila;

import java.util.Random;
import java.util.Scanner;

public class Treinador extends Entidade implements Impressao {

    //a mochila guarda todos os pokemons do treinador
    private Mochila mochila;

    public Treinador(String nome, Integer idade, Double peso, Utils sexo, Mochila mochila) {
        super(nome, idade, peso, sexo);
        this.mochila = mochila;
    }

    //Metodo que retorna um boolean se o pokemon for capturado(a chance de captura é delegada para a pokebola)
    public boolean capturar(Pokebola pokebola, Pokemon pokemon, Random r, Scanner scanner){
        if(r.nextInt(100) <= pokebola.calcularChance(pokemon)){
            System.out.println("Digite um apelido para o seu Pokemon: ");
            pokemon.setApelido(scanner.nextLine());
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

