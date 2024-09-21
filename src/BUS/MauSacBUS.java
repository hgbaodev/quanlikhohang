/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.MauSacDAO;
import DTO.ThuocTinhSanPham.MauSacDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class MauSacBUS {

    private MauSacDAO mausacDAO = new MauSacDAO();
    private ArrayList<MauSacDTO> listMauSac = new ArrayList<>();

    public MauSacBUS() {
        this.listMauSac = mausacDAO.selectAll();
    }

    public ArrayList<MauSacDTO> getAll() {
        return this.listMauSac;
    }

    public String[] getArrTenMauSac() {
        String[] result = new String[listMauSac.size()];
        for (int i = 0; i < listMauSac.size(); i++) {
            result[i] = listMauSac.get(i).getTenmau();
        }
        return result;
    }

    public MauSacDTO getByIndex(int index) {
        return this.listMauSac.get(index);
    }

    public boolean add(MauSacDTO msac) {
        boolean check = mausacDAO.insert(msac) != 0;
        if (check) {
            this.listMauSac.add(msac);
        }
        return check;
    }

    public boolean delete(MauSacDTO msac, int index) {
        boolean check = mausacDAO.delete(Integer.toString(msac.getMamau())) != 0;
        if (check) {
            this.listMauSac.remove(index);
        }
        return check;
    }

    public int getIndexByMaMau(int mamau) {
        int i = 0;
        int vitri = -1;
        while (i < this.listMauSac.size() && vitri == -1) {
            if (listMauSac.get(i).getMamau() == mamau) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public String getTenMau(int mamau) {
        int index = this.getIndexByMaMau(mamau);
        System.out.println(index);
        return this.listMauSac.get(index).getTenmau();
    }

    public boolean update(MauSacDTO msac) {
        boolean check = mausacDAO.update(msac) != 0;
        if (check) {
            this.listMauSac.set(getIndexByMaMau(msac.getMamau()), msac);
        }
        return check;
    }

    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < this.listMauSac.size() && check == true) {
            if (this.listMauSac.get(i).getTenmau().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }

}
