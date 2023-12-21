package GiaoDien;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DAO.QLKH_DAO;
import DAO.QLPH_DAO;
import Entity.Phong;
import Entity.UserInfo;
import connectDB.connectDB;
import testbutton.Buttontest;
import javax.swing.JSpinner;

public class GD_DatPhong extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblClock, TheoDoiThaoTac;
	private Timer timer;
	
    private DefaultListModel<Phong> phongListModel;
	private JPanel Right_QL_PHONG;
	private JLabel lbltenql;
	private JTextField textFieldTenKhach;
	private JTextField textFieldSDT;
	private JTextField textFieldCMND;
	private JTextField textFieldDiaChi;
	private JTextField textFieldSoLuongNguoi;

	private String ngayNhan;
	private String phone;
	private String maKhachHang;
	private String maPhong, tenKH, sdt, cmnd, diaChi, songuoi;
	private JRadioButton rdbtnNAM, rdbtnNU;
	private int soLuongKH;
	private float giaTien;
	private long lastClickTime = 0;
	private boolean isSelected = false;
	private testbutton.Buttontest btnLamMoiDuLieu, btnhuyphong, btnchuyenphong, btnDatPhong;
	private JSpinner spinnerGioNhanPhong;
	private SpinnerDateModel dateModel;
	private String layMaHD;
	private JRadioButton rdbtnNgayMai;
	private JRadioButton rdbtnHomNay;

	QLPH_DAO ds = new QLPH_DAO();
	QLKH_DAO dskh = new QLKH_DAO();
	JPanel pnl_danhsachphonghat = new JPanel();
	private JTextField textFieldTìmKhachDaThue;

	/**
	 * Launch the application.
	 */


//
	/**
	 * Create the frame.
	 */
	public GD_DatPhong() {

//		--------------------------- MAN HINH----------------------------
		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Giao Diện Đ Phòng");
		setBounds(100, 100, 1175, 650);

		contentPane = new JPanel();
		connectDB.getInstance().connect();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

//		---------------------------------HO TRO -----------------------
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://thach1311.github.io/huongDan/").toURI());
				} catch (Exception ex) {
				}
			}
		});

		TheoDoiThaoTac = new JLabel("Theo dõi thao tác");
		TheoDoiThaoTac.setIcon(new ImageIcon(GD_ThuePhong.class.getResource("/Imgs/eyes.png")));
		TheoDoiThaoTac.setBounds(482, 23, 291, 25);
		contentPane.add(TheoDoiThaoTac);
		TheoDoiThaoTac.setBackground(Color.BLACK);
		TheoDoiThaoTac.setFont(new Font("Tahoma", Font.BOLD, 13));
		TheoDoiThaoTac.setForeground(new Color(255, 255, 255));

		btnNewButton.setIcon(new ImageIcon(GD_Main_NV.class.getResource("/Imgs/iconHoTro.png")));
		btnNewButton.setBounds(304, 10, 49, 50);
		contentPane.add(btnNewButton);

//		-----------------------------------MENU--------------------------------------------------

		JLabel lblquanly = new JLabel("NV:");
		lblquanly.setForeground(Color.WHITE);
		lblquanly.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblquanly.setBounds(878, -20, 232, 80);
		contentPane.add(lblquanly);

		lbltenql = new JLabel();
		lbltenql.setForeground(Color.WHITE);
		lbltenql.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltenql.setBounds(833, 6, 232, 80);
		lbltenql.setText(UserInfo.getTenNhanVien());
		contentPane.add(lbltenql);

		JButton jButton = new JButton("Đăng Xuất");
		jButton.setBounds(980, 13, 135, 42);
		jButton.setFont(new Font("Tahoma ", Font.BOLD, 14));
		jButton.setBackground(new Color(255, 0, 0));
		jButton.setForeground(Color.WHITE);

		jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
		jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
		jButton.setContentAreaFilled(false);
		jButton.setFocusPainted(false);
		jButton.setOpaque(true);
		contentPane.add(jButton);

		jButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jButton.setBackground(Color.BLACK);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jButton.setBackground(new Color(255, 0, 0));
			}
		});

		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất!", null,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					GD_Login lg = new GD_Login();
					lg.setVisible(true);
					lg.setLocationRelativeTo(null);
					dispose();
				}
			}
		});

		JPanel box_clock = new JPanel();
		box_clock.setBackground(new Color(255, 255, 255));
		box_clock.setBounds(34, 10, 260, 50);
		contentPane.add(box_clock);
		box_clock.setLayout(null);

		lblClock = new JLabel();
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblClock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblClock.setBounds(10, 0, 240, 50);
		lblClock.setForeground(Color.BLACK);
		box_clock.add(lblClock);

		timer = new Timer(0, this);
		timer.start();
//  --------------------------------------------------------------------------

		// -----------------LEFT
		JPanel pnl_thongtinkhachhang = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		pnl_thongtinkhachhang.setOpaque(false);
		pnl_thongtinkhachhang.setBackground(new Color(255, 255, 255, 200));
		pnl_thongtinkhachhang.setBounds(0, 148, 329, 463);
		contentPane.add(pnl_thongtinkhachhang);
		pnl_thongtinkhachhang.setLayout(null);

		textFieldTenKhach = new JTextField();
		textFieldTenKhach.setEditable(false);
		textFieldTenKhach.setEnabled(false);
		textFieldTenKhach.setBounds(25, 31, 236, 25);
		pnl_thongtinkhachhang.add(textFieldTenKhach);
		textFieldTenKhach.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tên Khách Hàng");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(27, 0, 107, 33);
		pnl_thongtinkhachhang.add(lblNewLabel);

		// rad button
		rdbtnNAM = new JRadioButton("Nam");
		rdbtnNAM.setEnabled(false);
		rdbtnNAM.setBounds(25, 63, 109, 23);
		rdbtnNAM.setOpaque(false);
		rdbtnNAM.setContentAreaFilled(false);
		rdbtnNAM.setFocusPainted(false);
		pnl_thongtinkhachhang.add(rdbtnNAM);

		rdbtnNU = new JRadioButton("Nữ");
		rdbtnNU.setEnabled(false);
		rdbtnNU.setBounds(152, 63, 109, 23);
		rdbtnNU.setOpaque(false);
		pnl_thongtinkhachhang.add(rdbtnNU);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNAM);
		bg.add(rdbtnNU);
		pnl_thongtinkhachhang.add(rdbtnNAM);
		pnl_thongtinkhachhang.add(rdbtnNU);
		// rad button

		JLabel lblNewLabel_1 = new JLabel("Số điện thoại");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(25, 93, 93, 14);
		pnl_thongtinkhachhang.add(lblNewLabel_1);

		textFieldSDT = new JTextField();
		textFieldSDT.setBounds(25, 113, 236, 25);
		textFieldSDT.setColumns(10);
		pnl_thongtinkhachhang.add(textFieldSDT);
		textFieldSDT.addMouseListener(new MouseListener() {

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
				if (textFieldSDT.getText().equals("")) {
					textFieldTenKhach.setText("");
					textFieldCMND.setText("");
					textFieldDiaChi.setText("");
				} else {
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton btnTimKiemSDT = new JButton("");
		btnTimKiemSDT.setIcon(new ImageIcon(GD_ThuePhong.class.getResource("/Imgs/search.png")));
		btnTimKiemSDT.setBounds(271, 113, 53, 25);
		pnl_thongtinkhachhang.add(btnTimKiemSDT);
		btnTimKiemSDT.addMouseListener(new MouseListener() {
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

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				System.out.print(ktraPhone);
				phone = textFieldSDT.getText();
				if (phone.matches("^0[1-9]\\d{8}$") == false) {
					JOptionPane.showMessageDialog(null, "Số điện thoại di động bắt đầu bằng 09 cuối cùng là 7 chữ số.");
				} else {
//					
					timSoDienThoai(phone);
					if (textFieldTenKhach.getText().equals("")) {
						int result = JOptionPane.showConfirmDialog(null, "Không tìm thấy khách hàng. Bạn có muốn thêm khách hàng không?", "Thông báo", JOptionPane.YES_NO_OPTION);

				        if (result == JOptionPane.YES_OPTION) {
				            // Xử lý khi người dùng chọn "Yes"
				            System.out.println("Bạn đã chọn Yes.");
				            GD_QuanLyKhachHang kh = new GD_QuanLyKhachHang();
							kh.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							kh.setVisible(true);
							dispose();
				        } else {
				            // Xử lý khi người dùng chọn "No"
				            System.out.println("Bạn đã chọn No hoặc đóng cửa sổ.");
				        }
					} else {
						JOptionPane.showMessageDialog(null, "Tìm thấy khách hàng ");

					}
				}
			}
		});

		textFieldCMND = new JTextField();
		textFieldCMND.setEditable(false);
		textFieldCMND.setEnabled(false);
		textFieldCMND.setBounds(25, 168, 236, 25);
		pnl_thongtinkhachhang.add(textFieldCMND);
		textFieldCMND.setColumns(10);

		textFieldDiaChi = new JTextField();
		textFieldDiaChi.setEditable(false);
		textFieldDiaChi.setEnabled(false);
		textFieldDiaChi.setBounds(25, 221, 236, 25);
		pnl_thongtinkhachhang.add(textFieldDiaChi);
		textFieldDiaChi.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("CMND");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2.setBounds(25, 149, 60, 14);
		pnl_thongtinkhachhang.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Địa chỉ");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_3.setBounds(25, 204, 60, 14);
		pnl_thongtinkhachhang.add(lblNewLabel_3);

		JLabel lblNewLabel_11 = new JLabel("Số lượng người ");
		lblNewLabel_11.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_11.setBounds(25, 344, 109, 21);
		pnl_thongtinkhachhang.add(lblNewLabel_11);

		textFieldSoLuongNguoi = new JTextField();
		textFieldSoLuongNguoi.setEnabled(false);
		textFieldSoLuongNguoi.setColumns(10);
		textFieldSoLuongNguoi.setBounds(152, 343, 111, 25);
		pnl_thongtinkhachhang.add(textFieldSoLuongNguoi);

//        BUTTON THUE PHONG
		btnLamMoiDuLieu = new testbutton.Buttontest();
		btnLamMoiDuLieu.setEnabled(false);
		btnLamMoiDuLieu.setText("Làm mới");
		btnLamMoiDuLieu.setForeground(new Color(245, 245, 245));
		btnLamMoiDuLieu.setBackground(new Color(255, 128, 64));
		btnLamMoiDuLieu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLamMoiDuLieu.setRippleColor(Color.WHITE);
		btnLamMoiDuLieu.setShadowColor(Color.BLACK);
		btnLamMoiDuLieu.setBounds(25, 420, 109, 43);
		
		btnLamMoiDuLieu.setEnabled(true);
		btnLamMoiDuLieu.setSelected(true);
		btnLamMoiDuLieu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lammoi();
			}

		});
		pnl_thongtinkhachhang.add(btnLamMoiDuLieu);

		btnchuyenphong = new Buttontest();
		btnchuyenphong.setText("Chuyển phòng");
		btnchuyenphong.setShadowColor(Color.BLACK);
		btnchuyenphong.setRippleColor(Color.WHITE);
		btnchuyenphong.setForeground(new Color(245, 245, 245));
		btnchuyenphong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnchuyenphong.setEnabled(false);
		btnchuyenphong.setBackground(new Color(206, 157, 255));
		btnchuyenphong.setBounds(150, 376, 109, 43);
		btnchuyenphong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_ChuyenDatPhong chuyenPhongFrame = new GD_ChuyenDatPhong();
				// Set the room name in GD_ChuyenPhong
				chuyenPhongFrame.setRoomName(maPhong);
				chuyenPhongFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// Show the GD_ChuyenPhong frame
				chuyenPhongFrame.setVisible(true);
				dispose();

//				
			}
		});
		pnl_thongtinkhachhang.add(btnchuyenphong);
		JLabel lblavatar = new JLabel("");
		lblavatar.setHorizontalAlignment(SwingConstants.CENTER);
		lblavatar.setIcon(new ImageIcon(GD_Main_NV.class.getResource("/Imgs/t1 1.png")));
		lblavatar.setBounds(90, -444, 1333, 957);
		contentPane.add(lblavatar);

//-------------------------------------------------------------------------

// ------------------------------------------button huy thue phong 

		btnhuyphong = new testbutton.Buttontest();
		btnhuyphong.setText("Hủy Phòng");
		btnhuyphong.setForeground(new Color(245, 245, 245));
		btnhuyphong.setBackground(new Color(255, 85, 85));
		btnhuyphong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnhuyphong.setRippleColor(new Color(255, 255, 255));
		btnhuyphong.setShadowColor(new Color(0, 0, 0));
		btnhuyphong.setBounds(150, 420, 109, 43);
		btnhuyphong.setEnabled(false);

		btnhuyphong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.print("Huy ma kh : " + maKhachHang);
				// TODO Auto-generated method stubc
				tenKH = textFieldTenKhach.getText();
				if (tenKH.equals("")) {
					JOptionPane.showMessageDialog(null, "Không có thông tin khách hàng muốn hủy phòng hát");
				} else {

					layMaHD = layMaHoaDon(maPhong);
					ktraKhachHangThuePhong(maKhachHang, maPhong);
					xoaChiTietDichVu(layMaHD);
					xoaChiTietHoaDon(layMaHoaDon(maPhong));
					xoaHoaDon(layMaHD);
					reloadPhongHatList();
				}
			}
		});
		pnl_thongtinkhachhang.add(btnhuyphong);

		btnDatPhong = new Buttontest();
		btnDatPhong.setText("Đặt phòng");
		btnDatPhong.setShadowColor(new Color(0, 0, 0));
		btnDatPhong.setRippleColor(new Color(255, 255, 255));
		btnDatPhong.setForeground(new Color(245, 245, 245));
		btnDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDatPhong.setBackground(new Color(90, 167, 167));
		btnDatPhong.setBounds(25, 376, 109, 43);
		pnl_thongtinkhachhang.add(btnDatPhong);

		btnDatPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				tenKH = textFieldTenKhach.getText();
				if (tenKH.equals("")) {
					JOptionPane.showMessageDialog(null, "Lỗi đặt vì không có thông tin khách hàng");
				} else {

					// Lấy giá trị từ SpinnerDateModel
					Date gioNhanPhong = (Date) dateModel.getValue();
					// Chuyển đổi giá trị thành định dạng mong muốn
					SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
					String gioNhanPhongFormatted = dateFormat.format(gioNhanPhong);

					// In ra thông tin
					System.out.println("Giờ nhận phòng: " + gioNhanPhongFormatted);

					// Lấy ngày hiện tại
					LocalDate ngayHienTai = LocalDate.now();

					// Định dạng ngày theo định dạng mong muốn
					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String ngayHienTaiFormatted = ngayHienTai.format(dateFormatter);

					// In ra thông tin
					System.out.println("Ngày hiện tại: " + ngayHienTaiFormatted);

					// Lấy ngày mai bằng cách thêm 1 ngày vào ngày hiện tại
					LocalDate ngayMai = ngayHienTai.plusDays(1);

					// Định dạng ngày theo định dạng mong muốn
					String ngayMaiFormatted = ngayMai.format(dateFormatter);

					// In ra thông tin
					System.out.println("Ngày mai: " + ngayMaiFormatted);

					if (rdbtnHomNay.isSelected() == true) {
						ngayNhan = ngayHienTaiFormatted;
					}
					if (rdbtnNgayMai.isSelected() == true) {
						ngayNhan = ngayMaiFormatted;
					}

					String thoiGianNhanPhong = ngayNhan + " " + gioNhanPhongFormatted;
					System.out.println(thoiGianNhanPhong);

					loadPhieuDatPhong(e);

					LocalDateTime currentDateTime = LocalDateTime.now();

					// Định dạng ngày giờ theo ý muốn
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					// Chuyển đổi thành chuỗi
					String formattedDateTime = currentDateTime.format(formatter);

					soLuongKH = Integer.parseInt(textFieldSoLuongNguoi.getText());
					// In ra màn hình

					String tenNhanVien = lbltenql.getText();
					String layMaNhanVien = layMaNhanVien(tenNhanVien);

					taoPhieuDatPhong(formattedDateTime, thoiGianNhanPhong, maKhachHang, maPhong, soLuongKH,
							layMaNhanVien);
					taoHoaDon(formattedDateTime, formattedDateTime, maKhachHang, layMaNhanVien);
					taoChiTietHoaDon(maPhong);

				}

			}
		});

		rdbtnHomNay = new JRadioButton("Hôm nay");
		rdbtnHomNay.setSelected(true);
		rdbtnHomNay.setEnabled(false);
		bg.add(rdbtnHomNay);
		rdbtnHomNay.setBounds(25, 278, 111, 23);
		pnl_thongtinkhachhang.add(rdbtnHomNay);

		rdbtnNgayMai = new JRadioButton("Ngày mai");
		rdbtnNgayMai.setEnabled(false);
		bg.add(rdbtnNgayMai);
		rdbtnNgayMai.setBounds(152, 278, 111, 23);
		pnl_thongtinkhachhang.add(rdbtnNgayMai);

		dateModel = new SpinnerDateModel();
		spinnerGioNhanPhong = new JSpinner(dateModel);
		spinnerGioNhanPhong.setEnabled(false);
		JSpinner.DateEditor de_spinnerGioNhanPhong = new JSpinner.DateEditor(spinnerGioNhanPhong, "HH:mm:ss");
		spinnerGioNhanPhong.setEditor(de_spinnerGioNhanPhong);
		spinnerGioNhanPhong.setBounds(152, 308, 111, 25);
		pnl_thongtinkhachhang.add(spinnerGioNhanPhong);

//		JSpinner spinner = new JSpinner();

		JLabel lblThoiGianNhanPhong = new JLabel("Thời gian nhận ");
		lblThoiGianNhanPhong.setFont(new Font("Dialog", Font.BOLD, 12));
		lblThoiGianNhanPhong.setBounds(24, 308, 131, 20);
		pnl_thongtinkhachhang.add(lblThoiGianNhanPhong);

		JLabel lblNewLabel_5 = new JLabel("Ngày nhận ");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5.setBounds(27, 253, 130, 16);
		pnl_thongtinkhachhang.add(lblNewLabel_5);

//        -----------------------------------------MENU-------------------------------------------------------
		testbutton.Buttontest btndatphong1 = new testbutton.Buttontest();
        btndatphong1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
        KeyStroke keyStrokeDatPhong = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        btndatphong1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeDatPhong, "F1");
        btndatphong1.getActionMap().put("F1", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
            }
        });
        btndatphong1.setBorder(null);
        btndatphong1.setText("Đặt Phòng (F1)");
        btndatphong1.setForeground(Color.WHITE);
        btndatphong1.setFont(new Font("Tahoma", Font.BOLD, 18));
        btndatphong1.setBackground(new Color(128,128,128, 150));
        btndatphong1.setBounds(0, 70, 166, 87);
		contentPane.add(btndatphong1);
		btndatphong1.setLayout(null);
        
        testbutton.Buttontest btnthuephong = new testbutton.Buttontest();
        btnthuephong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_ThuePhong gdthuephong = new GD_ThuePhong();
				gdthuephong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdthuephong.setVisible(true);
				dispose();
			}
		});
        KeyStroke keyStrokeThuePhong = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
        btnthuephong.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeThuePhong, "F2");
        btnthuephong.getActionMap().put("F2", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_ThuePhong gdthuephong = new GD_ThuePhong();
				gdthuephong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdthuephong.setVisible(true);
				dispose();
            }
        });
        btnthuephong.setBorder(null);
        btnthuephong.setText("Thuê Phòng (F2)");
        btnthuephong.setForeground(Color.WHITE);
        btnthuephong.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnthuephong.setBackground(new Color(0,0,0, 150));
        btnthuephong.setBounds(165, 70, 166, 87);
		contentPane.add(btnthuephong);
		btnthuephong.setLayout(null);
		
        testbutton.Buttontest btndatdichvu = new testbutton.Buttontest();
        btndatdichvu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_DatDichVu gddv = new GD_DatDichVu();
				gddv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gddv.setVisible(true);
				dispose();
			}
		});
        KeyStroke keyStrokeDatdv = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0);
        btndatdichvu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeDatdv, "F3");
        btndatdichvu.getActionMap().put("F3", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_DatDichVu gddv = new GD_DatDichVu();
				gddv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gddv.setVisible(true);
				dispose();
            }
        });
        btndatdichvu.setBorder(null);
        btndatdichvu.setText("Đặt Dịch Vụ (F3)");
        btndatdichvu.setForeground(Color.WHITE);
        btndatdichvu.setFont(new Font("Tahoma", Font.BOLD, 18));
        btndatdichvu.setBackground(new Color(0, 0, 0, 150));
        btndatdichvu.setBounds(332, 70, 166, 87);
		contentPane.add(btndatdichvu);
		btndatdichvu.setLayout(null);
        
        testbutton.Buttontest btntstTrPhng = new testbutton.Buttontest();
        btntstTrPhng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_TraPhong gdtraphong = new GD_TraPhong();
				gdtraphong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtraphong.setVisible(true);
				dispose();
			}
		});
        KeyStroke keyStrokeTrPhong = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0);
        btntstTrPhng.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeTrPhong, "F4");
        btntstTrPhng.getActionMap().put("F4", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_TraPhong gdtraphong = new GD_TraPhong();
				gdtraphong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtraphong.setVisible(true);
				dispose();
            }
        });
        btntstTrPhng.setBorder(null);
        btntstTrPhng.setText("Trả Phòng (F4)");
        btntstTrPhng.setForeground(Color.WHITE);
        btntstTrPhng.setFont(new Font("Tahoma", Font.BOLD, 18));
        btntstTrPhng.setBackground(new Color(0,0,0, 150));
        btntstTrPhng.setBounds(496, 70, 165, 87);
        contentPane.add(btntstTrPhng);
        btntstTrPhng.setLayout(null);
        
        testbutton.Buttontest btnkhachhang = new testbutton.Buttontest();
        btnkhachhang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_QuanLyKhachHang gdqlykhachhang = new GD_QuanLyKhachHang();
				gdqlykhachhang.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqlykhachhang.setVisible(true);
				dispose();
			}
		});
        KeyStroke keyStrokeQLKH = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
        btnkhachhang.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeQLKH, "F5");
        btnkhachhang.getActionMap().put("F5", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_QuanLyKhachHang gdqlykhachhang = new GD_QuanLyKhachHang();
				gdqlykhachhang.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqlykhachhang.setVisible(true);
				dispose();
            }
        });
        btnkhachhang.setBorder(null);
        btnkhachhang.setText("Khách Hàng (F5)");
        btnkhachhang.setForeground(Color.WHITE);
        btnkhachhang.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnkhachhang.setBackground(new Color(0, 0, 0, 150));
        btnkhachhang.setBounds(660, 70, 166, 87);
		contentPane.add(btnkhachhang);
		btnkhachhang.setLayout(null);
		
		testbutton.Buttontest btnhoadon = new testbutton.Buttontest();
		btnhoadon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_QLHoaDon gdqlhoadon = new GD_QLHoaDon();
				gdqlhoadon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqlhoadon.setVisible(true);
				dispose();
			}
		});
		KeyStroke keyStrokeQLHD = KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0);
		btnhoadon.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeQLHD, "F6");
		btnhoadon.getActionMap().put("F6", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
            	GD_QLHoaDon gdqlhoadon = new GD_QLHoaDon();
				gdqlhoadon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqlhoadon.setVisible(true);
				dispose();
            }
        });
		btnhoadon.setBorder(null);
		btnhoadon.setText("Hóa Đơn (F6)");
		btnhoadon.setForeground(Color.WHITE);
		btnhoadon.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnhoadon.setBackground(new Color(0, 0, 0, 150));
		btnhoadon.setBounds(828, 70, 165, 87);
		contentPane.add(btnhoadon);
		btnhoadon.setLayout(null);
		
        testbutton.Buttontest btnthongke = new testbutton.Buttontest();
        btnthongke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_ThongKeKhachHang thongkekhachhang = new GD_ThongKeKhachHang();
				thongkekhachhang.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				thongkekhachhang.setVisible(true);
				dispose();
			}
		});
        KeyStroke keyStrokeThongKe = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0);
        btnthongke.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeThongKe, "F7");
        btnthongke.getActionMap().put("F7", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_ThongKeKhachHang thongkekhachhang = new GD_ThongKeKhachHang();
				thongkekhachhang.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				thongkekhachhang.setVisible(true);
				dispose();
            }
        });
        btnthongke.setBorder(null);
        btnthongke.setText("Thống Kê (F7)");
        btnthongke.setForeground(Color.WHITE);
        btnthongke.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnthongke.setBackground(new Color(0, 0, 0, 150));
        btnthongke.setBounds(993, 70, 166, 87);
		contentPane.add(btnthongke);
		btnthongke.setLayout(null);

		JPanel pnl_chucnangTimKH = new JPanel();
		pnl_chucnangTimKH.setBackground(new Color(255, 255, 255));
		pnl_chucnangTimKH.setBounds(330, 148, 828, 50);
		contentPane.add(pnl_chucnangTimKH);
		pnl_chucnangTimKH.setLayout(null);

		JRadioButton rdoTatCaPhong = new JRadioButton("Tất cả ");
		rdoTatCaPhong.setBounds(6, 14, 67, 23);
		JRadioButton rdoPhongBan = new JRadioButton("Phòng bận");
		rdoPhongBan.setBounds(75, 14, 86, 23);
		JRadioButton rdoPhongTrong = new JRadioButton("Phòng trống");
		rdoPhongTrong.setBounds(254, 14, 89, 23);

		JRadioButton rdoPhongCho = new JRadioButton("Phòng chờ");
		rdoPhongCho.setBounds(163, 14, 89, 23);

		pnl_chucnangTimKH.add(rdoTatCaPhong);
		pnl_chucnangTimKH.add(rdoPhongBan);
		pnl_chucnangTimKH.add(rdoPhongTrong);
		pnl_chucnangTimKH.add(rdoPhongCho);

		bg.add(rdoPhongTrong);
		bg.add(rdoPhongBan);
		bg.add(rdoTatCaPhong);
		bg.add(rdoPhongCho);

		JLabel lblNewLabel_4 = new JLabel("Tìm khách đã nhận ");
		lblNewLabel_4.setBounds(465, 14, 111, 23);
		pnl_chucnangTimKH.add(lblNewLabel_4);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(459, 0, 1, 50);
		pnl_chucnangTimKH.add(panel_1);

		textFieldTìmKhachDaThue = new JTextField();
		textFieldTìmKhachDaThue.setBounds(576, 11, 173, 24);
		pnl_chucnangTimKH.add(textFieldTìmKhachDaThue);
		textFieldTìmKhachDaThue.setColumns(10);

		JButton btnTimKiemKhachDaThueSDT = new JButton("");
		btnTimKiemKhachDaThueSDT.setIcon(new ImageIcon(GD_ThuePhong.class.getResource("/Imgs/search.png")));
		btnTimKiemKhachDaThueSDT.setBounds(753, 11, 53, 25);
		pnl_chucnangTimKH.add(btnTimKiemKhachDaThueSDT);
		
		JButton btnTimPhieuDatPhong = new JButton("Tìm phiếu đặt");
		btnTimPhieuDatPhong.setBounds(349, 14, 106, 23);
		pnl_chucnangTimKH.add(btnTimPhieuDatPhong);

		btnTimPhieuDatPhong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GD_QuanLyPhieuDatPhong phieu = new GD_QuanLyPhieuDatPhong();
				phieu.setVisible(true);
				dispose();
			}
		});
		
		btnTimKiemKhachDaThueSDT.addActionListener(new ActionListener() {

//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String phone = textFieldTìmKhachDaThue.getText();
//				timKhachDaThue(phone);
//			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String phone = textFieldTìmKhachDaThue.getText();
				String maPhongdeTim = "";
				String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
				String username = "sa";
				String password = "123";

				List<String> listTam = new ArrayList<>();

				try {
					// Kết nối đến cơ sở dữ liệu
					Connection connection = DriverManager.getConnection(url, username, password);

					String sqlPhongKH = "Select p.maPhong from HoaDon hd inner join ChiTietHoaDon cthd on hd.maHD = cthd.maHD \r\n"
							+ "inner join KhachHang kh on hd.maKH = kh.maKH\r\n"
							+ "inner join Phong p on cthd.maPhong = p.maPhong\r\n"
							+ "inner join TrangThaiPhong ttp on p.maTTP = ttp.maTTP\r\n"
							+ "where SDT = ? and p.maTTP = 'TTP001' and tienKhachTra = 0";
					PreparedStatement statementPhongKH = connection.prepareStatement(sqlPhongKH);
					statementPhongKH.setString(1, phone);

					ResultSet resultSetPhongKH = statementPhongKH.executeQuery();
					while (resultSetPhongKH.next()) {
						maPhongdeTim = resultSetPhongKH.getString("maPhong");
						listTam.add(maPhongdeTim);
					}
					List<Phong> loadedRooms = loadPhongHatByMaPhong(listTam);
					draw(loadedRooms);
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		});

//		loadData("TTP003");
//		loadAllRooms();
//		reloadPhongHatList();
//		loadAllRooms();
//		loadAllRooms();
		 phongListModel = new DefaultListModel<>();
//		rdoTatCaPhong.setSelected(true);
//		if (rdoTatCaPhong.isSelected()) {
//			loadAllRooms();
//			  phongListModel.clear();
//		}
		 rdoTatCaPhong.setSelected(true);
//		loadAllRooms();
		 reloadPhongHatList();
		rdoTatCaPhong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadAllRooms();
				  phongListModel.clear();
			}
		});
		
		rdoPhongBan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadData("TTP001");
				  phongListModel.clear();
			}
		});
		rdoPhongCho.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadData("TTP002");
				  phongListModel.clear();
			}
		});

		rdoPhongTrong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadData("TTP003");
				  phongListModel.clear();
			}
		});
		JLabel lb_hinhnen = new JLabel("");
		lb_hinhnen.setIcon(new ImageIcon(GD_DatPhong.class.getResource("/Imgs/370.png")));
		lb_hinhnen.setBounds(-40, -182, 1333, 957);
		contentPane.add(lb_hinhnen);

//----------------------------------------------------------------------------------------------------------		

	}

//	--------------------------------------HAM XU LY ----------------------------------------------------------------------
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			// Cập nhật thời gian
			updateClock();
		}
	}

	private void updateClock() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);

		String ampm;
		if (hour >= 12) {
			ampm = "PM";
			if (hour > 24) {
				hour -= 12;
			}
		} else {
			ampm = "AM";
			if (hour == 0) {
				hour = 12;
			}
		}

		String time = String.format("%02d:%02d:%02d %s  %04d/%02d/%02d", hour, minute, second, ampm, year, month, day);
		lblClock.setText(time);
	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
		GD_Main_NV mainnv = new GD_Main_NV();
		mainnv.setVisible(true);
	}

//   LOAD DATA 
	// LOAD DATA
	public void loadData(String trangThaiPhong) {

//		System.out.print(trangThaiPhong);
		QLPH_DAO ds = new QLPH_DAO();
		
		try {
			ArrayList<Phong> listPhong = ds.docbang();

			// Filter rooms based on the provided status
			List<Phong> filteredList = listPhong.stream()
					.filter(phong -> phong.getTrangThaiPhong().getMaTrangThai().equals(trangThaiPhong))
					.collect(Collectors.toList());

			if (filteredList != null && !filteredList.isEmpty()) {
				draw(filteredList);
				
			} else {
				JOptionPane.showMessageDialog(this, "Không có dữ liệu phòng hát có trạng thái " + trangThaiPhong,
						"Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu phòng hát.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void loadAllRooms() {
		QLPH_DAO ds = new QLPH_DAO();

		try {
			ArrayList<Phong> listPhong = ds.docbang();

			if (listPhong != null && !listPhong.isEmpty()) {
				draw(listPhong);
			} else {
				JOptionPane.showMessageDialog(this, "Không có dữ liệu phòng hát.", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu phòng hát.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

// LOAD DANH SACH PHONG HAT
	private void draw(List<Phong> filteredList) {
		
		Right_QL_PHONG = new JPanel();
		Right_QL_PHONG.setBorder(null);
		Right_QL_PHONG.setBackground(new Color(255, 255, 255, 150));
		Right_QL_PHONG.setBounds(328, 194, 833, 416);
		Right_QL_PHONG.setLayout(null);
		contentPane.add(Right_QL_PHONG);

		JScrollPane scrollPane_DSPH = new JScrollPane();
		scrollPane_DSPH.setBounds(0, 0, 833, 465);
		scrollPane_DSPH.setBackground(new Color(255, 255, 255, 0));
		Right_QL_PHONG.add(scrollPane_DSPH);

		JPanel panel_dsph = new JPanel();
		panel_dsph.setBackground(new Color(255, 255, 255));
		scrollPane_DSPH.setViewportView(panel_dsph);
		panel_dsph.setLayout(new GridLayout(0, 5, -50, 0));

		scrollPane_DSPH.setViewportView(panel_dsph);
		panel_dsph.setLayout(new GridLayout(0, 5, -50, 0));

		for (Phong ph : filteredList) {
			// load label cha

			JPanel pn_phonghat = new JPanel();
			pn_phonghat.setBackground(new Color(255, 255, 255, 0));

//    		pn_phonghat.setBorder(LineBorder.createBlackLineBorder());
			pn_phonghat.setLayout(null);
			pn_phonghat.setPreferredSize(new Dimension(200, 200));
			panel_dsph.add(pn_phonghat);

//    		//load suc chua
			JLabel lbl_succhua = new JLabel("Sức chứa : " + ph.getSoNguoi());
			lbl_succhua.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_succhua.setFont(new Font("Tahoma", Font.BOLD, 11));
			lbl_succhua.setForeground(new Color(255, 0, 0));
			lbl_succhua.setBounds(55, 100, 80, 35);
			pn_phonghat.add(lbl_succhua);

			// load ten phong
			JLabel lbl_tenphong = new JLabel(ph.getMaPhong());
			lbl_tenphong.setFont(new Font("Tahoma", Font.BOLD, 13));
			lbl_tenphong.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_tenphong.setBounds(55, 120, 80, 35);
			pn_phonghat.add(lbl_tenphong);

//    		DANH SACH PHONG BAN
			if (ph.getTrangThaiPhong().getMaTrangThai().equals("TTP001")) {

				// load hinh anh
				JLabel lbl_hinhanh = new JLabel("");
				lbl_hinhanh.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_hinhanh.setBounds(50, 10, 88, 85);
				pn_phonghat.add(lbl_hinhanh);

				LineBorder labelBorder = new LineBorder(Color.BLACK, 5);
				lbl_hinhanh.setBorder(labelBorder);
				lbl_hinhanh.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						maPhong = ph.getMaPhong();
						tenKH = textFieldTenKhach.getText();
						sdt = textFieldSDT.getText();
						cmnd = textFieldCMND.getText();
						diaChi = textFieldDiaChi.getText();
						songuoi = textFieldSoLuongNguoi.getText();

						btnDatPhong.setSelected(false);
						btnDatPhong.setEnabled(false);
						btnLamMoiDuLieu.setEnabled(false);
						btnhuyphong.setEnabled(true);
						layMaHoaDon(maPhong);

						if (ph.getLoaiPhong().getTenLoaiPhong().equals("Phòng Thường")) {
							giaTien = 100000;
						} else {
							giaTien = 180000;
						}
						@SuppressWarnings("unused")
						String gt = null;
						if (rdbtnNAM.isSelected()) {
							gt = "Nam";
						}
						if (rdbtnNU.isSelected()) {
							gt = "Nu";
						}

						isSelected = !isSelected;
						if (isSelected) {
//							btnthuephong.setText("Chuyển Phòng");
//							btnthuephong.setBackground(new Color(183, 157, 215));
							btnchuyenphong.setEnabled(true);
							LineBorder labelBorder = new LineBorder(Color.RED, 5);
							lbl_hinhanh.setBorder(labelBorder);

							String duLieu = textFieldSoLuongNguoi.getText().trim();

							if (!duLieu.isEmpty()) {
								try {
									int soLuongNguoi = Integer.parseInt(duLieu);
									if (soLuongNguoi > Integer.parseInt(ph.getSoNguoi())) {
										JOptionPane.showMessageDialog(null, "Số lượng người vượt mức cho phép");
									} else {
										JOptionPane.showMessageDialog(null, "Số lượng người phù hợp");
									}
								} catch (NumberFormatException e1) {
									// JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid
									// number.");
								}
							} else {
								// JOptionPane.showMessageDialog(null, "Input is empty. Please enter a valid
								// number.");
							}
						} else {
							LineBorder labelBorder = new LineBorder(Color.BLACK, 5);
							lbl_hinhanh.setBorder(labelBorder);

						}

						long clickTime = System.currentTimeMillis();

						if (clickTime - lastClickTime <= 500) { // Check if the click interval is less than 500
																// milliseconds (adjust as needed)
//	    	                    	phone = textFieldSDT.getText();
//	    	                    	System.out.print(maPhong);

							GD_ChiTietDatPhong chitiet = new GD_ChiTietDatPhong();
							chitiet.setVisible(true);
							chitiet.setLocationRelativeTo(null);
							chitiet.loadChiTietPhong(maPhong);
							chitiet.loadChiTietDichVuDaDat(maPhong);
							chitiet.loadChiTietKhachHangDaDatPhong(maPhong);
							chitiet.layMaHoaDon(maPhong);
							dispose();
						}

						lastClickTime = clickTime;

					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}
				});

				// phan loai phong
				if (ph.getLoaiPhong().getTenLoaiPhong().equals("Phòng Thường")) {
					lbl_hinhanh.setIcon(new ImageIcon(GD_PhongHat.class.getResource("/Imgs/micro_gray.png")));
				}

				else {
					lbl_hinhanh.setIcon(new ImageIcon(GD_PhongHat.class.getResource("/Imgs/micro_crown_gray.png")));
				}

			}

//    		DANH SACH PHONG TRONG
			if (ph.getTrangThaiPhong().getMaTrangThai().equals("TTP003")) {
				// load hinh anh
				// load hinh anh
				JLabel lbl_hinhanh = new JLabel("");
				lbl_hinhanh.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_hinhanh.setBounds(50, 10, 88, 85);
				pn_phonghat.add(lbl_hinhanh);

				LineBorder labelBorder = new LineBorder(Color.BLACK, 5);
				lbl_hinhanh.setBorder(labelBorder);
				lbl_hinhanh.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						sdt = textFieldSDT.getText();
						maPhong = ph.getMaPhong();
						tenKH = textFieldTenKhach.getText();
						cmnd = textFieldCMND.getText();
						diaChi = textFieldDiaChi.getText();
						songuoi = textFieldSoLuongNguoi.getText();

						btnLamMoiDuLieu.setEnabled(false);
						btnDatPhong.setEnabled(true);
						btnDatPhong.setSelected(true);
						if (ph.getLoaiPhong().getTenLoaiPhong().equals("Phòng Thường")) {
							giaTien = 100000;
						} else {
							giaTien = 180000;
						}
						@SuppressWarnings("unused")
						String gt = "";
						if (rdbtnNAM.isSelected()) {
							gt = "Nam";
						}
						if (rdbtnNU.isSelected()) {
							gt = "Nu";
						}
//    					

						btnhuyphong.setEnabled(false);
//    					Neu sdt trong thi loi
						if (sdt.equals("")) {
							LineBorder labelBorder1 = new LineBorder(Color.BLACK, 5);
							lbl_hinhanh.setBorder(labelBorder1);
							JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại mới chọn được phòng");
						}
//    					Neu co sdt thi thuc hien duoc
						else {
							btnLamMoiDuLieu.setEnabled(true);
//        					Onclick
							isSelected = !isSelected;
							if (isSelected) {
								LineBorder labelBorder = new LineBorder(Color.RED, 5);
								lbl_hinhanh.setBorder(labelBorder);

								String duLieu = textFieldSoLuongNguoi.getText().trim();
								if (duLieu.equals("")) {
									JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng người");
									LineBorder labelBorder1 = new LineBorder(Color.BLACK, 5);
									lbl_hinhanh.setBorder(labelBorder1);
								}

								else {
									if (!duLieu.isEmpty()) {
										try {
											int soLuongNguoi = Integer.parseInt(duLieu);
											if (soLuongNguoi > Integer.parseInt(ph.getSoNguoi())) {
												JOptionPane.showMessageDialog(null, "Số lượng người vượt mức cho phép");
											} else {
												JOptionPane.showMessageDialog(null, "Số lượng người phù hợp");
											}
										} catch (NumberFormatException e1) {
//	           					            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
										}
									}
								}

							}

							else {
								LineBorder labelBorder = new LineBorder(Color.BLACK, 5);
								lbl_hinhanh.setBorder(labelBorder);
							}

						}
// 					 Double click
						long clickTime = System.currentTimeMillis();

						if (clickTime - lastClickTime <= 500) { // Check if the click interval is less than 500
																// milliseconds (adjust as needed)
							phone = textFieldSDT.getText();
							GD_ChiTietPhong chitiet = new GD_ChiTietPhong();
							chitiet.setVisible(true);
							chitiet.setLocationRelativeTo(null);
							chitiet.loadChiTietPhong(maPhong);
							chitiet.loadChiTietDichVuDaDat(maPhong);
							dispose();

						}

						lastClickTime = clickTime;

					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}
				});

				// phan loai phong
				if (ph.getLoaiPhong().getTenLoaiPhong().equals("Phòng Thường")) {
					lbl_hinhanh.setIcon(new ImageIcon(GD_PhongHat.class.getResource("/Imgs/micro.png")));
				}

				else {
					lbl_hinhanh.setIcon(new ImageIcon(GD_PhongHat.class.getResource("/Imgs/micro_with_crown.png")));
				}

			}

//    		DANH SACH PHONG CHỜ
			if (ph.getTrangThaiPhong().getMaTrangThai().equals("TTP002")) {

				// load hinh anh
				// load hinh anh
				JLabel lbl_hinhanh = new JLabel("");
				lbl_hinhanh.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_hinhanh.setBounds(50, 10, 88, 85);
				pn_phonghat.add(lbl_hinhanh);

				LineBorder labelBorder = new LineBorder(Color.BLACK, 5);
				lbl_hinhanh.setBorder(labelBorder);
				lbl_hinhanh.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						maPhong = ph.getMaPhong();
						tenKH = textFieldTenKhach.getText();
						sdt = textFieldSDT.getText();
						cmnd = textFieldCMND.getText();
						diaChi = textFieldDiaChi.getText();
						songuoi = textFieldSoLuongNguoi.getText();

						btnLamMoiDuLieu.setEnabled(true);
						btnhuyphong.setEnabled(true);
						btnDatPhong.setEnabled(false);
						btnchuyenphong.setEnabled(false);
						layMaHoaDon(maPhong);
						if (ph.getLoaiPhong().getTenLoaiPhong().equals("Phòng Thường")) {
							giaTien = 100000;
						} else {
							giaTien = 180000;
						}
						@SuppressWarnings("unused")
						String gt = "";
						if (rdbtnNAM.isSelected()) {
							gt = "Nam";
						}
						if (rdbtnNU.isSelected()) {
							gt = "Nu";
						}

						isSelected = !isSelected;
						if (isSelected) {

//							btnchuyenphong.setVisible(false);
							btnchuyenphong.setSelected(false);
							btnLamMoiDuLieu.setEnabled(true);
							LineBorder labelBorder = new LineBorder(Color.RED, 5);
							lbl_hinhanh.setBorder(labelBorder);

							String duLieu = textFieldSoLuongNguoi.getText().trim();

							if (!duLieu.isEmpty()) {
								try {
									int soLuongNguoi = Integer.parseInt(duLieu);
									if (soLuongNguoi > Integer.parseInt(ph.getSoNguoi())) {
										JOptionPane.showMessageDialog(null, "Số lượng người vượt mức cho phép");
									} else {
										JOptionPane.showMessageDialog(null, "Số lượng người phù hợp");
									}
								} catch (NumberFormatException e1) {
									// JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid
									// number.");
								}
							} else {
								// JOptionPane.showMessageDialog(null, "Input is empty. Please enter a valid
								// number.");
							}
						} else {
							LineBorder labelBorder = new LineBorder(Color.BLACK, 5);
							lbl_hinhanh.setBorder(labelBorder);

						}

						long clickTime = System.currentTimeMillis();

						if (clickTime - lastClickTime <= 500) { // Check if the click interval is less than 500
																// milliseconds (adjust as needed)
//	    	                    	phone = textFieldSDT.getText();
//	    	                    	System.out.print(maPhong);

							GD_ChiTietDatPhong chitiet = new GD_ChiTietDatPhong();
							chitiet.setVisible(true);
							chitiet.setLocationRelativeTo(null);
							chitiet.loadChiTietPhong(maPhong);
							chitiet.loadChiTietDichVuDaDat(maPhong);
							chitiet.loadChiTietKhachHangDaDatPhong(maPhong);
							dispose();
						}

						lastClickTime = clickTime;

					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}
				});

				// phan loai phong
				if (ph.getLoaiPhong().getTenLoaiPhong().equals("Phòng Thường")) {
					lbl_hinhanh.setIcon(new ImageIcon(GD_PhongHat.class.getResource("/Imgs/microWait.png")));
				}

				else {
					lbl_hinhanh.setIcon(new ImageIcon(GD_PhongHat.class.getResource("/Imgs/microWaitVip.png")));
				}

			}
		}

	}

	// LOAD PHIEU DAT PHONG
	public void loadPhieuDatPhong(ActionEvent e) {
		GD_PhieuDatPhong phieuDatPhong = new GD_PhieuDatPhong();
		phieuDatPhong.setVisible(true);
		phieuDatPhong.setLocationRelativeTo(null);

		phieuDatPhong.loadPhieuDatPhongTuDuLieuNhap(maPhong, tenKH, sdt, cmnd, diaChi, songuoi, giaTien);
		dispose();
	}

// LÀM MỚI
	public void lammoi() {

		textFieldTenKhach.setText("");
		textFieldCMND.setText("");
		textFieldDiaChi.setText("");
		textFieldSoLuongNguoi.setText("");
		textFieldSDT.setText("");

	}

//	HÀM TÌM SỐ ĐIỆN THOẠI KH

	public void timSoDienThoai(String phone) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			// Kết nối đến cơ sở dữ liệu
			Connection connection = DriverManager.getConnection(url, username, password);

			// Truy vấn SQL để lấy dữ liệu
			String sql = "select * from KhachHang\r\n" + "where SDT = ? ";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, phone);
			ResultSet resultSet = statement.executeQuery();
			// Lặp qua các dòng kết quả và thêm vào JComboBox
			while (resultSet.next()) {
				String columnName1 = resultSet.getString("tenKH");
				String columnName2 = resultSet.getString("gioiTinh");
				String columnName3 = resultSet.getString("CMND");
				String columnName4 = resultSet.getString("DiaChi");
				@SuppressWarnings("unused")
				String columnName5 = resultSet.getString("SDT");
				String columnName6 = resultSet.getString("maKH");

				textFieldTenKhach.setText(columnName1);
				textFieldTenKhach.setEnabled(true);

				textFieldCMND.setText(columnName3);
				textFieldCMND.setEnabled(true);

				textFieldDiaChi.setText(columnName4);
				textFieldDiaChi.setEnabled(true);

				if (columnName2.equals("Nam")) {
					rdbtnNAM.setEnabled(true);
					rdbtnNAM.setSelected(true);
				} else {
					rdbtnNU.setEnabled(true);
					rdbtnNU.setSelected(true);
				}

				textFieldSoLuongNguoi.setEnabled(true);
				spinnerGioNhanPhong.setEnabled(true);
				rdbtnNgayMai.setEnabled(true);
				rdbtnHomNay.setEnabled(true);
				rdbtnHomNay.setSelected(true);
				maKhachHang = columnName6;
			}

			// Đóng các tài nguyên
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// HÀM CHUYỀN TRẠNG THÁI TRỐNG -> BẬN
	public void capNhatTrangThaiTrongThanhBan(String maPhong) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			// Kết nối đến cơ sở dữ liệu
			Connection connection = DriverManager.getConnection(url, username, password);

			// Truy vấn SQL để cập nhật dữ liệu
			String sql = "UPDATE Phong SET maTTP = 'TTP001' WHERE maPhong = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Thiết lập giá trị tham số cho mã phòng
			statement.setString(1, maPhong);

			// Thực hiện câu lệnh UPDATE
			@SuppressWarnings("unused")
			int rowsAffected = statement.executeUpdate();

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// HÀM CHUYỀN TRẠNG THÁI BẬN -> TRỐNG
	public void capNhatTrangThaiBanThanhTrong(String maPhong) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			// Kết nối đến cơ sở dữ liệu
			Connection connection = DriverManager.getConnection(url, username, password);

			// Truy vấn SQL để cập nhật dữ liệu
			String sql = "UPDATE Phong SET maTTP = 'TTP003' WHERE maPhong = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Thiết lập giá trị tham số cho mã phòng
			statement.setString(1, maPhong);

			// Thực hiện câu lệnh UPDATE
			@SuppressWarnings("unused")
			int rowsAffected = statement.executeUpdate();

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// HÀM CHUYỀN TRẠNG THÁI TRỐNG -> CHỜ
	@SuppressWarnings("unused")
	public void capNhatTrangThaiTrongThanhCho(String maPhong) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			// Kết nối đến cơ sở dữ liệu
			Connection connection = DriverManager.getConnection(url, username, password);

			// Truy vấn SQL để cập nhật dữ liệu
			String sql = "UPDATE Phong SET maTTP = 'TTP002' WHERE maPhong = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Thiết lập giá trị tham số cho mã phòng
			statement.setString(1, maPhong);

			// Thực hiện câu lệnh UPDATE
			int rowsAffected = statement.executeUpdate();

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// HÀM CHUYỀN TRẠNG THÁI CHỜ -> BẬN
	@SuppressWarnings("unused")
	public void capNhatTrangThaiChoThanhBan(String maPhong) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			// Kết nối đến cơ sở dữ liệu
			Connection connection = DriverManager.getConnection(url, username, password);

			// Truy vấn SQL để cập nhật dữ liệu
			String sql = "UPDATE Phong SET maTTP = 'TTP001' WHERE maPhong = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Thiết lập giá trị tham số cho mã phòng
			statement.setString(1, maPhong);

			// Thực hiện câu lệnh UPDATE
			int rowsAffected = statement.executeUpdate();

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// HÀM TAO PHIEU DAT PHONG
	public void taoPhieuDatPhong(String thoiGianDangKyPhong, String thoiGianNhanPhong, String maKhachHang,
			String maPhong, int soLuongKhach, String layMaNhanVien) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			// Kết nối đến cơ sở dữ liệu
			Connection connection = DriverManager.getConnection(url, username, password);

			// Lấy mã PDP hiện tại từ cơ sở dữ liệu
			String sqlGetMaxMaPDP = "SELECT MAX(maPDP) FROM [dbo].[PhieuDatPhong]";
			PreparedStatement statementGetMaxMaPDP = connection.prepareStatement(sqlGetMaxMaPDP);
			ResultSet resultSet = statementGetMaxMaPDP.executeQuery();

			String maPDP = "PDPAA001"; // Mã mặc định nếu không có dữ liệu trong bảng

			if (resultSet.next()) {
				// Nếu có dữ liệu, tăng giá trị của mã PDP
				String maxMaPDP = resultSet.getString(1);
				if (maxMaPDP != null) {
					int numberPart = Integer.parseInt(maxMaPDP.substring(5)); // Lấy phần số từ mã hiện tại
					numberPart++; // Tăng giá trị
					maPDP = "PDPAA" + String.format("%03d", numberPart); // Format mã mới
				}
			}

			// Thực hiện câu lệnh INSERT
			String sql = "INSERT INTO [dbo].[PhieuDatPhong] "
					+ "([maPDP], [thoiGianDangKyDatPhong], [thoiGianNhanPhong], [tinhTrangPhong], [maKH], [maNV], [maPhong],[soLuongKH],[tinhTrangPhieu] ) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			// Thiết lập giá trị tham số
			statement.setString(1, maPDP);
			statement.setString(2, thoiGianDangKyPhong); // thoiGianDangKyDatPhong
			statement.setString(3, thoiGianNhanPhong); // thoiGianNhanPhong
			statement.setString(4, "Bình thường");
			statement.setString(5, maKhachHang);
			statement.setString(6, layMaNhanVien); // Bạn cần cập nhật mã NV một cách đúng đắn
			statement.setString(7, maPhong);
			statement.setInt(8, soLuongKhach);
			statement.setBoolean(9,true);
			// Thực hiện câu lệnh INSERT
			@SuppressWarnings("unused")
			int rowsAffected = statement.executeUpdate();

			// Đóng các tài nguyên
			resultSet.close();
			statementGetMaxMaPDP.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Hàm kiểm tra mã khách hàng có trùng trong phiếu đặt hay không
	public void ktraKhachHangThuePhong(String maKhachHang, String maPhong) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";
		@SuppressWarnings("unused")
		String data1 = null, data2 = null;
		try {
			// Kết nối đến cơ sở dữ liệu
			Connection connection = DriverManager.getConnection(url, username, password);

			// Truy vấn SQL để lấy dữ liệu
			String sql = "select * from PhieuDatPhong";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery();
			// Lặp qua các dòng kết quả và thêm vào JComboBox
			while (resultSet.next()) {
				String columnName1 = resultSet.getString("maKH");
				String columnName2 = resultSet.getString("maPhong");

				System.out.print("Doi so truyen vao " + maKhachHang + "--" + maPhong);
				System.out.print("Du lieu data" + columnName1 + "--" + columnName2);

				if (maKhachHang.equals(columnName1) && maPhong.equals(columnName2)) {
					xoaPhieuDatPhong(maKhachHang, maPhong);
					capNhatTrangThaiBanThanhTrong(maPhong);
					reloadPhongHatList();

				}
				data1 = columnName1;
				data2 = columnName2;
			}

			if (!maKhachHang.equals(data1)) {
				JOptionPane.showMessageDialog(null,
						"Khách hàng có mã: " + maKhachHang + " không đặt phòng: " + maPhong);

			}

			// Đóng các tài nguyên
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ktraKhachHangDatPhong(String maKhachHang, String maPhong) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";
		@SuppressWarnings("unused")
		String data1 = null, data2 = null;
		try {
			// Kết nối đến cơ sở dữ liệu
			Connection connection = DriverManager.getConnection(url, username, password);

			// Truy vấn SQL để lấy dữ liệu
			String sql = "select * from PhieuDatPhong";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery();
			// Lặp qua các dòng kết quả và thêm vào JComboBox
			while (resultSet.next()) {
				String columnName1 = resultSet.getString("maKH");
				String columnName2 = resultSet.getString("maPhong");

				System.out.print("Doi so truyen vao " + maKhachHang + "--" + maPhong);
				System.out.print("Du lieu data" + columnName1 + "--" + columnName2);

				if (maKhachHang.equals(columnName1) && maPhong.equals(columnName2)) {

					capNhatTrangThaiChoThanhBan(maPhong);
					JOptionPane.showMessageDialog(null, "Nhận phòng thành công");
					reloadPhongHatList();

				}
				data1 = columnName1;
				data2 = columnName2;
			}

			if (!maKhachHang.equals(data1)) {
				JOptionPane.showMessageDialog(null,
						"Khách hàng có mã: " + maKhachHang + " không đặt phòng: " + maPhong);

			}

			// Đóng các tài nguyên
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// HÀM TAO PHIEU DAT PHONG
	public void xoaPhieuDatPhong(String maKhachHang, String maPhong) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			// Kết nối đến cơ sở dữ liệu
			Connection connection = DriverManager.getConnection(url, username, password);

			// Truy vấn SQL để xóa dữ liệu
			String sql = "DELETE FROM PhieuDatPhong WHERE maKH = ? AND maPhong = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, maKhachHang);
			statement.setString(2, maPhong);

			// Sử dụng executeUpdate() thay vì executeQuery()
			int rowsAffected = statement.executeUpdate();

			// Kiểm tra xem có dòng nào bị ảnh hưởng hay không
			if (rowsAffected > 0) {
				JOptionPane.showMessageDialog(null, "Hủy phòng " + maPhong + "thành công");
				theoDoiThaoTac("Bạn vừa mới hủy phòng hát: ", maPhong);

			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu để xóa");
			}

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void theoDoiThaoTac(String thaotac, String dulieu) {
		TheoDoiThaoTac.setText(thaotac + " " + dulieu);
	}

	public void reloadPhongHatList() {
		try {
			QLPH_DAO ds = new QLPH_DAO();
			ArrayList<Phong> listPhong = ds.docbang();

			if (listPhong != null && !listPhong.isEmpty()) {
				// Xóa tất cả các thành phần hiện tại trên panel_dsph
//				Right_QL_PHONG.removeAll();

				// Vẽ lại danh sách phòng hát
				draw(listPhong);

				// Cập nhật lại giao diện
//				contentPane.revalidate();
//				contentPane.repaint();
			} else {
				// Hiển thị thông báo hoặc xử lý khi không có dữ liệu phòng hát
				JOptionPane.showMessageDialog(this, "Không có dữ liệu phòng hát.", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			// Xử lý ngoại lệ, có thể hiển thị thông báo hoặc ghi log
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu phòng hát.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void timKhachDaThue(String phone) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			// Kết nối đến cơ sở dữ liệu
			Connection connection = DriverManager.getConnection(url, username, password);

			// Truy vấn SQL để lấy dữ liệu
			String sql = "select tenKH,SDT,maPhong from PhieuDatPhong pdp\r\n" + "inner join KhachHang kh\r\n"
					+ "on pdp.maKH = kh.maKH\r\n" + "where SDT = ? ";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, phone);
			ResultSet resultSet = statement.executeQuery();

			boolean found = false;

			// Lặp qua các dòng kết quả và hiển thị thông báo nếu tìm thấy
			while (resultSet.next()) {
				found = true;

				String tenKH = resultSet.getString("tenKH");
				String SDT = resultSet.getString("SDT");
				String maPhong = resultSet.getString("maPhong");

//                 Hiển thị thông báo
				String message = "Tên khách hàng: " + tenKH + "\nSố điện thoại: " + SDT + "\nMã phòng: " + maPhong;
				JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);

				// Load and display the list of rooms
				List<Phong> loadedRooms = loadPhongHatByMaPhong(maPhong);
				draw(loadedRooms);
			}

			// Kiểm tra và hiển thị thông báo nếu không tìm thấy
			if (!found) {
				JOptionPane.showMessageDialog(null, "Khách hàng chưa thuê phòng.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			}

			// Đóng các tài nguyên
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Định nghĩa hàm loadPhongHatByMaPhong

	public List<Phong> loadPhongHatByMaPhong(String maPhong) {
		QLPH_DAO ds = new QLPH_DAO();
		List<Phong> filteredList = new ArrayList<>();

		try {
			ArrayList<Phong> listPhong = ds.docbang();

			// Filter rooms based on the provided room code
			filteredList = listPhong.stream().filter(phong -> phong.getMaPhong().equals(maPhong))
					.collect(Collectors.toList());

			if (filteredList.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không có dữ liệu phòng hát có mã phòng " + maPhong, "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu phòng hát.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

		return filteredList;
	}

	public String phatSinhMaHoaDon() {
		String maHoaDon = "";

		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);

			// Truy vấn để lấy mã hóa đơn cuối cùng trong cơ sở dữ liệu
			String query = "SELECT TOP 1 maHD FROM HoaDon ORDER BY maHD DESC";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// Nếu có mã hóa đơn trong cơ sở dữ liệu, thực hiện phát sinh mã mới
			if (resultSet.next()) {
				String lastMaHoaDon = resultSet.getString("maHD");
				int number = Integer.parseInt(lastMaHoaDon.substring(4)) + 1;
				maHoaDon = String.format("HDAA%03d", number);
			} else {
				// Nếu không có mã hóa đơn nào trong cơ sở dữ liệu, tạo mã đầu tiên
				maHoaDon = "HDAA001";
			}

			// Đóng các tài nguyên
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return maHoaDon;
	}

	public void taoHoaDon(String gioNhanPhong, String ngayLap, String maKH, String maNV) {
		// Phát sinh mã hóa đơn
		String maHD = phatSinhMaHoaDon();
		layMaHD = maHD;
		@SuppressWarnings("unused")
		String layMaHDChoChiTiet = maHD;
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);

			// Thực hiện câu lệnh INSERT
			String sql = "INSERT INTO [dbo].[HoaDon] "
					+ "([maHD], [gioKetThuc], [gioNhanPhong], [ngayLap], [tienKhachTra], [tongTien], [maKH] , [maNV]) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			// Thiết lập giá trị tham số
			statement.setString(1, maHD);
			statement.setString(2, null); // Gio Ket Thuc
			statement.setString(3, gioNhanPhong); // Gio Nhan Phong
			statement.setString(4, ngayLap);
			statement.setFloat(5, 0);
			statement.setFloat(6, 0);
			statement.setString(7, maKH);
			statement.setString(8, maNV);

			// Thực hiện câu lệnh INSERT
			statement.executeUpdate();

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void taoChiTietHoaDon(String maHD) {
		// Phát sinh mã hóa đơn

		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);

			// Thực hiện câu lệnh INSERT
			String sql = "insert into ChiTietHoaDon ([maHD],[maPhong],[thoiLuong]) values (?,?,0)";

			PreparedStatement statement = connection.prepareStatement(sql);

			// Thiết lập giá trị tham số
			statement.setString(1, layMaHD);
			statement.setString(2, maPhong);

			// Thực hiện câu lệnh INSERT
			statement.executeUpdate();

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void xoaChiTietHoaDon(String maHD) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);

			// Thực hiện câu lệnh DELETE
			String sql = "delete from ChiTietHoaDon where maHD = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

//            System.out.print("Lay madh cho chi tiet hd" + layMaHDChoChiTiet);

			// Thiết lập giá trị tham số
			statement.setString(1, maHD);

			// Thực hiện câu lệnh DELETE
			statement.executeUpdate();

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String layMaHoaDon(String maPhong) {
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";
		String maHD = null;

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String sqlQuery = "SELECT *\r\n" + "FROM ChiTietHoaDon\r\n"
					+ "WHERE maHD = (SELECT MAX(maHD) FROM ChiTietHoaDon WHERE maPhong = ?)";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
				preparedStatement.setString(1, maPhong);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						maHD = resultSet.getString("maHD");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return maHD;
	}

	public String layMaNhanVien(String tenNhanVien) {
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";
		String maNV = null;

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String sqlQuery = "select * from NhanVien\r\n" + "where tenNV = ? ";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
				preparedStatement.setString(1, tenNhanVien);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						maNV = resultSet.getString("maNV");

					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return maNV;
	}

	public void xoaChiTietDichVu(String maHD) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);

			// Thực hiện câu lệnh DELETE
			String sql = "delete from ChiTietDichVu where maHD = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

//	            System.out.print("Lay madh cho chi tiet hd" + layMaHDChoChiTiet);

			// Thiết lập giá trị tham số
			statement.setString(1, maHD);

			// Thực hiện câu lệnh DELETE
			statement.executeUpdate();

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void xoaHoaDon(String maHD) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);

			// Thực hiện câu lệnh DELETE
			String sql = "delete from HoaDon where maHD = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

//	            System.out.print("Lay madh cho chi tiet hd" + layMaHDChoChiTiet);

			// Thiết lập giá trị tham số
			statement.setString(1, maHD);

			// Thực hiện câu lệnh DELETE
			statement.executeUpdate();

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Phong> loadPhongHatByMaPhong(List<String> maPhong) {
		QLPH_DAO ds = new QLPH_DAO();
		List<Phong> filteredList = new ArrayList<>();

		try {
			int i = 0;
			ArrayList<Phong> listPhong = ds.docbang();

			// Filter rooms based on the provided room code
			for (Phong ph : listPhong) {
				if (i < maPhong.size()) {
					if (ph.getMaPhong().equals(maPhong.get(i))) {
						filteredList.add(ph);
						i++;
					}
				}
			}

			if (filteredList.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không có dữ liệu phòng hát có mã phòng " + maPhong, "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu phòng hát.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

		return filteredList;
	}

	@SuppressWarnings("unused")
	private static void resetUI(JComponent component) {
		// Thực hiện các bước cần thiết để reset giao diện
		// Trong trường hợp này, chúng ta có thể xóa hoặc thay đổi các thành phần trên
		// component
		component.removeAll();

		// Thực hiện các cập nhật khác cần thiết tại đây

		// Sau đó, gọi revalidate và repaint để cập nhật giao diện
		component.revalidate();
		component.repaint();
	}
}
