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

public class QLDV_DAO {
	ArrayList<DichVu> dsdv;
	ArrayList<DatDichVu> ddv;
	public QLDV_DAO() {
		dsdv = new ArrayList<DichVu>();
		ddv = new ArrayList<DatDichVu>();
	}
	
	public ArrayList<DichVu> docbang(){
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select maDV, donGia, soLuong, tenDichVu from DichVu";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				String maDichVu = rs.getString(1);
				double donGia = rs.getDouble(2);
				int soLuong = rs.getInt(3);
				String tenDichVu = rs.getString(4);
				DichVu dv = new DichVu(maDichVu, donGia, soLuong,tenDichVu);
				dsdv.add(dv);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dsdv;
	}
	
	public boolean themDichVu(DichVu dv) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement smt = null;
		int n = 0;
		try {
			smt = con.prepareStatement("insert into DichVu values(?, ?, ?, ?)");
			smt.setString(1, dv.getMaDichVu());
			smt.setDouble(2, dv.getGiaDichVu());
			smt.setInt(3, dv.getSoLuongDichVu());
			smt.setString(4, dv.getTenDichVu());
			n = smt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean update(DichVu kh) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement smt = null;
		int n = 0;
		try {
			smt = con.prepareStatement("UPDATE DichVu SET donGia = ?, soLuong = ?, tenDichVu = ? where maDv = ?");

			smt.setDouble(1, kh.getGiaDichVu());
			smt.setInt(2, kh.getSoLuongDichVu());
			smt.setString(3, kh.getTenDichVu());
			smt.setString(4, kh.getMaDichVu());

			
			n = smt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public DichVu getDV(int index) {
		return dsdv.get(index);
	}
	
	public static void deleteDvbymaDV(String maDV) {
	    connectDB.getInstance();
		try (Connection con = connectDB.getConnection()) {
	        String sql = "DELETE from DichVu where maDV = ?";
	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            preparedStatement.setString(1, maDV);
	            int n = preparedStatement.executeUpdate();
	            
	            if (n > 0) {
	                JOptionPane.showMessageDialog(null, "Xoa thanh cong");
	            } else {
	                JOptionPane.showMessageDialog(null, "Khong tim thay DichVu co maDV = " + maDV);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public int getMaxMaDV() {
        int maxMaDV = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connectDB.getInstance();
			conn = connectDB.getConnection();
            String query = "SELECT MAX(CONVERT(INT, SUBSTRING(maDV, 3, LEN(maDV)))) FROM DichVu";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	maxMaDV = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxMaDV;
    }
	
	public ArrayList<DichVu> getDs(){
		return dsdv;
	}
	
	 public DichVu layDichVuTheoMa(String maDV) {
	        DichVu dichVu = null;
	        String query = "Select maDV, tenDichVu, donGia from DichVu where maDV = ?";
	        connectDB.getInstance();
			Connection con = connectDB.getConnection();
	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	            preparedStatement.setString(1, maDV);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    String maDichVu = resultSet.getString("maDV");
	                    String tenDichVu = resultSet.getString("tenDichVu");
	                    double donGia = resultSet.getDouble("donGia");

	                    dichVu = new DichVu(maDichVu, tenDichVu, donGia);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return dichVu;
	    }
	public ArrayList<Entity.DichVu> detaiedDichVu(String madv){
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stm = null;
		try {
			
			String sql = "Select maDV, tenDichVu, donGia from DichVu where maDV = ?";
			stm = con.prepareStatement(sql);
			stm.setString(1, madv);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String ten = rs.getString(2);
				Double gia = rs.getDouble(3);
				DichVu s = new DichVu(madv, ten, gia);
				dsdv.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsdv;
	}
	
	public String getmaHDbymaPhong(String maPhong) {
        String maHD = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            
            connectDB.getInstance();
			conn = connectDB.getConnection();
            
            String sqlQuery = "select maHD from ChiTietHoaDon where maPhong = ?";
            
           
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, maPhong); 
            
            rs = pstmt.executeQuery();

            if (rs.next()) {
                maHD = rs.getString("maHD");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return maHD;
    }
	
	public void luuDulieuDatDichVu(ArrayList<ChiTietDichVu> dsDichVuDaDat, String maHD) {
        try {
            connectDB.getInstance();
			Connection connection = connectDB.getConnection();
            String sql = "INSERT INTO ChiTietDichVu (maHD, maDV, soLuong) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (ChiTietDichVu ddichVu : dsDichVuDaDat) {
            	statement.setString(1, maHD);
                statement.setString(2, ddichVu.getDichVu().getMaDichVu());
                statement.setInt(3, ddichVu.getSoLuong());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    public String getMaDVFromMaHD(String mahd) {
    	String madv = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            
            connectDB.getInstance();
			conn = connectDB.getConnection();
            
            String sqlQuery = "select cthd.maHD, ctdv.maDv, ctdv.soLuong\r\n"
            		+ "from ChiTietDichVu ctdv\r\n"
            		+ "inner join ChiTietHoaDon cthd ON cthd.maHD = ctdv.maHD where ctdv.maHD = ?";
            
           
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, mahd); 
            
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	
                madv = rs.getString("maDV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return madv;
    }
    
	public String getsoluongbymahdmadv(String madv, String mahd) {
        String sl = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            
            connectDB.getInstance();
			conn = connectDB.getConnection();
            
            String sqlQuery = "select soLuong from ChiTietDichVu where maDV = ? and  maHD = ?";
            
           
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, madv); 
            pstmt.setString(2, mahd); 
            
            rs = pstmt.executeQuery();

            if (rs.next()) {
                sl = rs.getString("soLuong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return sl;
    }
	public void updatesoLuongbyMaHDMaDV(String maHD, String maDV, int sl) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try  {
        	connectDB.getInstance();
			conn = connectDB.getConnection();
            
        	String query = "UPDATE ChiTietDichVu SET soLuong = ? WHERE maHD = ? AND maDV = ?";
        	pstmt = conn.prepareStatement(query);
        	pstmt.setInt(1, sl);
            pstmt.setString(2, maHD);
            pstmt.setString(3, maDV);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public boolean checkMaHD_MaDVExist(String maHD, String maDV) {
        boolean exists = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
        	connectDB.getInstance();
			conn = connectDB.getConnection();
        	String query = "SELECT COUNT(*) AS count FROM ChiTietDichVu WHERE maHD = ? AND maDV = ?";
        	pstmt = conn.prepareStatement(query);
            pstmt.setString(1, maHD);
            pstmt.setString(2, maDV);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
	
}
