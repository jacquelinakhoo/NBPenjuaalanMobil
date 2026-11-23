/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package penjualanmobil;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class MenuDataMobil extends javax.swing.JFrame {

    private DefaultTableModel model = null;
    private PreparedStatement stat;
    private ResultSet rs;
    Koneksi k = new Koneksi();

    Color vanillaCream = new Color(255, 247, 230);
    Color midnightLagoon = new Color(45, 58, 71);
    Color rosewood = new Color(180, 106, 114);
    Color sageLeaf = new Color(168, 181, 138);
    Color ubahBlue = new Color(160, 200, 255);
    Color lightGrey = new Color(230, 230, 230);

    Font titleFont = new Font("Goudy Old Style", Font.BOLD, 24);
    Font labelFont = new Font("Goudy Old Style", Font.PLAIN, 16);
    Font inputFont = new Font("Bookman Old Style", Font.PLAIN, 14);
    Font buttonFont = new Font("Bookman Old Style", Font.BOLD, 12);

    public MenuDataMobil() {
        initComponents();
        applyCustomStyles();
        k.connect();
        Tampiltabel();

        // Action Listeners added here so they don't break the Design tab
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });

        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        tblmobil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmobilMouseClicked(evt);
            }
        });
    }

    class mobil {
        String kode, merk, type, warna;
        int harga;

        public mobil() {
            this.kode = textkode.getText();
            this.merk = textmerk.getText();
            this.type = texttype.getText();
            this.warna = textwarna.getText();
            this.harga = Integer.parseInt(textharga.getText());
        }
    }

    public void Tampiltabel() {
        model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("Merk");
        model.addColumn("Type");
        model.addColumn("Warna");
        model.addColumn("Harga");
        tblmobil.setModel(model);

        try {
            this.stat = k.getCon().prepareStatement(
                    "select KodeMobil, Merk, Type, Warna, Harga from mobil");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                Object[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
                };
                model.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        textkode.setText("");
        textmerk.setText("");
        texttype.setText("");
        textwarna.setText("");
        textharga.setText("");
    }

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            mobil m = new mobil();
            this.stat = k.getCon().prepareStatement(
                    "insert into mobil (KodeMobil, Merk, Type, Warna, Harga) values(?,?,?,?,?)");
            stat.setString(1, m.kode);
            stat.setString(2, m.merk);
            stat.setString(3, m.type);
            stat.setString(4, m.warna);
            stat.setInt(5, m.harga);
            stat.executeUpdate();
            Tampiltabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void tblmobilMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblmobil.getSelectedRow();
        if (row == -1) {
            return;
        }
        textkode.setText(model.getValueAt(row, 0).toString());
        textmerk.setText(model.getValueAt(row, 1).toString());
        texttype.setText(model.getValueAt(row, 2).toString());
        textwarna.setText(model.getValueAt(row, 3).toString());
        textharga.setText(model.getValueAt(row, 4).toString());
    }

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            mobil m = new mobil();
            this.stat = k.getCon().prepareStatement(
                    "update mobil set Merk=?, Type=?, Warna=?, Harga=? where KodeMobil=?");
            stat.setString(1, m.merk);
            stat.setString(2, m.type);
            stat.setString(3, m.warna);
            stat.setInt(4, m.harga);
            stat.setString(5, textkode.getText());
            stat.executeUpdate();
            Tampiltabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            this.stat = k.getCon().prepareStatement("delete from mobil where KodeMobil=?");
            stat.setString(1, textkode.getText());
            stat.executeUpdate();
            Tampiltabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {
        new MenuUtama().setVisible(true);
        this.dispose();
    }

    private void applyCustomStyles() {
        getContentPane().setBackground(vanillaCream);

        jLabel1.setFont(titleFont);
        jLabel1.setForeground(rosewood);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.JLabel[] labels = {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6};
        for (javax.swing.JLabel lbl : labels) {
            lbl.setFont(labelFont);
            lbl.setForeground(midnightLagoon);
        }

        javax.swing.JTextField[] fields = {textkode, textmerk, texttype, textwarna, textharga};
        for (javax.swing.JTextField txt : fields) {
            txt.setFont(inputFont);
            txt.setForeground(midnightLagoon);
            txt.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
        }

        styleButton(btntambah, sageLeaf, Color.WHITE);
        styleButton(btnubah, ubahBlue, Color.WHITE);
        styleButton(btnhapus, rosewood, Color.WHITE);

        btnkembali.setFont(labelFont);
        btnkembali.setForeground(midnightLagoon);
        btnkembali.setBackground(lightGrey);
        btnkembali.setOpaque(true);
        btnkembali.setContentAreaFilled(true);
        btnkembali.setBorderPainted(false);
        btnkembali.setFocusPainted(false);
        btnkembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnkembali.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        tblmobil.setFont(inputFont);
        tblmobil.setRowHeight(30);
        tblmobil.setGridColor(new Color(230, 230, 230));
        tblmobil.getTableHeader().setFont(labelFont);
        tblmobil.getTableHeader().setBackground(lightGrey);
        tblmobil.getTableHeader().setForeground(midnightLagoon);
        jScrollPane1.getViewport().setBackground(vanillaCream);

        javax.swing.table.DefaultTableCellRenderer headerRenderer
                = (javax.swing.table.DefaultTableCellRenderer) tblmobil.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
    }

    private void styleButton(javax.swing.JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(buttonFont);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        btnkembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textkode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textmerk = new javax.swing.JTextField();
        texttype = new javax.swing.JTextField();
        textwarna = new javax.swing.JTextField();
        textharga = new javax.swing.JTextField();
        btnubah = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmobil = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnkembali.setText("Kembali");
        jLabel1.setText("Halaman Data Mobil");
        jLabel2.setText("Kode Mobil:");
        jLabel3.setText("Merk Mobil:");
        jLabel4.setText("Type Mobil:");
        jLabel5.setText("Warna Mobil:");
        jLabel6.setText("Harga Mobil:");

        btnubah.setText("Ubah");
        btntambah.setText("Tambah");
        btnhapus.setText("Hapus");

        tblmobil.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
                },
                new String[]{
                    "Kode Mobil", "Merk", "Tipe", "Warna", "Harga"
                }
        ));
        jScrollPane1.setViewportView(tblmobil);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnkembali)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(58, 58, 58))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(42, 42, 42)
                                                                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel4)
                                                                                .addComponent(jLabel3)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(jLabel5)
                                                                                .addComponent(jLabel6))
                                                                        .addGap(28, 28, 28)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(textwarna, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(texttype, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(textmerk, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(textkode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                                                                .addComponent(textharga)))))))
                                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnkembali)
                                                .addGap(39, 39, 39))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(textkode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(textmerk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(texttype, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(textwarna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(textharga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuDataMobil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkembali;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton btnubah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblmobil;
    private javax.swing.JTextField textharga;
    private javax.swing.JTextField textkode;
    private javax.swing.JTextField textmerk;
    private javax.swing.JTextField texttype;
    private javax.swing.JTextField textwarna;
    // End of variables declaration                   
}