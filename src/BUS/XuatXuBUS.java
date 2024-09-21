/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.XuatXuDAO;
import DTO.ThuocTinhSanPham.XuatXuDTO;
import java.util.ArrayList;

/**
 *
 * @author 84907
 */
public class XuatXuBUS {
    private XuatXuDAO xuatxuDAO = new XuatXuDAO();
    private ArrayList<XuatXuDTO> listXuatXu = new ArrayList<>();

    public XuatXuBUS() {
        this.listXuatXu = xuatxuDAO.selectAll();
    }

    public ArrayList<XuatXuDTO> getAll() {
        return this.listXuatXu;
    }

    public String[] getArrTenXuatXu() {
        String[] result = new String[listXuatXu.size()];
        for (int i = 0; i < listXuatXu.size(); i++) {
            result[i] = listXuatXu.get(i).getTenxuatxu();
        }
        return result;
    }

    public XuatXuDTO getByIndex(int index) {
        return this.listXuatXu.get(index);
    }

    public boolean add(XuatXuDTO xuatxu) {
        boolean check = xuatxuDAO.insert(xuatxu) != 0;
        if (check) {
            this.listXuatXu.add(xuatxu);
        }
        return check;
    }

    public boolean delete(XuatXuDTO xuatxu, int index) {
        boolean check = xuatxuDAO.delete(Integer.toString(xuatxu.getMaxuatxu())) != 0;
        if (check) {
            this.listXuatXu.remove(index);
        }
        return check;
    }

    public int getIndexByMaXX(int maxx) {
        int i = 0;
        int vitri = -1;
        while (i < this.listXuatXu.size() && vitri == -1) {
            if (listXuatXu.get(i).getMaxuatxu()== maxx) vitri = i;
            else i++;
        }
        return vitri;
    }

    public String getTenXuatXu(int maxx) {
        int index = this.getIndexByMaXX(maxx);
        return this.listXuatXu.get(index).getTenxuatxu();
    }

    public boolean update(XuatXuDTO xuatxu) {
        boolean check = xuatxuDAO.update(xuatxu) != 0;
        if (check) {
            this.listXuatXu.set(getIndexByMaXX(xuatxu.getMaxuatxu()), xuatxu);
        }
        return check;
    }
    
    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < this.listXuatXu.size() && check == true) {
            if (this.listXuatXu.get(i).getTenxuatxu().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }

}
