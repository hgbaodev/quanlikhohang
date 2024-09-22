package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.PhieuNhapDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhieuNhapBUS {

    public final PhieuNhapDAO phieunhapDAO = new PhieuNhapDAO();
    public final ChiTietPhieuNhapDAO ctPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    public final ChiTietSanPhamDAO chitietsanphamDAO = new ChiTietSanPhamDAO();

    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();

    ArrayList<PhieuNhapDTO> listPhieuNhap;

    public PhieuNhapBUS() {
    }

    public ArrayList<PhieuNhapDTO> getAll() {
        this.listPhieuNhap = phieunhapDAO.selectAll();
        return this.listPhieuNhap;
    }

    public ArrayList<PhieuNhapDTO> getAllList() {
        return this.listPhieuNhap;
    }

    public ArrayList<ChiTietSanPhamDTO> convertHashMapToArray(HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> chitietsanpham) {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        for (ArrayList<ChiTietSanPhamDTO> ctsp : chitietsanpham.values()) {
            result.addAll(ctsp);
        }
        return result;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getChiTietPhieu(int maphieunhap) {
        return ctPhieuNhapDAO.selectAll(Integer.toString(maphieunhap));
    }

    public ArrayList<ChiTietPhieuDTO> getChiTietPhieu_Type(int maphieunhap) {
        ArrayList<ChiTietPhieuNhapDTO> arr = ctPhieuNhapDAO.selectAll(Integer.toString(maphieunhap));
        ArrayList<ChiTietPhieuDTO> result = new ArrayList<>();
        for (ChiTietPhieuDTO i : arr) {
            result.add(i);
        }
        return result;
    }

    public boolean add(PhieuNhapDTO phieu, ArrayList<ChiTietPhieuNhapDTO> ctPhieu, HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> chitietsanpham) {
        boolean check = phieunhapDAO.insert(phieu) != 0;
        if (check) {
            check = ctPhieuNhapDAO.insert(ctPhieu) != 0;
            check = chitietsanphamDAO.insert_mutiple(convertHashMapToArray(chitietsanpham)) != 0;
            //cap nhat gia nhap moi vao phienbansanpham
            PhienBanSanPhamBUS phienbanBUS = new PhienBanSanPhamBUS();
            for (ChiTietPhieuNhapDTO ctpn: ctPhieu) {
                PhienBanSanPhamDTO phienban = phienbanBUS.getByMaPhienBan(ctpn.getMaphienbansp());
                phienban.setGianhap(ctpn.getDongia());
                phienban.setGiaxuat((int) Math.round(ctpn.getDongia() * 1.2)); //!!!!
                phienbanBUS.update(phienban);
            }
        }
        return check;
    }

    public ChiTietPhieuNhapDTO findCT(ArrayList<ChiTietPhieuNhapDTO> ctphieu, int mapb) {
        ChiTietPhieuNhapDTO p = null;
        int i = 0;
        while (i < ctphieu.size() && p == null) {
            if (ctphieu.get(i).getMaphienbansp() == mapb) {
                p = ctphieu.get(i);
            } else {
                i++;
            }
        }
        return p;
    }

    public long getTongTien(ArrayList<ChiTietPhieuNhapDTO> ctphieu) {
        long result = 0;
        for (ChiTietPhieuNhapDTO item : ctphieu) {
            result += item.getDongia() * item.getSoluong();
        }
        return result;
    }

    public ArrayList<PhieuNhapDTO> fillerPhieuNhap(int type, String input, int mancc, int manv, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
        Long price_min = !price_minnn.equals("") ? Long.valueOf(price_minnn) : 0L;
        Long price_max = !price_maxxx.equals("") ? Long.valueOf(price_maxxx) : Long.MAX_VALUE;
        Timestamp time_start = new Timestamp(time_s.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_e.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        Timestamp time_end = new Timestamp(calendar.getTimeInMillis());
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        for (PhieuNhapDTO phieuNhap : getAllList()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuNhap.getMaphieu()).contains(input)
                            || nccBUS.getTenNhaCungCap(phieuNhap.getManhacungcap()).toLowerCase().contains(input)
                            || nvBUS.getNameById(phieuNhap.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuNhap.getMaphieu()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (nccBUS.getTenNhaCungCap(phieuNhap.getManhacungcap()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieuNhap.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
            }

            if (match
                    && (manv == 0 || phieuNhap.getManguoitao() == manv) && (mancc == 0 || phieuNhap.getManhacungcap() == mancc)
                    && (phieuNhap.getThoigiantao().compareTo(time_start) >= 0)
                    && (phieuNhap.getThoigiantao().compareTo(time_end) <= 0)
                    && phieuNhap.getTongTien() >= price_min
                    && phieuNhap.getTongTien() <= price_max) {
                result.add(phieuNhap);
            }
        }

        return result;
    }

    public boolean checkCancelPn(int maphieu) {
        return phieunhapDAO.checkCancelPn(maphieu);
    }

    public int cancelPhieuNhap(int maphieu) {
        return phieunhapDAO.cancelPhieuNhap(maphieu);
    }

}
