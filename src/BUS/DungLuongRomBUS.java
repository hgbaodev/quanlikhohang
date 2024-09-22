/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DungLuongRomDAO;
import DTO.ThuocTinhSanPham.DungLuongRomDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DungLuongRomBUS {

    private final DungLuongRomDAO dlromDAO = new DungLuongRomDAO();
    private ArrayList<DungLuongRomDTO> listDLRom = new ArrayList<>();

    public DungLuongRomBUS getInstance() {
        return new DungLuongRomBUS();
    }

    public DungLuongRomBUS() {
        listDLRom = dlromDAO.selectAll();
    }

    public ArrayList<DungLuongRomDTO> getAll() {
        return this.listDLRom;
    }

    public DungLuongRomDTO getByIndex(int index) {
        return this.listDLRom.get(index);
    }

    public int getIndexByMaRom(int marom) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDLRom.size() && vitri == -1) {
            if (listDLRom.get(i).getMadungluongrom() == marom) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public boolean add(DungLuongRomDTO dlrom) {
        boolean check = dlromDAO.insert(dlrom) != 0;
        if (check) {
            this.listDLRom.add(dlrom);
        }
        return check;
    }

    public boolean delete(DungLuongRomDTO dlrom, int index) {
        boolean check = dlromDAO.delete(Integer.toString(dlrom.getMadungluongrom())) != 0;
        if (check) {
            this.listDLRom.remove(index);
        }
        return check;
    }

    public boolean update(DungLuongRomDTO dlrom) {
        boolean check = dlromDAO.update(dlrom) != 0;
        if (check) {
            this.listDLRom.set(getIndexById(dlrom.getMadungluongrom()), dlrom);
        }
        return check;
    }

    public int getIndexById(int madlrom) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDLRom.size() && vitri == -1) {
            if (listDLRom.get(i).getMadungluongrom() == madlrom) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public int getKichThuocById(int madlrom) {
        return this.listDLRom.get(this.getIndexById(madlrom)).getDungluongrom();
    }

    public String[] getArrKichThuoc() {
        String[] result = new String[listDLRom.size()];
        for (int i = 0; i < listDLRom.size(); i++) {
            result[i] = Integer.toString(listDLRom.get(i).getDungluongrom()) + "GB";
        }
        return result;
    }

    public boolean checkDup(int dl) {
        boolean check = true;
        int i = 0;
        while (i < this.listDLRom.size() && check == true) {
            if (this.listDLRom.get(i).getDungluongrom()== dl) {
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }
}
