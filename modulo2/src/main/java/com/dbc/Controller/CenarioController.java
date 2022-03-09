package com.dbc.Controller;

import com.dbc.exeptions.InvalidCenarioException;
import com.dbc.model.cenario.Cenario;
import com.dbc.model.entidades.Pokemon;
import com.dbc.model.entidades.PokemonBase;
import com.dbc.model.enums.*;
import com.dbc.model.objetos.Pokedex;

import java.util.*;

public class CenarioController {
    private final List<Cenario> cenarios;
    private final Pokedex pokedex;
    private int cenarioAtual;

    public CenarioController(){
        this.pokedex = popularPokedex();
        this.cenarios = popularCenarios();
        this.cenarioAtual = 3;
    }

    public boolean alterarCenario(int posicao){
        if(posicao<cenarios.size()){
            if(posicao>=0){
                this.cenarioAtual = posicao;
                return true;
            }
        }
        return false;
    }

    private Pokedex popularPokedex() {
        Map<Integer, PokemonBase> mapPokemons = new HashMap<>();
        mapPokemons.put(1, new PokemonBase("Bulbasoro", 20, 6.7, 11.0, 87.5, 5, Dificuldades.FACIL, TipoPokemon.GRASS,
                TipoPokemon.POISON, Raridades.COMUM));
        mapPokemons.put(2, new PokemonBase("Ivysoro", 20, 13.0, 20.0, 87.5, 16, Dificuldades.MEDIO, TipoPokemon.GRASS,
                TipoPokemon.POISON, Raridades.RARO));
        mapPokemons.put(3, new PokemonBase("Venusoro", 20, 100.0, 150.0, 87.5, 32, Dificuldades.DIFICIL,
                TipoPokemon.GRASS, TipoPokemon.POISON, Raridades.SUPER_RARO));
        mapPokemons.put(4, new PokemonBase("Charmandoro", 20, 8.5, 15.5, 87.5, 5, Dificuldades.FACIL, TipoPokemon.FIRE,
                null, Raridades.COMUM));
        mapPokemons.put(5, new PokemonBase("Charmeleoro", 20, 19.0, 36.0, 87.5, 16, Dificuldades.MEDIO,
                TipoPokemon.FIRE, null, Raridades.RARO));
        mapPokemons.put(6, new PokemonBase("Charizoro", 20, 90.5, 142.5, 87.5, 36, Dificuldades.DIFICIL,
                TipoPokemon.FIRE, TipoPokemon.DRAGON, Raridades.SUPER_RARO));
        mapPokemons.put(7, new PokemonBase("Squirsoro", 20, 9.0, 17.0, 87.5, 5, Dificuldades.FACIL, TipoPokemon.WATER,
                null, Raridades.COMUM));
        mapPokemons.put(8, new PokemonBase("Wartosoro", 20, 22.5, 37.5, 87.5, 16, Dificuldades.MEDIO, TipoPokemon.WATER,
                null, Raridades.RARO));
        mapPokemons.put(9, new PokemonBase("Blastoro", 20, 85.5, 120.0, 87.5, 36, Dificuldades.DIFICIL,
                TipoPokemon.WATER, null, Raridades.SUPER_RARO));
        mapPokemons.put(74, new PokemonBase("Geodoro", 20, 20.0, 38.0, 50.0, 5, Dificuldades.FACIL, TipoPokemon.ROCK,
                TipoPokemon.GROUND, Raridades.COMUM));
        mapPokemons.put(75, new PokemonBase("Gravoro", 20, 105.0, 170.0, 50.0, 16, Dificuldades.MEDIO, TipoPokemon.ROCK,
                TipoPokemon.GROUND, Raridades.RARO));
        mapPokemons.put(76, new PokemonBase("Goloro", 20, 300.0, 420.0, 50.0, 36, Dificuldades.DIFICIL,
                TipoPokemon.ROCK, TipoPokemon.GROUND, Raridades.SUPER_RARO));

        Pokedex pokedex = new Pokedex(mapPokemons);

        return pokedex;
    }

    private List<Cenario> popularCenarios() {
        List<Cenario> cenarios = new ArrayList<>();

        cenarios.add(new Cenario(TiposTerreno.GRAMA, 4, Arrays.asList(this.pokedex.getPokedexCompleta().get(1),
                this.pokedex.getPokedexCompleta().get(2), this.pokedex.getPokedexCompleta().get(3))));
        cenarios.add(new Cenario(TiposTerreno.AGUA, 10, Arrays.asList(this.pokedex.getPokedexCompleta().get(7),
                this.pokedex.getPokedexCompleta().get(8), this.pokedex.getPokedexCompleta().get(9))));
        cenarios.add(new Cenario(TiposTerreno.CAVERNA, 30,
                Arrays.asList(this.pokedex.getPokedexCompleta().get(4), this.pokedex.getPokedexCompleta().get(5),
                        this.pokedex.getPokedexCompleta().get(6), this.pokedex.getPokedexCompleta().get(74),
                        this.pokedex.getPokedexCompleta().get(75), this.pokedex.getPokedexCompleta().get(76))));
        cenarios.add(new Cenario(TiposTerreno.CHAO, 4, List.of()));

        return cenarios;
    }

    public Pokemon gerarPokemon() throws InvalidCenarioException {
        Random r = new Random();
        PokemonBase pokemonBase;
        pokemonBase = this.selecionarPokemon();
        int randLevel = r.nextInt(8)+cenarios.get(cenarioAtual).getLevelMedio()-4;//variacao de 4 levels pra cima ou pra baixo
        //garantir que não ha niveis nogativos
        if(randLevel<1){
            randLevel=1;
        }
        //idade: idade maxima = level base + idade base
        //peso = 0.8~1.2 * peso base
        //sexo = random 0 ou 1 para o sexo do pokemon
        //level = level minimo = base level ou o level gerado aleatoriamente
        return new Pokemon(null
                ,pokemonBase.getRaca()
                //idade em pokemons nao parece fazer tanto sentido
                ,r.nextInt(cenarios.get(cenarioAtual).getLevelMedio())+pokemonBase.getIdadeMinima()
                ,((double)(r.nextDouble(
                pokemonBase.getPesoMaximo() - pokemonBase.getPesoMinimo()
        ) + pokemonBase.getPesoMinimo()))
                //calcula o sexo de acordo com a chance de ser masculino
                , (r.nextInt(100)<=pokemonBase.getPorcentagemMacho()? Utils.MASCULINO:Utils.FEMININO)
                ,pokemonBase.getDificuldade()
                ,Math.max(pokemonBase.getLevelMinimo(),randLevel)
                ,pokemonBase.getTipo()[0]
                ,(pokemonBase.getTipo().length>1?pokemonBase.getTipo()[1]:null)
                ,pokemonBase.getRaridade()
                ,null);
    }

    //metodo para selecionar um pokemon da lista conforme a raridade
    //seleciona por raridade, um dos pokemons disponiveis para aquela raridade
    //5% - super raros, 20% raros, 75% comuns
    private PokemonBase selecionarPokemon() throws InvalidCenarioException {

        if(cenarios.get(cenarioAtual).getPokemonsDisponiveis().isEmpty()){
            throw new InvalidCenarioException("Lista de pokemons vazia, não é possível selecionar pokemons");
        }

        List<PokemonBase> superRaros = cenarios.get(cenarioAtual).getPokemonsDisponiveis().stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.SUPER_RARO)
                .toList();
        List<PokemonBase> raros = cenarios.get(cenarioAtual).getPokemonsDisponiveis().stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.RARO)
                .toList();
        List<PokemonBase> comuns = cenarios.get(cenarioAtual).getPokemonsDisponiveis().stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.COMUM)
                .toList();

        Random r = new Random();

        int valorAleatorio;
        if(comuns.isEmpty()){
            //somente super raros = retorna um super raro
            if(raros.isEmpty()){
                return superRaros.get(r.nextInt(superRaros.size()));
            }
            //somente raros = retorna um raro
            else if(superRaros.isEmpty()){
                return raros.get(r.nextInt(raros.size()));
            }
            //raros e super raros = calcula probabilidade proporcional
            else{
                valorAleatorio = r.nextInt(25);

                if(valorAleatorio<5){
                    return superRaros.get(r.nextInt(superRaros.size()));
                }else{
                    return raros.get(r.nextInt(raros.size()));
                }
            }
        }
        else if(raros.isEmpty()){
            //somente comuns
            if(superRaros.isEmpty()){
                return comuns.get(r.nextInt(comuns.size()));
            }
            //comuns e super raros = calcula probabilidade proporcional
            else{
                valorAleatorio = r.nextInt(80);
                if(valorAleatorio<5){
                    return superRaros.get(r.nextInt(superRaros.size()));
                }else{
                    return comuns.get(r.nextInt(comuns.size()));
                }
            }
        }
        //comuns e raros = calcula probabilidade proporcional
        else if(superRaros.isEmpty()){
            valorAleatorio = r.nextInt(95);
            if(valorAleatorio<20){
                return raros.get(r.nextInt(raros.size()));
            }else{
                return comuns.get(r.nextInt(comuns.size()));
            }
        }
        //todos disponiveis
        else{
            valorAleatorio = r.nextInt(100);

            if(valorAleatorio<5){
                return superRaros.get(r.nextInt(superRaros.size()));
            }else if(valorAleatorio<25){
                return raros.get(r.nextInt(raros.size()));
            }else{
                return comuns.get(r.nextInt(comuns.size()));
            }
        }
    }
}
