package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ModelEscola;
import model.ModelProfessor;
import view.Conexao;

/**
 * @author Gelvazio Camargo
 */
public class ControllerProfessorDB extends ControllerDBPadrao {

private static final String sqlTodos = "select * from public.tbprofessor order by codigo";


    private static final String sqlProfessor = "SELECT * FROM public.tbprofessor WHERE codigo = ?";

    
public boolean excluirProfessor(int codigoProfessor) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement("delete from tbprofessor where codigo = ?");
            pstmt.setInt(1, codigoProfessor);
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

                
                
                ModelEscola escola = new ModelEscola(codigo, nome);
                
                listaDados.add(escola);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql, getTodos():\n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return listaDados;
    }

    public ModelProfessor getProfessor(int codigo) {
        ModelProfessor professor = new ModelProfessor();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlProfessor);
            pstmt.setInt(1, codigo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // Pega valores String(texto)
                String nome = rs.getString("nome");
                ModelProfessor professorBanco = new ModelProfessor(codigo, nome);

                // recebe a escola do banco de dados
                professor = professorBanco;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL. getProfessor(): \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }

        return professor;
    }

    public boolean gravarAlteracaoProfessor(ModelProfessor professor) {
     Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            
            pstmt = conn.prepareStatement
                (" update tbprofessor set "
                   // + " codigo = ?,"
                    + " nome = ?"
                    + " where codigo = ?");
            
            pstmt.setString(1, professor.getNome());
            pstmt.setInt(2, professor.getCodigo());
            
            pstmt.executeUpdate();
            
            executou = true;            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        
        return executou;   
    }

    public boolean gravarInsercaoProfessor(ModelProfessor professor) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            
            pstmt = conn.prepareStatement
                (" insert into tbprofessor "
                    + "(codigo, nome) "
                    + "values(?, ?)");
            
            pstmt.setInt(1, professor.getCodigo());
            pstmt.setString(2, professor.getNome());
            
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
