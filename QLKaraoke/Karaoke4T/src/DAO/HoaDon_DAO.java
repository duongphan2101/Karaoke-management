package DAO;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;
import connectDB.connectDB;

public class HoaDon_DAO {
	ArrayList<HoaDon> hoaDon;
	
	public HoaDon_DAO() {
		hoaDon = new ArrayList<HoaDon>();
	}
	
	public ArrayList<HoaDon> docbang(){
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select maHD , kh.SDT , kh.tenKH , nv.tenNV , gioNhanPhong, ngayLap from HoaDon hd inner join KhachHang kh on hd.maKH = kh.maKH inner join NhanVien nv on hd.maNV = nv.maNV";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				String maHD = rs.getString(1);
				KhachHang sdtKH = new KhachHang(null, null, null, rs.getString(2), null, null);
				KhachHang tenKH = new KhachHang(null, null, null, null, rs.getString(3), null);
				NhanVien tenNV = new NhanVien(null, rs.getString(4), null, null, null, null, null, null, null, null);
				String gioNhanPhong = rs.getString(5);
				String ngayLap = rs.getString(6);
				HoaDon hd = new HoaDon(maHD, null, gioNhanPhong, ngayLap, 0, 0, sdtKH, tenKH , tenNV);
				hoaDon.add(hd);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return hoaDon;
	}
	
//	public boolean create(HoaDon hoaDon) {
//		Connection con = connectDB.getInstance().getConnection();
//		PreparedStatement smt = null;
//		int n = 0;
//		try {
//			smt = con.prepareStatement("insert into HoaDon values(?, ?, ?, ?, ?)");
//			smt.setString(1, hoaDon.getMaHD());
//			
//			n = smt.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return n > 0;
//	}
}
