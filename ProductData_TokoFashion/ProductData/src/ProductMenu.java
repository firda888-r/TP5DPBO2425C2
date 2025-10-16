import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductMenu extends JFrame {
    public static void main(String[] args) {
        ProductMenu menu = new ProductMenu();
        menu.setSize(700, 600);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(new Color(255, 182, 193));
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int selectedIndex = -1;
    private Database database;

    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;

    public ProductMenu() {
        database = new Database();

        productTable.setModel(setTable());
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        String[] kategoriData = { "???", "Pakaian", "Tas" };
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        deleteButton.setVisible(false);

        addUpdateButton.addActionListener(e -> {
            if (selectedIndex == -1) {
                insertData();
            } else {
                updateData();
            }
        });

        deleteButton.addActionListener(e -> deleteData());
        cancelButton.addActionListener(e -> clearForm());

        // Ambil nilai dari tabel (tanpa ArrayList)
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = productTable.getSelectedRow();

                String curId = productTable.getModel().getValueAt(selectedIndex, 0).toString();
                String curNama = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curHarga = productTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curKategori = productTable.getModel().getValueAt(selectedIndex, 3).toString();

                idField.setText(curId);
                namaField.setText(curNama);
                hargaField.setText(curHarga);
                kategoriComboBox.setSelectedItem(curKategori);

                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        Object[] cols = {"ID", "Nama", "Harga", "Kategori"};
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        try {
            ResultSet rs = database.selectQuery("SELECT * FROM product");
            while (rs.next()) {
                Object[] row = {
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga"),
                        rs.getString("kategori")
                };
                tmp.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data dari database!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return tmp;
    }

    private void insertData() {
        String id = idField.getText().trim();
        String nama = namaField.getText().trim();
        String hargaStr = hargaField.getText().trim();
        String kategori = kategoriComboBox.getSelectedItem().toString();

        // Validasi input kosong
        if (id.isEmpty() || nama.isEmpty() || hargaStr.isEmpty() || kategori.equals("???")) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double harga = Double.parseDouble(hargaStr);

            // Cek ID duplikat
            ResultSet rs = database.selectQuery("SELECT * FROM product WHERE id = '" + id + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "ID sudah ada! Gunakan ID lain.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "INSERT INTO product (id, nama, harga, kategori) VALUES ('" + id + "', '" + nama + "', " + harga + ", '" + kategori + "')";
            database.insertUpdateDeleteQuery(query);

            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateData() {
        String id = idField.getText().trim();
        String nama = namaField.getText().trim();
        String hargaStr = hargaField.getText().trim();
        String kategori = kategoriComboBox.getSelectedItem().toString();

        // Validasi input kosong
        if (id.isEmpty() || nama.isEmpty() || hargaStr.isEmpty() || kategori.equals("???")) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double harga = Double.parseDouble(hargaStr);
            String query = "UPDATE product SET nama='" + nama + "', harga=" + harga + ", kategori='" + kategori + "' WHERE id='" + id + "'";
            database.insertUpdateDeleteQuery(query);

            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteData() {
        String id = idField.getText();

        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            String query = "DELETE FROM product WHERE id='" + id + "'";
            database.insertUpdateDeleteQuery(query);
            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }
}
