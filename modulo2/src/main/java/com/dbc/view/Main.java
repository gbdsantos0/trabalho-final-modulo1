package com.dbc.view;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int escolha = 0;
		
		do {
			System.out.println("Digite 1 Procurar Pokemon");
			System.out.println("Digite 2 para mudar de local");
			System.out.println("Digite 3 para listar pokemom ");
			System.out.println("Digite 4 para editar o apelido de um pokemom");
			System.out.println("Digite 5 para assassinar um pokemom");
			System.out.println("Digite 8 para sair");
			escolha = sc.nextInt();
			sc.nextLine();
			
			
		}while(true);
		
	}

}
