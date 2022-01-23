/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu_apotek;

import Connection.Koneksi;
import Login.Login_apotek;
import Login.Registrasi_Admin;
import PopUp.Data_Apoteker;
import PopUp.Data_Dokter;
import PopUp.Data_Obat;
import PopUp.Data_Pasien1;
import java.io.File;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class form_cetakLaporan extends javax.swing.JFrame {
    private Connection conn = new Koneksi().connect();
    /**
     * Creates new form form_cetakLaporan
     */
    public form_cetakLaporan() {
        initComponents();
    }

    public void printDataPasien () {
        try {
            Locale locale = new Locale( "id", "ID" );
            HashMap par = new HashMap();
            par.put(JRParameter.REPORT_LOCALE, locale);
            JasperPrint jp = JasperFillManager.fillReport
            (form_dataPasien.class.getResourceAsStream("/Report_apotek/Report_dataPasien.jasper"), par, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
        }
    }    
    
    public void printDataApoteker () {
        try {
            Locale locale = new Locale( "id", "ID" );
            HashMap par = new HashMap();
            par.put(JRParameter.REPORT_LOCALE, locale);
            JasperPrint jp = JasperFillManager.fillReport
            (form_dataApoteker.class.getResourceAsStream("/Report_apotek/Report_dataApoteker.jasper"), par, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
        }
    }

    public void printDataDokter () {
        try {
            Locale locale = new Locale( "id", "ID" );
            HashMap par = new HashMap();
            par.put(JRParameter.REPORT_LOCALE, locale);
            JasperPrint jp = JasperFillManager.fillReport
            (form_dataDokter.class.getResourceAsStream("/Report_apotek/Report_dataDokter.jasper"), par, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
        }
    }

    public void printDataObat () {
        try {
            Locale locale = new Locale( "id", "ID" );
            HashMap par = new HashMap();
            par.put(JRParameter.REPORT_LOCALE, locale);
            JasperPrint jp = JasperFillManager.fillReport
            (form_dataObat.class.getResourceAsStream("/Report_apotek/Report_dataObat.jasper"), par, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
        }
    }

    public void printDataAdmin () {
        try {
            Locale locale = new Locale( "id", "ID" );
            HashMap par = new HashMap();
            par.put(JRParameter.REPORT_LOCALE, locale);
            JasperPrint jp = JasperFillManager.fillReport
            (form_dataAdmin.class.getResourceAsStream("/Report_apotek/Report_dataAdmin.jasper"), par, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
        }
    }

    public void printDataJadwal () {
        try {
            Locale locale = new Locale( "id", "ID" );
            HashMap par = new HashMap();
            par.put(JRParameter.REPORT_LOCALE, locale);
            JasperPrint jp = JasperFillManager.fillReport
            (form_dataJadwal.class.getResourceAsStream("/Report_apotek/Report_dataJadwal.jasper"), par, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
        }
    }    
    
    public void printDataPemesanan () {
        try {
            Locale locale = new Locale( "id", "ID" );
            HashMap par = new HashMap();
            par.put(JRParameter.REPORT_LOCALE, locale);
            JasperPrint jp = JasperFillManager.fillReport
            (form_pemesananObat.class.getResourceAsStream("/Report_apotek/Report_pemesananObat.jasper"), par, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
        }
    }
    
    public void printDataKonsultasi () {
        try {
            Locale locale = new Locale( "id", "ID" );
            HashMap par = new HashMap();
            par.put(JRParameter.REPORT_LOCALE, locale);
            JasperPrint jp = JasperFillManager.fillReport
            (form_konsultasiDokter.class.getResourceAsStream("/Report_apotek/Report_dataKonsultasi.jasper"), par, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
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
        bpemesananObat = new javax.swing.JButton();
        bkonsul = new javax.swing.JButton();
        Icon_pesan = new javax.swing.JLabel();
        text_Konsultasi = new javax.swing.JLabel();
        Icon_Konsultasi = new javax.swing.JLabel();
        text_pesanObat = new javax.swing.JLabel();
        bdataObat = new javax.swing.JButton();
        text_dataObat = new javax.swing.JLabel();
        icon_dataObat = new javax.swing.JLabel();
        bdataPasien = new javax.swing.JButton();
        text_dataPasien = new javax.swing.JLabel();
        icon_dataPasien = new javax.swing.JLabel();
        bdataApoteker = new javax.swing.JButton();
        text_dataApoteker = new javax.swing.JLabel();
        icon_apoteker = new javax.swing.JLabel();
        bdataAdmin = new javax.swing.JButton();
        text_dataAdmin = new javax.swing.JLabel();
        icon_dataAdmin = new javax.swing.JLabel();
        bdataDokter = new javax.swing.JButton();
        text_dataDokter = new javax.swing.JLabel();
        icon_dokter = new javax.swing.JLabel();
        bdataJadwal = new javax.swing.JButton();
        text_dataJadwal = new javax.swing.JLabel();
        icon_jadwalPraktek = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        bkembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bpemesananObat.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        bpemesananObat.setBorder(null);
        bpemesananObat.setBorderPainted(false);
        bpemesananObat.setContentAreaFilled(false);
        bpemesananObat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bpemesananObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bpemesananObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpemesananObatActionPerformed(evt);
            }
        });
        jPanel1.add(bpemesananObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 130, 110));

        bkonsul.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        bkonsul.setBorder(null);
        bkonsul.setBorderPainted(false);
        bkonsul.setContentAreaFilled(false);
        bkonsul.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bkonsul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bkonsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkonsulActionPerformed(evt);
            }
        });
        jPanel1.add(bkonsul, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 130, 110));

        Icon_pesan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/pills_70px.png"))); // NOI18N
        jPanel1.add(Icon_pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 70, 60));

        text_Konsultasi.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        text_Konsultasi.setText("Konsultasi");
        jPanel1.add(text_Konsultasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 320, -1, 20));

        Icon_Konsultasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/doctors_bag_70px.png"))); // NOI18N
        jPanel1.add(Icon_Konsultasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 70, 60));

        text_pesanObat.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        text_pesanObat.setText("Pemesanan Obat");
        jPanel1.add(text_pesanObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, -1, 20));

        bdataObat.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        bdataObat.setBorder(null);
        bdataObat.setBorderPainted(false);
        bdataObat.setContentAreaFilled(false);
        bdataObat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdataObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bdataObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdataObatActionPerformed(evt);
            }
        });
        jPanel1.add(bdataObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 130, 110));

        text_dataObat.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        text_dataObat.setText("Data Obat");
        jPanel1.add(text_dataObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, 20));

        icon_dataObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/pillsData_70px.png"))); // NOI18N
        jPanel1.add(icon_dataObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 70, 60));

        bdataPasien.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        bdataPasien.setBorder(null);
        bdataPasien.setBorderPainted(false);
        bdataPasien.setContentAreaFilled(false);
        bdataPasien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdataPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bdataPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdataPasienActionPerformed(evt);
            }
        });
        jPanel1.add(bdataPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 130, 110));

        text_dataPasien.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        text_dataPasien.setText("Data Pasien");
        jPanel1.add(text_dataPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, 20));

        icon_dataPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/sick_70px.png"))); // NOI18N
        jPanel1.add(icon_dataPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 70, 60));

        bdataApoteker.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        bdataApoteker.setBorder(null);
        bdataApoteker.setBorderPainted(false);
        bdataApoteker.setContentAreaFilled(false);
        bdataApoteker.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdataApoteker.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bdataApoteker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdataApotekerActionPerformed(evt);
            }
        });
        jPanel1.add(bdataApoteker, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 130, 110));

        text_dataApoteker.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        text_dataApoteker.setText("Data Apoteker");
        jPanel1.add(text_dataApoteker, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, -1, 20));

        icon_apoteker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/doctor_female_70px.png"))); // NOI18N
        jPanel1.add(icon_apoteker, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 70, 60));

        bdataAdmin.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        bdataAdmin.setBorder(null);
        bdataAdmin.setBorderPainted(false);
        bdataAdmin.setContentAreaFilled(false);
        bdataAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdataAdmin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bdataAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdataAdminActionPerformed(evt);
            }
        });
        jPanel1.add(bdataAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 130, 110));

        text_dataAdmin.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        text_dataAdmin.setText("Data Admin");
        jPanel1.add(text_dataAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, -1, 20));

        icon_dataAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/user_70px.png"))); // NOI18N
        jPanel1.add(icon_dataAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 70, 60));

        bdataDokter.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        bdataDokter.setBorder(null);
        bdataDokter.setBorderPainted(false);
        bdataDokter.setContentAreaFilled(false);
        bdataDokter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdataDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bdataDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdataDokterActionPerformed(evt);
            }
        });
        jPanel1.add(bdataDokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 130, 110));

        text_dataDokter.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        text_dataDokter.setText("Data Dokter");
        jPanel1.add(text_dataDokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, -1, 20));

        icon_dokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/doctor_male_70px.png"))); // NOI18N
        jPanel1.add(icon_dokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 70, 60));

        bdataJadwal.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        bdataJadwal.setBorder(null);
        bdataJadwal.setBorderPainted(false);
        bdataJadwal.setContentAreaFilled(false);
        bdataJadwal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdataJadwal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bdataJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdataJadwalActionPerformed(evt);
            }
        });
        jPanel1.add(bdataJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 130, 110));

        text_dataJadwal.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        text_dataJadwal.setText("Jadwal Praktek");
        jPanel1.add(text_dataJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 460, -1, 20));

        icon_jadwalPraktek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/schedule_70px.png"))); // NOI18N
        jPanel1.add(icon_jadwalPraktek, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 70, 60));

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CETAK LAPORAN");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 30));

        bkembali.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bkembali.setText("Kembali");
        bkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkembaliActionPerformed(evt);
            }
        });
        jPanel1.add(bkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 510));

        setSize(new java.awt.Dimension(568, 552));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bpemesananObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpemesananObatActionPerformed
        printDataPemesanan();
    }//GEN-LAST:event_bpemesananObatActionPerformed

    private void bkonsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkonsulActionPerformed
        printDataKonsultasi();
    }//GEN-LAST:event_bkonsulActionPerformed

    private void bdataObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdataObatActionPerformed
        printDataObat();
    }//GEN-LAST:event_bdataObatActionPerformed

    private void bdataPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdataPasienActionPerformed
        printDataPasien();
    }//GEN-LAST:event_bdataPasienActionPerformed

    private void bdataApotekerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdataApotekerActionPerformed
        printDataApoteker();
    }//GEN-LAST:event_bdataApotekerActionPerformed

    private void bdataAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdataAdminActionPerformed
        printDataAdmin();
    }//GEN-LAST:event_bdataAdminActionPerformed

    private void bdataDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdataDokterActionPerformed
        printDataDokter();
    }//GEN-LAST:event_bdataDokterActionPerformed

    private void bdataJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdataJadwalActionPerformed
        printDataJadwal();
    }//GEN-LAST:event_bdataJadwalActionPerformed

    private void bkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkembaliActionPerformed
        dispose();   
        new Menu_apotek.Menu_utama().setVisible(true);               
    }//GEN-LAST:event_bkembaliActionPerformed

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
            java.util.logging.Logger.getLogger(form_cetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_cetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_cetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_cetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_cetakLaporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Icon_Konsultasi;
    private javax.swing.JLabel Icon_pesan;
    private javax.swing.JButton bdataAdmin;
    private javax.swing.JButton bdataApoteker;
    private javax.swing.JButton bdataDokter;
    private javax.swing.JButton bdataJadwal;
    private javax.swing.JButton bdataObat;
    private javax.swing.JButton bdataPasien;
    private javax.swing.JButton bkembali;
    private javax.swing.JButton bkonsul;
    private javax.swing.JButton bpemesananObat;
    private javax.swing.JLabel icon_apoteker;
    private javax.swing.JLabel icon_dataAdmin;
    private javax.swing.JLabel icon_dataObat;
    private javax.swing.JLabel icon_dataPasien;
    private javax.swing.JLabel icon_dokter;
    private javax.swing.JLabel icon_jadwalPraktek;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel text_Konsultasi;
    private javax.swing.JLabel text_dataAdmin;
    private javax.swing.JLabel text_dataApoteker;
    private javax.swing.JLabel text_dataDokter;
    private javax.swing.JLabel text_dataJadwal;
    private javax.swing.JLabel text_dataObat;
    private javax.swing.JLabel text_dataPasien;
    private javax.swing.JLabel text_pesanObat;
    // End of variables declaration//GEN-END:variables
}
