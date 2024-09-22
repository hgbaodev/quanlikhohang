package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.SanPhamDTO;

public class SanPhamDAO implements DAOinterface<SanPhamDTO> {

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `sanpham`(`tensp`, `hinhanh`, `xuatxu`, `chipxuly`, `dungluongpin`, `kichthuocman`, `hedieuhanh`, `phienbanhdh`, `camerasau`, `cameratruoc`, `thoigianbaohanh`, `thuonghieu`, `khuvuckho`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            // pst.setInt(1, t.getMasp());
            // pst.setString(2, t.getTensp());
            // pst.setString(3, t.getHinhanh());
            // pst.setInt(4, t.getXuatxu());
            // pst.setString(5, t.getChipxuly());
            // pst.setInt(6, t.getDungluongpin());
            // pst.setDouble(7, t.getKichthuocman());
            // pst.setInt(8, t.getHedieuhanh());
            // pst.setInt(9, t.getPhienbanhdh());
            // pst.setString(10, t.getCamerasau());
            // pst.setString(11, t.getCameratruoc());
            // pst.setInt(12, t.getThoigianbaohanh());
            // pst.setInt(13, t.getThuonghieu());
            // pst.setInt(14, t.getKhuvuckho());
            //****Sua */
            pst.setString(1, t.getTensp());
            pst.setString(2, t.getHinhanh());
            pst.setInt(3, t.getXuatxu());
            pst.setString(4, t.getChipxuly());
            pst.setInt(5, t.getDungluongpin());
            pst.setDouble(6, t.getKichthuocman());
            pst.setInt(7, t.getHedieuhanh());
            pst.setInt(8, t.getPhienbanhdh());
            pst.setString(9, t.getCamerasau());
            pst.setString(10, t.getCameratruoc());
            pst.setInt(11, t.getThoigianbaohanh());
            pst.setInt(12, t.getThuonghieu());
            pst.setInt(13, t.getKhuvuckho());
            //****Sua */
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(SanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `tensp`=?,`hinhanh`=?,`xuatxu`=?,`chipxuly`=?,`dungluongpin`=?,`kichthuocman`=?,`hedieuhanh`=?,`phienbanhdh`=?,`camerasau`=?,`cameratruoc`=?,`thoigianbaohanh`=?,`thuonghieu`=?,`khuvuckho`=? WHERE `masp`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTensp());
            pst.setString(2, t.getHinhanh());
            pst.setInt(3, t.getXuatxu());
            pst.setString(4, t.getChipxuly());
            pst.setInt(5, t.getDungluongpin());
            pst.setDouble(6, t.getKichthuocman());
            pst.setInt(7, t.getHedieuhanh());
            pst.setInt(8, t.getPhienbanhdh());
            pst.setString(9, t.getCamerasau());
            pst.setString(10, t.getCameratruoc());
            pst.setInt(11, t.getThoigianbaohanh());
            pst.setInt(12, t.getThuonghieu());
            pst.setInt(13, t.getKhuvuckho());
            pst.setInt(14, t.getMasp());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `trangthai`=0 WHERE masp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE `trangthai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masp");
                String tendm = rs.getString("tensp");
                String hinhanh = rs.getString("hinhanh");
                int xuatxu = rs.getInt("xuatxu");
                String chipxuly = rs.getString("chipxuly");
                int dungluongpin = rs.getInt("dungluongpin");
                double kichthuocman = rs.getDouble("kichthuocman");
                int hedieuhanh = rs.getInt("hedieuhanh");
                int phienbanhdh = rs.getInt("phienbanhdh");
                String camerasau = rs.getString("camerasau");
                String cameratruoc = rs.getString("cameratruoc");
                int thoigianbaohanh = rs.getInt("thoigianbaohanh");
                int thuonghieu = rs.getInt("thuonghieu");
                int khuvuckho = rs.getInt("khuvuckho");
                int soluongton = rs.getInt("soluongton");
                SanPhamDTO sp = new SanPhamDTO(madm, tendm, hinhanh, xuatxu, chipxuly, dungluongpin, kichthuocman, hedieuhanh, phienbanhdh, camerasau, cameratruoc, thoigianbaohanh, thuonghieu, khuvuckho, soluongton);
                result.add(sp);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public SanPhamDTO selectById(String t) {
        SanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE masp=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masp");
                String tendm = rs.getString("tensp");
                String hinhanh = rs.getString("hinhanh");
                int xuatxu = rs.getInt("xuatxu");
                String chipxuly = rs.getString("chipxuly");
                int dungluongpin = rs.getInt("dungluongpin");
                double kichthuocman = rs.getDouble("kichthuocman");
                int hedieuhanh = rs.getInt("hedieuhanh");
                int phienbanhdh = rs.getInt("phienbanhdh");
                String camerasau = rs.getString("camerasau");
                String cameratruoc = rs.getString("cameratruoc");
                int thoigianbaohanh = rs.getInt("thoigianbaohanh");
                int thuonghieu = rs.getInt("thuonghieu");
                int khuvuckho = rs.getInt("khuvuckho");
                int soluongton = rs.getInt("soluongton");
                result = new SanPhamDTO(madm, tendm, hinhanh, xuatxu, chipxuly, dungluongpin, kichthuocman, hedieuhanh, phienbanhdh, camerasau, cameratruoc, thoigianbaohanh, thuonghieu, khuvuckho, soluongton);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
    public SanPhamDTO selectByPhienBan(String t) {
        SanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham sp join phienbansanpham pb on sp.masp=pb.masp WHERE maphienbansp=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masp");
                String tendm = rs.getString("tensp");
                String hinhanh = rs.getString("hinhanh");
                int xuatxu = rs.getInt("xuatxu");
                String chipxuly = rs.getString("chipxuly");
                int dungluongpin = rs.getInt("dungluongpin");
                double kichthuocman = rs.getDouble("kichthuocman");
                int hedieuhanh = rs.getInt("hedieuhanh");
                int phienbanhdh = rs.getInt("phienbanhdh");
                String camerasau = rs.getString("camerasau");
                String cameratruoc = rs.getString("cameratruoc");
                int thoigianbaohanh = rs.getInt("thoigianbaohanh");
                int thuonghieu = rs.getInt("thuonghieu");
                int khuvuckho = rs.getInt("khuvuckho");
                int soluongton = rs.getInt("soluongton");
                result = new SanPhamDTO(madm, tendm, hinhanh, xuatxu, chipxuly, dungluongpin, kichthuocman, hedieuhanh, phienbanhdh, camerasau, cameratruoc, thoigianbaohanh, thuonghieu, khuvuckho, soluongton);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND   TABLE_NAME   = 'sanpham'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateSoLuongTon(int masp, int soluong) {
        int quantity_current = this.selectById(Integer.toString(masp)).getSoluongton();
        int result = 0;
        int quantity_change = quantity_current + soluong;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `soluongton`=? WHERE masp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, quantity_change);
            pst.setInt(2, masp);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
