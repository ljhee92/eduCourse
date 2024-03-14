package eduCourse_prj.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.LoginVO;



public class StudentLoginDAO {
	private static StudentLoginDAO slDAO;

	private StudentLoginDAO() {

	}

	public static StudentLoginDAO getInstance() {
		if (slDAO == null) {
			slDAO = new StudentLoginDAO();

		} // end if

		return slDAO;
	}
	
	
	public LoginVO studentLogin(LoginVO lVO) throws SQLException {
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
	        String selectQuery = "SELECT STD_NUMBER, STD_PASSWORD, STD_NAME FROM student WHERE STD_NUMBER = ? AND STD_PASSWORD = ?";
	        pstmt = con.prepareStatement(selectQuery);

	        // 4. SQL 쿼리에 파라미터 설정

	        pstmt.setString(1, lVO.getId());
	        pstmt.setString(2, lVO.getPassword());

	        // 5. 쿼리 실행 및 결과 처리
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	
	            lresultVO = new LoginVO(rs.getString("STD_NUMBER"), rs.getString("STD_PASSWORD"),rs.getString("STD_NAME"));
	        }
	    } finally {
	        // 6. 리소스 해제
	        dbcon.dbClose(rs, pstmt, con);
	    }

	  
	    return lresultVO;
	}
}