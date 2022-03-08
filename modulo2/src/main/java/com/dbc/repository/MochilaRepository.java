package com.dbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dbc.exeptions.BancoDeDadosException;
import com.dbc.model.objetos.Mochila;

public class MochilaRepository implements Repository<Integer, Mochila> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_MOCHILA.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Mochila adicionar(Mochila mochila) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = BdConnection.getConnection();

            Integer proximoId = this.getProximoId(con);
            //TODO mochila.setIdMochila(proximoId);

            String sql = "INSERT INTO \"Mochila\"\n" +
                    "(id_mochila, id_treinador, quantidadeGreatBalls, quantidadeHeavyBalls, quantidadeMasterBalls, quantidadeNetBalls, quantidadePokeBalls)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, proximoId);//TODO mochila.getIdMochila());
            stmt.setInt(2, 0);//TODO mochila.getIdTreinador*/);
            stmt.setInt(3, mochila.getQuantidadeGreatBalls());
            stmt.setInt(4, mochila.getQuantidadeHeavyBalls());
            stmt.setInt(5, mochila.getQuantidadeMasterBalls());
            stmt.setInt(6, mochila.getQuantidadeNetBalls());
            stmt.setInt(7, mochila.getQuantidadePokeBalls());

            int res = stmt.executeUpdate();
            System.out.println("adicionarMochila.res=" + res);
            return mochila;
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

            String sql = "DELETE FROM \"Mochila\" WHERE \"id_mochila\" = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerMochilaPorId.res=" + res);

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
    public boolean editar(Integer id, Mochila mochila) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = BdConnection.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE Mochila SET ");
            sql.append(" \"id_treinador\" = ?,");
            sql.append(" \"quantidadeGreatBalls\" = ?,");
            sql.append(" \"quantidadeHeavyBalls\" = ?,");
            sql.append(" \"quantidadeMasterBalls\" = ?,");
            sql.append(" \"quantidadeNetBalls\" = ?,");
            sql.append(" \"quantidadePokeBalls\" = ?,");
            sql.append(" WHERE \"id_mochila\" = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());


            stmt.setInt(2, 0);//TODO mochila.getIdTreinador*/);
            stmt.setInt(3, mochila.getQuantidadeGreatBalls());
            stmt.setInt(4, mochila.getQuantidadeHeavyBalls());
            stmt.setInt(5, mochila.getQuantidadeMasterBalls());
            stmt.setInt(6, mochila.getQuantidadeNetBalls());
            stmt.setInt(7, mochila.getQuantidadePokeBalls());
            stmt.setInt(1, 0);//TODO mochila.getIdMochila());

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarMochila.res=" + res);

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
    public List<Mochila> listar() throws BancoDeDadosException {
        List<Mochila> mochilas = new ArrayList<>();
        Connection con = null;
        try {
            con = BdConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM \"Mochila\"";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Mochila mochila = new Mochila();
                //TODO mochila.setIdMochila(res.getInt("\"id_mochila\""));
                //TODO mochila.setIdTreinador(res.getInt("\"id_treinador\""));
                mochila.setQuantidadeGreatBalls(res.getInt("\"quantidadeGreatBalls\""));
                mochila.setQuantidadeHeavyBalls(res.getInt("\"quantidadeHeavyBalls\""));
                mochila.setQuantidadeMasterBalls(res.getInt("\"quantidadeMasterBalls\""));
                mochila.setQuantidadeNetBalls(res.getInt("\"quantidadeNetBalls\""));
                mochila.setQuantidadePokeBalls(res.getInt("\"quantidadePokeBalls\""));
                mochilas.add(mochila);
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
        return mochilas;
    }

}
