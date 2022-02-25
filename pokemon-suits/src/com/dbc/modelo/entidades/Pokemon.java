package com.dbc.modelo.entidades;

import com.dbc.modelo.enums.Dificuldades;
import com.dbc.modelo.enums.Raridades;
import com.dbc.modelo.enums.TipoPokemon;
import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.interfaces.Impressao;

public class Pokemon extends Entidade implements Impressao {

    private final Dificuldades dificuldade;
    private Integer level;
    private final TipoPokemon[] tipo;
    private final Raridades raridade;

    public Pokemon(String nome, Integer idade, Double peso, Utils sexo,
                   Dificuldades dificuldade, Integer level, TipoPokemon tipo1, TipoPokemon tipo2,
                   Raridades raridade) {
        super(nome, idade, peso, sexo);
        this.dificuldade = dificuldade;
        this.level = level;
        this.tipo = (tipo2 == null) ? new TipoPokemon[]{tipo1} : new TipoPokemon[]{tipo1, tipo2};
        this.raridade = raridade;
    }


    //getter e setters
    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}
    public Dificuldades getDificuldade() {return dificuldade;}
    public TipoPokemon[] getTipo() {return tipo;}
    public Raridades getRaridade() {return raridade;}

    @Override
    public void imprimir() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return  "        nome: \n"+ super.getNome() +
                "        idade: \n" + super.getIdade() +
                "        peso: \n" + super.getPeso() +
                "        sexo: \n" + ((super.getSexo() == Utils.MASCULINO)?"Masculino":"Feminino") +
                "        Tipo de Pokemon: \n" + this.getTipo() +
                "        raridade: " + this.getRaridade();
    }
}
