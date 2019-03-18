

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JoinForm extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblIdCheck;
	private JTextField txtId;
	private JTextField txtPw;
	private JTextField txtPw2;
	private JTextField txtNickName;
	private JButton btnInsert;

	SqlString ss = new SqlString();
	DataBase db = null;
	private JLabel lblId;
	private JLabel lblPw;
	private JLabel lblPw_1;
	private JLabel lblNickname;
	private JLabel lblPwCheck;
	private JButton btnUpdate;
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */

	public JoinForm() {
		super(ConstVariable.TITLE + "Join Form");
		setLocation(-28, -45);
		setLocationRelativeTo(null);
		setSize(380, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtId = new JTextField();
		txtId.setBounds(104, 12, 116, 21);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtPw = new JTextField();
		txtPw.setBounds(104, 45, 116, 21);
		contentPane.add(txtPw);
		txtPw.setColumns(10);

		txtPw2 = new JTextField();
		txtPw2.setBounds(104, 78, 116, 21);
		contentPane.add(txtPw2);
		txtPw2.setColumns(10);

		txtNickName = new JTextField();
		txtNickName.setBounds(104, 114, 116, 21);
		contentPane.add(txtNickName);
		txtNickName.setColumns(10);

		btnInsert = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnInsert.setBounds(14, 156, 206, 23);
		contentPane.add(btnInsert);

		JLabel lblIdCheck = new JLabel("\uC544\uC774\uB514 \uC911\uBCF5 \uCCB4\uD06C");
		lblIdCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (e.getSource() == lblIdCheck) {
						// new SqlString().idCheck(txtId.getText());
						ss.idCheck(txtId.getText());
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		lblIdCheck.setBounds(234, 16, 110, 15);
		contentPane.add(lblIdCheck);

		lblId = new JLabel("ID");
		lblId.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblId.setBounds(16, 16, 34, 18);
		contentPane.add(lblId);

		lblPw = new JLabel("PW");
		lblPw.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblPw.setBounds(14, 47, 34, 18);
		contentPane.add(lblPw);

		lblPw_1 = new JLabel("PW_check");
		lblPw_1.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblPw_1.setBounds(14, 80, 84, 18);
		contentPane.add(lblPw_1);

		lblNickname = new JLabel("NICKNAME");
		lblNickname.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblNickname.setBounds(14, 115, 97, 18);
		contentPane.add(lblNickname);

		lblPwCheck = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		lblPwCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (e.getSource() == lblPwCheck) {
						new SqlString().passwordCheck(txtPw.getText(), txtPw2.getText());
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		lblPwCheck.setBounds(234, 80, 110, 15);
		contentPane.add(lblPwCheck);

		btnUpdate = new JButton("\uC218\uC815");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnUpdate) {
						if (btnUpdate.getText().equals("¼öÁ¤")) {
							db = new DataBase();
							txtId.setEditable(false);
							txtPw.setEditable(false);
							txtPw2.setEditable(false);
							txtNickName.setEditable(true);
							while (db.rs.next()) {
								txtNickName.setText(db.rs.getString("nickname"));
							}
							btnUpdate.setText("¿Ï·á");
						} else if (btnUpdate.getText().equals("¿Ï·á")) {
							new SqlString().updateCustomer(txtId.getText(), txtNickName.getText());
							txtId.setEditable(true);
							txtPw.setEditable(true);
							txtPw2.setEditable(true);
							txtNickName.setEditable(true);
							btnUpdate.setText("¼öÁ¤");
						}
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btnUpdate.setBounds(227, 156, 63, 23);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("\uC0AD\uC81C");
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == btnDelete) {
					new SqlString().deleteCustomer(txtId.getText());
				}

			}

		});

		btnDelete.setBounds(293, 156, 63, 23);
		contentPane.add(btnDelete);
		setResizable(false);
		setLocationRelativeTo(null);
		addListener();
		setVisible(true);
	}

	private void addListener() {
		btnInsert.addActionListener(this);// ½Å±Ô
		txtId.addActionListener(this);
		txtPw.addActionListener(this);
		txtPw2.addActionListener(this);
		txtNickName.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if (e.getSource() == btnInsert) {
				new SqlString().insertCustomer(txtId.getText(), txtPw.getText(), txtPw2.getText(),
						txtNickName.getText());
				return;
			} else if (e.getSource() == txtId) {
				txtPw.requestFocus();
				ss.idCheck(txtId.getText());
			} else if (e.getSource() == txtPw) {
				btnInsert.requestFocus();
				ss.idCheck(txtId.getText());
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}
}