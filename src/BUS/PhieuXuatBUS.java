package BUS;

import DAO.ChiTietPhieuXuatDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.PhieuXuatDAO;
import DTO.ChiTietPhieuDTO;
import DTO.PhieuXuatDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author robot
 */
public class PhieuXuatBUS {

    private final PhieuXuatDAO phieuXuatDAO = PhieuXuatDAO.getInstance();

    private final ChiTietPhieuXuatDAO chiTietPhieuXuatDAO = ChiTietPhieuXuatDAO.getInstance();
    private final ChiTietSanPhamDAO chiTietSanPhamDAO = ChiTietSanPhamDAO.getInstance();
    private final ArrayList<PhieuXuatDTO> listPhieuXuat;

    NhanVienBUS nvBUS = new NhanVienBUS();
    KhachHangBUS khBUS = new KhachHangBUS();

    public PhieuXuatBUS() {
        this.listPhieuXuat = phieuXuatDAO.selectAll();
    }

    public ArrayList<PhieuXuatDTO> getAll() {
        return this.listPhieuXuat;
    }

    public PhieuXuatDTO getSelect(int index) {
        return listPhieuXuat.get(index);
    }

    public void cancel(int px) {
        phieuXuatDAO.cancel(px);
    }

    public void remove(int px) {
        listPhieuXuat.remove(px);
    }

    public void insert(PhieuXuatDTO px, ArrayList<ChiTietPhieuDTO> ct) {
        System.out.println("Bus insert ma phieu xuat: " + px.getMaphieu());
        phieuXuatDAO.insert(px);
        for (ChiTietPhieuDTO ctp : ct) {
            System.out.println("Bus insert ma phieu xuat: " + ctp.getMaphieu());
            System.out.println("Bus insert ma phien ban: " + ctp.getMaphienbansp());
            System.out.println("Bus insert so luong: " + ctp.getSoluong());
            System.out.println("Bus insert don gia: " + ctp.getDongia());
        }
        chiTietPhieuXuatDAO.insert(ct);
    }

    public ArrayList<ChiTietPhieuDTO> selectCTP(int maphieu) {
        return chiTietPhieuXuatDAO.selectAll(Integer.toString(maphieu));
    }

    public ArrayList<PhieuXuatDTO> fillerPhieuXuat(int type, String input, int makh, int manv, Date time_s, Date time_e,
            String price_minnn, String price_maxxx) {
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

        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        for (PhieuXuatDTO phieuXuat : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuXuat.getMaphieu()).contains(input)
                            || khBUS.getTenKhachHang(phieuXuat.getMakh()).toLowerCase().contains(input)
                            || nvBUS.getNameById(phieuXuat.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuXuat.getMaphieu()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (khBUS.getTenKhachHang(phieuXuat.getMakh()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieuXuat.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
            }

            if (match
                    && (manv == 0 || phieuXuat.getManguoitao() == manv) && (makh == 0 || phieuXuat.getMakh() == makh)
                    && (phieuXuat.getThoigiantao().compareTo(time_start) >= 0)
                    && (phieuXuat.getThoigiantao().compareTo(time_end) <= 0)
                    && phieuXuat.getTongTien() >= price_min
                    && phieuXuat.getTongTien() <= price_max) {
                result.add(phieuXuat);
            }
        }

        return result;
    }

    public PhieuXuatDTO getPhieuXuat(int maphieuxuat) {
        return phieuXuatDAO.selectById(maphieuxuat+"");
    }
}
