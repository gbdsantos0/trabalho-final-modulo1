package com.dbc.modelo;

import com.dbc.modelo.cenario.Cenario;
import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.entidades.PokemonBase;
import com.dbc.modelo.entidades.Treinador;
import com.dbc.modelo.enums.*;
import com.dbc.modelo.exeptions.InvalidCenarioExeption;
import com.dbc.modelo.interfaces.Pokebola;
import com.dbc.modelo.objetos.Mochila;
import com.dbc.modelo.objetos.Pokedex;
import com.dbc.modelo.pokebolas.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random r = new Random();
        Treinador ash = new Treinador("ash", 40, 80.0, Utils.MASCULINO, new Mochila(), new Pokedex());

        List<Cenario> cenarios = popularCenarios();
        Cenario cenarioAtual = cenarios.get(0);

        Pokebola pokebola = null;

        //contador para chance do pokemon fugir
        int contador = 0;


        int escolha = 0;

        do {
            System.out.println("Digite 1 Procurar Pokemon");
            System.out.println("Digite 2 para mudar de local");
            System.out.println("Digite 3 para listar pokemom ");
            System.out.println("Digite 4 para editar o nome de um pokemom");
            System.out.println("Digite 5 para assassinar um pokemom");
            System.out.println("Digite 8 para sair");
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha){
                case 1 -> {
                    try{
                        Pokemon pokemonEncontro = cenarioAtual.gerarPokemon();
                        System.out.println("Pokemon encontrado: ");
                        pokemonEncontro.imprimir();
                        System.out.println("Digite 1 para jogar a pokebola");
                        System.out.println("Digite 2 para fugir");
                        escolha = scanner.nextInt();
                        while(escolha!=2){
                            System.out.println("Qual pokebola você quer escolher?");
                            System.out.println("1 - Pokeball\n2 - GreatBall\n3 - NetBall\n4 - HeavyBall\n5 - MasterBall");
                            escolha = scanner.nextInt();

                            switch (escolha){
                                case 1 -> pokebola = new PokeBall();
                                case 2 -> pokebola = new GreatBall();
                                case 3 -> pokebola = new NetBall();
                                case 4 -> pokebola = new HeavyBall();
                                case 5 -> pokebola = new MasterBall();
                            }

                            if(ash.capturar(pokebola,pokemonEncontro,r)){
                                System.out.println("Parabéns, você conseguiu capturar "+ pokemonEncontro.getNome());
                                escolha = 2;
                            }else{
                                if(!(r.nextInt(100)<(30-pokemonEncontro.getDificuldade().getChance())*(1+(1/(contador+1)))+5)){
                                    System.out.println("Você fracassou miseravelmente, o que quer fazer agora?\n");
                                    System.out.println("Digite 1 para jogar a pokebola");
                                    System.out.println("Digite 2 para fugir");
                                    contador++;
                                    escolha = scanner.nextInt();
                                }else{
                                    System.out.println("O pokemon escapou, capturá-lo era seu único trabalho. Tu é ruim!\n");
                                    escolha = 2;
                                }

                            }
                        };

                        escolha = 0;
                    } catch(InvalidCenarioExeption ex){
                        System.out.println("Esta área não possui pokemons. Mude de área para procurar pokemons.");
                    }
                }
                case 2 -> {
                    System.out.println("1 - Grama\n2 - Água");
                    escolha = scanner.nextInt();
                    switch (escolha){
                        case 1 -> cenarioAtual = cenarios.get(0);
                        case 2 -> cenarioAtual = cenarios.get(1);
                    }

                    escolha = 0;
                }
                case 3 -> ash.getMochila().imprimir();
                case 4 -> ash.getMochila().atualizarNomePokemon(scanner);
                case 5 -> ash.getMochila().removerPokemom();
                default -> System.out.println("Você não pode fazer isso");
            }
        }while(escolha != 8);
    }


    public static List<Cenario> popularCenarios(){
        List<Cenario> cenarios = new ArrayList<>();
        cenarios.add(new Cenario(TiposTerreno.GRAMA,4, Arrays.asList(new PokemonBase("Bulbasaur"
                , 20
                , 6.7
                , 11.0
                , 85.0
                ,5
                , Dificuldades.FACIL
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.FACIL),
                new PokemonBase("Ivysaur"
                        , 20
                        , 13.0
                        , 20.0
                        , 87.5
                        ,16
                        , Dificuldades.MEDIO
                        , TipoPokemon.GRASS
                        , TipoPokemon.POISON
                        , Raridades.MEDIO),
                new PokemonBase("Venusaur"
                        , 20
                        , 100.0
                        , 150.0
                        , 87.5
                        ,32
                        , Dificuldades.DIFICIL
                        , TipoPokemon.GRASS
                        , TipoPokemon.POISON
                        , Raridades.DIFICIL)
                )));

        cenarios.add(new Cenario(TiposTerreno.AGUA,9, Arrays.asList(new PokemonBase("Squirtle"
                        , 20
                        , 9.0
                        , 17.0
                        , 87.5
                        , 5
                        ,Dificuldades.FACIL
                        , TipoPokemon.WATER
                        , null
                        , Raridades.FACIL),
                new PokemonBase("Wartortle"
                        , 20
                        , 22.5
                        , 37.5
                        , 87.5
                        , 16
                        ,Dificuldades.MEDIO
                        , TipoPokemon.WATER
                        , null
                        , Raridades.MEDIO),
                new PokemonBase("Blastoise"
                        , 20
                        , 85.5
                        , 120.0
                        , 87.5
                        , 32
                        ,Dificuldades.DIFICIL
                        , TipoPokemon.WATER
                        , null
                        , Raridades.DIFICIL)
        )));

        return cenarios;
    }

}