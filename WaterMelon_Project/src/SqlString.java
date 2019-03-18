

import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class SqlString {

	String sql = null;
	DataBase db = null;

	public String pwFind(String id) {
		db = new DataBase();
		String result = null;
		try {
			sql = "select password from accounts where id =  '" + id + "'";
//				System.out.println(sql);
			db.stmt = db.conn.prepareStatement(sql);
			db.rs = db.stmt.executeQuery(sql);
			if (!id.equals("")) {
				if (db.rs.next())
					result = db.rs.getString("password").toString();
				else
					result = "findMissUserInfo";
			} else
				result = "notNullInputId";
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			db.close();
		}

		return result;
	}

	public void idCheck(String id) {
		db = new DataBase();
		if (!id.equals("")) {
			try {
				sql = "select id from accounts where id =  '" + id + "'";
				System.out.println(sql);
				db.stmt = db.conn.prepareStatement(sql);
				db.rs = db.stmt.executeQuery(sql);
				if (db.rs.next()) {
					JOptionPane.showMessageDialog(null, "�ߺ��� ���̵� �����մϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "��� ������ ���̵� �Դϴ�.");
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				db.close();
			}
		} else
			JOptionPane.showMessageDialog(null, "���̵�� ������ �� �����ϴ�.");

	}

	public void passwordCheck(String pw, String pwcopy) {
		db = new DataBase();
		try {
			if (pw.equals(pwcopy)) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ�մϴ�.");
			} else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			db.close();
		}

	}

	public void insertCustomer(String id, String pw, String pwcopy, String nickname) {
		db = new DataBase();
		try {
			sql = "select id from accounts where id =  '" + id + "'";
			db.stmt = db.conn.prepareStatement(sql);
			db.rs = db.stmt.executeQuery(sql);
			if (db.rs.next()) {
				JOptionPane.showMessageDialog(null, "�ߺ��� ���̵� �����մϴ�.");
			} else {
				if (!pw.equals(pwcopy)) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				} else {

					try {
						sql = "insert into accounts values(" + (db.accountIdCount + 1) + ", '" + id + "', '" + pw
								+ "', " + null + ", '" + nickname + "')";
						JOptionPane.showMessageDialog(null, "���̵� : " + id + "\n��й�ȣ : " + pw + "\n �г��� : " + nickname
								+ "\n���� ȸ�������� ���������� ó���Ǿ����ϴ�.");
						int insert = db.stmt.executeUpdate(sql);
					} catch (Exception e) {
						System.out.println(e);
					} finally {
						db.close();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			db.close();
		}

	}

	public void updateCustomer(String id, String nickname) {
		db = new DataBase();
		if (!id.equals("")) {
			try {
				sql = "select id from accounts where id =  '" + id + "'";
				System.out.println(sql);
				db.stmt = db.conn.prepareStatement(sql);
				db.rs = db.stmt.executeQuery(sql);
				if (db.rs.next()) {
					sql = "update accounts set nickname='" + nickname + "' where id='" + id + "'";
					int update = db.stmt.executeUpdate(sql);

					JOptionPane.showMessageDialog(null, "�г����� ����Ǿ����ϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "�������� �ʴ� ���̵� �Դϴ�.");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else
			JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���");

	}

	public void deleteCustomer(String id) {
		db = new DataBase();
		try {
			sql = "delete from accounts where id='" + id + "'";
			int delete = db.stmt.executeUpdate(sql);

			if (delete == 0) {
				JOptionPane.showMessageDialog(null, "������ų �ڷᰡ �����ϴ�.");
				return;
			}
			JOptionPane.showMessageDialog(null, "������ �����Ǿ����ϴ�.");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			db.close();
		}

	}

	public void userCheck(String id, String pw) {
		db = new DataBase();
		try {
			sql = "select * from accounts where id = '" + id + "'";
			db.stmt = db.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			db.rs = db.stmt.executeQuery(sql);

			if (!id.equals("") && !pw.equals("")) {
				if (db.rs.next()) {
					if (pw.equals(db.rs.getString("password").toString())) {
						sql = "select nickname from accounts where id = '" + id + "'";
						db.stmt = db.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						db.rs = db.stmt.executeQuery(sql);
						db.rs.last();
						JOptionPane.showMessageDialog(null, "�α����� �����Ͽ����ϴ�.");
						new WaterMelon(db.rs.getString("nickname").toString());
						return;
					} else {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "���Ե� ȸ���� �ƴմϴ�.");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ��� �Է����ּ���.");
				return;
			}
		} catch (Exception e) {
			System.out.println(e);
			return;
		} finally {
			db.close();
			return;
		}
	}

}