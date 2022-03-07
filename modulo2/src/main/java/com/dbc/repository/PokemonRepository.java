package com.dbc.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dbc.exeptions.BancoDeDadosException;
import com.dbc.model.entidades.Pokemon;

public class PokemonRepository implements Repository<Integer, Pokemon> {

	@Override
	public Integer getProximoId(Connection connection) throws SQLException {
		return null;
	}

	@Override
	public Pokemon adicionar(Pokemon object) throws BancoDeDadosException {
		return null;
	}

	@Override
	public boolean remover(Integer id) throws BancoDeDadosException {
		return false;
	}

	@Override
	public boolean editar(Integer id, Pokemon objeto) throws BancoDeDadosException {
		return false;
	}

	@Override
	public List<Pokemon> listar() throws BancoDeDadosException {
		return null;
	}

}
