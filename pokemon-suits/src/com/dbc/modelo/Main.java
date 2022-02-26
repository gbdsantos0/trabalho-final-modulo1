package com.dbc.modelo;

import com.dbc.modelo.entidades.Treinador;
import com.dbc.modelo.enums.Utils;

import java.util.Random;

public class Main {

    public static void main(String[] args) {


        Random r = new Random();
        Treinador ash = new Treinador("ash",10,80.0, Utils.MASCULINO, null);

        ash.capturar(null, null, r);


    }
}
