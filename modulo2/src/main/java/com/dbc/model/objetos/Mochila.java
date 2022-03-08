package com.dbc.model.objetos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.dbc.model.entidades.Pokemon;
import com.dbc.model.interfaces.Impressao;

public class Mochila implements Impressao {
    private ArrayList<Pokemon> bag;
    private int quantidadeGreatBalls;
    private int quantidadeHeavyBalls;
    private int quantidadeMasterBalls;
    private int quantidadeNetBalls;
    private int quantidadePokeBalls;

    public Mochila() {
        this.bag = new ArrayList<>();
        this.quantidadeGreatBalls = 0;
        this.quantidadeHeavyBalls = 0;
        this.quantidadeMasterBalls = 0;
        this.quantidadeNetBalls = 0;
        this.quantidadePokeBalls = 0;
    }


    private Optional<Pokemon> pesquisarPorNome(String nomePokemon){
        return this.bag.stream().filter( p -> p.getNome().equalsIgnoreCase(nomePokemon)).findFirst(); 
    }
    //CIRAR
    public void adicionarPokemom(Pokemon p){
        this.bag.add(p);
    }

    //ATUALIZAR
    public void atualizarApelidoPokemon(Scanner scanner){
        System.out.println("Qual pokemon deseja renomear:");
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
        System.out.println("qual pokemon você deseja assassinar friamente: ");
        this.imprimir();
        System.out.println();
        Optional<Pokemon> pokemon = this.pesquisarPorNome(scanner.nextLine());

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

    public int getQuantidadeGreatBalls() {
        return quantidadeGreatBalls;
    }

    public int getQuantidadeHeavyBalls() {
        return quantidadeHeavyBalls;
    }

    public int getQuantidadeMasterBalls() {
        return quantidadeMasterBalls;
    }

    public int getQuantidadeNetBalls() {
        return quantidadeNetBalls;
    }

    public int getQuantidadePokeBalls() {
        return quantidadePokeBalls;
    }

    //setters
    public void setQuantidadeGreatBalls(int quantidadeGreatBalls) {
        if(quantidadeGreatBalls>=0 && quantidadeGreatBalls<=99){
            this.quantidadeGreatBalls = quantidadeGreatBalls;
        }else{
            System.out.println("Não é possível modificar a quantidade para valores negativos ou maiores que 99");
        }
    }

    public void setQuantidadeHeavyBalls(int quantidadeHeavyBalls) {
        if(quantidadeHeavyBalls>=0 && quantidadeHeavyBalls<=99){
            this.quantidadeHeavyBalls = quantidadeHeavyBalls;
        }else{
            System.out.println("Não é possível modificar a quantidade para valores negativos ou maiores que 99");
        }
    }

    public void setQuantidadeMasterBalls(int quantidadeMasterBalls) {
        if(quantidadeMasterBalls>=0 && quantidadeMasterBalls<=99){
            this.quantidadeMasterBalls = quantidadeMasterBalls;
        }else{
            System.out.println("Não é possível modificar a quantidade para valores negativos ou maiores que 99");
        }
    }

    public void setQuantidadeNetBalls(int quantidadeNetBalls) {
        if(quantidadeNetBalls>=0 && quantidadeNetBalls<=99){
            this.quantidadeNetBalls = quantidadeNetBalls;
        }else{
            System.out.println("Não é possível modificar a quantidade para valores negativos ou maiores que 99");
        }
    }

    public void setQuantidadePokeBalls(int quantidadePokeBalls) {
        if(quantidadePokeBalls>=0 && quantidadePokeBalls<=99){
            this.quantidadePokeBalls = quantidadePokeBalls;
        }else{
            System.out.println("Não é possível modificar a quantidade para valores negativos ou maiores que 99");
        }
    }
}








