package com.dbc.modelo.entidades;

import com.dbc.modelo.enums.Dificuldades;
import com.dbc.modelo.enums.Raridades;
import com.dbc.modelo.enums.TipoPokemon;
import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.interfaces.Impressao;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class PokemonBase implements Impressao {
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

    @Override
    public void imprimir() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return "Informações da espécie de Pokemon\n" +
                "Raça: " + raca +
                "\nIdade Minima: " + idadeMinima +
                "\npesoMinimo: " + df.format(pesoMinimo) +
                "\npesoMaximo: " + df.format(pesoMaximo) +
                "\nPorcentagem Macho: " + porcentagemMacho +
                "\nLevel Minimo: " + levelMinimo +
                "\nDificuldade: " + dificuldade +
                "\nTipo: " + this.getStringTipos() +
                "\nRaridade: " + raridade;
    }

    public String getStringTipos() {
        List<TipoPokemon> n = Arrays.stream(this.getTipo()).toList();
        TipoPokemon a = n.get(0);
        if(n.size() > 1){
            TipoPokemon b = n.get(1);
            return a.toString() + " " + b.toString();
        }
        return a.toString();
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
