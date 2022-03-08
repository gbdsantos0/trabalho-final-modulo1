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
		 String sql = "SELECT seq_pokemon.nextval mysequence from DUAL";

	        Statement stmt = connection.createStatement();
	        ResultSet res = stmt.executeQuery(sql);
	        
	        if (res.next()) {
	            return res.getInt("mysequence");
	        }
	        return null;
	}
	
	public Integer getProximoId() throws SQLException {
		Connection con = null;
        try {
            con = BdConnection.getConnection();
			 String sql = "SELECT seq_pokemon.nextval mysequence from DUAL";
			 	
		        Statement stmt = con.createStatement();
		        ResultSet res = stmt.executeQuery(sql);
		        
		        if (res.next()) {
		            return res.getInt("mysequence");
		        }
		        return null;
        }catch (Exception e) {
			
		}finally {
			if(con != null) {
				con.close();
			}
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

            String sql = "INSERT INTO \"PokemonBase\"\n" +
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

            String sql = "DELETE FROM \"Pokemon\" p  WHERE p.id_pokemon = ?";

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
            sql.append("UPDATE \"Pokemon\" p SET ");
            sql.append(" p.nome = ?,");
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
	
	public boolean editarTodosPokemons(List<Pokemon> pokemons) throws BancoDeDadosException {
		Connection con = null;
        try {
            con = BdConnection.getConnection();
            
            for (Pokemon pokemon : pokemons) {
            
	            StringBuilder sql = new StringBuilder();
	            sql.append("UPDATE \"Pokemon\" p SET ");
	            sql.append(" \"nome\" = ?,");
	            sql.append(" \"idade\" = ?,");
	            sql.append(" \"peso\" = ?,");
	            sql.append(" \"sexo\" = ?,");
	            sql.append(" \"apelido\" = ?,");
	            sql.append(" \"dificuldade\" = ?,");
	            sql.append(" \"level\" = ?,");
	            sql.append(" \"tipo1\" = ?,");
	            sql.append(" \"tipo2\" = ?,");
	            sql.append(" \"raridade\" = ?,");
	            sql.append(" \"id_mochila\" = ?");
	            sql.append(" WHERE \"id_pokemon\" = ? ");
	            
	            
	            
	            PreparedStatement stmt = con.prepareStatement(sql.toString());
	
	            stmt.setString(1, pokemon.getPokemon());
	            stmt.setInt(2, pokemon.getIdade());
	            stmt.setDouble(3, pokemon.getPeso());
	            stmt.setString(4, (pokemon.getSexo()==Utils.MASCULINO)?"M":"F");
	            stmt.setString(5, pokemon.getNome());
	            stmt.setInt(6, (pokemon.getDificuldade()==Dificuldades.DIFICIL)?3:(pokemon.getDificuldade()==Dificuldades.MEDIO?2:1));
	            stmt.setInt(7, pokemon.getLevel());
	            stmt.setInt(8, pokemon.getTipo()[1].getValor());
	            stmt.setInt(9, (pokemon.getTipo().length > 1)?pokemon.getTipo()[1].getValor():null);
	            stmt.setInt(10, (pokemon.getRaridade()==Raridades.SUPER_RARO)?3:(pokemon.getRaridade()==Raridades.RARO?2:1));
	            stmt.setInt(11, pokemon.getIdMochila());
	            stmt.setInt(12, pokemon.getId());
	            
	            stmt.executeUpdate();
            }
            return true;
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

	            String sql = "SELECT * FROM \"Pokemon\" p";

	            // Executa-se a consulta
	            ResultSet res = stmt.executeQuery(sql);

	            while (res.next()) {
	            	int a = res.getInt("tipo1");
	            	int b = res.getInt("tipo2");
	            	Pokemon pokemon = new Pokemon(
	            			res.getString("apelido"),
	            			res.getString("nome"),
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
