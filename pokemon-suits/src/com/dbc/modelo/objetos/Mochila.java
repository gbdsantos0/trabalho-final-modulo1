package com.dbc.modelo.objetos;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.interfaces.Impressao;

import java.util.*;

public class Mochila implements Impressao {
    private ArrayList<Pokemon> bag = new ArrayList<>();


    private Optional<Pokemon> pesquisarPorNome(String nomePokemon){
        return this.bag.stream().filter( p -> p.getNome().equalsIgnoreCase(nomePokemon)).findFirst();
    
    }
    //CIRAR
    public void adicionarPokemom(Pokemon p){
        this.bag.add(p);
    }

    //ATUALIZAR
    public void atualizarNomePokemon(Scanner scanner){
        System.out.println("Qual pokemom deseja renomear:");
        this.imprimir();
        System.out.println();
        Optional<Pokemon> pokemon = this.pesquisarPorNome(scanner.nextLine());

        if(pokemon.isPresent()){
            System.out.println("Digite um novo Nome: ");
            Pokemon p = pokemon.get();
            this.bag.remove(p);
            p.setNome(scanner.nextLine());
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
        Optional<Pokemon> pokemon = this.pesquisarPorNome(scanner.nextLine());

        if(pokemon.isPresent()){
            System.out.println("Digite um novo Nome: ");
            this.bag.remove(pokemon.get());
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








