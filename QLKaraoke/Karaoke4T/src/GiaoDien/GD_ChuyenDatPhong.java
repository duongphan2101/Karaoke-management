package GiaoDien;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import testbutton.Buttontest;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.text.DecimalFormat;



public class GD_ChuyenDatPhong extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtMaPhong;
	private JLabel lbl_machuyenphong, lbl_chuyenphong, lbl_maphonghientai;
	private JLabel lblmaphong;
	DefaultTableModel model;
	private testbutton.Buttontest btnChuyenPhong, btnLamMoi, btntimkiem;
	private String layMaPDP,layMaPhongMoi,LayMaHD;

	/**
	 * Launch the application.
	 */



	/**
	 * Create the frame.
	 */
	public GD_ChuyenDatPhong() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 682);
		setTitle("Chuyển Phòng");
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel1_chuyenphong = new JPanel();
		panel1_chuyenphong.setBackground(new Color(0, 88, 176));
		panel1_chuyenphong.setBounds(0, 0, 806, 96);
		contentPane.add(panel1_chuyenphong);
		panel1_chuyenphong.setLayout(null);
		
		lbl_chuyenphong = new JLabel("CHUYỂN PHÒNG");
		lbl_chuyenphong.setForeground(new Color(255, 255, 255));
		lbl_chuyenphong.setBounds(258, 25, 266, 49);
		panel1_chuyenphong.add(lbl_chuyenphong);
		lbl_chuyenphong.setFont(new Font("Tahoma", Font.BOLD, 32));
		
		JLabel lbl_phonghientai = new JLabel("Phòng hiện tại:");
		lbl_phonghientai.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl_phonghientai.setBounds(20, 106, 174, 31);
		contentPane.add(lbl_phonghientai);
		
		lbl_maphonghientai = new JLabel();
		lbl_maphonghientai.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_maphonghientai.setBounds(204, 108, 71, 27);
		contentPane.add(lbl_maphonghientai);
		
		lbl_machuyenphong = new JLabel();
		lbl_machuyenphong.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_machuyenphong.setBounds(325, 108, 71, 27);
	    contentPane.add(lbl_machuyenphong);
		
		JLabel lblmuiten = new JLabel();
		lblmuiten.setHorizontalAlignment(SwingConstants.CENTER);
		lblmuiten.setIcon(new ImageIcon(GD_ChuyenDatPhong.class.getResource("/Imgs/Arrow.png")));
		lblmuiten.setBounds(204, 108, 171, 27);
		contentPane.add(lblmuiten);
		
		btntimkiem = new Buttontest();
		btntimkiem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btntimkiem.setText("TÌM KIẾM");
		btntimkiem.setBackground(new Color(204, 153, 204));
		btntimkiem.setIcon(new ImageIcon(GD_ChuyenDatPhong.class.getResource("/Imgs/search.png")));
		btntimkiem.setBounds(669, 99, 121, 48);
		btntimkiem.setRippleColor(new Color(255, 255, 255));
		btntimkiem.setShadowColor(new Color(0,0,0));
		btntimkiem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (txtMaPhong.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã phòng cần tìm!");
		        } else {
		            // Gọi phương thức timKiemTheoMaPhong với mã phòng nhập từ JTextField
		            timKiemTheoMaPhong(txtMaPhong.getText());
		        }
		    }
		});
		contentPane.add(btntimkiem);
	    
	    lblmaphong = new JLabel("Mã phòng:");
	    lblmaphong.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    lblmaphong.setBounds(420, 106, 174, 31);
	    contentPane.add(lblmaphong);
		
		txtMaPhong = new JTextField();
		txtMaPhong.setBounds(544, 106, 115, 31);
		contentPane.add(txtMaPhong);
		txtMaPhong.setColumns(10);
		contentPane.add(txtMaPhong);
		
		testbutton.Buttontest quayLai = new testbutton.Buttontest();
		quayLai.setBorder(null);
		quayLai.setText("QUAY LẠI");
		quayLai.setForeground(Color.WHITE);
		quayLai.setFont(new Font("Tahoma", Font.BOLD, 13));
		quayLai.setBackground(new Color(0, 128, 255));
		quayLai.setBounds(20, 509, 140, 55);
		quayLai.setIcon(new ImageIcon(GD_ChuyenDatPhong.class.getResource("/Imgs/back.png")));
		quayLai.setRippleColor(new Color(255, 255, 255));
		quayLai.setShadowColor(new Color(0,0,0));
		quayLai.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				GD_DatPhong datPhong = new GD_DatPhong();
				datPhong.setVisible(true);
				datPhong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				datPhong.theoDoiThaoTac("Bạn vừa mới chuyển phòng cho: ",lbl_maphonghientai.getText());
				dispose();
			}
		});
	    contentPane.add(quayLai);
	    quayLai.setLayout(null);
	     
	    btnChuyenPhong = new testbutton.Buttontest();
	    btnChuyenPhong.setText("CHUYỂN PHÒNG");
	    btnChuyenPhong.setForeground(Color.WHITE);
	    btnChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnChuyenPhong.setBorder(null);
	    btnChuyenPhong.setBackground(new Color(51, 153, 102));
	    btnChuyenPhong.setBounds(635, 509, 140, 55);
	    btnChuyenPhong.setRippleColor(new Color(255, 255, 255));
	    btnChuyenPhong.setShadowColor(new Color(0,0,0));
	    btnChuyenPhong.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            GD_ThuePhong tp = new GD_ThuePhong();
	            tp.capNhatTrangThaiBanThanhTrong(lbl_maphonghientai.getText());
	            tp.capNhatTrangThaiTrongThanhBan(lbl_machuyenphong.getText());

	            LayMaHD = layMaHoaDon(lbl_maphonghientai.getText());
	            layMaPhongMoi =lbl_machuyenphong.getText();
	        	layMaPDP = layMaPDP(lbl_maphonghientai.getText());

	        	System.out.println(layMaPDP);
	        	System.out.println(layMaPhongMoi);
	        	updatePhieuDatPhong(layMaPDP, layMaPhongMoi);
	        	updateChiTietHoaDon(LayMaHD,layMaPhongMoi);
	            
	        	JOptionPane.showMessageDialog(null,"Chuyển phòng: " + lbl_maphonghientai.getText() + " sang " + lbl_machuyenphong.getText()+" thành công");
	        	tp.theoDoiThaoTac("Bạn vừa mới chuyển phòng cho: ",lbl_maphonghientai.getText());
	        	

	        } 
	    });
	    contentPane.add(btnChuyenPhong);
	    
	    btnLamMoi = new Buttontest();
	    btnLamMoi.setText("LÀM MỚI");
	    btnLamMoi.setForeground(Color.WHITE);
	    btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnLamMoi.setBorder(null);
	    btnLamMoi.setBackground(new Color(255, 0, 0));
	    btnLamMoi.setBounds(170, 509, 140, 55);
	    btnLamMoi.setIcon(new ImageIcon(GD_ChuyenDatPhong.class.getResource("/Imgs/reset.png")));
	    btnLamMoi.setRippleColor(new Color(255, 255, 255));
	    btnLamMoi.setShadowColor(new Color(0,0,0));
	    contentPane.add(btnLamMoi);
	    btnLamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lbl_machuyenphong.setText("");
				txtMaPhong.setText("");
			}
		});
		table = new JTable();
		table.setRowHeight(30);
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            // Lấy mã phòng từ hàng đã chọn và cập nhật lbl_machuyenphong
		            String selectedRoomCode = (String) table.getValueAt(selectedRow, 0);
		            lbl_machuyenphong.setText(selectedRoomCode);
		        }
		    }
		});

		
	    model = new DefaultTableModel();
		model.addColumn("Mã phòng");
		model.addColumn("Trạng thái phòng");
		model.addColumn("Loại phòng");
		model.addColumn("Số người");
		model.addColumn("Giá tiền mỗi giờ");
		table.setModel(model);
		addTable();
	    
	    // Create a JScrollPane and add the table to it
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(20, 188, 765, 290);
	    contentPane.add(scrollPane);
	    
	    scrollPane.setBorder(javax.swing.BorderFactory.createCompoundBorder(
	    	    javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10),
	    	    javax.swing.BorderFactory.createCompoundBorder(
	    	        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1),
	    	        javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)
	    	    )
	    ));
	    
	    JLabel logo = new JLabel();
	    logo.setHorizontalAlignment(SwingConstants.CENTER);
	    logo.setBounds(339, 509, 269, 55);
	    logo.setIcon(new ImageIcon(GD_ChuyenDatPhong.class.getResource("/Imgs/Karaoke4t.png")));
	    contentPane.add(logo);

	}

	private void addTable() {
	    if (model == null) {
	        model = new DefaultTableModel();
	    }

	    try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=Karaoke4T;user=sa;password=123")) {
	        String sql = "SELECT p.maPhong, ttp.tenTrangThaiPhong, lp.tenLoaiPhong, p.soNguoi, lp.giaTien " +
	                     "FROM Phong p " +
	                     "JOIN TrangThaiPhong ttp ON ttp.maTTP = p.maTTP " +
	                     "JOIN LoaiPhong lp ON lp.maLP = p.maLP " +
	                     "WHERE ttp.maTTP = 'TTP003'";

	        try (PreparedStatement statement = connection.prepareStatement(sql);
	             ResultSet resultSet = statement.executeQuery()) {

	            // Clear existing table data
	            model.setRowCount(0);

	            // Populate the table with the query results
	            while (resultSet.next()) {
	                String maPhong = resultSet.getString("maPhong");
	                String trangThai = resultSet.getString("tenTrangThaiPhong");
	                String loaiPhong = resultSet.getString("tenLoaiPhong");
	                int soNguoi = resultSet.getInt("soNguoi");
	                String giaTien = resultSet.getString("giaTien");

	                addRowToTable(maPhong, trangThai, loaiPhong, soNguoi, giaTien);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private void timKiemTheoMaPhong(String maPhong) {
	    if (model == null) {
	        model = new DefaultTableModel();
	    }
	    try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=Karaoke4T;user=sa;password=123")) {
	        String sql = "SELECT p.maPhong, ttp.tenTrangThaiPhong, lp.tenLoaiPhong, p.soNguoi, lp.giaTien " +
	                     "FROM Phong p " +
	                     "JOIN TrangThaiPhong ttp ON ttp.maTTP = p.maTTP " +
	                     "JOIN LoaiPhong lp ON lp.maLP = p.maLP " +
	                     "WHERE p.maPhong = ?";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, maPhong);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                model.setRowCount(0);

	                while (resultSet.next()) {
	                    String maPhongResult = resultSet.getString("maPhong");
	                    String trangThai = resultSet.getString("tenTrangThaiPhong");
	                    String loaiPhong = resultSet.getString("tenLoaiPhong");
	                    int soNguoi = resultSet.getInt("soNguoi");
	                    String giaTien = resultSet.getString("giaTien");
	                    addRowToTable(maPhongResult, trangThai, loaiPhong, soNguoi, giaTien);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void setRoomName(String roomName) {
		lbl_maphonghientai.setText(roomName);
	}
	   
	private void addRowToTable(String maPhong, String trangThai, String loaiPhong, int soNguoi, String giaTien) {
        // Format the giaTien value to Vietnamese currency format
        DecimalFormat currencyFormat = new DecimalFormat("###,### VND");
        String formattedGiaTien = currencyFormat.format(Double.parseDouble(giaTien));

        // Add the formatted data to the table model
        Object[] rowData = { maPhong, trangThai, loaiPhong, soNguoi, formattedGiaTien };
        model.addRow(rowData);
    }
	
	
	 public String layMaPDP(String maPhong) {
	        String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
	        String username = "sa";
	        String password = "123";
	        String maPDP = null;

	        try (Connection connection = DriverManager.getConnection(url, username, password)) {
	            String sqlQuery = "SELECT *\r\n"
	            		+ "FROM PhieuDatPhong\r\n"
	            		+ "WHERE maPDP = (SELECT MAX(maPDP) FROM PhieuDatPhong WHERE maPhong = ?)";
	            
	            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
	                preparedStatement.setString(1, maPhong);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                        maPDP = resultSet.getString("maPDP");
	                        layMaPDP = maPDP;
//	                        System.out.println(maHD);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return maPDP;
	    }

	 public void updatePhieuDatPhong(String maPDP, String newMaPhong) {
	        // Thông tin kết nối đến cơ sở dữ liệu
		 String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
	        String username = "sa";
	        String password = "123";

	        try (Connection connection = DriverManager.getConnection(url, username, password)) {
	            // Thực hiện câu lệnh UPDATE
	            String sql = "UPDATE PhieuDatPhong SET maPhong = ? WHERE maPDP = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                // Thiết lập giá trị tham số
	                statement.setString(1, newMaPhong);
	                statement.setString(2, maPDP);

	                // Thực hiện câu lệnh UPDATE
	                statement.executeUpdate();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception according to your application's needs
	        }
	    }
	 
	 

	 public String layMaHoaDon(String maPhong) {
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
//	                        System.out.println(maHD);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return maHD;
	    }
	 public void updateChiTietHoaDon(String maHD, String newMaPhong) {
	        // Thông tin kết nối đến cơ sở dữ liệu
		 String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
	        String username = "sa";
	        String password = "123";

	        try (Connection connection = DriverManager.getConnection(url, username, password)) {
	            // Thực hiện câu lệnh UPDATE
	            String sql = "UPDATE ChiTietHoaDon\r\n"
	            		+ "SET maPhong = ? \r\n"
	            		+ "where maHD= ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                // Thiết lập giá trị tham số
	                statement.setString(1, newMaPhong);
	                statement.setString(2, maHD);

	                // Thực hiện câu lệnh UPDATE
	                statement.executeUpdate();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception according to your application's needs
	        }
	    }
}
