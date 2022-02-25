package com.dbc.modelo.entidades;

import com.dbc.modelo.enums.Dificuldades;
import com.dbc.modelo.enums.Raridades;
import com.dbc.modelo.enums.TipoPokemon;
import com.dbc.modelo.enums.Utils;

public class Pokemon extends Entidade {

    private final Dificuldades dificuldade;
    private int level;
    private final TipoPokemon[] tipo;
    private final Raridades raridade;

    public Pokemon(String nome, int idade, double peso, Utils sexo,
                   Dificuldades dificuldade, int level, TipoPokemon tipo1, TipoPokemon tipo2,
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

}
