package com.dbc.testes;

import com.dbc.modelo.entidades.Pokemon;
import com.dbc.modelo.entidades.Treinador;
import com.dbc.modelo.enums.TipoPokemon;
import com.dbc.modelo.enums.Utils;
import com.dbc.modelo.objetos.Mochila;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TesteMochila {

    @Test
    public void adicionarUmPokemonNaMochila(){
        Treinador ash = new Treinador("ash", 40, 80.0, Utils.MASCULINO, new Mochila());

        ash.getMochila().adicionarPokemom(new Pokemon(null,null,null,null,null
                ,null, TipoPokemon.POISON, null, null));

        Assert.assertNotNull(ash.getMochila().getBag().get(0));
    }

}
