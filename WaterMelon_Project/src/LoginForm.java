

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm extends JFrame {

	SqlString ss = new SqlString();

	private JPanel contentPane;
	private JTextField txtWMId;
	private JPasswordField txtWMPw;
	static DataBase db = null;

	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		super(ConstVariable.TITLE + "Login Form");
		setBounds(100, 100, 325, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtWMId = new JTextField();
		txtWMId.setBounds(50, 8, 116, 21);
		contentPane.add(txtWMId);
		txtWMId.setColumns(10);

		txtWMPw = new JPasswordField();
		txtWMPw.setBounds(50, 39, 116, 21);
		contentPane.add(txtWMPw);
		txtWMPw.setColumns(10);

		JButton btnLogin = new JButton("\uB85C\uADF8\uC778");

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnLogin) {
					ss.userCheck(txtWMId.getText(), txtWMPw.getText());
				}
			}
		});
		btnLogin.setBounds(180, 8, 108, 52);
		contentPane.add(btnLogin);

		JButton btnJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnJoin) {
					JoinForm jf = new JoinForm();
				}
			}
		});
		btnJoin.setBounds(50, 70, 238, 21);
		contentPane.add(btnJoin);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblId.setBounds(14, 12, 42, 18);
		contentPane.add(lblId);

		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblPw.setBounds(12, 41, 42, 18);
		contentPane.add(lblPw);

		JButton btnFind = new JButton("ÆÐ½º¿öµå Ã£±â");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnFind) {
					FindForm ff = new FindForm();
				}
			}
		});
		btnFind.setBounds(50, 98, 238, 21);
		contentPane.add(btnFind);

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		return;
	}
}