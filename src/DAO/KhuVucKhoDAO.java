/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Statement;

import DTO.KhuVucKhoDTO;

public class KhuVucKhoDAO implements DAOinterface<KhuVucKhoDTO> {

    public static KhuVucKhoDAO getInstance() {//no recommended
        return new KhuVucKhoDAO();
    }

    @Override
    public int insert(KhuVucKhoDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `khuvuckho`(`tenkhuvuc`,`ghichu`,`trangthai`) VALUES (?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            // pst.setInt(1, t.getMakhuvuc());
            // pst.setString(2, t.getTenkhuvuc());
            // pst.setString(3, t.getGhichu());

            pst.setString(1, t.getTenkhuvuc());
            pst.setString(2, t.getGhichu());
            
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhuVucKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(KhuVucKhoDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `khuvuckho` SET `tenkhuvuc`=?,`ghichu`=? WHERE `makhuvuc`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenkhuvuc());
            pst.setString(2, t.getGhichu());
            pst.setInt(3, t.getMakhuvuc());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhuVucKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE khuvuckho SET trangthai = 0 WHERE  makhuvuc = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhuVucKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<KhuVucKhoDTO> selectAll() {
        ArrayList<KhuVucKhoDTO> result = new ArrayList<KhuVucKhoDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM khuvuckho WHERE trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int makhuvuc = rs.getInt("makhuvuc");
                String tenkhuvuc = rs.getString("tenkhuvuc");
                String ghichu = rs.getString("ghichu");
                KhuVucKhoDTO kvk = new KhuVucKhoDTO(makhuvuc, tenkhuvuc, ghichu);
                result.add(kvk);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public KhuVucKhoDTO selectById(String t) {
        KhuVucKhoDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM khuvuckho WHERE makhuvuc=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int makhuvuc = rs.getInt("makhuvuc");
                String tenkhuvuc = rs.getString("tenkhuvuc");
                String ghichu = rs.getString("ghichu");
                result = new KhuVucKhoDTO(makhuvuc, tenkhuvuc, ghichu);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND   TABLE_NAME   = 'khuvuckho'";
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
            Logger.getLogger(KhuVucKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
