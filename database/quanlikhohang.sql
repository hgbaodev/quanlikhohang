-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 07, 2023 at 03:56 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlikhohang`
--

-- --------------------------------------------------------

--
-- Table structure for table `ctkiemke`
--

CREATE TABLE `ctkiemke` (
  `maphieukiemmke` int(11) NOT NULL COMMENT 'Mã phiếu kiểm kê',
  `masanpham` int(11) NOT NULL COMMENT 'Mã sản phẩm',
  `soluong` int(11) NOT NULL,
  `chenhlech` int(11) NOT NULL,
  `ghichu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0,
  `hinhthucnhap` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctphieunhap`
--

INSERT INTO `ctphieunhap` (`maphieunhap`, `maphienbansp`, `soluong`, `dongia`, `hinhthucnhap`) VALUES
(1, 2, 6, 5000000, 0),
(1, 19, 2, 15000000, 0),
(2, 2, 7, 5000000, 0),
(2, 19, 5, 15000000, 0),
(3, 17, 12, 7000000, 0),
(4, 17, 5, 7000000, 0),
(5, 2, 2, 5000000, 0),
(8, 19, 5, 15000000, 0),
(9, 23, 7, 15500000, 0),
(10, 17, 1, 7000000, 1),
(11, 2, 1, 5000000, 1),
(12, 24, 3, 2000000, 0),
(12, 27, 3, 2000000, 0),
(12, 28, 4, 2500000, 0),
(12, 29, 5, 2500000, 0),
(13, 31, 15, 8000000, 0),
(13, 33, 15, 8000000, 0),
(13, 35, 15, 8000000, 0),
(13, 49, 30, 3200000, 0),
(14, 51, 12, 5000000, 0),
(14, 57, 7, 4000000, 0),
(14, 58, 6, 5000000, 0),
(15, 113, 13, 12000000, 0),
(15, 115, 7, 4500000, 0),
(16, 85, 10, 4100000, 0),
(16, 97, 20, 14000000, 0),
(17, 75, 9, 8200000, 0),
(18, 89, 5, 5200000, 0),
(18, 91, 5, 5200000, 0),
(19, 93, 3, 2500000, 0),
(20, 93, 5, 2600000, 0),
(21, 125, 5, 2500000, 0),
(23, 17, 2, 8000000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ctphieuxuat`
--

CREATE TABLE `ctphieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `maphienbansp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctphieuxuat`
--

INSERT INTO `ctphieuxuat` (`maphieuxuat`, `maphienbansp`, `soluong`, `dongia`) VALUES
(2, 19, 1, 16500000),
(3, 19, 3, 16500000),
(4, 17, 5, 7800000),
(5, 17, 4, 7800000),
(6, 2, 4, 5500000),
(7, 17, 1, 7800000),
(8, 17, 3, 7800000),
(9, 2, 4, 5500000),
(10, 17, 2, 7800000),
(11, 19, 3, 16500000),
(12, 2, 2, 5500000),
(12, 19, 3, 16500000),
(13, 2, 1, 5500000),
(14, 113, 3, 14000000),
(15, 115, 2, 5500000),
(16, 91, 2, 6400000),
(16, 113, 3, 14000000),
(17, 89, 5, 6400000),
(17, 91, 3, 6400000),
(18, 75, 3, 9500000),
(19, 23, 2, 17000000),
(19, 27, 1, 2890000),
(20, 31, 2, 9000000),
(21, 51, 3, 5790000),
(22, 35, 4, 9000000),
(23, 19, 1, 16500000),
(24, 49, 3, 4400000),
(25, 17, 2, 7800000);

-- --------------------------------------------------------

--
-- Table structure for table `ctquyen`
--

CREATE TABLE `ctquyen` (
  `manhomquyen` int(11) NOT NULL,
  `machucnang` varchar(50) NOT NULL,
  `hanhdong` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctquyen`
--

INSERT INTO `ctquyen` (`manhomquyen`, `machucnang`, `hanhdong`) VALUES
(1, 'khachhang', 'create'),
(1, 'khachhang', 'delete'),
(1, 'khachhang', 'update'),
(1, 'khachhang', 'view'),
(1, 'khuvuckho', 'create'),
(1, 'khuvuckho', 'delete'),
(1, 'khuvuckho', 'update'),
(1, 'khuvuckho', 'view'),
(1, 'nhacungcap', 'create'),
(1, 'nhacungcap', 'delete'),
(1, 'nhacungcap', 'update'),
(1, 'nhacungcap', 'view'),
(1, 'nhanvien', 'create'),
(1, 'nhanvien', 'delete'),
(1, 'nhanvien', 'update'),
(1, 'nhanvien', 'view'),
(1, 'nhaphang', 'create'),
(1, 'nhaphang', 'delete'),
(1, 'nhaphang', 'update'),
(1, 'nhaphang', 'view'),
(1, 'nhomquyen', 'create'),
(1, 'nhomquyen', 'delete'),
(1, 'nhomquyen', 'update'),
(1, 'nhomquyen', 'view'),
(1, 'sanpham', 'create'),
(1, 'sanpham', 'delete'),
(1, 'sanpham', 'update'),
(1, 'sanpham', 'view'),
(1, 'taikhoan', 'create'),
(1, 'taikhoan', 'delete'),
(1, 'taikhoan', 'update'),
(1, 'taikhoan', 'view'),
(1, 'thongke', 'create'),
(1, 'thongke', 'delete'),
(1, 'thongke', 'update'),
(1, 'thongke', 'view'),
(1, 'thuoctinh', 'create'),
(1, 'thuoctinh', 'delete'),
(1, 'thuoctinh', 'update'),
(1, 'thuoctinh', 'view'),
(1, 'xuathang', 'create'),
(1, 'xuathang', 'delete'),
(1, 'xuathang', 'update'),
(1, 'xuathang', 'view'),
(2, 'khuvuckho', 'create'),
(2, 'khuvuckho', 'update'),
(2, 'khuvuckho', 'view'),
(2, 'nhacungcap', 'create'),
(2, 'nhacungcap', 'update'),
(2, 'nhacungcap', 'view'),
(2, 'nhaphang', 'create'),
(2, 'nhaphang', 'update'),
(2, 'nhaphang', 'view'),
(2, 'sanpham', 'create'),
(2, 'sanpham', 'update'),
(2, 'sanpham', 'view'),
(2, 'thuoctinh', 'create'),
(2, 'thuoctinh', 'delete'),
(2, 'thuoctinh', 'update'),
(2, 'thuoctinh', 'view'),
(3, 'khachhang', 'create'),
(3, 'khachhang', 'update'),
(3, 'khachhang', 'view'),
(3, 'sanpham', 'update'),
(3, 'sanpham', 'view'),
(3, 'xuathang', 'create'),
(3, 'xuathang', 'update'),
(3, 'xuathang', 'view'),
(4, 'donvitinh', 'view'),
(4, 'khuvuckho', 'view'),
(4, 'kiemke', 'view'),
(4, 'loaisanpham', 'view'),
(4, 'nhacungcap', 'view'),
(5, 'khachhang', 'view'),
(5, 'khuvuckho', 'view'),
(6, 'khuvuckho', 'view'),
(6, 'kiemke', 'view'),
(6, 'loaisanpham', 'view'),
(6, 'nhacungcap', 'view'),
(6, 'nhanvien', 'view'),
(7, 'loaisanpham', 'create'),
(7, 'nhanvien', 'create'),
(7, 'sanpham', 'create'),
(7, 'xuathang', 'create'),
(8, 'donvitinh', 'view'),
(9, 'khachhang', 'view'),
(9, 'khuvuckho', 'view'),
(10, 'khachhang', 'view'),
(10, 'khuvuckho', 'view'),
(10, 'nhanvien', 'view');

-- --------------------------------------------------------

--
-- Table structure for table `ctsanpham`
--

CREATE TABLE `ctsanpham` (
  `maimei` varchar(255) NOT NULL DEFAULT 'AUTO_INCREMENT' COMMENT 'Mã imei của sản phẩm',
  `maphienbansp` int(11) NOT NULL,
  `maphieunhap` int(11) NOT NULL,
  `maphieuxuat` int(11) DEFAULT NULL,
  `tinhtrang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctsanpham`
--

INSERT INTO `ctsanpham` (`maimei`, `maphienbansp`, `maphieunhap`, `maphieuxuat`, `tinhtrang`) VALUES
('107725056444797', 57, 14, NULL, 1),
('107725056444798', 57, 14, NULL, 1),
('107725056444799', 57, 14, NULL, 1),
('107725056444800', 57, 14, NULL, 1),
('107725056444801', 57, 14, NULL, 1),
('107725056444802', 57, 14, NULL, 1),
('107725056444803', 57, 14, NULL, 1),
('111111111111111', 17, 23, 25, 0),
('111111111111112', 17, 23, 25, 0),
('128680626510768', 27, 12, NULL, 1),
('128680626510769', 27, 12, 19, 0),
('128680626510770', 27, 12, NULL, 1),
('191487469319798', 29, 12, NULL, 1),
('191487469319799', 29, 12, NULL, 1),
('191487469319800', 29, 12, NULL, 1),
('191487469319801', 29, 12, NULL, 1),
('191487469319802', 29, 12, NULL, 1),
('201865493271034', 19, 8, NULL, 1),
('201865493271035', 19, 8, 11, 0),
('201865493271036', 19, 8, 11, 0),
('201865493271037', 19, 8, 11, 0),
('201865493271038', 19, 8, 23, 0),
('209563810276493', 23, 9, NULL, 1),
('209563810276494', 23, 9, NULL, 1),
('209563810276495', 23, 9, 19, 0),
('209563810276496', 23, 9, 19, 0),
('209563810276497', 23, 9, NULL, 1),
('209563810276498', 23, 9, NULL, 1),
('209563810276499', 23, 9, NULL, 1),
('234307273503481', 33, 13, NULL, 1),
('234307273503482', 33, 13, NULL, 1),
('234307273503483', 33, 13, NULL, 1),
('234307273503484', 33, 13, NULL, 1),
('234307273503485', 33, 13, NULL, 1),
('234307273503486', 33, 13, NULL, 1),
('234307273503487', 33, 13, NULL, 1),
('234307273503488', 33, 13, NULL, 1),
('234307273503489', 33, 13, NULL, 1),
('234307273503490', 33, 13, NULL, 1),
('234307273503491', 33, 13, NULL, 1),
('234307273503492', 33, 13, NULL, 1),
('234307273503493', 33, 13, NULL, 1),
('234307273503494', 33, 13, NULL, 1),
('234307273503495', 33, 13, NULL, 1),
('237439786201794', 85, 16, NULL, 1),
('237439786201795', 85, 16, NULL, 1),
('237439786201796', 85, 16, NULL, 1),
('237439786201797', 85, 16, NULL, 1),
('237439786201798', 85, 16, NULL, 1),
('237439786201799', 85, 16, NULL, 1),
('237439786201800', 85, 16, NULL, 1),
('237439786201801', 85, 16, NULL, 1),
('237439786201802', 85, 16, NULL, 1),
('237439786201803', 85, 16, NULL, 1),
('248644019558673', 58, 14, NULL, 1),
('248644019558674', 58, 14, NULL, 1),
('248644019558675', 58, 14, NULL, 1),
('248644019558676', 58, 14, NULL, 1),
('248644019558677', 58, 14, NULL, 1),
('248644019558678', 58, 14, NULL, 1),
('267300933303009', 113, 15, NULL, 1),
('267300933303010', 113, 15, 14, 0),
('267300933303011', 113, 15, 14, 0),
('267300933303012', 113, 15, 14, 0),
('267300933303013', 113, 15, NULL, 1),
('267300933303014', 113, 15, NULL, 1),
('267300933303015', 113, 15, 16, 0),
('267300933303016', 113, 15, 16, 0),
('267300933303017', 113, 15, 16, 0),
('267300933303018', 113, 15, NULL, 1),
('267300933303019', 113, 15, NULL, 1),
('267300933303020', 113, 15, NULL, 1),
('267300933303021', 113, 15, NULL, 1),
('325645285296325', 125, 21, NULL, 1),
('325645285296326', 125, 21, NULL, 1),
('325645285296327', 125, 21, NULL, 1),
('325645285296328', 125, 21, NULL, 1),
('325645285296329', 125, 21, NULL, 1),
('354091067813468', 2, 5, NULL, 1),
('354091067813469', 2, 5, 6, 0),
('355663242747336', 17, 4, 4, 0),
('355663242747337', 17, 4, 4, 0),
('355663242747338', 17, 4, 4, 0),
('355663242747339', 17, 4, 4, 0),
('355663242747340', 17, 4, 4, 0),
('356285038690365', 93, 20, NULL, 1),
('356285038690366', 93, 20, NULL, 1),
('356285038690367', 93, 20, NULL, 1),
('356285038690368', 93, 20, NULL, 1),
('356285038690369', 93, 20, NULL, 1),
('356285077460984', 19, 1, 2, 0),
('356285077460985', 19, 1, 3, 0),
('356285077460989', 19, 2, 3, 0),
('356285077460990', 19, 2, 3, 0),
('356285077460991', 19, 2, 12, 0),
('356285077460992', 19, 2, 12, 0),
('356285077460993', 19, 2, 12, 0),
('356285088460123', 2, 2, 6, 0),
('356285088460124', 2, 2, 6, 0),
('356285088460125', 2, 2, 6, 0),
('356285088460126', 2, 2, NULL, 1),
('356285088460127', 2, 2, NULL, 1),
('356285088460128', 2, 2, 9, 0),
('356285088460129', 2, 2, 9, 0),
('356285088460876', 2, 1, 9, 0),
('356285088460877', 2, 1, 9, 0),
('356285088460878', 2, 1, NULL, 1),
('356285088460879', 2, 1, NULL, 1),
('356285088460880', 2, 1, 12, 0),
('356285088460881', 2, 1, 12, 0),
('356679247460989', 17, 3, 5, 0),
('356679247460990', 17, 3, 5, 0),
('356679247460991', 17, 3, 5, 0),
('356679247460992', 17, 3, NULL, 1),
('356679247460993', 17, 3, 5, 0),
('356679247460994', 17, 3, NULL, 1),
('356679247460995', 17, 3, 8, 0),
('356679247460996', 17, 3, 8, 0),
('356679247460997', 17, 3, 7, 0),
('356679247460998', 17, 3, 8, 0),
('356679247460999', 17, 3, NULL, 1),
('356679247461000', 17, 3, 10, 0),
('427856011841915', 24, 12, NULL, 1),
('427856011841916', 24, 12, NULL, 1),
('427856011841917', 24, 12, NULL, 1),
('493536926712616', 51, 14, NULL, 1),
('493536926712617', 51, 14, 21, 0),
('493536926712618', 51, 14, 21, 0),
('493536926712619', 51, 14, 21, 0),
('493536926712620', 51, 14, NULL, 1),
('493536926712621', 51, 14, NULL, 1),
('493536926712622', 51, 14, NULL, 1),
('493536926712623', 51, 14, NULL, 1),
('493536926712624', 51, 14, NULL, 1),
('493536926712625', 51, 14, NULL, 1),
('493536926712626', 51, 14, NULL, 1),
('493536926712627', 51, 14, NULL, 1),
('514897969548020', 28, 12, NULL, 1),
('514897969548021', 28, 12, NULL, 1),
('514897969548022', 28, 12, NULL, 1),
('514897969548023', 28, 12, NULL, 1),
('578559728952141', 35, 13, NULL, 1),
('578559728952142', 35, 13, NULL, 1),
('578559728952143', 35, 13, NULL, 1),
('578559728952144', 35, 13, 22, 0),
('578559728952145', 35, 13, 22, 0),
('578559728952146', 35, 13, 22, 0),
('578559728952147', 35, 13, 22, 0),
('578559728952148', 35, 13, NULL, 1),
('578559728952149', 35, 13, NULL, 1),
('578559728952150', 35, 13, NULL, 1),
('578559728952151', 35, 13, NULL, 1),
('578559728952152', 35, 13, NULL, 1),
('578559728952153', 35, 13, NULL, 1),
('578559728952154', 35, 13, NULL, 1),
('578559728952155', 35, 13, NULL, 1),
('630481155578246', 97, 16, NULL, 1),
('630481155578247', 97, 16, NULL, 1),
('630481155578248', 97, 16, NULL, 1),
('630481155578249', 97, 16, NULL, 1),
('630481155578250', 97, 16, NULL, 1),
('630481155578251', 97, 16, NULL, 1),
('630481155578252', 97, 16, NULL, 1),
('630481155578253', 97, 16, NULL, 1),
('630481155578254', 97, 16, NULL, 1),
('630481155578255', 97, 16, NULL, 1),
('630481155578256', 97, 16, NULL, 1),
('630481155578257', 97, 16, NULL, 1),
('630481155578258', 97, 16, NULL, 1),
('630481155578259', 97, 16, NULL, 1),
('630481155578260', 97, 16, NULL, 1),
('630481155578261', 97, 16, NULL, 1),
('630481155578262', 97, 16, NULL, 1),
('630481155578263', 97, 16, NULL, 1),
('630481155578264', 97, 16, NULL, 1),
('630481155578265', 97, 16, NULL, 1),
('663695386896779', 115, 15, NULL, 1),
('663695386896780', 115, 15, NULL, 1),
('663695386896781', 115, 15, NULL, 1),
('663695386896782', 115, 15, NULL, 1),
('663695386896783', 115, 15, NULL, 1),
('663695386896784', 115, 15, 15, 0),
('663695386896785', 115, 15, 15, 0),
('692900977366244', 93, 19, NULL, 1),
('692900977366245', 93, 19, NULL, 1),
('692900977366246', 93, 19, NULL, 1),
('692900977366247', 93, 19, NULL, 1),
('692900977366248', 93, 19, NULL, 1),
('692900977366249', 93, 19, NULL, 1),
('845223209939936', 91, 18, 17, 0),
('845223209939937', 91, 18, 17, 0),
('845223209939938', 91, 18, 17, 0),
('845223209939939', 91, 18, 16, 0),
('845223209939940', 91, 18, 16, 0),
('853057665280035', 31, 13, 20, 0),
('853057665280036', 31, 13, 20, 0),
('853057665280037', 31, 13, NULL, 1),
('853057665280038', 31, 13, NULL, 1),
('853057665280039', 31, 13, NULL, 1),
('853057665280040', 31, 13, NULL, 1),
('853057665280041', 31, 13, NULL, 1),
('853057665280042', 31, 13, NULL, 1),
('853057665280043', 31, 13, NULL, 1),
('853057665280044', 31, 13, NULL, 1),
('853057665280045', 31, 13, NULL, 1),
('853057665280046', 31, 13, NULL, 1),
('853057665280047', 31, 13, NULL, 1),
('853057665280048', 31, 13, NULL, 1),
('853057665280049', 31, 13, NULL, 1),
('876068039547345', 75, 17, 18, 0),
('876068039547346', 75, 17, 18, 0),
('876068039547347', 75, 17, 18, 0),
('876068039547348', 75, 17, NULL, 1),
('876068039547349', 75, 17, NULL, 1),
('876068039547350', 75, 17, NULL, 1),
('876068039547351', 75, 17, NULL, 1),
('876068039547352', 75, 17, NULL, 1),
('876068039547353', 75, 17, NULL, 1),
('912609172880156', 17, 10, 10, 0),
('919448001026640', 49, 13, NULL, 1),
('919448001026641', 49, 13, NULL, 1),
('919448001026642', 49, 13, NULL, 1),
('919448001026643', 49, 13, NULL, 1),
('919448001026644', 49, 13, NULL, 1),
('919448001026645', 49, 13, NULL, 1),
('919448001026646', 49, 13, 24, 0),
('919448001026647', 49, 13, 24, 0),
('919448001026648', 49, 13, 24, 0),
('919448001026649', 49, 13, NULL, 1),
('919448001026650', 49, 13, NULL, 1),
('919448001026651', 49, 13, NULL, 1),
('919448001026652', 49, 13, NULL, 1),
('919448001026653', 49, 13, NULL, 1),
('919448001026654', 49, 13, NULL, 1),
('919448001026655', 49, 13, NULL, 1),
('919448001026656', 49, 13, NULL, 1),
('919448001026657', 49, 13, NULL, 1),
('919448001026658', 49, 13, NULL, 1),
('919448001026659', 49, 13, NULL, 1),
('919448001026660', 49, 13, NULL, 1),
('919448001026661', 49, 13, NULL, 1),
('919448001026662', 49, 13, NULL, 1),
('919448001026663', 49, 13, NULL, 1),
('919448001026664', 49, 13, NULL, 1),
('919448001026665', 49, 13, NULL, 1),
('919448001026666', 49, 13, NULL, 1),
('919448001026667', 49, 13, NULL, 1),
('919448001026668', 49, 13, NULL, 1),
('919448001026669', 49, 13, NULL, 1),
('964768426041520', 89, 18, 17, 0),
('964768426041521', 89, 18, 17, 0),
('964768426041522', 89, 18, 17, 0),
('964768426041523', 89, 18, 17, 0),
('964768426041524', 89, 18, 17, 0),
('968080239661041', 2, 11, 13, 0);

-- --------------------------------------------------------

--
-- Table structure for table `danhmucchucnang`
--

CREATE TABLE `danhmucchucnang` (
  `machucnang` varchar(50) NOT NULL,
  `tenchucnang` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `danhmucchucnang`
--

INSERT INTO `danhmucchucnang` (`machucnang`, `tenchucnang`, `trangthai`) VALUES
('khachhang', 'Quản lý khách hàng', 0),
('khuvuckho', 'Quản lý khu vực kho', 0),
('nhacungcap', 'Quản lý nhà cung cấp', 0),
('nhanvien', 'Quản lý nhân viên', 0),
('nhaphang', 'Quản lý nhập hàng', 0),
('nhomquyen', 'Quản lý nhóm quyền', 0),
('sanpham', 'Quản lý sản phẩm', 0),
('taikhoan', 'Quản lý tài khoản', 0),
('thongke', 'Quản lý thống kê', 0),
('thuoctinh', 'Quản lý thuộc tính', 0),
('xuathang', 'Quản lý xuất hàng', 0);

-- --------------------------------------------------------

--
-- Table structure for table `dungluongram`
--

CREATE TABLE `dungluongram` (
  `madlram` int(11) NOT NULL,
  `kichthuocram` int(11) DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dungluongram`
--

INSERT INTO `dungluongram` (`madlram`, `kichthuocram`, `trangthai`) VALUES
(1, 3, 1),
(2, 2, 1),
(3, 4, 1),
(4, 6, 1),
(5, 8, 1),
(6, 12, 1);

-- --------------------------------------------------------

--
-- Table structure for table `dungluongrom`
--

CREATE TABLE `dungluongrom` (
  `madlrom` int(11) NOT NULL,
  `kichthuocrom` int(11) DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dungluongrom`
--

INSERT INTO `dungluongrom` (`madlrom`, `kichthuocrom`, `trangthai`) VALUES
(1, 32, 1),
(2, 64, 1),
(3, 128, 1),
(4, 256, 1),
(5, 512, 1),
(6, 1024, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hedieuhanh`
--

CREATE TABLE `hedieuhanh` (
  `mahedieuhanh` int(11) NOT NULL,
  `tenhedieuhanh` varchar(255) NOT NULL,
  `trangthai` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hedieuhanh`
--

INSERT INTO `hedieuhanh` (`mahedieuhanh`, `tenhedieuhanh`, `trangthai`) VALUES
(1, 'Android', 1),
(2, 'IOS', 1),
(3, '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` int(11) NOT NULL,
  `tenkhachhang` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL,
  `ngaythamgia` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`makh`, `tenkhachhang`, `diachi`, `sdt`, `trangthai`, `ngaythamgia`) VALUES
(1, 'Nguyễn Văn A', 'Gia Đức, Ân Đức, Hoài Ân, Bình Định', '0387913347', 1, '2023-04-19 09:52:29'),
(2, 'Trần Nhất Nhất', '205 Trần Hưng Đạo, Phường 10, Quận 5, Thành phố Hồ Chí Minh', '0123456789', 1, '2023-04-19 09:52:29'),
(3, 'Hoàng Gia Bo', 'Khoa Trường, Hoài Ân, Bình Định', '0987654321', 1, '2023-04-19 09:52:29'),
(4, 'Hồ Minh Hưng', 'Khoa Trường, Hoài Ân, Bình Định', '0867987456', 1, '2023-04-19 09:52:29'),
(29, 'Nguyễn Thị Minh Anh', '123 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0935123456', 1, '2023-04-30 17:59:57'),
(30, 'Trần Đức Minh', '789 Đường Lê Hồng Phong, Thành phố Đà Nẵng', '0983456789', 1, '2023-04-30 18:08:12'),
(31, 'Lê Hải Yến', '456 Tôn Thất Thuyết, Quận 4, Thành phố Hồ Chí Minh', '0977234567', 1, '2023-04-30 18:08:47'),
(32, 'Phạm Thanh Hằng', '102 Lê Duẩn, Thành phố Hải Phòng', '0965876543', 1, '2023-04-30 18:12:59'),
(33, 'Hoàng Đức Anh', '321 Lý Thường Kiệt, Thành phố Cần Thơ', '0946789012', 1, '2023-04-30 18:13:47'),
(34, 'Ngô Thanh Tùng', '987 Trần Hưng Đạo, Quận 1, Thành phố Hồ Chí Minh', '0912345678', 1, '2023-04-30 18:14:12'),
(35, 'Võ Thị Kim Ngân', '555 Nguyễn Văn Linh, Quận Nam Từ Liêm, Hà Nội', '0916789123', 1, '2023-04-30 18:15:11'),
(36, 'Đỗ Văn Tú', '777 Hùng Vương, Thành phố Huế', '0982345678', 1, '2023-04-30 18:15:56'),
(37, 'Lý Thanh Trúc', '888 Nguyễn Thái Học, Quận Ba Đình, Hà Nội', '0982123456', 1, '2023-04-30 18:16:22'),
(38, 'Bùi Văn Hoàng', '222 Đường 2/4, Thành phố Nha Trang', '0933789012', 1, '2023-04-30 18:16:53'),
(39, 'Lê Văn Thành', '23 Đường 3 Tháng 2, Quận 10, TP. Hồ Chí Minh', '0933456789', 1, '2023-04-30 18:17:46'),
(40, 'Nguyễn Thị Lan Anh', '456 Lê Lợi, Quận 1, TP. Hà Nội', '0965123456', 1, '2023-04-30 18:18:10'),
(41, 'Phạm Thị Mai', '234 Lê Hồng Phong, Quận 5, TP. Hồ Chí Minh', '0946789012', 1, '2023-04-30 18:18:34'),
(42, 'Hoàng Văn Nam', ' 567 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0912345678', 1, '2023-04-30 18:19:16');

-- --------------------------------------------------------

--
-- Table structure for table `khuvuckho`
--

CREATE TABLE `khuvuckho` (
  `makhuvuc` int(11) NOT NULL,
  `tenkhuvuc` varchar(255) NOT NULL,
  `ghichu` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khuvuckho`
--

INSERT INTO `khuvuckho` (`makhuvuc`, `tenkhuvuc`, `ghichu`, `trangthai`) VALUES
(1, 'Khu vực A', 'Apple', 1),
(2, 'Khu vực B', 'Xiaomi', 1),
(3, 'Khu vực C', 'Samsung', 1),
(4, 'Khu vực D', 'Realme', 1),
(5, 'Khu vực E', 'Oppo', 1);

-- --------------------------------------------------------

--
-- Table structure for table `mausac`
--

CREATE TABLE `mausac` (
  `mamau` int(11) NOT NULL,
  `tenmau` varchar(50) NOT NULL DEFAULT '0',
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mausac`
--

INSERT INTO `mausac` (`mamau`, `tenmau`, `trangthai`) VALUES
(1, 'Xanh', 1),
(2, 'Đỏ', 1),
(3, 'Vàng', 1),
(4, 'Bạc', 1),
(5, 'Hồng', 1),
(6, 'Đen', 1),
(7, 'Xanh ngọc', 1),
(8, 'Tím', 1),
(9, 'Xanh dương', 1),
(10, 'Xanh lá', 1),
(11, 'Cam', 1),
(12, 'Xám', 1),
(13, 'Trắng', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` int(11) NOT NULL,
  `tennhacungcap` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `tennhacungcap`, `diachi`, `email`, `sdt`, `trangthai`) VALUES
(1, 'Công Ty TNHH Thế Giới Di Động', ' Phòng 6.5, Tầng6, Tòa Nhà E. Town 2, 364 Cộng Hòa, P. 13, Q. Tân Bình, Tp. Hồ Chí Minh', 'lienhe@thegioididong.com', '02835100100', 1),
(2, 'Công ty Vivo Việt Nam', 'Số 79 đường số 6, Hưng Phước 4, Phú Mỹ Hưng, quận 7, TPHCM', 'contact@paviet.vn', '19009477', 1),
(3, 'Công Ty TNHH Bao La', '8/38 Đinh Bô Lĩnh, P.24, Q. Bình Thạnh, Tp. Hồ Chí Minh', 'contact@baola.vn', '02835119060', 1),
(4, 'Công Ty Nokia', 'Phòng 703, Tầng7, Tòa Nhà Metropolitan, 235 Đồng Khởi, P. Bến Nghé, Q. 1, Tp. Hồ Chí Minh (TPHCM)', 'chau.nguyen@nokia.com', '02838236894', 1),
(5, 'Hệ Thống Phân Phối Chính Hãng Xiaomi', '261 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', 'info@mihome.vn', '0365888866', 1),
(6, 'Công Ty Samsung Việt Nam', 'Tòa nhà tài chính Bitexco, 2 Hải Triều, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', 'contact@samsung.vn', '0988788456', 1),
(7, 'Công ty Oppo Việt Nam', '27 Đ. Nguyễn Trung Trực, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', 'oppovietnam@oppo.vn', '0456345234', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL,
  `hoten` varchar(255) NOT NULL,
  `gioitinh` int(11) NOT NULL,
  `ngaysinh` date NOT NULL,
  `sdt` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `hoten`, `gioitinh`, `ngaysinh`, `sdt`, `email`, `trangthai`) VALUES
(1, 'Trần Nhật Sinh', 1, '2003-12-20', '0387913347', 'transinh085@gmail.com', 1),
(2, 'Hoàng Gia Bảo', 1, '2023-04-11', '0355374322', 'musicanime2501@gmail.com', 1),
(3, 'Đỗ Nam Công Chính', 1, '2003-04-11', '0123456789', 'chinchin@gmail.com', 1),
(4, 'Đinh Ngọc Ân', 1, '2003-04-03', '0123456789', 'ngocan@gmail.com', 1),
(5, 'Vũ Trung Hiếu', 1, '2023-05-06', '0123456789', 'hieu@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhomquyen`
--

CREATE TABLE `nhomquyen` (
  `manhomquyen` int(11) NOT NULL,
  `tennhomquyen` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhomquyen`
--

INSERT INTO `nhomquyen` (`manhomquyen`, `tennhomquyen`, `trangthai`) VALUES
(1, 'Quản lý kho', 1),
(2, 'Nhân viên nhập hàng', 1),
(3, 'Nhân viên xuất hàng', 1),
(4, 'Thủ kho', 0),
(5, 'Nhân viên kiểm kho', 0),
(6, 'tesst', 0),
(7, '', 0),
(8, 'ok', 0),
(9, 'ok', 0),
(10, 'abc', 1);

-- --------------------------------------------------------

--
-- Table structure for table `phienbansanpham`
--

CREATE TABLE `phienbansanpham` (
  `maphienbansp` int(11) NOT NULL,
  `masp` int(11) DEFAULT NULL,
  `rom` int(11) DEFAULT NULL,
  `ram` int(11) DEFAULT 0,
  `mausac` int(11) DEFAULT NULL,
  `gianhap` int(11) DEFAULT NULL,
  `giaxuat` int(11) DEFAULT NULL,
  `soluongton` int(11) DEFAULT 0,
  `trangthai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phienbansanpham`
--

INSERT INTO `phienbansanpham` (`maphienbansp`, `masp`, `rom`, `ram`, `mausac`, `gianhap`, `giaxuat`, `soluongton`, `trangthai`) VALUES
(2, 1, 1, 1, 1, 5000000, 5500000, 5, 1),
(15, 1, 3, 4, 1, 6000000, 6500000, 0, 1),
(17, 2, 3, 5, 1, 7000000, 7800000, 3, 1),
(19, 3, 3, 3, 5, 15000000, 16500000, 1, 1),
(21, 3, 3, 3, 6, 15000000, 16500000, 0, 1),
(23, 3, 3, 3, 1, 15500000, 17000000, 5, 1),
(24, 4, 1, 1, 1, 2000000, 2890000, 3, 1),
(25, 4, 1, 1, 1, 2000000, 2890000, 0, 0),
(26, 4, 1, 1, 1, 2000000, 2890000, 0, 0),
(27, 4, 1, 1, 6, 2000000, 2890000, 2, 1),
(28, 4, 2, 1, 6, 2500000, 3390000, 4, 1),
(29, 4, 2, 1, 1, 2500000, 3390000, 5, 1),
(31, 5, 3, 5, 8, 8000000, 9000000, 13, 1),
(33, 5, 3, 5, 6, 8000000, 9000000, 15, 1),
(35, 5, 3, 5, 1, 8000000, 9000000, 11, 1),
(37, 5, 4, 5, 1, 9000000, 10000000, 0, 1),
(39, 5, 4, 5, 6, 9000000, 10000000, 0, 1),
(41, 5, 4, 5, 8, 9000000, 10000000, 0, 1),
(43, 6, 3, 3, 9, 3000000, 4100000, 0, 1),
(45, 6, 3, 3, 6, 3000000, 4100000, 0, 1),
(47, 6, 3, 4, 6, 3200000, 4400000, 0, 1),
(49, 6, 3, 4, 9, 3200000, 4400000, 27, 1),
(51, 7, 3, 5, 9, 5000000, 5790000, 9, 1),
(53, 7, 3, 5, 10, 5000000, 5790000, 0, 1),
(55, 7, 3, 3, 9, 4000000, 4890000, 0, 1),
(57, 7, 3, 3, 10, 4000000, 4890000, 7, 1),
(58, 7, 3, 5, 12, 5000000, 5790000, 6, 1),
(59, 7, 3, 3, 12, 4000000, 4890000, 0, 1),
(61, 8, 2, 3, 9, 2000000, 2990000, 0, 1),
(63, 8, 2, 3, 10, 2000000, 2990000, 0, 1),
(65, 8, 2, 3, 12, 2000000, 2990000, 0, 1),
(67, 8, 3, 3, 9, 2200000, 3290000, 0, 1),
(69, 8, 3, 3, 10, 2200000, 3290000, 0, 1),
(71, 8, 3, 3, 12, 2200000, 3290000, 0, 1),
(73, 9, 4, 5, 10, 8200000, 9500000, 0, 1),
(75, 9, 4, 5, 9, 8200000, 9500000, 6, 1),
(77, 10, 3, 3, 11, 3600000, 4700000, 0, 1),
(79, 10, 3, 3, 6, 3600000, 4700000, 0, 1),
(81, 10, 3, 3, 9, 3600000, 4700000, 0, 1),
(83, 10, 3, 4, 11, 4100000, 5200000, 0, 1),
(85, 10, 3, 4, 6, 4100000, 5200000, 10, 1),
(87, 10, 3, 4, 9, 4100000, 5200000, 0, 1),
(89, 11, 4, 5, 6, 5200000, 6400000, 0, 1),
(91, 11, 4, 5, 13, 5200000, 6400000, 0, 1),
(93, 12, 2, 3, 13, 2500000, 3000000, 11, 1),
(95, 12, 2, 3, 9, 2500000, 3000000, 0, 1),
(97, 13, 3, 5, 13, 14000000, 16000000, 20, 1),
(99, 13, 3, 5, 10, 14000000, 16000000, 0, 1),
(101, 13, 3, 5, 4, 14000000, 16000000, 0, 1),
(103, 13, 3, 5, 6, 14000000, 16000000, 0, 1),
(105, 13, 4, 5, 13, 16000000, 18000000, 0, 1),
(107, 13, 4, 5, 10, 16000000, 18000000, 0, 1),
(109, 13, 4, 5, 4, 16000000, 18000000, 0, 1),
(111, 13, 4, 5, 6, 16000000, 18000000, 0, 1),
(113, 14, 4, 5, 1, 12000000, 14000000, 7, 1),
(115, 15, 3, 5, 4, 4500000, 5500000, 5, 1),
(117, 15, 3, 5, 6, 4500000, 5500000, 0, 1),
(118, 1, 1, 1, 1, 5000000, 5500000, 0, 0),
(119, 2, 3, 5, 1, 7000000, 7800000, 0, 0),
(120, 1, 1, 1, 1, 5000000, 5500000, 0, 0),
(121, 1, 1, 1, 1, 5000000, 5500000, 0, 0),
(122, 1, 1, 1, 1, 5000000, 5500000, 0, 0),
(123, 1, 3, 4, 2, 6000000, 6500000, 0, 0),
(125, 17, 1, 1, 1, 2500000, 3000000, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieubaohanh`
--

CREATE TABLE `phieubaohanh` (
  `maphieubaohanh` int(11) NOT NULL,
  `maimei` varchar(255) NOT NULL,
  `lydo` varchar(50) NOT NULL,
  `thoigian` datetime NOT NULL DEFAULT curdate(),
  `thoigiantra` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieudoi`
--

CREATE TABLE `phieudoi` (
  `maphieudoi` tinyint(4) NOT NULL DEFAULT 0,
  `maimei` varchar(255) NOT NULL,
  `lydo` varchar(255) NOT NULL,
  `thoigian` date DEFAULT curdate(),
  `nguoitao` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieukiemke`
--

CREATE TABLE `phieukiemke` (
  `maphieu` int(11) NOT NULL,
  `thoigian` date NOT NULL DEFAULT curdate(),
  `nguoitaophieukiemke` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `thoigian` datetime DEFAULT current_timestamp(),
  `manhacungcap` int(11) NOT NULL,
  `nguoitao` int(11) NOT NULL,
  `tongtien` bigint(20) NOT NULL DEFAULT 0,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `thoigian`, `manhacungcap`, `nguoitao`, `tongtien`, `trangthai`) VALUES
(1, '2023-04-01 17:34:12', 1, 1, 60000000, 1),
(2, '2023-04-03 17:42:17', 1, 1, 110000000, 1),
(3, '2023-04-04 18:07:58', 1, 1, 84000000, 1),
(4, '2023-04-04 18:19:51', 3, 4, 35000000, 1),
(5, '2023-04-06 08:18:01', 1, 1, 10000000, 1),
(8, '2023-04-07 20:33:58', 1, 1, 75000000, 1),
(9, '2023-04-07 01:09:27', 1, 1, 108500000, 1),
(10, '2023-04-07 08:42:52', 1, 1, 7000000, 1),
(11, '2023-04-10 00:22:13', 1, 1, 5000000, 1),
(12, '2023-04-13 00:52:47', 2, 1, 34500000, 1),
(13, '2023-04-13 00:56:04', 6, 1, 456000000, 1),
(14, '2023-04-14 00:57:07', 5, 1, 118000000, 1),
(15, '2023-04-15 00:59:02', 7, 1, 187500000, 1),
(16, '2023-04-16 00:59:46', 6, 1, 321000000, 1),
(17, '2023-04-17 01:00:30', 6, 1, 73800000, 1),
(18, '2023-04-19 01:01:25', 5, 1, 52000000, 1),
(19, '2023-04-20 01:02:22', 1, 1, 7500000, 1),
(20, '2023-05-09 12:09:23', 2, 1, 13000000, 1),
(21, '2023-05-10 08:17:32', 6, 1, 12500000, 1),
(23, '2023-05-10 08:25:11', 1, 1, 16000000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieutra`
--

CREATE TABLE `phieutra` (
  `maphieutra` int(11) NOT NULL,
  `maimei` varchar(255) NOT NULL,
  `lydo` varchar(255) NOT NULL,
  `thoigian` date DEFAULT curdate(),
  `nguoitao` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `thoigian` datetime NOT NULL DEFAULT current_timestamp(),
  `tongtien` bigint(20) DEFAULT NULL,
  `nguoitaophieuxuat` int(11) DEFAULT NULL,
  `makh` int(11) DEFAULT NULL,
  `trangthai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieuxuat`
--

INSERT INTO `phieuxuat` (`maphieuxuat`, `thoigian`, `tongtien`, `nguoitaophieuxuat`, `makh`, `trangthai`) VALUES
(2, '2023-04-04 09:56:35', 16500000, 1, 1, 1),
(3, '2023-04-04 03:18:23', 49500000, 1, 1, 1),
(4, '2023-04-05 03:19:31', 39000000, 1, 4, 1),
(5, '2023-04-06 18:30:26', 31200000, 1, 40, 1),
(6, '2023-04-06 01:01:48', 22000000, 1, 38, 1),
(7, '2023-04-06 12:39:44', 7800000, 1, 3, 1),
(8, '2023-04-08 12:40:04', 23400000, 1, 4, 1),
(9, '2023-04-09 12:40:32', 22000000, 1, 33, 1),
(10, '2023-04-11 12:40:50', 15600000, 1, 41, 1),
(11, '2023-04-11 12:42:33', 49500000, 1, 37, 1),
(12, '2023-04-12 02:31:45', 60500000, 1, 31, 1),
(13, '2023-04-13 00:23:02', 5500000, 1, 41, 1),
(14, '2023-04-30 01:52:18', 42000000, 1, 37, 1),
(15, '2023-05-01 01:57:39', 11000000, 1, 4, 1),
(16, '2023-05-02 01:58:16', 54800000, 1, 30, 1),
(17, '2023-05-03 01:59:44', 51200000, 1, 39, 1),
(18, '2023-05-04 02:00:13', 28500000, 1, 39, 1),
(19, '2023-05-05 02:01:28', 36890000, 1, 3, 1),
(20, '2023-05-06 02:06:24', 18000000, 1, 35, 1),
(21, '2023-05-07 10:08:49', 17370000, 1, 37, 1),
(22, '2023-05-08 22:56:21', 36000000, 1, 34, 1),
(23, '2023-05-09 22:57:23', 16500000, 1, 30, 1),
(24, '2023-05-10 02:55:35', 13200000, 1, 37, 1),
(25, '2023-05-10 08:28:18', 15600000, 1, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `masp` int(11) NOT NULL,
  `tensp` varchar(255) DEFAULT NULL,
  `hinhanh` varchar(255) DEFAULT NULL,
  `xuatxu` int(11) DEFAULT NULL,
  `chipxuly` varchar(255) DEFAULT NULL,
  `dungluongpin` int(11) DEFAULT NULL,
  `kichthuocman` double DEFAULT NULL,
  `hedieuhanh` int(11) DEFAULT NULL,
  `phienbanhdh` int(11) DEFAULT NULL,
  `camerasau` varchar(255) DEFAULT NULL,
  `cameratruoc` varchar(255) DEFAULT NULL,
  `thoigianbaohanh` int(11) DEFAULT NULL,
  `thuonghieu` int(11) DEFAULT NULL,
  `khuvuckho` int(11) DEFAULT NULL,
  `soluongton` int(11) DEFAULT 0,
  `trangthai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`masp`, `tensp`, `hinhanh`, `xuatxu`, `chipxuly`, `dungluongpin`, `kichthuocman`, `hedieuhanh`, `phienbanhdh`, `camerasau`, `cameratruoc`, `thoigianbaohanh`, `thuonghieu`, `khuvuckho`, `soluongton`, `trangthai`) VALUES
(1, 'Vivo Y22s', '92vivo-y22s-vang-thumb-600x600.jpg', 1, 'SnapDragon 680', 5000, 6.55, 1, 12, 'Chính 50 MP & Phụ 2 MP', '8 MP', 24, 1, 1, 5, 1),
(2, 'Samsung Galaxy A53 5G', '57samsung-galaxy-a53-cam-thumb-1-600x600.jpg', 1, 'Exynos 1280', 5000, 6.5, 1, 12, 'Chính 64 MP & Phụ 12 MP, 5 MP, 5 MP', '32 MP', 24, 3, 2, 3, 1),
(3, 'iPhone 13 mini', '997iphone-13-mini-pink-1-600x600.jpg', 1, ' Apple A15 Bionic', 2438, 5.4, 2, 15, '2 camera 12 MP', ' 12 MP', 36, 1, 1, 6, 0),
(4, 'Vivo Y02s', '74vivo-y02s-thumb-1-2-3-600x600.jpg', 1, 'MediaTek Helio P35', 5000, 6.51, 1, 12, '8 MP', ' 5 MP', 24, 10, 3, 14, 1),
(5, 'Samsung Galaxy A54 5G', '399samsung-galaxy-a54-5g-tim-thumb-600x600.jpg', 2, ' Exynos 1380 8 nhân', 5000, 6.4, 1, 12, 'Chính 50 MP & Phụ 12 MP, 5 MP', ' 32 MP', 24, 3, 3, 39, 1),
(6, 'Samsung Galaxy A13', '337samsung-galaxy-a14-black-thumb-600x600.jpg', 1, 'Exynos 850', 5000, 6.6, 1, 12, 'Chính 50 MP & Phụ 5 MP, 2 MP, 2 MP', '8 MP', 24, 3, 2, 27, 1),
(7, 'Xiaomi Redmi Note 12', '717xiaomi-redmi-note-12-4g-mono-den-600x600.jpg', 1, ' Snapdragon 685 8 nhân', 5000, 6.67, 1, 12, 'Chính 50 MP & Phụ 8 MP, 2 MP', '13 MP', 24, 2, 4, 22, 1),
(8, 'Xiaomi Redmi 12C', '437xiaomi-redmi-12c-grey-thumb-600x600.jpg', 1, 'MediaTek Helio G85', 5000, 6.71, 1, 12, 'Chính 50 MP & Phụ QVGA', '5 MP', 24, 1, 1, 0, 1),
(9, 'Samsung Galaxy S20 FE', '286samsung-galaxy-s20-fan-edition-xanh-la-thumbnew-600x600.jpeg', 1, 'Snapdragon 865', 4500, 6.5, 1, 12, 'Chính 12 MP & Phụ 12 MP, 8 MP', '32 MP', 24, 3, 4, 6, 1),
(10, 'Samsung Galaxy A23', '826samsung-galaxy-a23-cam-thumb-600x600.jpg', 1, 'Snapdragon 680', 5000, 6.6, 1, 12, 'Chính 50 MP & Phụ 5 MP, 2 MP, 2 MP', '8 MP', 24, 1, 1, 10, 1),
(11, 'Realme 10', '877realme-10-thumb-1-600x600.jpg', 1, 'MediaTek Helio G99', 5000, 6.4, 1, 12, 'Chính 50 MP & Phụ 2 MP', '16 MP', 24, 11, 1, 0, 1),
(12, 'Vivo Y21', '960vivo-y21-blue-01-600x600.jpg', 1, 'MediaTek Helio P35', 5000, 6.51, 1, 12, 'Chính 13 MP & Phụ 2 MP', '8 MP', 24, 10, 5, 11, 1),
(13, 'Samsung Galaxy S22+ 5G', '177Galaxy-S22-Ultra-Burgundy-600x600.jpg', 1, 'Snapdragon 8 Gen 1', 4500, 6.6, 1, 12, 'Chính 50 MP & Phụ 12 MP, 10 MP', '10 MP', 24, 3, 4, 20, 1),
(14, 'OPPO Reno6 Pro 5G', '203oppo-reno6-pro-grey-600x600.jpg', 1, 'Snapdragon 870 5G', 4500, 6.55, 1, 11, 'Chính 50 MP & Phụ 16 MP, 13 MP, 2 MP', '32 MP', 24, 3, 4, 7, 1),
(15, ' OPPO A95', '555oppo-a95-4g-bac-2-600x600.jpg', 1, 'Snapdragon 662', 5000, 6.43, 1, 11, 'Chính 48 MP & Phụ 2 MP, 2 MP', '16 MP', 24, 1, 1, 5, 1),
(17, 'Samsung Galaxy A53 5G S', '74319198933.jpg', 1, 'chip a', 5000, 5.6, 1, 12, 'msdf', 'dsfgfdg', 24, 1, 1, 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `manv` int(11) NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `manhomquyen` int(11) NOT NULL,
  `tendangnhap` varchar(50) NOT NULL DEFAULT '',
  `trangthai` int(11) NOT NULL,
  `otp` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`manv`, `matkhau`, `manhomquyen`, `tendangnhap`, `trangthai`, `otp`) VALUES
(1, '$2a$12$6GSkiQ05XjTRvCW9MB6MNuf7hOJEbbeQx11Eb8oELil1OrCq6uBXm', 1, 'admin', 1, 'null'),
(2, '$2a$12$SAlAhcsudMzNEouyBaoHnOKR23ixdH0ZkcoyXUJ5gS/NFt.b4oqw6', 1, 'hgbaodev', 1, '451418'),
(3, '$2a$12$SAlAhcsudMzNEouyBaoHnOKR23ixdH0ZkcoyXUJ5gS/NFt.b4oqw6', 10, 'chinh', 1, NULL),
(4, '$2a$12$SAlAhcsudMzNEouyBaoHnOKR23ixdH0ZkcoyXUJ5gS/NFt.b4oqw6', 2, 'ngocan', 0, NULL),
(5, '$2a$12$SAlAhcsudMzNEouyBaoHnOKR23ixdH0ZkcoyXUJ5gS/NFt.b4oqw6', 3, 'hieunek', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `mathuonghieu` int(11) NOT NULL,
  `tenthuonghieu` varchar(255) NOT NULL,
  `trangthai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thuonghieu`
--

INSERT INTO `thuonghieu` (`mathuonghieu`, `tenthuonghieu`, `trangthai`) VALUES
(1, 'Apple', 1),
(2, 'Xiaomi', 1),
(3, 'Samsung', 1),
(4, 'Sữa tươi', 0),
(7, 'g', 0),
(8, '', 0),
(9, 'Oppo', 1),
(10, 'Vivo', 1),
(11, 'Realme', 1),
(12, 'Nokia', 1),
(13, 'Vsmart', 1),
(14, 'Apple', 0);

-- --------------------------------------------------------

--
-- Table structure for table `xuatxu`
--

CREATE TABLE `xuatxu` (
  `maxuatxu` int(11) NOT NULL,
  `tenxuatxu` varchar(50) NOT NULL,
  `trangthai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `xuatxu`
--

INSERT INTO `xuatxu` (`maxuatxu`, `tenxuatxu`, `trangthai`) VALUES
(1, 'Trung Quốc', 1),
(2, 'Hàn Quốc', 1),
(3, 'Việt Nam', 1),
(4, 'USA', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ctkiemke`
--
ALTER TABLE `ctkiemke`
  ADD PRIMARY KEY (`maphieukiemmke`,`masanpham`);

--
-- Indexes for table `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD PRIMARY KEY (`maphieunhap`,`maphienbansp`);

--
-- Indexes for table `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`,`maphienbansp`);

--
-- Indexes for table `ctquyen`
--
ALTER TABLE `ctquyen`
  ADD PRIMARY KEY (`manhomquyen`,`machucnang`,`hanhdong`) USING BTREE;

--
-- Indexes for table `ctsanpham`
--
ALTER TABLE `ctsanpham`
  ADD PRIMARY KEY (`maimei`) USING BTREE;

--
-- Indexes for table `danhmucchucnang`
--
ALTER TABLE `danhmucchucnang`
  ADD PRIMARY KEY (`machucnang`);

--
-- Indexes for table `dungluongram`
--
ALTER TABLE `dungluongram`
  ADD PRIMARY KEY (`madlram`);

--
-- Indexes for table `dungluongrom`
--
ALTER TABLE `dungluongrom`
  ADD PRIMARY KEY (`madlrom`);

--
-- Indexes for table `hedieuhanh`
--
ALTER TABLE `hedieuhanh`
  ADD PRIMARY KEY (`mahedieuhanh`) USING BTREE;

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Indexes for table `khuvuckho`
--
ALTER TABLE `khuvuckho`
  ADD PRIMARY KEY (`makhuvuc`);

--
-- Indexes for table `mausac`
--
ALTER TABLE `mausac`
  ADD PRIMARY KEY (`mamau`),
  ADD UNIQUE KEY `tenmau` (`tenmau`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`manhacungcap`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`);

--
-- Indexes for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  ADD PRIMARY KEY (`manhomquyen`);

--
-- Indexes for table `phienbansanpham`
--
ALTER TABLE `phienbansanpham`
  ADD PRIMARY KEY (`maphienbansp`) USING BTREE,
  ADD KEY `FK_phienbansanpham_sanpham` (`masp`);

--
-- Indexes for table `phieubaohanh`
--
ALTER TABLE `phieubaohanh`
  ADD PRIMARY KEY (`maphieubaohanh`);

--
-- Indexes for table `phieudoi`
--
ALTER TABLE `phieudoi`
  ADD PRIMARY KEY (`maphieudoi`),
  ADD KEY `FK_phieudoi_ctsanpham` (`maimei`),
  ADD KEY `FK_phieudoi_taikhoan` (`nguoitao`);

--
-- Indexes for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  ADD PRIMARY KEY (`maphieu`),
  ADD KEY `FK_phieukiemke_taikhoan` (`nguoitaophieukiemke`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `FK_phieunhap_nhacungcap` (`manhacungcap`),
  ADD KEY `FK_phieunhap_taikhoan` (`nguoitao`);

--
-- Indexes for table `phieutra`
--
ALTER TABLE `phieutra`
  ADD PRIMARY KEY (`maphieutra`),
  ADD KEY `FK_phieutra_ctsanpham` (`maimei`),
  ADD KEY `FK_phieutra_taikhoan` (`nguoitao`);

--
-- Indexes for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`),
  ADD KEY `FK_phieuxuat_khachhang` (`makh`),
  ADD KEY `FK_phieuxuat_taikhoan` (`nguoitaophieuxuat`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masp`) USING BTREE;

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`manv`),
  ADD UNIQUE KEY `tendangnhap` (`tendangnhap`),
  ADD KEY `FK_taikhoan_nhomquyen` (`manhomquyen`);

--
-- Indexes for table `thuonghieu`
--
ALTER TABLE `thuonghieu`
  ADD PRIMARY KEY (`mathuonghieu`) USING BTREE;

--
-- Indexes for table `xuatxu`
--
ALTER TABLE `xuatxu`
  ADD PRIMARY KEY (`maxuatxu`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dungluongram`
--
ALTER TABLE `dungluongram`
  MODIFY `madlram` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `dungluongrom`
--
ALTER TABLE `dungluongrom`
  MODIFY `madlrom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `hedieuhanh`
--
ALTER TABLE `hedieuhanh`
  MODIFY `mahedieuhanh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `khuvuckho`
--
ALTER TABLE `khuvuckho`
  MODIFY `makhuvuc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `mausac`
--
ALTER TABLE `mausac`
  MODIFY `mamau` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `manhacungcap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `manhomquyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `phienbansanpham`
--
ALTER TABLE `phienbansanpham`
  MODIFY `maphienbansp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=126;

--
-- AUTO_INCREMENT for table `phieubaohanh`
--
ALTER TABLE `phieubaohanh`
  MODIFY `maphieubaohanh` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  MODIFY `maphieu` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `maphieunhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `phieutra`
--
ALTER TABLE `phieutra`
  MODIFY `maphieutra` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  MODIFY `maphieuxuat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `thuonghieu`
--
ALTER TABLE `thuonghieu`
  MODIFY `mathuonghieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `xuatxu`
--
ALTER TABLE `xuatxu`
  MODIFY `maxuatxu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ctkiemke`
--
ALTER TABLE `ctkiemke`
  ADD CONSTRAINT `FK_ctkiemke_phieukiemke` FOREIGN KEY (`maphieukiemmke`) REFERENCES `phieukiemke` (`maphieu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD CONSTRAINT `FK__phieuxuat` FOREIGN KEY (`maphieuxuat`) REFERENCES `phieuxuat` (`maphieuxuat`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ctquyen`
--
ALTER TABLE `ctquyen`
  ADD CONSTRAINT `FK__nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phienbansanpham`
--
ALTER TABLE `phienbansanpham`
  ADD CONSTRAINT `FK_phienbansanpham_sanpham` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieudoi`
--
ALTER TABLE `phieudoi`
  ADD CONSTRAINT `FK_phieudoi_ctsanpham` FOREIGN KEY (`maimei`) REFERENCES `ctsanpham` (`maimei`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieudoi_taikhoan` FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieukiemke`
--
ALTER TABLE `phieukiemke`
  ADD CONSTRAINT `FK_phieukiemke_taikhoan` FOREIGN KEY (`nguoitaophieukiemke`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_phieunhap_nhacungcap` FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieunhap_taikhoan` FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieutra`
--
ALTER TABLE `phieutra`
  ADD CONSTRAINT `FK_phieutra_ctsanpham` FOREIGN KEY (`maimei`) REFERENCES `ctsanpham` (`maimei`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieutra_taikhoan` FOREIGN KEY (`nguoitao`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `FK_phieuxuat_khachhang` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_phieuxuat_taikhoan` FOREIGN KEY (`nguoitaophieuxuat`) REFERENCES `taikhoan` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_taikhoan_nhanvien` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_taikhoan_nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;