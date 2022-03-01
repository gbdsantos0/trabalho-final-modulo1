package com.dbc.modelo.enums;

public enum Raridades {

    COMUM(30),
    RARO(15),
    SUPER_RARO(5);

    public int chance;

    Raridades(int chance){
        this.chance = chance;
    }

    public int getChance() {return chance;}
}
