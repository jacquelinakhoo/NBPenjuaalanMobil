package penjualanmobil;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;

public class TransaksiBeliKredit extends javax.swing.JFrame {

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

    public TransaksiBeliKredit() {
        initComponents();
        applyCustomStyles(); 
        
                // Action for the Kembali button
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new MenuUtama().show(); // Opens the main menu
                dispose(); // Closes this current window
            }
        });
    }

    private void applyCustomStyles() {
        getContentPane().setBackground(vanillaCream);

        jLabel1.setFont(titleFont);
        jLabel1.setForeground(rosewood);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.JLabel[] labels = {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7};
        for (javax.swing.JLabel lbl : labels) {
            lbl.setFont(labelFont);
            lbl.setForeground(midnightLagoon);
        }

        javax.swing.JTextField[] fields = {textidbeli, textbayar};
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
        cbPaket.setFont(inputFont);
        
        dateTanggal.setFont(inputFont);
        dateTanggal.getCalendarButton().setBackground(lightGrey);

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

        tblkredit.setFont(inputFont);
        tblkredit.setRowHeight(30); 
        tblkredit.setGridColor(new Color(230, 230, 230)); 
        tblkredit.getTableHeader().setFont(labelFont);
        tblkredit.getTableHeader().setBackground(lightGrey); 
        tblkredit.getTableHeader().setForeground(midnightLagoon);
        
        javax.swing.table.DefaultTableCellRenderer headerRenderer = 
                (javax.swing.table.DefaultTableCellRenderer) tblkredit.getTableHeader().getDefaultRenderer();
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
        textidbeli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbKtp = new javax.swing.JComboBox<>();
        cbMobil = new javax.swing.JComboBox<>();
        cbPaket = new javax.swing.JComboBox<>();
        dateTanggal = new com.toedter.calendar.JDateChooser();
        textbayar = new javax.swing.JTextField();
        btnubah = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblkredit = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        btnkembali.setText("Kembali");
        jLabel1.setText("Transaksi Beli Kredit");
        jLabel2.setText("ID_Beli:");
        jLabel3.setText("KTP Pembeli:");
        jLabel4.setText("Kode Mobil:");
        jLabel5.setText("Paket Kredit:");
        jLabel6.setText("Tanggal Pembelian:");
        jLabel7.setText("Bayar (Rp):");

        cbKtp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2" }));
        cbMobil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2" }));
        cbPaket.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2" }));

        btnubah.setText("Ubah");
        btntambah.setText("Tambah");
        btnhapus.setText("Hapus");

        tblkredit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{null, null, null, null},{null, null, null, null}},
            new String [] {"Title 1", "Title 2", "Title 3", "Title 4"}
        ));
        jScrollPane1.setViewportView(tblkredit);

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
                                        .addComponent(jLabel3).addComponent(jLabel2).addComponent(jLabel4).addComponent(jLabel5).addComponent(jLabel6).addComponent(jLabel7))
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbKtp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbMobil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbPaket, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dateTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                        .addComponent(textidbeli, javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(jLabel2).addComponent(textidbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3).addComponent(cbKtp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4).addComponent(cbMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5).addComponent(cbPaket, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6).addComponent(dateTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7).addComponent(textbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
} catch (Exception ex) {}
        java.awt.EventQueue.invokeLater(() -> new TransaksiBeliKredit().setVisible(true));
    }

    private javax.swing.JButton btnhapus, btnkembali, btntambah, btnubah;
    private javax.swing.JComboBox<String> cbKtp, cbMobil, cbPaket;
    private com.toedter.calendar.JDateChooser dateTanggal;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblkredit;
    private javax.swing.JTextField textbayar, textidbeli;
}