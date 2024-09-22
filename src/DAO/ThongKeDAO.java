/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeNhaCungCapDTO;
import DTO.ThongKe.ThongKeTheoThangDTO;
import DTO.ThongKe.ThongKeTonKhoDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import config.JDBCUtil;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeDAO {

    public static ThongKeDAO getInstance() {
        return new ThongKeDAO();
    }

    public static HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> getThongKeTonKho(String text, Date timeStart, Date timeEnd) {
        HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> result = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                         WITH nhap AS (
                           SELECT maphienbansp, SUM(soluong) AS sl_nhap
                           FROM ctphieunhap
                           JOIN phieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap
                           WHERE thoigian BETWEEN ? AND ?
                           GROUP BY maphienbansp
                         ),
                         xuat AS (
                           SELECT maphienbansp, SUM(soluong) AS sl_xuat
                           FROM ctphieuxuat
                           JOIN phieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                           WHERE thoigian BETWEEN ? AND ?
                           GROUP BY maphienbansp
                         ),
                         nhap_dau AS (
                           SELECT ctphieunhap.maphienbansp, SUM(ctphieunhap.soluong) AS sl_nhap_dau
                           FROM phieunhap
                           JOIN ctphieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap
                           WHERE phieunhap.thoigian < ?
                           GROUP BY ctphieunhap.maphienbansp
                         ),
                         xuat_dau AS (
                           SELECT ctphieuxuat.maphienbansp, SUM(ctphieuxuat.soluong) AS sl_xuat_dau
                           FROM phieuxuat
                           JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                           WHERE phieuxuat.thoigian < ?
                           GROUP BY ctphieuxuat.maphienbansp
                         ),
                         dau_ky AS (
                           SELECT
                             phienbansanpham.maphienbansp,
                             COALESCE(nhap_dau.sl_nhap_dau, 0) - COALESCE(xuat_dau.sl_xuat_dau, 0) AS soluongdauky
                           FROM phienbansanpham
                           LEFT JOIN nhap_dau ON phienbansanpham.maphienbansp = nhap_dau.maphienbansp
                           LEFT JOIN xuat_dau ON phienbansanpham.maphienbansp = xuat_dau.maphienbansp
                         ),
                         temp_table AS (
                         SELECT sanpham.masp, phienbansanpham.maphienbansp, sanpham.tensp, dau_ky.  , COALESCE(nhap.sl_nhap, 0) AS soluongnhap, COALESCE(xuat.sl_xuat, 0)  AS soluongxuat, (dau_ky.soluongdauky + COALESCE(nhap.sl_nhap, 0) - COALESCE(xuat.sl_xuat, 0)) AS soluongcuoiky, kichthuocram, kichthuocrom, tenmau
                         FROM dau_ky
                         LEFT JOIN nhap ON dau_ky.maphienbansp = nhap.maphienbansp
                         LEFT JOIN xuat ON dau_ky.maphienbansp = xuat.maphienbansp
                         JOIN phienbansanpham ON phienbansanpham.maphienbansp = dau_ky.maphienbansp
                         JOIN sanpham ON phienbansanpham.masp = sanpham.masp
                         JOIN dungluongram ON phienbansanpham.ram = dungluongram.madlram
                         JOIN dungluongrom ON phienbansanpham.rom = dungluongrom.madlrom
                         JOIN mausac ON phienbansanpham.mausac = mausac.mamau
                         )
                         SELECT * FROM temp_table
                         WHERE tensp LIKE ? OR masp LIKE ?
                         ORDER BY masp;""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setTimestamp(3, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(4, new Timestamp(calendar.getTimeInMillis()));
            pst.setTimestamp(5, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(6, new Timestamp(timeStart.getTime()));
            pst.setString(7, "%" + text + "%");
            pst.setString(8, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int masp = rs.getInt("masp");
                int maphienbansp = rs.getInt("maphienbansp");
                String tensp = rs.getString("tensp");
                int soluongdauky = rs.getInt("soluongdauky");
                int soluongnhap = rs.getInt("soluongnhap");
                int soluongxuat = rs.getInt("soluongxuat");
                int soluongcuoiky = rs.getInt("soluongcuoiky");
                int ram = rs.getInt("kichthuocram");
                int rom = rs.getInt("kichthuocrom");
                String mausac = rs.getString("tenmau");
                ThongKeTonKhoDTO p = new ThongKeTonKhoDTO(masp, maphienbansp, tensp, ram, rom, mausac, soluongdauky, soluongnhap, soluongxuat, soluongcuoiky);
                result.computeIfAbsent(masp, k -> new ArrayList<>()).add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int year_start, int year_end) {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sqlSetStartYear = "SET @start_year = ?;";
            String sqlSetEndYear = "SET @end_year = ?;";
            String sqlSelect = """
                     WITH RECURSIVE years(year) AS (
                       SELECT @start_year
                       UNION ALL
                       SELECT year + 1
                       FROM years
                       WHERE year < @end_year
                     )
                     SELECT 
                       years.year AS nam,
                       COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi, 
                       COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu
                     FROM years
                     LEFT JOIN phieuxuat ON YEAR(phieuxuat.thoigian) = years.year
                     LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                     LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp
                     LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp
                     GROUP BY years.year
                     ORDER BY years.year;""";
            PreparedStatement pstStartYear = con.prepareStatement(sqlSetStartYear);
            PreparedStatement pstEndYear = con.prepareStatement(sqlSetEndYear);
            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);

            pstStartYear.setInt(1, year_start);
            pstEndYear.setInt(1, year_end);

            pstStartYear.execute();
            pstEndYear.execute();

            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                int thoigian = rs.getInt("nam");
                Long chiphi = rs.getLong("chiphi");
                Long doanhthu = rs.getLong("doanhthu");
                ThongKeDoanhThuDTO x = new ThongKeDoanhThuDTO(thoigian, chiphi, doanhthu, doanhthu - chiphi);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeKhachHangDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                          WITH kh AS (
                         SELECT khachhang.makh, khachhang.tenkhachhang , COUNT(phieuxuat.maphieuxuat ) AS tongsophieu, SUM(phieuxuat.tongtien) AS tongsotien
                         FROM khachhang
                         JOIN phieuxuat ON khachhang.makh = phieuxuat.makh
                         WHERE phieuxuat.thoigian BETWEEN ? AND ? 
                         GROUP BY khachhang.makh, khachhang.tenkhachhang)
                         SELECT makh,tenkhachhang,COALESCE(kh.tongsophieu, 0) AS soluong ,COALESCE(kh.tongsotien, 0) AS total 
                         FROM kh WHERE tenkhachhang LIKE ? OR makh LIKE ?""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int makh = rs.getInt("makh");
                String tenkh = rs.getString("tenkhachhang");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total");
                ThongKeKhachHangDTO x = new ThongKeKhachHangDTO(makh, tenkh, soluong, tongtien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<ThongKeNhaCungCapDTO> getThongKeNCC(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeNhaCungCapDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                          WITH ncc AS (
                         SELECT nhacungcap.manhacungcap, nhacungcap.tennhacungcap , COUNT(phieunhap.maphieunhap ) AS tongsophieu, SUM(phieunhap.tongtien) AS tongsotien
                         FROM nhacungcap
                         JOIN phieunhap ON nhacungcap.manhacungcap = phieunhap.manhacungcap
                         WHERE phieunhap.thoigian BETWEEN ? AND ? 
                         GROUP BY nhacungcap.manhacungcap, nhacungcap.tennhacungcap)
                         SELECT manhacungcap,tennhacungcap,COALESCE(ncc.tongsophieu, 0) AS soluong ,COALESCE(ncc.tongsotien, 0) AS total 
                         FROM ncc WHERE tennhacungcap LIKE ? OR manhacungcap LIKE ?""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int mancc = rs.getInt("manhacungcap");
                String tenncc = rs.getString("tennhacungcap");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total");
                ThongKeNhaCungCapDTO x = new ThongKeNhaCungCapDTO(mancc, tenncc, soluong, tongtien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam) {
        ArrayList<ThongKeTheoThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT months.month AS thang, \n"
                    + "       COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi,\n"
                    + "       COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu\n"
                    + "FROM (\n"
                    + "       SELECT 1 AS month\n"
                    + "       UNION ALL SELECT 2\n"
                    + "       UNION ALL SELECT 3\n"
                    + "       UNION ALL SELECT 4\n"
                    + "       UNION ALL SELECT 5\n"
                    + "       UNION ALL SELECT 6\n"
                    + "       UNION ALL SELECT 7\n"
                    + "       UNION ALL SELECT 8\n"
                    + "       UNION ALL SELECT 9\n"
                    + "       UNION ALL SELECT 10\n"
                    + "       UNION ALL SELECT 11\n"
                    + "       UNION ALL SELECT 12\n"
                    + "     ) AS months\n"
                    + "LEFT JOIN phieuxuat ON MONTH(phieuxuat.thoigian) = months.month AND YEAR(phieuxuat.thoigian) = ? \n"
                    + "LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
                    + "LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp\n"
                    + "LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp\n"
                    + "GROUP BY months.month\n"
                    + "ORDER BY months.month;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, nam);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int thang = rs.getInt("thang");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTheoThangDTO thongke = new ThongKeTheoThangDTO(thang, chiphi, doanhthu, loinhuan);
                result.add(thongke);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            String ngayString = nam + "-" + thang + "-" + "01";
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT \n"
                    + "  dates.date AS ngay, \n"
                    + "  COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi, \n"
                    + "  COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu\n"
                    + "FROM (\n"
                    + "  SELECT DATE('" + ngayString + "') + INTERVAL c.number DAY AS date\n"
                    + "  FROM (\n"
                    + "    SELECT 0 AS number\n"
                    + "    UNION ALL SELECT 1\n"
                    + "    UNION ALL SELECT 2\n"
                    + "    UNION ALL SELECT 3\n"
                    + "    UNION ALL SELECT 4\n"
                    + "    UNION ALL SELECT 5\n"
                    + "    UNION ALL SELECT 6\n"
                    + "    UNION ALL SELECT 7\n"
                    + "    UNION ALL SELECT 8\n"
                    + "    UNION ALL SELECT 9\n"
                    + "    UNION ALL SELECT 10\n"
                    + "    UNION ALL SELECT 11\n"
                    + "    UNION ALL SELECT 12\n"
                    + "    UNION ALL SELECT 13\n"
                    + "    UNION ALL SELECT 14\n"
                    + "    UNION ALL SELECT 15\n"
                    + "    UNION ALL SELECT 16\n"
                    + "    UNION ALL SELECT 17\n"
                    + "    UNION ALL SELECT 18\n"
                    + "    UNION ALL SELECT 19\n"
                    + "    UNION ALL SELECT 20\n"
                    + "    UNION ALL SELECT 21\n"
                    + "    UNION ALL SELECT 22\n"
                    + "    UNION ALL SELECT 23\n"
                    + "    UNION ALL SELECT 24\n"
                    + "    UNION ALL SELECT 25\n"
                    + "    UNION ALL SELECT 26\n"
                    + "    UNION ALL SELECT 27\n"
                    + "    UNION ALL SELECT 28\n"
                    + "    UNION ALL SELECT 29\n"
                    + "    UNION ALL SELECT 30\n"
                    + "  ) AS c\n"
                    + "  WHERE DATE('" + ngayString + "') + INTERVAL c.number DAY <= LAST_DAY('" + ngayString + "')\n"
                    + ") AS dates\n"
                    + "LEFT JOIN phieuxuat ON DATE(phieuxuat.thoigian) = dates.date\n"
                    + "LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
                    + "LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp\n"
                    + "LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp\n"
                    + "GROUP BY dates.date\n"
                    + "ORDER BY dates.date;";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKe7NgayGanNhat() {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                         WITH RECURSIVE dates(date) AS (
                           SELECT DATE_SUB(CURDATE(), INTERVAL 7 DAY)
                           UNION ALL
                           SELECT DATE_ADD(date, INTERVAL 1 DAY)
                           FROM dates
                           WHERE date < CURDATE()
                         )
                         SELECT 
                           dates.date AS ngay,
                           COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu,
                           COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi
                         FROM dates
                         LEFT JOIN phieuxuat ON DATE(phieuxuat.thoigian) = dates.date
                         LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                         LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp
                         LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp
                         GROUP BY dates.date
                         ORDER BY dates.date;""";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTuNgayDenNgay(String star, String end) {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String setStar = "SET @start_date = '" + star + "'";
            String setEnd = "SET @end_date = '" + end + "'  ;";
            String sqlSelect = "SELECT \n"
                    + "  dates.date AS ngay, \n"
                    + "  COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi, \n"
                    + "  COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu\n"
                    + "FROM (\n"
                    + "  SELECT DATE_ADD(@start_date, INTERVAL c.number DAY) AS date\n"
                    + "  FROM (\n"
                    + "    SELECT a.number + b.number * 31 AS number\n"
                    + "    FROM (\n"
                    + "      SELECT 0 AS number\n"
                    + "      UNION ALL SELECT 1\n"
                    + "      UNION ALL SELECT 2\n"
                    + "      UNION ALL SELECT 3\n"
                    + "      UNION ALL SELECT 4\n"
                    + "      UNION ALL SELECT 5\n"
                    + "      UNION ALL SELECT 6\n"
                    + "      UNION ALL SELECT 7\n"
                    + "      UNION ALL SELECT 8\n"
                    + "      UNION ALL SELECT 9\n"
                    + "      UNION ALL SELECT 10\n"
                    + "      UNION ALL SELECT 11\n"
                    + "      UNION ALL SELECT 12\n"
                    + "      UNION ALL SELECT 13\n"
                    + "      UNION ALL SELECT 14\n"
                    + "      UNION ALL SELECT 15\n"
                    + "      UNION ALL SELECT 16\n"
                    + "      UNION ALL SELECT 17\n"
                    + "      UNION ALL SELECT 18\n"
                    + "      UNION ALL SELECT 19\n"
                    + "      UNION ALL SELECT 20\n"
                    + "      UNION ALL SELECT 21\n"
                    + "      UNION ALL SELECT 22\n"
                    + "      UNION ALL SELECT 23\n"
                    + "      UNION ALL SELECT 24\n"
                    + "      UNION ALL SELECT 25\n"
                    + "      UNION ALL SELECT 26\n"
                    + "      UNION ALL SELECT 27\n"
                    + "      UNION ALL SELECT 28\n"
                    + "      UNION ALL SELECT 29\n"
                    + "      UNION ALL SELECT 30\n"
                    + "    ) AS a\n"
                    + "    CROSS JOIN (\n"
                    + "      SELECT 0 AS number\n"
                    + "      UNION ALL SELECT 1\n"
                    + "      UNION ALL SELECT 2\n"
                    + "      UNION ALL SELECT 3\n"
                    + "      UNION ALL SELECT 4\n"
                    + "      UNION ALL SELECT 5\n"
                    + "      UNION ALL SELECT 6\n"
                    + "      UNION ALL SELECT 7\n"
                    + "      UNION ALL SELECT 8\n"
                    + "      UNION ALL SELECT 9\n"
                    + "      UNION ALL SELECT 10\n"
                    + "    ) AS b\n"
                    + "  ) AS c\n"
                    + "  WHERE DATE_ADD(@start_date, INTERVAL c.number DAY) <= @end_date\n"
                    + ") AS dates\n"
                    + "LEFT JOIN phieuxuat ON DATE(phieuxuat.thoigian) = dates.date\n"
                    + "LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
                    + "LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp\n"
                    + "LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp\n"
                    + "GROUP BY dates.date\n"
                    + "ORDER BY dates.date;";

            PreparedStatement pstStart = con.prepareStatement(setStar);
            PreparedStatement pstEnd = con.prepareStatement(setEnd);
            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);

            pstStart.execute();
            pstEnd.execute();
            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
