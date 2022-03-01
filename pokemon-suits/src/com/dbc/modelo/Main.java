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


        Pokedex pokedex = popularPokedex();

        List<Cenario> cenarios = popularCenarios();
        Cenario cenarioAtual = cenarios.get(0);

        Treinador ash = new Treinador("ash", 40, 80.0, Utils.MASCULINO, new Mochila(), pokedex);

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
                                default -> {
                                    pokebola = new PokeBall();
                                    System.out.println("Nenhuma pokebola válida selecionada. Usando PokeBall normal!");
                                }
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
                        escolha = 0;
                    }
                }
                case 2 -> {
                    System.out.println("1 - Grama\n2 - Água\n3 - Caverna\n4 - Chão");
                    escolha = scanner.nextInt();
                    switch (escolha){
                        case 1 -> cenarioAtual = cenarios.get(0);
                        case 2 -> cenarioAtual = cenarios.get(1);
                        case 3 -> cenarioAtual = cenarios.get(2);
                        case 4 -> cenarioAtual = cenarios.get(3);
                        default -> System.out.println("Não existe essa opção! Favor selecionar um local válido.");
                    }

                    escolha = 0;
                }
                case 3 -> ash.getMochila().imprimir();
                case 4 -> ash.getMochila().atualizarNomePokemon(scanner);
                case 5 -> ash.getMochila().removerPokemom();
                case 8 -> System.out.println("Obrigado por jogar!");
                default -> System.out.println("Você não pode fazer isso");
            }
        }while(escolha != 8);
    }

    private static Pokedex popularPokedex() {
        Map<Integer,PokemonBase> mapPokemons = new HashMap<>();
        mapPokemons.put(1,new PokemonBase("Bulbasoro"
                , 20
                , 6.7
                , 11.0
                , 87.5
                ,5
                , Dificuldades.FACIL
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.COMUM));
        mapPokemons.put(2,new PokemonBase("Ivysoro"
                        , 20
                        , 13.0
                        , 20.0
                        , 87.5
                        ,16
                        , Dificuldades.MEDIO
                        , TipoPokemon.GRASS
                        , TipoPokemon.POISON
                        , Raridades.RARO));
        mapPokemons.put(3
                ,new PokemonBase("Venusoro"
                        , 20
                        , 100.0
                        , 150.0
                        , 87.5
                        ,32
                        , Dificuldades.DIFICIL
                        , TipoPokemon.GRASS
                        , TipoPokemon.POISON
                        , Raridades.SUPER_RARO));
        mapPokemons.put(4
                ,new PokemonBase("Charmandoro"
                        , 20
                        , 8.5
                        , 15.5
                        , 87.5
                        ,5
                        , Dificuldades.FACIL
                        , TipoPokemon.FIRE
                        , null
                        , Raridades.COMUM));
        mapPokemons.put(5
                ,new PokemonBase("Charmeleoro"
                        , 20
                        , 19.0
                        , 36.0
                        , 87.5
                        ,16
                        , Dificuldades.MEDIO
                        , TipoPokemon.FIRE
                        , null
                        , Raridades.RARO));
        mapPokemons.put(6
                ,new PokemonBase("Charizoro"
                        , 20
                        , 90.5
                        , 142.5
                        , 87.5
                        ,36
                        , Dificuldades.DIFICIL
                        , TipoPokemon.FIRE
                        , TipoPokemon.DRAGON
                        , Raridades.SUPER_RARO));
        mapPokemons.put(7
                ,new PokemonBase("Squirsoro"
                        , 20
                        , 9.0
                        , 17.0
                        , 87.5
                        , 5
                        ,Dificuldades.FACIL
                        , TipoPokemon.WATER
                        , null
                        , Raridades.COMUM));
        mapPokemons.put(8
                ,new PokemonBase("Wartosoro"
                        , 20
                        , 22.5
                        , 37.5
                        , 87.5
                        , 16
                        ,Dificuldades.MEDIO
                        , TipoPokemon.WATER
                        , null
                        , Raridades.RARO));
        mapPokemons.put(9
                ,new PokemonBase("Blastoro"
                        , 20
                        , 85.5
                        , 120.0
                        , 87.5
                        , 36
                        ,Dificuldades.DIFICIL
                        , TipoPokemon.WATER
                        , null
                        , Raridades.SUPER_RARO));
        mapPokemons.put(74
                ,new PokemonBase("Geodoro"
                        , 20
                        , 20.0
                        , 38.0
                        , 50.0
                        , 5
                        ,Dificuldades.FACIL
                        , TipoPokemon.ROCK
                        , TipoPokemon.GROUND
                        , Raridades.COMUM));
        mapPokemons.put(75
                ,new PokemonBase("Gravoro"
                        , 20
                        , 105.0
                        , 170.0
                        , 50.0
                        , 16
                        ,Dificuldades.MEDIO
                        , TipoPokemon.ROCK
                        , TipoPokemon.GROUND
                        , Raridades.RARO));
        mapPokemons.put(76
                ,new PokemonBase("Goloro"
                        , 20
                        , 300.0
                        , 420.0
                        , 50.0
                        , 36
                        ,Dificuldades.DIFICIL
                        , TipoPokemon.ROCK
                        , TipoPokemon.GROUND
                        , Raridades.SUPER_RARO));

        Pokedex pokedex = new Pokedex(mapPokemons);

        return pokedex;
    }

    private static List<Cenario> popularCenarios() {
        List<Cenario> cenarios = new ArrayList<>();

        cenarios.add(new Cenario(TiposTerreno.GRAMA, 4, Arrays.asList(Pokedex.getPokedexCompleta().get(1), Pokedex.getPokedexCompleta().get(2), Pokedex.getPokedexCompleta().get(3))));
        cenarios.add(new Cenario(TiposTerreno.AGUA, 10, Arrays.asList(Pokedex.getPokedexCompleta().get(7), Pokedex.getPokedexCompleta().get(8), Pokedex.getPokedexCompleta().get(9))));
        cenarios.add(new Cenario(TiposTerreno.CAVERNA, 30, Arrays.asList(Pokedex.getPokedexCompleta().get(4), Pokedex.getPokedexCompleta().get(5), Pokedex.getPokedexCompleta().get(6),
                Pokedex.getPokedexCompleta().get(74), Pokedex.getPokedexCompleta().get(75), Pokedex.getPokedexCompleta().get(76))));
        cenarios.add(new Cenario(TiposTerreno.CHAO, 4, List.of()));

        return cenarios;
    }


/*    public static List<Cenario> popularCenarios(){
        List<Cenario> cenarios = new ArrayList<>();
        cenarios.add(new Cenario(TiposTerreno.GRAMA,4, Arrays.asList(new PokemonBase("Bulbasaur"
                , 20
                , 6.7
                , 11.0
                , 85.0
                ,5
                , Dificuldades.COMUM
                , TipoPokemon.GRASS
                , TipoPokemon.POISON
                , Raridades.COMUM),
                new PokemonBase("Ivysaur"
                        , 20
                        , 13.0
                        , 20.0
                        , 87.5
                        ,16
                        , Dificuldades.RARO
                        , TipoPokemon.GRASS
                        , TipoPokemon.POISON
                        , Raridades.RARO),
                new PokemonBase("Venusaur"
                        , 20
                        , 100.0
                        , 150.0
                        , 87.5
                        ,32
                        , Dificuldades.SUPER_RARO
                        , TipoPokemon.GRASS
                        , TipoPokemon.POISON
                        , Raridades.SUPER_RARO)
                )));

        cenarios.add(new Cenario(TiposTerreno.AGUA,9, Arrays.asList(new PokemonBase("Squirtle"
                        , 20
                        , 9.0
                        , 17.0
                        , 87.5
                        , 5
                        ,Dificuldades.COMUM
                        , TipoPokemon.WATER
                        , null
                        , Raridades.COMUM),
                new PokemonBase("Wartortle"
                        , 20
                        , 22.5
                        , 37.5
                        , 87.5
                        , 16
                        ,Dificuldades.RARO
                        , TipoPokemon.WATER
                        , null
                        , Raridades.RARO),
                new PokemonBase("Blastoise"
                        , 20
                        , 85.5
                        , 120.0
                        , 87.5
                        , 32
                        ,Dificuldades.SUPER_RARO
                        , TipoPokemon.WATER
                        , null
                        , Raridades.SUPER_RARO)
        )));

        return cenarios;
    }*/

}