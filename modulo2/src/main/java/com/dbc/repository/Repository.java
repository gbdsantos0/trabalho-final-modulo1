package com.dbc.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dbc.exeptions.BancoDeDadosException;

public interface Repository<KEY, OBJECT> {
	
	Integer getProximoId(Connection connection) throws SQLException;

	OBJECT adicionar(OBJECT object) throws BancoDeDadosException;

    boolean remover(KEY id) throws BancoDeDadosException;

    boolean editar(KEY id, OBJECT objeto) throws BancoDeDadosException;

    List<OBJECT> listar() throws BancoDeDadosException;
	
}
