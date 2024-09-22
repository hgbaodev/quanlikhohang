package GUI.Panel;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Main;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.NumericDocumentFilter;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Component.SelectForm;
import GUI.Component.TableSorter;
import GUI.Dialog.ChiTietPhieuDialog;
import helper.Formater;
import helper.JTableExporter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.text.PlainDocument;

public final class PhieuNhap extends JPanel implements ActionListener, KeyListener, PropertyChangeListener, ItemListener {

    PanelBorderRadius main, functionBar, box;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tablePhieuNhap;
    JScrollPane scrollTablePhieuNhap;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    SelectForm cbxNhaCungCap, cbxNhanVien;
    InputDate dateStart, dateEnd;
    InputForm moneyMin, moneyMax;

    TaoPhieuNhap nhapKho;
    Main m;
    NhanVienDTO nv;

    PhieuNhapBUS phieunhapBUS = new PhieuNhapBUS();
    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();
    ArrayList<PhieuNhapDTO> listPhieu;

    Color BackgroundColor = new Color(240, 247, 250);

    public PhieuNhap(Main m, NhanVienDTO nv) {
        this.m = m;
        this.nv = nv;
        initComponent();
        this.listPhieu = phieunhapBUS.getAll();
        loadDataTalbe(this.listPhieu);
    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(10, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(10, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tablePhieuNhap = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã phiếu nhập", "Nhà cung cấp", "Nhân viên nhập", "Thời gian", "Tổng tiền"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuNhap.setModel(tblModel);
        tablePhieuNhap.setDefaultEditor(Object.class, null);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablePhieuNhap.setDefaultRenderer(Object.class, centerRenderer);
        tablePhieuNhap.setFocusable(false);
        tablePhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(10);
        tablePhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(10);
        tablePhieuNhap.getColumnModel().getColumn(2).setPreferredWidth(200);

        tablePhieuNhap.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tablePhieuNhap, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tablePhieuNhap, 1, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tablePhieuNhap, 5, TableSorter.VND_CURRENCY_COMPARATOR);

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "detail", "cancel", "export"};
        mainFunction = new MainFunction(m.user.getManhomquyen(), "nhaphang", action);

        //Add Event MouseListener
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }

        functionBar.add(mainFunction);

        String[] objToSearch = {"Tất cả", "Mã phiếu nhập", "Nhà cung cấp", "Nhân viên nhập"};
        search = new IntegratedSearch(objToSearch);
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(this);
        search.btnReset.addActionListener(this);
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        box = new PanelBorderRadius();
        box.setPreferredSize(new Dimension(250, 0));
        box.setLayout(new GridLayout(6, 1, 10, 0));
        box.setBorder(new EmptyBorder(0, 5, 150, 5));
        contentCenter.add(box, BorderLayout.WEST);

        // Handle
        String[] listNcc = nccBUS.getArrTenNhaCungCap();
        listNcc = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listNcc)).toArray(String[]::new);
        String[] listNv = nvBUS.getArrTenNhanVien();
        listNv = Stream.concat(Stream.of("Tất cả"), Arrays.stream(listNv)).toArray(String[]::new);

        // init
        cbxNhaCungCap = new SelectForm("Nhà cung cấp", listNcc);
        cbxNhanVien = new SelectForm("Nhân viên nhập", listNv);
        dateStart = new InputDate("Từ ngày");
        dateEnd = new InputDate("Đến ngày");
        moneyMin = new InputForm("Từ số tiền (VND)");
        moneyMax = new InputForm("Đến số tiền (VND)");

        PlainDocument doc_min = (PlainDocument) moneyMin.getTxtForm().getDocument();
        doc_min.setDocumentFilter(new NumericDocumentFilter());

        PlainDocument doc_max = (PlainDocument) moneyMax.getTxtForm().getDocument();
        doc_max.setDocumentFilter(new NumericDocumentFilter());

        // add listener
        cbxNhaCungCap.getCbb().addItemListener(this);
        cbxNhanVien.getCbb().addItemListener(this);
        dateStart.getDateChooser().addPropertyChangeListener(this);
        dateEnd.getDateChooser().addPropertyChangeListener(this);
        moneyMin.getTxtForm().addKeyListener(this);
        moneyMax.getTxtForm().addKeyListener(this);

        box.add(cbxNhaCungCap);
        box.add(cbxNhanVien);
        box.add(dateStart);
        box.add(dateEnd);
        box.add(moneyMin);
        box.add(moneyMax);

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentCenter.add(main, BorderLayout.CENTER);
        main.add(scrollTablePhieuNhap);
    }

    public void loadDataTalbe(ArrayList<PhieuNhapDTO> listphieunhap) {
        tblModel.setRowCount(0);
        int size = listphieunhap.size();
        for (int i = 0; i < size; i++) {
            tblModel.addRow(new Object[]{
                i + 1, (int) listphieunhap.get(i).getMaphieu(),
                nccBUS.getTenNhaCungCap(listphieunhap.get(i).getManhacungcap()),
                nvBUS.getNameById(listphieunhap.get(i).getManguoitao()),
                Formater.FormatTime(listphieunhap.get(i).getThoigiantao()),
                Formater.FormatVND(listphieunhap.get(i).getTongTien())
            });
        }
    }

    public int getRowSelected() {
        int index = tablePhieuNhap.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu nhập");
        }
        return index;
    }

    public void Fillter() throws ParseException {
        if (validateSelectDate()) {
            int type = search.cbxChoose.getSelectedIndex();
            int mancc = cbxNhaCungCap.getSelectedIndex() == 0 ? 0 : nccBUS.getByIndex(cbxNhaCungCap.getSelectedIndex() - 1).getMancc();
            int manv = cbxNhanVien.getSelectedIndex() == 0 ? 0 : nvBUS.getByIndex(cbxNhanVien.getSelectedIndex() - 1).getManv();
            String input = search.txtSearchForm.getText() != null ? search.txtSearchForm.getText() : "";
            Date time_start = dateStart.getDate() != null ? dateStart.getDate() : new Date(0);
            Date time_end = dateEnd.getDate() != null ? dateEnd.getDate() : new Date(System.currentTimeMillis());
            String min_price = moneyMin.getText();
            String max_price = moneyMax.getText();
            this.listPhieu = phieunhapBUS.fillerPhieuNhap(type, input, mancc, manv, time_start, time_end, min_price, max_price);
            loadDataTalbe(listPhieu);
        }
    }

    public void resetForm() {
        cbxNhaCungCap.setSelectedIndex(0);
        cbxNhanVien.setSelectedIndex(0);
        search.cbxChoose.setSelectedIndex(0);
        search.txtSearchForm.setText("");
        moneyMin.setText("");
        moneyMax.setText("");
        dateStart.getDateChooser().setCalendar(null);
        dateEnd.getDateChooser().setCalendar(null);
        this.listPhieu = phieunhapBUS.getAllList();
        loadDataTalbe(listPhieu);
    }

    public boolean validateSelectDate() throws ParseException {
        Date time_start = dateStart.getDate();
        Date time_end = dateEnd.getDate();

        Date current_date = new Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateStart.getDateChooser().setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateEnd.getDateChooser().setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            dateEnd.getDateChooser().setCalendar(null);
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == mainFunction.btn.get("create")) {
            nhapKho = new TaoPhieuNhap(nv, "create", m);
            m.setPanel(nhapKho);
            loadDataTalbe(listPhieu);
        } else if (source == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            int maPhieuNhap = (int) tablePhieuNhap.getValueAt(index, 1);
            if (index != -1) {
//                nhapKho = new TaoPhieuNhap(nv, "view", listPhieu.get(index), m);
//                m.setPanel(nhapKho);
                ChiTietPhieuDialog ctsp = new ChiTietPhieuDialog(m, "Thông tin phiếu nhập", true, findPhieuNhap(maPhieuNhap));//get index khi chua sort?????
            }
        } else if (source == mainFunction.btn.get("cancel")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn huỷ phiếu ?\nThao tác này không thể hoàn tác nên hãy suy nghĩ kĩ !", "Huỷ phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    PhieuNhapDTO pn = listPhieu.get(index);
                    System.out.println(pn);
                    if (!phieunhapBUS.checkCancelPn(pn.getMaphieu())) {
                        JOptionPane.showMessageDialog(null, "Sản phẩm trong phiếu này đã được xuất đi không thể hủy phiếu này!");
                    } else {
                        int c = phieunhapBUS.cancelPhieuNhap(pn.getMaphieu());
                        if (c == 0) {
                            JOptionPane.showMessageDialog(null, "Hủy phiếu không thành công!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Hủy phiếu thành công!");
                            loadDataTalbe(phieunhapBUS.getAll());
                        }
                    }
                }
            }
        } else if (source == search.btnReset) {
            resetForm();
        } else if (source == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tablePhieuNhap);
            } catch (IOException ex) {
                Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PhieuNhapDTO findPhieuNhap(int maPhieuNhap) {
        for (PhieuNhapDTO pn : listPhieu) {
            if (pn.getMaphieu() == maPhieuNhap) {
                return pn;
            }
        }
        return null;
    }
}
