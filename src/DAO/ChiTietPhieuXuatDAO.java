package DAO;

import DTO.ChiTietPhieuDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ChiTietPhieuXuatDAO implements ChiTietInterface<ChiTietPhieuDTO> {

    public static ChiTietPhieuXuatDAO getInstance() {
        return new ChiTietPhieuXuatDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietPhieuDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `ctphieuxuat`(`maphieuxuat`, `maphienbansp`, `soluong`, `dongia`) VALUES (?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaphieu());
                pst.setInt(2, t.get(i).getMaphienbansp());
                pst.setInt(3, t.get(i).getSoluong());
                pst.setInt(4, t.get(i).getDongia());
                result = pst.executeUpdate();

                int soluong = -(t.get(i).getSoluong());
                int change = PhienBanSanPhamDAO.getInstance().updateSoLuongTon(t.get(i).getMaphienbansp(), soluong);
                
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int reset(ArrayList<ChiTietPhieuDTO> t){
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
        PhienBanSanPhamDAO.getInstance().updateSoLuongTon(t.get(i).getMaphienbansp(), +(t.get(i).getSoluong()));
        delete(t.get(i).getMaphieu()+"");
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM ctphieuxuat WHERE maphieuxuat = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ArrayList<ChiTietPhieuDTO> t, String pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuDTO> selectAll(String t) {
        ArrayList<ChiTietPhieuDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieuxuat WHERE maphieuxuat = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieuxuat");
                int maphienbansp = rs.getInt("maphienbansp");
                int dongia = rs.getInt("dongia");
                int soluong = rs.getInt("soluong");
                ChiTietPhieuDTO ctphieu = new ChiTietPhieuDTO(maphieu, maphienbansp, soluong, dongia);
                result.add(ctphieu);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

}
