package com.dbc.model.objetos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.dbc.model.entidades.Pokemon;
import com.dbc.model.interfaces.Impressao;

public class Mochila implements Impressao {
    private ArrayList<Pokemon> bag = new ArrayList<>();


    private Optional<Pokemon> pesquisarPorApelido(String nomePokemon){
        return this.bag.stream().filter( p -> p.getApelido().equalsIgnoreCase(nomePokemon)).findFirst();
    
    }
    //CIRAR
    public void adicionarPokemom(Pokemon p){
        this.bag.add(p);
    }

    //ATUALIZAR
    public void atualizarApelidoPokemon(Scanner scanner){
        System.out.println("Qual pokemom deseja renomear:");
        this.imprimir();
        System.out.println();
        Optional<Pokemon> pokemon = this.pesquisarPorApelido(scanner.nextLine());

        if(pokemon.isPresent()){
            System.out.println("Digite um novo Nome: ");
            Pokemon p = pokemon.get();
            this.bag.remove(p);
            p.setApelido(scanner.nextLine());
            this.bag.add(p);
        }else{
            System.out.println("Este pokemon não existe!! ");
        }
    }

    //REMOVER
    public void removerPokemom(Scanner scanner){
        System.out.println("qual pokemom você deseja assassinar friamente: ");
        this.imprimir();
        System.out.println();
        Optional<Pokemon> pokemon = this.pesquisarPorApelido(scanner.nextLine());

        if(pokemon.isPresent()){
            this.bag.remove(pokemon.get());
            System.out.println("Este pokemon \"retirado\"!! ");
        }else{
            System.out.println("Este pokemon não existe!! ");
        }
    }
    //MOSTRAR
    public void imprimir() {
        this.bag.forEach(p -> System.out.println("======================\n" + p + "\n============================\n"));
    }

    //getter
    public List<Pokemon> getBag() {
        //retorna uma lista não modificavel;
        return Collections.unmodifiableList(this.bag);
    }
}







