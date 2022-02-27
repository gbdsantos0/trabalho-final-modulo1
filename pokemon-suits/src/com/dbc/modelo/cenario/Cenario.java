package com.dbc.modelo.cenario;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.entidades.PokemonBase;
import com.dbc.modelo.enums.*;
import com.dbc.modelo.exeptions.InvalidCenarioExeption;
import com.dbc.modelo.interfaces.Impressao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    public Pokemon gerarPokemon() throws InvalidCenarioExeption {
        Random r = new Random();

        PokemonBase pokemonBase;
        try {
            pokemonBase = this.selecionarPokemon();
        }catch (InvalidCenarioExeption ex){
            throw new InvalidCenarioExeption("Lista de pokemons vazia, não é possível gerar pokemons");
        }


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
                ,r.nextInt(this.levelMedio)+pokemonBase.getIdadeMinima() //idade em pokemons nao parece fazer tanto sentido
                ,((double)(r.nextDouble(pokemonBase.getPesoMaximo()-pokemonBase.getPesoMinimo())+pokemonBase.getPesoMinimo()))
                , (r.nextInt(100)<=pokemonBase.getPorcentagemMacho()?Utils.MASCULINO:Utils.FEMININO)//calcula o sexo de acordo com a chance de ser masculino
                ,pokemonBase.getDificuldade()
                ,Math.max(pokemonBase.getLevelMinimo(),randLevel)
                ,pokemonBase.getTipo()[0]
                ,(pokemonBase.getTipo().length>1?pokemonBase.getTipo()[1]:null)
                ,pokemonBase.getRaridade());
    }

    //metodo para selecionar um pokemon da lista conforme a raridade
    //seleciona por raridade, um dos pokemons disponiveis para aquela raridade
    //5% - super raros, 20% raros, 75% comuns
    private PokemonBase selecionarPokemon() throws InvalidCenarioExeption {

        if(pokemonsDisponiveis.isEmpty()){
            throw new InvalidCenarioExeption("Lista de pokemons vazia, não é possível selecionar pokemons");
        }

        List<PokemonBase> superRaros = pokemonsDisponiveis.stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.DIFICIL)
                .toList();
        List<PokemonBase> raros = pokemonsDisponiveis.stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.MEDIO)
                .toList();
        List<PokemonBase> comuns = pokemonsDisponiveis.stream()
                .filter(pokemon -> pokemon.getRaridade() == Raridades.FACIL)
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
        }else if(raros.isEmpty()){
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

    //teste rapido
    public static void main(String[] args) {
        Cenario cenario = new Cenario(TiposTerreno.GRAMA,20,Arrays.asList(new PokemonBase("Bulbassalto"
                , 0
                , 6.7
                ,10.0
                , 85.0
                , 1
                , Dificuldades.MEDIO
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.FACIL)));
        try {
            cenario.gerarPokemon().imprimir();
        }catch (InvalidCenarioExeption ex){
            System.err.println("Não é possível gerar pokemons em cenários sem lista de pokemons");
        }
        System.out.println("####################################################################################################"+
                "####################################################################################################");
        cenario.imprimir();

    }




}
