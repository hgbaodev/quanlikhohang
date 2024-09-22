package DAO;

import DTO.ChiTietPhieuDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhieuXuatDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhieuXuatDAO implements DAOinterface<PhieuXuatDTO> {
    
    public static PhieuXuatDAO getInstance(){
        return new PhieuXuatDAO();
    }

    @Override
    public int insert(PhieuXuatDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `phieuxuat`(`tongtien`, `nguoitaophieuxuat`, `makh`, `trangthai`) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            // pst.setInt(1, t.getMaphieu());
            // pst.setInt(2, (int) t.getTongTien());
            // pst.setInt(3, t.getManguoitao());
            // pst.setInt(4, t.getMakh());
            // pst.setInt(5, t.getTrangthai());
            pst.setLong(1, t.getTongTien());
            pst.setInt(2, t.getManguoitao());
            pst.setInt(3, t.getMakh());
            pst.setInt(4, t.getTrangthai());

            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(PhieuXuatDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `phieuxuat` SET `thoigian`=?,`manhacungcap`=?,`tongtien`=?,`trangthai`=? WHERE `maphieuxuat`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setTimestamp(1, t.getThoigiantao());
            pst.setLong(3, t.getTongTien());
            pst.setInt(4, t.getTrangthai());
            pst.setInt(5, t.getMaphieu());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE phieuxuat SET trangthai = 0 WHERE maphieuxuat = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    

    @Override
    public ArrayList<PhieuXuatDTO> selectAll() {
        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieuxuat ORDER BY maphieuxuat DESC";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieu = rs.getInt("maphieuxuat");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int makh = rs.getInt("makh");
                int nguoitao = rs.getInt("nguoitaophieuxuat");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                PhieuXuatDTO phieuxuat = new PhieuXuatDTO(makh, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
                result.add(phieuxuat);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public PhieuXuatDTO selectById(String t) {
        PhieuXuatDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieuxuat WHERE maphieuxuat=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieu = rs.getInt("maphieuxuat");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int makh = rs.getInt("makh");
                int nguoitao = rs.getInt("nguoitaophieuxuat");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                result = new PhieuXuatDTO(makh, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public PhieuXuatDTO cancel(int phieu) {
        PhieuXuatDTO result = null;
        try {
            ArrayList<ChiTietSanPhamDTO> chitietsanpham = ChiTietSanPhamDAO.getInstance().selectAllByMaPhieuXuat(phieu);
            ArrayList<ChiTietPhieuDTO> chitietphieu = ChiTietPhieuXuatDAO.getInstance().selectAll(phieu+"");
            ChiTietPhieuXuatDAO.getInstance().reset(chitietphieu);
            for (ChiTietSanPhamDTO chiTietSanPhamDTO : chitietsanpham) {
                ChiTietSanPhamDAO.getInstance().reset(chiTietSanPhamDTO);
            }
            deletePhieu(phieu);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int deletePhieu(int t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM `phieuxuat` WHERE maphieuxuat = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<PhieuXuatDTO> selectAllofKH(int makh) {
        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieuxuat WHERE makh=? ORDER BY maphieuxuat DESC";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, makh);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieu = rs.getInt("maphieuxuat");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int kh = rs.getInt("makh");
                int nguoitao = rs.getInt("nguoitaophieuxuat");
                long tongtien = rs.getLong("tongtien");
                int trangthai = rs.getInt("trangthai");
                PhieuXuatDTO phieuxuat = new PhieuXuatDTO(kh, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
                result.add(phieuxuat);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND TABLE_NAME   = 'phieuxuat'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
