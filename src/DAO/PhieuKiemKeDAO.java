package DAO;

import DTO.PhieuKiemKeDTO;
import DTO.PhieuKiemKeDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robot
 */
public class PhieuKiemKeDAO implements DAOinterface<PhieuKiemKeDTO>{
    
    public static PhieuKiemKeDAO getInstance(){
        return new PhieuKiemKeDAO();
    }

    @Override
    public int insert(PhieuKiemKeDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `phieukiemke`(`nguoitaophieukiemke`) VALUES (?)";
            PreparedStatement pst = con.prepareStatement(sql);
            // pst.setInt(1, t.getMaphieukiemke());
            pst.setInt(1, t.getNguoitao());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(PhieuKiemKeDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String maphieu) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM phieukiemke WHERE maphieu = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maphieu);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<PhieuKiemKeDTO> selectAll() {
        ArrayList<PhieuKiemKeDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieukiemke ORDER BY maphieu DESC";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieu");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int manguoitao = rs.getInt("nguoitaophieukiemke");
                PhieuKiemKeDTO phieukiemke = new PhieuKiemKeDTO(maphieu, manguoitao, thoigiantao);
                result.add(phieukiemke);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public PhieuKiemKeDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND TABLE_NAME   = 'phieukiemke'";
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
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
