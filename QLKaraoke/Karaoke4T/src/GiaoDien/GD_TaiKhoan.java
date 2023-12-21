package GiaoDien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import java.awt.Graphics;
import java.awt.SystemColor;

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
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DAO.QLTK_DAO;
import Entity.*;
import connectDB.connectDB;
 

public class GD_TaiKhoan extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaTK, txtmk, txtTaiKhoan, txtTenNV, txt_email;
	private JTable table;
	DefaultTableModel model;
	@SuppressWarnings("unused")
	private JLabel lblClock, lbltenql,TheoDoiThaoTac;
	private Timer timer;
	private testbutton.Buttontest btnthem, btnxoa, btnlammoi, btnsua;
	QLTK_DAO dstk = new QLTK_DAO();
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	String quanly;
	private JTextField txtTimKiem;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public GD_TaiKhoan() {
		initComponents();
		setResizable(false);
		String tenuser = layThongTinTen();
		try {
			connectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		QLTK_DAO dstk = new QLTK_DAO();
		
		setBackground(Color.WHITE);
		setTitle("Giao Diện Tài Khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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
		
		
//		------------------------------------------
		
		TheoDoiThaoTac = new JLabel("Theo dõi thao tác");
		TheoDoiThaoTac.setIcon(new ImageIcon(GD_TaiKhoan.class.getResource("/Imgs/eyes.png")));
		TheoDoiThaoTac.setForeground(Color.WHITE);
		TheoDoiThaoTac.setFont(new Font("Tahoma", Font.BOLD, 13));
		TheoDoiThaoTac.setBackground(Color.BLACK);
		TheoDoiThaoTac.setBounds(365, 29, 381, 25);
		contentPane.add(TheoDoiThaoTac);
		
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
			}
		});
        btntaikhoan.setBorder(null);
        btntaikhoan.setText("Tài Khoản (F4)");
        btntaikhoan.setForeground(Color.WHITE);
        btntaikhoan.setFont(new Font("Tahoma", Font.BOLD, 20));
        btntaikhoan.setBackground(new Color(128, 128, 128, 150));
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
		
		//panel bg mờ
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panel.setBounds(-2, 151, 1173, 172);
		panel.setBackground(new Color(255, 255, 255,180));
		panel.setOpaque(false);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JButton btntimkiem = new JButton("");
		btntimkiem.setIcon(new ImageIcon(GD_TaiKhoan.class.getResource("/Imgs/search.png")));
		btntimkiem.setBounds(554, 123, 55, 27);
		btntimkiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        // Kiểm tra xem ô txtTimKiem có rỗng hay không
		        if (txtTimKiem.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã tài khoản vào ô tìm kiếm!");
		        } else {
		            // Nếu không rỗng, thực hiện tìm kiếm
		            timKiemTheoMaTaiKhoan();
		        }
		    }

			private void timKiemTheoMaTaiKhoan() {
			    String maTKCanTim = txtTimKiem.getText();
			    ArrayList<TaiKhoanNhanVien> ketQuaTimKiem = dstk.timKiemTheoMaTK(maTKCanTim);

			    // Xóa dữ liệu hiện tại trong bảng
			    model.setRowCount(0);

			    // Hiển thị kết quả tìm kiếm trong bảng
			    if (ketQuaTimKiem.isEmpty()) {
			        thongbao();
			    } else {
			        for (TaiKhoanNhanVien tk : ketQuaTimKiem) {
			            Object[] rowData = {tk.getMaTaiKhoan(), tk.getTenTaiKhoan(), tk.getMatKhau(), tk.getTenNV(), tk.getEmail()};
			            model.addRow(rowData);
			        }
			    }
			}
		});
        panel.add(btntimkiem);
  
		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(619, 123, 177, 27);
		panel.add(txtTimKiem);

		JLabel lblhoten = new JLabel("Mã Tài Khoản");
		lblhoten.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblhoten.setBounds(31, 10, 159, 28);
		panel.add(lblhoten);
		
		txtMaTK = new JTextField();
		txtMaTK.setEditable(false);
		txtMaTK.setBounds(31, 37, 202, 27);
		panel.add(txtMaTK);
		txtMaTK.setColumns(10);

		JLabel lblmakh = new JLabel("Tên Nhân Viên");
		lblmakh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmakh.setBounds(31, 90, 145, 23);
		panel.add(lblmakh);
		txtTenNV = new JTextField();
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(31, 123, 202, 27);
		panel.add(txtTenNV);
		
		JLabel lbldiachi = new JLabel("Mật Khẩu");
		lbldiachi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldiachi.setBounds(281, 91, 145, 20);
		panel.add(lbldiachi);
		txtmk = new JTextField();
		txtmk.setColumns(10);
		txtmk.setBounds(281, 123, 241, 27);
		panel.add(txtmk);
		
		JLabel lbltentk = new JLabel("Tên Tài Khoản");
		lbltentk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbltentk.setBounds(287, 10, 159, 28);
		panel.add(lbltentk);
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setBounds(281, 37, 241, 27);
		panel.add(txtTaiKhoan);
		
		//button them
				btnthem = new testbutton.Buttontest();
				btnthem.setText("Thêm");
				btnthem.setFont(new Font("Segoe UI", Font.BOLD, 16));
				btnthem.setForeground(new Color(245,245,245));
				btnthem.setBackground(new Color(90, 167, 167));
				btnthem.setRippleColor(new Color(255, 255, 255));
				btnthem.setShadowColor(new Color(0,0,0));
				btnthem.setBounds(822, 24, 128, 48);
				btnthem.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	btnthemActionPerformed(e);
				    }
					
				});
				panel.add(btnthem);
				
				//button xoa
				btnxoa = new testbutton.Buttontest();
				btnxoa.setText("Xóa");
				btnxoa.setForeground(SystemColor.text);
				btnxoa.setFont(new Font("Segoe UI", Font.BOLD, 16));
				btnxoa.setBackground(new Color(254, 122, 21));
				btnxoa.setRippleColor(new Color(255, 255, 255));
				btnxoa.setShadowColor(new Color(0,0,0));
				btnxoa.setBounds(822, 109, 128, 48);
				
				btnxoa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnxoaActionPerformed(e);
					}
				});
				panel.add(btnxoa);
				
				//button sua
				btnsua = new testbutton.Buttontest();
				btnsua.setText("Sửa");
				btnsua.setForeground(SystemColor.text);
				btnsua.setFont(new Font("Segoe UI", Font.BOLD, 16));
				btnsua.setBackground(new Color(226, 211, 107));
				btnsua.setRippleColor(new Color(255, 255, 255));
				btnsua.setShadowColor(new Color(0,0,0));
				btnsua.setBounds(1009, 24, 128, 48);

				btnsua.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnsuaActionPerformed(e);
					}
				});
				panel.add(btnsua);
				
				//button lammoi
				btnlammoi = new testbutton.Buttontest();
				btnlammoi.setText("Làm mới");
				btnlammoi.setForeground(SystemColor.text);
				btnlammoi.setFont(new Font("Segoe UI", Font.BOLD, 16));
				btnlammoi.setBackground(new Color(51, 83, 158));
				btnlammoi.setRippleColor(new Color(255, 255, 255));
				btnlammoi.setShadowColor(new Color(0,0,0));
				btnlammoi.setBounds(1009, 109, 128, 48);
				btnlammoi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnlammoiActionPerformed(e);
					}
				});
				panel.add(btnlammoi);
				
				JLabel TxtGmail = new JLabel("Email");
				TxtGmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
				TxtGmail.setBounds(554, 10, 159, 28);
				panel.add(TxtGmail);
				
				txt_email = new JTextField();
				txt_email.setColumns(10);
				txt_email.setBounds(554, 37, 241, 27);
				panel.add(txt_email);
				
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtMaTK.setText(model.getValueAt(row, 0).toString());
				txtTaiKhoan.setText(model.getValueAt(row, 1).toString());
				txtmk.setText(model.getValueAt(row, 2).toString());
				txtTenNV.setText(model.getValueAt(row, 3).toString());
				txt_email.setText(model.getValueAt(row, 4).toString());
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 323, 1161, 290); // Adjust the position and size as needed
		contentPane.add(scrollPane);
		
	    model = new DefaultTableModel();
		model.addColumn("Mã tài khoản");
		model.addColumn("Tên tài khoản");
		model.addColumn("Mật khẩu");
		model.addColumn("Tên nhân viên");
		model.addColumn("Email");
		table.setModel(model);
		
		JLabel lblquanly = new JLabel("QL:");
		lblquanly.setForeground(Color.WHITE);
		lblquanly.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblquanly.setBounds(878, -20, 232, 80);
		contentPane.add(lblquanly);
		
		JLabel lbltenql = new JLabel();
		lbltenql.setForeground(Color.WHITE);
		lbltenql.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltenql.setText(UserInfo.getTenNhanVien());
		lbltenql.setBounds(853, 6, 232, 80);

		lbltenql = new JLabel();
		lbltenql.setForeground(Color.WHITE);
		lbltenql.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltenql.setBounds(853, 6, 232, 80);
		lbltenql.setText(tenuser);
		contentPane.add(lbltenql);
		
		JLabel lblavatar = new JLabel("");
		lblavatar.setHorizontalAlignment(SwingConstants.CENTER);
		lblavatar.setIcon(new ImageIcon(GD_TaiKhoan.class.getResource("/Imgs/t1 1.png")));
		lblavatar.setBounds(90, -444, 1333, 957);
		contentPane.add(lblavatar);
	
		JLabel lblhinhnen = new JLabel("");
		lblhinhnen.setHorizontalAlignment(SwingConstants.CENTER);
		lblhinhnen.setIcon(new ImageIcon(GD_TaiKhoan.class.getResource("/Imgs/370.png")));
		lblhinhnen.setBounds(-95, -176, 1333, 957);
		contentPane.add(lblhinhnen);
	
		connectDB.getInstance().connect();
		updateTableData();
		
	}
	protected void btnlammoiActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		lammoi();		
	}
	protected void btnsuaActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		
		if(row >= 0) {
			TaiKhoanNhanVien tk = reverSPFromTextField();
			if(dstk.update(tk)) {
//				table.setValueAt(txtMaTK.getText(), row, 1);
				table.setValueAt(txtTaiKhoan.getText(), row, 1);
				table.setValueAt(txtmk.getText(), row, 2);
				table.setValueAt(txtTenNV.getText(), row, 3);
				table.setValueAt(txt_email.getText(), row, 4);
				JOptionPane.showMessageDialog(this, "Sửa thông tin tài khoản nhân viên thành công!");
				theoDoiThaoTac("Bạn vừa sửa tài khoản: ", tk.getMaTaiKhoan());
				table.setModel(model);
			}
		}
		loadTable();
	}
	protected void btnxoaActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String matk = "";
		int row = table.getSelectedRow();
		if(row >= 0) {
			matk = (String) table.getValueAt(row, 0);
			if(dstk.delete(matk)) {
				model.removeRow(row);
				lammoi();
			}
		}
		JOptionPane.showMessageDialog(this, "Xóa Tài Khoản Thành Công");
		theoDoiThaoTac("Bạn vừa xóa tài khoản: ", matk);

	}
	private void btnthemActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 // Check if the name is empty
	    String tennv = txtTenNV.getText().trim();
	    if (tennv.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the method execution if the name is empty
	    }

	    // Check if the name follows the specified format (uppercase, spaces, no special characters or numbers)
	    if (!tennv.matches("[A-Z\\s]+") || tennv.matches(".*[!@#$%^&*()_+={}:;\"\"<>?,./\\-\\d].*")) {
	        JOptionPane.showMessageDialog(this, "Tên nhân viên phải viết hoa có dấu cách và không chứa ký tự đặc biệt, số", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the method execution if the name format is incorrect
	    }

	    // Check if the email is in the correct format
	    String txtemail = txt_email.getText().trim();
	    if (txtemail.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Email không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the method execution if the name is empty
	    }
	    
	    if (!txtemail.matches(".+@gmail\\.com")) {
	        JOptionPane.showMessageDialog(this, "Email phải có định dạng @gmail.com", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the method execution if the email format is incorrect
	    }

	    String tentaikhoan = txtTaiKhoan.getText().trim();
	    if (tentaikhoan.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Tên tài khoản không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the method execution if the username is empty
	    }

	    // Check if the username contains spaces
	    if (tentaikhoan.contains(" ")) {
	        JOptionPane.showMessageDialog(this, "Tên tài khoản không được chứa khoảng trắng", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the method execution if the username contains spaces
	    }
	    
	    String txtpassword = txtmk.getText().trim();
	    if (txtpassword.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the method execution if the username is empty
	    }

	    // Check if the password meets the constraints
	    if (!isValidPassword(txtpassword)) {
	        JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 8 ký tự, ký tự đầu tiên viết hoa và có chứa ít nhất một số", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the method execution if the password does not meet the constraints
	    }

	    int maxMaTK = dstk.getMaxMaTaiKhoan();
	    
	    // Tăng mã tài khoản lên 1 để có mã mới
	    maxMaTK++;
	    
	    // Gán giá trị mới cho ô nhập liệu mã tài khoản
	    txtMaTK.setText("TK" + String.format("%03d", maxMaTK));
	    
		TaiKhoanNhanVien tk = reverSPFromTextField();
		if(dstk.create(tk)) {
			Object [] rowData = {txtMaTK.getText(), txtTaiKhoan.getText(), txtmk.getText(), txtTenNV.getText(), txt_email.getText()};
			model.addRow(rowData);
			JOptionPane.showMessageDialog(this, "Thêm Tài Khoản Thành Công");
			theoDoiThaoTac("Bạn vừa mới thêm tài khoản: ", txtMaTK.getText());
			lammoi();
		}
		loadTable();
	}
	private boolean isValidPassword(String txtpassword) {
		 // Minimum length of 8 characters
	    if (txtpassword.length() < 8) {
	        return false;
	    }

	    // First character must be uppercase
	    if (!Character.isUpperCase(txtpassword.charAt(0))) {
	        return false;
	    }

	    // Password must contain at least one digit
	    if (!txtpassword.matches(".*\\d.*")) {
	        return false;
	    }

	    // All constraints are met
	    return true;
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
    
    private TaiKhoanNhanVien reverSPFromTextField() {
		String matk = txtMaTK.getText().toString();
		String tentk = txtTaiKhoan.getText().toString();
		String mk = txtmk.getText().toString();
		String tennv = txtTenNV.getText().toString();
		String email = txt_email.getText().toString();
		return new TaiKhoanNhanVien(matk, tentk, mk, tennv,email);
	}
//    
    private void updateTableData() {
		QLTK_DAO ds = new QLTK_DAO();
		ArrayList<TaiKhoanNhanVien> ls = ds.doctubang();
		for(TaiKhoanNhanVien s : ls) {
			String [] rowData = {s.getMaTaiKhoan(),s.getTenTaiKhoan(),s.getMatKhau(), s.getTenNV(), s.getEmail()};
			model.addRow(rowData);
			table.setModel(model);
		}
	}
//    
    public void loadTable() {
    	QLTK_DAO dstk = new QLTK_DAO();
		model.setRowCount(0);
			for(TaiKhoanNhanVien s : dstk.doctubang()) {
			
			Object  rowData[] = {s.getMaTaiKhoan(),s.getTenTaiKhoan(),s.getMatKhau(), s.getTenNV(), s.getEmail()};

			model.addRow(rowData);

		}
	}
    
    public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaTK.setText(table.getValueAt(row, 1).toString());
		txtTaiKhoan.setText(table.getValueAt(row,2).toString());
		txtTenNV.setText(table.getValueAt(row, 3).toString());
		txtmk.setText(table.getValueAt(row, 4).toString());
		txt_email.setText(table.getValueAt(row, 5).toString());
	}
	public void lammoi() {
		txtMaTK.setText("");
		txtmk.setText("");
		txtTaiKhoan.setText("");
		txtTenNV.setText("");
		txt_email.setText("");
		txtMaTK.requestFocus();
	}
	
	 public void thongbao() {
		 JOptionPane.showMessageDialog(null, "Không tìm thấy mã tài khoản");
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
	 
	private void theoDoiThaoTac(String thaotac,String dulieu) {
		TheoDoiThaoTac.setText(thaotac +" "+ dulieu);
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
}
