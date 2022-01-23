/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu_apotek;

import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Connection.Koneksi;


public class form_dataApoteker extends javax.swing.JFrame {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel TabelApoteker;

    /**
     * Creates new form form_dataApoteker
     */
    public form_dataApoteker() {
        initComponents();
        datatable();
        kosong();
        aktif();        
        auto();        
    }

    protected void aktif (){
        txtnmApoteker.requestFocus();
    }
    
    protected void kosong(){
        //txtid_anggota.setText("");
        txtnmApoteker.setText("");
        cbjenkel.setSelectedItem(null);
        txtalamat.setText("");
        txtemail.setText("");
        txtnotelp.setText("");
    }

    protected void datatable(){
         Object [] Baris = {"ID Apoteker","Nama Apoteker","Jenis Kelamin","Alamat","Email","No. Telepon"};
            TabelApoteker = new DefaultTableModel(null,Baris);
            String cariitem = txtcari.getText();
            
            try{
                String sql = "SELECT * FROM tb_apoteker where id_apoteker like '%"+cariitem+"%' or nama_apoteker like '%"+cariitem+"%'";
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    TabelApoteker.addRow(new Object [] {
                        hasil.getString(1),
                        hasil.getString(2),
                        hasil.getString(3),
                        hasil.getString(4),
                        hasil.getString(5),
                        hasil.getString(6)
                    });
                }
                tbapoteker.setModel(TabelApoteker);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "data gagal Dipanggil"+e);
            }
    }

    public void auto() {
    txtidApoteker.setText("");
        try {
            String sql1 = "select max(right(id_apoteker,4)) from tb_apoteker";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()) {
                if(hasil.first()==false) {
                    txtidApoteker.setText("APT001");
                    
                } else {
                    hasil.last();
                    int code = hasil.getInt(1)+1;
                    String Nomor = String.valueOf(code);
                    int noLong = Nomor.length();
                    
                    for (int a=0; a<4-noLong;a++) {
                        Nomor = "0" + Nomor;
                    }
                    txtidApoteker.setText("APT" + Nomor);
                    
                }
            }            
            } catch (Exception e) {
        }
                   txtidApoteker.setEnabled(false);
                   //txtangg.requestFocus();
    }

        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtidApoteker = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnmApoteker = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtalamat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbapoteker = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        bbatal = new javax.swing.JButton();
        bcari = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtnotelp = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbjenkel = new javax.swing.JComboBox<>();
        bkembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtidApoteker.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtidApoteker.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtidApoteker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidApotekerActionPerformed(evt);
            }
        });
        jPanel1.add(txtidApoteker, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 180, 20));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel5.setText("ID Apoteker");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 20));

        txtnmApoteker.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnmApoteker.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnmApoteker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmApotekerActionPerformed(evt);
            }
        });
        jPanel1.add(txtnmApoteker, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 260, 20));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel6.setText("Nama Apoteker");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, 20));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel7.setText("Jenis Kelamin");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, 20));

        txtalamat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtalamat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtalamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtalamatActionPerformed(evt);
            }
        });
        jPanel1.add(txtalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 260, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel9.setText("Email");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, 20));

        txtemail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtemail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        jPanel1.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 260, 20));

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("DATA APOTEKER");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 30));

        tbapoteker.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbapoteker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbapotekerMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbapoteker);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 660, 170));

        txtcari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtcari.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        jPanel1.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 260, 20));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel10.setText("ID/Nama Apoteker");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, 20));

        bbatal.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bbatal.setText("Batal");
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });
        jPanel1.add(bbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 90, 30));

        bcari.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcari.setText("Cari");
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });
        jPanel1.add(bcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 80, 30));

        bsimpan.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 90, 30));

        bubah.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bubah.setText("Ubah");
        bubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubahActionPerformed(evt);
            }
        });
        jPanel1.add(bubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 90, 30));

        bhapus.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        jPanel1.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 90, 30));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel11.setText("Alamat");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, 20));

        txtnotelp.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnotelp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnotelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnotelpActionPerformed(evt);
            }
        });
        jPanel1.add(txtnotelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 260, 20));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel12.setText("No. Telepon");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, -1, 20));

        cbjenkel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));
        cbjenkel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjenkelActionPerformed(evt);
            }
        });
        jPanel1.add(cbjenkel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 260, -1));

        bkembali.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bkembali.setText("Kembali");
        bkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkembaliActionPerformed(evt);
            }
        });
        jPanel1.add(bkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 550));

        setSize(new java.awt.Dimension(716, 596));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtidApotekerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidApotekerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidApotekerActionPerformed

    private void txtnmApotekerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmApotekerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmApotekerActionPerformed

    private void txtalamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtalamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtalamatActionPerformed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtnotelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnotelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnotelpActionPerformed

    private void cbjenkelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjenkelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbjenkelActionPerformed

    private void bkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkembaliActionPerformed
        dispose();
        new Menu_apotek.Menu_utama().setVisible(true);
    }//GEN-LAST:event_bkembaliActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        String sql = "insert into tb_apoteker values (?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtidApoteker.getText());
            stat.setString(2, txtnmApoteker.getText());
            stat.setString(3, cbjenkel.getItemAt(cbjenkel.getSelectedIndex()));
            stat.setString(4, txtalamat.getText());
            stat.setString(5, txtemail.getText());
            stat.setString(6, txtnotelp.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil Disimpan");
            kosong();
            txtidApoteker.requestFocus();
            auto();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan"+e);
        }
        datatable();         // TODO add your handling code here:
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        try {
            String sql = "update tb_apoteker set nama_apoteker=?,jenkel=?,alamat=?,email=?,no_telp=? where id_apoteker = '"+txtidApoteker.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnmApoteker.getText());
            stat.setString(2, cbjenkel.getItemAt(cbjenkel.getSelectedIndex()));
            stat.setString(3, txtalamat.getText());
            stat.setString(4, txtemail.getText());
            stat.setString(5, txtnotelp.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil Diubah");
            kosong();
            txtidApoteker.requestFocus();
            auto();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah" +e);
        }
        datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_bubahActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "konfirmasi dialog",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_apoteker where id_apoteker = '"+txtidApoteker.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                txtnmApoteker.requestFocus();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Data Gagal Dihapus"+e);
            }
            datatable();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_bhapusActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        kosong();
        datatable();
        auto();
    }//GEN-LAST:event_bbatalActionPerformed

    private void tbapotekerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbapotekerMouseClicked
        int bar = tbapoteker.getSelectedRow();
        String a = tbapoteker.getValueAt(bar, 0). toString();
        String b = tbapoteker.getValueAt(bar, 1). toString();
        String c = tbapoteker.getValueAt(bar, 2). toString();
        String d = tbapoteker.getValueAt(bar, 3). toString();
        String e = tbapoteker.getValueAt(bar, 4). toString();
        String f = tbapoteker.getValueAt(bar, 5). toString();
        //String f = tbmember.getValueAt(bar, 5). toString();
        
        
        txtidApoteker.setText(a);
        txtnmApoteker.setText(b);
        cbjenkel.setSelectedItem(c);
        txtalamat.setText(d);
        txtemail.setText(e);
        txtnotelp.setText(f);         // TODO add your handling code here:
    }//GEN-LAST:event_tbapotekerMouseClicked

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_bcariActionPerformed

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
            java.util.logging.Logger.getLogger(form_dataApoteker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_dataApoteker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_dataApoteker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_dataApoteker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_dataApoteker().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkembali;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JComboBox<String> cbjenkel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbapoteker;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtidApoteker;
    private javax.swing.JTextField txtnmApoteker;
    private javax.swing.JTextField txtnotelp;
    // End of variables declaration//GEN-END:variables
}