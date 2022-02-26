package com.dbc.modelo.objetos;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.enums.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mochila {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Pokemon> bag = new ArrayList<>();


    public ArrayList<Pokemon> getBag() {
        return bag;
    }



    public void criarPokemom(){

    }

    public void consultarPokedex(){
        bag.forEach(pokemom -> System.out.println(pokemom));
    }

    public void editarPokemon(Scanner scanner){
        int atualizar = 0;
        System.out.println("digite 1 para mudar o nome: ");
        System.out.println("Digite 2 para mudar a idade: ");
        System.out.println("Digite 3 para mudar o peso: ");
        System.out.println("Digite 4 para mudar o level: ");
        switch (atualizar) {
            case 1:
                System.out.println("Qual pokemom deseja renomear:");
                this.bag.forEach(pokemom -> System.out.println(pokemom));
                int idPokemomRenomeado = scanner.nextInt();
                scanner.nextLine();

                Pokemon pokemonRenomeado = new Pokemon(null, null, null, null, null, null, null, null, null);
                System.out.println("Digite o novo nome: ");
                pokemonRenomeado.setNome(scanner.nextLine());
                break;

            case 2:
                System.out.println("Qual pokemom deseja mudar idade: ");
                this.bag.forEach(pokemom -> System.out.println(pokemom));
                int idPokemomNovaIdade = scanner.nextInt();
                scanner.nextLine();

                Pokemon pokemonNovaIdade = new Pokemon(null, null, null, null, null, null, null, null, null);
                System.out.println("Digite uma nova idade: ");
                pokemonNovaIdade.setIdade(scanner.nextInt());
                break;

            case 3:
                System.out.println("Qual pokemom deseja mudar peso: ");
                this.bag.forEach(pokemom -> System.out.println(pokemom));
                int idPokemomPeso = scanner.nextInt();
                scanner.nextLine();

                Pokemon pokemonNovoPeso = new Pokemon(null, null, null, null, null, null, null, null, null);
                System.out.println("Digite um novo peso: ");
                pokemonNovoPeso.setPeso(scanner.nextDouble());
                break;
        }
    }

    public void removerPokemom(){
        System.out.println("qual pokemom você deseja assassinar friamente: ");
        this.bag.forEach(pokemom -> System.out.println(pokemom));
        int pokemomId = scanner.nextInt();
        this.bag.remove(pokemomId);
    }
}








