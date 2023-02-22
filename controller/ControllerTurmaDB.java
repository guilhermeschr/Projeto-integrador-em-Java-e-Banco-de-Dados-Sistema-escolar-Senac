package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ModelTurma;
import view.Conexao;

/**
 * @author Guilherme Schröder
 */
public class ControllerTurmaDB extends ControllerDBPadrao {

private static final String sqlTodos = "select * from public.tbturma order by codigo";


    private static final String sqlTurma = "SELECT * FROM public.tbturma WHERE codigo = ?";


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
                // Pega valor inteiro
                int codigo = rs.getInt("codigo");
                
                // Pega valores String(texto)
                String descricao = rs.getString("descricao");
                String nivel = rs.getString("nivel");
                String periodo = rs.getString("periodo");

                
                
                ModelTurma turma = new ModelTurma(codigo, descricao,nivel,periodo);
                
                listaDados.add(turma);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql, getTodos():\n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return listaDados;
    }

    public ModelTurma getTurma(int codigo) {
        ModelTurma professor = new ModelTurma();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlTurma);
            pstmt.setInt(1, codigo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // Pega valores String(texto)
                String descricao = rs.getString("descricao");
                String nivel = rs.getString("nivel");
                String periodo = rs.getString("periodo");
                ModelTurma turmaBanco = new ModelTurma(codigo, descricao,nivel,periodo);

                // recebe a escola do banco de dados
                professor = turmaBanco;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL. getProfessor(): \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }

        return professor;
    }


    public boolean gravarAlteracaoTurma(ModelTurma turma) {
    Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            
            pstmt = conn.prepareStatement
                (" update tbturma set "
//                    + " codigo = ?,"
                    + " descricao = ?,"
                    + " nivel = ?,"
                    + " periodo = ?"
                    + " where codigo = ?");
            
            pstmt.setString(1, turma.getDescricao());
            pstmt.setString(2, turma.getNivel());
            pstmt.setString(3, turma.getPeriodo());
            pstmt.setInt(4,turma.getCodigo());
            
            pstmt.executeUpdate();
            
            executou = true;            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        
        return executou;    
    }

    public boolean gravarInsercaoTurma(ModelTurma turma) {
       Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            
            pstmt = conn.prepareStatement
                (" insert into tbturma "
                    + "(descricao,nivel,periodo) "
                    + "values(?,?,?)");
            
            pstmt.setString(1, turma.getDescricao());
            pstmt.setString(2, turma.getNivel());
            pstmt.setString(3, turma.getPeriodo());
            
            pstmt.executeUpdate();
            
            executou = true;            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        
        return executou;
    }

    public boolean excluirTurma(int codigoTurma) {
    Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement("delete from tbturma where codigo = ?");
            pstmt.setInt(1, codigoTurma);
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
