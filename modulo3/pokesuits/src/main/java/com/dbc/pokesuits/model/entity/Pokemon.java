package com.dbc.pokesuits.model.entity;

import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;
import com.dbc.pokesuits.model.entity.Entidade;

public class Pokemon extends Entidade {

	private Integer id;
    private String pokemon;
    private Integer level;
    //Usamos Enumerations para Padronizar os atributos que influenciam na captura
    private Dificuldades dificuldade;
    private TipoPokemon[] tipo;
    private Raridades raridade;
    private Integer idMochila;


}
