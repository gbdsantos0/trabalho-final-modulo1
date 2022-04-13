package com.pokesuits.pokebase.dto;

import com.pokesuits.pokebase.enums.Dificuldades;
import com.pokesuits.pokebase.enums.Raridades;
import com.pokesuits.pokebase.enums.TipoPokemon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PokemonBaseDTO {
	private Integer id;
    private String racaPokemon;
    private Double pesoMinimo;
    private Double pesoMaximo;
    private Double porcentagemMacho;
    private Integer levelMinimo;
    private Dificuldades dificuldade;
    private TipoPokemon tipo1;
    private TipoPokemon tipo2;
    private Raridades raridade;
}
