package com.dbc.modelo.entidades;

import com.dbc.modelo.enums.Dificuldades;
import com.dbc.modelo.enums.Raridades;
import com.dbc.modelo.enums.TipoPokemon;
import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.interfaces.Impressao;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Pokemon extends Entidade implements Impressao {

    private Integer level;
    //Usamos Enumerations para Padronizar os atributos que influenciam na captura
    private final Dificuldades dificuldade;
    private final TipoPokemon[] tipo;
    private final Raridades raridade;

    public Pokemon(String nome, Integer idade, Double peso, Utils sexo,
                   Dificuldades dificuldade, Integer level, TipoPokemon tipo1, TipoPokemon tipo2,
                   Raridades raridade) {
        super(nome, idade, peso, sexo);
        this.dificuldade = dificuldade;
        this.level = level;
        //checa se o Pokemon tem somente um atributo ou se ele tem dois
        this.tipo = (tipo2 == null) ? new TipoPokemon[]{tipo1} : new TipoPokemon[]{tipo1, tipo2};
        this.raridade = raridade;
    }


    //getter e setters
    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}
    public Dificuldades getDificuldade() {return dificuldade;}
    public TipoPokemon[] getTipo() {return tipo;}
    public String getStringTipos() {
        List<TipoPokemon> n = Arrays.stream(this.getTipo()).toList();
        TipoPokemon a = n.get(0);
        if(n.size() > 1){
            TipoPokemon b = n.get(1);
            return a.toString() + " " + b.toString();
        }
        return a.toString();
    }
    public Raridades getRaridade() {return raridade;}

    @Override
    public void imprimir() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return  "nome:"+ super.getNome() +
                "\nidade: " + super.getIdade() +
                "\npeso: " + df.format(super.getPeso() )+
                "\nsexo: " + ((super.getSexo() == Utils.MASCULINO)?"Masculino":"Feminino") +
                "\nTipo de Pokemon: " + this.getStringTipos() +
                " \nraridade: " + this.getRaridade();
    }
}
