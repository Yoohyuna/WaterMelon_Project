

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
					JOptionPane.showMessageDialog(null, "중복된 아이디가 존재합니다.");
				} else {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				db.close();
			}
		} else
			JOptionPane.showMessageDialog(null, "아이디는 공백일 수 없습니다.");

	}

	public void passwordCheck(String pw, String pwcopy) {
		db = new DataBase();
		try {
			if (pw.equals(pwcopy)) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치합니다.");
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
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
				JOptionPane.showMessageDialog(null, "중복된 아이디가 존재합니다.");
			} else {
				if (!pw.equals(pwcopy)) {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				} else {

					try {
						sql = "insert into accounts values(" + (db.accountIdCount + 1) + ", '" + id + "', '" + pw
								+ "', " + null + ", '" + nickname + "')";
						JOptionPane.showMessageDialog(null, "아이디 : " + id + "\n비밀번호 : " + pw + "\n 닉네임 : " + nickname
								+ "\n으로 회원가입이 정상적으로 처리되었습니다.");
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

					JOptionPane.showMessageDialog(null, "닉네임이 변경되었습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "존재하지 않는 아이디 입니다.");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요");

	}

	public void deleteCustomer(String id) {
		db = new DataBase();
		try {
			sql = "delete from accounts where id='" + id + "'";
			int delete = db.stmt.executeUpdate(sql);

			if (delete == 0) {
				JOptionPane.showMessageDialog(null, "삭제시킬 자료가 없습니다.");
				return;
			}
			JOptionPane.showMessageDialog(null, "계정이 삭제되었습니다.");
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
						JOptionPane.showMessageDialog(null, "로그인이 성공하였습니다.");
						new WaterMelon(db.rs.getString("nickname").toString());
						return;
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "가입된 회원이 아닙니다.");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 모두 입력해주세요.");
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