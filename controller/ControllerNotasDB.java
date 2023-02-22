package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.ModelNota;
import view.Conexao;

/**
 * @author Gelvazio Camargo
 */
public class ControllerNotasDB extends ControllerDBPadrao {

private static final String sqlTodos = "select * from public.tbnota order by codigo";


    private static final String sqlNota = "SELECT * FROM public.tbnota WHERE codigo = ?";


    public ArrayList getTodos() {
        ArrayList listaDados = new ArrayList();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlTodos);

            while (rs.next()) {

                int codigonota = rs.getInt("codigo");
                int codigoprofessor = rs.getInt("codigoprofessor");
                int codigomateria  = rs.getInt("codigomateria");
                int codigoaluno  = rs.getInt("codigoaluno");
                Date data = rs.getDate("datanota");
                double nota = rs.getDouble("nota");



                ModelNota Nota = new ModelNota(codigonota, codigoprofessor,codigomateria, codigoaluno , data, nota);

                listaDados.add(Nota);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql, getTodos():\n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return listaDados;
    }

    public ModelNota getNota(int codigonota) {
        ModelNota Nota = new ModelNota();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlNota);
            pstmt.setInt(1, codigonota);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // Pega valores String(texto)
                codigonota = rs.getInt("codigo");
                int codigoprofessor = rs.getInt("codigoprofessor");
                int codigomateria  = rs.getInt("codigomateria");
                int codigoaluno  = rs.getInt("codigoaluno");
                double nota = rs.getDouble("nota");
                Date data = rs.getDate("datanota");
                ModelNota notaBanco = new ModelNota(codigoprofessor,codigomateria, codigoaluno, codigonota, data, nota);

                // recebe a escola do banco de dados
                Nota = notaBanco;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL. getNota(): \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }

        return Nota;
    }

    public boolean gravarAlteracaoNotas(ModelNota Notas) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;

        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();

            pstmt = conn.prepareStatement
                (" update tbnota set "
                    + " codigoprofessor = ?,"
                    + " codigomateria = ?,"
                    + " codigoaluno = ?,"
                    + " datanota = ?,"
                    + " nota = ?"
                    + " where codigo = ?");

            pstmt.setInt(1, Notas.getCodigoprofessor());
            pstmt.setInt(2, Notas.getCodigomateria());
            pstmt.setInt(3, Notas.getCodigoaluno());
            pstmt.setDate(4, (java.sql.Date) Notas.getData());
            pstmt.setDouble(5, Notas.getNota());
            pstmt.setInt(6, Notas.getCodigonota());

            pstmt.executeUpdate();

            executou = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }

        return executou;
    }

    public boolean gravarInsercaoNota(ModelNota Notas) {
     Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;

        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();

            pstmt = conn.prepareStatement
                (" insert into tbnota "
                    + "(codigoprofessor, codigomateria, codigoaluno, datanota, nota) "
                    + "values(?, ?,?,?,?)");

            pstmt.setInt(1, Notas.getCodigoprofessor());
            pstmt.setInt(2, Notas.getCodigomateria());
            pstmt.setInt(3, Notas.getCodigoaluno());
            pstmt.setDate(4, (java.sql.Date) Notas.getData());

//            java.util.Date data = new java.util.Date();
//            java.sql.Date dataSql = new java.sql.Date(data.getTime());


            pstmt.setDouble(5, Notas.getNota());

            pstmt.executeUpdate();

            executou = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }

        return executou;
    }


    public boolean excluirNota(int codigoNota) {
       Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;

        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement("delete from tbnota where codigo = ?");
            pstmt.setInt(1, codigoNota);
            pstmt.executeUpdate();

            executou = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }

        return executou;

    }
    
    
}
