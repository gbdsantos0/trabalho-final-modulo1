package com.dbc.model.interfaces;

import com.dbc.model.entidades.Pokemon;

public interface Pokebola {
    //Deve retornar um valor de 0 a 100%(pode passar, mas nao tem problema pois o calculo nao gera valores maiores que 100 na comparacao)
    public Double calcularChance(Pokemon pokemon);
}