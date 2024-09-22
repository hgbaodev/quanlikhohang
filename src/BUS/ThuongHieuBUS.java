/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThuongHieuDAO;
import DTO.ThuocTinhSanPham.ThuongHieuDTO;
import java.util.ArrayList;

/**
 *
 * @author 84907
 */
public class ThuongHieuBUS {

    private final ThuongHieuDAO lhDAO = new ThuongHieuDAO();
    private ArrayList<ThuongHieuDTO> listLH = new ArrayList<>();

    public ThuongHieuBUS() {
        listLH = lhDAO.selectAll();
    }

    public ArrayList<ThuongHieuDTO> getAll() {
        return this.listLH;
    }

    public ThuongHieuDTO getByIndex(int index) {
        return this.listLH.get(index);
    }

    public int getIndexByMaLH(int maloaihang) {
        int i = 0;
        int vitri = -1;
        while (i < this.listLH.size() && vitri == -1) {
            if (listLH.get(i).getMathuonghieu() == maloaihang) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(String name) {
        ThuongHieuDTO lh = new ThuongHieuDTO(lhDAO.getAutoIncrement(), name);
        boolean check = lhDAO.insert(lh) != 0;
        if (check) {
            this.listLH.add(lh);
        }
        return check;
    }

    public Boolean delete(ThuongHieuDTO lh) {
        boolean check = lhDAO.delete(Integer.toString(lh.getMathuonghieu())) != 0;
        if (check) {
            this.listLH.remove(lh);
        }
        return check;
    }

    public Boolean update(ThuongHieuDTO lh) {
        boolean check = lhDAO.update(lh) != 0;
        if (check) {
            this.listLH.set(getIndexByMaLH(lh.getMathuonghieu()), lh);
        }
        return check;
    }

    public ArrayList<ThuongHieuDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<ThuongHieuDTO> result = new ArrayList<>();
        for (ThuongHieuDTO i : this.listLH) {
            if (Integer.toString(i.getMathuonghieu()).toLowerCase().contains(text) || i.getTenthuonghieu().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public String[] getArrTenThuongHieu() {
        int size = listLH.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listLH.get(i).getTenthuonghieu();
        }
        return result;
    }

    public String getTenThuongHieu(int mathuonghieu) {
        return this.listLH.get(this.getIndexByMaLH(mathuonghieu)).getTenthuonghieu();
    }

    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < this.listLH.size() && check == true) {
            if (this.listLH.get(i).getTenthuonghieu().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }
}
