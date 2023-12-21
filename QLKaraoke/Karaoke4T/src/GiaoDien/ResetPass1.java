package GiaoDien;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class ResetPass1 extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private JTextField txtNewPass, txtVerify;
	private JPasswordField passNew, passVerify;
//	private JButton btnReset;
	private testbutton.Buttontest btnReset;
	public static String email;
	@SuppressWarnings("unused")
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public ResetPass1(String email) {
		this.email= email;
		initComponents();
	}
	private void initComponents() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Đặt lại mật khẩu");
	    setBounds(100, 100, 734, 505);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setLocationRelativeTo(null);
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

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
	    panel.setForeground(new Color(255, 255, 255));
	    panel.setBackground(new Color(255, 255, 255, 200));
	    panel.setBounds(201, 36, 323, 379);
	    panel.setOpaque(false);
	    contentPane.add(panel);
	    panel.setLayout(null);

	    JLabel lblNewLabel_1 = new JLabel("WELCOME");
	    lblNewLabel_1.setBounds(72, 25, 174, 37);
	    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
	    panel.add(lblNewLabel_1);

	    JLabel lblNewLabel_2 = new JLabel("Karaoke 4T");
	    lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    lblNewLabel_2.setBounds(107, 72, 118, 17);
	    panel.add(lblNewLabel_2);

	    btnReset = new testbutton.Buttontest();
	    btnReset.setText("Reset");	    
	    btnReset.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Check if any of the fields are empty
	            if (passNew.getPassword().length == 0 || 
		                passVerify.getPassword().length == 0) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
		                return; // Exit the method if any field is empty
		            }

	            String newPassword = new String(passNew.getPassword());
	            String confirmPassword = new String(passVerify.getPassword());

	            // Check if the new passwords match
	            if (newPassword.equals(confirmPassword)) {
	                // Gọi phương thức để cập nhật mật khẩu trong cơ sở dữ liệu
	                SendEmail1.updatePasswordInDatabase(email, newPassword);
	                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
	                GD_Login login = new GD_Login();
	                login.setVisible(true);
	                dispose();
	            } else {
	                JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không đúng");
	            }
	        }
	    });
	    btnReset.setBounds(44, 329, 239, 40);
	    panel.add(btnReset);
	    btnReset.setForeground(Color.WHITE);
	    btnReset.setBackground(Color.BLACK);
	    btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));

	    JLabel lblNewLabel_4 = new JLabel("Mật khẩu mới");
	    lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblNewLabel_4.setBounds(44, 168, 118, 17);
	    panel.add(lblNewLabel_4);

	    JLabel lblNewLabel_4_1 = new JLabel("Nhập lại mật khẩu");
	    lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblNewLabel_4_1.setBounds(44, 234, 118, 17);
	    panel.add(lblNewLabel_4_1);
	    
	    passVerify = new JPasswordField();
	    passVerify.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    passVerify.setBounds(44, 257, 240, 40);
	    panel.add(passVerify);
	    
	    passNew = new JPasswordField();
	    passNew.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    passNew.setBounds(44, 191, 240, 40);
	    panel.add(passNew);
	    passNew.setColumns(10);

	    JLabel lblNewLabel = new JLabel("");
	    lblNewLabel.setIcon(new ImageIcon(ResetPass1.class.getResource("/Imgs/BG_login.jpg")));
	    lblNewLabel.setBounds(0, 0, 728, 468);
	    contentPane.add(lblNewLabel);
	}
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ResetPass1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResetPass1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResetPass1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResetPass1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		ResetPass1 aaa = new ResetPass1(email);
		aaa.setVisible(true);
	}

}
