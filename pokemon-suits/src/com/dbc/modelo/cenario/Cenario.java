package com.dbc.modelo.cenario;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.enums.Raridades;
import com.dbc.modelo.enums.TiposTerreno;
import com.dbc.modelo.enums.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Cenario {
    private TiposTerreno terreno;
    private int levelMedio;
    private List<Pokemon> pokemonsDisponiveis;

    public Cenario(TiposTerreno terreno, int levelMedio, List<Pokemon> pokemonsDisponiveis) {
        this.terreno = terreno;
        this.levelMedio = levelMedio;
        this.pokemonsDisponiveis = pokemonsDisponiveis;
    }


    //metodo para gerar um pokemon em um encontro de pokemon
    public Pokemon gerarPokemon(){
        Random r = new Random();
        Pokemon pokemonBase = this.selecionarPokemon();


        int randLevel = r.nextInt(levelMedio);

        //idade: idade maxima = level base + idade base
        //peso = 0.8~1.2 * peso base
        //sexo = random 0 ou 1 para o sexo do pokemon
        //level = level minimo = base level ou o level gerado aleatoriamente
        return new Pokemon(pokemonBase.getNome()
                ,r.nextInt(pokemonBase.getIdade())+pokemonBase.getLevel()
                ,((double)(r.nextInt(40)/10)+0.8)*pokemonBase.getPeso()
                , (r.nextInt(2)==1?Utils.MASCULINO:Utils.FEMININO)
                ,pokemonBase.getDificuldade()
                ,Math.max(pokemonBase.getLevel(),randLevel)
                ,pokemonBase.getTipo()[0]
                ,pokemonBase.getTipo()[1]
                ,pokemonBase.getRaridade());
    }

    //metodo para selecionar um pokemon da lista conforme a raridade
    //seleciona por raridade, um dos pokemons disponiveis para aquela raridade
    //5% - super raros, 20% raros, 75% comuns
    private Pokemon selecionarPokemon(){
        List<Pokemon> superRaros = pokemonsDisponiveis.stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.DIFICIL)
                .toList();
        List<Pokemon> raros = pokemonsDisponiveis.stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.MEDIO)
                .toList();
        List<Pokemon> comuns = pokemonsDisponiveis.stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.FACIL)
                .toList();

        Random r = new Random();

        int valorAleatorio = r.nextInt(100);

        if(valorAleatorio<5){
            return superRaros.get(r.nextInt(superRaros.size()-1));
        }else if(valorAleatorio<25){
            return raros.get(r.nextInt(raros.size()-1));
        }else{
            return comuns.get(r.nextInt(comuns.size()-1));
        }
    }

    public static void main(String[] args) {

    }
    //

}
