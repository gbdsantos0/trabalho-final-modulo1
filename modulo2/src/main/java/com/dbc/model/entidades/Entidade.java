package com.dbc.model.entidades;

import com.dbc.model.enums.Utils;

public abstract class Entidade {

    private String nome;
    private Integer idade;
    private Double peso;
    private final Utils sexo;

    public Entidade(String nome, Integer idade, Double peso, Utils sexo) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.sexo = sexo;
    }

    //getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Utils getSexo() {
        return sexo;
    }
}
