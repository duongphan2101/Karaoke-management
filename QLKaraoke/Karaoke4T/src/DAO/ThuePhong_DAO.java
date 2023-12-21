package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Entity.KhachHang;
import connectDB.connectDB;

public class ThuePhong_DAO {

	public boolean create(KhachHang kh) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement smt = null;
		int n = 0;
		try {
			smt = con.prepareStatement("insert into KhachHang values(?, ?, ?, ?, ?, ?)");
			smt.setString(1, kh.getMaKH());
			smt.setString(2, kh.getGioiTinh());
			smt.setString(3, kh.getTenKH());
			smt.setString(4, kh.getSDT());
			smt.setString(5, kh.getCMND());
			smt.setString(6, kh.getDiaChi());
			n = smt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}

}
