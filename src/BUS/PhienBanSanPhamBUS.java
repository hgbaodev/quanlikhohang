/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PhienBanSanPhamDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhienBanSanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhienBanSanPhamBUS {
    private PhienBanSanPhamDAO cauhinhDAO = new PhienBanSanPhamDAO();
    
    public PhienBanSanPhamBUS() {
    }
    
    public ArrayList<PhienBanSanPhamDTO> getAll(int masp) {
        return cauhinhDAO.selectAll(Integer.toString(masp));
    }
    
    public PhienBanSanPhamDTO getByMaPhienBan(int mapb) {
        return cauhinhDAO.selectById(mapb);
    }
    
    public int getIndexByMaPhienBan(ArrayList<PhienBanSanPhamDTO> list, int mapb) {
        int i = 0;
        int vitri = -1;
        while(i < list.size() && vitri == -1) {
            if(list.get(i).getMaphienbansp() == mapb) {
                vitri = i;
            } else i++;
        }
        return vitri;
    }
    
    public void getStringListImei() {
    }
    
    public static boolean checkDuplicate(ArrayList<PhienBanSanPhamDTO> listch, PhienBanSanPhamDTO ch) {
        boolean check = false;
        int i = 0;
        while(i < listch.size() && check == false) {
            if(listch.get(i).equals(ch)) check = true;
            else i++;
        }
        return check;
    }
    
    public Boolean add(ArrayList<PhienBanSanPhamDTO> listch) {
        boolean check = cauhinhDAO.insert(listch) != 0;
        return check;
    }
}

