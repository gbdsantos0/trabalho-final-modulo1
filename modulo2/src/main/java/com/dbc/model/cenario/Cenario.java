package com.dbc.model.cenario;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.dbc.exeptions.InvalidCenarioException;
import com.dbc.model.entidades.Pokemon;
import com.dbc.model.entidades.PokemonBase;
import com.dbc.model.enums.Raridades;
import com.dbc.model.enums.TiposTerreno;
import com.dbc.model.enums.Utils;
import com.dbc.model.interfaces.Impressao;

public class Cenario implements Impressao {
    private final TiposTerreno terreno;
    private final int levelMedio;
    private final List<PokemonBase> pokemonsDisponiveis;

    public Cenario(TiposTerreno terreno, int levelMedio, List<PokemonBase> pokemonsDisponiveis) {
        this.terreno = terreno;
        this.levelMedio = levelMedio;
        this.pokemonsDisponiveis = pokemonsDisponiveis;
    }


    //metodo para gerar um pokemon em um encontro de pokemon
    public Pokemon gerarPokemon() throws InvalidCenarioException {
        Random r = new Random();
        PokemonBase pokemonBase;
        pokemonBase = this.selecionarPokemon();
        int randLevel = r.nextInt(8)+this.levelMedio-4;//variacao de 4 levels pra cima ou pra baixo
        //garantir que não ha niveis nogativos
        if(randLevel<1){
            randLevel=1;
        }
        //idade: idade maxima = level base + idade base
        //peso = 0.8~1.2 * peso base
        //sexo = random 0 ou 1 para o sexo do pokemon
        //level = level minimo = base level ou o level gerado aleatoriamente
        return new Pokemon(pokemonBase.getRaca()
                //idade em pokemons nao parece fazer tanto sentido
                ,r.nextInt(this.levelMedio)+pokemonBase.getIdadeMinima()
                ,((double)(r.nextDouble(
                        pokemonBase.getPesoMaximo() - pokemonBase.getPesoMinimo()
                        ) + pokemonBase.getPesoMinimo()))
                    //calcula o sexo de acordo com a chance de ser masculino
                , (r.nextInt(100)<=pokemonBase.getPorcentagemMacho()?Utils.MASCULINO:Utils.FEMININO)
                ,pokemonBase.getDificuldade()
                ,Math.max(pokemonBase.getLevelMinimo(),randLevel)
                ,pokemonBase.getTipo()[0]
                ,(pokemonBase.getTipo().length>1?pokemonBase.getTipo()[1]:null)
                ,pokemonBase.getRaridade());
    }

    //metodo para selecionar um pokemon da lista conforme a raridade
    //seleciona por raridade, um dos pokemons disponiveis para aquela raridade
    //5% - super raros, 20% raros, 75% comuns
    private PokemonBase selecionarPokemon() throws InvalidCenarioException {

        if(pokemonsDisponiveis.isEmpty()){
            throw new InvalidCenarioException("Lista de pokemons vazia, não é possível selecionar pokemons");
        }

        List<PokemonBase> superRaros = pokemonsDisponiveis.stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.SUPER_RARO)
                .toList();
        List<PokemonBase> raros = pokemonsDisponiveis.stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.RARO)
                .toList();
        List<PokemonBase> comuns = pokemonsDisponiveis.stream()
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

    public TiposTerreno getTerreno() {
        return terreno;
    }

    public int getLevelMedio() {
        return levelMedio;
    }

    public List<PokemonBase> getPokemonsDisponiveis() {
        //retorna uma lista nao modificavel
        return Collections.unmodifiableList(pokemonsDisponiveis);
    }

    @Override
    public void imprimir() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Cenario: " +
                "\nTipo de terreno: " + terreno +
                "\nNível médio do local: " + levelMedio +
                "\nLista de Pokemons disponíveis na região: " + pokemonsDisponiveis;
    }

}
