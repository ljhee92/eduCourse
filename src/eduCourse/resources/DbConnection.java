package eduCourse.resources;

import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {

	private static DbConnection dbConnection;

	private DbConnection() {
		
	}// DbConnection

	public static DbConnection getInstance() {
		if (dbConnection == null) {
			dbConnection = new DbConnection();
		} // end if
		return dbConnection;
	}// getInstance

	public Connection getConnection() throws SQLException {
		Properties properties = new Properties();
		Connection connection = null;
		
		try {
			properties.load(new FileInputStream("src/eduCourse/resources/jdbc.properties"));
			Class.forName(properties.getProperty("jdbc.driverClassName"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DB 연결 중 오류 발생");
			e.printStackTrace();
		} // end catch
		
		String url = properties.getProperty("jdbc.url");
		String id = properties.getProperty("jdbc.username");
		String pass = properties.getProperty("jdbc.password");
		
		connection = DriverManager.getConnection(url, id, pass);

		return connection;
	}// getConnection

	public void dbClose(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		try {
			if(rs != null) {
				rs.close();
			} // end if
			
			if(stmt != null) {
				stmt.close();
			} // end if
		} finally {
			if(con != null) {
				con.close();
			} // end if
		} // end finally
	} // dbClose

}// class