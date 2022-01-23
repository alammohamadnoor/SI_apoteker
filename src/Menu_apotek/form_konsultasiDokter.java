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

public class form_konsultasiDokter extends javax.swing.JFrame {
    private Connection conn = new Koneksi().connect();
    public static String nama;
    private DefaultTableModel TabelKonsultasi;
    String idAdmin, idDokter, idPasien;
    int biayaKonsul;
    public String a1,a2,a3,b1,b2;
    //public int a6;
    //public Data_Obat formPesanObat = null;
    public Data_Dokter formPilihDokter = null;
    public Data_Pasien1 formPilihPasienn = null;
    /**
     * Creates new form form_konsultasiDokter
     */
    public form_konsultasiDokter() {
        initComponents();
        txtnmAdmin.setText(Registrasi_Admin.getnama());
        String KD = UserID.getUserLogin();
        txtnmAdmin.setText(KD);        
        datatable();
        kosong();
        //aktif();        
        auto();   
        cbidAdmin.setSelectedItem(Login_apotek.getusername());   
        mati();
        comboIDAdmin();        
        comboJadwalPraktek();        
    }
    
    protected void kosong(){
        txtidDokter.setText("");
        txtnmDokter.setText("");
        txtidPasien.setText("");
        txtnmPasien.setText("");
        cbjadwalPraktek.setSelectedItem(null);
        txtkeluhan.setText("");
        txtresepObat.setText("");
        txtbiayaKonsul.setText("");
    }

    protected void mati(){
        cbidAdmin.setEnabled(false);
        txtnmAdmin.setEnabled(false);
        txtidDokter.setEnabled(false);
        txtnmDokter.setEnabled(false);
        cbjadwalPraktek.setEnabled(false);
        txtidPasien.setEnabled(false);
        txtnmPasien.setEnabled(false);

    }
    
    protected void datatable(){
         Object [] Baris = {"Nomor Konsultasi","ID Admin","ID Dokter","Jadwal Praktek","ID Pasien","Keluhan","Resep Obat","Biaya Konsul"};
            TabelKonsultasi = new DefaultTableModel(null,Baris);
            String cariitem = txtcari.getText();
            
            try{
                String sql = "SELECT * FROM tb_konsul where nomor_konsul like '%"+cariitem+"%'";
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    TabelKonsultasi.addRow(new Object [] {
                        hasil.getString(1),
                        hasil.getString(2),
                        hasil.getString(3),
                        hasil.getString(4),
                        hasil.getString(5),
                        hasil.getString(6),
                        hasil.getString(7),
                        hasil.getString(8)
                    });
                }
                tbkonsul.setModel(TabelKonsultasi);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal Dipanggil"+e);
            }
    }

    public void auto() {
    txtnoKonsul.setText("");
        try {
            String sql1 = "select max(right(nomor_konsul,4)) from tb_konsul";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()) {
                if(hasil.first()==false) {
                    txtnoKonsul.setText("KNS001");
                    
                } else {
                    hasil.last();
                    int code = hasil.getInt(1)+1;
                    String Nomor = String.valueOf(code);
                    int noLong = Nomor.length();
                    
                    for (int a=0; a<4-noLong;a++) {
                        Nomor = "0" + Nomor;
                    }
                    txtnoKonsul.setText("KNS" + Nomor);
                    
                }
            }            
            } catch (Exception e) {
        }
                   txtnoKonsul.setEnabled(false);
                   //txtangg.requestFocus();
    }

    protected void comboIDAdmin (){
    //   cbidangg.removeAllItems();
    //   cbidangg.addItem("Pilih...");
       try {
          String sql ="select * from tb_admin";
          Statement st=conn.createStatement();
          ResultSet ComboAdmin = st.executeQuery(sql);
           while (ComboAdmin.next()) {
               String idAdmin =ComboAdmin.getString("username");
               cbidAdmin.addItem(idAdmin);
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Gagal Menampilkan Id Admin \n" +e.getMessage());
       }
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
    
    public void pilihDokter() {
    Data_Dokter pilihDokter = new Data_Dokter();
         pilihDokter.formPilihDokter = this;
            txtidDokter.setText(a1);
            txtnmDokter.setText(a2);
            cbjadwalPraktek.setSelectedItem(a3);
    }      
    
    public void pilihPasienn() {
    Data_Pasien1 pilihPasienn = new Data_Pasien1();
         pilihPasienn.formPilihPasienn = this;
            txtidPasien.setText(b1);
            txtnmPasien.setText(b2);
    }      
    
    public void printbuk () {
        try {
            Locale locale = new Locale( "id", "ID" );
            Map<String, Object> parameter = new HashMap <String, Object>();
            parameter.put("nomor_konsul", txtnoKonsul.getText());
            File file = new File ("src/Bukti_apotek/Bukti_dataKonsultasi.jrxml");
            //class.forName("com.mysql.jdbc.Driver");
            //Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/db_perpus","root","");
            parameter.put(JRParameter.REPORT_LOCALE, locale);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperViewer.viewReport(jasperPrint,false);    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat Dicetak"+ex.getMessage(),"Cetak Data", JOptionPane.ERROR_MESSAGE);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtidPasien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtnmAdmin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtnmDokter = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbjadwalPraktek = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtnmPasien = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtbiayaKonsul = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbkonsul = new javax.swing.JTable();
        bbatal = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        bcari = new javax.swing.JButton();
        bcetakBukti = new javax.swing.JButton();
        bkembali = new javax.swing.JButton();
        bcariPasien = new javax.swing.JButton();
        bcariDokter = new javax.swing.JButton();
        txtidDokter = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtnoKonsul = new javax.swing.JTextField();
        cbidAdmin = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtkeluhan = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtresepObat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("KONSULTASI");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 30));

        txtidPasien.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtidPasien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtidPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidPasienActionPerformed(evt);
            }
        });
        jPanel1.add(txtidPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 160, 20));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel6.setText("Nama Admin");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 20));

        txtnmAdmin.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnmAdmin.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnmAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmAdminActionPerformed(evt);
            }
        });
        jPanel1.add(txtnmAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 260, 20));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel7.setText("ID Dokter");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, 20));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel9.setText("Nama Dokter");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, 20));

        txtnmDokter.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnmDokter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnmDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmDokterActionPerformed(evt);
            }
        });
        jPanel1.add(txtnmDokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 260, 20));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel10.setText("Jadwal Praktek");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 20));

        cbjadwalPraktek.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbjadwalPraktek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjadwalPraktekActionPerformed(evt);
            }
        });
        jPanel1.add(cbjadwalPraktek, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 260, -1));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel11.setText("ID Admin");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 20));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel12.setText("ID Pasien");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, -1, 20));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel13.setText("Keluhan");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, 20));

        txtnmPasien.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnmPasien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnmPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmPasienActionPerformed(evt);
            }
        });
        jPanel1.add(txtnmPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 260, 20));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel14.setText("Nama Pasien");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 570, 10));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel15.setText("Resep Obat");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, 20));

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel16.setText("Biaya Konsul");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, -1, 20));

        txtbiayaKonsul.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtbiayaKonsul.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtbiayaKonsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbiayaKonsulActionPerformed(evt);
            }
        });
        jPanel1.add(txtbiayaKonsul, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 260, 20));

        tbkonsul.setModel(new javax.swing.table.DefaultTableModel(
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
        tbkonsul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkonsulMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbkonsul);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 600, 480));

        bbatal.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bbatal.setText("Batal");
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });
        jPanel1.add(bbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 590, 90, 30));

        bsimpan.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 590, 90, 30));

        bubah.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bubah.setText("Ubah");
        bubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubahActionPerformed(evt);
            }
        });
        jPanel1.add(bubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 590, 90, 30));

        bhapus.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        jPanel1.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 590, 90, 30));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel17.setText("Nomor Konsultasi");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, -1, 20));

        txtcari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtcari.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        jPanel1.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 230, 20));

        bcari.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcari.setText("Cari");
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });
        jPanel1.add(bcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 100, 80, 30));

        bcetakBukti.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcetakBukti.setText("Bukti");
        bcetakBukti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcetakBuktiActionPerformed(evt);
            }
        });
        jPanel1.add(bcetakBukti, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 100, 80, 30));

        bkembali.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bkembali.setText("Kembali");
        bkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkembaliActionPerformed(evt);
            }
        });
        jPanel1.add(bkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 30));

        bcariPasien.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcariPasien.setText("Cari");
        bcariPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariPasienActionPerformed(evt);
            }
        });
        jPanel1.add(bcariPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 80, 30));

        bcariDokter.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcariDokter.setText("Cari");
        bcariDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariDokterActionPerformed(evt);
            }
        });
        jPanel1.add(bcariDokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 80, 30));

        txtidDokter.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtidDokter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtidDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidDokterActionPerformed(evt);
            }
        });
        jPanel1.add(txtidDokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 160, 20));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel18.setText("Nomor Konsultasi");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 150, 20));

        txtnoKonsul.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnoKonsul.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnoKonsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnoKonsulActionPerformed(evt);
            }
        });
        jPanel1.add(txtnoKonsul, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 160, 20));

        cbidAdmin.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbidAdmin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbidAdminItemStateChanged(evt);
            }
        });
        cbidAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbidAdminActionPerformed(evt);
            }
        });
        jPanel1.add(cbidAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 160, -1));

        txtkeluhan.setColumns(20);
        txtkeluhan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtkeluhan.setRows(5);
        jScrollPane4.setViewportView(txtkeluhan);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 260, 120));

        txtresepObat.setColumns(20);
        txtresepObat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtresepObat.setRows(5);
        jScrollPane1.setViewportView(txtresepObat);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 260, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 640));

        setSize(new java.awt.Dimension(1246, 680));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtidPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidPasienActionPerformed

    private void txtnmAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmAdminActionPerformed

    private void txtnmDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmDokterActionPerformed

    private void cbjadwalPraktekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjadwalPraktekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbjadwalPraktekActionPerformed

    private void txtnmPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmPasienActionPerformed

    private void txtbiayaKonsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbiayaKonsulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbiayaKonsulActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void bkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkembaliActionPerformed
        dispose();
        new Menu_apotek.Menu_utama().setVisible(true);
    }//GEN-LAST:event_bkembaliActionPerformed

    private void bcariPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariPasienActionPerformed
        Data_Pasien1 dabatt =new Data_Pasien1();
        dabatt.formPilihPasienn=this;
        dabatt.setVisible(true);
        dabatt.setResizable(false);           // TODO add your handling code here:
    }//GEN-LAST:event_bcariPasienActionPerformed

    private void bcariDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariDokterActionPerformed
        Data_Dokter dadok =new Data_Dokter();
        dadok.formPilihDokter=this;
        dadok.setVisible(true);
        dadok.setResizable(false);         // TODO add your handling code here:
    }//GEN-LAST:event_bcariDokterActionPerformed

    private void txtidDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidDokterActionPerformed

    private void txtnoKonsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnoKonsulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnoKonsulActionPerformed

    private void cbidAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbidAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbidAdminActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        //jumlahObat=Integer.parseInt (txtjmlObat.getText());
        //hargaSatuan=Integer.parseInt (txthrgSatuan.getText());
        biayaKonsul=Integer.parseInt (txtbiayaKonsul.getText());        
        String sql = "insert into tb_konsul values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnoKonsul.getText());
            stat.setString(2, cbidAdmin.getItemAt(cbidAdmin.getSelectedIndex()));
            stat.setString(3, txtidDokter.getText());
            stat.setString(4, cbjadwalPraktek.getItemAt(cbjadwalPraktek.getSelectedIndex()));            
            stat.setString(5, txtidPasien.getText());
            stat.setString(6, txtkeluhan.getText());
            stat.setString(7, txtresepObat.getText());
            stat.setInt(8, biayaKonsul);
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil Disimpan");
            kosong();
            //txtidPasien.requestFocus();
            auto();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan"+e);
        }
        datatable();
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        biayaKonsul=Integer.parseInt (txtbiayaKonsul.getText()); 
        try {
            String sql = "update tb_konsul set id_dokter=?, jadwal_praktek=?, id_pasien=?, keluhan=?, resep_obat=?, biaya_konsul=?  where nomor_konsul = '"+txtnoKonsul.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtidDokter.getText());
            stat.setString(2, cbjadwalPraktek.getItemAt(cbjadwalPraktek.getSelectedIndex()));            
            stat.setString(3, txtidPasien.getText());
            stat.setString(4, txtkeluhan.getText());
            stat.setString(5, txtresepObat.getText());
            stat.setInt(6, biayaKonsul);
            //stat.setString(6, txtjmlObat.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil Diubah");
            kosong();
            //txtidPasien.requestFocus();
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
            String sql = "delete from tb_konsul where nomor_konsul = '"+txtnoKonsul.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                //txtnmPasien.requestFocus();
               auto ();
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

    private void tbkonsulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkonsulMouseClicked
        int bar = tbkonsul.getSelectedRow();
        String a = tbkonsul.getValueAt(bar, 0). toString();
        String b = tbkonsul.getValueAt(bar, 1). toString();
        String c = tbkonsul.getValueAt(bar, 2). toString();
        String d = tbkonsul.getValueAt(bar, 3). toString();
        String e = tbkonsul.getValueAt(bar, 4). toString();
        String f = tbkonsul.getValueAt(bar, 5). toString();
        String g = tbkonsul.getValueAt(bar, 6). toString();
        String h = tbkonsul.getValueAt(bar, 7). toString();
        //String f = tbmember.getValueAt(bar, 5). toString();
        
        
        txtnoKonsul.setText(a);
        cbidAdmin.setSelectedItem(b);
        txtidDokter.setText(c);
        cbjadwalPraktek.setSelectedItem(d);
        txtidPasien.setText(e);
        txtkeluhan.setText(f);
        txtresepObat.setText(g);         // TODO add your handling code here:
        txtbiayaKonsul.setText(h);        // TODO add your handling code here:
    }//GEN-LAST:event_tbkonsulMouseClicked

    private void bcetakBuktiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcetakBuktiActionPerformed
        printbuk();
    }//GEN-LAST:event_bcetakBuktiActionPerformed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_bcariActionPerformed

    private void cbidAdminItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbidAdminItemStateChanged
        try {
            String sql = "select * from tb_admin where username='"+ cbidAdmin.getSelectedItem()+"'";
            Statement st=conn.createStatement();
            ResultSet rsidAdmin = st.executeQuery(sql);
            while (rsidAdmin.next()) {
                txtnmAdmin.setText(rsidAdmin.getString(1));
            }
        } catch (Exception e) {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cbidAdminItemStateChanged

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
            java.util.logging.Logger.getLogger(form_konsultasiDokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_konsultasiDokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_konsultasiDokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_konsultasiDokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_konsultasiDokter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bcariDokter;
    private javax.swing.JButton bcariPasien;
    private javax.swing.JButton bcetakBukti;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkembali;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JComboBox<String> cbidAdmin;
    private javax.swing.JComboBox<String> cbjadwalPraktek;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbkonsul;
    private javax.swing.JTextField txtbiayaKonsul;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtidDokter;
    private javax.swing.JTextField txtidPasien;
    private javax.swing.JTextArea txtkeluhan;
    private javax.swing.JTextField txtnmAdmin;
    private javax.swing.JTextField txtnmDokter;
    private javax.swing.JTextField txtnmPasien;
    private javax.swing.JTextField txtnoKonsul;
    private javax.swing.JTextArea txtresepObat;
    // End of variables declaration//GEN-END:variables
}
