package com.dbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dbc.exeptions.BancoDeDadosException;
import com.dbc.model.entidades.Pokemon;
import com.dbc.model.enums.Dificuldades;
import com.dbc.model.enums.Raridades;
import com.dbc.model.enums.TipoPokemon;
import com.dbc.model.enums.Utils;

public class PokemonRepository implements Repository<Integer, Pokemon> {
	
	@Override
	public Integer getProximoId(Connection connection) throws SQLException {
		 String sql = "SELECT seq_pokemon.nextval mysequence from SEQ_POKEMON";

	        Statement stmt = connection.createStatement();
	        ResultSet res = stmt.executeQuery(sql);
	        
	        if (res.next()) {
	            return res.getInt("mysequence");
	        }
	        return null;
	}

	@Override
	public Pokemon adicionar(Pokemon pokemon) throws BancoDeDadosException {
		Connection con = null;
        try {
            con = BdConnection.getConnection();

            Integer proximoId = this.getProximoId(con);
            pokemon.setId(proximoId);

            String sql = "INSERT INTO POKEMON\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, getProximoId(con));
            stmt.setString(2, pokemon.getPokemon());
            stmt.setInt(3, pokemon.getIdade());
            stmt.setDouble(4, pokemon.getPeso());
            stmt.setString(5, (pokemon.getSexo()==Utils.MASCULINO)?"M":"F");
            stmt.setString(6, pokemon.getNome());
            stmt.setInt(7, (pokemon.getDificuldade()==Dificuldades.DIFICIL)?3:(pokemon.getDificuldade()==Dificuldades.MEDIO?2:1));
            stmt.setInt(8, pokemon.getLevel());
            stmt.setInt(9, pokemon.getTipo()[1].getValor());
            stmt.setInt(10, (pokemon.getTipo().length > 1)?pokemon.getTipo()[1].getValor():null);
            stmt.setInt(11, (pokemon.getRaridade()==Raridades.SUPER_RARO)?3:(pokemon.getRaridade()==Raridades.RARO?2:1));
            stmt.setInt(12, pokemon.getIdMochila());
            
            stmt.executeUpdate();
            return pokemon;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public boolean remover(Integer id) throws BancoDeDadosException {
		Connection con = null;
        try {
            con = BdConnection.getConnection();

            String sql = "DELETE FROM POKEMON WHERE id_pokemon = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public boolean editar(Integer id, Pokemon pokemon) throws BancoDeDadosException {
		Connection con = null;
        try {
            con = BdConnection.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE POKEMON SET ");
            sql.append(" nome = ?,");
            sql.append(" WHERE id_pokemon = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, pokemon.getNome());
            stmt.setInt(2, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public List<Pokemon> listar() throws BancoDeDadosException {
		 List<Pokemon> pokemons = new ArrayList<>();
	        Connection con = null;
	        try {
	            con = BdConnection.getConnection();
	            Statement stmt = con.createStatement();

	            String sql = "SELECT * FROM POKEMON";

	            // Executa-se a consulta
	            ResultSet res = stmt.executeQuery(sql);

	            while (res.next()) {
	            	int a = res.getInt("tipo1");
	            	int b = res.getInt("tipo2");
	            	Pokemon pokemon = new Pokemon(
	            			res.getString("apelido"),
	            			res.getInt("idade"),
	            			res.getDouble("peso"),
	            			(res.getString("sexo").equalsIgnoreCase("M"))?Utils.MASCULINO:Utils.FEMININO,
	            			(res.getInt("dificuldade")==3)?Dificuldades.DIFICIL:(res.getInt("dificuldade")==2)?Dificuldades.MEDIO:Dificuldades.FACIL,
	            			res.getInt("level"),
	            			Arrays.asList(TipoPokemon.values()).stream().filter(t -> t.getValor() == a).findFirst().get(),
	            			(b > 0)?Arrays.asList(TipoPokemon.values()).stream().filter(t -> t.getValor() == b).findFirst().get():null,
	            			(res.getInt("raridade")==3)?Raridades.SUPER_RARO:(res.getInt("raridade")==2)?Raridades.RARO:Raridades.COMUM,
	            			res.getInt("id_mochila"));
	            	pokemon.setId(res.getInt("id_pokemon"));
	            	pokemons.add(pokemon);
	            }
	        } catch (SQLException e) {
	            throw new BancoDeDadosException(e.getCause());
	        } finally {
	            try {
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return pokemons;
	    }

}
