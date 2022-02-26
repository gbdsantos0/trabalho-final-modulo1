package com.dbc.modelo;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.entidades.Treinador;
import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.objetos.Mochila;
import com.dbc.modelo.objetos.Pokedex;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random r = new Random();
        Treinador ash = new Treinador("ash", 40, 80.0, Utils.MASCULINO, new Mochila(), new Pokedex());

        int escolha = 0;

        do {
            System.out.println("Digite 1 Procurar Pokemon");
            System.out.println("Digite 2 para mudar de local");
            System.out.println("Digite 3 para listar pokemom ");
            System.out.println("Digite 4 para editar o nome de um pokemom");
            System.out.println("Digite 5 para assassinar um pokemom");
            System.out.println("Digite 8 para sair");
            switch (escolha){
                case 1 -> {
                    
                }
                case 2 -> {

                }
                case 3 -> ash.getMochila().imprimir();
                case 4 -> ash.getMochila().atualizarNomePokemon(scanner);
                case 5 -> ash.getMochila().removerPokemom();
            }
        }while(escolha != 8);
    }
}