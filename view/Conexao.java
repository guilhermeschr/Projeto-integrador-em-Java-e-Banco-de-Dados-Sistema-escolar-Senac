package view;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {

    private static final String DRIVER   = "org.postgresql.Driver";
    private static final String BANCO    = "postgres";
    private static final String CONEXAO  = "jdbc:postgresql://db.xtqujdtyeaehthmoufai.supabase.co/" + BANCO;
    private static final String USUARIO  = "postgres";
    private static final String SENHA    = "g2p4VKQH1TtQZmpc";
    
    public static Connection getConexao() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(CONEXAO, USUARIO, SENHA);
        } catch (ClassNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Erro de driver! \n" + erro.getMessage());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão! \n" + erro.getMessage());
        }
        return conn;
    }

    public static void closeAll(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão! \n " + erro.getMessage());
        }
    }
}
