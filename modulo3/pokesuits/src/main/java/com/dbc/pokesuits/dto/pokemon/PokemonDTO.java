package com.dbc.pokesuits.dto.pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class PokemonDTO extends PokemonCreateDTO {
	private Long idPokemon;
}
