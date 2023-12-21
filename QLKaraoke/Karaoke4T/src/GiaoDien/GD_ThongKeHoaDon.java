package GiaoDien;

import java.awt.BorderLayout;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import DAO.ThongKeKhachHang_DAO;

import connectDB.connectDB;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComponent;
import javax.swing.ScrollPaneConstants;

public class GD_ThongKeHoaDon extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblClock, lbltenql;
	private Timer timer;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	String quanly;
	Double tongTien;
	private JDateChooser dateChooser_start, dateChooser_end;
	@SuppressWarnings("unused")
	private ButtonGroup bg = new ButtonGroup();
	ThongKeKhachHang_DAO dstk = new ThongKeKhachHang_DAO();
	@SuppressWarnings("unused")
	private JButton btnThongKe = new JButton();
	private JPanel panel_plot;
	private JLabel lbl_tdt, lbl_tkhach, lbl_dvit, lbl_dvnhieu, lbl_pit, lbl_pnhieu, lbl_hd;
	private ChartPanel chartPanel;
	private JTable table = new JTable();
	private DefaultTableModel model = (DefaultTableModel) table.getModel();
	@SuppressWarnings("unused")
	private JTable table_1;
	@SuppressWarnings("unused")
	private JFreeChart chart;
	DecimalFormat currencyFormat = new DecimalFormat("###,###,### VND");
//	private JComboBox comboBoxThang;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GD_ThongKeHoaDon() {
		initComponents();
		setResizable(false);
		String tenuser = layThongTinTen();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1175, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Thống Kê Hóa Đơn");
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		 Ho tro -----------------------
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://thach1311.github.io/huongDan/").toURI());
				} catch (Exception ex) {
				}
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

		lbltenql = new JLabel();
		lbltenql.setForeground(Color.WHITE);
		lbltenql.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltenql.setBounds(853, 6, 232, 80);
		lbltenql.setText(tenuser);
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

		//
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

			}
		});
		btnthongke.setBorder(null);
		btnthongke.setText("Thống Kê (F5)");
		btnthongke.setForeground(Color.WHITE);
		btnthongke.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnthongke.setBackground(new Color(128, 128, 128, 150));
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
		panel.setBounds(-2, 151, 393, 462);
		panel.setBackground(new Color(255, 255, 255, 200));
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lbl_ngaybatdau = new JLabel("Ngày Bắt Đầu");
		lbl_ngaybatdau.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_ngaybatdau.setBounds(10, 10, 110, 21);
		panel.add(lbl_ngaybatdau);

		ButtonGroup bg = new ButtonGroup();

		JButton btnThongKe = new JButton("Thống kê");
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setBackground(new Color(194, 100, 154));
		btnThongKe.setBounds(117, 410, 142, 42);
		panel.add(btnThongKe);

		dateChooser_start = new JDateChooser();
		dateChooser_start.setDate(new Date());
		dateChooser_start.setBounds(10, 31, 140, 33);
		panel.add(dateChooser_start);

		dateChooser_end = new JDateChooser();
		dateChooser_end.setDate(new Date());
		dateChooser_end.setBounds(243, 31, 140, 33);
		panel.add(dateChooser_end);

		JLabel lbl_ngayketthuc = new JLabel("Ngày Kết Thúc");
		lbl_ngayketthuc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_ngayketthuc.setBounds(243, 10, 110, 21);
		panel.add(lbl_ngayketthuc);

		lbl_pnhieu = new JLabel("Phòng được hát nhiều nhất : ");
		lbl_pnhieu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_pnhieu.setBounds(10, 74, 373, 33);
		panel.add(lbl_pnhieu);

		lbl_pit = new JLabel("Phòng được hát ít nhất : ");
		lbl_pit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_pit.setBounds(10, 104, 373, 33);
		panel.add(lbl_pit);

		lbl_dvnhieu = new JLabel("Dịch vụ được gọi nhiều nhất : ");
		lbl_dvnhieu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dvnhieu.setBounds(10, 134, 373, 33);
		panel.add(lbl_dvnhieu);

		lbl_dvit = new JLabel("Dịch vụ được gọi ít nhất : ");
		lbl_dvit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dvit.setBounds(10, 169, 373, 33);
		panel.add(lbl_dvit);

		lbl_tkhach = new JLabel("Tổng số lượng khách hàng: ");
		lbl_tkhach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_tkhach.setBounds(10, 204, 373, 33);
		panel.add(lbl_tkhach);

		lbl_tdt = new JLabel("Tổng Doanh Thu: ");
		lbl_tdt.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_tdt.setBounds(10, 274, 363, 33);
		panel.add(lbl_tdt);

		lbl_hd = new JLabel("Tổng số lượng Hóa Đơn: ");
		lbl_hd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_hd.setBounds(10, 239, 373, 33);
		panel.add(lbl_hd);
		
		JLabel lbl_cb_HD = new JLabel("Hóa Đơn");
		lbl_cb_HD.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cb_HD.setBounds(10, 328, 64, 13);
		panel.add(lbl_cb_HD);
		
		JLabel lbl_cb_dv = new JLabel("Dịch Vụ");
		lbl_cb_dv.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cb_dv.setBounds(80, 328, 62, 13);
		panel.add(lbl_cb_dv);
		
		JLabel lbl_cb_p = new JLabel("Phòng");
		lbl_cb_p.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cb_p.setBounds(152, 328, 62, 13);
		panel.add(lbl_cb_p);
		
		JLabel lbl_cb_dt = new JLabel("Doanh Thu");
		lbl_cb_dt.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cb_dt.setBounds(224, 328, 62, 13);
		panel.add(lbl_cb_dt);
		
		JRadioButton rd_kh = new JRadioButton("");
		rd_kh.setHorizontalAlignment(SwingConstants.CENTER);
		rd_kh.setBounds(20, 335, 30, 30);
		panel.add(rd_kh);
		
		JRadioButton rd_dv = new JRadioButton("");
		rd_dv.setHorizontalAlignment(SwingConstants.CENTER);
		rd_dv.setBounds(90, 335, 30, 30);
		panel.add(rd_dv);
		
		JRadioButton rd_p = new JRadioButton("");
		rd_p.setHorizontalAlignment(SwingConstants.CENTER);
		rd_p.setBounds(165, 335, 30, 30);
		panel.add(rd_p);
		
		JRadioButton rd_dt = new JRadioButton("");
		rd_dt.setHorizontalAlignment(SwingConstants.CENTER);
		rd_dt.setBounds(242, 335, 30, 30);
		panel.add(rd_dt);
		
		JLabel lbl_cb_dttk = new JLabel("Khách Hàng");
		lbl_cb_dttk.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cb_dttk.setBounds(274, 328, 119, 13);
		panel.add(lbl_cb_dttk);
		
		JRadioButton rd_dttk = new JRadioButton("");
		rd_dttk.setHorizontalAlignment(SwingConstants.CENTER);
		rd_dttk.setBounds(319, 335, 30, 30);
		panel.add(rd_dttk);

		bg = new ButtonGroup();
		bg.add(rd_dttk);
		bg.add(rd_dt);
		bg.add(rd_p);
		bg.add(rd_dv);
		bg.add(rd_kh);
		panel_plot = new JPanel();
		panel_plot.setBounds(385, 151, 776, 462);
		contentPane.add(panel_plot);
		panel_plot.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 10, 766, 452);
		panel_plot.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		btnThongKe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dstk = new ThongKeKhachHang_DAO();
				Date DateStart = dateChooser_start.getDate();
				Date DateEnd = dateChooser_end.getDate();

				if (DateStart != null && DateEnd != null && DateEnd.after(DateStart)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String startDateString = sdf.format(DateStart);
					String endDateString = sdf.format(DateEnd);
					
					// tkdv
					String[] thongtindichvu = dstk.tkdv(startDateString, endDateString);
					if (thongtindichvu.length > 0) {
						String mostCalledServiceInfo = thongtindichvu[0];
						lbl_dvnhieu.setText("Dịch vụ được gọi nhiều nhất: " + mostCalledServiceInfo);

						String leastCalledServiceInfo = thongtindichvu[1];
						lbl_dvit.setText("Dịch vụ được gọi ít nhất: " + leastCalledServiceInfo);
					} else {
						lbl_dvnhieu.setText("Dịch vụ được gọi nhiều nhất: Không có dữ liệu!");
						lbl_dvit.setText("Dịch vụ được gọi ít nhất: Không có dữ liệu!");
					}
					// tkph
					String[] thongtinphong = dstk.tkp(startDateString, endDateString);
					if (thongtinphong.length <= 0) {
						lbl_pnhieu.setText("Phòng được hát nhiều nhất: Không có dữ liệu!");
						lbl_pit.setText("Phòng được hát ít nhất: Không có dữ liệu!");
						
					} else {
						String mostCalledRoomInfo = thongtinphong[0];
						lbl_pnhieu.setText("Phòng được hát nhiều nhất: " + mostCalledRoomInfo);

						String leastCalledRommInfo = thongtinphong[1];
						lbl_pit.setText("Phòng được hát ít nhất: " + leastCalledRommInfo);
					}
					int slk = dstk.tkkh(startDateString, endDateString);
					if (slk > 0) {
						lbl_tkhach.setText("Tổng số lượng khách hàng: " + slk);
					} else {
						lbl_tkhach.setText("Tổng số lượng khách hàng: Không có dữ liệu!");
					}
					int slhd = dstk.tkhd(startDateString, endDateString);
					if (slhd > 0) {
						lbl_hd.setText("Tổng số lượng Hóa Đơn: " + slhd);
					} else {
						lbl_tkhach.setText("Tổng số lượng Hóa Đơn: Không có dữ liệu!");
					}

					Double dt = dstk.tkdt(startDateString, endDateString);
					if (dt > 0) {
						DecimalFormat df = new DecimalFormat("#,###.##");
						lbl_tdt.setText("Tổng Doanh Thu: " + df.format(dt) + "VND");
					} else {
						lbl_tkhach.setText("Tổng Doanh Thu: Không có dữ liệu!");
					}
					boolean hasData = hasDataBetweenDates(startDateString, endDateString);

					if (!hasData) {
					    JOptionPane.showMessageDialog(null, "Không có dữ liệu từ " + startDateString + " đến " + endDateString);
					}
					else {
						if (rd_kh.isSelected()) {
							createChart_KH(startDateString, endDateString);
							model = new DefaultTableModel();
							loadtb_KH(startDateString, endDateString);
							lbl_pnhieu.setText("Tổng số lượng Hóa Đơn: " + slhd);
							DecimalFormat df = new DecimalFormat("#,###.##");
							lbl_pit.setText("Tổng Doanh Thu: " + df.format(dt) + " VND");
							Double dttb = dstk.dttb(startDateString, endDateString);
							lbl_dvnhieu.setText("Doanh Thu Trung Bình: " + df.format(dttb) + " VND/HoaDon");
							lbl_dvit.setText("");
							lbl_hd.setText("");
							lbl_tkhach.setText("");
							lbl_tdt.setText("");
							
						} else if (rd_dv.isSelected()) {
							createChart_DV(startDateString, endDateString);
							model = new DefaultTableModel();
							loadtb_DV(startDateString, endDateString);
//							String[] thongtindichvu = dstk.tkdv(startDateString, endDateString);
							if (thongtindichvu.length > 0) {
								String mostCalledServiceInfo = thongtindichvu[0];
								lbl_pnhieu.setText("Dịch vụ được gọi nhiều nhất: " + mostCalledServiceInfo);

								String leastCalledServiceInfo = thongtindichvu[1];
								lbl_pit.setText("Dịch vụ được gọi ít nhất: " + leastCalledServiceInfo);
							} else {
								lbl_pnhieu.setText("Dịch vụ được gọi nhiều nhất: Không có dữ liệu!");
								lbl_pit.setText("Dịch vụ được gọi ít nhất: Không có dữ liệu!");
							}
							lbl_dvnhieu.setText("");
							lbl_dvit.setText("");
							lbl_hd.setText("");
							lbl_tkhach.setText("");
							lbl_tdt.setText("");
							
						} else if (rd_p.isSelected()) {
							createChart_P(startDateString, endDateString);
							model = new DefaultTableModel();
							loadtb_P(startDateString, endDateString);
							if (thongtinphong.length <= 0) {
								lbl_pnhieu.setText("Phòng được hát nhiều nhất: Không có dữ liệu!");
								lbl_pit.setText("Phòng được hát ít nhất: Không có dữ liệu!");
								
							} else {
								String mostCalledRoomInfo = thongtinphong[0];
								lbl_pnhieu.setText("Phòng được hát nhiều nhất: " + mostCalledRoomInfo);

								String leastCalledRommInfo = thongtinphong[1];
								lbl_pit.setText("Phòng được hát ít nhất: " + leastCalledRommInfo);
							}
							lbl_dvnhieu.setText("");
							lbl_dvit.setText("");
							lbl_hd.setText("");
							lbl_tkhach.setText("");
							lbl_tdt.setText("");
						
						} else if (rd_dt.isSelected()) {
							createChart_DT(startDateString, endDateString);
							model = new DefaultTableModel();
							loadtb_DT(startDateString, endDateString);
							@SuppressWarnings("static-access")
							String[] dtp = dstk.laydoanhthuphong(startDateString, endDateString);
							String dtpt = null, dtpv = null;
							if(dtp != null) {
								dtpt = dtp [0];
								dtpv = dtp[1];
							}
							lbl_pnhieu.setText("Tổng số lượng Hóa Đơn: " + slhd);
							DecimalFormat df = new DecimalFormat("#,###.##");
							lbl_pit.setText("Tổng Doanh Thu: " + df.format(dt) + " VND");
							if(dtpt != null) {
								lbl_dvnhieu.setText("Doanh Thu Phòng Thường: " + dtpt + " VND");
							}else {
								lbl_dvnhieu.setText("Doanh Thu Phòng Thường: Không có dữ liệu !");
							}
							if(dtpv != null) {
								lbl_dvit.setText("Doanh Thu Phòng VIP: " + dtpv + " VND");
							}else {
								lbl_dvit.setText("Doanh Thu Phòng VIP: Không có dữ liệu !");
							}
							lbl_tkhach.setText("");
							lbl_hd.setText("");
							lbl_tdt.setText("");
						} else if(rd_dttk.isSelected()) {
							createChart_DTTK(startDateString, endDateString);
							model = new DefaultTableModel();
							loadtb_DTTK(startDateString, endDateString);
							lbl_pnhieu.setText("");
							lbl_pit.setText("");
							lbl_dvnhieu.setText("");
							lbl_dvit.setText("");
							lbl_hd.setText("");
							lbl_tkhach.setText("");
							lbl_tdt.setText("");
						}
					}
					
				}

				else {

					JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu!");
				}

			}
		});
		

		JLabel lblavatar = new JLabel("");
		lblavatar.setHorizontalAlignment(SwingConstants.CENTER);
		lblavatar.setIcon(new ImageIcon(GD_ThongKeHoaDon.class.getResource("/Imgs/t1 1.png")));
		lblavatar.setBounds(90, -444, 1333, 957);
		contentPane.add(lblavatar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1161, 613);
		contentPane.add(lblNewLabel);
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setIcon(new ImageIcon(GD_QuanLyKhachHang.class.getResource("/Imgs/370.png")));

		connectDB.getInstance().connect();
//		loadTable();
	}
	
	private void loadtb_DTTK(String ns, String ne) {
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

	        String sql = "SELECT KhachHang.MaKH, KhachHang.TenKH, KhachHang.SDT, SUM(HoaDon.tongTien) AS DoanhThu\r\n"
	        		+ "FROM HoaDon\r\n"
	        		+ "INNER JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH\r\n"
	        		+ "WHERE HoaDon.ngayLap BETWEEN ? AND ?\r\n"
	        		+ "GROUP BY KhachHang.MaKH, KhachHang.TenKH, KhachHang.SDT ORDER BY DoanhThu DESC";

	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, ns);
	        pstmt.setString(2, ne); 
	       
	        ResultSet rs = pstmt.executeQuery();
	        
	    	model.addColumn("Mã Khách Hàng");
	    	model.addColumn("Tên Khách Hàng");
			model.addColumn("SDT");			
			model.addColumn("Doanh Thu");

			model.setRowCount(0);
			table.setModel(model);
	        while (rs.next()) {
	        	String dt = formatCurrency(Double.parseDouble(rs.getString("DoanhThu")));
	            Object[] rowData = {
	                rs.getString("maKH"),
	                rs.getString("tenKH"),
	                rs.getString("SDT"),
	                dt
	            };
	            model.addRow(rowData);
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("unused")
	private String formatCurrency(double tienTe) {
	    // Create a DecimalFormat with the desired format
	    currencyFormat = new DecimalFormat("###,###,### VND");
	    
	    // Format the amount as currency
	    String formattedAmount = currencyFormat.format(tienTe);
	    
	    return formattedAmount;
	}
	
	public boolean hasDataBetweenDates(String startDate, String endDate) {
	    boolean hasData = false;

	    try {
	        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

	        String sqlQuery = "SELECT COUNT(*) AS count FROM HoaDon WHERE ngayLap BETWEEN ? AND ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

	        preparedStatement.setString(1, startDate);
	        preparedStatement.setString(2, endDate);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt("count");
	            hasData = count > 0;
	        }

	        resultSet.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return hasData;
	}
	
	
	private void loadtb_DT(String ns, String ne) {
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

	        String sql = "SELECT hd.ngayLap, SUM(hd.tongTien) AS DoanhThuNgay\r\n"
	                   + "FROM HoaDon hd\r\n"
	                   + "WHERE ngayLap BETWEEN ? AND ?\r\n"
	                   + "GROUP BY hd.ngayLap;";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, ns);
	        pstmt.setString(2, ne); 

	        ResultSet rs = pstmt.executeQuery();

	        model.addColumn("Ngày");
	        model.addColumn("Doanh Thu");
	        table.setModel(model);
	        while (rs.next()) {	
	        	String dt = formatCurrency(Double.parseDouble(rs.getString("DoanhThuNgay")));
	            Object[] rowData = {
	                rs.getString("ngayLap"),
	                dt
	            };
	            model.addRow(rowData);
	        }

	        
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	private void loadtb_KH(String ns, String ne) {
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

	        // Tạo câu lệnh SQL với PreparedStatement
	        String sql = "SELECT hd.maHD, kh.tenKH, kh.SDT, hd.ngayLap, nv.tenNV, hd.tongTien " +
	                     "FROM KhachHang kh " +
	                     "INNER JOIN HoaDon hd ON kh.makH = hd.maKH " +
	                     "INNER JOIN NhanVien nv ON nv.maNV = hd.maNV " +
	                     "WHERE hd.ngayLap BETWEEN ? AND ?";

	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, ns);
	        pstmt.setString(2, ne); 
	       
	        ResultSet rs = pstmt.executeQuery();
	        
	    	model.addColumn("Mã Hóa Đơn");
			model.addColumn("Tên Khách Hàng");
			model.addColumn("SDT");
			model.addColumn("Ngày Lập");
			model.addColumn("Nhân Viên Lập HD");
			model.addColumn("Tổng Tiền");
			model.setRowCount(0);
			table.setModel(model);
	        while (rs.next()) {
	        	String dt = formatCurrency(Double.parseDouble(rs.getString("TongTien")));
	            Object[] rowData = {
	                rs.getString("maHD"),
	                rs.getString("tenKH"),
	                rs.getString("SDT"),
	                rs.getString("ngayLap"),
	                rs.getString("tenNV"),
	                dt
	            };
	            model.addRow(rowData);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private void loadtb_P(String ns, String ne) {
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

	        // Tạo câu lệnh SQL với PreparedStatement
	        String sql = "SELECT p.maPhong, COUNT(*) AS SoLanHatNhieuNhat\r\n"
	        		+ "	                FROM Phong p\r\n"
	        		+ "	                INNER JOIN ChiTietHoaDon cthd ON p.maPhong = cthd.maPhong\r\n"
	        		+ "					INNER JOIN HoaDon hd ON cthd.MaHD = hd.MaHD\r\n"
	        		+ "	                WHERE ngayLap BETWEEN ? and ?\r\n"
	        		+ "	                GROUP BY p.MaPhong\r\n"
	        		+ "	                ORDER BY COUNT(*) DESC;";

	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, ns);
	        pstmt.setString(2, ne); 
	       
	        ResultSet rs = pstmt.executeQuery();
	        
			model.addColumn("Mã Phòng");
			model.addColumn("Số lần được hát");
			model.setRowCount(0);
			table.setModel(model);
	        while (rs.next()) {
	            Object[] rowData = {
	                rs.getString("maPhong"),
	                rs.getString("SoLanHatNhieuNhat"),

	            };
	            model.addRow(rowData);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private void loadtb_DV(String ns, String ne) {
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

	        // Tạo câu lệnh SQL với PreparedStatement
	        String sql = "SELECT ctdv.maDV, dv.tenDichVu, COUNT(*) AS SoLanGoi\r\n"
	        		+ "	                FROM ChiTietDichVu ctdv\r\n"
	        		+ "	                INNER JOIN DichVu dv ON ctdv.maDV = dv.maDV\r\n"
	        		+ "	                INNER JOIN HoaDon hd ON ctdv.maHD = hd.maHD\r\n"
	        		+ "	                WHERE ngayLap BETWEEN ? and ?\r\n"
	        		+ "	                GROUP BY ctdv.maDV, dv.tenDichVu \r\n"
	        		+ "	                ORDER BY COUNT(*) DESC";

	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, ns);
	        pstmt.setString(2, ne); 
	       
	        ResultSet rs = pstmt.executeQuery();
	        
	    	model.addColumn("Mã Dịch Vụ");
			model.addColumn("Tên Dịch Vụ");
			model.addColumn("Số Lần Gọi");
			model.setRowCount(0);
			table.setModel(model);
	        while (rs.next()) {
	            Object[] rowData = {
	                rs.getString("maDV"),
	                rs.getString("tenDichVu"),
	                rs.getString("soLanGoi"),
	              
	            };
	            model.addRow(rowData);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	
	private void createChart_DTTK(String ns, String ne) {
	    DefaultCategoryDataset dataset = createDataset_DTTK(ns, ne);

	    JFreeChart chart = ChartFactory.createBarChart(
	            "Doanh Thu theo Khách Hàng", // Tiêu đề biểu đồ
	            "Khách Hàng", // Nhãn trục x
	            "Doanh Thu", // Nhãn trục y
	            dataset, // Dữ liệu
	            PlotOrientation.VERTICAL, // Định dạng biểu đồ (dọc)
	            true, // Có hiển thị legent không
	            true, // Có tạo tooltips không
	            false // Có tạo URLs không
	    );

	    CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(Color.WHITE); // Màu nền của biểu đồ
	    plot.setRangeGridlinePaint(Color.BLACK); // Màu của đường gridlines

	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(860, 670));

	    JFrame chartFrame = new JFrame("Biểu Đồ Doanh Thu Theo Khách Hàng");
	    chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    chartFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
	    chartFrame.setSize(900, 700);
	    chartFrame.setLocationRelativeTo(null);
	    chartFrame.setVisible(true);
	}

	private DefaultCategoryDataset createDataset_DTTK(String ns, String ne) {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	    try {
	        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

	        String sqlQuery = "SELECT KhachHang.TenKH, SUM(HoaDon.tongTien) AS DoanhThu "
	                + "FROM HoaDon "
	                + "INNER JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH "
	                + "WHERE HoaDon.ngayLap BETWEEN ? AND ? "
	                + "GROUP BY KhachHang.TenKH";

	        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
	        preparedStatement.setString(1, ns);
	        preparedStatement.setString(2, ne);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            String kh = resultSet.getString("TenKH");
	            int dt = resultSet.getInt("DoanhThu");
	            dataset.addValue(dt, "Doanh Thu", kh);
	        }

	        resultSet.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dataset;
	}


	/////

	private void createChart_DT(String ns, String ne) {

		DefaultCategoryDataset dataset = createDataset_DT(ns, ne);
		JFreeChart chart = ChartFactory.createBarChart("Doanh Thu theo ngày", "Ngày", "Doanh Thu", dataset,
				PlotOrientation.VERTICAL,
		        true, // Có hiển thị legent không
		        true, // Có tạo tooltips không
		        false);
		
//		chart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(119, 136, 153));
		chart.getCategoryPlot().getRenderer().setSeriesOutlinePaint(0, null);

		chart.getCategoryPlot().setOutlineVisible(true);
		chart.getCategoryPlot().setBackgroundPaint(new Color(240, 240, 240));
		CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(Color.WHITE); // Màu nền của biểu đồ
	    plot.setRangeGridlinePaint(Color.BLACK); // Màu của đường gridlines
	    
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(860, 670));
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		
		 renderer.setItemMargin(0);
		// Create a new frame to display the chart
		JFrame chartFrame = new JFrame("Biểu Đồ Doanh Thu");
		chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		chartFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
		chartFrame.setSize(900, 700);
		chartFrame.setLocationRelativeTo(null);
		chartFrame.setVisible(true);

	}

	private DefaultCategoryDataset createDataset_DT(String ns, String ne) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		try {
			Connection connection = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

			// Prepare SQL query with parameters
			String sqlQuery = "SELECT ngayLap, SUM(tongTien) AS DoanhThu\r\n" + "FROM HoaDon\r\n"
					+ "WHERE ngayLap BETWEEN ? AND ?\r\n" + "GROUP BY ngayLap";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			// Set parameters for the SQL query using dates

			preparedStatement.setString(1, ns);
			preparedStatement.setString(2, ne);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Date ngayLap = resultSet.getDate("ngayLap");
				int dt = resultSet.getInt("DoanhThu");
				dataset.addValue(dt, "Doanh Thu", ngayLap.toString());
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataset;
	}

	////
	private void createChart_KH(String ns, String ne) {

		DefaultCategoryDataset dataset = createDataset_KH(ns, ne);
		JFreeChart chart = ChartFactory.createBarChart("Số lượng Hóa Đơn theo ngày", "Ngày", "Số lượng Hóa Đơn",
				dataset, PlotOrientation.VERTICAL,
		        true,
		        true,
		        false);
		CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(Color.WHITE); // Màu nền của biểu đồ
	    plot.setRangeGridlinePaint(Color.BLACK); // Màu của đường gridlines
//		chart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(119, 136, 153));
		chart.getCategoryPlot().getRenderer().setSeriesOutlinePaint(0, null);

		chart.getCategoryPlot().setOutlineVisible(true);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(860, 670));

		// Create a new frame to display the chart
		JFrame chartFrame = new JFrame("Biểu Đồ Hóa Đơn");
		chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		chartFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
		chartFrame.setSize(900, 700);
		chartFrame.setLocationRelativeTo(null);
		chartFrame.setVisible(true);
	}

	private DefaultCategoryDataset createDataset_KH(String ns, String ne) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		try {
			Connection connection = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

			// Prepare SQL query with parameters
			String sqlQuery = "SELECT ngayLap, COUNT(maKH) AS SoLuongKhachHang " + "FROM HoaDon "
					+ "WHERE ngayLap BETWEEN ? AND ? " + "GROUP BY ngayLap";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			// Set parameters for the SQL query using dates

			preparedStatement.setString(1, ns);
			preparedStatement.setString(2, ne);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Date ngayLap = resultSet.getDate("ngayLap");
				int soLuongKhachHang = resultSet.getInt("SoLuongKhachHang");
				dataset.addValue(soLuongKhachHang, "Số lượng Hóa Đơn", ngayLap.toString());
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataset;
	}

	/////////
	private void createChart_DV(String ns, String ne) {

		DefaultCategoryDataset dataset = createDataset_DV(ns, ne);
		JFreeChart chart = ChartFactory.createBarChart("Thống Kê Dịch Vụ", "Dịch vụ", "Số lần được gọi",
				dataset, PlotOrientation.VERTICAL,
		        true, // Có hiển thị legent không
		        true, // Có tạo tooltips không
		        false);
//		chart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(119, 136, 153));
		chart.getCategoryPlot().getRenderer().setSeriesOutlinePaint(0, null);
		CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(Color.WHITE); // Màu nền của biểu đồ
	    plot.setRangeGridlinePaint(Color.BLACK); // Màu của đường gridlines
		chart.getCategoryPlot().setOutlineVisible(true);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(860, 670));

		// Create a new frame to display the chart
		JFrame chartFrame = new JFrame("Biểu Đồ Dịch Vụ");
		chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		chartFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
		chartFrame.setSize(900, 700);
		chartFrame.setLocationRelativeTo(null);
		chartFrame.setVisible(true);
	}

	private DefaultCategoryDataset createDataset_DV(String ns, String ne) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		try {
			Connection connection = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

			// Prepare SQL query with parameters
			String sqlQuery = "SELECT ctdv.maDV, dv.tenDichVu, COUNT(*) AS SoLanGoi\r\n"
					+ "	                FROM ChiTietDichVu ctdv\r\n"
					+ "	                INNER JOIN DichVu dv ON ctdv.maDV = dv.maDV\r\n"
					+ "	                INNER JOIN HoaDon hd ON ctdv.maHD = hd.maHD\r\n"
					+ "	                WHERE ngayLap BETWEEN ? and ?\r\n"
					+ "	                GROUP BY ctdv.maDV, dv.tenDichVu \r\n"
					+ "	                ORDER BY COUNT(*) DESC";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			// Set parameters for the SQL query using dates

			preparedStatement.setString(1, ns);
			preparedStatement.setString(2, ne);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String maDV = resultSet.getString("tenDichVu");
				int soLuongDichVu = resultSet.getInt("SoLanGoi");
				dataset.addValue(soLuongDichVu,"Số Lần Gọi" ,maDV);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataset;
	}

	////
	private void createChart_P(String ns, String ne) {

		DefaultCategoryDataset dataset = createDataset_P(ns, ne);
		JFreeChart chart = ChartFactory.createBarChart("Thống Kê Phòng", "Phòng", "Số lần được hát", dataset, 
				PlotOrientation.VERTICAL,
		        true, // Có hiển thị legent không
		        true, // Có tạo tooltips không
		        false);
//		chart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(119, 136, 153));
		chart.getCategoryPlot().getRenderer().setSeriesOutlinePaint(0, null);
		CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(Color.WHITE); // Màu nền của biểu đồ
	    plot.setRangeGridlinePaint(Color.BLACK); // Màu của đường gridlines
		chart.getCategoryPlot().setOutlineVisible(true);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(860, 670));

		// Create a new frame to display the chart
		JFrame chartFrame = new JFrame("Biểu Đồ Phòng");
		chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		chartFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
		chartFrame.setSize(900, 700);
		chartFrame.setLocationRelativeTo(null);
		chartFrame.setVisible(true);
	}

	private DefaultCategoryDataset createDataset_P(String ns, String ne) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		try {
			Connection connection = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Karaoke4T;user=sa;password=123;");

			// Prepare SQL query with parameters
			String sqlQuery = "SELECT p.maPhong, COUNT(*) AS SoLanHatNhieuNhat\r\n"
					+ "	                FROM Phong p\r\n"
					+ "	                INNER JOIN ChiTietHoaDon cthd ON p.maPhong = cthd.maPhong\r\n"
					+ "					INNER JOIN HoaDon hd ON cthd.MaHD = hd.MaHD\r\n"
					+ "	                WHERE ngayLap BETWEEN ? and ?\r\n"
					+ "	                GROUP BY p.MaPhong\r\n"
					+ "	                ORDER BY COUNT(*) DESC;";

			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			// Set parameters for the SQL query using dates

			preparedStatement.setString(1, ns);
			preparedStatement.setString(2, ne);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String ma = resultSet.getString("maPhong");
				int soLuong = resultSet.getInt("SoLanHatNhieuNhat");
				dataset.addValue(soLuong, "Số Lần", ma);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataset;
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

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));

		pack();
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
		GD_Main_QL mainql = new GD_Main_QL();
		mainql.setVisible(true);
	}


	private String layThongTinTen() {
		String query = "SELECT tenNV FROM TaiKhoan WHERE maTK = ?";
		try {
			con = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;databasename=Karaoke4T;user=sa;password=123");
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
