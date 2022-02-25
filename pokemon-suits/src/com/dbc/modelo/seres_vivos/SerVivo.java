package com.dbc.modelo.seres_vivos;

public abstract class SerVivo {

    private String nome;
    private int idade;
    private double peso;
//    private Utils sexo;
    private String sexo;


    //getters e setters
    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public int getIdade() {return idade;}

    public void setIdade(int idade) {this.idade = idade;}

    public double getPeso() {return peso;}

    public void setPeso(double peso) {this.peso = peso;}

    public String getSexo() {return sexo;}

    public void setSexo(String sexo) {this.sexo = sexo;}
}
