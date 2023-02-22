package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import view.Conexao;

/**
 *
 * @author Gelvazio Camargo
 */
public class ControllerDBPadrao {

    public int getUltimoCodigo(String tabela, String coluna) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int codigo = 0;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            String sequence = tabela + "_" + coluna + "_seq";
            rs = stmt.executeQuery("select nextval('public." + sequence + "') as codigo");
            while (rs.next()) {
                codigo = rs.getInt("codigo");
            }
        } catch (SQLException erro) {
            System.out.println("Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        return codigo;
    }
}
