package com.dbc.modelo.entidades;

import com.dbc.modelo.enums.Utils;

public abstract class Entidade {

    private String nome;
    private int idade;
    private double peso;
    private final Utils sexo;

    public Entidade(String nome, int idade, double peso, Utils sexo) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.sexo = sexo;
    }

    //getters e setters

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public int getIdade() {return idade;}

    public void setIdade(int idade) {this.idade = idade;}

    public double getPeso() {return peso;}

    public void setPeso(double peso) {this.peso = peso;}

    public Utils getSexo() {return sexo;}

}
