/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maniaComprar.telas;

import java.sql.*;
import br.com.maniaComprar.dal.ModuloConexao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * 
 *
 * @author 
 */

public class TelaCadastroCliente extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form TelaCadastroCliente
     */
    public TelaCadastroCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
        System.out.println(conexao);
    }
    
    //Salva os dados no BD
public void adicionar() throws ClassNotFoundException {
try {
    
Class.forName("com.mysql.jdbc.Driver");
// * Conexão como BD.
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_maniadecomprar", "root", "");
// * Objeto comdo SQL.
Statement pst = con.createStatement();
        
// * Pega os dados informado no formulário,
String cpf = jTextCpf.getText();
String nome = jTextNome.getText();
String rg = jTextRg.getText();
String numero = jTextNumero.getText();
String nascimento = jTextnascimento.getText();
String sexo = jTextSexo.getText();
String endereco = jTextendereco.getText();
String email = jTextEmail.getText();
String limiteCredito = jTextLimiteCredito.getText();
String debito = jTextDebito.getText();

// * Insere os dados do formulário no BD.
pst.executeUpdate("insert into pessoa (cpf,nome,rg,telefone,nascimento,sexo,endereco,email) values ('"
+ cpf
+ "','"
+ nome
+ "','"
+ rg
+ "','"
+ numero
+ "','"
+ nascimento
+ "','"
+ sexo
+ "','"
+ endereco
+ "','"
+ email
+ "')");

pst.executeUpdate("insert into cliente (cpf,nome,limite_credito,debito) values('"
        +cpf
        +"','"
        +nome
        +"','"
        +limiteCredito
        +"','"
        +debito
        +"')");
        JOptionPane.showMessageDialog(null, "Dados Salvos!");

   
jTextCpf.setText(null);
jTextNome.setText(null);
jTextRg.setText(null);
jTextNumero.setText(null);
jTextnascimento.setText(null);
jTextSexo.setText(null);
jTextendereco.setText(null);
jTextEmail.setText(null);

conexao.close();

} catch (SQLException Erro) {
  JOptionPane.showMessageDialog(null,
    "Erro Cmdo SQL" + Erro.getMessage());

  JOptionPane.showMessageDialog(null, "Driver não encontrado!");

 

}
}
private void consultar(){
    String sql = " select * from pessoa where cpf=?";
    
        try {
            pst= conexao.prepareStatement(sql);
            pst.setString(1,jTextCpf.getText());
            rs=pst.executeQuery();
            if (rs.next()) {
                jTextNome.setText(rs.getString(2));
                jTextRg.setText(rs.getString(3));
                jTextNumero.setText(rs.getString(4));
                jTextnascimento.setText(rs.getString(5));
                jTextSexo.setText(rs.getString(6));
                jTextendereco.setText(rs.getString(7));
                jTextEmail.setText(rs.getString(8));
                
                //String sql = "select * from cliente where cpf=?";
                
                
                
                } else {
                JOptionPane.showMessageDialog(null,"Usuário não Cadastrado");
                
                
                 jTextNome.setText(null);
                 jTextRg.setText(null);
                jTextNumero.setText(null);
                jTextnascimento.setText(null);
                jTextSexo.setText(null);
                jTextendereco.setText(null);
                jTextEmail.setText(null);
                  
                 
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        } 
        
    }

private void alterar(){
String sql = "update pessoa set nome=?,rg=?,telefone=?,nascimento=?,sexo=?,endereco=?,email=? where cpf=?";
    try {
        pst=conexao.prepareStatement(sql);
        pst.setString(1,jTextNome.getText());
        pst.setString(2,jTextRg.getText());
        pst.setString(3,jTextNumero.getText());
        pst.setString(4,jTextnascimento.getText());
        pst.setString(5,jTextSexo.getText());
        pst.setString(6,jTextendereco.getText());
        pst.setString(7,jTextEmail.getText());
        pst.setString(8,jTextCpf.getText());
        
        int adicionado = pst.executeUpdate();
        
        if (adicionado > 0) {
          JOptionPane.showMessageDialog(null, "Dados atualizado!"); 
          jTextCpf.setText(null);
          jTextNome.setText(null);
          jTextRg.setText(null);
          jTextNumero.setText(null);
          jTextnascimento.setText(null);
          jTextSexo.setText(null);
          jTextendereco.setText(null);
          jTextEmail.setText(null);
            
        } else {
        }
       
    } catch (Exception e) {
     JOptionPane.showMessageDialog(null,e);   
        
    }


}
 
 private void remover(){

 int confirma = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja remover o usuário?","Atenção",JOptionPane.YES_NO_OPTION);
  if (confirma==JOptionPane.YES_OPTION){
      String sql= "delete from pessoa where cpf=?";
      try {
         pst = conexao.prepareStatement(sql); 
         pst.setString(1,jTextCpf.getText());
         int removido = pst.executeUpdate();
         if(removido>0){
         JOptionPane.showMessageDialog(null,"Usuário removido com sucesso");
         jTextCpf.setText(null);
          jTextNome.setText(null);
          jTextRg.setText(null);
          jTextNumero.setText(null);
          jTextnascimento.setText(null);
          jTextSexo.setText(null);
          jTextendereco.setText(null);
          jTextEmail.setText(null);
         }
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null,e);  
      }
      
  }

}

    
   /*
   private void adicionar(){
   
       String sql = "insert into pesssoa(cpf,nome,rg,telefone,nascimento,sexo,endereco,email) values(?,?,?,?,?,?,?,?)";
        try {
            pst=conexao.prepareStatement(sql);
           pst.setString(1,jTextCpf.getText());
             pst.setString(2,jTextNome.getText());
               pst.setString(3,jTextRg.getText());
                 pst.setString(4,jTextNumero.getText());
                   pst.setString(5,jTextnascimento.getText());
                   pst.setString(6,jTextSexo.getText());
                    pst.setString(7,jTextendereco.getText());
                     pst.setString(8,jTextEmail.getText());
                     
                     pst.executeUpdate();
           
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e + "log:" +sql);
       }
   }/*
   
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbl_nome = new javax.swing.JLabel();
        btn_buscar = new javax.swing.JButton();
        jTextNome = new javax.swing.JTextField();
        lbl_rg = new javax.swing.JLabel();
        btn_deletar = new javax.swing.JButton();
        btn_atualizar = new javax.swing.JButton();
        jTextRg = new javax.swing.JTextField();
        lbl_cpf = new javax.swing.JLabel();
        jTextCpf = new javax.swing.JTextField();
        lbl_dataNas = new javax.swing.JLabel();
        jTextnascimento = new javax.swing.JTextField();
        lbl_endereco = new javax.swing.JLabel();
        jTextendereco = new javax.swing.JTextField();
        lbl_numero = new javax.swing.JLabel();
        jTextNumero = new javax.swing.JTextField();
        lbl_email = new javax.swing.JLabel();
        jTextEmail = new javax.swing.JTextField();
        btn_cancelar = new javax.swing.JButton();
        lbl_sexo = new javax.swing.JLabel();
        jTextSexo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblLimiteCredito = new javax.swing.JLabel();
        jTextLimiteCredito = new javax.swing.JTextField();
        lbldebito = new javax.swing.JLabel();
        jTextDebito = new javax.swing.JTextField();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mania de Comprar- Cadastrar Cliente");

        lbl_nome.setText("*Nome:");

        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/maniaComprar/icones/read.png"))); // NOI18N
        btn_buscar.setToolTipText("Buscar");
        btn_buscar.setPreferredSize(new java.awt.Dimension(80, 80));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        jTextNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNomeActionPerformed(evt);
            }
        });

        lbl_rg.setText("*RG:");

        btn_deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/maniaComprar/icones/delete.png"))); // NOI18N
        btn_deletar.setToolTipText("Excluir");
        btn_deletar.setPreferredSize(new java.awt.Dimension(80, 80));
        btn_deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletarActionPerformed(evt);
            }
        });

        btn_atualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/maniaComprar/icones/atualizar.png"))); // NOI18N
        btn_atualizar.setToolTipText("Atualizar");
        btn_atualizar.setPreferredSize(new java.awt.Dimension(80, 80));
        btn_atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atualizarActionPerformed(evt);
            }
        });

        jTextRg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextRgActionPerformed(evt);
            }
        });

        lbl_cpf.setText("*CPF:");

        lbl_dataNas.setText("*Data de Nas.");

        jTextnascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextnascimentoActionPerformed(evt);
            }
        });

        lbl_endereco.setText("*Endereço:");

        jTextendereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextenderecoActionPerformed(evt);
            }
        });

        lbl_numero.setText("*Fone:");

        jTextNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNumeroActionPerformed(evt);
            }
        });

        lbl_email.setText("*E-mail:");

        jTextEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextEmailActionPerformed(evt);
            }
        });

        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        lbl_sexo.setText("Sexo:");

        jTextSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextSexoActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/maniaComprar/icones/create.png"))); // NOI18N
        jButton1.setToolTipText("Cadastrar");
        jButton1.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("*campos obrigatórios");

        lblLimiteCredito.setText("*Limite de Crédito:");

        lbldebito.setText("*Débito:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(btn_deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(btn_atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_rg, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextRg, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lbl_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lbl_dataNas)
                                                        .addGap(36, 36, 36)
                                                        .addComponent(jTextnascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(128, 128, 128)
                                                        .addComponent(lbl_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                                                .addComponent(jTextendereco, javax.swing.GroupLayout.Alignment.TRAILING))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_cancelar)
                                        .addGap(53, 53, 53)))
                                .addContainerGap(20, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_email)
                                    .addComponent(lbl_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblLimiteCredito)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(lbldebito)
                                        .addGap(35, 35, 35)
                                        .addComponent(jTextDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_atualizar, btn_buscar, btn_deletar, jButton1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_nome))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_rg)
                            .addComponent(jTextRg, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_cpf)
                            .addComponent(jTextCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextnascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_dataNas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_numero)
                            .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_sexo))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextendereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_endereco))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_email)
                            .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLimiteCredito)
                            .addComponent(jTextLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbldebito)
                            .addComponent(jTextDebito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(btn_cancelar))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_atualizar, btn_buscar, btn_deletar, jButton1});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextnascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextnascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextnascimentoActionPerformed

    private void jTextRgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextRgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextRgActionPerformed

    private void jTextenderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextenderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextenderecoActionPerformed

    private void btn_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atualizarActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btn_atualizarActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
         //new TelaConsulta().setVisible(true);
    // dispose();
    consultar();
    
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void jTextSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextSexoActionPerformed

    private void jTextNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNumeroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here
            adicionar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNomeActionPerformed

    private void jTextEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextEmailActionPerformed

    private void btn_deletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletarActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btn_deletarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
         int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atencao", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION){
            //System.exit(0);
           dispose();
        }// TODO add your handling code here:
        
    }//GEN-LAST:event_btn_cancelarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_atualizar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_deletar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextCpf;
    private javax.swing.JTextField jTextDebito;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextLimiteCredito;
    private javax.swing.JTextField jTextNome;
    private javax.swing.JTextField jTextNumero;
    private javax.swing.JTextField jTextRg;
    private javax.swing.JTextField jTextSexo;
    private javax.swing.JTextField jTextendereco;
    private javax.swing.JTextField jTextnascimento;
    private javax.swing.JLabel lblLimiteCredito;
    private javax.swing.JLabel lbl_cpf;
    private javax.swing.JLabel lbl_dataNas;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_endereco;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JLabel lbl_numero;
    private javax.swing.JLabel lbl_rg;
    private javax.swing.JLabel lbl_sexo;
    private javax.swing.JLabel lbldebito;
    // End of variables declaration//GEN-END:variables
}
