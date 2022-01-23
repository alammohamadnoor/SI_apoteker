
package Menu_apotek;

import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Connection.Koneksi;

public class form_dataDokter extends javax.swing.JFrame {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel TabelDokter;
    String jadwalPraktek;
    /**
     * Creates new form form_dataDokter
     */
    public form_dataDokter() {
        initComponents();
        datatable();
        kosong();
        aktif();        
        auto();   
        comboJadwalPraktek();
    }
    
    protected void aktif (){
        txtnmDokter.requestFocus();
    }
    
    protected void kosong(){
        txtnmDokter.setText("");
        cbjenkel.setSelectedItem(null);
        cbjadwalPraktek.setSelectedItem(null);
        txtalamat.setText("");
        txtemail.setText("");
        txtnotelp.setText("");
    }

    protected void datatable(){
         Object [] Baris = {"ID Dokter","Nama Dokter","Jenis Kelamin","Jadwal Praktek","Alamat","Email","No. Telepon"};
            TabelDokter = new DefaultTableModel(null,Baris);
            String cariitem = txtcari.getText();
            
            try{
                String sql = "SELECT * FROM tb_dokter where id_dokter like '%"+cariitem+"%' or nama_dokter like '%"+cariitem+"%'";
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    TabelDokter.addRow(new Object [] {
                        hasil.getString(1),
                        hasil.getString(2),
                        hasil.getString(3),
                        hasil.getString(4),
                        hasil.getString(5),
                        hasil.getString(6),
                        hasil.getString(7)
                    });
                }
                tbdokter.setModel(TabelDokter);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "data gagal Dipanggil"+e);
            }
    }

    public void auto() {
    txtidDokter.setText("");
        try {
            String sql1 = "select max(right(id_dokter,4)) from tb_dokter";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()) {
                if(hasil.first()==false) {
                    txtidDokter.setText("DOK001");
                    
                } else {
                    hasil.last();
                    int code = hasil.getInt(1)+1;
                    String Nomor = String.valueOf(code);
                    int noLong = Nomor.length();
                    
                    for (int a=0; a<4-noLong;a++) {
                        Nomor = "0" + Nomor;
                    }
                    txtidDokter.setText("DOK" + Nomor);
                    
                }
            }            
            } catch (Exception e) {
        }
                   txtidDokter.setEnabled(false);
                   //txtangg.requestFocus();
    }

    protected void comboJadwalPraktek (){
       //cbidangg.removeAllItems();
       //cbidangg.addItem("Pilih...");
       try {
          String sql ="select * from tb_jadwal";
          Statement st=conn.createStatement();
          ResultSet cjadwalPraktek = st.executeQuery(sql);
           while (cjadwalPraktek.next()) {
               String jadwalPraktek =cjadwalPraktek.getString("jadwal_praktek");
               cbjadwalPraktek.addItem(jadwalPraktek);
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Gagal Menampilkan Id User \n" +e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        txtidDokter = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnmDokter = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtalamat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbdokter = new javax.swing.JTable();
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
        jLabel13 = new javax.swing.JLabel();
        cbjadwalPraktek = new javax.swing.JComboBox<>();
        bkembali1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtidDokter.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtidDokter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtidDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidDokterActionPerformed(evt);
            }
        });
        jPanel1.add(txtidDokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 180, 20));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel5.setText("ID Dokter");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 20));

        txtnmDokter.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnmDokter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnmDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmDokterActionPerformed(evt);
            }
        });
        jPanel1.add(txtnmDokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 260, 20));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel6.setText("Nama Dokter");
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
        jLabel8.setText("DATA DOKTER");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 30));

        tbdokter.setModel(new javax.swing.table.DefaultTableModel(
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
        tbdokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdokterMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbdokter);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 660, 170));

        txtcari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtcari.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        jPanel1.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 260, 20));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel10.setText("ID/Nama Dokter");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, 20));

        bbatal.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bbatal.setText("Batal");
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });
        jPanel1.add(bbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 90, 30));

        bcari.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcari.setText("Cari");
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });
        jPanel1.add(bcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 80, 30));

        bsimpan.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 90, 30));

        bubah.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bubah.setText("Ubah");
        bubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubahActionPerformed(evt);
            }
        });
        jPanel1.add(bubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 90, 30));

        bhapus.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        jPanel1.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 90, 30));

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

        cbjenkel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbjenkel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));
        cbjenkel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjenkelActionPerformed(evt);
            }
        });
        jPanel1.add(cbjenkel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 260, -1));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel13.setText("Jadwal Praktek");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, 20));

        cbjadwalPraktek.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbjadwalPraktek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjadwalPraktekActionPerformed(evt);
            }
        });
        jPanel1.add(cbjadwalPraktek, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 260, -1));

        bkembali1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bkembali1.setText("Kembali");
        bkembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkembali1ActionPerformed(evt);
            }
        });
        jPanel1.add(bkembali1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 590));

        setSize(new java.awt.Dimension(718, 630));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtidDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidDokterActionPerformed

    private void txtnmDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmDokterActionPerformed

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

    private void cbjadwalPraktekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjadwalPraktekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbjadwalPraktekActionPerformed

    private void bkembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkembali1ActionPerformed
        dispose();
        new Menu_apotek.Menu_utama().setVisible(true);
    }//GEN-LAST:event_bkembali1ActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        String sql = "insert into tb_dokter values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtidDokter.getText());
            stat.setString(2, txtnmDokter.getText());
            stat.setString(3, cbjenkel.getItemAt(cbjenkel.getSelectedIndex()));
            stat.setString(4, cbjadwalPraktek.getItemAt(cbjadwalPraktek.getSelectedIndex()));
            stat.setString(5, txtalamat.getText());
            stat.setString(6, txtemail.getText());
            stat.setString(7, txtnotelp.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil Disimpan");
            kosong();
            txtidDokter.requestFocus();
            auto();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan"+e);
        }
        datatable();
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        try {
            String sql = "update tb_dokter set nama_dokter=?,jenkel=?,jadwal_praktek=?,alamat=?,email=?,no_telp=? where id_dokter = '"+txtidDokter.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnmDokter.getText());
            stat.setString(2, cbjenkel.getItemAt(cbjenkel.getSelectedIndex()));
            stat.setString(3, cbjadwalPraktek.getItemAt(cbjadwalPraktek.getSelectedIndex()));
            stat.setString(4, txtalamat.getText());
            stat.setString(5, txtemail.getText());
            stat.setString(6, txtnotelp.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil Diubah");
            kosong();
            txtidDokter.requestFocus();
            auto();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah" +e);
        }
        datatable();
    }//GEN-LAST:event_bubahActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "konfirmasi dialog",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_dokter where id_dokter = '"+txtidDokter.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                txtnmDokter.requestFocus();
                auto();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Data Gagal Dihapus"+e);
            }
            datatable();
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        kosong();
        datatable();
        auto();
    }//GEN-LAST:event_bbatalActionPerformed

    private void tbdokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdokterMouseClicked
        int bar = tbdokter.getSelectedRow();
        String a = tbdokter.getValueAt(bar, 0). toString();
        String b = tbdokter.getValueAt(bar, 1). toString();
        String c = tbdokter.getValueAt(bar, 2). toString();
        String d = tbdokter.getValueAt(bar, 3). toString();
        String e = tbdokter.getValueAt(bar, 4). toString();
        String f = tbdokter.getValueAt(bar, 5). toString();
        String g = tbdokter.getValueAt(bar, 6). toString();
        //String f = tbmember.getValueAt(bar, 5). toString();
        
        
        txtidDokter.setText(a);
        txtnmDokter.setText(b);
        cbjenkel.setSelectedItem(c);
        cbjadwalPraktek.setSelectedItem(d);
        txtalamat.setText(e);
        txtemail.setText(f);
        txtnotelp.setText(g);        // TODO add your handling code here:
    }//GEN-LAST:event_tbdokterMouseClicked

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable();
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
            java.util.logging.Logger.getLogger(form_dataDokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_dataDokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_dataDokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_dataDokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_dataDokter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkembali1;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JComboBox<String> cbjadwalPraktek;
    private javax.swing.JComboBox<String> cbjenkel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbdokter;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtidDokter;
    private javax.swing.JTextField txtnmDokter;
    private javax.swing.JTextField txtnotelp;
    // End of variables declaration//GEN-END:variables
}
