package com.dbc.pokesuits.dto.pokemon;

import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;
import com.dbc.pokesuits.enums.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PokemonCreateDTO {

	private String racaPokemon;
	private Double peso;
	private Utils sexo;
    private String nome;
    private Integer level;
    //Usamos Enumerations para Padronizar os atributos que influenciam na captura
    private Dificuldades dificuldade;
    private TipoPokemon tipo1;
    private TipoPokemon tipo2;
    private Raridades raridade;
    private Integer idMochila;
	
	
}
