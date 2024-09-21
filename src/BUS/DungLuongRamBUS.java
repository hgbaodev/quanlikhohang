/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DungLuongRamDAO;
import DTO.ThuocTinhSanPham.DungLuongRamDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DungLuongRamBUS {
    private final DungLuongRamDAO dlramDAO = new DungLuongRamDAO();
    private ArrayList<DungLuongRamDTO> listDLRam = new ArrayList<>();

    public DungLuongRamBUS getInstance() {
        return new DungLuongRamBUS();
    }
    
    public DungLuongRamBUS() {
        listDLRam = dlramDAO.selectAll();
    }

    public ArrayList<DungLuongRamDTO> getAll() {
        return this.listDLRam;
    }

    public DungLuongRamDTO getByIndex(int index) {
        return this.listDLRam.get(index);
    }

    public int getIndexByMaRam(int maram) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDLRam.size() && vitri == -1) {
            if (listDLRam.get(i).getMadlram()== maram) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public boolean add(DungLuongRamDTO dlram) {
        boolean check = dlramDAO.insert(dlram) != 0;
        if (check) {
            this.listDLRam.add(dlram);
        }
        return check;
    }

    public boolean delete(DungLuongRamDTO dlram, int index) {
        boolean check = dlramDAO.delete(Integer.toString(dlram.getMadlram())) != 0;
        if (check) {
            this.listDLRam.remove(index);
        }
        return check;
    }

    public boolean update(DungLuongRamDTO dlram) {
        boolean check = dlramDAO.update(dlram) != 0;
        if (check) {
            this.listDLRam.set(getIndexById(dlram.getMadlram()), dlram);
        }
        return check;
    }

    public int getIndexById(int madlram) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDLRam.size() && vitri == -1) {
            if (listDLRam.get(i).getMadlram()== madlram) {
                vitri = i;
            } else i++;
        }
        return vitri;
    }
    
    public boolean checkDup(int dl) {
        boolean check = true;
        int i = 0;
        while(i < this.listDLRam.size() && check == true) {
            if(this.listDLRam.get(i).getDungluongram()==dl) {
                check = false;
            } else i++;
        }
        return check;
    }
    
    public int getKichThuocById(int madlram) {
        return this.listDLRam.get(this.getIndexById(madlram)).getDungluongram();
    }
    
    public String[] getArrKichThuoc() {
        String[] result = new String[listDLRam.size()];
        for(int i = 0; i < listDLRam.size(); i++) {
            result[i] = Integer.toString(listDLRam.get(i).getDungluongram())+"GB";
        }
        return result;
    }
}
