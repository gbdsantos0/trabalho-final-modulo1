package com.dbc.pokesuits.model.entity;

import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;

public class PokemonBase {
    private String raca;
    private Integer idadeMinima;
    private Double pesoMinimo;
    private Double pesoMaximo;
    private Double porcentagemMacho;
    private Integer levelMinimo;
    private Dificuldades dificuldade;
    private TipoPokemon[] tipo;
    private Raridades raridade;

}
