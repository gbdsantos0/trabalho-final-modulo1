package com.dbc.pokesuits.controller;

import com.dbc.pokesuits.dto.cenario.CenarioDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.model.entity.Treinador;
import com.dbc.pokesuits.service.CenarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cenario")
@Log
public class CenarioController {
    @Autowired
    private CenarioService cenarioService;

    @ApiOperation(value = "Altera o cenário atual utilizando o idCenario alvo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cenário para o qual foi alterado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/alterarcenario/{idcenario}")
    public CenarioDTO alterarCenario(@PathVariable("idcenario") Integer idCenario) throws Exception{
        CenarioDTO cenarioDTO = cenarioService.alterarCenario(idCenario);
        log.info("Cenário alterado");
        return cenarioDTO;
    }

    @ApiOperation(value = "Gera um pokemon para ser utililizado no encontro pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Pokemon conforme o cenário atual com chances divididas por rarirade e quantidade de pokemons"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/gerarpokemon")
    public PokemonCreateDTO gerarPokemon() throws Exception{
        PokemonCreateDTO pokemonCreateDTO = cenarioService.gerarPokemon();
        log.info("Pokemon gerado com sucesso pelo cenário");
        return pokemonCreateDTO;
    }

    @ApiOperation(value = "Tentativa de capturar um pokemon encontrado (deve ser passado no corpo por JSON)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Pokemon capturado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/capturar/{nomepokebola}")//TODO ADICIONAR REQUESTINFO PARA CASO DE NAO CONSEGUIR CAPTURAR
    public PokemonDTO capturarPokemon(@Valid @RequestBody PokemonCreateDTO pokemonCreateDTO, @PathVariable("nomepokebola") String nomePokebola) throws Exception{//todo adicionar o treinadorDTO
        PokemonDTO pokemonDTO = cenarioService.capturar(pokemonCreateDTO, nomePokebola, null);
        log.info("Pokemon Capturado com sucesso");
        return pokemonDTO;
    }
}