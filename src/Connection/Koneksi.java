/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Koneksi {
    private Connection koneksi;
    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Terkoneksi");

        } catch (ClassNotFoundException ex) {
            System.out.println("Gagal Koneksi");
        }
        String url = "jdbc:mysql://localhost/db_apoteker";
        try {
            koneksi = DriverManager.getConnection(url, "root", "");
            System.out.println("Berhasil Koneksi Database");
        } catch (SQLException ex) {
            System.out.println("Gagal Koneksi Database" + ex);
        }
        return koneksi;
    }
}