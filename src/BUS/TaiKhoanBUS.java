
package BUS;

import DAO.NhomQuyenDAO;
import DAO.TaiKhoanDAO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;

/**
 *
 * @author robot
 */
public class TaiKhoanBUS {
    private ArrayList<TaiKhoanDTO> listTaiKhoan;
    private ArrayList<NhomQuyenDTO> listNhomQuyen;
    private NhomQuyenDAO nhomQuyenDAO = NhomQuyenDAO.getInstance();
    
    public TaiKhoanBUS(){
        this.listTaiKhoan  = TaiKhoanDAO.getInstance().selectAll();
        this.listNhomQuyen = NhomQuyenDAO.getInstance().selectAll();
    }
    
    public ArrayList<TaiKhoanDTO> getTaiKhoanAll(){
        return listTaiKhoan;
    }
    
    public TaiKhoanDTO getTaiKhoan(int index){
        return listTaiKhoan.get(index);
    }
    public int getTaiKhoanByMaNV(int manv){
        int i = 0;
        int vitri = -1;
        while (i < this.listTaiKhoan.size() && vitri == -1) {
            if (listTaiKhoan.get(i).getManv()== manv) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    
    public NhomQuyenDTO getNhomQuyenDTO(int manhom){
        return nhomQuyenDAO.selectById(manhom+"");
    }
    
    public void addAcc(TaiKhoanDTO tk){
        listTaiKhoan.add(tk);
    }
    
    public void updateAcc(int index, TaiKhoanDTO tk){
        listTaiKhoan.set(index, tk);
    }
    
    public void deleteAcc(int manv){
        
    }

    public ArrayList<TaiKhoanDTO> search(String txt, String type) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (TaiKhoanDTO i : listTaiKhoan) {
                    if (Integer.toString(i.getManv()).contains(txt) || i.getUsername().contains(txt) ) {
                        result.add(i);
                    }
                }
            }
            case "Mã nhân viên" -> {
                for (TaiKhoanDTO i : listTaiKhoan) {
                    if (Integer.toString(i.getManv()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Username" -> {
                for (TaiKhoanDTO i : listTaiKhoan) {
                    if (i.getUsername().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

}
