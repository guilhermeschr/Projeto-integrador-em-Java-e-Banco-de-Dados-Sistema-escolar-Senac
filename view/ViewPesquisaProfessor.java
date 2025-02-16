package view;

import controller.ControllerEscolaDB;
import controller.ControllerProfessorDB;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.ModelEscola;

/**
 *
 * @author Gelvazio Camargo
 */
public class ViewPesquisaProfessor extends javax.swing.JDialog {

    public int codigo;
    public String nome;

    ControllerProfessorDB professordb = new ControllerProfessorDB();

    public ViewPesquisaProfessor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        // coloca a tela no centro
        this.setLocationRelativeTo(null);
    }

    public void listaTodosRegistros() {                
        DefaultTableModel modelo = (DefaultTableModel) tabelaDados.getModel();
       
        // Reseta os registros da tabela
        modelo.setRowCount(0);
                
        // Lista todas as pessoas da classe escoladb
        ArrayList<ModelEscola> professor = professordb.getTodos();
        
        // Percorre a lista de pessoas recebidas e adiciona na tabela da tela
        //   tipo        apelido     arrayList de pessoas
        for (ModelEscola auxEscola : professor) {
//            if(auxPessoa.getCodigo() > 1){
                modelo.addRow(new Object[]{
                    auxEscola.getCodigo(),
                    auxEscola.getCodigo() + " - "+ auxEscola.getNome(),
                });                
//            }            
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

        jSeparator8 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDados = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        btnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 620, 10));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Consulta de Professor");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 660, -1));

        tabelaDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaDados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaDadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaDados);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 640, 340));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 620, -1));

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Todos");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jRadioButton2.setText("Nome");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        jRadioButton3.setText("Código");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaDadosMouseClicked
        codigo = Integer.parseInt(String.valueOf(tabelaDados.getModel().getValueAt(tabelaDados.getSelectedRow(), 0)));
        nome = String.valueOf(tabelaDados.getModel().getValueAt(tabelaDados.getSelectedRow(), 1));
        dispose();
    }//GEN-LAST:event_tabelaDadosMouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed

        if (jRadioButton1.isSelected()) {
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            DefaultTableModel model = (DefaultTableModel) tabelaDados.getModel();
            model.setNumRows(0);
//            cli.setListaCli(cli.retornaClientes());
//            for (int f = 0; f < cli.getListaCli().size(); f++) {
//                String[] linhaResul;
//                linhaResul = cli.getListaCli().get(f).split(";");
//                Object[] linha = {linhaResul[0], linhaResul[1], linhaResul[3], linhaResul[2]};
//                model.addRow(linha);
//            }
        } else {
            jRadioButton2.setSelected(true);
        }

        jTextField1.requestFocus();
        jTextField1.selectAll();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed

        if (jRadioButton3.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
        } else {
            jRadioButton2.setSelected(true);
        }

        jTextField1.requestFocus();
        jTextField1.selectAll();
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed

        if (jRadioButton2.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
        } else {
            jRadioButton1.setSelected(true);
        }

        jTextField1.requestFocus();
        jTextField1.selectAll();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        listaTodosRegistros();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        //        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            //            model.setNumRows(0);
            //            cli.setListaCli(cli.retornaClientes());
            //            if (jRadioButton1.isSelected()) { //Todos
                //                for (int f = 0; f < cli.getListaCli().size(); f++) {
                    //                    String[] linhaResul;
                    //                    linhaResul = cli.getListaCli().get(f).split(";");
                    //                    Object[] linha = {linhaResul[0], linhaResul[1], linhaResul[3], linhaResul[2]};
                    //                    model.addRow(linha);
                    //                }
                //            } else if (jRadioButton3.isSelected()) { //Código
                //                for (int f = 0; f < cli.getListaCli().size(); f++) {
                    //                    String[] linhaResul;
                    //                    linhaResul = cli.getListaCli().get(f).split(";");
                    //                    if (linhaResul[0].equals(jTextField1.getText())) {
                        //                        Object[] linha = {linhaResul[0], linhaResul[1], linhaResul[3], linhaResul[2]};
                        //                        model.addRow(linha);
                        //                    }
                    //                }
                //            } else if (jRadioButton2.isSelected()) { //Descrição
                //                for (int f = 0; f < cli.getListaCli().size(); f++) {
                    //                    String[] linhaResul;
                    //                    linhaResul = cli.getListaCli().get(f).split(";");
                    //                    String nome = linhaResul[1].toUpperCase();
                    //                    String digitado = jTextField1.getText().toUpperCase();
                    //                    if (nome.contains(digitado)) {
                        //                        Object[] linha = {linhaResul[0], linhaResul[1], linhaResul[3], linhaResul[2]};
                        //                        model.addRow(linha);
                        //                    }
                    //                }
                //            }
            //        }
    }//GEN-LAST:event_jTextField1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPesquisaProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPesquisaProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPesquisaProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPesquisaProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewPesquisaProfessor dialog = new ViewPesquisaProfessor(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tabelaDados;
    // End of variables declaration//GEN-END:variables
}
