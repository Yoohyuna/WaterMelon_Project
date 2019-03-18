

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FindForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtPw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	public FindForm() {
		super(ConstVariable.TITLE + "Find Form");
		setBounds(100, 100, 189, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel PnIdFind = new JPanel();
		PnIdFind.setBounds(0, 0, 183, 122);
		contentPane.add(PnIdFind);
		PnIdFind.setLayout(null);

		txtId = new JTextField();
		txtId.setBounds(33, 21, 138, 21);
		PnIdFind.add(txtId);
		txtId.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("����", Font.BOLD, 12));
		lblId.setBounds(12, 24, 24, 15);
		PnIdFind.add(lblId);

		txtPw = new JTextField();
		txtPw.setText("Password");
		txtPw.setBounds(33, 52, 138, 21);
		txtPw.setColumns(10);
		txtPw.setEditable(false);
		PnIdFind.add(txtPw);

		JButton btnFind = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SqlString ss = new SqlString();
				if (e.getSource() == btnFind) {
					txtPw.setEditable(true);
					txtPw.setText(new SqlString().pwFind(txtId.getText()));
					if (ss.pwFind(txtId.getText()).equals("findMissUserInfo")) {
						JOptionPane.showMessageDialog(null, "���Ե� ȸ���� �ƴմϴ�.");
						txtPw.setText("���Ե� ȸ���� �ƴմϴ�.");
						txtPw.setEditable(false);
						return;
					} else if (ss.pwFind(txtId.getText()).equals("notNullInputId")) {
						JOptionPane.showMessageDialog(null, "���̵� �Է��� �ּ���.");
						txtPw.setText("���̵� �Է��� �ּ���.");
						txtPw.setEditable(false);
						return;
					} else {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ã�ҽ��ϴ�!");
						txtPw.setText(ss.pwFind(txtId.getText()));
						return;
					}
				}
			}
		});

		btnFind.setBounds(33, 83, 138, 21);
		PnIdFind.add(btnFind);

		setResizable(false);

		setVisible(true);
	}
}