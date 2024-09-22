package GUI.Panel;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.NhomQuyenBUS;
import BUS.TaiKhoanBUS;
import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.Main;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Dialog.ListNhanVien;
import GUI.Dialog.TaiKhoanDialog;
import helper.BCrypt;
import helper.JTableExporter;
import helper.Validation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TaiKhoan extends JPanel implements ActionListener, ItemListener {

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableTaiKhoan;
    JScrollPane scrollTableTaiKhoan;
    MainFunction mainFunction;
    IntegratedSearch search;
    public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    Color BackgroundColor = new Color(240, 247, 250);
    DefaultTableModel tblModel;
    public TaiKhoanBUS taiKhoanBus = new TaiKhoanBUS();
    ArrayList<TaiKhoanDTO> listTk = taiKhoanBus.getTaiKhoanAll();

    private Main m;

    public TaiKhoan(Main m) {
        this.m = m;
        initComponent();
        loadTable(listTk);
    }

    private void initComponent() {
        tableTaiKhoan = new JTable();
        tableTaiKhoan.setDefaultEditor(Object.class, null);
        scrollTableTaiKhoan = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"MaNV", "Tên đăng nhập", "Nhóm quyền", "Trạng thái"};
        tblModel.setColumnIdentifiers(header);
        tableTaiKhoan.setModel(tblModel);
        tableTaiKhoan.setFocusable(false);
        scrollTableTaiKhoan.setViewportView(tableTaiKhoan);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        tableTaiKhoan.setDefaultRenderer(Object.class, centerRenderer);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableTaiKhoan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableTaiKhoan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4 chỉ để thêm contentCenter ở giữa mà contentCenter không bị dính sát vào các thành phần khác
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

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "update", "delete", "detail", "import", "export"};
        mainFunction = new MainFunction(m.user.getManhomquyen(), "taikhoan", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }

        functionBar.add(mainFunction);
        search = new IntegratedSearch(new String[]{"Tất cả", "Mã nhân viên", "Username"});
        search.cbxChoose.addItemListener(this);
        functionBar.add(search);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                listTk = taiKhoanBus.search(txt, type);
                loadTable(listTk);
            }
        });
        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTableTaiKhoan);
    }

    public void loadTable(ArrayList<TaiKhoanDTO> list) {
        tblModel.setRowCount(0);
        for (TaiKhoanDTO taiKhoanDTO : list) {
            int tt = taiKhoanDTO.getTrangthai();
            String trangthaiString = "";
            switch (tt) {
                case 1 -> {
                    trangthaiString = "Hoạt động";
                }
                case 0 -> {
                    trangthaiString = "Ngưng hoạt động";
                }
            }
            tblModel.addRow(new Object[]{
                taiKhoanDTO.getManv(), taiKhoanDTO.getUsername(), taiKhoanBus.getNhomQuyenDTO(taiKhoanDTO.getManhomquyen()).getTennhomquyen(), trangthaiString
            });
        }
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public int getRowSelected() {
        int index = tableTaiKhoan.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản");
        }
        return index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            ListNhanVien listNV = new ListNhanVien(this, owner, "Chọn tài khoản", true);
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                TaiKhoanDialog add = new TaiKhoanDialog(this, owner, "Cập nhật tài khoản", true, "update", listTk.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa tài khoản :)!", "Xóa xóa tài khoản",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    TaiKhoanDAO.getInstance().delete(listTk.get(index).getManv() + "");
                    loadTable(taiKhoanBus.getTaiKhoanAll());
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                TaiKhoanDialog add = new TaiKhoanDialog(this, owner, "Thêm tài khoản", true, "view", listTk.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableTaiKhoan);
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == mainFunction.btn.get("import")) {
            importExcel();
        }
    }

    public void importExcel() {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        JFileChooser jf = new JFileChooser();
        int result = jf.showOpenDialog(null);
        jf.setDialogTitle("Open file");
        Workbook workbook = null;
        int k = 0;
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = jf.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);
                    Cell cell0=excelRow.getCell(0);
                    int manv = (int)excelRow.getCell(0).getNumericCellValue();
                    String tendangnhap = excelRow.getCell(1).getStringCellValue();
                    String matkhau = excelRow.getCell(2).getStringCellValue();
                    String nhomquyen = excelRow.getCell(3).getStringCellValue();
                    int check1 = 0, check2 = 0, check3 = 0, check4 = 0;
                    if (Validation.isEmpty(String.valueOf(manv)) || Validation.isEmpty(tendangnhap)
                            || Validation.isEmpty(matkhau)
                            || Validation.isEmpty(nhomquyen)) {
                        check1 = 1;
                    }
                    int manhomquyen = 0;
                    NhanVienBUS nvbus = new NhanVienBUS();
                    ArrayList<NhanVienDTO> nvlist = nvbus.getAll();
                    for (NhanVienDTO nv : nvlist) {
                        if (nv.getManv() == manv) {
                            check2 = 0;
                            break;
                        } else {
                            check2 = 1;
                        }
                    }
                    ArrayList<TaiKhoanDTO> curlist = taiKhoanBus.getTaiKhoanAll();
                    for (TaiKhoanDTO tk : curlist) {
                        if (tk.getUsername().equals(tendangnhap)) {
                            check3 = 1;
                            break;
                        } else {
                            check3 = 0;
                        }
                    }
                    NhomQuyenBUS nhomquyenbus = new NhomQuyenBUS();
                    ArrayList<NhomQuyenDTO> quyenlist = nhomquyenbus.getAll();
                    for (NhomQuyenDTO quyen : quyenlist) {
                        if (quyen.getTennhomquyen().trim().equals(nhomquyen.trim())) {
                            check4 = 0;
                            manhomquyen = quyen.getManhomquyen();
                            break;
                        } else {
                            check4 = 1;
                        }
                    }
                    System.out.println(manv + ":" + tendangnhap + ":" + matkhau + ":" + manhomquyen);
                    System.out.println(check1 + " " + check2 + " " + check3 + " " + check4);
                    if (check1 != 0 || check2 != 0 || check3 != 0 || check4 != 0) {
                        k += 1;
                    } else {
                        System.out.println(manv + ":" + tendangnhap + ":" + matkhau + ":" + manhomquyen);
                        String pass = BCrypt.hashpw(matkhau, BCrypt.gensalt(12));
                        TaiKhoanDTO newaccount = new TaiKhoanDTO(manv, tendangnhap, pass, manhomquyen, 1);
                        TaiKhoanDAO.getInstance().insert(newaccount);
                        listTk.add(newaccount);
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Lỗi đọc file");
            } catch (IOException ex) {
                System.out.println("Lỗi đọc file");
            }
        }
        if (k != 0) {
            JOptionPane.showMessageDialog(this, "Những dữ liệu không chuẩn không được thêm vào");
        } else {
            JOptionPane.showMessageDialog(this, "Nhập dữ liệu thành công");
        }

        loadTable(listTk);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        listTk = taiKhoanBus.search(txt, type);
        loadTable(listTk);
    }

}
