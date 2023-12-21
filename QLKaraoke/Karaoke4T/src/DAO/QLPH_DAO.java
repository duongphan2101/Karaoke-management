package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entity.*;
import connectDB.connectDB;

public class QLPH_DAO {
	ArrayList<Phong> dsph;
	Connection con = connectDB.getConnection();

    
	public QLPH_DAO() {
		dsph = new ArrayList<Phong>();
		
	}
	

 
	
	public ArrayList<Phong> docbang(){
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select maPhong , soNguoi , tenPhong , giaTien,lp.maLP, ttp.maTTP from Phong p inner join LoaiPhong lp on p.maLP = lp.maLP inner join TrangThaiPhong ttp on p.maTTP = ttp.maTTP";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				String maPhong = rs.getString(1);
				String soNguoi = rs.getString(2);
				String tenPhong = rs.getString(3);
				String tinhTrang = rs.getString(4);
				LoaiPhong loaiPhong = new LoaiPhong("","",0);
				String maLoaiPhong = rs.getString(5);
				if(maLoaiPhong.equals("LP001")) {
					loaiPhong = new LoaiPhong("","Phòng Thường", 0);
				}else if(maLoaiPhong.equals("LP002")) {
					loaiPhong = new LoaiPhong("","Phòng Vip", 0);
				}
				TrangThaiPhong maTrangThaiPhong = new TrangThaiPhong(rs.getString(6),null );
				Phong ph = new Phong(maPhong, soNguoi, tenPhong, tinhTrang, loaiPhong, maTrangThaiPhong);
				dsph.add(ph);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dsph;
	}
	
	
	
	public boolean create(Phong ph) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement smt = null;
		int n = 0;
		try {
			smt = con.prepareStatement("insert into Phong(maPhong, soNguoi, tenPhong, tinhTrangPhong, maLP, maTTP) values(?, ?, ?, ?, ?, ?)");
			smt.setString(1, ph.getMaPhong());
			smt.setString(2, ph.getSoNguoi());
			smt.setString(3, ph.getTenPhong());
			smt.setString(4, ph.getTinTrangPhong());
			if(ph.getLoaiPhong().getTenLoaiPhong().equals("Phòng Thường")) {
				smt.setString(5, "LP001");
			}else if(ph.getLoaiPhong().getTenLoaiPhong().equals("Phòng Vip")) {
				smt.setString(5, "LP002");
			}
			smt.setString(6, ph.getTrangThaiPhong().getMaTrangThai());
			n = smt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public void remove(int index) {
		Phong nv = dsph.get(index);
		String sql = "delete from Phong where maPhong = ?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, nv.getMaPhong());
			statement.execute();
			dsph.remove(index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int size() {
		return dsph.size();
	}
	
	public static void deleteCustomerByCustomerId(String maKH) {
        connectDB.getInstance();
		try (Connection con = connectDB.getConnection()) {
            String sql = "DELETE FROM Phong WHERE maPhong = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, maKH);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Xoa thanh cong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public int getIndex(String maPH) {
		Phong temp = new Phong(maPH);
		return dsph.indexOf(temp);
	}
	
	public ArrayList<Phong> layttphongfrommaph(String maPhong){
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select * from Phong where maPhong = ?";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				String soNguoi = rs.getString(2);
				String tenPhong = rs.getString(3);
				String tinhTrang = rs.getString(4);
				LoaiPhong tenLoaiPhong = new LoaiPhong(null, rs.getString(5), 0);
				TrangThaiPhong maTrangThaiPhong = new TrangThaiPhong(rs.getString(6),null );
				Phong ph = new Phong(maPhong, soNguoi, tenPhong, tinhTrang, tenLoaiPhong, maTrangThaiPhong);
				dsph.add(ph);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dsph;
	}
	public boolean update(Phong ph) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement smt = null;
		int n = 0;
		try {
			smt = con.prepareStatement("UPDATE Phong SET soNguoi = ?, tenPhong = ?, tinhTrangPhong = ?, maLP = ? , maTTP = ? where maPhong = ?");

			smt.setString(1, ph.getSoNguoi());
			smt.setString(2, ph.getTenPhong());
			smt.setString(3, ph.getTinTrangPhong());
			if(ph.getLoaiPhong().getTenLoaiPhong().equals("Phòng Thường")) {
				smt.setString(4, "LP001");
			}else if(ph.getLoaiPhong().getTenLoaiPhong().equals("Phòng Vip")) {
				smt.setString(4, "LP002");
			}
			smt.setString(5, ph.getTrangThaiPhong().getMaTrangThai());
			smt.setString(6, ph.getMaPhong());
			
			n = smt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public int getMaxMaPH() {
        int maxMaKH = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connectDB.getInstance();
			conn = connectDB.getConnection();
            String query = "SELECT MAX(CONVERT(INT, SUBSTRING(maPhong, 2, LEN(maPhong)))) FROM Phong";
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
}
