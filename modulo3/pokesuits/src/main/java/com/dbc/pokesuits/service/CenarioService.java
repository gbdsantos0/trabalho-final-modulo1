package com.dbc.pokesuits.service;

import com.dbc.pokesuits.dto.PokemonBaseDTO;
import com.dbc.pokesuits.dto.PokemonDTO;
import com.dbc.pokesuits.repository.CenarioRepository;
import com.dbc.pokesuits.repository.PokemonBaseRepository;
import com.dbc.pokesuits.repository.PokemonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CenarioService {
    @Autowired
    private CenarioRepository cenarioRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PokemonBaseService pokemonBaseService;
    @Autowired
    private PokemonService pokemonService;

    private PokemonDTO ultimoPokemonEncontrado;
    private Integer cenarioAtual=0;

    public boolean capturar(){

        return false;
    };

    public PokemonDTO gerarPokemon() throws Exception{
        Random r = new Random();
        PokemonBaseDTO pokemonBaseDTO;
        pokemonBaseDTO = this.selecionarPokemon();
        int randLevel = r.nextInt(8)+cenarioRepository.listAll().get(cenarioAtual).getLevelMedio()-4;//variacao de 4 levels pra cima ou pra baixo
        //garantir que não ha niveis nogativos
        if(randLevel<1){
            randLevel=1;
        }
        return PokemonDTO.builder()
                .build();
    }

    public PokemonBaseDTO selecionarPokemon(){
        return new PokemonBaseDTO();
    }

    public boolean alterarCenario(Integer id) throws Exception{
        cenarioRepository.getById(id);
        cenarioAtual=id;
        return true;
    }

}
