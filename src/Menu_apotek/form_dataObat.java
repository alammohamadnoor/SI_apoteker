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

public class form_dataObat extends javax.swing.JFrame {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel TabelObat;

    /**
     * Creates new form form_dataObat
     */
    public form_dataObat() {
        initComponents();
        datatable();
        kosong();
        aktif();        
        auto();        
    }
    
    protected void aktif (){
        txtnmObat.requestFocus();
    }
    
    protected void kosong(){
        //txtid_anggota.setText("");
        txtnmObat.setText("");
        txtjmlObat.setText("");
        txtberatObat.setText("");
        txthrgObat.setText("");

    }

    protected void datatable(){
         Object [] Baris = {"ID Obat","Nama Obat","Berat","Jumlah Obat","Harga Satuan"};
            TabelObat = new DefaultTableModel(null,Baris);
            String cariitem = txtcari.getText();
            
            try{
                String sql = "SELECT * FROM tb_obat where id_obat like '%"+cariitem+"%' or nama_obat like '%"+cariitem+"%'";
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    TabelObat.addRow(new Object [] {
                        hasil.getString(1),
                        hasil.getString(2),
                        hasil.getString(3),
                        hasil.getString(4),
                        hasil.getString(5)
                    });
                }
                tbobat.setModel(TabelObat);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal Dipanggil"+e);
            }
    }

    public void auto() {
    txtidObat.setText("");
        try {
            String sql1 = "select max(right(id_obat,4)) from tb_obat";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()) {
                if(hasil.first()==false) {
                    txtidObat.setText("OBT001");
                    
                } else {
                    hasil.last();
                    int code = hasil.getInt(1)+1;
                    String Nomor = String.valueOf(code);
                    int noLong = Nomor.length();
                    
                    for (int a=0; a<4-noLong;a++) {
                        Nomor = "0" + Nomor;
                    }
                    txtidObat.setText("OBT" + Nomor);
                    
                }
            }            
            } catch (Exception e) {
        }
                   txtidObat.setEnabled(false);
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
        txtidObat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnmObat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtjmlObat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtberatObat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txthrgObat = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbobat = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        bbatal = new javax.swing.JButton();
        bcari = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        bkembali2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtidObat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtidObat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtidObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidObatActionPerformed(evt);
            }
        });
        jPanel1.add(txtidObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 180, 20));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel5.setText("ID Obat");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 20));

        txtnmObat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnmObat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnmObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmObatActionPerformed(evt);
            }
        });
        jPanel1.add(txtnmObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 260, 20));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel6.setText("Nama Obat");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, 20));

        txtjmlObat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtjmlObat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtjmlObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjmlObatActionPerformed(evt);
            }
        });
        jPanel1.add(txtjmlObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 260, 20));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel7.setText("Jumlah Obat");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, 20));

        txtberatObat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtberatObat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtberatObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtberatObatActionPerformed(evt);
            }
        });
        jPanel1.add(txtberatObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 260, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel9.setText("Harga Satuan");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, 20));

        txthrgObat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txthrgObat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txthrgObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthrgObatActionPerformed(evt);
            }
        });
        jPanel1.add(txthrgObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 240, 20));

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("DATA OBAT");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 30));

        tbobat.setModel(new javax.swing.table.DefaultTableModel(
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
        tbobat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbobatMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbobat);

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
        jLabel10.setText("ID/Nama Obat");
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
        jLabel11.setText("Berat");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, 20));

        bkembali2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bkembali2.setText("Kembali");
        bkembali2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkembali2ActionPerformed(evt);
            }
        });
        jPanel1.add(bkembali2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 550));

        setSize(new java.awt.Dimension(716, 591));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtidObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidObatActionPerformed

    private void txtnmObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmObatActionPerformed

    private void txtjmlObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjmlObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlObatActionPerformed

    private void txtberatObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtberatObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtberatObatActionPerformed

    private void txthrgObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthrgObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthrgObatActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void bkembali2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkembali2ActionPerformed
        dispose();
        new Menu_apotek.Menu_utama().setVisible(true);
    }//GEN-LAST:event_bkembali2ActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        String sql = "insert into tb_obat values (?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtidObat.getText());
            stat.setString(2, txtnmObat.getText());
            stat.setString(3, txtberatObat.getText());
            stat.setString(4, txtjmlObat.getText());
            stat.setString(5, txthrgObat.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil Disimpan");
            kosong();
            txtidObat.requestFocus();
            auto();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan"+e);
        }
        datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        try {
            String sql = "update tb_obat set nama_obat =?,berat =?,jumlah_obat=?,harga_satuan=? where id_obat = '"+txtidObat.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnmObat.getText());
            stat.setString(2, txtberatObat.getText());
            stat.setString(3, txtjmlObat.getText());
            stat.setString(4, txthrgObat.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil Diubah");
            kosong();
            txtidObat.requestFocus();
            auto();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah" +e);
        }
        datatable();         // TODO add your handling code here:
    }//GEN-LAST:event_bubahActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "konfirmasi dialog",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_obat where id_obat = '"+txtidObat.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                txtnmObat.requestFocus();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Data Gagal Dihapus"+e);
            }
            datatable();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_bhapusActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        kosong();
        datatable();
        auto();        // TODO add your handling code here:
    }//GEN-LAST:event_bbatalActionPerformed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable ();        // TODO add your handling code here:
    }//GEN-LAST:event_bcariActionPerformed

    private void tbobatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbobatMouseClicked
        int bar = tbobat.getSelectedRow();
        String a = tbobat.getValueAt(bar, 0). toString();
        String b = tbobat.getValueAt(bar, 1). toString();
        String c = tbobat.getValueAt(bar, 2). toString();
        String d = tbobat.getValueAt(bar, 3). toString();
        String e = tbobat.getValueAt(bar, 4). toString();
        //String f = tbmember.getValueAt(bar, 5). toString();
        
        
        txtidObat.setText(a);
        txtnmObat.setText(b);
        txtberatObat.setText(c);
        txtjmlObat.setText(d);
        txthrgObat.setText(e);    // TODO add your handling code here:
    }//GEN-LAST:event_tbobatMouseClicked

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
            java.util.logging.Logger.getLogger(form_dataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_dataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_dataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_dataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_dataObat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkembali2;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbobat;
    private javax.swing.JTextField txtberatObat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txthrgObat;
    private javax.swing.JTextField txtidObat;
    private javax.swing.JTextField txtjmlObat;
    private javax.swing.JTextField txtnmObat;
    // End of variables declaration//GEN-END:variables
}
