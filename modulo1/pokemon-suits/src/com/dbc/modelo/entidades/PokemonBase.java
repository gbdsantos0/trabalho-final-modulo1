package com.dbc.modelo.entidades;

import com.dbc.modelo.enums.Dificuldades;
import com.dbc.modelo.enums.Raridades;
import com.dbc.modelo.enums.TipoPokemon;
import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.interfaces.Impressao;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class PokemonBase {
    private final String raca;
    private final Integer idadeMinima;
    private final Double pesoMinimo;
    private final Double pesoMaximo;
    private final Double porcentagemMacho;
    private final Integer levelMinimo;
    private final Dificuldades dificuldade;
    private final TipoPokemon[] tipo;
    private final Raridades raridade;


    public PokemonBase(String raca, Integer idadeMinima, Double pesoMinimo, Double pesoMaximo, Double porcentagemMacho, Integer levelMinimo, Dificuldades dificuldade, TipoPokemon tipo1, TipoPokemon tipo2, Raridades raridade) {
        this.raca = raca;
        this.idadeMinima = idadeMinima;
        this.pesoMinimo = pesoMinimo;
        this.pesoMaximo = pesoMaximo;
        this.porcentagemMacho = porcentagemMacho;
        this.levelMinimo = levelMinimo;
        this.dificuldade = dificuldade;
        this.tipo = (tipo2 == null) ? new TipoPokemon[]{tipo1} : new TipoPokemon[]{tipo1, tipo2};
        this.raridade = raridade;
    }

    public String getRaca() {
        return raca;
    }

    public Integer getIdadeMinima() {
        return idadeMinima;
    }

    public Double getPesoMinimo() {
        return pesoMinimo;
    }

    public Double getPesoMaximo() {
        return pesoMaximo;
    }

    public Double getPorcentagemMacho() {
        return porcentagemMacho;
    }

    public Integer getLevelMinimo() {
        return levelMinimo;
    }

    public Dificuldades getDificuldade() {
        return dificuldade;
    }

    public TipoPokemon[] getTipo() {
        return tipo;
    }

    public Raridades getRaridade() {
        return raridade;
    }
}
