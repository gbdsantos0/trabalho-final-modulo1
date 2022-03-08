package com.dbc.model.entidades;

import java.util.Random;
import java.util.Scanner;

import com.dbc.model.enums.Utils;
import com.dbc.model.interfaces.Impressao;
import com.dbc.model.interfaces.Pokebola;
import com.dbc.model.objetos.Mochila;
import com.dbc.repository.PokemonRepository;

public class Treinador extends Entidade implements Impressao {

    //a mochila guarda todos os pokemons do treinador
    private Mochila mochila;
    private int idTreinador;
    private int idMochila;

    public Treinador(String nome, Integer idade, Double peso, Utils sexo, Mochila mochila) {
        super(nome, idade, peso, sexo);
        this.mochila = mochila;
    }
    
    public void setMochila(Mochila mochila) {
		this.mochila = mochila;
	}
    
    public int getIdTreinador() {
        return idTreinador;
    }

    public void setIdTreinador(int idTreinador) {
        this.idTreinador = idTreinador;
    }

    public int getIdMochila() {
        return idMochila;
    }

    public void setIdMochila(int idMochila) {
        this.idMochila = idMochila;
    }

    //Metodo que retorna um boolean se o pokemon for capturado(a chance de captura é delegada para a pokebola)
    public boolean capturar(Pokebola pokebola, Pokemon pokemon, Random r, Scanner scanner, PokemonRepository pokemonRepository){
        if(r.nextInt(100) <= pokebola.calcularChance(pokemon)){
            System.out.println("Digite um apelido para o seu Pokemon: ");
            pokemon.setNome(scanner.nextLine());
            pokemon.setIdMochila(this.getIdMochila());
            try {
            	pokemonRepository.adicionar(pokemon);
			} catch (Exception e){}
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

