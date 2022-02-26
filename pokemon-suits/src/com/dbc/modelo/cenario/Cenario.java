package com.dbc.modelo.cenario;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.enums.*;
import com.dbc.modelo.interfaces.Impressao;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Cenario implements Impressao {
    private final TiposTerreno terreno;
    private final int levelMedio;
    private final List<Pokemon> pokemonsDisponiveis;

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

        if(valorAleatorio<5 && !superRaros.isEmpty()){
            return superRaros.get(r.nextInt(superRaros.size()));
        }else if(valorAleatorio<25 && !raros.isEmpty()){
            return raros.get(r.nextInt(raros.size()));
        }else{
            return comuns.get(r.nextInt(comuns.size()));
        }
    }


    @Override
    public void imprimir() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Cenario: " +
                "\nTipo de terreno: " + terreno +
                "\nNível médio do local: " + levelMedio +
                "\nLista de Pokemons disponíveis na região: " + pokemonsDisponiveis;
    }

    //teste rapido
    public static void main(String[] args) {
        Cenario cenario = new Cenario(TiposTerreno.GRAMA,20,Arrays.asList(new Pokemon("Bulbassalto"
                , 20
                , 6.7
                , Utils.MASCULINO
                , Dificuldades.MEDIO
                ,5
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.FACIL)));
        cenario.gerarPokemon().imprimir();
        System.out.println("####################################################################################################"+
                "####################################################################################################");
        cenario.imprimir();
    }
    //tipos está imprimindo memoria




}
