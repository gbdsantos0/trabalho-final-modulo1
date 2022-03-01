package com.dbc.modelo.objetos;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.entidades.PokemonBase;
import com.dbc.modelo.enums.Dificuldades;
import com.dbc.modelo.enums.Raridades;
import com.dbc.modelo.enums.TipoPokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Pokedex {
    private static Map<Integer, PokemonBase> pokedexCompleta;

    //nao consegui criar estaticamente pois o Map.of só pode inicializar com 10 itens(10 keys+10 values)
    /*private final static Map<Integer, PokemonBase> pokedexCompleta2 = new HashMap<>(Map.of(
            1,new PokemonBase("Bulbasoro"
                 , 20
                 , 6.7
                 , 11.0
                 , 87.5
                 ,5
                 , Dificuldades.COMUM
                 , TipoPokemon.GRASS
                 , TipoPokemon.POISON
                 , Raridades.COMUM)
            ,2,new PokemonBase("Ivysoro"
                 , 20
                 , 13.0
                 , 20.0
                 , 87.5
                 ,16
                 , Dificuldades.RARO
                 , TipoPokemon.GRASS
                 , TipoPokemon.POISON
                 , Raridades.RARO)
            ,3,new PokemonBase("Venusoro"
                 , 20
                 , 100.0
                 , 150.0
                 , 87.5
                 ,32
                 , Dificuldades.SUPER_RARO
                 , TipoPokemon.GRASS
                 , TipoPokemon.POISON
                 , Raridades.SUPER_RARO)
            ,4,new PokemonBase("Charmandoro"
                 , 20
                 , 8.5
                 , 15.5
                 , 87.5
                 ,5
                 , Dificuldades.COMUM
                 , TipoPokemon.GRASS
                 , TipoPokemon.POISON
                 , Raridades.COMUM)
            ,5,new PokemonBase("Charmeleoro"
                 , 20
                 , 19.0
                 , 36.0
                 , 87.5
                 ,16
                 , Dificuldades.RARO
                 , TipoPokemon.GRASS
                 , TipoPokemon.POISON
                 , Raridades.RARO)
            ,6,new PokemonBase("Venusoro"
                 , 20
                 , 90.5
                 , 142.5
                 , 87.5
                 ,36
                 , Dificuldades.SUPER_RARO
                 , TipoPokemon.GRASS
                 , TipoPokemon.POISON
                 , Raridades.SUPER_RARO)
            ,7,new PokemonBase("Squirsoro"
                 , 20
                 , 9.0
                 , 17.0
                 , 87.5
                 , 5
                 ,Dificuldades.COMUM
                 , TipoPokemon.WATER
                 , null
                 , Raridades.COMUM)
            ,8,new PokemonBase("Wartosoro"
                 , 20
                 , 22.5
                 , 37.5
                 , 87.5
                 , 16
                 ,Dificuldades.RARO
                 , TipoPokemon.WATER
                 , null
                 , Raridades.RARO)
            ,9,new PokemonBase("Blastoro"
             , 20
             , 85.5
             , 120.0
             , 87.5
             , 36
             ,Dificuldades.SUPER_RARO
             , TipoPokemon.WATER
             , null
             , Raridades.SUPER_RARO)
            ,74,new PokemonBase("Geodoro"
                 , 20
                 , 20.0
                 , 38.0
                 , 50.0
                 , 5
                 ,Dificuldades.COMUM
                 , TipoPokemon.ROCK
                 , TipoPokemon.GROUND
                 , Raridades.COMUM)
            ,75,new PokemonBase("Gravoro"
                 , 20
                 , 105.0
                 , 170.0
                 , 50.0
                 , 16
                 ,Dificuldades.RARO
                 , TipoPokemon.ROCK
                 , TipoPokemon.GROUND
                 , Raridades.RARO)
            ,76,new PokemonBase("Goloro"
                 , 20
                 , 300.0
                 , 420.0
                 , 50.0
                 , 36
                 ,Dificuldades.SUPER_RARO
                 , TipoPokemon.ROCK
                 , TipoPokemon.GROUND
                 , Raridades.SUPER_RARO)
    )
    );*/

    public Pokedex(Map<Integer, PokemonBase> pokedexCompleta) {
        Pokedex.pokedexCompleta = new HashMap<>(pokedexCompleta);
    }

    public static Map<Integer, PokemonBase> getPokedexCompleta() {
        return Collections.unmodifiableMap(pokedexCompleta);
    }
}

//        #001
//        nome:Bulbasaur
//        idade:
//        peso:
//        sexo: M
//        dificuldade:
//        pokemomtipo:grass / poison
//        raridade:
//
//
//        ######################################################
//        #002
//        nome: Ivysaur
//        idade:
//        peso:
//        sexo: M
//        dificuldade:
//        pokemomtipo:grass / poison
//        raridade:
//
//
//        #######################################################
//        #003
//        nome:Venusaur
//        idade:
//        peso:
//        sexo: M
//        dificuldade:
//        pokemomtipo:grass / poison
//        raridade:
//
//        #######################################################
//        #004
//        nome:Charmander
//        idade:
//        peso:
//        sexo: M
//        dificuldade:
//        pokemomtipo:Fire
//        raridade:
//
//
//        ########################################################
//        #005
//        nome:Charmeleon
//        idade:
//        peso:
//        sexo: M
//        dificuldade:
//        pokemomtipo:Fire
//        raridade:
//
//        ########################################################
//        #006
//        nome:Charizard
//        idade:
//        peso:
//        sexo: M
//        dificuldade:
//        pokemomtipo:Fire / Flying
//        raridade:
//
//        ######################################################
//        #007
//        nome:Squirtle
//        idade:
//        peso:
//        sexo: f
//        dificuldade:
//        pokemomtipo:Water
//        raridade:
//
//        #####################################################
//
//        #008
//        nome:wartortle
//        idade:
//        peso:
//        sexo: f
//        dificuldade:
//        pokemomtipo:water
//        raridade:
//
//        #####################################################
//        #009
//        nome:Blastoise
//        idade:
//        peso:
//        sexo: f
//        dificuldade:
//        pokemomtipo:water
//        raridade:
//
//        #####################################################
//        #010
//        nome:Caterpie
//        idade:
//        peso:
//        sexo: f
//        dificuldade:
//        pokemomtipo:bug
//        raridade:
//
//        #####################################################
//
//        #011
//        nome:Metapod
//        idade:
//        peso:
//        sexo: f
//        dificuldade:
//        pokemomtipo:bug
//        raridade:
