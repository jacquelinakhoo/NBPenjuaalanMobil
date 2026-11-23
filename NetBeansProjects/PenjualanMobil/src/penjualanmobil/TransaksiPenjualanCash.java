package penjualanmobil;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TransaksiPenjualanCash extends javax.swing.JFrame {

    private static final String TABEL = "belicash";
    private static final String COL_KODE = "KodeCash";
    private static final String COL_KTP = "KTP";
    private static final String COL_MOBIL = "KodeMobil";
    private static final String COL_TANGGAL = "CashTgl";
    private static final String COL_BAYAR = "CashBayar";

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

    public TransaksiPenjualanCash() {
        initComponents();
        applyCustomStyles();
        k.connect();
        ComboPembeli();
        ComboMobil();
        Tampiltabel();

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

        tblcash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcashMouseClicked(evt);
            }
        });
    }

    class cash {
        String kode, ktp, kodemobil, tanggal;
        int bayar;

        public cash() {
            this.kode = textkodecash.getText();

            Object itemKtp = cbKtp.getSelectedItem();
            if (itemKtp == null) {
                throw new RuntimeException("Pilih KTP pembeli dulu");
            }
            this.ktp = itemKtp.toString().split(":")[0];

            Object itemMobil = cbMobil.getSelectedItem();
            if (itemMobil == null) {
                throw new RuntimeException("Pilih kode mobil dulu");
            }
            this.kodemobil = itemMobil.toString().split(":")[0];

            Date date = dateTanggal.getDate();
            if (date == null) {
                throw new RuntimeException("Pilih tanggal dulu");
            }
            this.tanggal = new SimpleDateFormat("yyyy-MM-dd").format(date);

            this.bayar = Integer.parseInt(textbayar.getText());
        }
    }

    public void ComboPembeli() {
        cbKtp.removeAllItems();
        try {
            this.stat = k.getCon().prepareStatement("select KTP, NamaPembeli from pembeli");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                cbKtp.addItem(rs.getString("KTP") + ":" + rs.getString("NamaPembeli"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ComboMobil() {
        cbMobil.removeAllItems();
        try {
            this.stat = k.getCon().prepareStatement(
                    "select KodeMobil, Merk, Type, Warna, Harga from mobil");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                cbMobil.addItem(rs.getString("KodeMobil") + ":"
                        + rs.getString("Merk") + ":"
                        + rs.getString("Type") + ":"
                        + rs.getString("Warna") + ":"
                        + rs.getInt("Harga"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void Tampiltabel() {
        model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("KTP");
        model.addColumn("Kode Mobil");
        model.addColumn("Tanggal");
        model.addColumn("Total Bayar");
        tblcash.setModel(model);

        try {
            this.stat = k.getCon().prepareStatement(
                    "select " + COL_KODE + ", " + COL_KTP + ", " + COL_MOBIL + ", "
                    + COL_TANGGAL + ", " + COL_BAYAR + " from " + TABEL);
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
        textkodecash.setText("");
        textbayar.setText("");
        dateTanggal.setDate(null);
    }

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            cash c = new cash();
            this.stat = k.getCon().prepareStatement(
                    "insert into " + TABEL + " (" + COL_KODE + ", " + COL_KTP + ", "
                    + COL_MOBIL + ", " + COL_TANGGAL + ", " + COL_BAYAR + ") values(?,?,?,?,?)");
            stat.setString(1, c.kode);
            stat.setString(2, c.ktp);
            stat.setString(3, c.kodemobil);
            stat.setString(4, c.tanggal);
            stat.setInt(5, c.bayar);
            stat.executeUpdate();
            Tampiltabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void tblcashMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblcash.getSelectedRow();
        if (row == -1) {
            return;
        }
        textkodecash.setText(model.getValueAt(row, 0).toString());

        String ktp = model.getValueAt(row, 1).toString();
        for (int i = 0; i < cbKtp.getItemCount(); i++) {
            if (cbKtp.getItemAt(i).startsWith(ktp + ":")) {
                cbKtp.setSelectedIndex(i);
                break;
            }
        }

        String kodemobil = model.getValueAt(row, 2).toString();
        for (int i = 0; i < cbMobil.getItemCount(); i++) {
            if (cbMobil.getItemAt(i).startsWith(kodemobil + ":")) {
                cbMobil.setSelectedIndex(i);
                break;
            }
        }

        try {
            String tanggal = model.getValueAt(row, 3).toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(tanggal);
            dateTanggal.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        textbayar.setText(model.getValueAt(row, 4).toString());
    }

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            cash c = new cash();
            this.stat = k.getCon().prepareStatement(
                    "update " + TABEL + " set " + COL_KTP + "=?, " + COL_MOBIL + "=?, "
                    + COL_TANGGAL + "=?, " + COL_BAYAR + "=? where " + COL_KODE + "=?");
            stat.setString(1, c.ktp);
            stat.setString(2, c.kodemobil);
            stat.setString(3, c.tanggal);
            stat.setInt(4, c.bayar);
            stat.setString(5, c.kode);
            stat.executeUpdate();
            Tampiltabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            this.stat = k.getCon().prepareStatement(
                    "delete from " + TABEL + " where " + COL_KODE + "=?");
            stat.setString(1, textkodecash.getText());
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

        javax.swing.JTextField[] fields = {textkodecash, textbayar};
        for (javax.swing.JTextField txt : fields) {
            txt.setFont(inputFont);
            txt.setForeground(midnightLagoon);
            txt.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
        }

        cbKtp.setFont(inputFont);
        cbMobil.setFont(inputFont);

        dateTanggal.setFont(inputFont);
        dateTanggal.getCalendarButton().setBackground(lightGrey);
        dateTanggal.getCalendarButton().setFont(inputFont);

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

        tblcash.setFont(inputFont);
        tblcash.setRowHeight(30);
        tblcash.setGridColor(new Color(230, 230, 230));
        tblcash.getTableHeader().setFont(labelFont);
        tblcash.getTableHeader().setBackground(lightGrey);
        tblcash.getTableHeader().setForeground(midnightLagoon);
        jScrollPane1.getViewport().setBackground(vanillaCream);

        javax.swing.table.DefaultTableCellRenderer headerRenderer
                = (javax.swing.table.DefaultTableCellRenderer) tblcash.getTableHeader().getDefaultRenderer();
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
    private void initComponents() {
        btnkembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textkodecash = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbKtp = new javax.swing.JComboBox<>();
        cbMobil = new javax.swing.JComboBox<>();
        dateTanggal = new com.toedter.calendar.JDateChooser();
        textbayar = new javax.swing.JTextField();
        btnubah = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcash = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        btnkembali.setText("Kembali");
        jLabel1.setText("Transaksi Penjualan Cash");
        jLabel2.setText("Kode Cash:");
        jLabel3.setText("KTP Pembeli:");
        jLabel4.setText("Kode Mobil:");
        jLabel5.setText("Tanggal Beli:");
        jLabel6.setText("Bayar (RP):");

        cbKtp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2"}));
        cbMobil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2"}));

        btnubah.setText("Ubah");
        btntambah.setText("Tambah");
        btnhapus.setText("Hapus");

        tblcash.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{{null, null, null, null}, {null, null, null, null}},
                new String[]{"Title 1", "Title 2", "Title 3", "Title 4"}
        ));
        jScrollPane1.setViewportView(tblcash);

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
                                                                                .addComponent(jLabel3).addComponent(jLabel2).addComponent(jLabel4).addComponent(jLabel5).addComponent(jLabel6))
                                                                        .addGap(28, 28, 28)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(cbKtp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(cbMobil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(dateTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                                                                .addComponent(textkodecash, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(textbayar, javax.swing.GroupLayout.Alignment.LEADING)))))))
                                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup().addComponent(btnkembali).addGap(39, 39, 39))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2).addComponent(textkodecash, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3).addComponent(cbKtp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4).addComponent(cbMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5).addComponent(dateTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6).addComponent(textbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    }

    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {
        }
        java.awt.EventQueue.invokeLater(() -> new TransaksiPenjualanCash().setVisible(true));
    }

    private javax.swing.JButton btnhapus, btnkembali, btntambah, btnubah;
    private javax.swing.JComboBox<String> cbKtp, cbMobil;
    private com.toedter.calendar.JDateChooser dateTanggal;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblcash;
    private javax.swing.JTextField textbayar, textkodecash;
}