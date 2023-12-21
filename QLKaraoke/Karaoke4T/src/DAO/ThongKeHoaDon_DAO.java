package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.ThongKeHoaDon;
import connectDB.connectDB;

public class ThongKeHoaDon_DAO {
	ArrayList<ThongKeHoaDon> dstk;
	public ThongKeHoaDon_DAO () {
		dstk = new ArrayList<ThongKeHoaDon>();
	}
	public ArrayList<Entity.ThongKeHoaDon> doctubang(){
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select maHD,kh.tenKH,kh.SDT,ngayLap,tenNV,tongTien from HoaDon h inner join KhachHang kh on h.maKH = kh.maKH inner join NhanVien nv on h.maNV = nv.maNV";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				HoaDon maHD = new HoaDon(rs.getString(1),null,null,null,0,0,null,null,null);
				KhachHang tenKH = new KhachHang(null,null,rs.getString(2),null,null,null);
				KhachHang SDT = new KhachHang(null,null,null,rs.getString(3),null,null);
				HoaDon ngayLap = new HoaDon(null,null,null,rs.getString(4),0,0,null,null,null);
				NhanVien tenNV = new NhanVien(null,rs.getString(5),null,null,null,null,null,null,null,null);
				HoaDon tongTien = new HoaDon(null,null,null,null,0,rs.getFloat(6),null,null,null);
				ThongKeHoaDon tkhd = new ThongKeHoaDon(maHD,tenKH,SDT,ngayLap,tenNV,tongTien);
				dstk.add(tkhd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dstk;
	}
	
	public ArrayList<Entity.ThongKeHoaDon> doctubangTheoThang(int ngay,String thang,int nam) {
	    try {
	        connectDB.getInstance();
			Connection con = connectDB.getConnection();
	        String sql = "select * from HoaDon\r\n"
	        		+ "where  DAY(ngayLap) = ? and month(ngayLap) = ? and YEAR(ngayLap) = ?";
	        
	        // Sử dụng PreparedStatement để có thể truyền tham số
	        try (PreparedStatement sta = con.prepareStatement(sql)) {
	            // Truyền giá trị của biến thang vào tham số ?
	            sta.setInt(1, ngay);
	            sta.setString(2, thang);
	            sta.setInt(3, nam);

	            ResultSet rs = sta.executeQuery();
	            while (rs.next()) {
	                HoaDon maHD = new HoaDon(rs.getString(1), null, null, null, 0, 0, null, null,null);
	                KhachHang tenKH = new KhachHang(null, null, rs.getString(2), null, null, null);
	                KhachHang SDT = new KhachHang(null, null, null, rs.getString(3), null, null);
	                HoaDon ngayLap = new HoaDon(null, null, null, rs.getString(4), 0, 0, null, null ,null);
	                NhanVien tenNV = new NhanVien(null, rs.getString(5), null, null, null, null, null, null, null, null);
	                HoaDon tongTien = new HoaDon(null, null, null, null, 0, rs.getFloat(6), null, null,null);
	                ThongKeHoaDon tkhd = new ThongKeHoaDon(maHD, tenKH, SDT, ngayLap, tenNV, tongTien);
	                dstk.add(tkhd);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dstk;
	}

}
