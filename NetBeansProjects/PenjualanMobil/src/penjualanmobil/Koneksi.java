package penjualanmobil;

import java.sql.*;
import javax.swing.JOptionPane;

public class Koneksi {

    private final String url = "jdbc:mysql://localhost/penjualan_mobil";
    private final String username = "root";
    private final String password = "";
    private Connection con;

    public void connect() {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Koneksi Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public Connection getCon() {
        return con;
    }
}