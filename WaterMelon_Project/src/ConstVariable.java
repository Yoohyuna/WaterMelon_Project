
public class ConstVariable {

//	public static final String CREATE_ACCOUNTS = "create table accounts(accountid number, id varchar2(20) not null, password varchar2(30) not null, password_2nd number(6), nickname varchar2(20) not null)";
//	public static final String CREATE_ACCOUNTS_PRIMARY_KEY = "alter table accounts add constraint accounts_pk_accountid primary key(accountid)";
//	public static final String SEQUENCESQL = "create SEQUENCE seq_inc START WITH 1 INCREMENT BY 1 NOCACHE";
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String CONNSTR = "jdbc:oracle:thin:@192.168.0.111:1521/orcl";
	public static final String UID = "scott";
	public static final String UPW = "tiger";
	public static final String TITLE = "WaterMelon Music Player_";
	// String directory = System.getProperty("user.dir");
	public static final String DIRECTORY = "\\\\192.168.0.111\\WaterMelon\\SubFile";
}
