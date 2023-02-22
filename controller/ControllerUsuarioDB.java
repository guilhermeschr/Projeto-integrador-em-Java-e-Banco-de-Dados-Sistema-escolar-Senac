package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ModelUsuario;
import view.Conexao;
import static view.Principal.mensagemErro;

/**
 *
 * @author Guilherme Schroeder
 */
public class ControllerUsuarioDB extends ControllerDBPadrao {

    public static int STATUS_ATIVO = 1;

    private static final String sqlTodos = "SELECT * FROM USUARIO";

    private final String sqlInserir
        = "INSERT INTO public.tbusuario" +
            "(usunome, usuemail, ususenha, usuativo)" +
            "VALUES(?,?,?,1)";
    private static final String sqlExcluir = "DELETE FROM public.tbusuario WHERE usucodigo= ?";
    private static final String sqlAlterar =
            "UPDATE public.tbusuario SET "
            + " usunome=?, "
            + " usuemail=?, "
            + " ususenha=? " +
        "WHERE usucodigo=?";

    public ModelUsuario getModelUsuario(String email, String senha) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ModelUsuario model = new ModelUsuario();

        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select usucodigo,"  +
                                    "      usunome,  "  +
                                    "      usuemail, "  +
                                    "      ususenha, "  +
                                    "      usuativo  "  +
                                    " from public.tbusuario " +
                                    "where usuemail ilike '" + email + "'"+
                                    "  and ususenha ilike '" + senha + "'");
            if (rs.next()) {
                int codigo  = rs.getInt("usucodigo");
                String nome = rs.getString("usunome");
                email       = rs.getString("usuemail");
                senha       = rs.getString("ususenha");
                int ativo   = rs.getInt("usuativo");

                model.setCodigo(codigo);
                model.setNome(nome);
                model.setEmail(email);
                model.setSenha(senha);
                model.setAtivo(ativo);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! metodo: \n getModelUsuario()" + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        return model;
    }

    public ModelUsuario getUsuarioPorCodigo(int codigo) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ModelUsuario model = new ModelUsuario();

        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select usucodigo,"  +
                                    "      usunome,  "  +
                                    "      usuemail, "  +
                                    "      ususenha, "  +
                                    "      usuativo  "  +
                                    " from public.tbusuario " +
                                    "where usucodigo = " + codigo);
            if (rs.next()) {
                String nome = rs.getString("usunome");
                String email= rs.getString("usuemail");
                String senha= rs.getString("ususenha");
                int ativo   = rs.getInt("usuativo");

                model.setCodigo(codigo);
                model.setNome(nome);
                model.setEmail(email);
                model.setSenha(senha);
                model.setAtivo(ativo);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! metodo: \n getModelUsuario()" + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        return model;
    }

    public boolean alterarUsuario(ModelUsuario usuario) {
        boolean alterou = false;
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = Conexao.getConexao();

            pstmt = conn.prepareStatement(sqlAlterar);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setInt(4, usuario.getCodigo());

            pstmt.executeUpdate();

            alterou = true;
        } catch (SQLException erro) {
            mensagemErro("Erro no sql. alterarUsuario(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return alterou;
    }

    public boolean excluirUsuario(int codigo) {
        boolean excluiu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlExcluir);
            pstmt.setInt(1, codigo);
            pstmt.executeUpdate();
            excluiu = true;
        } catch (SQLException erro) {
            mensagemErro("Erro no sql. excluirUsuario():\n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return excluiu;
    }

    public boolean inserirUsuario(ModelUsuario usuario) {
        boolean inseriu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {

            conn = Conexao.getConexao();

            pstmt = conn.prepareStatement(sqlInserir);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());

            pstmt.executeUpdate();

            inseriu = true;
        } catch (SQLException erro) {
            String sMensagemErro = "Erro no sql. inserirUsuarios(): \n" + erro.getMessage()+"sql \n"+sqlInserir;

            mensagemErro(sMensagemErro);
        } finally {
            Conexao.closeAll(conn);
        }
        return inseriu;
    }

    public ArrayList getTodos() {
        ArrayList listaUsuario = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from public.tbusuario");
            while (rs.next()) {
                int codigo  = rs.getInt("usucodigo");
                String nome = rs.getString("usunome");
                String email= rs.getString("usuemail");
                String senha= rs.getString("ususenha");
                int ativo   = rs.getInt("usuativo");

                ModelUsuario model = new ModelUsuario();
                model.setCodigo(codigo);
                model.setNome(nome);
                model.setEmail(email);
                model.setSenha(senha);
                model.setAtivo(ativo);

                listaUsuario.add(model);
            }
        } catch (SQLException erro) {
            mensagemErro("Erro no sql, getTodos(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return listaUsuario;
    }

}
