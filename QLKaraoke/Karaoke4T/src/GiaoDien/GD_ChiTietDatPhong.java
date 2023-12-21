package GiaoDien;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTable;
import testbutton.Buttontest;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class GD_ChiTietDatPhong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String LayMaHD;
	private JLabel lbl_tenphong,lbl_giatien,lbl_soluongnguoi,lbl_loaiphong,lbl_trangthaiphong,lbl_tennhanvien,lbl_tenkhach,lbl_sdtKH,lblGioVao;
	private JTable table = new JTable();
	private DefaultTableModel model = (DefaultTableModel) table.getModel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GD_ChiTietDatPhong() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1039, 658);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBorder(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JPanel pnl_chitietphong = new JPanel();
		pnl_chitietphong.setBackground(new Color(133, 133, 194));
		pnl_chitietphong.setBounds(0, 0, 1046, 66);
		contentPane.add(pnl_chitietphong);
		pnl_chitietphong.setLayout(null);
		
		JLabel lbl_chiTietPhong = new JLabel("Chi tiết phòng");
		lbl_chiTietPhong.setForeground(new Color(255, 255, 255));
		lbl_chiTietPhong.setBounds(404, 11, 214, 45);
		pnl_chitietphong.add(lbl_chiTietPhong);
		lbl_chiTietPhong.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JPanel pnl_thongtinphong = new JPanel();
		pnl_thongtinphong.setBackground(new Color(230, 230, 230));
		pnl_thongtinphong.setBounds(28, 77, 969, 504);
		contentPane.add(pnl_thongtinphong);
		pnl_thongtinphong.setLayout(null);
		
		JPanel pnl_thongtinphongtxt = new JPanel();
		pnl_thongtinphongtxt.setBounds(34, 9, 434, 39);
		pnl_thongtinphong.add(pnl_thongtinphongtxt);
		
		JLabel lblNewLabel = new JLabel("Thông tin phòng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnl_thongtinphongtxt.add(lblNewLabel);
		
		JLabel lbl_iconmicro = new JLabel("");
		lbl_iconmicro.setIcon(new ImageIcon(GD_ChiTietPhong.class.getResource("/Imgs/Mic.png")));
		lbl_iconmicro.setBounds(404, 65, 64, 64);
		pnl_thongtinphong.add(lbl_iconmicro);
		
		lbl_tenphong = new JLabel("Phòng ");
		lbl_tenphong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_tenphong.setBounds(34, 80, 245, 25);
		pnl_thongtinphong.add(lbl_tenphong);
		
		JPanel pnl_showthongtin = new JPanel();
		pnl_showthongtin.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		pnl_showthongtin.setBackground(new Color(230, 230, 230));
		pnl_showthongtin.setBounds(34, 140, 434, 215);
		pnl_thongtinphong.add(pnl_showthongtin);
		pnl_showthongtin.setLayout(null);
		
		 lbl_trangthaiphong = new JLabel("Trạng thái phòng :");
		lbl_trangthaiphong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_trangthaiphong.setBounds(25, 11, 382, 25);
		pnl_showthongtin.add(lbl_trangthaiphong);
		
		 lbl_loaiphong = new JLabel("Loại phòng :");
		lbl_loaiphong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_loaiphong.setBounds(25, 57, 382, 25);
		pnl_showthongtin.add(lbl_loaiphong);
		
		 lbl_soluongnguoi = new JLabel("Số lượng người :");
		lbl_soluongnguoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_soluongnguoi.setBounds(25, 112, 382, 25);
		pnl_showthongtin.add(lbl_soluongnguoi);
		
		 lbl_giatien = new JLabel("Giá tiền/h :");
		lbl_giatien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_giatien.setBounds(25, 168, 382, 25);
		pnl_showthongtin.add(lbl_giatien);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(300, 377, 364, 2);
		pnl_thongtinphong.add(separator);
		
		lbl_tenkhach = new JLabel("Tên Khách Hàng:");
		lbl_tenkhach.setBounds(34, 390, 282, 15);
		pnl_thongtinphong.add(lbl_tenkhach);
		lbl_tenkhach.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lbl_sdtKH = new JLabel("SĐT Khách Hàng:");
		lbl_sdtKH.setBounds(34, 416, 282, 15);
		pnl_thongtinphong.add(lbl_sdtKH);
		lbl_sdtKH.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lbl_tennhanvien = new JLabel("Tên Nhân Viên:");
		lbl_tennhanvien.setBounds(34, 442, 282, 15);
		pnl_thongtinphong.add(lbl_tennhanvien);
		lbl_tennhanvien.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblGioVao = new JLabel("Giờ Vào:");
		lblGioVao.setBounds(34, 469, 341, 15);
		pnl_thongtinphong.add(lblGioVao);
		lblGioVao.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JPanel pnl_thongtinphongtxt_1 = new JPanel();
		pnl_thongtinphongtxt_1.setBounds(478, 9, 448, 39);
		pnl_thongtinphong.add(pnl_thongtinphongtxt_1);
		
		JLabel lblThongTinDichVu = new JLabel("Thông tin dịch vụ");
		lblThongTinDichVu.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnl_thongtinphongtxt_1.add(lblThongTinDichVu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(478, 140, 457, 215);
		pnl_thongtinphong.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnCapNhatDichVu = new JButton("Cập nhật dịch vụ");
		btnCapNhatDichVu.setBounds(768, 111, 158, 23);
		pnl_thongtinphong.add(btnCapNhatDichVu);
		model = (DefaultTableModel) table.getModel();
		model.addColumn("Mã DV");
		model.addColumn("Tên Dịch Vụ");
		model.addColumn("Đơn Giá");
		model.addColumn("Số Lượng");
		
		btnCapNhatDichVu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_DatDichVu dv = new GD_DatDichVu();
				dv.setVisible(true);
				dispose();
			}
		});
//		Buttontest btntstQuayLai_1 = new Buttontest();
//		btntstQuayLai_1.setText("Cập nhật dịch vụ");
//		btntstQuayLai_1.setForeground(Color.WHITE);
//		btntstQuayLai_1.setFont(new Font("Tahoma", Font.BOLD, 16));
//		btntstQuayLai_1.setBorder(null);
//		btntstQuayLai_1.setBackground(new Color(128, 255, 128));
//		btntstQuayLai_1.setBounds(754, 377, 181, 39);
//		pnl_thongtinphong.add(btntstQuayLai_1);
		
		Buttontest btntstQuayLai = new Buttontest();
		btntstQuayLai.setText("Quay lại");
		btntstQuayLai.setForeground(Color.WHITE);
		btntstQuayLai.setFont(new Font("Tahoma", Font.BOLD, 16));
		btntstQuayLai.setBorder(null);
		btntstQuayLai.setBackground(new Color(19, 142, 234));
		btntstQuayLai.setBounds(38, 582, 111, 39);
		contentPane.add(btntstQuayLai);
		
		btntstQuayLai.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				 GD_DatPhong dat = new GD_DatPhong();
				 dat.setVisible(true);
				 dat.setLocationRelativeTo(null);
		            dispose();
			}
		});
		
		
//		loadChiTietDichVuDaDat(tam);
//		loadChiTietPhong(tam);
	}
	public void loadChiTietDichVuDaDat(String maHD) {
		// Thông tin kết nối đến cơ sở dữ liệu
        String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
        String username = "sa";
        String password = "123";
        
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection connection = DriverManager.getConnection(url, username, password);

	        String sql = "select dv.maDV, dv.tenDichVu ,dv.donGia, ctdv.soLuong\r\n"
	        		+ "from ChiTietDichVu ctdv\r\n"
	        		+ "inner join DichVu dv ON dv.maDV = ctdv.maDV\r\n"
	        		+ "inner join ChiTietHoaDon cthd ON cthd.maHD = ctdv.maHD where cthd.maHD = ?";

	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, maHD);

	        ResultSet resultSet = statement.executeQuery();
//	        table = new JTable();
//	        model =(DefaultTableModel) table.getModel();
	        
	        // Xóa dữ liệu cũ trong bảng
	        model.setRowCount(0);

	        while (resultSet.next()) {
	            String maDV = resultSet.getString("maDV");
	            String tenDV = resultSet.getString("tenDichVu");
	            double gia = resultSet.getDouble("donGia");
	            int soLuong = resultSet.getInt("soLuong");
	            
	            // Thêm dòng mới vào bảng
	            Object [] ds = {maDV, tenDV, gia, soLuong};
	            model.addRow(ds);
	            
	            
//	            System.out.println(ds);
	        }table.setModel(model);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	

    public void loadChiTietPhong(String maPhong) {
		// Thông tin kết nối đến cơ sở dữ liệu
        String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
        String username = "sa";
        String password = "123";
        
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "select tenPhong,tenTrangThaiPhong,tenLoaiPhong,giaTien from Phong p\r\n"
            		+ "inner join TrangThaiPhong ttp\r\n"
            		+ "on p.maTTP = ttp.maTTP\r\n"
            		+ "inner join LoaiPhong lp\r\n"
            		+ "on p.maLP = lp.maLP\r\n"
            		+ "where maPhong = ? ";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,maPhong);
           
            ResultSet resultSet = statement.executeQuery();
            // Lặp qua các dòng kết quả và thêm vào JComboBox
            while (resultSet.next()) {
                String columnName1 = resultSet.getString("tenPhong");
                String columnName2 = resultSet.getString("tenTrangThaiPhong");
                String columnName3 = resultSet.getString("tenLoaiPhong");
                String columnName4 = resultSet.getString("giaTien");
                
//                String columnName5 = resultSet.getString("SDT");
//                String columnName6 = resultSet.getString("tenKH");
//                String columnName7 = resultSet.getString("tenNV");
//                String columnName8 = resultSet.getString("soLuongDichVu");
//                String columnName9 = resultSet.getString("gioNhanPhong");
//                String columnName10 = resultSet.getString("thoiLuong");
//                String columnName11 = resultSet.getString("soNguoi");
//                String columnName12 = resultSet.getString("donGia");
//                String columnName13 = resultSet.getString("tongTien");
                
                
                lbl_tenphong.setText("Tên phòng hát: " + columnName1);
                lbl_trangthaiphong.setText("Trạng thái phòng hát: " + columnName2);
                lbl_loaiphong.setText("Loại phòng hát: " + columnName3);
                lbl_giatien.setText("Giá phòng hát: " + columnName4 + " VND");
//                lbl_tienphong.setText("Tiền phòng: " + columnName4 + " VND");
//                lbl_sdtKH.setText("Số điện thoại khách hàng: " + columnName5);
//                lbl_tenkhach.setText("Tên khách hàng: " + columnName6);
//                lbl_tennhanvien.setText("Tên nhân viên: " + columnName7);
//                lblTongDichVu.setText("Tổng số lượng dịch vụ: " + columnName8);
//                lblGioVao.setText("Giờ nhận phòng: " + columnName9);
//                lblThoiLuong.setText("Thời lượng hát: " + columnName10);
//                lbl_soluongnguoi.setText("Sức chứa: " + columnName11);
//                lbl_tiendichvu.setText("Tiền dịch vụ " + columnName12 + " VND");
//                lbl_tongcong.setText("Tổng cộng: " + columnName13 + " VND");
               
            }

            // Đóng các tài nguyên
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
    
    public void loadChiTietKhachHangDaDatPhong(String maPhong) {
		// Thông tin kết nối đến cơ sở dữ liệu
        String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
        String username = "sa";
        String password = "123";
        
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "select  tenKH,kh.SDT,tenNV,thoiGianNhanPhong,soLuongKH \r\n"
            		+ "from PhieuDatPhong pdp\r\n"
            		+ "inner join KhachHang kh\r\n"
            		+ "on pdp.maKH = kh.maKH\r\n"
            		+ "inner join NhanVien nv\r\n"
            		+ "on pdp.maNV = nv.maNV\r\n"
            		+ "where maPhong = ?";
            
            PreparedStatement statement = connection.prepareStatement(sql);
           
            statement.setString(1,maPhong);
            
           
            ResultSet resultSet = statement.executeQuery();
            // Lặp qua các dòng kết quả và thêm vào JComboBox
            while (resultSet.next()) {
               
                String columnName5 = resultSet.getString("SDT");
                String columnName6 = resultSet.getString("tenKH");
                String columnName7 = resultSet.getString("tenNV");
                String columnName9 = resultSet.getString("thoiGianNhanPhong");
                String columnName10 = resultSet.getString("soLuongKH");
                
                lbl_sdtKH.setText("Số điện thoại khách hàng: " + columnName5);
                lbl_tenkhach.setText("Tên khách hàng: " + columnName6);
                lbl_tennhanvien.setText("Tên nhân viên: " + columnName7);
                lblGioVao.setText("Giờ nhận phòng: " + columnName9);
                lbl_soluongnguoi.setText("Số lượng người trong phòng: " +columnName10);
               

               
            }

            // Đóng các tài nguyên
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
    
    public void layMaHoaDon(String maPhong) {
        String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
        String username = "sa";
        String password = "123";
        String maHD = null;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sqlQuery = "SELECT *\r\n"
            		+ "FROM ChiTietHoaDon\r\n"
            		+ "WHERE maHD = (SELECT MAX(maHD) FROM ChiTietHoaDon WHERE maPhong = ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, maPhong);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        maHD = resultSet.getString("maHD");
                        LayMaHD = maHD;
//                        System.out.println(maHD);
                        System.out.println(LayMaHD);
                        loadChiTietDichVuDaDat(LayMaHD);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
