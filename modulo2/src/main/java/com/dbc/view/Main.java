package com.dbc.view;

import java.util.Scanner;

import com.dbc.Controller.CenarioController;
import com.dbc.Controller.MochilaController;
import com.dbc.Controller.PokemonController;
import com.dbc.exeptions.InvalidCenarioException;
import com.dbc.model.entidades.Pokemon;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int escolha = 0;
		CenarioController cenarioController = new CenarioController();
		MochilaController mochilaController = new MochilaController();
		PokemonController pokemonController = new PokemonController();
		
		
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
				
				do {
					System.out.println("Qual pokebola você quer escolher?");
					cenarioController.re
					
					switch (sc.nextLine().toLowerCase()) {
					case "pokeball" ->{
						mochilaController.usarPokeBall(escolha)
					}
					case "greatBall" -> {

					}
					case "netBall" -> {

					}
					case "heavyBall" -> {

					}
					case "masterBall" -> {

					}
					
					default ->
					throw new IllegalArgumentException("Unexpected value: " + sc.nextLine().toLowerCase());
					}
					
				} while (naoCapturou);
				
			}
			
			
			
			default ->
			throw new IllegalArgumentException("Unexpected value: " + escolha);
			}
			
		}while(escolha != 8);
		
	}

}
