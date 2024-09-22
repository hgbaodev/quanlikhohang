/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThuocTinhSanPham;

import BUS.HeDieuHanhBUS;
import BUS.NhomQuyenBUS;
import DAO.HeDieuHanhDAO;
import DTO.ThuocTinhSanPham.HeDieuHanhDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Panel.QuanLyThuocTinhSP;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author 84907
 */
public class HeDieuHanhDialog extends JDialog implements MouseListener {

    HeaderTitle headTite;
    JPanel top, main, bottom;
    InputForm ms;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;
    ButtonCustom add, del, update;
    HeDieuHanhBUS msBUS = new HeDieuHanhBUS();
    ArrayList<HeDieuHanhDTO> list = msBUS.getAll();
    QuanLyThuocTinhSP qltt;
    private final NhomQuyenBUS nhomquyenBus = new NhomQuyenBUS();

    public HeDieuHanhDialog(JFrame owner, QuanLyThuocTinhSP qltt, String title, boolean modal, int nhomquyen) {
        super(owner, title, modal);
        initComponent(qltt);
        loadQuyen(nhomquyen);
        loadDataTable(list);
    }

    public void loadQuyen(int nhomquyen) {
        if (!nhomquyenBus.checkPermisson(nhomquyen, "thuoctinh", "create")) {
            add.setVisible(false);
        }
        if (!nhomquyenBus.checkPermisson(nhomquyen, "thuoctinh", "delete")) {
            del.setVisible(false);
        }
        if (!nhomquyenBus.checkPermisson(nhomquyen, "thuoctinh", "update")) {
            update.setVisible(false);
        }
    }

    public void initComponent(QuanLyThuocTinhSP qltt) {
        this.qltt = qltt;
        this.setSize(new Dimension(425, 500));
        this.setLayout(new BorderLayout(0, 0));
        this.setResizable(false);
        headTite = new HeaderTitle("HỆ ĐIỀU HÀNH");
        this.setBackground(Color.white);
        top = new JPanel();
        main = new JPanel();
        bottom = new JPanel();

        top.setLayout(new GridLayout(1, 1));
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(0, 70));
        top.add(headTite);

        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(420, 200));
        ms = new InputForm("Tên hệ điều hành sắc");
        ms.setPreferredSize(new Dimension(150, 70));
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.addMouseListener(this);
        scrollTable = new JScrollPane(table);
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã hệ điều hành", "Tên hệ điều hành"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        scrollTable.setViewportView(table);
        scrollTable.setPreferredSize(new Dimension(420, 250));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        main.add(ms);
        main.add(scrollTable);

        add = new ButtonCustom("Thêm", "excel", 15, 100, 40);
        add.addMouseListener(this);
        del = new ButtonCustom("Xóa", "danger", 15, 100, 40);
        del.addMouseListener(this);
        update = new ButtonCustom("Sửa", "success", 15, 100, 40);
        update.addMouseListener(this);
        bottom.setBackground(Color.white);
        bottom.setLayout(new FlowLayout(1, 20, 20));
        bottom.add(add);
        bottom.add(del);
        bottom.add(update);
        bottom.setPreferredSize(new Dimension(0, 70));

        this.add(top, BorderLayout.NORTH);
        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
    }

    public void loadDataTable(ArrayList<HeDieuHanhDTO> result) {
        tblModel.setRowCount(0);
        for (HeDieuHanhDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getMahdh(), ncc.getTenhdh()
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == add) {
            if (Validation.isEmpty(ms.getText())) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên hệ điều hành mới");
            } else {
                String tenmau = ms.getText();
                if (msBUS.checkDup(tenmau)) {
                    int id = HeDieuHanhDAO.getInstance().getAutoIncrement();
                    msBUS.add(new HeDieuHanhDTO(id, tenmau));
                    loadDataTable(list);
                    ms.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Hệ điều hành đã tồn tại !");
                }

            }
        } else if (e.getSource() == del) {
            int index = getRowSelected();
            if (index != -1) {
                msBUS.delete(list.get(index), index);
                loadDataTable(list);
                ms.setText("");
            }
        } else if (e.getSource() == update) {
            int index = getRowSelected();
            if (index != -1) {
                if (Validation.isEmpty(ms.getText())) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tên hệ điều hành");
                } else {
                    String tenmau = ms.getText();
                    if (msBUS.checkDup(tenmau)) {
                        msBUS.update(new HeDieuHanhDTO(list.get(index).getMahdh(), tenmau));
                        loadDataTable(list);
                        ms.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Hệ điều hành đã tồn tại !");
                    }
                }
            }
        } else if (e.getSource() == table) {
            int index = table.getSelectedRow();
            ms.setText(list.get(index).getTenhdh());
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hệ điều hành sắc!");
        }
        return index;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }
}
