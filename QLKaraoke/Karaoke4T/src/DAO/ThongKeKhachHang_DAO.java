package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.DecimalFormat;
import Entity.KhachHang;
import Entity.PhieuDatPhong;
import Entity.ThongKeKhachHang;
import connectDB.connectDB;

public class ThongKeKhachHang_DAO {
	ArrayList<ThongKeKhachHang> dskh;
	public ThongKeKhachHang_DAO () {
		dskh = new ArrayList<ThongKeKhachHang>();
	}
	public ArrayList<Entity.ThongKeKhachHang> doctubang(){
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select kh.maKH,tenKH,SDT,CMND,diaChi,thoiGianNhanPhong from PhieuDatPhong p\r\n"
					+ "inner join KhachHang kh\r\n"
					+ "on p.maKH = kh.maKH";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				KhachHang maKH = new KhachHang(rs.getString(1),null,null,null,null,null);
				KhachHang tenKH = new KhachHang(null,null,rs.getString(2),null,null,null);
				KhachHang SDT = new KhachHang(null,null,null,rs.getString(3),null,null);
				KhachHang CMND = new KhachHang(null,null,null,null,rs.getString(4),null);
				KhachHang diaChi = new KhachHang(null,null,null,null,null,rs.getString(5));
				PhieuDatPhong thoigianNhan = new PhieuDatPhong(null,null,null,rs.getString(6));
				ThongKeKhachHang tkkh = new ThongKeKhachHang(maKH,tenKH,SDT,CMND,diaChi,thoigianNhan);
				dskh.add(tkkh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dskh;
	}
	
	public ArrayList<Entity.ThongKeKhachHang> doctubangTheoThang(int ngay,String thang,int nam) {
	    try {
	        connectDB.getInstance();
			Connection con = connectDB.getConnection();
	        String sql = "select kh.maKH,tenKH,SDT,CMND,diaChi,thoiGianNhanPhong\r\n"
	        		+ "from PhieuDatPhong p\r\n"
	        		+ "inner join KhachHang kh\r\n"
	        		+ "on p.maKH = kh.maKH\r\n"
	        		+ "where Day(thoiGianNhanPhong) = ? and month(thoiGianNhanPhong) = ? and YEAR(thoiGianNhanPhong) = ?";
	        
	        // Sử dụng PreparedStatement để có thể truyền tham số
	        try (PreparedStatement sta = con.prepareStatement(sql)) {
	            // Truyền giá trị của biến thang vào tham số ?
	            sta.setInt(1, ngay);
	            sta.setString(2, thang);
	            sta.setInt(3, nam);

	            ResultSet rs = sta.executeQuery();
	            while (rs.next()) {
	            	KhachHang maKH = new KhachHang(rs.getString(1),null,null,null,null,null);
					KhachHang tenKH = new KhachHang(null,null,rs.getString(2),null,null,null);
					KhachHang SDT = new KhachHang(null,null,null,rs.getString(3),null,null);
					KhachHang CMND = new KhachHang(null,null,null,null,rs.getString(4),null);
					KhachHang diaChi = new KhachHang(null,null,null,null,null,rs.getString(5));
					PhieuDatPhong thoigianNhan = new PhieuDatPhong(null,null,null,rs.getString(6));
					ThongKeKhachHang tkkh = new ThongKeKhachHang(maKH,tenKH,SDT,CMND,diaChi,thoigianNhan);
					dskh.add(tkkh);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dskh;
	}

	public String[] tkdv(String ns, String ne) {
	    ArrayList<String> servicesList = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
	        String query = "SELECT ctdv.maDV, dv.tenDichVu, COUNT(*) AS SoLanGoi " +
	                "FROM ChiTietDichVu ctdv " +
	                "INNER JOIN DichVu dv ON ctdv.maDV = dv.maDV " +
	                "INNER JOIN HoaDon hd ON ctdv.maHD = hd.maHD " +
	                "WHERE ngayLap BETWEEN ? AND ? " +
	                "GROUP BY ctdv.maDV, dv.tenDichVu " +
	                "ORDER BY COUNT(*) DESC";

	        PreparedStatement pstmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        pstmt.setString(1, ns);
	        pstmt.setString(2, ne);
	        ResultSet rs = pstmt.executeQuery();

	        // Lấy ra dịch vụ được gọi nhiều nhất
	        if (rs.next()) {
	            String tenDVMost = rs.getString("tenDichVu");
	            int soLanMost = rs.getInt("SoLanGoi");
	            String serviceInfoMost = tenDVMost + ", Số lần gọi: " + soLanMost;
	            servicesList.add(serviceInfoMost);
	        }
	        if (rs.last()) {
//	            rs.previous();
	            String tenDVLeast = rs.getString("tenDichVu");
	            int soLanLeast = rs.getInt("SoLanGoi");
	            String serviceInfoLeast = tenDVLeast + ", Số lần gọi: " + soLanLeast;
	            servicesList.add(serviceInfoLeast);
	        } else {
	            String serviceInfoMost = servicesList.get(0);
	            servicesList.add(serviceInfoMost);
	        }

	        String[] services = servicesList.toArray(new String[0]);
	        return services;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	public String[] tkp(String ns, String ne) {
	    ArrayList<String> servicesList = new ArrayList<>(); 

	    try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
	        String query = "SELECT p.maPhong, COUNT(*) AS SoLanHatNhieuNhat\r\n"
	                + "FROM Phong p\r\n"
	                + "INNER JOIN ChiTietHoaDon cthd ON p.maPhong = cthd.maPhong\r\n"
	                + "INNER JOIN HoaDon hd ON cthd.MaHD = hd.MaHD\r\n"
	                + "WHERE ngayLap BETWEEN ? AND ?\r\n"
	                + "GROUP BY p.MaPhong\r\n"
	                + "ORDER BY COUNT(*) DESC;";

	        PreparedStatement pstmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        pstmt.setString(1, ns);
	        pstmt.setString(2, ne);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String maphMost = rs.getString("maPhong");
	            int soLanMost = rs.getInt("SoLanHatNhieuNhat");
	            String serviceInfoMost = maphMost + ", Số lần gọi: " + soLanMost;
	            servicesList.add(serviceInfoMost);
	        }

	        if (rs.last()) {
	            String maphLeast = rs.getString("maPhong");
	            int soLanLeast = rs.getInt("SoLanHatNhieuNhat");
	            String serviceInfoLeast = maphLeast + ", Số lần gọi: " + soLanLeast;
	            servicesList.add(serviceInfoLeast);
	        } else {
	            String serviceInfoMost = servicesList.get(0);
	            servicesList.add(serviceInfoMost);
	        }
	        String[] services = servicesList.toArray(new String[0]);
	        return services;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public int tkkh(String startDate, String endDate) {
        int customerCount = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
            String query = "SELECT COUNT(DISTINCT maKH) AS SoLuongKhach\r\n"
            		+ "FROM HoaDon\r\n"
            		+ "WHERE ngayLap BETWEEN ? AND ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customerCount = rs.getInt("SoLuongKhach");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerCount;
    }
	
	public int tkhd(String startDate, String endDate) {
        int HDCount = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
            String query = "SELECT COUNT(maHD) AS SoLuongHoaDon\r\n"
            		+ "FROM HoaDon\r\n"
            		+ "WHERE ngayLap BETWEEN ? AND ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                HDCount = rs.getInt("SoLuongHoaDon");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return HDCount;
    }
	public double tkdt(String startDate, String endDate) {
	    double DoanhThu = 0.0;
	    try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
	        String query = "SELECT SUM(tongTien) AS TongDoanhThu FROM HoaDon WHERE ngayLap BETWEEN ? AND ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, startDate);
	        pstmt.setString(2, endDate);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            DoanhThu = rs.getDouble("TongDoanhThu");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return DoanhThu;
	}
	
	public double dttb(String startDate, String endDate) {
	    double DoanhThuTB = 0.0;
	    try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
	        String query = "\r\n"
	        		+ "SELECT \r\n"
	        		+ "    SUM(tongTien) AS TongDoanhThu,\r\n"
	        		+ "    COUNT(maHD) AS SoLuongHoaDon,\r\n"
	        		+ "    SUM(tongTien) / COUNT(maHD) AS DoanhThuTrungBinh\r\n"
	        		+ "FROM HoaDon \r\n"
	        		+ "WHERE ngayLap BETWEEN ? AND ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, startDate);
	        pstmt.setString(2, endDate);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            DoanhThuTB = rs.getDouble("DoanhThuTrungBinh");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return DoanhThuTB;
	}
	public static String[] laydoanhthuphong(String ns, String ne) {
		      

		        ArrayList<String> resultList = new ArrayList<>();
		        DecimalFormat decimalFormat = new DecimalFormat("#,###");
		        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
		            if (conn != null) {
		                System.out.println("Kết nối thành công đến cơ sở dữ liệu!");

		                String query = "SELECT \r\n"
		                		+ "    SUM(hd.tongTien) AS TongDoanhThu\r\n"
		                		+ "FROM HoaDon hd\r\n"
		                		+ "Inner join ChiTietHoaDon cthd ON cthd.maHD = hd.maHD\r\n"
		                		+ "INNER JOIN Phong p ON cthd.maPhong = p.maPhong\r\n"
		                		+ "WHERE hd.ngayLap BETWEEN  ? and ?\r\n"
		                		+ "    AND p.maLP IN ('LP001', 'LP002') -- Chỉ lấy các phòng thường và VIP\r\n"
		                		+ "GROUP BY p.maLP";

		                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		                    pstmt.setString(1, ns);
		                    pstmt.setString(2, ne);
		                    ResultSet rs = pstmt.executeQuery();

		                    while (rs.next()) {
		                        double tongDoanhThu = rs.getDouble("TongDoanhThu");
		                        String formattedDoanhThu = decimalFormat.format(tongDoanhThu);
		                        resultList.add(formattedDoanhThu);
		                       
		                    }
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        String[] resultArray = new String[resultList.size()];
		        return resultList.toArray(resultArray);
	}
     ///////////
	public String[] tkdvtnv(String ns, String ne, String tennv) {
	    ArrayList<String> servicesList = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
	        String query = "SELECT ctdv.maDV, dv.tenDichVu, COUNT(*) AS SoLanGoi\r\n"
	        		+ "	                FROM ChiTietDichVu ctdv 			\r\n"
	        		+ "	                INNER JOIN DichVu dv ON ctdv.maDV = dv.maDV \r\n"
	        		+ "	                INNER JOIN HoaDon hd ON ctdv.maHD = hd.maHD \r\n"
	        		+ "					Inner join NhanVien nv On nv.maNV = hd.maNV\r\n"
	        		+ "	                WHERE ngayLap BETWEEN ? AND ? and nv.tenNV = ?\r\n"
	        		+ "	                GROUP BY ctdv.maDV, dv.tenDichVu\r\n"
	        		+ "	               ORDER BY COUNT(*) DESC";

	        PreparedStatement pstmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        pstmt.setString(1, ns);
	        pstmt.setString(2, ne);
	        pstmt.setString(3, tennv);
	        ResultSet rs = pstmt.executeQuery();

	        // Lấy ra dịch vụ được gọi nhiều nhất
	        if (rs.next()) {
	            String tenDVMost = rs.getString("tenDichVu");
	            int soLanMost = rs.getInt("SoLanGoi");
	            String serviceInfoMost = tenDVMost + ", Số lần gọi: " + soLanMost;
	            servicesList.add(serviceInfoMost);
	        }
	        if (rs.last()) {
//	            rs.previous();
	            String tenDVLeast = rs.getString("tenDichVu");
	            int soLanLeast = rs.getInt("SoLanGoi");
	            String serviceInfoLeast = tenDVLeast + ", Số lần gọi: " + soLanLeast;
	            servicesList.add(serviceInfoLeast);
	        } else {
	            String serviceInfoMost = servicesList.get(0);
	            servicesList.add(serviceInfoMost);
	        }

	        String[] services = servicesList.toArray(new String[0]);
	        return services;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	public String[] tkptnv(String ns, String ne, String tennv) {
	    ArrayList<String> servicesList = new ArrayList<>(); 

	    try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
	        String query = "				   SELECT p.maPhong, COUNT(*) AS SoLanHatNhieuNhat\r\n"
	        		+ "	                FROM Phong p\r\n"
	        		+ "	                INNER JOIN ChiTietHoaDon cthd ON p.maPhong = cthd.maPhong\r\n"
	        		+ "	               INNER JOIN HoaDon hd ON cthd.MaHD = hd.MaHD\r\n"
	        		+ "				   Inner join NhanVien nv On nv.maNV = hd.maNV\r\n"
	        		+ "	                WHERE ngayLap BETWEEN ? and ? and nv.tenNV = ?\r\n"
	        		+ "	                GROUP BY p.MaPhong\r\n"
	        		+ "	               ORDER BY COUNT(*) DESC;";

	        PreparedStatement pstmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        pstmt.setString(1, ns);
	        pstmt.setString(2, ne);
	        pstmt.setString(3, tennv);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String maphMost = rs.getString("maPhong");
	            int soLanMost = rs.getInt("SoLanHatNhieuNhat");
	            String serviceInfoMost = maphMost + ", Số lần gọi: " + soLanMost;
	            servicesList.add(serviceInfoMost);
	        }

	        if (rs.last()) {
	            String maphLeast = rs.getString("maPhong");
	            int soLanLeast = rs.getInt("SoLanHatNhieuNhat");
	            String serviceInfoLeast = maphLeast + ", Số lần gọi: " + soLanLeast;
	            servicesList.add(serviceInfoLeast);
	        } else {
	            String serviceInfoMost = servicesList.get(0);
	            servicesList.add(serviceInfoMost);
	        }
	        String[] services = servicesList.toArray(new String[0]);
	        return services;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public int tkkhtnv(String startDate, String endDate, String tennv) {
        int customerCount = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
            String query = "				   SELECT COUNT(DISTINCT maKH) AS SoLuongKhach\r\n"
            		+ "            		FROM HoaDon hd\r\n"
            		+ "					Inner join NhanVien nv On nv.maNV = hd.maNV\r\n"
            		+ "            		WHERE ngayLap BETWEEN ? and ? and nv.tenNV = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            pstmt.setString(3, tennv);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customerCount = rs.getInt("SoLuongKhach");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerCount;
    }
	
	public int tkhdtnv(String startDate, String endDate, String tennv) {
        int HDCount = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
            String query = "SELECT COUNT(maHD) AS SoLuongHoaDon\r\n"
            		+ "FROM HoaDon hd\r\n"
            		+ "Inner join NhanVien nv On nv.maNV = hd.maNV\r\n"
            		+ "WHERE ngayLap BETWEEN ? AND ? and nv.tenNV = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            pstmt.setString(3, tennv);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                HDCount = rs.getInt("SoLuongHoaDon");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return HDCount;
    }
	public double tkdttnv(String startDate, String endDate,String tennv) {
	    double DoanhThu = 0.0;
	    try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
	        String query = "					SELECT SUM(tongTien) AS TongDoanhThu FROM HoaDon hd\r\n"
	        		+ "					Inner join NhanVien nv On nv.maNV = hd.maNV\r\n"
	        		+ "					WHERE ngayLap BETWEEN ? and ? and nv.tenNV = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, startDate);
	        pstmt.setString(2, endDate);
	        pstmt.setString(3, tennv);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            DoanhThu = rs.getDouble("TongDoanhThu");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return DoanhThu;
	}
	
	public double dttbtnv(String startDate, String endDate, String tennv) {
	    double DoanhThuTB = 0.0;
	    try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
	        String query = "\r\n"
	        		+ "SELECT \r\n"
	        		+ "    SUM(tongTien) AS TongDoanhThu,\r\n"
	        		+ "    COUNT(maHD) AS SoLuongHoaDon,\r\n"
	        		+ "    SUM(tongTien) / COUNT(maHD) AS DoanhThuTrungBinh\r\n"
	        		+ "FROM HoaDon hd\r\n"
	        		+ "Inner join NhanVien nv On nv.maNV = hd.maNV\r\n"
	        		+ "WHERE ngayLap BETWEEN ? AND ? and nv.tenNV = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, startDate);
	        pstmt.setString(2, endDate);
	        pstmt.setString(3, tennv);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            DoanhThuTB = rs.getDouble("DoanhThuTrungBinh");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return DoanhThuTB;
	}
	public static String[] laydoanhthuphongtnv(String ns, String ne, String tennv) {
		      

		        ArrayList<String> resultList = new ArrayList<>();
		        DecimalFormat decimalFormat = new DecimalFormat("#,###");
		        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;")) {
		            if (conn != null) {
		                System.out.println("Kết nối thành công đến cơ sở dữ liệu!");

		                String query = "SELECT \r\n"
		                		+ "    SUM(hd.tongTien) AS TongDoanhThu\r\n"
		                		+ "FROM HoaDon hd\r\n"
		                		+ "Inner join ChiTietHoaDon cthd ON cthd.maHD = hd.maHD\r\n"
		                		+ "INNER JOIN Phong p ON cthd.maPhong = p.maPhong\r\n"
		                		+ "Inner join NhanVien nv On nv.maNV = hd.maNV\r\n"
		                		+ "WHERE hd.ngayLap BETWEEN  ? and ?\r\n"
		                		+ "    AND p.maLP IN ('LP001', 'LP002') and nv.tenNV = ?\r\n"
		                		+ "GROUP BY p.maLP";

		                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		                    pstmt.setString(1, ns);
		                    pstmt.setString(2, ne);
		                    pstmt.setString(3, tennv);
		                    ResultSet rs = pstmt.executeQuery();

		                    while (rs.next()) {
		                        double tongDoanhThu = rs.getDouble("TongDoanhThu");
		                        String formattedDoanhThu = decimalFormat.format(tongDoanhThu);
		                        resultList.add(formattedDoanhThu);
		                       
		                    }
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        String[] resultArray = new String[resultList.size()];
		        return resultList.toArray(resultArray);
		    
        }

}
