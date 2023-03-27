/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.KhachHang;
import DTO.KhoHang;

public class KhoHangDAO implements DAOinterface<KhoHang>{
    public static KhoHangDAO getInstance() {
        return new KhoHangDAO();
    }

    @Override
    public int insert(KhoHang t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `khohang`(`makhohang`, `tenkhohang`, `diachi`,`mota`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMakhohang());
            pst.setString(2, t.getTenkhohang());
            pst.setString(3, t.getDiachi());
            pst.setString(4, t.getMota());

            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhoHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(KhoHang t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `khohang` SET `makhohang`='?',`tenkhohang`='?',`diachi`='?',`mota`='?' WHERE makhohang=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMakhohang());
            pst.setString(2, t.getTenkhohang());
            pst.setString(3, t.getDiachi());
            pst.setString(4, t.getMota());
            pst.setInt(5, t.getMakhohang());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhoHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM khohang WHERE makhohang = '?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhoHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<KhoHang> selectAll() {
         ArrayList<KhoHang> result = new ArrayList<KhoHang>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM khohang";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int makhohang = rs.getInt("makhohang");
                String tenkhohang = rs.getString("tenkhohnag");
                String diachi = rs.getString("diachi");
                String mota = rs.getString("mota");
                
                KhoHang kh = new KhoHang(makhohang, tenkhohang, diachi, mota);
                result.add(kh);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public KhoHang selectById(String t) {
        KhoHang result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM khohang WHERE makhohang='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int makhohang = rs.getInt("makhohang");
                String tenkhohang = rs.getString("tenkhohnag");
                String diachi = rs.getString("diachi");
                String mota = rs.getString("mota");
                
                result = new KhoHang(makhohang, tenkhohang, diachi, mota);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
}
