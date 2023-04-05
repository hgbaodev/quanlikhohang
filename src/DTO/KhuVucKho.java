/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

public class KhuVucKho {

    private int makhuvuckho;
    private String tenkhuvuc;
    private String ghichu;

    public KhuVucKho() {
    }

    public KhuVucKho(int makhuvuckho, String tenkhuvuc, String ghichu) {
        this.makhuvuckho = makhuvuckho;
        this.tenkhuvuc = tenkhuvuc;
        this.ghichu = ghichu;
    }

    public int getMakhuvuckho() {
        return makhuvuckho;
    }

    public void setMakhuvuckho(int makhuvuckho) {
        this.makhuvuckho = makhuvuckho;
    }

    public String getTenkhuvuc() {
        return tenkhuvuc;
    }

    public void setTenkhuvuc(String tenkhuvuc) {
        this.tenkhuvuc = tenkhuvuc;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.makhuvuckho;
        hash = 61 * hash + Objects.hashCode(this.tenkhuvuc);
        hash = 61 * hash + Objects.hashCode(this.ghichu);
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
        final KhuVucKho other = (KhuVucKho) obj;
        if (this.makhuvuckho != other.makhuvuckho) {
            return false;
        }
        if (!Objects.equals(this.tenkhuvuc, other.tenkhuvuc)) {
            return false;
        }
        return Objects.equals(this.ghichu, other.ghichu);
    }

    @Override
    public String toString() {
        return "KhuVucKho{" + "makhuvuckho=" + makhuvuckho + ", tenkhuvuc=" + tenkhuvuc + ", ghichu=" + ghichu + '}';
    }
}
