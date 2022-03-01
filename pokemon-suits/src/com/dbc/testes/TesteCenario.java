package com.dbc.testes;

import com.dbc.modelo.cenario.Cenario;
import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.entidades.PokemonBase;
import com.dbc.modelo.enums.*;
import com.dbc.modelo.exeptions.InvalidCenarioExeption;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

public class TesteCenario {
    @Test
    public void gerarPokemonListaVazia(){
        //SETUP
        Cenario cenario = new Cenario(TiposTerreno.GRAMA,20, List.of());
        Pokemon pokemonGerado = null;


        //ACTION
        try {
            pokemonGerado = cenario.gerarPokemon();

        }catch (InvalidCenarioExeption e){}

        //ASSERT
        Assert.assertNull(pokemonGerado);
    }

    @Test
    public void gerarPokemonListaComUmNormal(){
        //SETUP
        Cenario cenario = new Cenario(TiposTerreno.GRAMA,20,Arrays.asList(new PokemonBase("Bulbassalto"
                , 0
                , 6.7
                ,10.0
                , 85.0
                , 1
                , Dificuldades.MEDIO
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.COMUM)));
        Pokemon pokemonGerado = null;


        //ACTION
        try {
            pokemonGerado = cenario.gerarPokemon();

        }catch (InvalidCenarioExeption ex){
            System.err.println("Não é possível gerar pokemons em cenários sem lista de pokemons");
        }

        //ASSERT
        Assert.assertNotNull(pokemonGerado);
        Assert.assertEquals(pokemonGerado.getRaridade(),Raridades.COMUM);
    }

    @Test
    public void gerarPokemonListaComUmRaro(){
        //SETUP
        Cenario cenario = new Cenario(TiposTerreno.GRAMA,20,Arrays.asList(new PokemonBase("Bulbassalto"
                , 0
                , 6.7
                ,10.0
                , 85.0
                , 1
                , Dificuldades.MEDIO
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.RARO)));
        Pokemon pokemonGerado = null;


        //ACTION
        try {
            pokemonGerado = cenario.gerarPokemon();

        }catch (InvalidCenarioExeption ex){
            System.err.println("Não é possível gerar pokemons em cenários sem lista de pokemons");
        }

        //ASSERT
        Assert.assertNotNull(pokemonGerado);
        Assert.assertEquals(pokemonGerado.getRaridade(),Raridades.RARO);
    }

    @Test
    public void gerarPokemonListaComUmSuperRaro(){
        //SETUP
        Cenario cenario = new Cenario(TiposTerreno.GRAMA,20,Arrays.asList(new PokemonBase("Bulbassalto"
                , 0
                , 6.7
                ,10.0
                , 85.0
                , 1
                , Dificuldades.MEDIO
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.SUPER_RARO)));
        Pokemon pokemonGerado = null;


        //ACTION
        try {
            pokemonGerado = cenario.gerarPokemon();

        }catch (InvalidCenarioExeption ex){
            System.err.println("Não é possível gerar pokemons em cenários sem lista de pokemons");
        }

        //ASSERT
        Assert.assertNotNull(pokemonGerado);
        Assert.assertEquals(pokemonGerado.getRaridade(),Raridades.SUPER_RARO);
    }

}
