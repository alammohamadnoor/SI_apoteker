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
import PopUp.Data_Obat;
import PopUp.Data_Pasien;
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

public class form_pemesananObat extends javax.swing.JFrame {
    private Connection conn = new Koneksi().connect();
    public static String nama;
    private DefaultTableModel TabelPesanObat;
    String idAdmin, idObat, idApoteker, idPasien;
    int hargaSatuan, jumlahObat, Total;
    public String a1,a2,a3,b1,b2,c1,c2;
    //public int a6;
    public Data_Obat formPesanObat = null;
    public Data_Apoteker formPilihApoteker = null;
    public Data_Pasien formPilihPasien = null;
    
    //public pilih_peminjaman piljam = null;
  
    /**
     * Creates new form form_pemesananObat
     */
    public form_pemesananObat() {
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
    }
    
    protected void mati(){
        //cbidAdmin.setEnabled(false);
        //txtnmAdmin.setEnabled(false);
        txtidObat.setEnabled(false);
        txtnmObat.setEnabled(false);
        txthrgSatuan.setEnabled(false);
        txtidApoteker.setEnabled(false);
        txtnmApoteker.setEnabled(false);
        txtidPasien.setEnabled(false);
        txtnmPasien.setEnabled(false);
    }
    
    protected void kosong(){
        txtidApoteker.setText("");
        txtnmApoteker.setText("");
        txtidPasien.setText("");
        txtnmPasien.setText("");
        txtidObat.setText("");
        txtnmObat.setText("");
        txtjmlObat.setText("");
        txthrgSatuan.setText("");
        txthrgTotal.setText("");
    }

    protected void datatable(){
         Object [] Baris = {"Nomor Pemesanan","ID Admin","ID Apoteker","ID Pasien","ID Obat","Nama Obat","Jumlah Obat","Harga Satuan","Total"};
            TabelPesanObat = new DefaultTableModel(null,Baris);
            String cariitem = txtcari.getText();
            
            try{
                String sql = "SELECT * FROM tb_pemesananobat where nomor_pemesanan like '%"+cariitem+"%'";
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    TabelPesanObat.addRow(new Object [] {
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
                tbpesanObat.setModel(TabelPesanObat);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal Dipanggil"+e);
            }
    }

    public void auto() {
    txtnoPesan.setText("");
        try {
            String sql1 = "select max(right(nomor_pemesanan,4)) from tb_pemesananobat";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql1);
            while(hasil.next()) {
                if(hasil.first()==false) {
                    txtnoPesan.setText("PSO001");
                    
                } else {
                    hasil.last();
                    int code = hasil.getInt(1)+1;
                    String Nomor = String.valueOf(code);
                    int noLong = Nomor.length();
                    
                    for (int a=0; a<4-noLong;a++) {
                        Nomor = "0" + Nomor;
                    }
                    txtnoPesan.setText("PSO" + Nomor);
                    
                }
            }            
            } catch (Exception e) {
        }
                   txtnoPesan.setEnabled(false);
                   //txtangg.requestFocus();
    }

    protected void comboIDAdmin (){
       cbidAdmin.removeAllItems();
       cbidAdmin.addItem("Pilih...");
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
    
    public void pilihObat() {
    Data_Obat pilihObat = new Data_Obat();
         pilihObat.formPesanObat = this;
            txtidObat.setText(a1);
            txtnmObat.setText(a2);
            txthrgSatuan.setText(a3);
    }    
    
    public void pilihApoteker() {
    Data_Apoteker pilihApoteker = new Data_Apoteker();
         pilihApoteker.formPilihApoteker = this;
            txtidApoteker.setText(b1);
            txtnmApoteker.setText(b2);
    }      
    
    public void pilihPasien() {
    Data_Pasien pilihPasien = new Data_Pasien();
         pilihPasien.formPilihPasien = this;
            txtidPasien.setText(c1);
            txtnmPasien.setText(c2);
    }      
    
    public void hitungTotal (){
        int jumlahObat, hargaSatuan, total;
        
        jumlahObat = Integer.parseInt(txtjmlObat.getText());
        hargaSatuan = Integer.parseInt(txthrgSatuan.getText());
        
        total = jumlahObat * hargaSatuan;
        txthrgTotal.setText(Integer.toString(total));
    }    
    
    public void printbuk () {
        try {
            Locale locale = new Locale( "id", "ID" );
            Map<String, Object> parameter = new HashMap <String, Object>();
            parameter.put("nomor_pemesanan", txtnoPesan.getText());
            File file = new File ("src/Bukti_apotek/Bukti_pemesananObat.jrxml");
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
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtnmApoteker = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtnmPasien = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtidPasien = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txthrgSatuan = new javax.swing.JTextField();
        txthrgTotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        bcariApoteker = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbpesanObat = new javax.swing.JTable();
        bcetakBukti = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        bbatal = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bcari = new javax.swing.JButton();
        bkembali2 = new javax.swing.JButton();
        bhitung = new javax.swing.JButton();
        bcariObat = new javax.swing.JButton();
        bcariPasien = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtnoPesan = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtjmlObat = new javax.swing.JTextField();
        txtnmAdmin = new javax.swing.JTextField();
        txtnmObat = new javax.swing.JTextField();
        txtidObat = new javax.swing.JTextField();
        txtidApoteker = new javax.swing.JTextField();
        cbidAdmin = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("PEMESANAN OBAT");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 30));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel5.setText("ID Admin");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 20));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel6.setText("Nama Admin");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 20));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel7.setText("ID Apoteker");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, 20));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel8.setText("Nama Apoteker");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, 20));

        txtnmApoteker.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnmApoteker.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnmApoteker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmApotekerActionPerformed(evt);
            }
        });
        jPanel1.add(txtnmApoteker, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 280, 20));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel9.setText("ID Pasien");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, -1, 20));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel11.setText("Nama Pasien");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, -1, 20));

        txtnmPasien.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnmPasien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnmPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmPasienActionPerformed(evt);
            }
        });
        jPanel1.add(txtnmPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 280, 20));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel12.setText("ID Obat");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, 20));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel13.setText("Nama Obat");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, 20));

        txtidPasien.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtidPasien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtidPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidPasienActionPerformed(evt);
            }
        });
        jPanel1.add(txtidPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 160, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 560, 10));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel14.setText("Harga Satuan");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, -1, 20));

        txthrgSatuan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txthrgSatuan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txthrgSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthrgSatuanActionPerformed(evt);
            }
        });
        jPanel1.add(txthrgSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 150, 20));

        txthrgTotal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txthrgTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txthrgTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthrgTotalActionPerformed(evt);
            }
        });
        jPanel1.add(txthrgTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 150, 20));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel15.setText("Harga Total");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, -1, 20));

        bcariApoteker.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcariApoteker.setText("Cari");
        bcariApoteker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariApotekerActionPerformed(evt);
            }
        });
        jPanel1.add(bcariApoteker, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 80, 30));

        tbpesanObat.setModel(new javax.swing.table.DefaultTableModel(
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
        tbpesanObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpesanObatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbpesanObat);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 680, 370));

        bcetakBukti.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcetakBukti.setText("Bukti");
        bcetakBukti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcetakBuktiActionPerformed(evt);
            }
        });
        jPanel1.add(bcetakBukti, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 110, 80, 30));

        txtcari.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtcari.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        jPanel1.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 230, 20));

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel16.setText("Nomor Pemesanan");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, 160, 20));

        bbatal.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bbatal.setText("Batal");
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });
        jPanel1.add(bbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 490, 90, 30));

        bsimpan.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 90, 30));

        bubah.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bubah.setText("Ubah");
        bubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubahActionPerformed(evt);
            }
        });
        jPanel1.add(bubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 90, 30));

        bhapus.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        jPanel1.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 90, 30));

        bcari.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcari.setText("Cari");
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });
        jPanel1.add(bcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 110, 80, 30));

        bkembali2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bkembali2.setText("Kembali");
        bkembali2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkembali2ActionPerformed(evt);
            }
        });
        jPanel1.add(bkembali2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 30));

        bhitung.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bhitung.setText("Hitung");
        bhitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhitungActionPerformed(evt);
            }
        });
        jPanel1.add(bhitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 80, 30));

        bcariObat.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcariObat.setText("Cari");
        bcariObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariObatActionPerformed(evt);
            }
        });
        jPanel1.add(bcariObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 80, 30));

        bcariPasien.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bcariPasien.setText("Cari");
        bcariPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariPasienActionPerformed(evt);
            }
        });
        jPanel1.add(bcariPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 80, 30));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel17.setText("Nomor Pemesanan");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 20));

        txtnoPesan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnoPesan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnoPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnoPesanActionPerformed(evt);
            }
        });
        jPanel1.add(txtnoPesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 160, 20));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel18.setText("Jumlah Obat");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, 20));

        txtjmlObat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtjmlObat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtjmlObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjmlObatActionPerformed(evt);
            }
        });
        jPanel1.add(txtjmlObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 290, 20));

        txtnmAdmin.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnmAdmin.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnmAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmAdminActionPerformed(evt);
            }
        });
        jPanel1.add(txtnmAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 290, 20));

        txtnmObat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnmObat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtnmObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmObatActionPerformed(evt);
            }
        });
        jPanel1.add(txtnmObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 290, 20));

        txtidObat.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtidObat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtidObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidObatActionPerformed(evt);
            }
        });
        jPanel1.add(txtidObat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 160, 20));

        txtidApoteker.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtidApoteker.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtidApoteker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidApotekerActionPerformed(evt);
            }
        });
        jPanel1.add(txtidApoteker, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 160, 20));

        cbidAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbidAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 160, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 540));

        setSize(new java.awt.Dimension(1365, 580));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtnmApotekerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmApotekerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmApotekerActionPerformed

    private void txtnmPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmPasienActionPerformed

    private void txtidPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidPasienActionPerformed

    private void txthrgSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthrgSatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthrgSatuanActionPerformed

    private void txthrgTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthrgTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthrgTotalActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void bkembali2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkembali2ActionPerformed
        dispose();
        new Menu_apotek.Menu_utama().setVisible(true);
    }//GEN-LAST:event_bkembali2ActionPerformed

    private void txtnoPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnoPesanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnoPesanActionPerformed

    private void txtjmlObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjmlObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlObatActionPerformed

    private void bcariObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariObatActionPerformed
        Data_Obat dabat =new Data_Obat();
        dabat.formPesanObat=this;
        dabat.setVisible(true);
        dabat.setResizable(false);           // TODO add your handling code here:
    }//GEN-LAST:event_bcariObatActionPerformed

    private void bcariApotekerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariApotekerActionPerformed
        Data_Apoteker datek =new Data_Apoteker();
        datek.formPilihApoteker=this;
        datek.setVisible(true);
        datek.setResizable(false);          // TODO add your handling code here:
    }//GEN-LAST:event_bcariApotekerActionPerformed

    private void txtnmAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmAdminActionPerformed

    private void txtnmObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmObatActionPerformed

    private void txtidObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidObatActionPerformed

    private void txtidApotekerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidApotekerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidApotekerActionPerformed

    private void bcariPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariPasienActionPerformed
        Data_Pasien dapa =new Data_Pasien();
        dapa.formPilihPasien=this;
        dapa.setVisible(true);
        dapa.setResizable(false);         // TODO add your handling code here:
    }//GEN-LAST:event_bcariPasienActionPerformed

    private void bhitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhitungActionPerformed
        hitungTotal();
    }//GEN-LAST:event_bhitungActionPerformed

    private void tbpesanObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpesanObatMouseClicked
        int bar = tbpesanObat.getSelectedRow();
        String a = tbpesanObat.getValueAt(bar, 0). toString();
        String b = tbpesanObat.getValueAt(bar, 1). toString();
        String c = tbpesanObat.getValueAt(bar, 2). toString();
        String d = tbpesanObat.getValueAt(bar, 3). toString();
        String e = tbpesanObat.getValueAt(bar, 4). toString();
        String f = tbpesanObat.getValueAt(bar, 5). toString();
        String g = tbpesanObat.getValueAt(bar, 6). toString();
        String h = tbpesanObat.getValueAt(bar, 7). toString();
        //String f = tbmember.getValueAt(bar, 5). toString();
        
        
        txtnoPesan.setText(a);
        cbidAdmin.setSelectedItem(b);
        txtidApoteker.setText(c);
        txtidPasien.setText(d);
        txtidObat.setText(e);
        txtjmlObat.setText(f);         // TODO add your handling code here:
        txthrgSatuan.setText(g);         // TODO add your handling code here:
        txthrgTotal.setText(h);         // TODO add your handling code here:
    }//GEN-LAST:event_tbpesanObatMouseClicked

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        jumlahObat=Integer.parseInt (txtjmlObat.getText());
        hargaSatuan=Integer.parseInt (txthrgSatuan.getText());
        Total=Integer.parseInt (txthrgTotal.getText());        
        String sql = "insert into tb_pemesananobat values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnoPesan.getText());
            stat.setString(2, cbidAdmin.getItemAt(cbidAdmin.getSelectedIndex()));
            stat.setString(3, txtidApoteker.getText());
            stat.setString(4, txtidPasien.getText());
            stat.setString(5, txtidObat.getText());
            stat.setInt(6, jumlahObat);
            stat.setInt(7, hargaSatuan);
            stat.setInt(8, Total);
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil Disimpan");
            kosong();
            //txtidPasien.requestFocus();
            auto();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan"+e);
        }
        datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        jumlahObat=Integer.parseInt (txtjmlObat.getText());
        try {
            String sql = "update tb_pemesananobat set id_obat=?, jumlah_obat=? where nomor_pemesanan = '"+txtnoPesan.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtidObat.getText());
            stat.setInt(2, jumlahObat);
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil Diubah");
            kosong();
            //txtidPasien.requestFocus();
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
            String sql = "delete from tb_pemesananobat where nomor_pemesanan = '"+txtnoPesan.getText()+"'";
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

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_bcariActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        kosong();
        datatable();
        auto();        // TODO add your handling code here:
    }//GEN-LAST:event_bbatalActionPerformed

    private void bcetakBuktiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcetakBuktiActionPerformed
        printbuk();        // TODO add your handling code here:
    }//GEN-LAST:event_bcetakBuktiActionPerformed

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
            java.util.logging.Logger.getLogger(form_pemesananObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_pemesananObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_pemesananObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_pemesananObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_pemesananObat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bcariApoteker;
    private javax.swing.JButton bcariObat;
    private javax.swing.JButton bcariPasien;
    private javax.swing.JButton bcetakBukti;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bhitung;
    private javax.swing.JButton bkembali2;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JComboBox<String> cbidAdmin;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbpesanObat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txthrgSatuan;
    private javax.swing.JTextField txthrgTotal;
    private javax.swing.JTextField txtidApoteker;
    private javax.swing.JTextField txtidObat;
    private javax.swing.JTextField txtidPasien;
    private javax.swing.JTextField txtjmlObat;
    private javax.swing.JTextField txtnmAdmin;
    private javax.swing.JTextField txtnmApoteker;
    private javax.swing.JTextField txtnmObat;
    private javax.swing.JTextField txtnmPasien;
    private javax.swing.JTextField txtnoPesan;
    // End of variables declaration//GEN-END:variables
}
