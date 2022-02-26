package com.dbc.testes;

import com.dbc.modelo.cenario.Cenario;
import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.enums.*;
import com.dbc.modelo.exeptions.InvalidCenarioExeption;
import org.junit.Assert;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TesteCenario {
    @Test
    public void gerarPokemonListaVazia(){
        //SETUP
        Cenario cenario = new Cenario(TiposTerreno.GRAMA,20, List.of());
        Pokemon pokemonGerado = null;


        //ACTION
        try {
            pokemonGerado = cenario.gerarPokemon();

        }catch (InvalidCenarioExeption ex){
            System.err.println("Não é possível gerar pokemons em cenários sem lista de pokemons");
        }

        //ASSERT
        Assert.assertNull(pokemonGerado);
    }

    @Test
    public void gerarPokemonListaComUmNormal(){
        //SETUP
        Cenario cenario = new Cenario(TiposTerreno.GRAMA,20, Arrays.asList(new Pokemon("Bulbassalto"
                , 20
                , 6.7
                , Utils.MASCULINO
                , Dificuldades.MEDIO
                ,5
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.FACIL)));
        Pokemon pokemonGerado = null;


        //ACTION
        try {
            pokemonGerado = cenario.gerarPokemon();

        }catch (InvalidCenarioExeption ex){
            System.err.println("Não é possível gerar pokemons em cenários sem lista de pokemons");
        }

        //ASSERT
        Assert.assertNotNull(pokemonGerado);
        Assert.assertEquals(pokemonGerado.getRaridade(),Raridades.FACIL);
    }

    @Test
    public void gerarPokemonListaComUmRaro(){
        //SETUP
        Cenario cenario = new Cenario(TiposTerreno.GRAMA,20, Arrays.asList(new Pokemon("Bulbassalto Grande"
                , 20
                , 6.7
                , Utils.MASCULINO
                , Dificuldades.MEDIO
                ,5
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.MEDIO)));
        Pokemon pokemonGerado = null;


        //ACTION
        try {
            pokemonGerado = cenario.gerarPokemon();

        }catch (InvalidCenarioExeption ex){
            System.err.println("Não é possível gerar pokemons em cenários sem lista de pokemons");
        }

        //ASSERT
        Assert.assertNotNull(pokemonGerado);
        Assert.assertEquals(pokemonGerado.getRaridade(),Raridades.MEDIO);
    }

    @Test
    public void gerarPokemonListaComUmSuperRaro(){
        //SETUP
        Cenario cenario = new Cenario(TiposTerreno.GRAMA,20, Arrays.asList(new Pokemon("Bulbassalto Maior Ainda"
                , 20
                , 6.7
                , Utils.MASCULINO
                , Dificuldades.MEDIO
                ,5
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.DIFICIL)));
        Pokemon pokemonGerado = null;


        //ACTION
        try {
            pokemonGerado = cenario.gerarPokemon();

        }catch (InvalidCenarioExeption ex){
            System.err.println("Não é possível gerar pokemons em cenários sem lista de pokemons");
        }

        //ASSERT
        Assert.assertNotNull(pokemonGerado);
        Assert.assertEquals(pokemonGerado.getRaridade(),Raridades.DIFICIL);
    }

}
