package GiaoDien;

import java.awt.Color;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DAO.*;
import Entity.*;
import connectDB.connectDB;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTable;

import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class GD_QLNhanVien extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtten;
	private JTextField txtsdt;
	private JRadioButton rdbtnNAM;
	private JRadioButton rdbtnNU;
	private JTextField txtcmnd;
	private JTextField txtdc;
	private JTable table;
	DefaultTableModel model;
	private JLabel lblClock;
	private Timer timer;
	private JTextField txtns;
	private JTextField textFieldTrangThaiLamViec;
	@SuppressWarnings("unused")
	private String TrangThaiLamViec[] = {"Đang làm","Nghĩ việc","Tạm nghĩ việc"};
	private JComboBox<String> cbLoaiNhanVien;
	@SuppressWarnings("unused")
	private JComboBox<String> cbtrangthai;
	@SuppressWarnings("unused")
	private LoaiNhanVien_DAO dslnv;
	QLNV_DAO dsnv = new QLNV_DAO();
	QLTK_DAO dstk = new QLTK_DAO();
	private JTextField txtMaNV;
	private JTextField txt_maTK;
	private JTextField txtTim;
	ButtonGroup bg = new ButtonGroup();
	
	

	
	 
	/**
	 * Create the frame.
	 */
	public GD_QLNhanVien() {
		
		try {
			connectDB.getInstance().connect();
//			loadTable();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		dslnv = new LoaiNhanVien_DAO();
		

		initComponents();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1175, 650);
		setResizable(false);
		setTitle("Giao Diện Nhân Viên");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel jpanel = new JPanel();
		jpanel.setBounds(0, 70, 232, 80);
		contentPane.setLayout(null);
		
//		 Ho tro -----------------------
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://thach1311.github.io/huongDan/").toURI());
				}
				catch(Exception ex){}
			}
		});
		btnNewButton.setIcon(new ImageIcon(GD_Main_NV.class.getResource("/Imgs/iconHoTro.png")));
		btnNewButton.setBounds(304, 10, 49, 50);
		contentPane.add(btnNewButton);
		
//		------------------------------------------
//		Clock ---------------------------------
		JPanel box_clock = new JPanel();
		box_clock.setBounds(34, 10, 260, 50);
        box_clock.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(box_clock);
        box_clock.setLayout(null);

        lblClock = new JLabel();
        lblClock.setHorizontalAlignment(SwingConstants.CENTER);
        lblClock.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblClock.setBounds(0, 0, 260, 50);
        lblClock.setOpaque(true);
        lblClock.setBackground(Color.WHITE);
        box_clock.add(lblClock);

        timer = new Timer(0, this);
        timer.start();
		
		JLabel lblquanly = new JLabel("QL:");
		lblquanly.setForeground(Color.WHITE);
		lblquanly.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblquanly.setBounds(878, -20, 232, 80);
		contentPane.add(lblquanly);
		
		JLabel lbltenql = new JLabel();
		lbltenql.setForeground(Color.WHITE);
		lbltenql.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltenql.setBounds(853, 6, 232, 80);
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
		        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất!", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		            GD_Login lg = new GD_Login();
		            lg.setVisible(true);
		            lg.setLocationRelativeTo(null);
		            dispose();
		        }
		    }
		});
		
		testbutton.Buttontest btnphonghat = new testbutton.Buttontest();
        btnphonghat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_PhongHat gdphong = new GD_PhongHat();
				gdphong.setVisible(true);
				dispose();
			}
		});
        KeyStroke keyStrokePhongHat = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        btnphonghat.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokePhongHat, "F1");
        btnphonghat.getActionMap().put("F1", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_PhongHat gdphong = new GD_PhongHat();
				gdphong.setVisible(true);
				dispose();
            }
        });
        btnphonghat.setBorder(null);
        btnphonghat.setText("Phòng Hát (F1)");
        btnphonghat.setForeground(Color.WHITE);
        btnphonghat.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnphonghat.setBackground(new Color(0, 0, 0, 150));
        btnphonghat.setBounds(0, 70, 232, 87);
		contentPane.add(btnphonghat);
		btnphonghat.setLayout(null);
		
		testbutton.Buttontest btndichvu = new testbutton.Buttontest();
        btndichvu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_QLDichVu gdqldv = new GD_QLDichVu();
				gdqldv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqldv.setVisible(true);
				dispose();
			}
		});
        KeyStroke keyStrokeDichVu = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
        btndichvu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeDichVu, "F2");
        btndichvu.getActionMap().put("F2", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_QLDichVu gdqldv = new GD_QLDichVu();
				gdqldv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdqldv.setVisible(true);
				dispose();
            }
        });
        btndichvu.setBorder(null);
        btndichvu.setText("Dịch Vụ (F2)");
        btndichvu.setForeground(Color.WHITE);
        btndichvu.setFont(new Font("Tahoma", Font.BOLD, 20));
        btndichvu.setBackground(new Color(0, 0, 0, 150));
        btndichvu.setBounds(230, 70, 239, 87);
		contentPane.add(btndichvu);
		btndichvu.setLayout(null);
		
		testbutton.Buttontest btnnhanvien = new testbutton.Buttontest();
        btnnhanvien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
        btnnhanvien.setBorder(null);
        btnnhanvien.setText("Nhân Viên (F3)");
        btnnhanvien.setForeground(Color.WHITE);
        btnnhanvien.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnnhanvien.setBackground(new Color(128, 128, 128, 150));
        btnnhanvien.setBounds(465, 70, 232, 87);
		contentPane.add(btnnhanvien);
		btnnhanvien.setLayout(null);
		
		testbutton.Buttontest btntaikhoan = new testbutton.Buttontest();
        btntaikhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_TaiKhoan gdtaikhoan = new GD_TaiKhoan();
				gdtaikhoan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtaikhoan.setVisible(true);
				dispose();

			}
		});
        KeyStroke keyStroketk = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0);
        btntaikhoan.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroketk, "F4");
        btntaikhoan.getActionMap().put("F4", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_TaiKhoan gdtaikhoan = new GD_TaiKhoan();
				gdtaikhoan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtaikhoan.setVisible(true);
				dispose();
            }
        });
        btntaikhoan.setBorder(null);
        btntaikhoan.setText("Tài Khoản (F4)");
        btntaikhoan.setForeground(Color.WHITE);
        btntaikhoan.setFont(new Font("Tahoma", Font.BOLD, 20));
        btntaikhoan.setBackground(new Color(0, 0, 0, 150));
        btntaikhoan.setBounds(695, 70, 232, 87);
		contentPane.add(btntaikhoan);
		btntaikhoan.setLayout(null);
		
		testbutton.Buttontest btnthongke = new testbutton.Buttontest();
        btnthongke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_ThongKeHoaDon gdtkhd = new GD_ThongKeHoaDon();
				gdtkhd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtkhd.setVisible(true);
				dispose();

			}
		});
        KeyStroke keyStroketke = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
        btnthongke.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroketke, "F5");
        btnthongke.getActionMap().put("F5", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_ThongKeHoaDon gdtkhd = new GD_ThongKeHoaDon();
				gdtkhd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtkhd.setVisible(true);
				dispose();
            }
        });
        btnthongke.setBorder(null);
        btnthongke.setText("Thống Kê (F5)");
        btnthongke.setForeground(Color.WHITE);
        btnthongke.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnthongke.setBackground(new Color(0, 0, 0, 150));
        btnthongke.setBounds(925, 70, 232, 87);
		contentPane.add(btnthongke);
		btnthongke.setLayout(null);
		
		JPanel panel = new JPanel() {
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
		panel.setOpaque(false);
		panel.setBounds(-2, 151, 1173, 172);
		panel.setBackground(new Color(255, 255, 255,200));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập Họ Tên");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 10, 159, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số Điện Thoại");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(156, 90, 145, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số CMND");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(324, 90, 92, 23);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Địa Chỉ");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(504, 93, 193, 20);
		panel.add(lblNewLabel_1_4);
		
		txtten = new JTextField();
		txtten.setBounds(10, 37, 173, 27);
		panel.add(txtten);
		txtten.setColumns(10);
		
		//rad button
		rdbtnNAM = new JRadioButton("Nam");
		rdbtnNAM.setBounds(304, 37, 67, 23);
		rdbtnNAM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNAM.setOpaque(false);
		rdbtnNAM.setContentAreaFilled(false);
		rdbtnNAM.setFocusPainted(false);
		panel.add(rdbtnNAM);
	
		
		rdbtnNU = new JRadioButton("Nu");
		rdbtnNU.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNU.setBounds(373, 37, 55, 23);
		rdbtnNU.setOpaque(false);
		panel.add(rdbtnNU);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNAM);
		bg.add(rdbtnNU);
		panel.add(rdbtnNAM);
		panel.add(rdbtnNU);
		//radbuton//
		
		txtsdt = new JTextField();
		txtsdt.setColumns(10);
		txtsdt.setBounds(156, 123, 128, 27);
		panel.add(txtsdt);
		
		txtcmnd = new JTextField();
		txtcmnd.setColumns(10);
		txtcmnd.setBounds(321, 123, 152, 27);
		panel.add(txtcmnd);
		
		txtdc = new JTextField();
		txtdc.setColumns(10);
		txtdc.setBounds(504, 123, 193, 27);
		panel.add(txtdc);
		
		//button them
		testbutton.Buttontest btnthem = new testbutton.Buttontest();
		btnthem.setText("Thêm");
		btnthem.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnthem.setForeground(SystemColor.text);
		btnthem.setBackground(new Color(90, 167, 167));
		btnthem.setRippleColor(new Color(255, 255, 255));
		btnthem.setShadowColor(new Color(0,0,0));
		btnthem.setBounds(859, 26, 128, 48);

		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hoTenNV = txtten.getText();
				String namSinhNV = txtns.getText();
				@SuppressWarnings("unused")
				String trangThai = textFieldTrangThaiLamViec.getText();
				String sdtNV = txtsdt.getText();
				String cmndNV = txtcmnd.getText();
				String diaChiNV = txtdc.getText();
				
				if(hoTenNV.matches( "^[A-Z][\\p{L}']*([\\p{Zs}\\-'][A-Z][\\p{L}']*)*$") == false){
					  JOptionPane.showMessageDialog(null,"Họ và tên phải có ký tự đầu viết hoa, có dấu và không có ký tự đặc biệt");
		        }
				else if (rdbtnNAM.isSelected() == false && rdbtnNU.isSelected() == false) {
					JOptionPane.showMessageDialog(null, "Giới tính không được để trống");
				}
				else if (!namSinhNV.matches("19\\d{2}|20\\d{2}") || Integer.parseInt(namSinhNV) >= Calendar.getInstance().get(Calendar.YEAR)) {
				    JOptionPane.showMessageDialog(null,"Năm sinh phải 4 chữ số, không có ký tự đặc biệt và trước năm hiện tại");
				}
				else if(sdtNV.matches("^0[1-9]\\d{8}$") == false) {
					JOptionPane.showMessageDialog(null, "Số điện thoại di động có 10 chữ số, 2 chữ số đấu là 09.");
				}
				else if(cmndNV.matches("^[0-9]{9}$") == false) {
					JOptionPane.showMessageDialog(null, "Số chứng minh nhân dân phải 9 chữ số");
				}
				else if(diaChiNV.matches("^[\\p{L}0-9,\\s.'-]+$") == false) {
					JOptionPane.showMessageDialog(null, "Cho phép các ký tự phổ biến trong địa chỉ như dấu phẩy, khoảng trắng, dấu chấm, dấu nháy đơn, dấu gạch ngang và số");
				}
		        else {
		        	btnthemActionPerformed(e);
		        }
			}
			@SuppressWarnings("unused")
			private boolean isValid(String hoTen, String namSinh, String trangThai, String sdt, String cmnd, String diaChi) {
			    // Kiểm tra 1: Tất cả tham số không được trống
			    if (hoTen.isEmpty() || namSinh.isEmpty() || trangThai.isEmpty() || sdt.isEmpty() || cmnd.isEmpty() || diaChi.isEmpty()) {
			        return false;
			    }

			    // Kiểm tra 2: hoTenNV phải có ký tự đầu viết hoa có dấu
			    if (!hoTen.matches("\\p{Lu}\\p{L}*")) {
			        JOptionPane.showMessageDialog(null, "Tên phải có ký tự đầu viết hoa, có dấu");
			        return false;
			    }

			    // Kiểm tra 3: namSinhNV phải trước năm hiện tại và có 4 chữ số
			    if (!namSinh.matches("19\\d{2}|20\\d{2}") || Integer.parseInt(namSinh) >= Calendar.getInstance().get(Calendar.YEAR)) {
			        JOptionPane.showMessageDialog(null, "Năm sinh phải 4 chữ số, không có ký tự đặc biệt và trước năm hiện tại");
			        return false;
			    }

			    // Kiểm tra 4: sdtNV bắt đầu bằng 09 và cuối cùng là 7 chữ số
			    if (!sdt.matches("09\\d{7}")) {
			        JOptionPane.showMessageDialog(null, "Số điện thoại di động có 10 chữ số, 2 chữ số đầu là 09.");
			        return false;
			    }

			    // Kiểm tra 5: cmndNV phải 9 chữ số
			    if (!cmnd.matches("\\d{9}")) {
			        JOptionPane.showMessageDialog(null, "Số chứng minh nhân dân phải 9 chữ số");
			        return false;
			    }

			    // Kiểm tra 6: diaChiNV cho phép các ký tự như dấu phẩy, khoản trắng, dấu chấm, dấu nháy đơn, dấu gạch ngang và số
			    if (!diaChi.matches("[\\w,\\s.'-]+")) {
			        JOptionPane.showMessageDialog(null, "Cho phép các ký tự phổ biến trong địa chỉ như dấu phẩy, khoản trắng, dấu chấm, dấu nháy đơn, dấu gạch ngang và số");
			        return false;
			    }
			    
			    // Kiểm tra 7: diaChiNV không được trống
			    if (diaChi.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống");
			        return false;
			    }


			    // Nếu tất cả điều kiện ràng buộc đều thỏa, trả về true
			    return true;
			}

		});
		panel.add(btnthem);
		
		
		//button xoa
		testbutton.Buttontest btnxoa = new testbutton.Buttontest();
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SystemColor.text);
		btnxoa.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnxoa.setBackground(new Color(254, 122, 21));
		btnxoa.setBounds(859, 112, 128, 48);
		btnxoa.setRippleColor(new Color(255, 255, 255));
		btnxoa.setShadowColor(new Color(0,0,0));
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnxoaActionPerformed(e);
			}
		});
		panel.add(btnxoa);
		
		//button sua
		testbutton.Buttontest btnsua = new testbutton.Buttontest();
		btnsua.setText("Sửa");
		btnsua.setForeground(SystemColor.text);
		btnsua.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnsua.setBackground(new Color(226, 211, 107));
		btnsua.setBounds(1018, 26, 128, 48);
		btnsua.setRippleColor(new Color(255, 255, 255));
		btnsua.setShadowColor(new Color(0,0,0));
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsuaActionPerformed(e);
			}
		});
		panel.add(btnsua);
		
		//button lammoi
		testbutton.Buttontest btnLmMi = new testbutton.Buttontest();
	    btnLmMi.setText("Làm mới");
		btnLmMi.setForeground(SystemColor.text);
		btnLmMi.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnLmMi.setBackground(new Color(51, 83, 158));
		btnLmMi.setBounds(1018, 112, 128, 48);
		btnLmMi.setRippleColor(new Color(255, 255, 255));
		btnLmMi.setShadowColor(new Color(0,0,0));
		btnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lammoi();
			}
		});
		panel.add(btnLmMi);
		
		txtns = new JTextField();
		txtns.setColumns(10);
		txtns.setBounds(422, 37, 92, 27);
		panel.add(txtns);
		
		JLabel lblNewLabel_1_2 = new JLabel("Năm Sinh");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(422, 10, 79, 28);
		panel.add(lblNewLabel_1_2);
		
		

		
		cbLoaiNhanVien = new JComboBox<>();
		cbLoaiNhanVien.setBounds(548, 37, 104, 27);
		panel.add(cbLoaiNhanVien);
		loadComBoBox();
		
		
		
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Loại NV");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1.setBounds(538, 10, 79, 28);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Trạng Thái Làm Việc");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1.setBounds(674, 10, 159, 28);
		panel.add(lblNewLabel_1_2_1_1);
		
		textFieldTrangThaiLamViec = new JTextField();
		textFieldTrangThaiLamViec.setText("Đang làm");
		textFieldTrangThaiLamViec.setColumns(10);
		textFieldTrangThaiLamViec.setBounds(676, 40, 173, 27);
		panel.add(textFieldTrangThaiLamViec);
		
		txtMaNV = new JTextField();
		txtMaNV.setBounds(10, 123, 128, 24);
		txtMaNV.setEnabled(false);
		panel.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		JLabel lbl_manv = new JLabel("Mã Nhân Viên");
		lbl_manv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_manv.setBounds(10, 91, 104, 20);
		panel.add(lbl_manv);
		
		txt_maTK = new JTextField();
		txt_maTK.setBounds(195, 37, 106, 27);
		txt_maTK.setEnabled(false);
		panel.add(txt_maTK);
		txt_maTK.setColumns(10);
		
		JLabel lbl_matk = new JLabel("Mã Tài Khoản");
		lbl_matk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_matk.setBounds(192, 19, 109, 14);
		panel.add(lbl_matk);
		
		JButton btntimkiem = new JButton("");
		btntimkiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timKiemTheoMaTaiKhoan();
			}
		});
		btntimkiem.setIcon(new ImageIcon(GD_TaiKhoan.class.getResource("/Imgs/search.png")));
		btntimkiem.setBounds(714, 123, 39, 27);
		panel.add(btntimkiem);
		
		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTim.setBounds(753, 123, 96, 27);
		panel.add(txtTim);
		txtTim.setColumns(10);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Tìm Kiếm");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4_1.setBounds(753, 97, 96, 20);
		panel.add(lblNewLabel_1_4_1);
		    
		
//		txt_MaTK = new JTextField();
//		txt_MaTK.setBounds(204, 37, 67, 27);
//		panel.add(txt_MaTK);
//		txt_MaTK.setColumns(10);
		
//		JLabel lblNewLabel_2 = new JLabel("Mã Tài Khoản");
//		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		lblNewLabel_2.setBounds(190, 10, 111, 28);
//		panel.add(lblNewLabel_2);
		
		
		//talbe
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 323, 1161, 290);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NhanVien nv = reverSPFromTextField();
				int row = table.getSelectedRow();
				txtMaNV.setText(model.getValueAt(row, 0).toString());
				txtten.setText(model.getValueAt(row, 1).toString());
				if(model.getValueAt(row, 2).equals("Nam")) {
					rdbtnNAM.setSelected(true);
				}
				if(model.getValueAt(row, 2).equals("Nu")) {
					rdbtnNU.setSelected(true);
				}
				nv.setGioiTinh(model.getValueAt(row, 2).toString());
				txtns.setText(model.getValueAt(row, 3).toString());
				txtcmnd.setText(model.getValueAt(row, 4).toString());
				txtsdt.setText(model.getValueAt(row, 5).toString());
				textFieldTrangThaiLamViec.setText(model.getValueAt(row, 6).toString());
				txtdc.setText(model.getValueAt(row, 7).toString());
				String tenLNV = model.getValueAt(row, 8).toString();
				if(tenLNV.equals("LNV001")) {
                	tenLNV = "Quản lý";
                }else if(tenLNV.equals("LNV002")) {
                	tenLNV = "Thu ngân";
                }else if(tenLNV.equals("LNV003")) {
                	tenLNV = "Lễ tân";
                }else if(tenLNV.equals("LNV004")) {
                	tenLNV = "Bảo vệ";
                }else if(tenLNV.equals("LNV005")) {
                	tenLNV = "Vệ sinh";
                }
				cbLoaiNhanVien.setSelectedItem(tenLNV);
				txt_maTK.setText(model.getValueAt(row, 9).toString());
			}
		});
		// Set the component orientation to RIGHT_TO_LEFT
		
		contentPane.add(scrollPane);
		
	    model = new DefaultTableModel();
		model.addColumn("Mã NV");
		model.addColumn("Họ Tên");
		model.addColumn("Giới tính");
		model.addColumn("Năm Sinh");
		model.addColumn("Số CMND");
		model.addColumn("Số Điện Thoại");
		model.addColumn("Trạng Thái Làm Việc");
		model.addColumn("Địa Chỉ");
		model.addColumn("Loại nhân viên");
		model.addColumn("Mã tài khoản");

		table.setModel(model);
		
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 500);
		scrollPane.setRowHeaderView(scrollBar);
		
//         -------------------------------
		JLabel lblavatar = new JLabel("");
		lblavatar.setHorizontalAlignment(SwingConstants.CENTER);
		lblavatar.setIcon(new ImageIcon(GD_TaiKhoan.class.getResource("/Imgs/t1 1.png")));
		lblavatar.setBounds(90, -444, 1333, 957);
		contentPane.add(lblavatar);
		
		JLabel hinhnen = new JLabel("");
		hinhnen.setBounds(0, 0, 1161, 613);
		contentPane.add(hinhnen);
		hinhnen.setBackground(new Color(240, 240, 240));
		hinhnen.setForeground(new Color(255, 0, 0));
		hinhnen.setIcon(new ImageIcon(GD_QLNhanVien.class.getResource("/Imgs/370.png")));

		
//		connectDB.getInstance().connect();
		updateTableData();
			
		
	}
	private void timKiemTheoMaTaiKhoan() {
	    String maTKCanTim = txtTim.getText();
	    ArrayList<NhanVien> ketQuaTimKiem = dsnv.timnv(maTKCanTim);

	    // Xóa dữ liệu hiện tại trong bảng
	    model.setRowCount(0);

	    // Hiển thị kết quả tìm kiếm trong bảng
	    if (ketQuaTimKiem.isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên");
	    } else {
	        for (NhanVien n : ketQuaTimKiem) {
	        	
	            Object[] rowData = {n.getMaNV(), n.getTenNV(), n.getGioiTinh(), n.getNgaySinh(), n.getCMND(), n.getSDT(), n.getTrangThaiLamViec(), n.getMaDC(), n.getLNV().getMaLNV(), n.getMaTK().getMaTaiKhoan()};
	            model.addRow(rowData);
	        }
	    }
	}
	
	private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         GD_Main_QL mainql=new GD_Main_QL();
         mainql.setVisible(true);
    }
    
	private NhanVien reverSPFromTextField() {
		String maNV = txtMaNV.getText().toString();
		String ten = txtten.getText().toString();
		String gt = "";
		if (rdbtnNAM != null && rdbtnNAM.isSelected()) {
			gt = rdbtnNAM.getText();
	    }
	    if (rdbtnNU != null && rdbtnNU.isSelected()) {
	    	gt = rdbtnNU.getText();
	    }
		String ngaySinh = txtns.getText().toString();
		String cmnd = txtcmnd.getText().toString();
		String sdt = txtsdt.getText().toString();
		String trangThaiLamViec = textFieldTrangThaiLamViec.getText().toString();
		String dc = txtdc.getText().toString();
		LoaiNhanVien loainv = new LoaiNhanVien("","");
		
		if(cbLoaiNhanVien.getSelectedItem().toString().equals("Quản lý")) {
			loainv = new LoaiNhanVien("LNV001","");
		}else if(cbLoaiNhanVien.getSelectedItem().toString().equals("Thu ngân")) {
			loainv = new LoaiNhanVien("LNV002","");
		}else if(cbLoaiNhanVien.getSelectedItem().toString().equals("Lễ tân")) {
			loainv = new LoaiNhanVien("LNV003","");
		}else if(cbLoaiNhanVien.getSelectedItem().toString().equals("Bảo vệ")) {
			loainv = new LoaiNhanVien("LNV004","");
		}else if(cbLoaiNhanVien.getSelectedItem().toString().equals("Vệ sinh")) {
			loainv = new LoaiNhanVien("LNV005","");
		}
		TaiKhoanNhanVien maTK = new TaiKhoanNhanVien(txt_maTK.getText().toString(), null, null, null,null);
		return new NhanVien(maNV,ten,gt,ngaySinh,cmnd,sdt,trangThaiLamViec,dc,loainv,maTK);
	}
	
	
	
	protected void btnsuaActionPerformed(ActionEvent e) {
		QLNV_DAO dsnv = new QLNV_DAO();
		int row = table.getSelectedRow();
		
		if(row >= 0) {
			NhanVien nv = reverSPFromTextField();
			if(dsnv.update(nv)) {

				table.setValueAt(txtten.getText(), row, 1);
				table.setValueAt(nv.getGioiTinh(), row, 2);
				table.setValueAt(txtns.getText(), row, 3);
				table.setValueAt(txtcmnd.getText(), row, 4);
				table.setValueAt(txtsdt.getText(), row, 5);
				table.setValueAt(textFieldTrangThaiLamViec.getText(), row, 6);
				table.setValueAt(txtdc.getText(), row, 7);
				table.setValueAt(cbLoaiNhanVien.getSelectedItem().toString(), row, 8);
				table.setValueAt(txt_maTK.getText(), row, 9);
//				table.setValueAt(txtMaNV.getText(), row, 10);
				JOptionPane.showMessageDialog(this, "Sửa thông tin nhân viên thành công!");
				table.setModel(model);
			}
		}
		loadTable();
		
	}

	protected void btnxoaActionPerformed(ActionEvent e) {
		QLNV_DAO dsnv = new QLNV_DAO();
		int row = table.getSelectedRow();
		if(row >= 0) {
			String manv = (String) table.getValueAt(row, 0);
			if(dsnv.delete(manv)) {
				model.removeRow(row);
				lammoi();
			}
		}
		JOptionPane.showMessageDialog(this, "Xóa Nhân viên Thành Công");
		
	}

	protected void btnthemActionPerformed(ActionEvent e) {
		QLNV_DAO dsnv = new QLNV_DAO();
		int maxMaNV = dsnv.getMaxMaNV();
		int maxMaTK_NV = dsnv.getMaxMaTK();
		int maxMaTK = dstk.getMaxMaTaiKhoan();
		var maTK_NV = "";		
		maxMaNV++;
		
		txt_maTK.setText("TK" + String.format("%03d", maxMaTK));
		txtMaNV.setText("NVAA" + String.format("%04d", maxMaNV));
		maTK_NV = "TK" + String.format("%03d", maxMaTK_NV);
		
		
		NhanVien nv = reverSPFromTextField();
		
		if(txt_maTK.getText().equals(maTK_NV)) {
		    JOptionPane.showMessageDialog(this, "Mã tài khoản đã được sử dụng hãy tạo tài khoản mới trước khi thêm nhân viên");
		    GD_TaiKhoan tk = new GD_TaiKhoan();
		    tk.setVisible(true);
		    dispose();
		    lammoi();
		}
		else {
			if(dsnv.create(nv)){
			    Object [] rowData = {txtMaNV.getText(),txtten.getText(),nv.getGioiTinh(),txtns.getText(),txtcmnd.getText(),txtsdt.getText(),textFieldTrangThaiLamViec.getText(),txtdc.getText(),nv.getLNV().getMaLNV(),txt_maTK.getText()};
				model.addRow(rowData);
				JOptionPane.showMessageDialog(this, "Thêm Nhân Viên Thành Công");
				lammoi();
				loadTable();
			}
		}
	}
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
        int month = cal.get(Calendar.MONTH)+1;
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

	
	
	private void updateTableData() {
		QLNV_DAO ds = new QLNV_DAO();
		ArrayList<NhanVien> ls = ds.doctubang();
		for(NhanVien s : ls) {
			String [] rowData = {s.getMaNV(), s.getTenNV(),s.getGioiTinh(), s.getNgaySinh(), s.getCMND(),s.getSDT(), s.getTrangThaiLamViec() , s.getMaDC(),s.getLNV().getMaLNV(),s.getMaTK().getMaTaiKhoan()};
			model.addRow(rowData);
			table.setModel(model);
		}
		
	}
	

	public void loadComBoBox() {

		// Thông tin kết nối đến cơ sở dữ liệu
        String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
        String username = "sa";
        String password = "123";
        
        String tenLNV = "";
        
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection connection = DriverManager.getConnection(url, username, password);
            // Truy vấn SQL để lấy dữ liệu
            String sql = "SELECT maLNV FROM LoaiNhanVien";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Lặp qua các dòng kết quả và thêm vào JComboBox
            while (resultSet.next()) {
                String columnName = resultSet.getString("maLNV");
                tenLNV = columnName;
                if(tenLNV.equals("LNV001")) {
                	tenLNV = "Quản lý";
                }else if(tenLNV.equals("LNV002")) {
                	tenLNV = "Thu ngân";
                }else if(tenLNV.equals("LNV003")) {
                	tenLNV = "Lễ tân";
                }else if(tenLNV.equals("LNV004")) {
                	tenLNV = "Bảo vệ";
                }else if(tenLNV.equals("LNV005")) {
                	tenLNV = "Vệ sinh";
                }
                cbLoaiNhanVien.addItem(tenLNV);
            }

            // Đóng các tài nguyên
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	public void loadTable() {
		QLNV_DAO dsnv = new QLNV_DAO();
		model.setRowCount(0);
			for(NhanVien s : dsnv.doctubang()) {
			Object  rowData[] = {s.getMaNV(),s.getTenNV(),s.getGioiTinh(),s.getNgaySinh(),s.getCMND(),s.getSDT(),s.getTrangThaiLamViec(),s.getMaDC(),s.getLNV().getMaLNV(),s.getMaTK().getMaTaiKhoan()};

			model.addRow(rowData);

		}
	}
	
//	public void mouseClicked(MouseEvent e) {
//		int row = table.getSelectedRow();
//		txtMaNV.setText(table.getValueAt(row, 0).toString());
//		txtten.setText(table.getValueAt(row,2).toString());
//		r.setText(table.getValueAt(row, 3).toString());
//		txtmk.setText(table.getValueAt(row, 4).toString());
//	}

	
	public void lammoi() {
		txtMaNV.setText("");
		txt_maTK.setText("");
		txtcmnd.setText("");
		txtten.setText("");
		txtns.setText("");
		txtsdt.setText("");
		txtdc.setText("");
		textFieldTrangThaiLamViec.setText("");
		cbLoaiNhanVien.setSelectedIndex(1);
		txtTim.setText("");
		bg.clearSelection();
		loadTable();

	}
}