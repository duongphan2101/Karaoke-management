package GiaoDien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;


import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.border.LineBorder;


public class GD_Main_QL extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblClock, lbltenql;
	private Timer timer;
	@SuppressWarnings("unused")
	private JButton jButton;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	String quanly;
	


	public GD_Main_QL() {
		initComponents();
		setResizable(false);
		String tenuser = layThongTinTen();
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1175, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
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
		JLabel lblavatar = new JLabel("");
		lblavatar.setHorizontalAlignment(SwingConstants.CENTER);
		lblavatar.setIcon(new ImageIcon(GD_Main_QL.class.getResource("/Imgs/t1 1.png")));
		lblavatar.setBounds(90, -444, 1333, 957);
		contentPane.add(lblavatar);
		
		JLabel lblquanly = new JLabel("QL:");
		lblquanly.setForeground(Color.WHITE);
		lblquanly.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblquanly.setBounds(878, -20, 232, 80);
		contentPane.add(lblquanly);
		
		lbltenql = new JLabel();
		lbltenql.setForeground(Color.WHITE);
		lbltenql.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltenql.setBounds(853, 6, 252, 80);
		lbltenql.setText(tenuser);
		contentPane.add(lbltenql);

		JButton jButton_1 = new JButton("Đăng Xuất");
		jButton_1.setBounds(990, 10, 150, 50);
		jButton_1.setFont(new Font("Tahoma ", Font.BOLD, 14));
		jButton_1.setBackground(new Color(255, 0, 0));
		jButton_1.setForeground(Color.WHITE);
		jButton_1.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
		jButton_1.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
		jButton_1.setContentAreaFilled(false);
		jButton_1.setFocusPainted(false);
		jButton_1.setOpaque(true);
		contentPane.add(jButton_1);
		jButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jButton_1.setBackground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				jButton_1.setBackground(new Color(255, 0, 0));
			}
		});
		jButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất!", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					GD_Login lg = new GD_Login();
					lg.setVisible(true);
					lg.setLocationRelativeTo(null);
					dispose();
					}
				}
		});

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
				GD_QLNhanVien gdnv = new GD_QLNhanVien();
				gdnv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdnv.setVisible(true);
				dispose();
			}
		});
        KeyStroke keyStrokeNV = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0);
        btnnhanvien.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeNV, "F3");
        btnnhanvien.getActionMap().put("F3", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_QLNhanVien gdnv = new GD_QLNhanVien();
				gdnv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdnv.setVisible(true);
				dispose();
            }
        });
        btnnhanvien.setBorder(null);
        btnnhanvien.setText("Nhân Viên (F3)");
        btnnhanvien.setForeground(Color.WHITE);
        btnnhanvien.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnnhanvien.setBackground(new Color(0, 0, 0, 150));
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(GD_Main_QL.class.getResource("/Imgs/370.png")));
		lblNewLabel.setBounds(-95, -176, 1333, 957);
		contentPane.add(lblNewLabel);
		
	}
	
	private String layThongTinTen() {
	    String query = "SELECT tenNV FROM TaiKhoan WHERE maTK = ?";
	    try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=Karaoke4T;user=sa;password=123");
	        pst = con.prepareStatement(query);
	        pst.setString(1, "TK001"); // Điền điều kiện truy vấn của bạn
	        rs = pst.executeQuery();
	        if (rs.next()) {
	            return rs.getString("tenNV");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
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
         GD_Login login=new GD_Login();
         login.setVisible(true);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
            // Cập nhật thời gian
            updateClock();
        }
	}
	//dong ho
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
        
        String time = String.format("%02d:%02d:%02d %s  %02d/%02d/%04d", hour, minute, second, ampm, day, month, year);
        lblClock.setText(time);
    }
}

