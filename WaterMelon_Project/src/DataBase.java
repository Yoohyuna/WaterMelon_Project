
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	public static boolean logonoff = false;
	public Connection conn;
	public Statement stmt;
	public ResultSet rs;
	public String sql = null;
	public int accountIdCount = 0;

	int iTotal = 0;
	int iLast = 0;

	public DataBase() {
		init();
	}

	public void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(ConstVariable.CONNSTR, ConstVariable.UID, ConstVariable.UPW);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ���� : " + e);
		} catch (SQLException e) {
			System.out.println("����̹� ���� ���� : " + e);
		}

		try {
			sql = "select 	*  from accounts order by accountid asc";
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last();// 10
			accountIdCount = rs.getInt("accountid");
			iTotal = rs.getRow();// ���� ���ȣ(�� ������ ���ڵ� ��ȣ)
			iLast = rs.getInt("accountid");// ������ ���ڵ����� ����ȣ�� ���ϱ�
			rs.first();
		} catch (SQLException e) {
			System.out.println("���� ���̺� �ҷ����� ���� ->" + e);
		}
	}

	public void close() {
		try {
			if (rs != null) { // �޼��忡 ���� ���� ��쵵 �����Ƿ� null�� �ƴҶ��� �ݾ��ش�.
				rs.close(); // ������ �ֱٿ� ���ͺ��� �ݴ´�.
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("�޸� ���� ���� : " + e);
		}
	}
}
