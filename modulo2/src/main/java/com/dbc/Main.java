package com.dbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.dbc.exeptions.BancoDeDadosException;
import com.dbc.exeptions.InvalidCenarioException;
import com.dbc.model.cenario.Cenario;
import com.dbc.model.entidades.Pokemon;
import com.dbc.model.entidades.PokemonBase;
import com.dbc.model.entidades.Treinador;
import com.dbc.model.enums.Dificuldades;
import com.dbc.model.enums.Raridades;
import com.dbc.model.enums.TipoPokemon;
import com.dbc.model.enums.TiposTerreno;
import com.dbc.model.enums.Utils;
import com.dbc.model.interfaces.Pokebola;
import com.dbc.model.objetos.Mochila;
import com.dbc.model.objetos.Pokedex;
import com.dbc.model.pokebolas.GreatBall;
import com.dbc.model.pokebolas.HeavyBall;
import com.dbc.model.pokebolas.MasterBall;
import com.dbc.model.pokebolas.NetBall;
import com.dbc.model.pokebolas.PokeBall;
import com.dbc.repository.MochilaRepository;
import com.dbc.repository.PokemonRepository;
import com.dbc.repository.TreinadorRepository;

public class Main {
	
	public static void main(String[] args) {
		
		MochilaRepository mochilaRepository =  new MochilaRepository();
		PokemonRepository pokemonRepository = new PokemonRepository();
		TreinadorRepository treinadorRepository = new TreinadorRepository(); 
		
		Scanner scanner = new Scanner(System.in);
		Random r = new Random();

		Pokedex pokedex = popularPokedex();

		List<Cenario> cenarios = popularCenarios(pokedex);
		Cenario cenarioAtual = cenarios.get(3);
		
		Mochila mochila = null;
		List<Pokemon> p = null;
		Treinador treinador = null;
		
		try {
			treinador = treinadorRepository.getById(1);
			mochila = mochilaRepository.getById(treinador.getIdMochila());
			p = pokemonRepository.listar();
			mochila.adicionarPokemons(p);
		}catch(Exception e){
			e.printStackTrace();
		}

		treinador.setMochila(mochila);
		Pokebola pokebola = null;

		// contador para chance do pokemon fugir
		int contador = 0;

		int escolha = 0;

		do {
			System.out.println("Digite 1 Procurar Pokemon");
			System.out.println("Digite 2 para mudar de local");
			System.out.println("Digite 3 para listar pokemom ");
			System.out.println("Digite 4 para editar o apelido de um pokemom");
			System.out.println("Digite 5 para assassinar um pokemom");
			System.out.println("Digite 8 para sair");
			escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha) {
			case 1 -> {
				try {
					Pokemon pokemonEncontro = cenarioAtual.gerarPokemon();
					System.out.println("Pokemon encontrado: ");
					pokemonEncontro.imprimir();
					System.out.println("Digite 1 para jogar a pokebola");
					System.out.println("Digite 2 para fugir");
					escolha = scanner.nextInt();
					scanner.nextLine();
					while (escolha != 2) {
						System.out.println("Qual pokebola você quer escolher?");
						System.out.println("1 - Pokeball\n2 - GreatBall\n3 - NetBall\n4 - HeavyBall\n5 - MasterBall");
						escolha = scanner.nextInt();
						scanner.nextLine();
						switch (escolha) {
						case 1 -> {
							if(treinador.getMochila().getQuantidadePokeBalls() > 0) {
								pokebola = new PokeBall();
								treinador.getMochila().setQuantidadePokeBalls((treinador.getMochila().getQuantidadePokeBalls()- 1));
							}
						}
						case 2 -> {
							if(treinador.getMochila().getQuantidadeGreatBalls() > 0) {
								pokebola = new GreatBall();
								treinador.getMochila().setQuantidadeGreatBalls((treinador.getMochila().getQuantidadePokeBalls()- 1));
							}
						}
						case 3 -> {
							if(treinador.getMochila().getQuantidadeNetBalls() > 0) {
								pokebola = new NetBall();
								treinador.getMochila().setQuantidadeNetBalls((treinador.getMochila().getQuantidadePokeBalls()- 1));
							}
						}
						case 4 -> {
							if(treinador.getMochila().getQuantidadeHeavyBalls() > 0) {
								pokebola = new HeavyBall();
								treinador.getMochila().setQuantidadeHeavyBalls((treinador.getMochila().getQuantidadePokeBalls()- 1));
							}
						}
						case 5 -> {
							if(treinador.getMochila().getQuantidadeMasterBalls() > 0) {
								pokebola = new MasterBall();
								treinador.getMochila().setQuantidadeMasterBalls((treinador.getMochila().getQuantidadePokeBalls()- 1));
							}
						}
						default -> {
							pokebola = new PokeBall();
							System.out.println("Nenhuma pokebola válida selecionada. Usando PokeBall normal!");
						}
						}

						if (treinador.capturar(pokebola, pokemonEncontro, r, scanner)) {
							System.out.println("Parabéns, você conseguiu capturar " + pokemonEncontro.getNome());
							escolha = 2;
						} else {
							if (!(r.nextInt(100) < (30 - pokemonEncontro.getDificuldade().getChance())
									* (1 + (1 / (contador + 1))) + 5)) {
								System.out.println("Você fracassou miseravelmente, o que quer fazer agora?\n");
								System.out.println("Digite 1 para jogar a pokebola");
								System.out.println("Digite 2 para fugir");
								contador++;
								escolha = scanner.nextInt();
								scanner.nextLine();
							} else {
								System.out
										.println("O pokemon escapou, capturá-lo era seu único trabalho. Tu é ruim!\n");
								escolha = 2;
							}

						}
					}
					;

					escolha = 0;
				} catch (InvalidCenarioException ex) {
					System.out.println("Esta área não possui pokemons. Mude de área para procurar pokemons.");
					escolha = 0;
				}
			}
			case 2 -> {
				System.out.println("1 - Grama\n2 - Água\n3 - Caverna\n4 - Chão");
				escolha = scanner.nextInt();
				scanner.nextLine();
				switch (escolha) {
				case 1 -> cenarioAtual = cenarios.get(0);
				case 2 -> cenarioAtual = cenarios.get(1);
				case 3 -> cenarioAtual = cenarios.get(2);
				case 4 -> cenarioAtual = cenarios.get(3);
				default -> System.out.println("Não existe essa opção! Favor selecionar um local válido.");
				}

				escolha = 0;
			}
			case 3 -> treinador.getMochila().imprimir();
			case 4 -> treinador.getMochila().atualizarApelidoPokemon(scanner);
			case 5 -> {
				try {
					pokemonRepository.remover(treinador.getMochila().removerPokemom(scanner));
				} catch (BancoDeDadosException e) {
					e.printStackTrace();
				}
			}
			case 8 -> System.out.println("Obrigado por jogar!");
			default -> System.out.println("Você não pode fazer isso");
			}
		} while (escolha != 8);
		
		try {
			treinadorRepository.editar(treinador.getIdTreinador(), treinador);
			mochilaRepository.editar(treinador.getMochila().getIdMochila(), treinador.getMochila());
			
		} catch (Exception e) {
			
		}
		
		
	}

	private static Pokedex popularPokedex() {
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

	private static List<Cenario> popularCenarios(Pokedex pokedex) {
		List<Cenario> cenarios = new ArrayList<>();

		cenarios.add(new Cenario(TiposTerreno.GRAMA, 4, Arrays.asList(pokedex.getPokedexCompleta().get(1),
				pokedex.getPokedexCompleta().get(2), pokedex.getPokedexCompleta().get(3))));
		cenarios.add(new Cenario(TiposTerreno.AGUA, 10, Arrays.asList(pokedex.getPokedexCompleta().get(7),
				pokedex.getPokedexCompleta().get(8), pokedex.getPokedexCompleta().get(9))));
		cenarios.add(new Cenario(TiposTerreno.CAVERNA, 30,
				Arrays.asList(pokedex.getPokedexCompleta().get(4), pokedex.getPokedexCompleta().get(5),
						pokedex.getPokedexCompleta().get(6), pokedex.getPokedexCompleta().get(74),
						pokedex.getPokedexCompleta().get(75), pokedex.getPokedexCompleta().get(76))));
		cenarios.add(new Cenario(TiposTerreno.CHAO, 4, List.of()));

		return cenarios;
	}

}

