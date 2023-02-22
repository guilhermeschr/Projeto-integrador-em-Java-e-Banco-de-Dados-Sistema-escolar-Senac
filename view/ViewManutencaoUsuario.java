package view;
// classes de conexao
import controller.ControllerUsuarioDB;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.ModelUsuario;
import static view.Principal.mensagemErro;

/**
 *
 * @author Gelvazio Camargo
 */
public class ViewManutencaoUsuario extends JFrame {

    private static final String sqlBuscaUsuario
            = "SELECT * FROM usuario WHERE cd_usuario=?";
    ControllerUsuarioDB usuariodb = new ControllerUsuarioDB();

    public ViewManutencaoUsuario() {
        initComponents();
        this.setLocationRelativeTo(null);
        habilitaCampos(false);
    }

    private void habilitaCampos(boolean habilita) {
        edtCodigo.requestFocus();
        edtCodigo.setEnabled(!habilita);
        edtNome.setEnabled(habilita);
        edtEmail.setEnabled(habilita);
        edtSenha.setEnabled(habilita);
        edtSenha_Dois.setEnabled(habilita);

        btnGravar.setEnabled(habilita);
        btnCancelar.setEnabled(habilita);
     //   btnConsulta.setEnabled(!habilita);
        btnExcluir.setEnabled(habilita);

        if (habilita) {
            edtCodigo.requestFocus();
        } else {
            limpaTela();
        }
    }

    private void limpaTela() {
        edtCodigo.setText("");
        edtNome.setText("");
        edtEmail.setText("");
        edtSenha.setText("");
        edtSenha_Dois.setText("");
        edtCodigo.grabFocus();
    }

    private void Excluir_Registro() {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registro?");
        if (resposta == JOptionPane.YES_OPTION) {
            int auxCodigo = Integer.parseInt(edtCodigo.getText());
            String auxLogin = edtEmail.getText();

            if (usuariodb.excluirUsuario(auxCodigo)) {
                JOptionPane.showMessageDialog(null, "Exclusão efetuada com sucesso!");
                habilitaCampos(false);
            } else {
                mensagemErro("Não foi possivel excluir o registro!");
            }
        }
    }

    private void alterarGravar() {
        int ativo = ControllerUsuarioDB.STATUS_ATIVO;
        String nome = edtNome.getText();
        String email = edtEmail.getText();
        String senha = edtSenha.getText();

        ModelUsuario usuario = new ModelUsuario(nome, email, senha, ativo);

        ModelUsuario usuarioBancoDados = usuariodb.getModelUsuario(email, senha);

        if (usuarioBancoDados.getCodigo() > 0) {
            usuario.setCodigo(usuarioBancoDados.getCodigo());
            
            if (usuariodb.alterarUsuario(usuario)) {
                JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
                habilitaCampos(false);
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível alterar o registro!");
            }
        } else {
            if (usuariodb.inserirUsuario(usuario)) {
                JOptionPane.showMessageDialog(null, "Registro incluído com sucesso!");
                habilitaCampos(false);
                limpaTela();
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível incluir o registro!");
                edtCodigo.grabFocus();
            }
        }
    }

    private void ValidaCampoCodigoNaoNulo() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int codigo = Integer.parseInt(edtCodigo.getText());

        ModelUsuario model = usuariodb.getUsuarioPorCodigo(codigo);
        if (model.getCodigo() > 0) {
            habilitaCampos(true);

            edtNome.setText(model.getNome());
            edtEmail.setText(model.getEmail());
            edtSenha.setText(model.getSenha());
            edtSenha_Dois.setText(model.getSenha());

            edtNome.grabFocus();
            edtNome.requestFocus();
        } else {
            mensagemErro("Usuario Não Cadastrado!");
            habilitaCampos(false);
        }
    }

    private void GravarCompletoValidado() {
        String auxLogin = edtEmail.getText();
        String auxSenhaUm = edtSenha.getText();
        String auxSenhaDois = edtSenha_Dois.getText();
        if (auxLogin.equals("")) {
            mensagemErro("Favor Preencher o Login do Usuário!");
            edtEmail.grabFocus();
        } else if (auxSenhaUm.equals("")) {
            mensagemErro("Favor Preencher a Senha do Usuário!");
            edtSenha.grabFocus();
        } else if (auxSenhaDois.equals("")) {
            mensagemErro("Favor Preencher a Senha do Usuário!");
            edtSenha_Dois.grabFocus();
        } else {
            //Valida senha igual
            if (auxSenhaUm.equals(auxSenhaDois)) {
                alterarGravar();
            } else {
                mensagemErro("Senhas não conferem!");
                edtSenha_Dois.requestFocus();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        edtSenha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        edtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        edtSenha_Dois = new javax.swing.JTextField();
        edtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Cadastro de Usuários");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 245, 39));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtSenhaKeyPressed(evt);
            }
        });
        jPanel1.add(edtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 140, 30));

        jLabel8.setText("Senha:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 40, -1));

        edtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEmailActionPerformed(evt);
            }
        });
        edtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtEmailKeyPressed(evt);
            }
        });
        jPanel1.add(edtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 140, 30));

        jLabel2.setText("Email");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 40, -1));

        jLabel11.setText("Confirmar Senha:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 100, -1));

        jLabel7.setText("Código:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 50, -1));

        edtSenha_Dois.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtSenha_DoisKeyPressed(evt);
            }
        });
        jPanel1.add(edtSenha_Dois, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 140, 30));

        edtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodigoKeyPressed(evt);
            }
        });
        jPanel1.add(edtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 80, 30));

        jLabel3.setText("Nome:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 40, -1));

        edtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtNomeKeyPressed(evt);
            }
        });
        jPanel1.add(edtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 220, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 55, 350, 260));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Knob Valid Green.png"))); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });
        btnGravar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGravarKeyPressed(evt);
            }
        });
        jPanel2.add(btnGravar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 40));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Knob Cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarKeyPressed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 120, 40));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Knob Remove Red.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        btnExcluir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnExcluirKeyPressed(evt);
            }
        });
        jPanel2.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 120, 40));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Knob Red.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jPanel2.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 120, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 140, 250));

        setBounds(0, 0, 536, 434);
    }// </editor-fold>//GEN-END:initComponents

    private void edtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodigoKeyPressed
        String auxTexto = edtCodigo.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!auxTexto.equalsIgnoreCase("")) {
                ValidaCampoCodigoNaoNulo();
            } else {
                habilitaCampos(true);
                edtNome.requestFocus();
            }
        }
    }//GEN-LAST:event_edtCodigoKeyPressed

    private void edtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtEmailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            edtSenha.grabFocus();
        }
    }//GEN-LAST:event_edtEmailKeyPressed

    private void edtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtSenhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGravar.grabFocus();
        }
    }//GEN-LAST:event_edtSenhaKeyPressed

    private void edtSenha_DoisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtSenha_DoisKeyPressed

    }//GEN-LAST:event_edtSenha_DoisKeyPressed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente gravar o registro?");
        if (resposta == JOptionPane.YES_OPTION) {
            GravarCompletoValidado();
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnGravarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGravarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCancelar.grabFocus();
        }
    }//GEN-LAST:event_btnGravarKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente Cancelar a Edição?");
        if (resposta == JOptionPane.YES_OPTION) {
            limpaTela();
            habilitaCampos(false);
            edtCodigo.grabFocus();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnExcluir.grabFocus();
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Excluir_Registro();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnExcluirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExcluirKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         //   btnConsulta.grabFocus();
        }
    }//GEN-LAST:event_btnExcluirKeyPressed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed

        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void edtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtNomeKeyPressed

    }//GEN-LAST:event_edtNomeKeyPressed

    private void edtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEmailActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewManutencaoUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewManutencaoUsuario().setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnSair;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JTextField edtEmail;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtSenha;
    private javax.swing.JTextField edtSenha_Dois;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
