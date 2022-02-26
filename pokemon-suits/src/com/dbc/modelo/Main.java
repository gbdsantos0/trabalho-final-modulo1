package com.dbc.modelo;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.entidades.Treinador;
import com.dbc.modelo.enums.Utils;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random r = new Random();
        Treinador ash = new Treinador("ash", 10, 80.0, Utils.MASCULINO, null);

        int escolha = 0;
        while (escolha != 8) {
            System.out.println("Digite 1 para criar pokemom");
            System.out.println("Digite 2 para listar pokemom");
            System.out.println("Digite 3 para editar um pokemom");
            System.out.println("Digite 4 para assassinar um pokemom");
            System.out.println("Digite 8 para sair");
            escolha = scanner.nextInt();
            scanner.nextLine();
            int atualizar = 0;
            switch (escolha) {
                case 1:
                    ash.getMochila().getBag().add(new Pokemon(null, null, null, null, null, null, null, null, null));
                    break;

                case 2:
                    ash.getMochila().getBag().forEach(pokemom -> System.out.println(pokemom));
                    break;

                case 3:
                    System.out.println("digite 1 para mudar o nome: ");
                    System.out.println("Digite 2 para mudar a idade: ");
                    System.out.println("Digite 3 para mudar o peso: ");
                    System.out.println("Digite 4 para mudar o level: ");
                    switch (atualizar) {
                        case 1:
                            System.out.println("Qual pokemom deseja renomear:");
                            ash.getMochila().getBag().forEach(pokemom -> System.out.println(pokemom));
                            int idPokemomRenomeado = scanner.nextInt();
                            scanner.nextLine();

                            Pokemon pokemonRenomeado = new Pokemon(null, null, null, null, null, null, null, null, null);
                            System.out.println("Digite o novo nome: ");
                            pokemonRenomeado.setNome(scanner.nextLine());
                            break;

                        case 2:
                            System.out.println("Qual pokemom deseja mudar idade: ");
                            ash.getMochila().getBag().forEach(pokemom -> System.out.println(pokemom));
                            int idPokemomNovaIdade = scanner.nextInt();
                            scanner.nextLine();

                            Pokemon pokemonNovaIdade = new Pokemon(null, null, null, null, null, null, null, null, null);
                            System.out.println("Digite uma nova idade: ");
                            pokemonNovaIdade.setIdade(scanner.nextInt());
                            break;

                        case 3:
                            System.out.println("Qual pokemom deseja mudar peso: ");
                            ash.getMochila().getBag().forEach(pokemom -> System.out.println(pokemom));
                            int idPokemomPeso = scanner.nextInt();
                            scanner.nextLine();

                            Pokemon pokemonNovoPeso = new Pokemon(null, null, null, null, null, null, null, null, null);
                            System.out.println("Digite um novo peso: ");
                            pokemonNovoPeso.setPeso(scanner.nextDouble());
                            break;

                        case 4:
                            System.out.println("Qual pokemom deseja mudar level: ");
                            ash.getMochila().getBag().forEach(pokemom -> System.out.println(pokemom));
                            int idPokemomLevel = scanner.nextInt();
                            scanner.nextLine();

                            Pokemon pokemonNovoLevel = new Pokemon(null, null, null, null, null, null, null, null, null);
                            System.out.println("Digite um novo level: ");
                            pokemonNovoLevel.setLevel(scanner.nextInt());
                            break;

                        default:
                            System.out.println("opção invalida!");
                            break;
                    }

                case 4:
                    System.out.println("qual pokemom você deseja assassinar friamente: ");
                    ash.getMochila().getBag().forEach(pokemom -> System.out.println(pokemom));
                    int pokemomId = scanner.nextInt();
                    ash.getMochila().getBag().remove(pokemomId);
                    break;

                case 8:
                    break;

                default:
                    System.out.println("opção invalida!");
                    break;


            }


        }
    }
}