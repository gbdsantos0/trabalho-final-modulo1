package com.dbc.modelo.enums;

public enum Raridades {

    FACIL(30),
    MEDIO(15),
    DIFICIL(5);

    public int chance;

    Raridades(int chance){
        this.chance = chance;
    }

    public int getChance() {return chance;}
}
