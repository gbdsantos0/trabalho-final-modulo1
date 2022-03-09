package com.dbc.Controller;

import com.dbc.exeptions.BancoDeDadosException;
import com.dbc.model.objetos.Mochila;
import com.dbc.repository.MochilaRepository;
import com.dbc.repository.PokemonRepository;


public class MochilaController {

    public void adicionarGreatBall(int quantidade, int idMochila){
        MochilaRepository mochilaRepository = new MochilaRepository();
        try {
            Mochila mochila = mochilaRepository.getById(idMochila);
            if (mochila!=null) {

                mochila.setQuantidadeGreatBalls(mochila.getQuantidadeGreatBalls()+quantidade);

                mochilaRepository.editar(mochila.getIdMochila(),mochila);
            }
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public boolean usarGreatBall(int idMochila){
        MochilaRepository mochilaRepository = new MochilaRepository();
        try {
            Mochila mochila = mochilaRepository.getById(idMochila);
            if (mochila!=null) {
                if(mochila.setQuantidadeGreatBalls(mochila.getQuantidadeGreatBalls()-1)){
                    mochilaRepository.editar(mochila.getIdMochila(),mochila);
                    return true;
                }
            }
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return false;
    }

    public void adicionarHeavyBall(int quantidade, int idMochila){
        MochilaRepository mochilaRepository = new MochilaRepository();
        try {
            Mochila mochila = mochilaRepository.getById(idMochila);
            if (mochila!=null) {

                mochila.setQuantidadeHeavyBalls(mochila.getQuantidadeHeavyBalls()+quantidade);

                mochilaRepository.editar(mochila.getIdMochila(),mochila);
            }
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public boolean usarHeavyBall(int idMochila){
        MochilaRepository mochilaRepository = new MochilaRepository();
        try {
            Mochila mochila = mochilaRepository.getById(idMochila);
            if (mochila!=null) {
                if(mochila.setQuantidadeHeavyBalls(mochila.getQuantidadeHeavyBalls()-1)){
                    mochilaRepository.editar(mochila.getIdMochila(),mochila);
                    return true;
                }
            }
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return false;
    }

    public void adicionarMasterBall(int quantidade, int idMochila){
        MochilaRepository mochilaRepository = new MochilaRepository();
        try {
            Mochila mochila = mochilaRepository.getById(idMochila);
            if (mochila!=null) {

                mochila.setQuantidadeMasterBalls(mochila.getQuantidadeMasterBalls()+quantidade);

                mochilaRepository.editar(mochila.getIdMochila(),mochila);
            }
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public boolean usarMasterBall(int idMochila){
        MochilaRepository mochilaRepository = new MochilaRepository();
        try {
            Mochila mochila = mochilaRepository.getById(idMochila);
            if (mochila!=null) {
                if(mochila.setQuantidadeMasterBalls(mochila.getQuantidadeMasterBalls()-1)){
                    mochilaRepository.editar(mochila.getIdMochila(),mochila);
                    return true;
                }
            }
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return false;
    }

    public void adicionarNetBall(int quantidade, int idMochila){
        MochilaRepository mochilaRepository = new MochilaRepository();
        try {
            Mochila mochila = mochilaRepository.getById(idMochila);
            if (mochila!=null) {

                mochila.setQuantidadeNetBalls(mochila.getQuantidadeNetBalls()+quantidade);

                mochilaRepository.editar(mochila.getIdMochila(),mochila);
            }
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public boolean usarNetBall(int idMochila){
        MochilaRepository mochilaRepository = new MochilaRepository();
        try {
            Mochila mochila = mochilaRepository.getById(idMochila);
            if (mochila!=null) {
                if(mochila.setQuantidadeNetBalls(mochila.getQuantidadeNetBalls()-1)){
                    mochilaRepository.editar(mochila.getIdMochila(),mochila);
                    return true;
                }
            }
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return false;
    }

    public void adicionarPokeBall(int quantidade, int idMochila){
        MochilaRepository mochilaRepository = new MochilaRepository();
        try {
            Mochila mochila = mochilaRepository.getById(idMochila);
            if (mochila!=null) {

                mochila.setQuantidadePokeBalls(mochila.getQuantidadePokeBalls()+quantidade);

                mochilaRepository.editar(mochila.getIdMochila(),mochila);
            }
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public boolean usarPokeBall(int idMochila){
        MochilaRepository mochilaRepository = new MochilaRepository();
        try {
            Mochila mochila = mochilaRepository.getById(idMochila);
            if (mochila!=null) {
                if(mochila.setQuantidadePokeBalls(mochila.getQuantidadePokeBalls()-1)){
                    mochilaRepository.editar(mochila.getIdMochila(),mochila);
                    return true;
                }
            }
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return false;
    }

    public String retornarPokebolas(int idMochila){
        String pokebolasDisponiveis = "";
        MochilaRepository mr = new MochilaRepository();
        Mochila mochila;
        try{
            mochila = mr.getById(idMochila);
        }catch (Exception e){
            return pokebolasDisponiveis;
        }

        if(mochila.getQuantidadePokeBalls()>0){
            pokebolasDisponiveis+="PokeBalls = " + mochila.getQuantidadePokeBalls();
        }

        if(mochila.getQuantidadeGreatBalls()>0){
            pokebolasDisponiveis+="GreatBalls = " + mochila.getQuantidadeGreatBalls();
        }

        if(mochila.getQuantidadeHeavyBalls()>0){
            pokebolasDisponiveis+="HeavyBalls = " + mochila.getQuantidadeHeavyBalls();
        }

        if(mochila.getQuantidadeNetBalls()>0){
            pokebolasDisponiveis+="NetBalls = " + mochila.getQuantidadeNetBalls();
        }

        if(mochila.getQuantidadeMasterBalls()>0){
            pokebolasDisponiveis+="MasterBalls = " + mochila.getQuantidadeMasterBalls();
        }

        if(pokebolasDisponiveis.equalsIgnoreCase("")){
            pokebolasDisponiveis = "Nenhuma Pokebola disponível";
        }

        return pokebolasDisponiveis;
    }
    
}
