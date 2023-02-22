package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ModelEscola;
import view.Conexao;

/**
 * @author Gelvazio Camargo
 */
public class ControllerEscolaDB extends ControllerDBPadrao {

private static final String sqlTodos = "select * from public.tbescola order by codigo";


    private static final String sqlEscola = "SELECT * FROM public.tbescola WHERE codigo = ?";

    
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

    public ModelEscola getEscola(int codigo) {
        ModelEscola escola = new ModelEscola();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlEscola);
            pstmt.setInt(1, codigo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // Pega valores String(texto)
                String nome = rs.getString("nome");
                ModelEscola escolaBanco = new ModelEscola(codigo, nome);

                // recebe a escola do banco de dados
                escola = escolaBanco;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL. getEscola(): \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }

        return escola;
    }


    public boolean gravarAlteracaoEscola(ModelEscola escola) {
        
          Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            
            pstmt = conn.prepareStatement
                (" update tbescola set "
                    //+ " codigo = ?,"
                    + " nome = ?"
                  + " where codigo = ? " );
            
            
            pstmt.setString(1, escola.getNome());
            pstmt.setInt(2, escola.getCodigo());
            
            pstmt.executeUpdate();
            
            executou = true;            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        
        return executou;
    }

    public boolean gravarInsercaoEscola(ModelEscola escola) {
//      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            
            pstmt = conn.prepareStatement
                (" insert into tbescola "
                    + "(codigo, nome) "
                    + "values(?, ?)");
            
            pstmt.setInt(1, escola.getCodigo());
            pstmt.setString(2, escola.getNome());
            
            pstmt.executeUpdate();
            
            executou = true;            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        
        return executou;
    }    

    public boolean excluirEscola(int codigoEscola) {
    Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement("delete from tbescola where codigo = ?");
            pstmt.setInt(1, codigoEscola);
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
