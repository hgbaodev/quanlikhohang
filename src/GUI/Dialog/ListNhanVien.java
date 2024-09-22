/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Component.ButtonCustom;
import GUI.Panel.TaiKhoan;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author robot
 */
public class ListNhanVien extends JDialog implements MouseListener {

    private TaiKhoan guiTaiKhoan;
    private JTable tableNhanVien;
    private JScrollPane scrollTableSanPham;
    private DefaultTableModel tblModel;
    private ArrayList<NhanVienDTO> listnv ;
    DefaultTableCellRenderer centerRenderer;
    
    public ListNhanVien(TaiKhoan taiKhoan, JFrame owner, String title, boolean modal){
        super(owner, title, modal);
        this.listnv = NhanVienDAO.getInstance().selectAllNV();
        this.guiTaiKhoan = taiKhoan;
        this.centerRenderer = new DefaultTableCellRenderer();
        init();
    }

    public void init(){
        this.setSize(new Dimension(850,600));
        this.setLayout(new BorderLayout());
        JPanel panelSearch = new JPanel(new BorderLayout());
        panelSearch.setSize(new Dimension(0,150));
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel jLabelSearch = new JLabel("Tìm kiếm  ");
        jLabelSearch.setSize(new Dimension(100,0));
        JTextField jTextFieldSearch = new JTextField();
        jTextFieldSearch.setFont(new Font(FlatRobotoFont.FAMILY, 0, 13));
        jTextFieldSearch.putClientProperty("JTextField.placeholderText", "Tìm kiếm nhân viên....");
        jTextFieldSearch.putClientProperty("JTextField.showClearButton", true);
        jTextFieldSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String txt = jTextFieldSearch.getText();
                listnv = search(txt);
                loadDataTalbe(listnv);
            }
        });
        ButtonCustom buttonAdd = new ButtonCustom("Chọn nhân viên", "success", 14);
        buttonAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getRow()<0){
                    int input = JOptionPane.showConfirmDialog(null, 
                "Vui lòng chọn nhân viên!:)", "Thông báo", JOptionPane.DEFAULT_OPTION);
                } else{
                    dispose();
                    TaiKhoanDialog tkd = new TaiKhoanDialog(guiTaiKhoan, guiTaiKhoan.owner, "Thêm nhân viên", true, "create", listnv.get(getRow()).getManv());
                }
            }
            
        });
        panelSearch.add(jLabelSearch,BorderLayout.WEST);
        panelSearch.add(jTextFieldSearch,BorderLayout.CENTER);
        panelSearch.add(buttonAdd,BorderLayout.EAST);
        this.add(panelSearch,BorderLayout.NORTH);
        JPanel jPanelTable = new JPanel();
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        jPanelTable.setLayout(new GridLayout(1,1));
        tableNhanVien = new JTable();
        tableNhanVien.setFocusable(false);
        scrollTableSanPham = new JScrollPane();
        tableNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tableNhanVien = new JTable();
        tableNhanVien.setDefaultEditor(Object.class, null);
        tableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"MNV","Họ tên","Giới tính","Ngày Sinh","SDT","Email"};
        tblModel.setColumnIdentifiers(header);
        tableNhanVien.setDefaultRenderer(Object.class, centerRenderer);
        tableNhanVien.setFocusable(false);
        tableNhanVien.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableNhanVien);
        jPanelTable.add(scrollTableSanPham);
        this.add(jPanelTable,BorderLayout.CENTER);
        loadDataTalbe(listnv);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public int getRow(){
        return tableNhanVien.getSelectedRow();
    }
    
    public void loadDataTalbe(ArrayList<DTO.NhanVienDTO> listnv) {
        tblModel.setRowCount(0);
        System.out.println("size: "+listnv.size());
        for (DTO.NhanVienDTO nhanVien : listnv) {
            tblModel.addRow(new Object[]{
                nhanVien.getManv(),nhanVien.getHoten(),nhanVien.getGioitinh()==1?"Nam":"Nữ",nhanVien.getNgaysinh(),nhanVien.getSdt(),nhanVien.getEmail()
            });
        }
    }
    
    public ArrayList<NhanVienDTO> search(String text) {
        if(text.length()>0){
            text = text.toLowerCase();
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        System.out.println(text);
        for(NhanVienDTO i : listnv) {
           if(i.getHoten().toLowerCase().contains(text) || i.getEmail().toLowerCase().contains(text)
                   || i.getSdt().toLowerCase().contains(text)){
               result.add(i);
           }
        }
        return result;
        } else {
            return NhanVienDAO.getInstance().selectAll();
        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
