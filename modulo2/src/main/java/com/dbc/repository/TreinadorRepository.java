package com.dbc.repository;

import com.dbc.exeptions.BancoDeDadosException;
import com.dbc.model.entidades.Treinador;
import com.dbc.model.enums.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinadorRepository implements Repository<Integer, Treinador> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_TREINADOR.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Treinador adicionar(Treinador treinador) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = BdConnection.getConnection();

            Integer proximoId = this.getProximoId(con);
            
            //criar o id_treinador na classe treindor no projeto java!
            //TODO Treinador.setIdTreinador(proximoId);

            String sql = "INSERT INTO \"Treinador\" \n" +
                    "(\"id_treinador\", \"nome\", \"idade\",\" peso\", \"sexo\")\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, treinador.getIdTreinador());
            stmt.setString(2, treinador.getNome());
            stmt.setDate(3, treinador.getIdade()));
            stmt.setString(4, treinador.getPeso());
            stmt.setString(5, (treinador.getSexo()== Utils.MASCULINO)?"M":"F");
            
            
            int res = stmt.executeUpdate();
            System.out.println("adicionarTreinador.res=" + res);
            return treinador;
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

            String sql = "DELETE FROM \"Treinador\" WHERE \"id_treinador\" = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerTreinadorPorId.res=" + res);

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
    public boolean editar(Integer id, Treinador treinador) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = BdConnection.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE \"Treinador\" SET ");
            sql.append(" \"nome\" = ?,");
            sql.append(" \"idade\" = ?,");
            sql.append(" \"peso\" = ? ");
            sql.append(" \"sexo\" = ? ");
            sql.append(" WHERE \"id_treinador\" = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, treinador.getNome());
            stmt.setString(2, treinador.getIdade());
            stmt.setDate(3, treinador.getPeso());
            stmt.setString(5, (pokemon.getSexo()==Utils.MASCULINO)?"M":"F");
            stmt.setInt(5, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarTreinador.res=" + res);

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
    public List<Treinador> listar() throws BancoDeDadosException {
        List<Treinador> treinadores = new ArrayList<>();
        Connection con = null;
        try {
            con = BdConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM \"Treinador\"";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Treinador treinador = new Treinador(res.getString("\"nome\""),
                        res.getInt("\"idade\""),
                        res.getDouble("\"peso\""),
                        res.getString("sexo").equalsIgnoreCase("M")?Utils.MASCULINO:Utils.FEMININO)),



                treinador.setIdTreinador(res.getInt("\"id_treinador\""));
                treinador.setNome(res.getString("\"nome\""));
                treinador.setIdade(res.getInt("\"idade\""));
                treinador.setPeso(res.getDouble("\"peso\""));
                treinador.setSexo(res.getString("sexo").equalsIgnoreCase("M"))?Utils.MASCULINO:Utils.FEMININO);
                treinadores.add(treinador);
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
        return treinadores;
    }
}
