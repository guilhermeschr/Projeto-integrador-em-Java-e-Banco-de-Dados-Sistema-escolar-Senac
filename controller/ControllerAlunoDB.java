package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ModelAluno;
import view.Conexao;

/**
 * @author Gelvazio Camargo
 */
public class ControllerAlunoDB extends ControllerDBPadrao {

private static final String sqlTodos = "select * from public.tbaluno order by codigo";

    private static final String sqlAluno = "SELECT * FROM public.tbaluno WHERE codigo = ?";

    
    
    public boolean excluirAluno(int codigoAluno) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement("delete from tbAluno where codigo = ?");
            pstmt.setInt(1, codigoAluno);
            pstmt.executeUpdate();
            
            executou = true;            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        
        return executou;
    }
    
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
                String nome = rs.getString("nome");

                
                
                ModelAluno aluno = new ModelAluno(codigo, nome);
                
                listaDados.add(aluno);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql, getTodos():\n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return listaDados;
    }

    public ModelAluno getAluno(int codigo) {
        ModelAluno aluno = new ModelAluno();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlAluno);
            pstmt.setInt(1, codigo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // Pega valores String(texto)
                String nome = rs.getString("nome");
                ModelAluno alunoBanco = new ModelAluno(codigo, nome);

                // recebe a escola do banco de dados
                aluno = alunoBanco;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL. getAluno(): \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }

        return aluno;
    }

    
    public boolean gravarAlteracaoAluno(ModelAluno aluno) {

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            
            pstmt = conn.prepareStatement
                (" update tbaluno set "
                    + " nome         = ?"    // 1
                    + " where codigo = ?");  // 2 
            
            pstmt.setString(1, aluno.getNome());
            pstmt.setInt(2, aluno.getCodigo());
            
            pstmt.executeUpdate();
            
            executou = true;            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        
        return executou;
    }

    public boolean gravarInsercaoAluno(ModelAluno aluno) {
//      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            
            pstmt = conn.prepareStatement
                (" insert into tbAluno "
                    + "(codigo, nome) "
                    + "values(?, ?)");
            
            pstmt.setInt(1, aluno.getCodigo());
            pstmt.setString(2, aluno.getNome());
            
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
