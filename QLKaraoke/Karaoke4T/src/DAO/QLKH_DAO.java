package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.*;
import Entity.*;

public class QLKH_DAO {
	ArrayList<KhachHang> dskh;

	public QLKH_DAO() {
		dskh = new ArrayList<KhachHang>();
	}

	public ArrayList<Entity.KhachHang> doctubang() {
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "Select * from KhachHang";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				String gt = rs.getString(2);
				String ten = rs.getString(3);
				String sdt = rs.getString(4);
				String cccd = rs.getString(5);
				String dc = rs.getString(6);
				KhachHang kh = new KhachHang(ma, gt, ten, sdt, cccd, dc);
				dskh.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dskh;
	}

	public ArrayList<Entity.KhachHang> laytheomakh(String makh) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stm = null;
		try {

			String sql = "Select * from KhachHang";
			stm = con.prepareStatement(sql);
			stm.setString(0, makh);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				String gt = rs.getString(2);
				String ten = rs.getString(3);
				String sdt = rs.getString(4);
				String cccd = rs.getString(5);
				String dc = rs.getString(6);
				KhachHang kh = new KhachHang(makh, gt, ten, sdt, cccd, dc);
				dskh.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dskh;
	}

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
		return n > 0;
	}

	public boolean update(KhachHang kh) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement smt = null;
		int n = 0;
		try {
			smt = con.prepareStatement(
					"UPDATE KhachHang SET gioiTinh = ?, tenKH = ?, SDT = ?, CMND = ? , diaChi = ? where maKH = ?");

			smt.setString(1, kh.getGioiTinh());
			smt.setString(2, kh.getTenKH());
			smt.setString(3, kh.getSDT());
			smt.setString(4, kh.getCMND());
			smt.setString(5, kh.getDiaChi());
			smt.setString(6, kh.getMaKH());

			n = smt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public KhachHang getKH(int index) {
		return dskh.get(index);
	}

	public boolean delete(String makh) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement smt = null;

		int n = 0;
		try {
			smt = con.prepareStatement("delete from KhachHang where maKH = ?");
			smt.setString(1, makh);
			n = smt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<KhachHang> getDs() {
		return dskh;
	}

	public int getMaxMaKH() {
		int maxMaKH = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connectDB.getInstance();
			conn = connectDB.getConnection();
			String query = "SELECT MAX(CONVERT(INT, SUBSTRING(maKH, 5, LEN(maKH)))) FROM KhachHang";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				maxMaKH = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxMaKH;
	}

	public ArrayList<KhachHang> timkh(String text) {
		ArrayList<KhachHang> ketQuaTimKiem = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connectDB.getInstance();
			conn = connectDB.getConnection();

			String query = "SELECT * FROM KhachHang\r\n"
					+ "WHERE maKH = ? OR tenKH = ? OR SDT = ? OR CMND = ? OR diaChi = ?;";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setString(4, text);
			pstmt.setString(5, text);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String ma = rs.getString(1);
				String gt = rs.getString(2);
				String ten = rs.getString(3);

				String cmnd = rs.getString(5);
				String sdt = rs.getString(4);
				String dc = rs.getString(6);

				KhachHang kh = new KhachHang(ma, gt, ten, sdt, cmnd, dc);
				ketQuaTimKiem.add(kh);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQuaTimKiem;
	}
}
