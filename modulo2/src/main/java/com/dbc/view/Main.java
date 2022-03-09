package com.dbc.view;

import java.util.Random;
import java.util.Scanner;

import com.dbc.Controller.CenarioController;
import com.dbc.Controller.MochilaController;
import com.dbc.Controller.PokemonController;
import com.dbc.Controller.TreinadorController;
import com.dbc.exeptions.InvalidCenarioException;
import com.dbc.model.entidades.Pokemon;
import com.dbc.model.entidades.Treinador;
import com.dbc.model.interfaces.Pokebola;
import com.dbc.model.pokebolas.GreatBall;
import com.dbc.model.pokebolas.HeavyBall;
import com.dbc.model.pokebolas.MasterBall;
import com.dbc.model.pokebolas.NetBall;
import com.dbc.model.pokebolas.PokeBall;
import com.dbc.repository.TreinadorRepository;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int escolha = 0;
		CenarioController cenarioController = new CenarioController();
		MochilaController mochilaController = new MochilaController();
		PokemonController pokemonController = new PokemonController();
		TreinadorController treinadorController = new TreinadorController();
		
		Treinador treinador = treinadorController.retornarTreinador(1);
		Pokebola pokeboll = null;
		
		
		
		do {
			System.out.println("Digite 1 Procurar Pokemon");
			System.out.println("Digite 2 para mudar de local");
			System.out.println("Digite 3 para listar pokemom ");
			System.out.println("Digite 4 para editar o apelido de um pokemom");
			System.out.println("Digite 5 para assassinar um pokemom");
			System.out.println("Digite 8 para sair");
			escolha = sc.nextInt();
			sc.nextLine();
			switch (escolha) {
			case 1 -> {
				Pokemon pokemonGerado = null;
				try {
					pokemonGerado = cenarioController.gerarPokemon();
				} catch (InvalidCenarioException e) {
					System.out.println("Escolha outro Cenário");
					break;
				}
				System.out.println(pokemonGerado);
				System.out.println("Digite 1 para jogar a pokebola");
				System.out.println("Digite 2 para fugir");
				escolha = sc.nextInt();
				sc.nextLine();
				
				boolean naoCapturou = true;
				
				if(escolha == 2)break;
				int contador = 0;
				do {
					System.out.println("Qual pokebola você quer escolher?");
					System.out.println(mochilaController.retornarPokebolas(treinador.getIdMochila()));
					
					switch (sc.next().toLowerCase()) {
					case "pokeball" ->{
						 mochilaController.usarPokeBall(treinador.getIdMochila());
						 pokeboll = new PokeBall();
					}
					case "greatball" -> {
						mochilaController.usarGreatBall(treinador.getIdMochila());
						pokeboll = new GreatBall();
					}
					case "netball" -> {
						mochilaController.usarNetBall(treinador.getIdMochila());
						pokeboll = new NetBall();
					}
					case "heavyball" -> {
						mochilaController.usarHeavyBall(treinador.getIdMochila());
						pokeboll = new HeavyBall();
					}
					case "masterball" -> {
						mochilaController.usarMasterBall(treinador.getIdMochila());
						pokeboll = new MasterBall();
					}
					default ->
					throw new IllegalArgumentException("Unexpected value");
					}
					
					if (treinadorController.capturar(pokeboll, pokemonGerado, treinador.getIdMochila())) {
						System.out.println("Parabéns, você conseguiu capturar " + pokemonGerado.getNome());
						naoCapturou = false;
					} else {
						if (new Random().nextInt(100) > (30 - pokemonGerado.getDificuldade().getChance())+(contador * 2)) {
							System.out.println("Você fracassou miseravelmente, o que quer fazer agora?\n");
							System.out.println("Digite 1 para jogar a pokebola");
							System.out.println("Digite 2 para fugir");
							contador++;
							escolha = sc.nextInt();
							sc.nextLine();
							if(escolha == 2)naoCapturou = false;
						} else {
							System.out
									.println("O pokemon escapou, capturá-lo era seu único trabalho. Tu é ruim!\n");
							naoCapturou = false;
						}

					}
					
				} while (naoCapturou);
				contador = 0;
				
			}
			
			case 2 -> {
				System.out.println("1 - Grama\n2 - Água\n3 - Caverna\n4 - Chão");
				escolha = sc.nextInt();
				sc.nextLine();
				switch (escolha) {
				case 1 -> cenarioController.alterarCenario(0);
				case 2 -> cenarioController.alterarCenario(1);
				case 3 -> cenarioController.alterarCenario(2);
				case 4 -> cenarioController.alterarCenario(3);
				default -> System.out.println("Não existe essa opção! Favor selecionar um local válido.");
				}

				escolha = 0;
			}
			
			case 3 -> pokemonController.ListarPokemons().forEach(p -> System.out.println("======================\n" + p + "\n============================\n"));
			
			case 4 -> {
				System.out.println("Digite o id do pokemon que deseja alterar o nome");
				pokemonController.ListarPokemons().forEach(p -> System.out.println("======================\n" + p + "\n============================"));
				int c = sc.nextInt();
				sc.nextLine();
				String str = sc.nextLine();
				if(pokemonController.editarPokemon(str, null, c))System.out.println("alterado com sucesso");
			}
			
			case 5 -> {
				System.out.println("qual pokemon você deseja \"retirar\"? (digite o id)");
				pokemonController.ListarPokemons().forEach(p -> System.out.println("======================\n" + p + "\n============================\n"));
				if(pokemonController.RemoverPokemon(sc.nextInt()))System.out.println("pokemon assa... removido");
			}
			case 8 -> System.out.println("Obrigado por jogar!");
			default -> System.out.println("Você não pode fazer isso");
			}
			
		}while(escolha != 8);
		sc.close();
	}

}
