package com.pokesuits.pokebase.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pokesuits.pokebase.entity.PokemonBaseEntity;
import com.pokesuits.pokebase.entity.PokemonPorTiposEntity;

@Repository
public interface PokemonBaseRepository extends MongoRepository<PokemonBaseEntity, Integer>  {
    @Aggregation(pipeline = { 
    		"{ '$unwind' : '$tipos' }",
    		"{'$group':{ '_id': '$tipos', 'quantidade' : {'$sum': 1} }}"
    })
    List<PokemonPorTiposEntity> groupByTipoAndCount();

    List<PokemonBaseEntity> findByTipos(String tipos);
}

