package eduCourse_prj.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.LoginVO;



public class AdminLoginDAO {
	private static AdminLoginDAO alDAO;

	private AdminLoginDAO() {

	}

	public static AdminLoginDAO getInstance() {
		if (alDAO == null) {
			alDAO = new AdminLoginDAO();

		} // end if

		return alDAO;
	}
	
	
	public LoginVO AdminLogin(LoginVO lVO) throws SQLException {
	    LoginVO lresultVO = null;
	    DbConnection dbcon = DbConnection.getInstance();
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        // 1. 데이터베이스 접속 정보
	        String id = "scott";
	        String pass = "tiger";

	        // 2. 데이터베이스 연결
	        con = dbcon.getConnection(id, pass);

	        // 3. SQL 쿼리 준비
	        String selectQuery = "SELECT ADMIN_ID, ADMIN_PASSWORD, ADMIN_NAME FROM admin WHERE ADMIN_ID = ? AND ADMIN_PASSWORD = ?";
	        pstmt = con.prepareStatement(selectQuery);

	        // 4. SQL 쿼리에 파라미터 설정

	        pstmt.setString(1, lVO.getId());
	        pstmt.setString(2, lVO.getPassword());

	        // 5. 쿼리 실행 및 결과 처리
	        rs = pstmt.executeQuery();


	        if (rs.next()) {
	            lresultVO = new LoginVO(rs.getString("ADMIN_ID"), rs.getString("ADMIN_PASSWORD"),rs.getString("ADMIN_NAME"));
	        }
	    } finally {
	        // 6. 리소스 해제
	        dbcon.dbClose(rs, pstmt, con);
	    }


	    return lresultVO;
	}
}