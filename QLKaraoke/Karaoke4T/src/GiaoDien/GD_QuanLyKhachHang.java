package GiaoDien;


import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
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
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTable;
import DAO.*;
import Entity.*;
import connectDB.*;


import javax.swing.JScrollBar;
public class GD_QuanLyKhachHang extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtten, txtdc, txtmakh, txtsdt, txtcmnd;
	private JTable table;
	DefaultTableModel model;
	private JLabel lblClock;
	private Timer timer;
	private JRadioButton rdbtnNAM, rdbtnNU;
	QLKH_DAO dskh = new QLKH_DAO();
	private ButtonGroup bg;
	private JLabel TheoDoiThaoTac;
	private JTextField txtTim;
	
	/**
	 * Launch the application.
	 */

	 
	/**
	 * Create the frame.
	 */
	public GD_QuanLyKhachHang() {
		initComponents();
		setResizable(false);
		try {
			connectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		QLKH_DAO dskh = new QLKH_DAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1175, 650);
		setTitle("Giao Diện Khách Hàng");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel jpanel = new JPanel();
		jpanel.setBounds(0, 70, 232, 80);
		contentPane.setLayout(null);
		

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
		
        TheoDoiThaoTac = new JLabel("Theo dõi thao tác");
        TheoDoiThaoTac.setIcon(new ImageIcon(GD_QuanLyKhachHang.class.getResource("/Imgs/eyes.png")));
		TheoDoiThaoTac.setForeground(Color.WHITE);
		TheoDoiThaoTac.setFont(new Font("Tahoma", Font.BOLD, 13));
		TheoDoiThaoTac.setBackground(Color.BLACK);
		TheoDoiThaoTac.setBounds(373, 33, 381, 25);
		contentPane.add(TheoDoiThaoTac);

        JLabel lblavatar = new JLabel("");
		lblavatar.setHorizontalAlignment(SwingConstants.CENTER);
		lblavatar.setIcon(new ImageIcon(GD_Main_QL.class.getResource("/Imgs/t1 1.png")));
		lblavatar.setBounds(180, -444, 1243, 957);
		contentPane.add(lblavatar);
		
		JLabel lblquanly = new JLabel("NV:");
		lblquanly.setBounds(878, -20, 232, 80);
		lblquanly.setForeground(Color.WHITE);
		lblquanly.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblquanly);
		
		JLabel lbltenql = new JLabel();
		lbltenql.setBounds(833, 6, 232, 80);
		lbltenql.setForeground(Color.WHITE);
		lbltenql.setFont(new Font("Tahoma", Font.BOLD, 16));
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
		
		testbutton.Buttontest btndatphong1 = new testbutton.Buttontest();
        btndatphong1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_DatPhong gddatphong = new GD_DatPhong();
				gddatphong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gddatphong.setVisible(true);
				dispose();
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
				GD_DatPhong gddatphong = new GD_DatPhong();
				gddatphong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gddatphong.setVisible(true);
				dispose();
            }
        });
        btndatphong1.setBorder(null);
        btndatphong1.setText("Đặt Phòng (F1)");
        btndatphong1.setForeground(Color.WHITE);
        btndatphong1.setFont(new Font("Tahoma", Font.BOLD, 18));
        btndatphong1.setBackground(new Color(0,0,0, 150));
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
				
            }
        });
        btnkhachhang.setBorder(null);
        btnkhachhang.setText("Khách Hàng (F5)");
        btnkhachhang.setForeground(Color.WHITE);
        btnkhachhang.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnkhachhang.setBackground(new Color(128, 128, 128, 150));
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
		lblNewLabel_1.setBounds(31, 10, 159, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã Khách Hàng");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(31, 90, 145, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Số Điện Thoại");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(296, 10, 135, 28);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số CMND");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(270, 90, 92, 23);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Địa Chỉ");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(473, 93, 55, 20);
		panel.add(lblNewLabel_1_4);
		
		txtten = new JTextField();
		txtten.setBounds(31, 37, 202, 27);
		panel.add(txtten);
		txtten.setColumns(10);
		
		//rad button
		rdbtnNAM = new JRadioButton("Nam");
		rdbtnNAM.setBounds(520, 37, 109, 23);
		rdbtnNAM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNAM.setOpaque(false);
		rdbtnNAM.setContentAreaFilled(false);
		rdbtnNAM.setFocusPainted(false);
		panel.add(rdbtnNAM);
	
		
		rdbtnNU = new JRadioButton("Nữ");
		rdbtnNU.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNU.setBounds(656, 38, 109, 23);
		rdbtnNU.setOpaque(false);
		panel.add(rdbtnNU);
		
		bg = new ButtonGroup();
		bg.add(rdbtnNAM);
		bg.add(rdbtnNU);
		panel.add(rdbtnNAM);
		panel.add(rdbtnNU);
		//radbuton//
		
		txtmakh = new JTextField();
		txtmakh.setEditable(false);
		txtmakh.setColumns(10);
		txtmakh.setBounds(31, 123, 202, 27);
		panel.add(txtmakh);
		
		txtsdt = new JTextField();
		txtsdt.setColumns(10);
		txtsdt.setBounds(296, 37, 164, 27);
		panel.add(txtsdt);
		
		txtcmnd = new JTextField();
		txtcmnd.setColumns(10);
		txtcmnd.setBounds(270, 123, 164, 27);
		panel.add(txtcmnd);
		
		txtdc = new JTextField();
		txtdc.setColumns(10);
		txtdc.setBounds(473, 123, 145, 27);
		panel.add(txtdc);
		
		//button them
		testbutton.Buttontest btnthem = new testbutton.Buttontest();
		btnthem.setText("Thêm");
		btnthem.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnthem.setForeground(SystemColor.text);
		btnthem.setBackground(new Color(90, 167, 167));
		btnthem.setRippleColor(new Color(255, 255, 255));
		btnthem.setShadowColor(new Color(0,0,0));
		btnthem.setBounds(822, 24, 128, 48);
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				  
				  String hovaten = txtten.getText();
				  String phone = txtsdt.getText();
				  String cmnd = txtcmnd.getText();
				  String diachi = txtdc.getText();
				  
				  System.out.println();
				  System.out.println();
				  if (hovaten.isEmpty() && phone.isEmpty() && cmnd.isEmpty() && diachi.isEmpty()) {
					  JOptionPane.showMessageDialog(null,"Dữ liệu nhập vào không được trống");
				  }
				  else if(hovaten.matches( "^[A-Z][\\p{L}']*([\\p{Zs}\\-'][A-Z][\\p{L}']*)*$") == false){
					  JOptionPane.showMessageDialog(null,"Họ và tên phải có ký tự đầu viết hoa và không chứa ký tự đặc biệt");
				  }
				  else if (phone.matches("^09\\d{8}$") == false) {
					    JOptionPane.showMessageDialog(null, "SĐT bắt đầu bằng 09, có 10 chữ số và không có ký tự đặc biệt.");
				  }
				  else if (cmnd.matches("^[0-9]{9}$") == false) {
					  JOptionPane.showMessageDialog(null, "Số CMND phải 9 chữ số và không có ký tự đặc biệt");
				  }
				  else if (diachi.matches("^[\\p{L}0-9,\\s.'-]+$") == false) {
					  JOptionPane.showMessageDialog(null, "Cho phép các ký tự phổ biến trong địa chỉ như dấu phẩy, khoảng trắng, dấu chấm, dấu nháy đơn, dấu gạch ngang và số");
				  }
				  else if (rdbtnNAM.isSelected() == false && rdbtnNU.isSelected() == false) {
					  JOptionPane.showMessageDialog(null, "Giới tính không được để trống");
				  }
				  else {
					  btnthemActionPerformed(e);
					  
						GD_TinhTien tt = new GD_TinhTien();
						tt.khuyenMai("5%");
				  }
			}
		});
		panel.add(btnthem);
		
		
		//button xoa
		testbutton.Buttontest btnxoa = new testbutton.Buttontest();
		btnxoa.setText("Xóa");
		btnxoa.setForeground(SystemColor.text);
		btnxoa.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnxoa.setBackground(new Color(254, 122, 21));
		btnxoa.setBounds(822, 109, 128, 48);
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
		btnsua.setBounds(1009, 24, 128, 48);
		btnsua.setRippleColor(new Color(255, 255, 255));
		btnsua.setShadowColor(new Color(0,0,0));
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsuaActionPerformed(e);
			}
		});
		panel.add(btnsua);
		
		//button lammoi
		testbutton.Buttontest btnlammoi = new testbutton.Buttontest();
	    btnlammoi.setText("Làm mới");
		btnlammoi.setForeground(SystemColor.text);
		btnlammoi.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnlammoi.setBackground(new Color(51, 83, 158));
		btnlammoi.setBounds(1010, 109, 128, 48);
		btnlammoi.setRippleColor(new Color(255, 255, 255));
		btnlammoi.setShadowColor(new Color(0,0,0));
		btnlammoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLmMiActionPerformed(e);
			}
		});
		panel.add(btnlammoi);
		
		txtTim = new JTextField();
		txtTim.setColumns(10);
		txtTim.setBounds(692, 123, 120, 27);
		panel.add(txtTim);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Tìm Kiếm");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4_1.setBounds(693, 91, 90, 20);
		panel.add(lblNewLabel_1_4_1);
		
		JButton btntimkiem = new JButton("");
		btntimkiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timKiemTheoMaTaiKhoan();
			}
		});
		btntimkiem.setIcon(new ImageIcon(GD_QuanLyKhachHang.class.getResource("/Imgs/search.png")));
		btntimkiem.setBounds(638, 123, 55, 27);
		panel.add(btntimkiem);
		
		
		//talbe
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtmakh.setText(model.getValueAt(row, 0).toString());
				txtten.setText(model.getValueAt(row, 2).toString());
				txtsdt.setText(model.getValueAt(row, 3).toString());
				txtcmnd.setText(model.getValueAt(row, 4).toString());
				if(model.getValueAt(row, 1).equals("Nam")) {
					rdbtnNAM.setSelected(true);
				}
				else {
					rdbtnNU.setSelected(true);
				}

				txtdc.setText(model.getValueAt(row, 5).toString());
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 323, 1161, 290);
		contentPane.add(scrollPane);
		
		model = new DefaultTableModel();
		model.addColumn("Mã KH");
		model.addColumn("Giới tính");
		model.addColumn("Họ Tên");
		model.addColumn("Số Điện Thoại");
		model.addColumn("Số CMND");
		model.addColumn("Địa chỉ");
		// Add data to the table

		table.setModel(model);
		
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 500);
		scrollPane.setRowHeaderView(scrollBar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1161, 613);
		contentPane.add(lblNewLabel);
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setIcon(new ImageIcon(GD_QuanLyKhachHang.class.getResource("/Imgs/370.png")));
		
		
		
		
		
		connectDB.getInstance().connect();
		updateTableData();


	}
	
	
	protected void btnLmMiActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		lammoi();
	}

	protected void btnsuaActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		
		if(row >= 0) {
			KhachHang kh = reverSPFromTextField();
			if(dskh.update(kh)) {
				String gt = "";
				if(rdbtnNAM.isSelected()) {
					gt = "Nam";
				}
				if(rdbtnNU.isSelected()) {
					gt = "Nu";
				}
				table.setValueAt(gt, row, 1);
				table.setValueAt(txtten.getText(), row, 2);
				table.setValueAt(txtsdt.getText(), row, 3);
				table.setValueAt(txtcmnd.getText(), row, 4);
				table.setValueAt(txtdc.getText(), row, 5);
				JOptionPane.showMessageDialog(this, "Sửa thông tin khách hàng thành công!");
				theoDoiThaoTac("Bạn vừa mới sửa thông tin khách hàng: ", kh.getMaKH());
//				table.setModel(model);
			}
		}
		
//		updateTableData();
	}

	protected void btnxoaActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String makh = "";
		int row = table.getSelectedRow();
		if(row >= 0) {
			makh = (String) table.getValueAt(row, 0);
			if(dskh.delete(makh)) {
				model.removeRow(row);
				lammoi();
			}
		}
		JOptionPane.showMessageDialog(this, "Xóa Khách Hàng Thành Công");
		theoDoiThaoTac("Bạn vừa mới xóa khách hàng: ",makh);

	}

	protected void btnthemActionPerformed(ActionEvent e) {
		    int maxMaKH = dskh.getMaxMaKH();
		    
		    // Tăng mã kh lên 1 để có mã mới
		    maxMaKH++;
		    
		    // Gán giá trị mới cho ô nhập liệu mã kh
		    txtmakh.setText("KHAA" + String.format("%03d", maxMaKH));
			KhachHang kh = reverSPFromTextField();
			String gt = "";
			if(dskh.create(kh)) {
				if(rdbtnNAM.isSelected()) {
					gt = "Nam";
				}
				if(rdbtnNU.isSelected()) {
					gt = "Nu";
				}
				Object [] rowData = {txtmakh.getText(), gt, txtten.getText(), txtsdt.getText(), txtcmnd.getText(), txtdc.getText()};
				model.addRow(rowData);
				JOptionPane.showMessageDialog(this, "Thêm Khách Hàng Thành Công");
				theoDoiThaoTac("Bạn vừa mới thêm khách hàng: ",txtmakh.getText());
				lammoi();
			}
//			table.setModel(model);
//			updateTableData();
			loadTable();
		
	}
	
	private void timKiemTheoMaTaiKhoan() {
	    String maTKCanTim = txtTim.getText();
	    ArrayList<KhachHang> ketQuaTimKiem = dskh.timkh(maTKCanTim);

	    // Xóa dữ liệu hiện tại trong bảng
	    model.setRowCount(0);

	    // Hiển thị kết quả tìm kiếm trong bảng
	    if (ketQuaTimKiem.isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "Không tìm thấy Khách Hàng");
	    } else {
	        for (KhachHang n : ketQuaTimKiem) {
	        	
	            Object[] rowData = {n.getMaKH(), n.getGioiTinh(), n.getTenKH(), n.getSDT(), n.getCMND(), n.getDiaChi()};
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
         GD_Main_NV mainnv=new GD_Main_NV();
         mainnv.setVisible(true);
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
	
	private KhachHang reverSPFromTextField() {
		String makh = txtmakh.getText().toString();
		String ten = txtten.getText().toString();
		String sdt = txtsdt.getText().toString();
		String cccd = txtcmnd.getText().toString();
		String dch = txtdc.getText().toString();

		String gt = "";
		if(rdbtnNAM.isSelected()) {
			gt = "Nam";
		}
		if(rdbtnNU.isSelected()) {
			gt = "Nu";
		}
		return new KhachHang(makh, gt, ten, sdt, cccd, dch);
		
	}
	
	private void updateTableData() {
		QLKH_DAO ds = new QLKH_DAO();
		ArrayList<KhachHang> ls = ds.doctubang();
		
		for(KhachHang s : ls) {
			
			String [] rowData = {s.getMaKH(), s.getGioiTinh(), s.getTenKH(), s.getSDT()+"", s.getCMND()+"", s.getDiaChi()};

			model.addRow(rowData);
			table.setModel(model);
		}
		
	}
	public void loadTable() {
		QLKH_DAO dskh = new QLKH_DAO();
		model.setRowCount(0);
			for(KhachHang s : dskh.doctubang()) {
			
			Object  rowData[] = {s.getMaKH(), s.getGioiTinh(), s.getTenKH(), s.getSDT()+"", s.getCMND()+"", s.getDiaChi()};

			model.addRow(rowData);

		}
	}
	
	private void theoDoiThaoTac(String thaotac,String dulieu) {
		   TheoDoiThaoTac.setText(thaotac +" "+ dulieu);
	}
	
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtmakh.setText(table.getValueAt(row, 0).toString());
		if(table.getValueAt(row, 1).equals("Nam")) {
			rdbtnNAM.setSelected(true);
		}
		else {
			rdbtnNU.setSelected(true);
		}
		txtten.setText(table.getValueAt(row, 2).toString());
		txtsdt.setText(table.getValueAt(row, 3).toString());
		txtcmnd.setText(table.getValueAt(row, 4).toString());
		txtdc.setText(table.getValueAt(row, 5).toString());
	}
	public void lammoi() {
		txtcmnd.setText("");
		txtten.setText("");
		txtmakh.setText("");
		txtsdt.setText("");
		txtdc.setText("");
		bg.clearSelection();
		txtmakh.requestFocus();
		loadTable();
	}
}
