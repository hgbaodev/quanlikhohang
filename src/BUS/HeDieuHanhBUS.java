package BUS;

import DAO.HeDieuHanhDAO;
import DTO.ThuocTinhSanPham.HeDieuHanhDTO;
import java.util.ArrayList;

/**
 *
 * @author 84907
 */
public class HeDieuHanhBUS {

    private HeDieuHanhDAO hdhDAO = new HeDieuHanhDAO();
    private ArrayList<HeDieuHanhDTO> listHeDieuHanh = new ArrayList<>();

    public HeDieuHanhBUS() {
        this.listHeDieuHanh = hdhDAO.selectAll();
    }

    public ArrayList<HeDieuHanhDTO> getAll() {
        return this.listHeDieuHanh;
    }

    public String[] getArrTenHeDieuHanh() {
        String[] result = new String[listHeDieuHanh.size()];
        for (int i = 0; i < listHeDieuHanh.size(); i++) {
            result[i] = listHeDieuHanh.get(i).getTenhdh();
        }
        return result;
    }

    public HeDieuHanhDTO getByIndex(int index) {
        return this.listHeDieuHanh.get(index);
    }

    public boolean add(HeDieuHanhDTO hdh) {
        boolean check = hdhDAO.insert(hdh) != 0;
        if (check) {
            this.listHeDieuHanh.add(hdh);
        }
        return check;
    }

    public boolean delete(HeDieuHanhDTO hdh, int index) {
        boolean check = hdhDAO.delete(Integer.toString(hdh.getMahdh())) != 0;
        if (check) {
            this.listHeDieuHanh.remove(index);
        }
        return check;
    }

    public int getIndexByMaMau(int mamau) {
        int i = 0;
        int vitri = -1;
        while (i < this.listHeDieuHanh.size() && vitri == -1) {
            if (listHeDieuHanh.get(i).getMahdh() == mamau) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public String getTenHdh(int mahdh) {
        int index = this.getIndexByMaMau(mahdh);
        return this.listHeDieuHanh.get(index).getTenhdh();
    }

    public boolean update(HeDieuHanhDTO hdh) {
        boolean check = hdhDAO.update(hdh) != 0;
        if (check) {
            this.listHeDieuHanh.set(getIndexByMaMau(hdh.getMahdh()), hdh);
        }
        return check;
    }

    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < this.listHeDieuHanh.size() && check == true) {
            if (this.listHeDieuHanh.get(i).getTenhdh().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }

}
