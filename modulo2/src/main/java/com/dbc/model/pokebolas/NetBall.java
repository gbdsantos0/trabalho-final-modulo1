package com.dbc.model.pokebolas;


import com.dbc.model.entidades.Pokemon;
import com.dbc.model.enums.TipoPokemon;
import com.dbc.model.interfaces.Pokebola;
public class NetBall implements Pokebola {
    //Testa se o pokemon é do tipo WATER ou BUG para retornar 3x chance
    @Override
    public Double calcularChance(Pokemon pokemon){
        if(pokemon.getTipo()[0]==TipoPokemon.WATER||pokemon.getTipo()[0]==TipoPokemon.BUG||pokemon.getTipo()[1]==TipoPokemon.WATER||pokemon.getTipo()[1]==TipoPokemon.BUG){
            return 3.0 * pokemon.getDificuldade().getChance();
        }
        return 1.0 * pokemon.getDificuldade().getChance();
    }
}
