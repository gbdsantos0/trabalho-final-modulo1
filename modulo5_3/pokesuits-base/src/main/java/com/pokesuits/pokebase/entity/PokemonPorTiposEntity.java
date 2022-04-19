package com.pokesuits.pokebase.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("pokemon_base")
public class PokemonPorTiposEntity {

    @Id
    private String id;
    private Integer quantidade;
}
