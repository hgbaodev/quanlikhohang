/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author 84907
 */
public class ChiTietPhieu {
    private int maphieu;
    private int masanpham;
    private int soluong;
    private double dongia;

    public ChiTietPhieu() {
    }

    public ChiTietPhieu(int maphieu, int masanpham, int soluong, double dongia) {
        this.maphieu = maphieu;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.maphieu;
        hash = 19 * hash + this.masanpham;
        hash = 19 * hash + this.soluong;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.dongia) ^ (Double.doubleToLongBits(this.dongia) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietPhieu other = (ChiTietPhieu) obj;
        return true;
    }

    @Override
    public String toString() {
        return "ChiTietPhieu{" + "maphieu=" + maphieu + ", masanpham=" + masanpham + ", soluong=" + soluong + ", dongia=" + dongia + '}';
    }
    
    
}