package DAO;

import DTO.NhanVienDTO;
import config.JDBCUtil;
import DTO.TaiKhoanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robot
 */
public class TaiKhoanDAO implements DAOinterface<TaiKhoanDTO>{
    
    public static TaiKhoanDAO getInstance(){
        return new TaiKhoanDAO();
    }

    @Override
    public int insert(TaiKhoanDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `taikhoan`(`manv`,`tendangnhap`,`matkhau`,`manhomquyen`,`trangthai`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getManv());
            pst.setString(2, t.getUsername());
            pst.setString(3, t.getMatkhau());
            pst.setInt(4, t.getManhomquyen());
            pst.setInt(5, t.getTrangthai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(TaiKhoanDTO t) {
          int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `taikhoan` SET `tendangnhap`=?,`trangthai`=?,`manhomquyen`=? WHERE manv=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getUsername());
            pst.setInt(2, t.getTrangthai());
            pst.setInt(3, t.getManhomquyen());
            pst.setInt(4, t.getManv());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public void updatePass(String email, String password){
        int result;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE taikhoan tk join nhanvien nv on tk.manv=nv.manv SET `matkhau`=? WHERE email=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, password);
            pst.setString(2, email);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TaiKhoanDTO selectByEmail(String t) {
        TaiKhoanDTO tk = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan tk join nhanvien nv on tk.manv=nv.manv where nv.email = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                tk = new TaiKhoanDTO(manv, tendangnhap, matkhau, manhomquyen, trangthai);
                return tk;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception           
        }
        return tk;
    }
    
    public void sendOpt(String email, String opt){
        int result;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE taikhoan tk join nhanvien nv on tk.manv=nv.manv SET `otp`=? WHERE email=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, opt);
            pst.setString(2, email);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkOtp(String email, String otp){
        boolean check = false;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan tk join nhanvien nv on tk.manv=nv.manv where nv.email = ? and tk.otp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, otp);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                check = true;
                return check;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return check;
    }

    @Override
    public int delete(String t) {
         int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `taikhoan` SET `trangthai`='-1' where manv = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t));
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<TaiKhoanDTO> selectAll() {
        ArrayList<TaiKhoanDTO> result = new ArrayList<TaiKhoanDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE trangthai = '0' OR trangthai = '1'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int manv = rs.getInt("manv");
                String username = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                TaiKhoanDTO tk = new TaiKhoanDTO(manv, username, matkhau, manhomquyen, trangthai);
                result.add(tk);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public TaiKhoanDTO selectById(String t) {
        TaiKhoanDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE manv=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                TaiKhoanDTO tk = new TaiKhoanDTO(manv, tendangnhap, matkhau, manhomquyen, trangthai);
                return result;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public TaiKhoanDTO selectByUser(String t) {
        TaiKhoanDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE tendangnhap=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                TaiKhoanDTO tk = new TaiKhoanDTO(manv, tendangnhap, matkhau, manhomquyen, trangthai);
                result = tk;
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND   TABLE_NAME   = 'taikhoan'";
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
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
