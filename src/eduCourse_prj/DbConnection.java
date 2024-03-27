package eduCourse_prj;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {

	private static DbConnection dbcon;

	private DbConnection() {

	}// DbConnection

	public static DbConnection getInstance() {
		if (dbcon == null) {
			dbcon = new DbConnection();
		}

		return dbcon;
	}// getInstance

	public Connection getConnection(String url, String id, String pass) throws SQLException {
		Connection con = null;
		// 1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 2. Connection 얻기
		con = DriverManager.getConnection(url, id, pass);

		return con;

	}// getConnection

	/**
	 * 로컬 DBMS에 연동하여 Connection을 반환
	 * 
	 * @param id
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection(String id, String pass) throws SQLException {
		Connection con = null;
		// 1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:oracle:thin:@192.168.10.231:1521:orcl";
		// 2. Connection 얻기
		con = DriverManager.getConnection(url, id, pass);

		return con;

	}// getConnection

	/**
	 * 연결을 종료하는 메서드
	 * 
	 * @param rs
	 * @param stmt
	 * @param con
	 * @throws SQLException
	 */
	public void dbClose(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} finally {
			if (con != null) {
				con.close();
			}
		}

	}

}// class
