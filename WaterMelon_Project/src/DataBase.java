
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
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("드라이버 연결 실패 : " + e);
		}

		try {
			sql = "select 	*  from accounts order by accountid asc";
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last();// 10
			accountIdCount = rs.getInt("accountid");
			iTotal = rs.getRow();// 현재 행번호(맨 마지막 레코드 번호)
			iLast = rs.getInt("accountid");// 마지막 레코드행의 고객번호만 구하기
			rs.first();
		} catch (SQLException e) {
			System.out.println("계정 테이블 불러오기 실패 ->" + e);
		}
	}

	public void close() {
		try {
			if (rs != null) { // 메서드에 따라 없는 경우도 있으므로 null이 아닐때만 닫아준다.
				rs.close(); // 닫을땐 최근에 연것부터 닫는다.
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("메모리 해제 에러 : " + e);
		}
	}
}
